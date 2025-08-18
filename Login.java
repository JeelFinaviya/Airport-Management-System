package Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

class Login extends JFrame implements ActionListener {
    JLabel userName,passWord,welcome,admin;
    JTextField forUserName;
    JPasswordField forPassWord;
    JButton reset;
    JButton submit;
    JButton Exit;
    JPanel black,login,lightGray;

    public Login(){

        welcome = new JLabel("Welcome");
        welcome.setLayout(null);
        welcome.setForeground(Color.white);
        welcome.setBounds(45,5,150,20);

        admin = new JLabel("Admin Log In.");
        admin.setLayout(null);
        admin.setForeground(Color.white);
        admin.setFont(new Font("Tahoma",Font.PLAIN,16));
        admin.setBounds(20,35,150,20);


        lightGray = new JPanel();
        lightGray.setBackground(new Color(0,0,0));
        lightGray.setLayout(null);
        lightGray.setBounds(0,0,150,30);
        lightGray.add(welcome);

        black = new JPanel();
        black.setBackground(new Color(30,30,30));
        black.setLayout(null);
        black.setBounds(0,0,150,300);
        black.add(lightGray);

        login=new JPanel();
        login.setBackground(new Color(0,0,0));
        login.setLayout(null);
        login.setBounds(150,0,350,300);

        login.add(admin);

        userName = new JLabel("Username ");
        userName.setLayout(null);
        userName.setBounds(20,65,100,20);
        userName.setForeground(Color.white);

        login.add(userName);

        passWord = new JLabel("Password ");
        passWord.setLayout(null);
        passWord.setBounds(20,120,300,20);
        passWord.setForeground(Color.white);

        login.add(passWord);

        forUserName = new JTextField();
        forUserName.setVisible(true);
        forUserName.setLayout(null);
        forUserName.setBackground(new Color(238,238,238));
        forUserName.setBounds(20,90,250,20);

        login.add(forUserName);

        forPassWord = new JPasswordField();
        forPassWord.setVisible(true);
        forPassWord.setLayout(null);
        forPassWord.setBackground(new Color(238,238,238));
        forPassWord.setBounds(20,140,250,20);

        login.add(forPassWord);

        reset = new JButton("Reset");
        reset.setVisible(true);
        reset.setLayout(null);
        reset.setFocusable(false);
        reset.setBounds(0,100,150,20);
        reset.setForeground(Color.white);
        reset.setBackground(new Color(33,150,243));
        reset.addActionListener(this);

        black.add(reset);

        submit = new JButton("Log in");
        submit.setVisible(true);
        submit.setLayout(null);
        submit.setFocusable(false);
        submit.setBounds(20,175,250,20);
        submit.setForeground(Color.white);
        submit.setBackground(new Color(255,183,77));
        submit.addActionListener(this);

        login.add(submit);

        Exit = new JButton("Exit");
        Exit.setVisible(true);
        Exit.setLayout(null);
        Exit.setFocusable(false);
        Exit.setBounds(0,120,150,20);
        Exit.setForeground(Color.WHITE);
        Exit.setBackground(new Color(244,67,54));
        Exit.addActionListener(this);

        black.add(Exit);

        this.setVisible(true);
        this.setName("Verification");
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBounds(500,250,500,300);
        this.add(black);
        this.add(login);

}
    public static void main(String[] args) {
        new Login();
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==submit){

            if(!forUserName.getText().isEmpty() || !forPassWord.getText().isEmpty()) {
                String username = forUserName.getText();
                String password = forPassWord.getText();
                try {
                    Conn conn = new Conn();
                    if (conn != null) {
                        String q = "select * from login where userName='" + username + "' and passWord = '" + password + "'";
                        ResultSet rs = conn.st.executeQuery(q);
                        if (rs.next()) {
                            JOptionPane.showMessageDialog(null, "Valid", "Success", JOptionPane.PLAIN_MESSAGE);
                            this.dispose();
                            new House();
                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid Username or Password");
                            forUserName.setText("");
                            forPassWord.setText("");
                        }
                    } else {
                        System.out.println("Connection Fail");
                    }


                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }else{
                JOptionPane.showMessageDialog(null, "Enter All Fields.");
            }
        } else if (e.getSource()==reset) {
            forPassWord.setText("");
            forUserName.setText("");
        } else if (e.getSource()==Exit) {
            this.dispose();
        }
    }
}
