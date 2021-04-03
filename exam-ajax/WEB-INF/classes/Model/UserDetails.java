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

	public String[] getUsers() throws Exception {
		try {
			ArrayList<String> usernames = new ArrayList<String>();
			String user = "mahesh";
			String password = "M@hesh2546";

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/exam_db?autoReconnect=true&useSSL=false", user, password);

			Statement st = con.createStatement();

			String sql = "select username from users";
			ResultSet rs = st.executeQuery(sql);

			if (rs.next()) {
				do {
					String res = rs.getString("username");
					usernames.add(res);
				} while (rs.next());
			} else {
				return null;
			}
			String[] usersArray = new String[usernames.size()];
			usersArray = usernames.toArray(usersArray);
			return usersArray;

		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public void insertUser(String username, String password) throws Exception {
		try {
			String sql_user = "mahesh";
			String sql_password = "M@hesh2546";

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/exam_db?autoReconnect=true&useSSL=false", sql_user, sql_password);

			Statement st = con.createStatement();

			String sql = "insert into users(username, password) values(\"" + username + "\", \"" + password + "\")";
			st.executeUpdate(sql);
			Statement st2 = con.createStatement();
			String sql2 = "insert into marksheet(username,sub1, mark1,sub2, mark2, sub3, mark3, sub4, mark4, sub5, mark5) values(\""
					+ username + "\",\"ML\", \"50\", \"OOAD\", \"50\", \"IPR\", \"40\", \"CD\", \"45\", \"IP\", \"0\")";
			st2.executeUpdate(sql2);

		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println(e);
		}

	}
}