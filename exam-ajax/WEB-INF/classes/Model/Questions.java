package Model;

import java.util.*;
import java.sql.*;

public class Questions {

	public ResultSet getQuestions(int n) throws Exception {
		try {
			String user = "mahesh";
			String password = "M@hesh2546";

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/exam_db?autoReconnect=true&useSSL=false", user, password);

			Statement st = con.createStatement();

			String sql = "select * from questions order by rand()";
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

	public ResultSet getAnswers(String id) throws Exception {
		try {
			String user = "mahesh";
			String password = "M@hesh2546";

			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/exam_db?autoReconnect=true&useSSL=false", user, password);

			Statement st = con.createStatement();

			String sql = "select ans from questions where id =" + id + ";";
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