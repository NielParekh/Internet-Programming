package Model;

import java.util.*;
import java.sql.*;

public class UserDetails {

	public String getPassword(String username) throws Exception {
		try {
			String user = "mahesh";
			String password = "M@hesh2546";

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/exam_db?autoReconnect=true&useSSL=false", user, password);

			Statement st = con.createStatement();

			String sql = "select * from users where username = \'" + username + "\'";
			ResultSet rs = st.executeQuery(sql);

			if (rs.next()) {
				String res = rs.getString("password");
				return res;
			} else {
				return "_INVALID_";
			}

		} catch (Exception e) {
			System.out.println(e);
			return "0";
		}
	}
}