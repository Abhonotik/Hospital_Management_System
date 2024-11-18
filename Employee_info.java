package hospital.management.system;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Employee_info extends JFrame {
    Employee_info() {
        // Create the main panel
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 990, 590);
        panel.setBackground(new Color(109, 164, 170));
        panel.setLayout(null);
        add(panel);

        // Create and configure the table
        JTable table = new JTable();
        table.setBounds(10, 34, 980, 450);
        table.setBackground(new Color(109, 164, 170));
        table.setFont(new Font("Tahoma", Font.BOLD, 12));
        panel.add(table);

        // Populate the table with data from the database
        try {
            Conn c = new Conn();
            String q = "SELECT * FROM EMP_INFO";
            ResultSet resultSet = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultSet));
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Add column labels
        JLabel label1 = new JLabel("Name");
        label1.setBounds(41, 11, 70, 20);
        label1.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(label1);

        JLabel label2 = new JLabel("Age");
        label2.setBounds(180, 11, 70, 20);
        label2.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(label2);

        JLabel label3 = new JLabel("Phone");
        label3.setBounds(350, 11, 150, 20);
        label3.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(label3);

        JLabel label4 = new JLabel("Salary");
        label4.setBounds(500, 11, 70, 20);
        label4.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(label4);

        JLabel label5 = new JLabel("Gmail");
        label5.setBounds(700, 11, 70, 20);
        label5.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(label5);

        JLabel label6 = new JLabel("Aadhar");
        label6.setBounds(850, 11, 70, 20);
        label6.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(label6);

        // Create the Back button
        JButton button = new JButton("BACK");
        button.setBounds(350, 500, 120, 30);
        button.setBackground(Color.BLACK);
        button.setForeground(Color.WHITE);
        panel.add(button);

        // Add action listener to the Back button
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Close the current frame
                dispose(); // This will close the current window
                // Optionally, you can instantiate and show a previous frame here
                // For example: new PreviousFrame().setVisible(true);
            }
        });

        // JFrame settings
        setSize(1000, 600);
        setLocation(350, 230);
        setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ensure the application exits when closed
    }

    public static void main(String[] args) {
        new Employee_info();
    }
}
