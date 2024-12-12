package com.itsc.calculator;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OperationServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String operation = request.getParameter("operation");
        RequestDispatcher dispatcher = null;

        switch (operation) {
            case "add":
                dispatcher = request.getRequestDispatcher("/additionservlet");
                break;
            case "subtract":
                dispatcher = request.getRequestDispatcher("/subtractionservlet");
                break;
            default:
                response.getWriter().println("Invalid Operation");
                return;
        }
        
        dispatcher.forward(request, response);
    }
}