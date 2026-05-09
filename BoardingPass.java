package Project;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;

public class BoardingPass extends JFrame {
    JLabel heading, tick, head, n, total, cName, s, labelDes, FName, nameFlight, nameFlight1, code, fCode, date, ddate, safe, time, timing, pnr;
    JPanel detail, black, blue;

    public BoardingPass(String pnr1, int ticketCount) throws Exception {

        blue = new JPanel();
        blue.setLayout(null);
        blue.setBackground(new Color(0, 102, 204));
        blue.setBounds(0, 280, 1000, 60);

        head = new JLabel("✈ Your World, Our Skyline!");
        head.setForeground(Color.white);
        head.setFont(new Font("Segoe UI", Font.BOLD, 22));
        head.setBounds(290, 15, 500, 30);
        blue.add(head);

        black = new JPanel();
        black.setLayout(null);
        black.setBackground(new Color(25, 28, 36));
        black.setBounds(0, 0, 1000, 450);

        safe = new JLabel("Travel Safe, Fly Smart!");
        safe.setForeground(new Color(200, 200, 200));
        safe.setFont(new Font("Segoe UI", Font.ITALIC, 15));
        safe.setBounds(360, 60, 300, 30);
        black.add(safe);

        heading = new JLabel("AIRLINE RESERVATION");
        heading.setBounds(270, 10, 1000, 40);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 30));
        heading.setForeground(new Color(0, 153, 255));
        black.add(heading);

        n = new JLabel("BOARDING PASS");
        n.setBounds(680, 20, 200, 25);
        n.setFont(new Font("Tahoma", Font.BOLD, 16));
        n.setForeground(new Color(180, 180, 180));
        black.add(n);

        cName = new JLabel();
        cName.setBounds(60, 100, 400, 50);

        pnr = new JLabel(pnr1);
        pnr.setForeground(new Color(255, 255, 255));
        pnr.setFont(new Font("Segoe UI", Font.BOLD, 34));
        pnr.setBounds(500, 100, 300, 50);
        black.add(pnr);

        s = new JLabel();
        s.setBounds(60, 160, 250, 30);

        labelDes = new JLabel();
        labelDes.setBounds(250, 160, 250, 30);

        FName = new JLabel("FLIGHT");
        FName.setBounds(60, 210, 100, 25);
        FName.setFont(new Font("Segoe UI", Font.BOLD, 14));
        FName.setForeground(new Color(180, 180, 180));

        fCode = new JLabel("CODE");
        fCode.setBounds(220, 210, 100, 25);
        fCode.setFont(new Font("Segoe UI", Font.BOLD, 14));
        fCode.setForeground(new Color(180, 180, 180));

        date = new JLabel("DATE");
        date.setBounds(360, 210, 100, 25);
        date.setFont(new Font("Segoe UI", Font.BOLD, 14));
        date.setForeground(new Color(180, 180, 180));

        time = new JLabel("BOARDING");
        time.setBounds(520, 210, 120, 25);
        time.setFont(new Font("Segoe UI", Font.BOLD, 14));
        time.setForeground(new Color(180, 180, 180));

        tick = new JLabel("TOTAL SEATS");
        tick.setBounds(680, 210, 120, 25);
        tick.setFont(new Font("Segoe UI", Font.BOLD, 14));
        tick.setForeground(new Color(180, 180, 180));

        nameFlight = new JLabel();
        nameFlight.setBounds(60, 240, 150, 25);

        code = new JLabel();
        code.setBounds(220, 240, 100, 25);

        ddate = new JLabel();
        ddate.setBounds(360, 240, 150, 25);

        timing = new JLabel();
        timing.setBounds(520, 240, 150, 25);
        LocalTime current = LocalTime.now();
        DateTimeFormatter d = DateTimeFormatter.ofPattern("HH:mm");
        timing.setText(current.format(d));
        timing.setFont(new Font("Segoe UI", Font.BOLD, 20));
        timing.setForeground(new Color(0, 255, 127));

        total = new JLabel(Integer.toString(ticketCount));
        total.setBounds(700, 240, 100, 25);
        total.setForeground(new Color(255, 255, 255));
        total.setFont(new Font("Segoe UI", Font.BOLD, 18));

        black.add(cName);
        black.add(s);
        black.add(labelDes);
        black.add(nameFlight);
        black.add(code);
        black.add(ddate);
        black.add(timing);
        black.add(total);
        black.add(FName);
        black.add(fCode);
        black.add(date);
        black.add(time);
        black.add(tick);
        black.add(blue);

        try {
            Conn con = new Conn();
            String query = "select * from reservation where pnr='" + pnr1 + "'";
            ResultSet rs = con.st.executeQuery(query);
            if (rs.next()) {
                cName.setText(rs.getString("name").toUpperCase());
                cName.setForeground(new Color(255, 255, 255));
                cName.setFont(new Font("Segoe UI", Font.BOLD, 28));

                s.setText(rs.getString("src").toUpperCase().trim() + "  →");
                s.setForeground(new Color(0, 204, 255));
                s.setFont(new Font("Segoe UI", Font.BOLD, 20));

                labelDes.setText(rs.getString("des").toUpperCase().trim());
                labelDes.setForeground(new Color(0, 204, 255));
                labelDes.setFont(new Font("Segoe UI", Font.BOLD, 20));

                nameFlight.setText(rs.getString("flightname"));
                nameFlight.setForeground(new Color(255, 215, 0));
                nameFlight.setFont(new Font("Segoe UI", Font.BOLD, 18));

                code.setText(rs.getString("flightcode"));
                code.setForeground(Color.white);
                code.setFont(new Font("Segoe UI", Font.PLAIN, 18));

                ddate.setText(rs.getString("ddate"));
                ddate.setForeground(Color.white);
                ddate.setFont(new Font("Segoe UI", Font.PLAIN, 18));
            } else {
                JOptionPane.showMessageDialog(null, "Please enter correct PNR Number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        this.setTitle("Boarding Pass");
        this.setLayout(null);
        this.setBounds(400, 200, 850, 450);
        this.setResizable(false);
        this.add(black);
        this.setVisible(true);

        SwingUtilities.invokeLater(() -> saveBoardingPass(this));
    }

    public void saveBoardingPass(JFrame frame) {
        try {
            File dir = new File("C:\\BoardingPass");
            if (!dir.exists()) dir.mkdirs();

            BufferedImage image = new BufferedImage(frame.getWidth(), frame.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = image.createGraphics();
            frame.printAll(g2d);
            g2d.dispose();

            File file = new File(dir, "boarding_pass_" + pnr.getText() + ".png");
            ImageIO.write(image, "png", file);

            JOptionPane.showMessageDialog(frame, "Boarding pass saved at: " + file.getAbsolutePath());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(frame, "Error saving boarding pass: " + ex.getMessage());
        }
    }
}
