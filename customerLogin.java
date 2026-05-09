package Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

class CustomerLogin extends JFrame implements ActionListener {

    JLabel userName, passWord, welcome, customer, brand;
    JTextField forUserName;
    JPasswordField forPassWord;
    JButton reset, submit, Exit;
    JPanel sidebar, login, header;

    public CustomerLogin() {

        header = new JPanel();
        header.setBackground(new Color(0, 102, 204)); // blue
        header.setBounds(0, 0, 500, 50);
        header.setLayout(null);

        brand = new JLabel("Airline Reservation System – Customer Login");
        brand.setFont(new Font("Segoe UI", Font.BOLD, 16));
        brand.setForeground(Color.white);
        brand.setBounds(60, 10, 400, 30);
        header.add(brand);

        sidebar = new JPanel();
        sidebar.setBackground(new Color(30, 30, 30));
        sidebar.setLayout(null);
        sidebar.setBounds(0, 50, 150, 250);

        welcome = new JLabel("Welcome!");
        welcome.setFont(new Font("Segoe UI", Font.BOLD, 16));
        welcome.setBounds(25, 10, 100, 25);
        welcome.setForeground(Color.white);
        sidebar.add(welcome);

        customer = new JLabel("Customer Login");
        customer.setForeground(Color.white);
        customer.setFont(new Font("Tahoma", Font.PLAIN, 14));
        customer.setBounds(20, 40, 150, 25);
        sidebar.add(customer);

        reset = new JButton("Reset");
        reset.setFocusable(false);
        reset.setBounds(0, 100, 150, 30);
        reset.setForeground(Color.white);
        reset.setBackground(new Color(33, 150, 243));
        reset.addActionListener(this);
        sidebar.add(reset);

        Exit = new JButton("Exit");
        Exit.setFocusable(false);
        Exit.setBounds(0, 150, 150, 30);
        Exit.setForeground(Color.WHITE);
        Exit.setBackground(new Color(244, 67, 54));
        Exit.addActionListener(this);
        sidebar.add(Exit);

        login = new JPanel();
        login.setBackground(Color.BLACK);
        login.setLayout(null);
        login.setBounds(150, 50, 350, 250);

        userName = new JLabel("Aadhar Number");
        userName.setBounds(20, 40, 100, 20);
        userName.setForeground(Color.white);
        login.add(userName);

        forUserName = new JTextField();
        forUserName.setBackground(new Color(245, 245, 245));
        forUserName.setBounds(20, 65, 250, 25);
        login.add(forUserName);

        passWord = new JLabel("Password");
        passWord.setBounds(20, 100, 100, 20);
        passWord.setForeground(Color.white);
        login.add(passWord);

        forPassWord = new JPasswordField();
        forPassWord.setBackground(new Color(245, 245, 245));
        forPassWord.setBounds(20, 125, 250, 25);
        login.add(forPassWord);

        submit = new JButton("Log in");
        submit.setFocusable(false);
        submit.setBounds(20, 170, 250, 30);
        submit.setForeground(Color.white);
        submit.setBackground(new Color(255, 152, 0));
        submit.addActionListener(this);
        login.add(submit);

        this.setTitle("Customer Login");
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(500, 250, 500, 300);
        this.add(header);
        this.add(sidebar);
        this.add(login);
        this.setResizable(false);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new CustomerLogin();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submit) {
            if (!forUserName.getText().isEmpty() || !forPassWord.getText().isEmpty()) {
                String aadhar = forUserName.getText();
                String password = forPassWord.getText();
                try {
                    Conn conn = new Conn();
                    if (conn != null) {
                        String q = "select * from passenger where aadhar ='" + aadhar + "' and pass='" + password + "'";
                        ResultSet rs = conn.st.executeQuery(q);
                        if (rs.next()) {
                            JOptionPane.showMessageDialog(null, "Login Successful! Welcome " + rs.getString("name"));
                            this.dispose();
                            new House();
                        } else {
                            JOptionPane.showMessageDialog(null, "You Have To First Log In!");
                            this.dispose();
                            new AddCustomer();
                        }
                    } else {
                        System.out.println("Connection Fail");
                    }
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "Enter All Fields.");
            }
        } else if (e.getSource() == reset) {
            forPassWord.setText("");
            forUserName.setText("");
        } else if (e.getSource() == Exit) {
            this.dispose();
        }
    }
}
