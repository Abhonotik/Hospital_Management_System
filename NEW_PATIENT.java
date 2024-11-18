package hospital.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Date;

public class NEW_PATIENT extends JFrame implements ActionListener {

    JComboBox ComboBox;
    JTextField textFieldNumber , textName , textFieldDisease, textFieldDeposite;
    JRadioButton r1,r2;
    Choice c1;

    JLabel date;
    JButton b1,b2;

    NEW_PATIENT(){

        JPanel panel = new JPanel();
        panel.setBounds(5,5,840,540);
        panel.setBackground(new Color(90,156,163));
        panel.setLayout(null);
        add(panel);

        ImageIcon imageIcon = new ImageIcon(ClassLoader.getSystemResource(("icon/logo.png")));
        Image image =  imageIcon.getImage().getScaledInstance(300,300,Image.SCALE_DEFAULT);
        ImageIcon imageIcon1 = new ImageIcon (image);
        JLabel label = new JLabel(imageIcon1);
        label.setBounds(500,150,300,300);
        panel.add(label);

        JLabel labelName = new JLabel("NEW PATIENT FORM");
        labelName.setBounds(60,11,260,53);
        labelName.setFont(new Font("Tahoma",Font.BOLD,20));
        panel.add(labelName);

        JLabel labelID = new JLabel("ID:");
        labelID.setBounds(35,76,200,16);
        labelID.setFont(new Font("Tahoma",Font.BOLD,20));
        labelID.setForeground(Color.WHITE);
        panel.add(labelID);

        ComboBox = new JComboBox(new String[] {"Aadhar Card","Voter ID","Driving License"});
        ComboBox.setBounds(150,73,150,20);
        ComboBox.setBackground(new Color(255, 255, 255));
        ComboBox.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(ComboBox);

        JLabel labelNumber = new JLabel("Number:");
        labelNumber.setBounds(35,111,200,20);
        labelNumber.setFont(new Font("Tahoma",Font.BOLD,20));
        labelNumber.setForeground(Color.WHITE);
        panel.add(labelNumber);

        textFieldNumber = new JTextField();
        textFieldNumber.setBounds(150,111,150,20);
        panel.add(textFieldNumber);

        JLabel labelName1 = new JLabel("Name:");
        labelName1.setBounds(35,151,200,20);
        labelName1.setFont(new Font("Tahoma",Font.BOLD,20));
        labelName1.setForeground(Color.WHITE);
        panel.add(labelName1);

        textName = new JTextField();
        textName.setBounds(150,151,150,20);
        panel.add(textName);

        JLabel labelGender = new JLabel("Gender:");
        labelGender.setBounds(35,191,200,20);
        labelGender.setFont(new Font("Tahoma",Font.BOLD,20));
        labelGender.setForeground(Color.WHITE);
        panel.add(labelGender);

        r1= new JRadioButton("MALE");
        r1.setFont(new Font("Tahoma",Font.BOLD,14));
        r1.setBackground(new Color(189,164,170));
        r1.setForeground(Color.WHITE);
        r1.setBounds(150,191,80,16);
        panel.add(r1);

        r2= new JRadioButton("FEMALE");
        r2.setFont(new Font("Tahoma",Font.BOLD,14));
        r2.setBackground(new Color(189,164,170));
        r2.setForeground(Color.WHITE);
        r2.setBounds(250,191,90,16);
        panel.add(r2);

        JLabel labelDisease = new JLabel("Disease:");
        labelDisease.setBounds(35,231,200,20);
        labelDisease.setFont(new Font("Tahoma",Font.BOLD,20));
        labelDisease.setForeground(Color.WHITE);
        panel.add(labelDisease);

        textFieldDisease = new JTextField();
        textFieldDisease.setBounds(150,231,150,20);
        panel.add(textFieldDisease);


        JLabel labelRoom = new JLabel("Room:");
        labelRoom.setBounds(35,274,100,20);
        labelRoom.setFont(new Font("Tahoma",Font.BOLD,20));
        labelRoom.setForeground(Color.WHITE);
        panel.add(labelRoom);

        // Room choice //

        c1 = new Choice();
        try{
            Conn c = new Conn();
            ResultSet resultSet = c.statement.executeQuery("select * from Room");
            while(resultSet.next()){
                c1.add(resultSet.getNString("room_no"));
            }
        }
        catch(Exception E ){
            E.printStackTrace();

        }

        c1.setBounds(150,274,150,20);
        c1.setFont(new Font ("Tahoma",Font.BOLD,14));
        c1.setForeground(Color.WHITE);
        c1.setBackground(new Color(3,45,40));
        panel.add(c1);

        JLabel labelDate = new JLabel("TIME:");
        labelDate.setBounds(35,316,200,20);
        labelDate.setFont(new Font("Tahoma",Font.BOLD,20));
        labelDate.setForeground(Color.WHITE);
        panel.add(labelDate);

        Date date1 = new Date();
        date= new JLabel(""+date1);
        date.setBounds(150,316,250,20);
        date.setForeground(Color.WHITE);
        date.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(date);


        JLabel labelDeposite = new JLabel("Deposite:");
        labelDeposite.setBounds(35,359,200,20);
        labelDeposite.setFont(new Font("Tahoma",Font.BOLD,20));
        labelDeposite.setForeground(Color.WHITE);
        panel.add(labelDeposite);

        textFieldDeposite = new JTextField();
        textFieldDeposite.setBounds(150,359,150,20);
        panel.add(textFieldDeposite);


        b1= new JButton("ADD");
        b1.setBounds(100,430,120,30);
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.addActionListener(this);
        panel.add(b1);

        b2= new JButton("BACK");
        b2.setBounds(250,430,120,30);
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.addActionListener(this);
        panel.add(b2);





        setUndecorated(true);
        setSize(850,550);
        setLayout(null);
        setLocation(300,250);
        setVisible(true);
    }

    public static void main(String[] args) {
        new NEW_PATIENT();
    }

    @Override

    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == b1) {
            // Collect data from form
            String radioBTN = null;
            if (r1.isSelected()) {
                radioBTN = "MALE";
            } else if (r2.isSelected()) {
                radioBTN = "FEMALE";
            }

            String s1 = (String) ComboBox.getSelectedItem();
            String s2 = textFieldNumber.getText();
            String s3 = textName.getText();
            String s4 = radioBTN;
            String s5 = textFieldDisease.getText();
            String s6 = c1.getSelectedItem();
            String s7 = date.getText();
            String s8 = textFieldDeposite.getText();

            // Validate form fields
            if (s2.isEmpty() || s3.isEmpty() || s4 == null || s5.isEmpty() || s6.isEmpty() || s8.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                Conn c = new Conn();
                // Insert patient details into the database
                String q = "INSERT INTO Patient_info (ID, Number, Name, Gender, Patient_Disease, Room_Number, Time, Deposit) VALUES ('"
                        + s1 + "','" + s2 + "','" + s3 + "','" + s4 + "','" + s5 + "','" + s6 + "','" + s7 + "','" + s8 + "')";
                String q2 = "UPDATE Room SET Available = 'Occupied' WHERE room_no = '" + s6 + "'";

                c.statement.executeUpdate(q);
                c.statement.executeUpdate(q2);

                // Show success message
                JOptionPane.showMessageDialog(null, "Patient added successfully!");

            } catch (Exception ex) {
                // Log the exception for debugging
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error while adding patient: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else if (e.getSource() == b2) {
            setVisible(false);
        }
    }

}