package in.sp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import in.sp.dbcon.dbConnection;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/register")
public class Register extends HttpServlet {

	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		PrintWriter out=resp.getWriter();
		resp.setContentType("text/html");
		
		
		String myname=req.getParameter("name");
		String myemail=req.getParameter("email");
		String Pass=req.getParameter("Password");
		String City=req.getParameter("City");
	try {
		
		Connection con = dbConnection.getConnection();
		String insert_sql_query="INSERT INTO mvc_register VALUES(?,?,?,?)";
		PreparedStatement ps=con.prepareStatement(insert_sql_query);
		ps.setString(1, myname);
		ps.setString(2, myemail);
		ps.setString(3, Pass);
		ps.setString(4, City);
		
	int i=	ps.executeUpdate();
	
	if(i>0) {
		
		out.println("<h3 style='color:green'>Registered Successfully<h3>");
		
		RequestDispatcher rd= req.getRequestDispatcher("/login.html");
		rd.include(req, resp);
	}}
	
//	else
//		out.println("<h3 style='color:red'>User not Registered <h3>");
//	
//	RequestDispatcher rd= req.getRequestDispatcher("/Register.html");
//	rd.include(req, resp);
//	}
	
	catch (Exception e)
	
	{
e.printStackTrace();
	}
	
	}
	
	
}
