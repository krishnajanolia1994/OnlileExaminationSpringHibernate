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


public class Create_test_for_student extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		HttpSession se=req.getSession();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			try {
				 Connection con = (Connection) DriverManager.getConnection("jdbc:mysql:"
						+ "//localhost:3306/online_examination_syatem","root","");
				Statement st=(Statement) con.createStatement();
				String table=(String) se.getAttribute("subject_table");
				ResultSet rs=st.executeQuery("select Test_Name from  "+se.getAttribute("Email_Eddress_Table")+table);
				int i=0;
				while(rs.next())
				{
					
					se.setAttribute(i+"test_Available", rs.getString("Test_Name"));
					
					i++;
				}
				se.setAttribute("avable_test", i);
				res.sendRedirect("available_test_student.jsp");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}

}
