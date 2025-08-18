package Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import net.proteanit.sql.DbUtils;


public class JourneyDetail extends JFrame implements ActionListener {


    JTable table=new JTable();
    JLabel pnr,heading;
    JTextField pnrText;
    JButton show,Exit4;
    JPanel real,real1;


    public static void main(String[] args) throws Exception {
        new JourneyDetail();
    }


    public JourneyDetail() {

        real = new JPanel();
        real.setLayout(null);
        real.setBackground(new Color(30,30,30));
        real.setBounds(0,0,1000,100);

        real1 = new JPanel();
        real1.setLayout(null);
        real1.setBackground(new Color(30,30,30));
        real1.setBounds(0,200,1000,200);


        JScrollPane jsp = new JScrollPane(table);
        jsp.setBounds(0,100,792,100 );
        jsp.setBackground(Color.white);


        Exit4 = new JButton("Log Out");
        Exit4.setBounds(696,0,100,25);
        Exit4.setBackground(Color.red);
        Exit4.setForeground(Color.white);
        Exit4.setFocusable(false);
        Exit4.addActionListener(this);


        pnr = new JLabel("PNR Detail");
        pnr.setFont(new Font("Tahoma",Font.PLAIN,16));
        pnr.setForeground(Color.white);
        pnr.setBounds(50,50,100,25);

        pnrText = new JTextField();
        pnrText.setBounds(160,50,120,25);

        show = new JButton("Show Detail");
        show.setBounds(290,50,120,25);
        show.setFocusable(false);
        show.addActionListener(this);

        heading = new JLabel("≡ Journey Details:");
        heading.setBounds(10, 5, 700, 35);
        heading.setFont(new Font("Tahoma", Font.PLAIN, 24));
        heading.setForeground(Color.white);
        real.add(heading);


        getContentPane().setBackground(Color.WHITE);
        this.setLayout(null);
        this.setBounds(400, 250, 800, 400);
        this.setVisible(true);
        real1.add(pnr);
        real1.add(pnrText);
        real1.add(show);
        this.add(Exit4);
        this.add(jsp);
        this.add(real);
        this.add(real1);
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==Exit4){
            this.dispose();
        } else if (e.getSource()==show) {
            try {
                Conn con = new Conn();
                ResultSet rs = con.st.executeQuery("select * from reservation where PNR ='" + pnrText.getText() + "'");
                if (!rs.isBeforeFirst()) {
                    JOptionPane.showMessageDialog(null, "No information found", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                table.setModel(DbUtils.resultSetToTableModel(rs));
            }catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
}