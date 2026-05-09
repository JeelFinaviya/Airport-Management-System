package Project;

import com.toedter.calendar.IDateEditor;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Random;

public class BookFlight extends JFrame implements ActionListener {
    JLabel heading,depLabel,arrTime,depTime, name, nationality,arrLabel ,fname,fcode, aadhar, address, cName, nName, source, des, Add, gender, labelGender, flightName, flightCode, date;
    JTextField aNumber;
    JButton Flight, Exit3, fetch, BookFlight;
    Choice sou, destination,ticketChoice;
    static JDateChooser jDateChooser;
    JPanel header,mainPane,side;

    public static void main(String[] args) throws Exception {
        new BookFlight();
    }

    public BookFlight() throws Exception {





        side = new JPanel();
        side.setBackground(new Color(0,0,0));
        side.setLayout(null);
        side.setBounds(700,0,200,2000);


        mainPane = new JPanel();
        mainPane.setBackground(new Color(30,30,30));
        mainPane.setLayout(null);
        mainPane.setBounds(0,40,2000,2000);
        mainPane.add(side);

        JLabel tickets = new JLabel("No. of Tickets :");
        tickets.setBounds(60, 520, 150, 25);
        tickets.setFont(new Font("Tahoma", Font.PLAIN, 16));
        tickets.setForeground(Color.white);

        ticketChoice = new Choice();
        for (int i = 1; i <= 5; i++) {
            ticketChoice.add(String.valueOf(i));
        }
        ticketChoice.setBounds(215, 520, 240, 25);

        mainPane.add(tickets);
        mainPane.add(ticketChoice);

        header  = new JPanel();
        header.setBackground(new Color(238,238,238));
        header.setLayout(null);
        header.setBounds(0,0,2000,40);

        heading = new JLabel("≡ Book Flights:");
        heading.setBounds(10, 5, 500, 35);
        heading.setFont(new Font("Segoe UI", Font.PLAIN, 24));
        heading.setForeground(Color.BLACK);
        header.add(heading);

        aadhar = new JLabel("Aadhar No*");
        aadhar.setBounds(60, 30, 150, 25);
        aadhar.setFont(new Font("Tahoma", Font.PLAIN, 16));
        aadhar.setForeground(Color.white);

        aNumber = new JTextField();
        aNumber.setBackground(new Color(238,238,238));
        aNumber.setBounds(215, 30, 240, 25);

        fetch = new JButton("Fetch");
        fetch.setVisible(true);
        fetch.setFocusable(false);
        fetch.setForeground(Color.white);
        fetch.setBackground(new Color(30,144,255));
        fetch.setBounds(500, 30, 150, 25);
        fetch.addActionListener(this);


        name = new JLabel("Name          :");
        name.setBounds(60, 70, 150, 22);
        name.setFont(new Font("Tahoma", Font.PLAIN, 16));
        name.setForeground(Color.white);

        cName = new JLabel();
        cName.setBounds(215, 70, 150, 25);


        nationality = new JLabel("Nationality   :");
        nationality.setBounds(60, 110, 150, 25);
        nationality.setFont(new Font("Tahoma", Font.PLAIN, 16));
        nationality.setForeground(Color.white);

        nName = new JLabel();
        nName.setBounds(215, 110, 150, 25);


        address = new JLabel("Address       :");
        address.setBounds(60, 150, 150, 22);
        address.setFont(new Font("Tahoma", Font.PLAIN, 16));
        address.setForeground(Color.white);

        Add = new JLabel();
        Add.setBounds(215, 150, 150, 25);


        gender = new JLabel("Gender        :");
        gender.setBounds(60, 190, 100, 22);
        gender.setFont(new Font("Tahoma", Font.PLAIN, 16));
        gender.setForeground(Color.white);

        labelGender = new JLabel();
        labelGender.setBounds(215, 190, 150, 25);

        source = new JLabel("Source         :");
        source.setBounds(60, 230, 100, 22);
        source.setFont(new Font("Tahoma", Font.PLAIN, 16));
        source.setForeground(Color.white);

        des = new JLabel("Designation  :");
        des.setBounds(60, 270, 150, 22);
        des.setFont(new Font("Tahoma", Font.PLAIN, 16));
        des.setForeground(Color.white);

        sou = new Choice();
        sou.setBackground(new Color(238,238,238));
        sou.setBounds(215, 230, 240, 25);

        destination = new Choice();
        destination.setBackground(new Color(238,238,238));
        destination.setBounds(215, 270, 240, 25);


        try {
            Conn con = new Conn();
            String query = "select * from Flight";
            ResultSet rs = con.st.executeQuery(query);
            while (rs.next()) {
                sou.add(rs.getString("source"));
                destination.add(rs.getString("destination"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        Flight = new JButton("Fetch Flights");
        Flight.setFocusable(false);
        Flight.setForeground(Color.white);
        Flight.setBackground(new Color(50,205,50));
        Flight.setBounds(500,250, 150, 25);
        Flight.addActionListener(this);


        flightName = new JLabel("FlightName  :");
        flightName.setBounds(60, 315, 100, 22);
        flightName.setFont(new Font("Tahoma", Font.PLAIN, 16));
        flightName.setForeground(Color.white);

        fname = new JLabel();
        fname.setBounds(215, 315, 100, 22);


        flightCode = new JLabel("FlightCode    :");
        flightCode.setBounds(60, 360, 150, 22);
        flightCode.setFont(new Font("Tahoma", Font.PLAIN, 16));
        flightCode.setForeground(Color.white);

        fcode = new JLabel();
        fcode.setBounds(215,360,150,22);

        date = new JLabel("Date             :");
        date.setBounds(60, 480, 150, 22);
        date.setFont(new Font("Tahoma", Font.PLAIN, 16));
        date.setForeground(Color.white);

        jDateChooser = new JDateChooser();
        jDateChooser.setBackground(new Color(238,238,238));
        jDateChooser.setBounds(215, 480, 240, 25);

        depLabel = new JLabel("Departure    :");
        depLabel.setBounds(60, 400, 150, 22);
        depLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        depLabel.setForeground(Color.white);

        arrLabel = new JLabel("Arrival         :");
        arrLabel.setBounds(60, 440, 150, 22);
        arrLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        arrLabel.setForeground(Color.white);

        depTime = new JLabel();
        depTime.setBounds(215, 400, 150, 22);
        depTime.setFont(new Font("Tahoma", Font.PLAIN, 14));
        depTime.setForeground(Color.white);

        arrTime = new JLabel();
        arrTime.setBounds(215, 440, 150, 22);
        arrTime.setFont(new Font("Tahoma", Font.PLAIN, 14));
        arrTime.setForeground(Color.white);



        Exit3 = new JButton("Exit");
        Exit3.setFocusable(false);
        Exit3.setForeground(Color.white);
        Exit3.setBackground(new Color(220,20,60));
        Exit3.setBounds(50, 480, 100, 30);
        Exit3.addActionListener(this);

        BookFlight = new JButton("Book");
        BookFlight.setFocusable(false);
        BookFlight.setForeground(Color.white);
        BookFlight.setBackground(new Color(255,153,0));
        BookFlight.setBounds(110, 580, 265, 30);
        BookFlight.addActionListener(this);

        this.setVisible(true);
        this.setLayout(null);
        this.setBounds(400, 100, 900, 700);
        getContentPane().setBackground(Color.white);
        this.setResizable(false);

        this.add(mainPane);
        this.add(header);

        side.add(Exit3);

        mainPane.add(Flight);
        mainPane.add(fetch);

        mainPane.add(name);
        mainPane.add(cName);
        mainPane.add(nationality);
        mainPane.add(nName);
        mainPane.add(aadhar);
        mainPane.add(aNumber);
        mainPane.add(address);
        mainPane.add(Add);
        mainPane.add(source);
        mainPane.add(des);
        mainPane.add(labelGender);
        mainPane.add(gender);
        mainPane.add(BookFlight);
        mainPane.add(sou);
        mainPane.add(destination);
        mainPane.add(flightName);
        mainPane.add(fname);
        mainPane.add(fcode);
        mainPane.add(flightCode);
        mainPane.add(date);
        mainPane.add(jDateChooser);
        mainPane.add(depLabel);
        mainPane.add(arrLabel);
        mainPane.add(depTime);
        mainPane.add(arrTime);
    }

    public void actionPerformed(ActionEvent e) {
            if (e.getSource() == fetch) {
                if (!aNumber.getText().isEmpty()) {
                    String aadhar = aNumber.getText();

                    try {
                        Conn con = new Conn();
                        String query = "select * from passenger where aadhar='" + aadhar + "'";
                        ResultSet rs = con.st.executeQuery(query);
                        if (rs.next()) {
                            cName.setText(rs.getString("name"));
                            cName.setFont(new Font("Tahoma", Font.PLAIN, 14));
                            cName.setForeground(Color.white);
                            nName.setText(rs.getString("nationality"));
                            nName.setFont(new Font("Tahoma", Font.PLAIN, 14));
                            nName.setForeground(Color.white);
                            Add.setText(rs.getString("address"));
                            Add.setFont(new Font("Tahoma", Font.PLAIN, 14));
                            Add.setForeground(Color.white);
                            labelGender.setText(rs.getString("gender"));
                            labelGender.setFont(new Font("Tahoma", Font.PLAIN, 14));
                            labelGender.setForeground(Color.white);
                        } else {
                            JOptionPane.showMessageDialog(null, "Please enter correct aadhar no.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }catch(Exception ex){
                        ex.getStackTrace();
                    }
                }else{
                    JOptionPane.showMessageDialog(null, "Please enter All details.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }else if (e.getSource() == Flight) {
                String src = sou.getSelectedItem();
                String d = destination.getSelectedItem();

            try {
                Conn con1 = new Conn();
                String query1= "select * from flight where source = '" + src + "' and destination = '" + d + "'";
                ResultSet rs = con1.st.executeQuery(query1);
                if (rs.next()) {
                    fcode.setText(rs.getString("f_code"));
                    fcode.setFont(new Font("Tahoma", Font.PLAIN, 14));
                    fcode.setForeground(Color.white);

                    depTime.setText(rs.getString("departure_time"));
                    arrTime.setText(rs.getString("arrival_time"));

                    fname.setText(rs.getString("f_name"));
                    fname.setFont(new Font("Tahoma", Font.PLAIN, 14));
                    fname.setForeground(Color.white);


                }else{
                    JOptionPane.showMessageDialog(null,"No flights found!!","Error",JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                ex.getStackTrace();
            }

        }else if(e.getSource()==BookFlight){
                int ticketCount = Integer.parseInt(ticketChoice.getSelectedItem());

                Random random = new Random();
                String aadhar = aNumber.getText().trim();
                String name = cName.getText();
                String nationality = nName.getText();
                String flightName = fname.getText();
                String flightCode = fcode.getText();
                String src = sou.getSelectedItem();
                String des = destination.getSelectedItem();

                java.util.Date utilDate = jDateChooser.getDate();
                String date = new java.sql.Date(utilDate.getTime()).toString();


                if (!aadhar.isEmpty() && !name.isEmpty() && !nationality.isEmpty() && !flightName.isEmpty() && !flightCode.isEmpty() && !src.isEmpty() && !des.isEmpty() && !date.isEmpty()) {
                try {
                    Conn con = new Conn();
                    String s3 = "select available_seat from flight_schedule where f_code='" + flightCode + "' and flight_date='" + date + "'";
                    ResultSet rsCheck = con.st.executeQuery(s3);
                    if(rsCheck.next()){
                        int available =rsCheck.getInt("available_seat");
                        if(available<ticketCount){
                            JOptionPane.showMessageDialog(null, "Not enough seats available on this date!Only " + available + " left.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }else{
                        JOptionPane.showMessageDialog(null,  "No schedule available for this flight on selected date.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                            aNumber.setText("");
                            cName.setText("");
                            Add.setText("");
                            labelGender.setText("");
                            nName.setText("");
                            fname.setText("");
                            fcode.setText("");

                            try {
                                Conn con1 = new Conn();
                                String cancel = "" + random.nextInt(1000000);
                                String p = "" + random.nextInt(100000);
                                String s2 = "update flight_schedule set available_seat = available_seat - " + ticketCount + " where f_code='" + flightCode + "' and flight_date='" + date + "'";
                                int r=con1.st.executeUpdate(s2);
                                    String query1 = "insert into reservation values('PNR-" + p + "','UNPAID',"+ticketCount+ ",'" + cancel + "','TIC-" + random.nextInt(10000) + "','" + aadhar + "','" + name + "','" + nationality + "','" + flightName + "','" + flightCode + "','" + src + "','" + des + "','" + date + "')";
                                    int r2 = con1.st.executeUpdate(query1);
                                this.dispose();
                                new Payment("PNR-" + p,ticketCount);
                            } catch (Exception ex) {
                                ex.getStackTrace();
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Enter All Fields:", "Success", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }else if(e.getSource()==Exit3){
                this.dispose();
            }
        }
    }

