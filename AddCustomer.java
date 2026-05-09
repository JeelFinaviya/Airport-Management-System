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
    public static PassengerLinkedList list = new PassengerLinkedList();
    JLabel heading,pass, name, nationality, aadhar, address, gender, phone;
    JTextField cName,nName, aNumber, Add, pNumber;
    JRadioButton male, female, other;
    JButton save, Exit1, search;
    ImageIcon addCustomer;
    JPanel header, main;
    JPasswordField passText;

    public static void main(String[] args) throws Exception {
        new AddCustomer();
    }

    public AddCustomer() throws Exception {

        header = new JPanel();
        header.setBackground(new Color(0, 0, 0));
        header.setLayout(null);
        header.setBounds(0, 0, 2000, 40);


        main = new JPanel();
        main.setBackground(new Color(30, 30, 30));
        main.setLayout(null);
        main.setBounds(0, 20, 2000, 1000);


        heading = new JLabel("≡Customer Login:");
        heading.setBounds(10, 5, 700, 35);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 24));
        heading.setForeground(Color.white);

        header.add(heading);


        name = new JLabel("Name*");
        name.setBounds(60, 35, 100, 22);
        name.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        name.setForeground(Color.white);
        main.add(name);


        cName = new JTextField();
        cName.setBounds(60, 60, 250, 25);
        main.add(cName);

        nationality = new JLabel("Nationality*");
        nationality.setBounds(60, 90, 100, 25);
        nationality.setFont(new Font("segoe UI", Font.PLAIN, 16));
        nationality.setForeground(Color.white);
        main.add(nationality);

        nName = new JTextField();
        nName.setBounds(60, 115, 250, 25);
        main.add(nName);

        aadhar = new JLabel("Aadhar No*");
        aadhar.setBounds(60, 147, 100, 22);
        aadhar.setFont(new Font("segoe UI", Font.PLAIN, 16));
        aadhar.setForeground(Color.white);
        main.add(aadhar);

        aNumber = new JTextField();
        aNumber.setBounds(60, 170, 250, 25);
        main.add(aNumber);

        address = new JLabel("Address*");
        address.setBounds(60, 202, 100, 22);
        address.setFont(new Font("segoe UI", Font.PLAIN, 16));
        address.setForeground(Color.white);
        main.add(address);

        Add = new JTextField();
        Add.setBounds(60, 225, 250, 25);
        main.add(Add);

        phone = new JLabel("Phone No*");
        phone.setBounds(60, 252, 100, 22);
        phone.setFont(new Font("segoe UI", Font.PLAIN, 16));
        phone.setForeground(Color.white);
        main.add(phone);

        pNumber = new JTextField();
        pNumber.setBounds(60, 280, 250, 25);
        main.add(pNumber);

        pass = new JLabel("Password*");
        pass.setBounds(60, 310, 100, 22);
        pass.setFont(new Font("segoe UI", Font.PLAIN, 16));
        pass.setForeground(Color.white);
        main.add(pass);

        passText = new JPasswordField();
        passText.setBounds(60, 338, 250, 25);
        main.add(passText);



        gender = new JLabel("Gender*");
        gender.setBounds(60, 362, 100, 22);
        gender.setFont(new Font("segoe UI", Font.PLAIN, 16));
        gender.setForeground(Color.white);
        main.add(gender);

        ButtonGroup genderGroup = new ButtonGroup();

        male = new JRadioButton("Male");
        male.setFocusable(false);
        male.setBounds(63, 387, 70, 25);
        main.add(male);

        female = new JRadioButton("Female");
        female.setFocusable(false);
        female.setBounds(130, 387, 70, 25);
        main.add(female);

        other = new JRadioButton("Other");
        other.setFocusable(false);
        other.setBounds(200, 387, 70, 25);
        main.add(other);

        genderGroup.add(male);
        genderGroup.add(female);
        genderGroup.add(other);

        save = new JButton("Log In");
        save.setFocusable(false);
        save.setBackground(new Color(0, 123, 255));
        save.setForeground(Color.white);
        save.setBounds(60, 420 ,250, 30);
        save.addActionListener(this);
        main.add(save);

        search = new JButton("Back");
        search.setFocusable(false);
        search.setBackground(Color.WHITE);
        search.setForeground(new Color(0, 123, 255));
        search.setBounds(60, 460 ,250, 30);
        search.addActionListener(this);
        main.add(search);

        Exit1 = new JButton("Exit");
        Exit1.setFocusable(false);
        Exit1.setBackground(new Color(255, 59, 48));
        Exit1.setForeground(Color.white);
        Exit1.setBounds(60, 500, 250, 30);
        Exit1.addActionListener(this);
        main.add(Exit1);


        setVisible(true);
        setLayout(null);
        setBounds(400, 150, 900, 600);
        setResizable(false);
        add(header);
        add(main);


    }

    public void actionPerformed(ActionEvent e) {

        String name = cName.getText().toUpperCase();
        String nationality = nName.getText().toUpperCase();
        String phone = pNumber.getText().trim();
        String address = Add.getText().toUpperCase();
        String aadhar = aNumber.getText().trim();
        String gender = null;
        String  pas = passText.getText().trim();

        boolean n = true;
        if (male.isSelected()) {
            gender = "MALE";
            n = false;
        } else if (female.isSelected()) {
            gender = "FEMALE";
            n = false;
        } else if (other.isSelected()) {
            gender = "OTHER";
            n = false;
        }
        if (e.getSource() == Exit1) {
            this.dispose();
        } else if (e.getSource() == save) {

            if (passText.getText().length()<8 ||passText.getText().isEmpty()||cName.getText().isEmpty() || aNumber.getText().length() != 12 || pNumber.getText().length() != 10 || nName.getText().isEmpty() || pNumber.getText().isEmpty() || Add.getText().isEmpty() || aNumber.getText().isEmpty() || n) {
                JOptionPane.showMessageDialog(null, "Enter All Fields OR Enter Valid Detail", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                boolean b1 = true;
                boolean b2 = true;
                boolean b3 = true;
                boolean b4 = true;
                int i;
                for (i = 0; i < 12; i++) {
                    if (aadhar.charAt(i) < '0' || aadhar.charAt(i) > '9') {
                        b1 = false;
                    }
                }
                for (i = 0; i < name.length(); i++) {
                    char ch = name.charAt(i);
                    if (!((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'))) {
                        b2 = false;
                        break;
                    }
                }

                for (i = 0; i < nationality.length(); i++) {
                    char ch = nationality.charAt(i);
                    if (!((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z'))) {
                        b3 = false;
                        break;
                    }
                }
                    for (i = 0; i < 10; i++) {
                        if (phone.charAt(i) < '0' || phone.charAt(i) > '9') {
                            b4 = false;
                        }
                    }
                    if (b1 && b2 && b3 && b4) {
                        try {
                            Conn con = new Conn() ;
                            String s = "select * from passenger where aadhar ='"+aadhar+"'";
                            ResultSet rs = con.st.executeQuery(s);
                            if(!rs.next()) {
                                String s1 = "select * from passenger where pass='"+passText.getText().trim()+"'";
                                ResultSet rs1 = con.st.executeQuery(s1);
                                if(!rs1.next()) {
                                    String q = "insert into passenger values('" + name + "','" + nationality + "','" + phone + "','" + address + "','" + aadhar + "','" + pas + "','" + gender + "')";
                                    int r = con.st.executeUpdate(q);
                                    if (r > 0) {
                                        JOptionPane.showMessageDialog(null, "Login Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                                        this.dispose();
                                        new House();
                                    }
                                }else{
                                    passText.setText("");
                                    JOptionPane.showMessageDialog(null, "Enter New Password This Was Already Existing!!", "Success", JOptionPane.INFORMATION_MESSAGE);
                                }
                            }else {
                               JOptionPane.showMessageDialog(null, "This Customer Is Already Added", "Success", JOptionPane.INFORMATION_MESSAGE);
                               this.dispose();
                               new CustomerLogin();
                            }

                        } catch (Exception ex) {
                            ex.getStackTrace();
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Enter All Valid Details", "Success", JOptionPane.INFORMATION_MESSAGE);
                    }
            }
        }else if (e.getSource() == search) {
                this.dispose();
                new RoleChooser();
            }
    }
}