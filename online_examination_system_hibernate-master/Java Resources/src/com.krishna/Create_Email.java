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

public class Create_Email extends HttpServlet {
       
    
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession se=request.getSession();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con1=null;
			try 
			{
				con1 = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/online_examination_syatem","root","");
				Statement st=(Statement) con1.createStatement();
				ResultSet rs=st.executeQuery("select Email_Address from  teacher_login_table");
				int i=0;
				while(rs.next())
				{
					
					se.setAttribute(i+"Email_of_teacher", rs.getString("Email_Address"));
					
					i++;
				}
				se.setAttribute("avable_Email_number", i);
				response.sendRedirect("available_teacher_email.jsp");
			} 
			catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				try {
					con1.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
