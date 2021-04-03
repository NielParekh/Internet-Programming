package Controller;

import Model.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.sql.*;

public class QuestionServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse res) throws ServletException, IOException {
		Questions q = new Questions();
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		String uname = request.getParameter("uname");
		try {
			ResultSet rs = q.getQuestions(5);
			pw.println("<html>");
			pw.println("<head><title>Quiz Questions</title>");
			pw.println(
					"<link rel=\"preconnect\" href=\"https://fonts.gstatic.com\"><link href=\"https://fonts.googleapis.com/css2?family=Poppins:wght@600&display=swap\" rel=\"stylesheet\">");
			pw.println("<style>");
			pw.println(
					"body{font-family: \'Poppins\', sans-serif;display:flex; background: #8E2DE2;background: -webkit-linear-gradient(to bottom, #4A00E0, #8E2DE2);background: linear-gradient(to bottom, #4A00E0, #8E2DE2);}");
			pw.println("label{color: #EEE;font-size: 24px;}");
			pw.println("input{font-size: 24px;}");
			// pw.println("form{padding: 15px; width: 90%;border: 2px solid
			// #EEE;border-radius: 10px;}");
			pw.println("h1{color: #EEE;text-transform: uppercase;font-size:42px;");

			pw.println("</style>");

			pw.println("<body onkeydown=\"return (event.keyCode != 116)\"> ");
			pw.println("<div style=\"margin:auto;\">");
			pw.println("<h1 style=\"text-align: center;\"> Welcome " + uname + "!</h1>");
			pw.println("<form action=\"\" method=\"post\">");
			int qno = 1;

			do {
				// pw.println(rs.getString("ques"));
				pw.println("<br><label>" + rs.getString("ques") + "</label><br>");
				pw.println("<input type=\"radio\" id=\"" + rs.getString("id") + "op1\" name=\"" + rs.getString("id")
						+ "\" value=\"" + rs.getString("op1") + "\"required>");
				pw.println("<label for=\"" + rs.getString("id") + "op1\">" + rs.getString("op1") + "</label><br>");

				pw.println("<input type=\"radio\" id=\"" + rs.getString("id") + "op2\" name=\"" + rs.getString("id")
						+ "\" value=\"" + rs.getString("op2") + "\"required>");
				pw.println("<label for=\"" + rs.getString("id") + "op2\">" + rs.getString("op2") + "</label><br>");

				pw.println("<input type=\"radio\" id=\"" + rs.getString("id") + "op3\" name=\"" + rs.getString("id")
						+ "\" value=\"" + rs.getString("op3") + "\"required>");
				pw.println("<label for=\"" + rs.getString("id") + "op3\">" + rs.getString("op3") + "</label><br>");

				pw.println("<input type=\"radio\" id=\"" + rs.getString("id") + "op4\" name=\"" + rs.getString("id")
						+ "\" value=\"" + rs.getString("op4") + "\"required>");
				pw.println("<label for=\"" + rs.getString("id") + "op4\">" + rs.getString("op4") + "</label><br>");

				// pw.println(
				// "<input type=\"text\" name=\"ans" + qno + "\" value = \"" +
				// rs.getString("ans") + "\" hidden>");

				qno += 1;
			} while (rs.next());
			pw.println("<input type=\"text\" name=\"uname\" value = \"" + uname + "\" hidden>");
			pw.println(
					"<br><input type=\"submit\" value =\"Submit Test\" onclick=\"return confirm(\'Are you sure you want to submit?\')\">");
			pw.println("</form>");
			pw.println("</div></body></html>");
		} catch (

		Exception e) {
			System.out.println(e);
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		session.invalidate();
		Questions q = new Questions();

		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		try {
			int total = 0, score = 0;

			pw.println("<html>");
			pw.println("<head><title>Quiz Answered</title>");
			pw.println(
					"<link rel=\"preconnect\" href=\"https://fonts.gstatic.com\"><link href=\"https://fonts.googleapis.com/css2?family=Poppins:wght@600&display=swap\" rel=\"stylesheet\">");
			pw.println("<style>");
			pw.println(
					"body{font-family: \'Poppins\', sans-serif;display:flex; background: #8E2DE2;background: -webkit-linear-gradient(to bottom, #4A00E0, #8E2DE2);background: linear-gradient(to bottom, #4A00E0, #8E2DE2);}");
			pw.println("div {margin: auto;}");
			pw.println("table, th, td {padding: 10px;border: 3px solid #EEE;border-collapse: collapse;}");
			pw.println("input{font-size: 24px;}");
			pw.println("th{font-size: 32px;color: #EEE;}");
			pw.println("td{font-size: 24px;}");
			pw.println("</style>");
			pw.println("<body onkeydown=\"return (event.keyCode != 116)\"> ");
			pw.println("<div>");
			pw.println("<h1 style=\"text-align: center;font-size:40px;text-transform: uppercase;\">"
					+ request.getParameter("uname") + "\'s Quiz report</h1>");
			pw.println("<table>");
			pw.println("<tr>");
			pw.println("<th>QNO</th>");
			pw.println("<th>RESPONSE</th>");
			pw.println("<th>CORRECT ANS</th>");
			pw.println("<th>MARKS OBTAINED</th>");
			pw.println("</tr>");
			for (int i = 1; i <= 5; i++) {
				String entered = request.getParameter(String.valueOf(i));
				ResultSet correctSet = q.getAnswers(String.valueOf(i));
				String correct = correctSet.getString("ans");

				score = 0;
				if (entered.equals(correct))
					score = 1;
				total += score;
				String color = "#ff1731";
				if (score == 1) {
					color = "chartreuse";
				}
				pw.println("<tr>");
				pw.println("<td style=\"color:" + color + "\">" + i + "</td>");
				pw.println("<td style=\"color:" + color + "\">" + entered + "</td>");
				pw.println("<td style=\"color:" + color + "\">" + correct + "</td>");
				pw.println("<td style=\"color:" + color + "\">" + score + "</td>");
				pw.println("</tr>");
			}
			pw.println("<tr>");
			pw.println("<td></td>");
			pw.println("<td></td>");
			pw.println("<th>Total</th>");
			pw.println("<td>" + total + "/5 </td>");
			pw.println("</tr>");
			pw.println("</table><br><br>");
			pw.println("<form action=\"marksheet\" method=\"get\">");
			pw.println("<input type=\"submit\" value=\"View Marksheet\">");
			pw.println("<input type=\"text\" name=\"uname\" value=\"" + request.getParameter("uname") + "\" hidden>");
			pw.println("</form>");
			pw.println("</div></body></html>");
		} catch (

		Exception e) {
			System.out.println(e);
			pw.println(e);
		}
	}
}