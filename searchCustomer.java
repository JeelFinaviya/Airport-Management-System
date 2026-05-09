package Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class searchCustomer extends JFrame implements ActionListener {
    public static PassengerLinkedList list = new PassengerLinkedList();
    JLabel a, name, cName, nName, nationality, address, Add, gender, labelGender;
    JTextField aadhar;
    JButton search,exit;
    JPanel main;

    public static void main(String[] args) throws Exception {
        new searchCustomer();
    }

    public searchCustomer() throws Exception {

        main = new JPanel();
        main.setLayout(null);
        main.setBackground(new Color(30, 30, 30));
        main.setBounds(0, 0, 800, 400);

        name = new JLabel("Name          :");
        name.setBounds(60, 90, 150, 22);
        name.setFont(new Font("Tahoma", Font.PLAIN, 16));
        name.setForeground(Color.white);

        cName = new JLabel();
        cName.setBounds(215, 90, 150, 25);


        nationality = new JLabel("Nationality   :");
        nationality.setBounds(60, 130, 150, 25);
        nationality.setFont(new Font("Tahoma", Font.PLAIN, 16));
        nationality.setForeground(Color.white);

        nName = new JLabel();
        nName.setBounds(215, 130, 150, 25);


        address = new JLabel("Address       :");
        address.setBounds(60, 170, 150, 22);
        address.setFont(new Font("Tahoma", Font.PLAIN, 16));
        address.setForeground(Color.white);

        Add = new JLabel();
        Add.setBounds(215, 170, 150, 25);


        gender = new JLabel("Gender        :");
        gender.setBounds(60, 210, 100, 22);
        gender.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gender.setForeground(Color.white);

        labelGender = new JLabel();
        labelGender.setBounds(215, 210, 150, 25);


        a = new JLabel("AADHAR Number");
        a.setFont(new Font("Tahoma", Font.PLAIN, 16));
        a.setForeground(Color.white);
        a.setBounds(50, 50, 250, 25);

        aadhar = new JTextField();
        aadhar.setBounds(210, 50, 120, 25);

        search = new JButton("Show Detail");
        search.setBounds(340, 50, 120, 25);
        search.setFocusable(false);
        search.addActionListener(this);

        exit = new JButton("Exit");
        exit.setBackground(Color.red);
        exit.setForeground(Color.white);
        exit.setBounds(200, 260, 120, 25);
        exit.setFocusable(false);
        exit.addActionListener(this);


        this.setLayout(null);
        this.setBounds(400, 200, 800, 400);
        this.setVisible(true);
        this.add(main);
        main.add(a);
        main.add(aadhar);
        main.add(search);

        main.add(name);
        main.add(nName);
        main.add(cName);
        main.add(nationality);
        main.add(address);
        main.add(Add);
        main.add(gender);
        main.add(labelGender);
        main.add(exit);


    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == search) {
            try {
                Conn con = new Conn();
                String s = "select * from passenger";
                ResultSet rs = con.st.executeQuery(s);
                while (rs.next()) {
                    Passenger found = new Passenger(rs.getString("name"), rs.getString("nationality"), rs.getString("phone"), rs.getString("address"), rs.getString("aadhar"), rs.getString("gender"));
                    list.add(found);
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            String aadhar1 = aadhar.getText();
            if (!aadhar1.isEmpty() && aadhar1.length()==12) {
                boolean b = true;

                for(int i =0;i<aadhar1.length();i++){
                   if(aadhar1.charAt(i)<'0'||aadhar1.charAt(i)>'9'){
                       b=false;
                   }
                }

                if(b) {
                    Passenger found = list.searchByAadhar(aadhar1);
                    if (found != null) {
                        cName.setText(found.name);
                        cName.setFont(new Font("Tahoma", Font.PLAIN, 14));
                        cName.setForeground(Color.white);

                        nName.setText(found.nationality);
                        nName.setFont(new Font("Tahoma", Font.PLAIN, 14));
                        nName.setForeground(Color.white);

                        Add.setText(found.address);
                        Add.setFont(new Font("Tahoma", Font.PLAIN, 14));
                        Add.setForeground(Color.white);

                        labelGender.setText(found.gender);
                        labelGender.setFont(new Font("Tahoma", Font.PLAIN, 14));
                        labelGender.setForeground(Color.white);


                    }else {
                        JOptionPane.showMessageDialog(null, "No Customer Found.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Aadhar Contains(0-9).", "Error", JOptionPane.ERROR_MESSAGE);

                }
            }else {
                JOptionPane.showMessageDialog(null, "Enter All Field OR Valid Field.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource()==exit){
            this.dispose();
        }
    }
}