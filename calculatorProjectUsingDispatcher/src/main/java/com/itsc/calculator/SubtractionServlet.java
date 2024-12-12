package com.itsc.calculator;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class SubtractionServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int num1 = Integer.parseInt(request.getParameter("num1"));
        int num2 = Integer.parseInt(request.getParameter("num2"));
        int difference = num1 - num2;
        
        PrintWriter pw = response.getWriter();
        pw.println("<h2>Result of Subtraction</h2>");
        pw.println("<p>" + num1 + " - " + num2 + " = " + difference + "</p>");
    }
}