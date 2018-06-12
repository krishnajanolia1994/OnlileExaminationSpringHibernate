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


public class Create_Subject_Koocki extends HttpServlet 
{
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
		{
			HttpSession se=request.getSession();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/online_examination_syatem","root","");
				Statement st=(Statement) con.createStatement();
				String table=se.getAttribute("email")+"_subject_main_table";
				ResultSet rs=st.executeQuery("select Subject_Name from  "+table);
				int i=0;
				while(rs.next())
				{
					Cookie cookie=new Cookie(""+i,rs.getString("Subject_Name"));
					response.addCookie(cookie);
					cookie.setMaxAge(0);
					se.setAttribute(i+"subject", rs.getString("Subject_Name"));
					
					i++;
				}
				se.setAttribute("avable_subject_number", i);
				int k=(Integer) se.getAttribute("avable_subject_number");
				response.sendRedirect("available_subject.jsp");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

}
