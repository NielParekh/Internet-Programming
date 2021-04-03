package Controller;

import Model.Marksheet;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.sql.*;

public class MarksheetServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html");
            PrintWriter pw = response.getWriter();
            pw.println("<html>");
            pw.println("<head><title>Admin Dashboard</title>");
            pw.println(
                    "<link rel=\"preconnect\" href=\"https://fonts.gstatic.com\"><link href=\"https://fonts.googleapis.com/css2?family=Poppins:wght@600&display=swap\" rel=\"stylesheet\">");
            pw.println("<style>");
            pw.println(
                    "body{font-family: \'Poppins\', sans-serif;display:flex; background: #8E2DE2;background: -webkit-linear-gradient(to bottom, #4A00E0, #8E2DE2);background: linear-gradient(to bottom, #4A00E0, #8E2DE2);font-color: #EEE;}");
            pw.println("div{margin:auto;}");
            pw.println("h1{font-size: 40px;}");
            pw.println("table, th, td {padding: 10px;border: 3px solid #EEE;border-collapse: collapse;}");
            pw.println("th{font-size: 32px;color: #EEE;}");
            pw.println("td{font-size: 24px;color: #EEE;}");
            pw.println("</style>");

            pw.println("<body onkeydown=\"return (event.keyCode != 116)\"> ");
            pw.println("<div>");
            pw.println("<h1 style=\"text-align: center;\">" + request.getParameter("uname") + "'s Marksheet</h1>");
            Marksheet m = new Marksheet();
            ResultSet rs = m.getMarks(request.getParameter("uname"));
            pw.println("<table>");
            pw.println("<tr>");
            pw.println("<th>Subject</th>");
            pw.println("<th>Mark</th>");
            pw.println("</tr>");

            for (int i = 1; i <= 5; i++) {

                pw.println("<tr>");
                pw.println("<td>" + rs.getString("sub" + i) + "</td>");
                pw.println("<td>" + rs.getInt("mark" + i) + "</td>");
                pw.println("</tr>");
            }
            pw.println("</div></body></html>");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
