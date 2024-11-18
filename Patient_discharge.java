package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class Patient_discharge extends JFrame {

    Patient_discharge() {

        // Main Panel
        JPanel panel = new JPanel();
        panel.setBounds(5, 5, 790, 390);
        panel.setLayout(null);
        panel.setBackground(new Color(90, 156, 163));
        add(panel);

        // Title Label
        JLabel label = new JLabel("Check Out");
        label.setBounds(100, 20, 150, 20);
        label.setFont(new Font("Tahoma", Font.BOLD, 20));
        label.setForeground(Color.WHITE);
        panel.add(label);

        // Customer ID
        JLabel label1 = new JLabel("Customer ID");
        label1.setBounds(30, 80, 150, 20);
        label1.setFont(new Font("Tahoma", Font.BOLD, 14));
        label1.setForeground(Color.WHITE);
        panel.add(label1);

        Choice choice = new Choice();
        choice.setBounds(200, 80, 150, 25);
        panel.add(choice);

        try {
            Conn c = new Conn();
            ResultSet resultSet = c.statement.executeQuery("SELECT * FROM Patient_info");
            while (resultSet.next()) {
                choice.add(resultSet.getString("number"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Room Number
        JLabel label3 = new JLabel("Room Number");
        label3.setBounds(30, 130, 150, 20);
        label3.setFont(new Font("Tahoma", Font.BOLD, 14));
        label3.setForeground(Color.WHITE);
        panel.add(label3);

        JLabel RNo = new JLabel();
        RNo.setBounds(200, 130, 150, 20);
        RNo.setFont(new Font("Tahoma", Font.BOLD, 14));
        RNo.setForeground(Color.WHITE);
        panel.add(RNo);

        // In Time
        JLabel label4 = new JLabel("In Time");
        label4.setBounds(30, 180, 250, 20);
        label4.setFont(new Font("Tahoma", Font.BOLD, 14));
        label4.setForeground(Color.WHITE);
        panel.add(label4);

        JLabel InTime = new JLabel();
        InTime.setBounds(200, 180, 250, 20);
        InTime.setFont(new Font("Tahoma", Font.BOLD, 14));
        InTime.setForeground(Color.WHITE);
        panel.add(InTime);

        // Out Time
        JLabel label5 = new JLabel("Out Time");
        label5.setBounds(30, 230, 150, 20);
        label5.setFont(new Font("Tahoma", Font.BOLD, 14));
        label5.setForeground(Color.WHITE);
        panel.add(label5);

        Date date = new Date();
        JLabel OutTime = new JLabel(date.toString());
        OutTime.setBounds(200, 230, 250, 20);
        OutTime.setFont(new Font("Tahoma", Font.BOLD, 14));
        OutTime.setForeground(Color.WHITE);
        panel.add(OutTime);

        // Discharge Button
        JButton discharge = new JButton("Discharge");
        discharge.setBounds(30, 300, 120, 30);
        discharge.setBackground(Color.BLACK);
        discharge.setForeground(Color.WHITE);
        panel.add(discharge);
        discharge.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Conn c = new Conn();
                try {
                    String patientId = choice.getSelectedItem();
                    String roomNumber = RNo.getText();

                    // Validate fields
                    if (patientId == null || patientId.isEmpty() || roomNumber == null || roomNumber.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Please select a valid patient!", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Execute discharge operations
                    String deleteQuery = "DELETE FROM Patient_info WHERE number = '" + patientId + "'";
                    String updateRoomQuery = "UPDATE room SET Available = 'Available' WHERE room_no = '" + roomNumber + "'";

                    int rowsDeleted = c.statement.executeUpdate(deleteQuery);
                    int rowsUpdated = c.statement.executeUpdate(updateRoomQuery);

                    if (rowsDeleted > 0 && rowsUpdated > 0) {
                        JOptionPane.showMessageDialog(null, "Patient successfully discharged.");
                        setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to discharge patient. Please try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    }

                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "An error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Check Button
        JButton Check = new JButton("Check");
        Check.setBounds(170, 300, 120, 30);
        Check.setBackground(Color.BLACK);
        Check.setForeground(Color.WHITE);
        panel.add(Check);
        Check.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Conn c = new Conn();
                try {
                    String selectedPatientId = choice.getSelectedItem();
                    String query = "SELECT * FROM Patient_info WHERE number = '" + selectedPatientId + "'";
                    ResultSet resultSet = c.statement.executeQuery(query);

                    if (resultSet.next()) {
                        RNo.setText(resultSet.getString("Room_Number"));
                        InTime.setText(resultSet.getString("Time"));
                    } else {
                        RNo.setText("Not Found");
                        InTime.setText("Not Found");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Back Button
        JButton Back = new JButton("Back");
        Back.setBounds(300, 300, 120, 30);
        Back.setBackground(Color.BLACK);
        Back.setForeground(Color.WHITE);
        panel.add(Back);
        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        // Frame Settings
        setUndecorated(true);
        setSize(800, 400);
        setLayout(null);
        setLocation(400, 250);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Patient_discharge();
    }
}
