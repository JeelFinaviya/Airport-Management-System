package Project;

import com.google.zxing.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.sql.ResultSet;

public class Payment extends JFrame implements ActionListener {
    JLabel qrLabel;
    JButton payBtn, cancelBtn;
    String pnr;
    int tickets;

    public Payment(String pnr, int tickets) {
        this.pnr = pnr;
        this.tickets = tickets;

        setTitle("Airline Payment - " + pnr);
        setSize(420, 500);
        setLayout(null);
        getContentPane().setBackground(new Color(245, 245, 250));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel header = new JPanel();
        header.setBounds(0, 0, 420, 60);
        header.setBackground(new Color(44, 62, 80));
        header.setLayout(null);


        JLabel title = new JLabel("Secure Payment");
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(Color.WHITE);
        title.setBounds(100, 10, 300, 40);
        header.add(title);
        add(header);

        JPanel centerPanel = new JPanel();
        centerPanel.setBounds(20, 70, 380, 320);
        centerPanel.setBackground(Color.WHITE);
        centerPanel.setLayout(null);

        JLabel scanText = new JLabel("Scan this QR Code to Pay", SwingConstants.CENTER);
        scanText.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        scanText.setBounds(0, 10, 380, 30);
        centerPanel.add(scanText);

        qrLabel = new JLabel("Generating QR...", SwingConstants.CENTER);
        qrLabel.setBounds(65, 50, 250, 250);
        centerPanel.add(qrLabel);

        add(centerPanel);

        payBtn = new JButton("Confirm Payment");
        payBtn.setBackground(new Color(39, 174, 96));
        payBtn.setForeground(Color.WHITE);
        payBtn.setFocusable(false);
        payBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        payBtn.setBounds(50, 400, 160, 40);
        add(payBtn);

        cancelBtn = new JButton("Cancel");
        cancelBtn.setBackground(new Color(192, 57, 43));
        cancelBtn.setForeground(Color.WHITE);
        cancelBtn.setFocusable(false);
        cancelBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        cancelBtn.setBounds(220, 400, 120, 40);
        add(cancelBtn);

        try {
            String upi = "upi://pay?pa=jeelpatel8200262404@okicici&pn=AirlineBooking&am=" + (tickets * 2) + "&cu=INR&tn=PNR-" + pnr;
            String filePath = "paymentQR.png";
            generateQR(upi, 250, 250, filePath);
            qrLabel.setIcon(new ImageIcon(filePath));
            qrLabel.setText("");
        } catch (Exception e) {
            qrLabel.setText("QR Generation Failed ❌");
        }

        payBtn.addActionListener(this);
        cancelBtn.addActionListener(this);

        setVisible(true);
    }

    public static void generateQR(String text, int width, int height, String filePath) throws Exception {
        QRCodeWriter qrWriter = new QRCodeWriter();
        BitMatrix matrix = qrWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(matrix, "PNG", path);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == payBtn) {
            try {
                Conn con = new Conn();
                String s = "select payment_status from reservation where pnr='" + pnr + "'";
                ResultSet rs = con.st.executeQuery(s);

                if (rs.next()) {
                    String status = rs.getString("payment_status");

                    if (status.equalsIgnoreCase("PAID")) {
                        JOptionPane.showMessageDialog(this, "Already Paid! Boarding Pass Generated.");
                        new BoardingPass(pnr, tickets);
                    } else {
                        int confirm = JOptionPane.showConfirmDialog(this, "Have you completed the payment using the QR?", "Confirm Payment", JOptionPane.YES_NO_OPTION);

                        if (confirm == JOptionPane.YES_OPTION) {
                            con.st.executeUpdate("update reservation set payment_status='PAID' where pnr='" + pnr + "'");
                            JOptionPane.showMessageDialog(this, "✅ Payment Successful! Boarding Pass Generated.");
                            dispose();
                            new BoardingPass(pnr, tickets);
                        }
                    }
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else if (e.getSource() == cancelBtn) {
            try {
                Conn con = new Conn();
                con.st.executeUpdate("delete from reservation where pnr='" + pnr + "'");
                JOptionPane.showMessageDialog(this, "❌ Payment Cancelled. Booking Removed.");
                dispose();
            } catch (Exception ex) {
                ex.printStackTrace();
            }

        }
    }
}
