package Model;

import java.util.*;
import java.sql.*;

public class Marksheet {

    public void SetMarks(String uname, String total) throws Exception {
        try {
            String user = "mahesh";
            String password = "M@hesh2546";

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/exam_db?autoReconnect=true&useSSL=false", user, password);

            Statement st = con.createStatement();
            String sql = "UPDATE marksheet SET sub5 = \'Internet Programming\', mark5 =\'" + total
                    + "\' WHERE username = '" + uname + "'";
            st.executeUpdate(sql);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet getMarks(String uname) throws Exception {
        try {
            String user = "mahesh";
            String password = "M@hesh2546";

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/exam_db?autoReconnect=true&useSSL=false", user, password);

            Statement st = con.createStatement();

            String sql2 = "select * from marksheet where username=\"" + uname + "\"";
            ResultSet rs = st.executeQuery(sql2);

            if (rs.next()) {
                return rs;
            } else {
                return null;
            }

        } catch (Exception e) {
            System.out.println(e);
            return null;
        }
    }

}