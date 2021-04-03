package Model;

import java.util.*;
import java.sql.*;

public class Marksheet {

    public ResultSet getMarks(String uname) throws Exception {
        try {
            String user = "mahesh";
            String password = "M@hesh2546";

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/exam_db?autoReconnect=true&useSSL=false", user, password);

            Statement st = con.createStatement();

            String sql = "select * from marksheet where username=\"" + uname + "\"";
            ResultSet rs = st.executeQuery(sql);

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