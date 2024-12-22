package com.itsc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DeleteServlet extends HttpServlet {
	
	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private static final String QUERY = "DELETE FROM books WHERE id = ?";

	    @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	        resp.setContentType("text/html");
	        PrintWriter pw = resp.getWriter();

	        // Get the ID of the record to delete
	        int id;
	        try {
	            id = Integer.parseInt(req.getParameter("id"));
	        } catch (NumberFormatException e) {
	            pw.println("<h2>Error: Invalid book ID format.</h2>");
	            pw.println("<a href='Home.html'>Home</a><br>");
	            pw.println("<a href='booklist'>Book List</a>");
	            return;
	        }

	        // Load JDBC driver
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	            pw.println("<h1>Error: Unable to load JDBC driver.</h1>");
	            return;
	        }

	        // Connect to the database and execute the delete query
	        try (Connection conn = DriverManager.getConnection("jdbc:mysql:///bookregister", "root", "root");
	             PreparedStatement ps = conn.prepareStatement(QUERY)) {

	            // Set query parameter
	            ps.setInt(1, id);

	            // Execute the delete query
	            int count = ps.executeUpdate();
	            if (count == 1) {
	                pw.println("<h2>Record has been successfully deleted.</h2>");
	            } else {
	                pw.println("<h2>Error: Record could not be deleted. It may not exist.</h2>");
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	            pw.println("<h1>Error: " + e.getMessage() + "</h1>");
	        }

	        // Navigation links
	        pw.println("<br><a href='Home.html'>Home</a>");
	        pw.println("<br><a href='booklist'>Book List</a>");
	    }

	    @Override
	    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        doGet(req, resp);
	    }
	}


