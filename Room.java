package hospital.management.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Room extends JFrame {

    JTable table;

    Room() {
        // Create the panel
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 890, 490);
        panel.setBackground(new Color(90, 156, 163));
        panel.setLayout(null);
        add(panel);

        // Add an image
        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/h.jpeg"));
        Image image = imageIcon.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon(image);
        JLabel label = new JLabel(imageIcon1);
        label.setBounds(600, 150, 200, 200);
        panel.add(label);

        // Create the table
        table = new JTable();
        table.setBounds(10, 40, 500, 200);
        table.setBackground(new Color(90, 156, 163));
        panel.add(table);

        // Populate the table with data for available rooms only
        try {
            Conn c = new Conn();
            String q = "SELECT * FROM room WHERE is_allocated = FALSE"; // Query to fetch only available rooms
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Add labels for the table columns
        JLabel label1 = new JLabel("Room No");
        label1.setBounds(12, 15, 80, 15);
        label1.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(label1);

        JLabel label2 = new JLabel("Available");
        label2.setBounds(140, 15, 80, 15);
        label2.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(label2);

        JLabel label3 = new JLabel("Price");
        label3.setBounds(290, 15, 80, 15);
        label3.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(label3);

        JLabel label4 = new JLabel("Bed Type");
        label4.setBounds(400, 15, 80, 15);
        label4.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(label4);

        // Add button for allocating a room
        JButton allocateButton = new JButton("Allocate Room");
        allocateButton.setBounds(200, 300, 150, 30);
        allocateButton.setBackground(Color.BLACK);
        allocateButton.setForeground(Color.WHITE);
        panel.add(allocateButton);

        // Action for allocating a room
        allocateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow != -1) {
                    String roomNo = table.getValueAt(selectedRow, 0).toString();
                    try {
                        Conn c = new Conn();
                        // Update the room as allocated
                        String updateQuery = "UPDATE room SET is_allocated = TRUE WHERE room_no = '" + roomNo + "'";
                        c.statement.executeUpdate(updateQuery);
                        JOptionPane.showMessageDialog(null, "Room " + roomNo + " allocated successfully!");
                        dispose(); // Close current window
                        new Room(); // Refresh the frame to reflect updated availability
                    } catch (Exception ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(null, "Error allocating room!");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Please select a room to allocate.");
                }
            }
        });

        // Create the Back button
        JButton back = new JButton("Back");
        back.setBounds(400, 300, 120, 30);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        panel.add(back);

        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current frame
            }
        });

        // Set JFrame properties
        setSize(900, 500);
        setLocation(300, 230);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Room();
    }
}
