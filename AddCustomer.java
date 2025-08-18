package Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.Stack;

import static java.awt.Font.*;

public class AddCustomer extends JFrame implements ActionListener {
    public static PassengerLinkedList list=new PassengerLinkedList();
    JLabel heading,name,nationality,aadhar,address,gender,phone;
    JTextField cName,nName,aNumber,Add,pNumber;
    JRadioButton male,female,other;
    JButton save,Exit1,search;
    ImageIcon addCustomer;
    JPanel header,main;

    public static void main(String[] args) throws Exception {
        new AddCustomer();
    }

    public AddCustomer() throws Exception {

        header  = new JPanel();
        header.setBackground(new Color(0,0,0));
        header.setLayout(null);
        header.setBounds(0,0,2000,40);


        main  = new JPanel();
        main.setBackground(new Color(30,30,30));
        main.setLayout(null);
        main.setBounds(0,20,2000,1000);


        heading = new JLabel("≡Add Customer Details:");
        heading.setBounds(10, 5, 700, 35);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 24));
        heading.setForeground(Color.white);

        header.add(heading);


        name = new JLabel("Name*");
        name.setBounds(60, 75, 100, 22);
        name.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        name.setForeground(Color.white);
        main.add(name);


        cName = new JTextField();
        cName.setBounds(60, 100, 250, 25);
        main.add(cName);

        nationality = new JLabel("Nationality*");
        nationality.setBounds(60, 130, 100, 25);
        nationality.setFont(new Font("segoe UI", Font.PLAIN, 16));
        nationality.setForeground(Color.white);
        main.add(nationality);

        nName = new JTextField();
        nName.setBounds(60, 155, 250, 25);
        main.add(nName);

        aadhar = new JLabel("Aadhar No*");
        aadhar.setBounds(60, 187, 100, 22);
        aadhar.setFont(new Font("segoe UI", Font.PLAIN, 16));
        aadhar.setForeground(Color.white);
        main.add(aadhar);

        aNumber = new JTextField();
        aNumber.setBounds(60, 210, 250, 25);
        main.add(aNumber);

        address = new JLabel("Address*");
        address.setBounds(60, 242, 100, 22);
        address.setFont(new Font("segoe UI", Font.PLAIN, 16));
        address.setForeground(Color.white);
        main.add(address);

        Add = new JTextField();
        Add.setBounds(60, 265, 250, 25);
        main.add(Add);

        phone = new JLabel("Phone No*");
        phone.setBounds(60, 297, 100, 22);
        phone.setFont(new Font("segoe UI", Font.PLAIN, 16));
        phone.setForeground(Color.white);
        main.add(phone);

        pNumber = new JTextField();
        pNumber.setBounds(60, 320, 250, 25);
        main.add(pNumber);


        gender = new JLabel("Gender*");
        gender.setBounds(60, 352, 100, 22);
        gender.setFont(new Font("segoe UI", Font.PLAIN, 16));
        gender.setForeground(Color.white);
        main.add(gender);

        ButtonGroup genderGroup = new ButtonGroup();

        male = new JRadioButton("Male");
        male.setFocusable(false);
        male.setBounds(63, 377, 70, 25);
        main.add(male);

        female = new JRadioButton("Female");
        female.setFocusable(false);
        female.setBounds(130, 377, 70, 25);
        main.add(female);

        other = new JRadioButton("Other");
        other.setFocusable(false);
        other.setBounds(200, 377, 70, 25);
        main.add(other);

        genderGroup.add(male);
        genderGroup.add(female);
        genderGroup.add(other);

        save = new JButton("Add Customer");
        save.setFocusable(false);
        save.setBackground(new Color(0, 123, 255));
        save.setForeground(Color.white);
        save.setBounds(60, 410, 250, 30);
        save.addActionListener(this);
        main.add(save);

        search = new JButton("Search");
        search.setFocusable(false);
        search.setBackground(Color.WHITE);
        search.setForeground(new Color(0, 123, 255));
        search.setBounds(60, 450, 250, 30);
        search.addActionListener(this);
        main.add(search);

        Exit1 = new JButton("Exit");
        Exit1.setFocusable(false);
        Exit1.setBackground(new Color(255, 59, 48));
        Exit1.setForeground(Color.white);
        Exit1.setBounds(60, 490, 250, 30);
        Exit1.addActionListener(this);
        main.add(Exit1);



        setVisible(true);
        setLayout(null);
        setBounds(300, 150, 900, 600);
        setResizable(false);
        add(header);
        add(main);


    }

    public void actionPerformed(ActionEvent e) {

        String name = cName.getText();
        String nationality = nName.getText();
        String phone = pNumber.getText();
        String address = Add.getText();
        String aadhar = aNumber.getText();
        String gender = null;
        boolean n = true;
        if (male.isSelected()) {
            gender = "Male";
            n = false;
        } else if (female.isSelected()) {
            gender = "Female";
            n = false;
        } else if (other.isSelected()) {
            gender = "Other";
            n = false;
        }
        if (e.getSource() == Exit1) {
            this.dispose();
        } else if (e.getSource() == save) {

            if (cName.getText().isEmpty() || aNumber.getText().length()!=12 || nName.getText().isEmpty() || pNumber.getText().isEmpty() || Add.getText().isEmpty() || aNumber.getText().isEmpty() || n) {
                JOptionPane.showMessageDialog(null, "Enter All Fields OR Enter Valid Detail", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                boolean b1 =true;
                int i;
                for(i=0;i<12;i++){
                    if(aadhar.charAt(i)<'0' || aadhar.charAt(i)>'9'){
                        b1=false;
                    }
                }
                if(b1) {
                    try {
                        Conn con = new Conn();
                        String s1 = "select *from passenger where aadhar='" + aadhar + "'";
                        ResultSet rs = con.st.executeQuery(s1);
                        if (rs == null) {
                            String q = "insert into passenger values('" + name + "','" + nationality + "','" + phone + "','" + address + "','" + aadhar + "','" + gender + "')";
                            int r = con.st.executeUpdate(q);
                            if (r > 0) {
                                cName.setText("");
                                nName.setText("");
                                pNumber.setText("");
                                Add.setText("");
                                aNumber.setText("");
                                JOptionPane.showMessageDialog(null, "Added Customer", "Success", JOptionPane.INFORMATION_MESSAGE);
                            }
                        }else{
                            cName.setText("");
                            nName.setText("");
                            pNumber.setText("");
                            Add.setText("");
                            aNumber.setText("");
                            JOptionPane.showMessageDialog(null, "This Customer Is Already Added", "Success", JOptionPane.INFORMATION_MESSAGE);
                        }

                    }catch(Exception ex){
                        ex.getStackTrace();
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Aadhar Contains only (0-9)", "Success", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        } else if (e.getSource()==search) {

            try {
                new searchCustomer();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            }
        }
    }