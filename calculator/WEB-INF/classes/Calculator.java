import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.lang.Math;

public class Calculator extends HttpServlet {
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        String n1 = request.getParameter("op1");
        String n2 = request.getParameter("op2");
        String op = request.getParameter("operator");
        switch (op) {
        case "add":
            out.println("Sum is: " + (Float.parseFloat(n1) + Float.parseFloat(n2)));
            break;
        case "subtract":
            out.println("Difference is: " + (Float.parseFloat(n1) - Float.parseFloat(n2)));
            break;
        case "multiply":
            out.println("Product is: " + (Float.parseFloat(n1) * Float.parseFloat(n2)));
            break;
        case "divide":
            out.println("Quotient is: " + (Float.parseFloat(n1) / Float.parseFloat(n2)));
            break;
        case "power":
            out.println("Result is: " + Math.pow(Float.parseFloat(n1), Float.parseFloat(n2)));
            break;
        // case "modulus":
        // out.println("Remainder is: " + (Float.parseFloat(n1) %
        // Float.parseFloat(n2)));
        // break;
        default:
            out.println("Invalid Operand!");
            break;
        }

    }
}
