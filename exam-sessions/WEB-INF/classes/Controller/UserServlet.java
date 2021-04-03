package Controller;

import Model.*;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class UserServlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse res) throws ServletException, IOException {
		String username = request.getParameter("username");
		// System.out.println("Username is: " + username);
		String password = request.getParameter("password");
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		UserDetails ud = new UserDetails();

		try {

			String result = ud.getPassword(username);
			if (result.equals("_INVALID_")) {
				pw.println("<h1>Invalid Username!</h1><br><br>");
				pw.println("<button onclick=\"location.href = 'login.html';\">Contact admin and try again</button>");

			} else {
				if (username.equals("admin")) {
					if (result.equals(password)) {
						System.out.println("Admin login authorized!");
						res.sendRedirect("admin");
					} else {
						System.out.println("\nInvalid Admin Password!");
						pw.println("<h1>Invalid Password!</h1><br><br>");
						pw.println("<button onclick=\"location.href = 'login.html';\">Try again</button>");
					}
				}

				else if (result.equals(password)) {
					HttpSession session = request.getSession();
					session.setAttribute("username", new String(username));
					// System.out.println("Session created: " + (String)
					// session.getAttribute("username"));
					System.out.println("\nPassword Matched. Login Successful");
					// pw.println("<h1>Login Successful..!</h1>");
					// pw.println("<script> alert(\"Login successful\");</script>");
					res.sendRedirect("questions?uname=" + username);
				} else {
					System.out.println("\nInvalid Password!");
					pw.println("<h1>Invalid Password!</h1><br><br>");
					pw.println("<button onclick=\"location.href = 'login.html';\">Try again</button>");
				}
			}

		} catch (Exception e) {
			System.out.println("Exception thrown : " + e);
		}
	}
}