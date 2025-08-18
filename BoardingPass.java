package Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.spec.ECField;
import java.sql.Blob;
import java.sql.ResultSet;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class BoardingPass extends JFrame{
    JLabel heading,head,n, cName, s, labelDes, FName, nameFlight, nameFlight1, code, fCode, date, ddate,safe,time,timing,pnr;
    JPanel detail,black,blue;

    public BoardingPass(String pnr1) throws Exception {

        blue = new JPanel();
        blue.setLayout(null);
        blue.setBackground(new Color(77,166,255));
        blue.setBounds(0,280,1000,60);


        head = new JLabel("Your World,Our Skyline!");
        head.setForeground(Color.white);
        head.setLayout(null);
        head.setFont(new Font("Segoe UI",Font.PLAIN,24));
        head.setBounds(290,20,400,25);
        blue.add(head);


        black = new JPanel();
        black.setLayout(null);
        black.setBackground(new Color(12,14,18));
        black.setBounds(0,0,1000,450);

        detail = new JPanel();
        detail.setLayout(null);
        detail.setBackground(new Color(250,249,246));
        detail.setBounds(60,300,450,50);

        safe = new JLabel("Travel safe,fly smart!");
        safe.setForeground(Color.white);
        safe.setLayout(null);
        safe.setFont(new Font("Segoe UI",Font.PLAIN,16));
        safe.setBounds(325,-10,200,150);
        black.add(safe);


        heading = new JLabel("SKYLINE-AIRLINES");
        heading.setBounds(275, 10, 1000, 40);
        heading.setFont(new Font("segoe UI", Font.PLAIN, 32));
        heading.setForeground(new Color(77,166,255));
        black.add(heading);

        n = new JLabel("BOARDING PASS");
        n.setBounds(700, 10, 150, 25);
        n.setFont(new Font("Tahoma", Font.PLAIN, 16));
        n.setForeground(new Color(192,192,192));
        black.add(n);


        cName = new JLabel();
        cName.setBounds(260, 100, 150, 50);

        pnr = new JLabel(pnr1);
        pnr.setForeground(Color.white);
        pnr.setLayout(null);
        pnr.setBackground(new Color(255,255,255));
        pnr.setFont(new Font("Segoe UI",Font.PLAIN,34));
        pnr.setBounds(430,100,250,50);
        black.add(pnr);


        s = new JLabel();
        s.setBounds(220, 150, 150, 25);


        labelDes = new JLabel();
        labelDes.setBounds(370, 150, 150, 25);


        FName = new JLabel("FLIGHT");
        FName.setBounds(230, 200, 100, 25);
        FName.setFont(new Font("Segoi UI", Font.PLAIN, 16));
        FName.setForeground(new Color(160,160,160));


        nameFlight = new JLabel();
        nameFlight.setBounds(520, 150, 100, 25);


        nameFlight1 = new JLabel();
        nameFlight1.setBounds(230, 225, 100, 25);


        fCode = new JLabel("CODE");
        fCode.setBounds(355, 200, 100, 25);
        fCode.setFont(new Font("Segoi UI", Font.PLAIN, 16));
        fCode.setForeground(new Color(160,160,160));


        code = new JLabel();
        code.setBounds(355, 225, 100, 25);


        date = new JLabel("DATE");
        date.setBounds(465, 200, 100, 25);
        date.setFont(new Font("Segoi UI", Font.PLAIN, 16));
        date.setForeground(new Color(160,160,160));

        ddate = new JLabel();
        ddate.setBounds(465, 225, 200, 25);

        time = new JLabel("BOARDING");
        time.setBounds(620, 200, 100, 25);
        time.setFont(new Font("Segoi UI", Font.PLAIN, 16));
        time.setForeground(new Color(160,160,160));

        timing = new JLabel();
        timing.setBounds(620, 225, 100, 25);
        LocalTime current = LocalTime.now();
        DateTimeFormatter d = DateTimeFormatter.ofPattern("HH:mm:ss");
        timing.setText(""+ current.format(d));
        timing.setFont(new Font("Segoe UI", Font.PLAIN,20));
        timing.setForeground(Color.white);

        black.add(cName);
        black.add(s);
        black.add(labelDes);
        black.add(nameFlight);
        black.add(nameFlight1);
        black.add(FName);
        black.add(fCode);
        black.add(code);
        black.add(date);
        black.add(ddate);
        black.add(time);
        black.add(timing);
        black.add(blue);


        try {
            Conn con = new Conn();
            String query = "select * from reservation where pnr='" + pnr1 + "'";
            ResultSet rs = con.st.executeQuery(query);
            if (rs.next()) {

                cName.setText(rs.getString("name").toUpperCase());
                cName.setForeground(new Color(255,255,255));
                cName.setFont(new Font("Segoi UI", Font.PLAIN, 34));


                s.setText(rs.getString("src").toUpperCase().trim()+"        →");
                s.setForeground(new Color(255,255,255));
                s.setFont(new Font("Segoi Ui", Font.PLAIN, 18));

                labelDes.setText(rs.getString("des").toUpperCase().trim());
                labelDes.setForeground(new Color(255,255,255));
                labelDes.setFont(new Font("Segoi UI", Font.PLAIN, 16));

                nameFlight.setText(rs.getString("flightname"));
                nameFlight.setForeground(new Color(30,144,255));
                nameFlight.setFont(new Font("Segoe UI", Font.PLAIN,20));

                nameFlight1.setText(rs.getString("flightname"));
                nameFlight1.setForeground(Color.white);
                nameFlight1.setFont(new Font("Segoe UI", Font.PLAIN,20));

                code.setText(rs.getString("flightcode"));
                code.setForeground(Color.white);
                code.setFont(new Font("Segoe UI", Font.PLAIN, 20));

                ddate.setText(rs.getString("ddate"));
                ddate.setForeground(Color.white);
                ddate.setFont(new Font("Segoi UI", Font.PLAIN, 20));


            } else {
                JOptionPane.showMessageDialog(null, "Please enter correct PNR Number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.getStackTrace();
        }




        this.setTitle("Boarding Pass");
        this.setVisible(true);
        this.setLayout(null);
        this.setBounds(350, 180, 850, 450);
        this.setResizable(false);
        this.add(black);
        this.add(detail);

    }

}
