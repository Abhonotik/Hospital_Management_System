package hospital.management.system;

import com.mysql.cj.protocol.Resultset;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class search_room extends JFrame {

    Choice choice ;
    JTable table;
    search_room(){

        JPanel panel = new JPanel();
        panel.setBounds(5,5,690,490);
        panel.setBackground(new Color(90,156,163));
        panel.setLayout(null);
        add(panel);

        JLabel For = new JLabel("Search for Room");
        For.setBounds(250,11,186,31);
        For.setForeground(Color.white);
        For.setFont(new Font("Tahoma",Font.BOLD,20));
        panel.add(For);

        JLabel status = new JLabel("Status ");
        status .setBounds(70,73,80,20);
        status .setForeground(Color.white);
        status .setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(status);


        choice  = new Choice();
        choice.setBounds(170,70,120,20);
        choice.add("Available");
        choice.add("Occupied");
        panel.add(choice);

        table = new JTable();
        table.setBounds(0,187,700,210);
        table.setBackground(new Color(90,156,163));
        table.setForeground(Color.white);
        panel.add(table);

        try{
            Conn c = new Conn();
            String q = "Select * from room";
            ResultSet resultset = c.statement.executeQuery(q);
            table.setModel(DbUtils.resultSetToTableModel(resultset));
        }
        catch (Exception e )
        {
            e.printStackTrace();
        }

        JLabel Roomno = new JLabel("Room No  ");
        Roomno  .setBounds(23,162,150,20);
        Roomno  .setForeground(Color.white);
        Roomno .setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(Roomno );


        JLabel available = new JLabel("Availability ");
        available  .setBounds(210,162,150,20);
        available.setForeground(Color.white);
        available.setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(available );


        JLabel price  = new JLabel("Price ");
        price.setBounds(400,162,150,20);
        price.setForeground(Color.white);
        price .setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(price);


        JLabel Bed = new JLabel("Bed Type ");
        Bed .setBounds(580,162,150,20);
        Bed .setForeground(Color.white);
        Bed .setFont(new Font("Tahoma",Font.BOLD,14));
        panel.add(Bed );


        JButton search = new JButton("Search ");
        search.setBounds(200,420,120,25);
        search.setForeground(Color.WHITE);
        search.setBackground(Color.BLACK);
        panel.add(search);
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String q = "select * from Room where  Available ='"+choice.getSelectedItem()+"'";
                try{
                    Conn c = new Conn();
                    ResultSet resultSet= c.statement.executeQuery(q);
                    table.setModel(DbUtils.resultSetToTableModel(resultSet));

                }
                catch(Exception i){
                    i.printStackTrace();

                }
            }
        });
        JButton back = new JButton("Back");
        back.setBounds(380,420,120,25);
        back.setForeground(Color.WHITE);
        back.setBackground(Color.BLACK);
        panel.add(back);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        setUndecorated(true);
        setSize(700,500);
        setLayout(null);
        setLocation(450,250);
        setVisible(true);
    }

    public static void main(String[] args) {
        new search_room();
    }
}
