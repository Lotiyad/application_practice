package com.itsc.calculator;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
@WebServlet("/OperationServlet")
public class OperationServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String num1 = request.getParameter("num1");
        String num2 = request.getParameter("num2");
        String operation = request.getParameter("operation");

        try {
            int n1 = Integer.parseInt(num1);
            int n2 = Integer.parseInt(num2);
            int result = 0;

            switch (operation) {
                case "add":
                    result = n1 + n2;
                    break;
                case "subtract":
                    result = n1 - n2;
                    break;
            }

            response.setContentType("text/html");
            response.getWriter().println("<h1>Result: " + result + "</h1>");
        } catch (NumberFormatException e) {
            response.getWriter().println("<h1>Error: Invalid input</h1>");
        }
    }
}
