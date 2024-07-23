package in.sp.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.jasper.tagplugins.jstl.core.Out;

import com.mysql.cj.xdevapi.Result;

import in.sp.dbcon.dbConnection;
import in.sp.model.User;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class Login extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		PrintWriter out=resp.getWriter();// this use for out.println we have to provide  PrintWriter reference 
		resp.setContentType("text/html");// this is for providding that we are using html also
		
		String myemail=req.getParameter("email");
		String Pass=req.getParameter("password");
		try {
			Connection con =dbConnection.getConnection();
			String select_sql_qury="SELELCT * FROM mvc_register WHERE email=? AND password=?";
			PreparedStatement ps=con.prepareStatement(select_sql_qury);
			ps.setString(1, myemail);
			ps.setString(2,Pass);
			
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				User user=new User();
				user.setName(rs.getString("name"));
				user.setEmail(rs.getString("email"));
				user.setCity(rs.getString("City"));
				
				HttpSession session=req.getSession();
				session.setAttribute("session_user",user);
				RequestDispatcher rd= req.getRequestDispatcher("/profile.jsp");
				rd.forward(req,resp);
			}
			else {
				out.println("<h3 style='color:red> Eamil Id and Password not match</h3>");
				
				RequestDispatcher rd= req.getRequestDispatcher("/login.html");
				rd.include(req, resp); 
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
