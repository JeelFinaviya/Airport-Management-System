package Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RoleChooser extends JFrame implements ActionListener {
    JRadioButton adminRadio, customerRadio;
    JButton proceedBtn, exitBtn;
    ButtonGroup group;
    JPanel leftPanel, rightPanel;

    public RoleChooser() {
        setTitle("Airline Reservation System - Role Selection");
        setBounds(500,250,500,250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);

        leftPanel = new JPanel();
        leftPanel.setLayout(null);
        leftPanel.setBackground(new Color(30, 30, 30));
        leftPanel.setBounds(0, 0, 150, 300);

        JLabel welcome = new JLabel("Welcome");
        welcome.setFont(new Font("Segoe UI", Font.BOLD, 16));
        welcome.setForeground(Color.WHITE);
        welcome.setBounds(35, 10, 100, 20);
        leftPanel.add(welcome);

        JLabel choose = new JLabel("Choose Role");
        choose.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        choose.setForeground(Color.WHITE);
        choose.setBounds(25, 40, 120, 20);
        leftPanel.add(choose);

        exitBtn = new JButton("Exit");
        exitBtn.setBounds(0, 120, 150, 30);
        exitBtn.setFocusable(false);
        exitBtn.setBackground(new Color(244, 67, 54));
        exitBtn.setForeground(Color.WHITE);
        exitBtn.addActionListener(this);
        leftPanel.add(exitBtn);

        rightPanel = new JPanel();
        rightPanel.setLayout(null);
        rightPanel.setBackground(new Color(0, 0, 0));
        rightPanel.setBounds(150, 0, 350, 300);

        JLabel title = new JLabel("Select Your Role.");
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setForeground(new Color(0, 200, 120));
        title.setBounds(100, 20, 200, 30);
        rightPanel.add(title);

        adminRadio = new JRadioButton("Admin");
        adminRadio.setBounds(100, 80, 150, 25);
        adminRadio.setFocusable(false);
        rightPanel.add(adminRadio);

        customerRadio = new JRadioButton("️Customer");
        customerRadio.setBounds(100, 120, 150, 25);
        customerRadio.setFocusable(false);
        rightPanel.add(customerRadio);

        group = new ButtonGroup();
        group.add(adminRadio);
        group.add(customerRadio);

        proceedBtn = new JButton("Proceed →");
        proceedBtn.setBounds(100, 160, 150, 30);
        proceedBtn.setFocusable(false);
        proceedBtn.setBackground(new Color(33, 150, 243));
        proceedBtn.setForeground(Color.WHITE);
        proceedBtn.addActionListener(this);

        rightPanel.add(proceedBtn);

        this.add(leftPanel);
        this.add(rightPanel);
        this.setVisible(true);
    }


    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == proceedBtn) {
            if (adminRadio.isSelected()) {
                new Login();
                dispose();
            } else if (customerRadio.isSelected()) {
                try {
                    new CustomerLogin();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "⚠ Please select a role before proceeding.");
            }
        } else if (e.getSource() == exitBtn) {
            this.dispose();
        }
    }

    public static void main(String[] args) {
        new RoleChooser();
    }
}
