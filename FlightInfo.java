package Project;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import net.proteanit.sql.DbUtils;


public class FlightInfo extends JFrame implements ActionListener {
    JTable table=new JTable();
    JButton Exit2;
    JPanel n,n1;
    JLabel title,bottom;


    public static void main(String[] args) {
        new FlightInfo();
    }


    public FlightInfo() {
        try{

            n  = new JPanel();
            n.setBackground(new Color(30,30,30));
            n.setLayout(null);
            n.setBounds(0,0,800,40);

            title = new JLabel("Flight Details",JLabel.CENTER);
            title.setForeground(Color.white);
            title.setLayout(null);
            title.setBounds(170,5,400,32);
            title.setFont(new Font("Segoue UI",Font.PLAIN,22));
            n.add(title);

            bottom = new JLabel("Flight Details That Keep You Ahead.");
            bottom.setForeground(Color.white);
            bottom.setLayout(null);
            bottom.setBounds(180,50,400,32);
            bottom.setFont(new Font("Segoue UI",Font.BOLD,22));

            n1  = new JPanel();
            n1.setBackground(new Color(30,30,30));
            n1.setLayout(null);
            n1.setBounds(0,220,800,400);
            n1.add(bottom);

            Conn con = new Conn();
            ResultSet rs = con.st.executeQuery("select * from flight");
            table.setModel( DbUtils.resultSetToTableModel(rs));
            table.setBackground(new Color(50,50,50));
            table.setForeground(Color.white);
            table.getTableHeader().setBackground(new Color(50,50,50));
            table.getTableHeader().setForeground(Color.white);
            table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 12));



        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane jsp = new JScrollPane(table);
        jsp.setBackground(new Color(30,30,30));
        jsp.setBounds(0,40,800,180);
        this.add(jsp);

        Exit2 = new JButton("Exit");
        Exit2.setVisible(true);
        Exit2.setLayout(null);
        Exit2.setFocusable(false);
        Exit2.setBackground(Color.red);
        Exit2.setForeground(Color.white);
        Exit2.setBounds(270,90,200,25);
        Exit2.addActionListener(this);
        n1.add(Exit2);


        getContentPane().setBackground(Color.WHITE);
        this.setLayout(null);
        this.setBounds(400, 200, 800, 500);
        this.setVisible(true);
        this.add(n);
        this.add(n1);
        this.setTitle("Flight-Information");

    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==Exit2){
            this.dispose();
        }
    }
}
