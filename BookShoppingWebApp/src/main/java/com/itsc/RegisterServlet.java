package com.itsc;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String QUERY = 
        "insert into books(bookname, bookedition, bookprice) values(?, ?, ?)";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        resp.setContentType("text/html");

        // Load JDBC driver
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException cnf) {
            cnf.printStackTrace();
            pw.println("<h1>Error loading JDBC Driver: " + cnf.getMessage() + "</h1>");
            return;
        }

        // Get book details from request
        String bookName = req.getParameter("bookName");
        String bookEdition = req.getParameter("bookEdition");
        float bookPrice;

        try {
            bookPrice = Float.parseFloat(req.getParameter("bookPrice"));
        } catch (NumberFormatException nfe) {
            pw.println("<h1>Invalid book price.</h1>");
            return;
        }

        // Insert book into database
        try (Connection conn = DriverManager.getConnection("jdbc:mysql:///bookregister", "root", "root")) {
            PreparedStatement ps = conn.prepareStatement(QUERY);
            ps.setString(1, bookName);
            ps.setString(2, bookEdition);
            ps.setFloat(3, bookPrice);

            int count = ps.executeUpdate();

            if (count == 1) {
                pw.println("<h2>Book registered successfully.</h2>");
            } else {
                pw.println("<h2>Book registration failed.</h2>");
            }
        } catch (SQLException se) {
            se.printStackTrace();
            pw.println("<h1>Database error: " + se.getMessage() + "</h1>");
        } catch (Exception e) {
            e.printStackTrace();
            pw.println("<h1>" + e.getMessage() + "</h1>");
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


