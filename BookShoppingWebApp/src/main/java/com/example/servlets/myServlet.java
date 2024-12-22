package com.example.servlets;

import com.example.utils.DatabaseUtils;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/connect")
public class myServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try (Connection connection = DatabaseUtils.getConnection()) {
            out.println("<h1>Database Connection Successful!</h1>");
        } catch (SQLException e) {
            out.println("<h1>Database Connection Failed!</h1>");
            e.printStackTrace(out);
        }
    }
}

