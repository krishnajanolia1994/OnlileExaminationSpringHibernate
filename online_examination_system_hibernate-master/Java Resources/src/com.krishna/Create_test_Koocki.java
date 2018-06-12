package com.krishna;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Create_test_Koocki extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			try {
				Connection con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/"
						+ "online_examination_syatem","root","");
				HttpSession sc=request.getSession();
				String Subject=(String) sc.getAttribute("subject");
				Statement st=(Statement) con.createStatement();
				ResultSet rs=st.executeQuery("select Test_Name from  "+Subject);
				int i=0;
				while(rs.next())
				{
					Cookie cookie=new Cookie(""+i,rs.getString("Test_Name"));
					response.addCookie(cookie);
					cookie.setMaxAge(0);
					
					i++;
				}
				response.sendRedirect("available_test.jsp");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}

}
