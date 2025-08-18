package Project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

class Conn{
    Connection con;
    Statement st;

  public Conn()throws  Exception{
        String dburl = "jdbc:mysql://localhost:3306/airlinemanagmentsystem";
        String dbuser = "root";
        String dbpass = "";
        String driverName = "com.mysql.cj.jdbc.Driver";
        Class.forName(driverName);
        con= DriverManager.getConnection(dburl,dbuser,dbpass);
        st = con.createStatement();
    }

    public static void main(String[] args)throws Exception {
        new Conn();
    }
}
