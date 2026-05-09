package Project;

import com.mysql.cj.log.Log;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

class House extends JFrame implements ActionListener {

    JLabel heading,image;
    JMenuBar menuBar;
    JPanel header, main;

    JMenu details, ticket;
    JMenuItem fDetails, cDetails, jDetails, bDetail, ticketCancelDetails;
    JButton fd, cd, jd, bd, tcd,Exit,sc;

    public static void main(String[] args) {
        new House();
    }

    public House() {

        ImageIcon icon = new ImageIcon("C:\\Users\\JEEL\\IdeaProjects\\Group_project\\src\\Project\\Icons\\img.png");

        image = new JLabel();
        image.setBounds(330,50,1210,770);



        Image img = icon.getImage();
        Image scaledImg = img.getScaledInstance(image.getWidth(), image.getHeight(), Image.SCALE_SMOOTH);
        image.setIcon(new ImageIcon(scaledImg));


        header = new JPanel(new BorderLayout());
        header.setBackground(new Color(25, 118, 210));
        header.setLayout(null);
        header.setBounds(0, 0, 2000, 50);

        main = new JPanel();
        main.setLayout(null);
        main.setBackground(new Color(30, 30, 30));
        main.setBounds(0, 50, 330, 2000);


        menuBar = new JMenuBar();

        details = new JMenu("Details");
        menuBar.add(details);

        fDetails = new JMenuItem("Flights Details");
        cDetails = new JMenuItem("Add Customer Details");
        bDetail = new JMenuItem("Book Flight");
        jDetails = new JMenuItem("Journey Details");
        ticketCancelDetails = new JMenuItem("Cancel Booking");

        fDetails.addActionListener(this);
        cDetails.addActionListener(this);
        ticketCancelDetails.addActionListener(this);
        bDetail.addActionListener(this);
        jDetails.addActionListener(this);


        fd = new JButton("Flights Details");
        fd.setFocusable(false);
        fd.setBounds(30, 40, 250, 50);
        fd.setBackground(new Color(25, 118, 210));
        fd.setForeground(new Color(255, 255, 255));

        Exit = new JButton("Exit");
        Exit.setFocusable(false);
        Exit.setBounds(30, 580, 250, 50);
        Exit.setBackground(Color.red);
        Exit.setForeground(new Color(255, 255, 255));



        cd = new JButton("Add Customer Details");
        cd.setFocusable(false);
        cd.setBounds(30, 130, 250, 50);
        cd.setBackground(new Color(25, 118, 210));
        cd.setForeground(new Color(255, 255, 255));

        bd = new JButton("Book Flight");
        bd.setFocusable(false);
        bd.setBounds(30, 220, 250, 50);
        bd.setBackground(new Color(25, 118, 210));
        bd.setForeground(new Color(255, 255, 255));

        jd = new JButton("Journey Details");
        jd.setFocusable(false);
        jd.setBounds(30, 310, 250, 50);
        jd.setBackground(new Color(25, 118, 210));
        jd.setForeground(new Color(255, 255, 255));

        tcd = new JButton("Cancel Booking");
        tcd.setFocusable(false);
        tcd.setBounds(30, 490, 250, 50);
        tcd.setBackground(new Color(25, 118, 210));
        tcd.setForeground(new Color(255, 255, 255));

        sc = new JButton("Search Customer");
        sc.setFocusable(false);
        sc.setBounds(30, 400, 250, 50);
        sc.setBackground(new Color(25, 118, 210));
        sc.setForeground(new Color(255, 255, 255));

        fd.addActionListener(this);
        cd.addActionListener(this);
        tcd.addActionListener(this);
        bd.addActionListener(this);
        jd.addActionListener(this);
        Exit.addActionListener(this);
        sc.addActionListener(this);


        details.setMnemonic(KeyEvent.VK_D);
        details.add(fDetails);
        details.add(bDetail);
        details.add(cDetails);
        details.add(jDetails);
        details.add(ticketCancelDetails);


        main.add(fd);
        main.add(cd);
        main.add(jd);
        main.add(tcd);
        main.add(bd);
        main.add(Exit);
        main.add(sc);


        heading = new JLabel("AIR LINE RESERVATION ");
        heading.setForeground(Color.white);
        heading.setFont(new Font("Arial", Font.BOLD, 22));
        heading.setLayout(null);
        heading.setBounds(630, 0, 500, 50);
        header.add(heading);



        setVisible(true);
        setTitle("Airline Reservation System");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setResizable(false);
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setJMenuBar(menuBar);
        this.add(header);
        this.add(main);
        this.add(image);

    }

    public void actionPerformed(ActionEvent e) {

        try {
            if (e.getSource() == fd) {
                new FlightInfo();
            } else if (e.getSource() == cd) {
                new AddCustomer();
            } else if(e.getSource()==sc){
                new searchCustomer();
            }else if (e.getSource() == bd) {
                new BookFlight();
            } else if (e.getSource() == jd) {
                new JourneyDetail();
            } else if (e.getSource() == tcd) {
                new CancelTicket();
            } else if (e.getSource()==Exit) {
                this.dispose();
            } else {
                String text = e.getActionCommand();
                System.out.println(text);

                if (text.equals("Flights Details")) {
                    new FlightInfo();
                }
                if (text.equals("Add Customer Details")) {
                    new AddCustomer();
                }
                if (text.equals("Book Flight")) {
                    new BookFlight();
                }
                if (text.equals("Journey Details")) {
                    new JourneyDetail();
                }
                if (text.equals("Cancel Booking")) {
                    new CancelTicket();
                }
            }
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }
}
