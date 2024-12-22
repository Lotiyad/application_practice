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

public class EditServlet extends HttpServlet {
	

	    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private static final String QUERY = "UPDATE books SET bookname = ?, bookedition = ?, bookprice = ? WHERE id = ?";

	    @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	        resp.setContentType("text/html");
	        PrintWriter pw = resp.getWriter();

	        // Retrieve data from request
	        int id = Integer.parseInt(req.getParameter("id"));
	        String bookName = req.getParameter("bookName");
	        String bookEdition = req.getParameter("bookEdition");
	        float bookPrice = Float.parseFloat(req.getParameter("bookPrice"));

	        // Load JDBC driver
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	        } catch (ClassNotFoundException e) {
	            e.printStackTrace();
	            pw.println("<h1>Error: Unable to load JDBC driver.</h1>");
	            return;
	        }

	        // Update the record in the database
	        try (Connection conn = DriverManager.getConnection("jdbc:mysql:///bookregister", "root", "root");
	             PreparedStatement ps = conn.prepareStatement(QUERY)) {

	            // Set query parameters
	            ps.setString(1, bookName);
	            ps.setString(2, bookEdition);
	            ps.setFloat(3, bookPrice);
	            ps.setInt(4, id);

	            // Execute the update
	            int count = ps.executeUpdate();
	            if (count == 1) {
	                pw.println("<h2>Record has been edited successfully.</h2>");
	            } else {
	                pw.println("<h2>Error: Record could not be updated.</h2>");
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	            pw.println("<h1>Error: " + e.getMessage() + "</h1>");
	        }

	        // Provide navigation links
	        pw.println("<br><a href='Home.html'>Home</a>");
	        pw.println("<br><a href='booklist'>Book List</a>");
	    }

	    @Override
	    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	        doGet(req, resp);
	    }
	}


