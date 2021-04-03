package Controller;

import Model.UserDetails;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;

public class RegisterServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/plain;charset=UTF-8");
            ServletOutputStream out = response.getOutputStream();
            UserDetails ud = new UserDetails();
            String username = request.getParameter("username");
            // System.out.println("Username in doGet(): " + username);
            String[] usersArray = ud.getUsers();
            int flag = 0;
            for (int i = 0; i < usersArray.length; i++) {
                // System.out.println("User: " + usersArray[i]);
                if (username.equals(usersArray[i])) {
                    flag = 1;
                    out.print("0");
                }
            }
            if (flag == 0) {
                out.print("1");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            UserDetails ud = new UserDetails();
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            ud.insertUser(username, password);
            out.println("alert(\"Successfully added user!\")");
            response.sendRedirect("login.html");

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
