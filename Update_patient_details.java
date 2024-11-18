package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Update_patient_details extends JFrame {

    Update_patient_details() {

        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 940, 490);
        panel.setBackground(new Color(90, 156, 163));
        panel.setLayout(null);
        add(panel);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource("icon/pngtree-new-update-banner-template-isolated-png-image_8856147.png"));
        Image image = imageIcon.getImage().getScaledInstance(300, 300, Image.SCALE_DEFAULT);
        JLabel label = new JLabel(new ImageIcon(image));
        label.setBounds(500, 60, 300, 300);
        panel.add(label);

        JLabel label1 = new JLabel("Update Patient Details");
        label1.setBounds(124, 11, 250, 50);
        label1.setFont(new Font("Tahoma", Font.BOLD, 20));
        label1.setForeground(Color.WHITE);
        panel.add(label1);

        JLabel label2 = new JLabel("Name:");
        label2.setBounds(24, 88, 150, 14);
        label2.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label2.setForeground(Color.WHITE);
        panel.add(label2);

        Choice choice = new Choice();
        choice.setBounds(248, 85, 140, 25);
        panel.add(choice);

        try {
            Conn c = new Conn();
            ResultSet resultSet = c.statement.executeQuery("select * from Patient_info");
            while (resultSet.next()) {
                choice.add(resultSet.getString("Name"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        JLabel label3 = new JLabel("Phone Number:");
        label3.setBounds(24, 129, 100, 14);
        label3.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label3.setForeground(Color.WHITE);
        panel.add(label3);

        JTextField textFieldR = new JTextField();
        textFieldR.setBounds(248, 129, 140, 20);
        panel.add(textFieldR);

        JLabel label4 = new JLabel("In-Time");
        label4.setBounds(24, 174, 100, 14);
        label4.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label4.setForeground(Color.WHITE);
        panel.add(label4);

        JTextField textFieldINTime = new JTextField();
        textFieldINTime.setBounds(248, 170, 140, 20);
        panel.add(textFieldINTime);

        JLabel label5 = new JLabel("Amount Paid (Rs):");
        label5.setBounds(24, 216, 150, 14);
        label5.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label5.setForeground(Color.WHITE);
        panel.add(label5);

        JTextField textFieldAmounnt = new JTextField();
        textFieldAmounnt.setBounds(248, 216, 140, 20);
        panel.add(textFieldAmounnt);

        JLabel label6 = new JLabel("Pending Amount (Rs):");
        label6.setBounds(24, 261, 150, 14);
        label6.setFont(new Font("Tahoma", Font.PLAIN, 14));
        label6.setForeground(Color.WHITE);
        panel.add(label6);

        JTextField textFieldpending = new JTextField();
        textFieldpending.setBounds(248, 261, 140, 20);
        textFieldpending.setEditable(false);
        panel.add(textFieldpending);

        JButton check = new JButton("CHECK");
        check.setBounds(281, 378, 89, 23);
        check.setBackground(Color.BLACK);
        check.setForeground(Color.WHITE);
        panel.add(check);

        check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = choice.getSelectedItem();
                String patientQuery = "SELECT * FROM Patient_info WHERE Name = '" + id + "'";

                try {
                    Conn c = new Conn();
                    ResultSet resultSet = c.statement.executeQuery(patientQuery);
                    if (resultSet.next()) {
                        textFieldR.setText(resultSet.getString("Room_Number"));
                        textFieldINTime.setText(resultSet.getString("Time"));
                        textFieldAmounnt.setText(resultSet.getString("Deposit"));
                    } else {
                        JOptionPane.showMessageDialog(null, "Patient not found!");
                        return;
                    }

                    String roomQuery = "SELECT * FROM room WHERE room_no = '" + textFieldR.getText() + "'";
                    ResultSet resultSet1 = c.statement.executeQuery(roomQuery);
                    if (resultSet1.next()) {
                        int price = Integer.parseInt(resultSet1.getString("Price"));
                        int deposit = Integer.parseInt(textFieldAmounnt.getText());
                        int pendingAmount = price - deposit;
                        textFieldpending.setText(String.valueOf(pendingAmount));
                    } else {
                        JOptionPane.showMessageDialog(null, "Room details not found!");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage());
                }
            }
        });

        JButton update = new JButton("Update");
        update.setBounds(56, 378, 89, 23);
        update.setBackground(Color.BLACK);
        update.setForeground(Color.WHITE);

        panel.add(update);
        update.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Conn c = new Conn();
                    String q=choice.getSelectedItem();
                    String room = textFieldR.getText();
                    String time = textFieldINTime.getText();
                    String amount = textFieldAmounnt.getText();
                    c.statement.executeUpdate("UPDATE Patient_info SET room_NUMBER = '" + room + "', Time = '" + time + "', Deposit = '" + amount + "' WHERE name = '" + q + "'");
                    JOptionPane.showMessageDialog(null,"Update Successfully");
                    setVisible(false);

                }
                catch(Exception k){
                    k.printStackTrace();
                }
            }
        });


        JButton back = new JButton("Back");
        back.setBounds(168, 378, 89, 23);
        back.setBackground(Color.BLACK);
        back.setForeground(Color.WHITE);
        panel.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });


        setUndecorated(true);
        setSize(950, 500);
        setLayout(null);
        setLocation(400, 250);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Update_patient_details();
    }
}
