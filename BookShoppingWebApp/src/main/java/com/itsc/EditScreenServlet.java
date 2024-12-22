package com.itsc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class EditScreenServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	    private static final String QUERY = "SELECT bookname, bookedition, bookprice FROM books WHERE id = ?";

	    @Override
	    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	        resp.setContentType("text/html");
	        PrintWriter pw = resp.getWriter();

	        // Get the ID of the record to edit
	        int id = Integer.parseInt(req.getParameter("id"));

	        // Initialize database connection resources
	        try (Connection conn = DriverManager.getConnection("jdbc:mysql:///bookregister", "root", "root");
	             PreparedStatement ps = conn.prepareStatement(QUERY)) {

	            ps.setInt(1, id);

	            try (ResultSet rs = ps.executeQuery()) {
	                if (rs.next()) {
	                    // Start the form
	                    pw.println("<form action='editurl?id=" + id + "' method='post'>");
	                    pw.println("<table>");

	                    // Book Name
	                    pw.println("<tr>");
	                    pw.println("<td>Book Name:</td>");
	                    pw.println("<td><input type='text' name='bookName' value='" + escapeHtml(rs.getString(1)) + "'></td>");
	                    pw.println("</tr>");

	                    // Book Edition
	                    pw.println("<tr>");
	                    pw.println("<td>Book Edition:</td>");
	                    pw.println("<td><input type='text' name='bookEdition' value='" + escapeHtml(rs.getString(2)) + "'></td>");
	                    pw.println("</tr>");

	                    // Book Price
	                    pw.println("<tr>");
	                    pw.println("<td>Book Price:</td>");
	                    pw.println("<td><input type='text' name='bookPrice' value='" + rs.getFloat(3) + "'></td>");
	                    pw.println("</tr>");

	                    // Form Actions
	                    pw.println("<tr>");
	                    pw.println("<td colspan='2'><input type='submit' value='Edit'> <input type='reset' value='Cancel'></td>");
	                    pw.println("</tr>");

	                    pw.println("</table>");
	                    pw.println("</form>");
	                } else {
	                    pw.println("<h3>No record found with ID: " + id + "</h3>");
	                }
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	            pw.println("<h1>Error: " + e.getMessage() + "</h1>");
	        }

	        pw.println("<br><a href='Home.html'>Home</a>");
	    }

	    @Override
	    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	        doGet(req, resp);
	    }

	    /**
	     * Escapes HTML characters to prevent XSS.
	     */
	    private String escapeHtml(String input) {
	        return input == null ? "" : input.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;").replace("\"", "&quot;").replace("'", "&#x27;");
	    }
	}
