package Project;

import com.toedter.calendar.IDateEditor;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Random;


public class CancelTicket extends JFrame implements ActionListener {
    JLabel ticketCountLabel,heading, name, cancellation, fcode, PNR, cName, cancel,flightCode, date, datechooser;
    JTextField PNRTEXT;
    JButton cancelTicket, Exit3, fetch;
    Random random;
    JPanel back;
    int bookedTickets = 0;

    public static void main(String[] args) throws Exception {
        new CancelTicket();

    }

    public CancelTicket() throws Exception {
        random = new Random();

        back= new JPanel();
        back.setLayout(null);
        back.setBackground(new Color(13,13,13));
        back.setBounds(0,0,800,450);



        heading = new JLabel("≡ Cancellation:");
        heading.setBounds(10, 10, 250, 35);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 32));
        heading.setForeground(Color.white);


        PNR = new JLabel("PNR Number*");
        PNR.setBounds(60, 70, 150, 25);
        PNR.setFont(new Font("Tahoma", Font.PLAIN, 16));

        PNR.setForeground(new Color(255,255,255));

        PNRTEXT = new JTextField();
        PNRTEXT.setForeground(new Color(30,30,30));
        PNRTEXT.setBounds(215, 70, 120, 25);
        PNRTEXT.setBackground(new Color(255,255,255));

        fetch = new JButton("Show Details");
        fetch.setVisible(true);
        fetch.setFocusable(false);
        fetch.setBackground(new Color(255,255,255));
        fetch.setForeground(new Color(0,0,0));
        fetch.setBounds(350, 70, 120, 25);
        fetch.addActionListener(this);


        name = new JLabel("Name                :");
        name.setBounds(60, 120, 150, 22);
        name.setFont(new Font("Tahoma", Font.PLAIN, 16));
        name.setForeground(new Color(255,255,255));

        cName = new JLabel();
        cName.setBounds(215, 120, 150, 25);


        cancellation = new JLabel("Cancellation No  :");
        cancellation.setBounds(60, 165, 150, 25);
        cancellation.setFont(new Font("Tahoma", Font.PLAIN, 16));
        cancellation.setForeground(new Color(255,255,255));

        cancel = new JLabel();
        cancel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        cancel.setBounds(215, 165, 150, 25);


        flightCode = new JLabel("Flight Code        :");
        flightCode.setFont(new Font("Tahoma", Font.PLAIN, 16));
        flightCode.setForeground(new Color(255,255,255));
        flightCode.setBounds(60, 213, 150, 25);

        fcode = new JLabel();
        fcode.setBounds(215, 213, 150, 25);

        date = new JLabel("Date                  :");
        date.setBounds(60, 260, 150, 22);
        date.setFont(new Font("Tahoma", Font.PLAIN, 16));
        date.setForeground(new Color(255,255,255));

        datechooser = new JLabel();
        datechooser.setBounds(215, 260, 150, 25);


        cancelTicket = new JButton("Cancel Booking");
        cancelTicket.setFocusable(false);
        cancelTicket.setBackground(new Color(25,118,210));
        cancelTicket.setForeground(new Color(255,255,255));
        cancelTicket.setBounds(220, 350, 140, 25);
        cancelTicket.addActionListener(this);
        back.add(cancelTicket);

        ticketCountLabel = new JLabel("Tickets Booked   :");
        ticketCountLabel.setBounds(60, 300, 300, 22);
        ticketCountLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        ticketCountLabel.setForeground(new Color(255, 255, 255));
        back.add(ticketCountLabel);



        Exit3 = new JButton("Exit");
        Exit3.setFocusable(false);
        Exit3.setForeground(new Color(255,255,255));
        Exit3.setBackground(new Color(211,47,47));
        Exit3.setBounds(75, 350, 140, 25);
        Exit3.addActionListener(this);


        this.setVisible(true);
        this.setLayout(null);
        this.setBounds(400, 200, 800, 450);
        this.setResizable(false);
        this.add(back);


        back.add(heading);
        back.add(name);
        back.add(cName);
        back.add(cancellation);
        back.add(cancel);
        back.add(fcode);
        back.add(flightCode);
        back.add(PNR);
        back.add(PNRTEXT);
        back.add(Exit3);
        back.add(fetch);
        back.add(date);
        back.add(datechooser);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == fetch) {
            if (!PNRTEXT.getText().isEmpty()) {
                String PNR = PNRTEXT.getText();

                try {
                    Conn con = new Conn();
                    String query = "select * from reservation where pnr='" + PNR + "'";
                    ResultSet rs = con.st.executeQuery(query);
                    if (rs.next()) {
                        cName.setText(rs.getString("name"));
                        cName.setForeground(Color.white);
                        cName.setFont(new Font("Tahoma", Font.PLAIN, 14));
                        cancel.setText(rs.getString("cancelno"));
                        cancel.setForeground(Color.white);
                        cancel.setFont(new Font("Tahoma",Font.PLAIN,14));
                        fcode.setText(rs.getString("flightcode"));
                        fcode.setForeground(Color.white);
                        fcode.setFont(new Font("Tahoma", Font.PLAIN, 14));
                        datechooser.setText(rs.getString("ddate"));
                        datechooser.setForeground(Color.white);
                        datechooser.setFont(new Font("Tahoma", Font.PLAIN, 14));
                        bookedTickets=rs.getInt("total_ticket");
                        ticketCountLabel.setText("Tickets Booked    : " + bookedTickets);


                    } else {
                        JOptionPane.showMessageDialog(null, "Please enter correct PNR Number.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    ex.getStackTrace();
                }
            } else {
                JOptionPane.showMessageDialog(null, "PNR Field Is Empty!!.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == Exit3) {
            this.dispose();
        } else if (e.getSource() == cancelTicket) {
            String PNR = PNRTEXT.getText();

            try {
                Conn con = new Conn();
                String query3 = "update flight_schedule set available_seat = available_seat + " + bookedTickets + " where f_code='" + fcode.getText() + "' and flight_date='" + datechooser.getText() + "'";

                String query2 = "delete from reservation where pnr='" + PNR + "'";

                int r1 = con.st.executeUpdate(query2);
                int r3 = con.st.executeUpdate(query3);
                
                if(r1>0){
                    PNRTEXT.setText("");
                    cName.setText("");
                    cancel.setText("");
                    fcode.setText("");
                    datechooser.setText("");
                    ticketCountLabel.setText("Tickets Booked : 0");
                    JOptionPane.showMessageDialog(null, "Ticket(s) Cancel Successfully.", "Success", JOptionPane.PLAIN_MESSAGE);
                }else {
                    JOptionPane.showMessageDialog(null, "Something Wrong!!.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
