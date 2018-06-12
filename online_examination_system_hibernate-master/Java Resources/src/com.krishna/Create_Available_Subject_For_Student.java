package com.krishna;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;


public class Create_Available_Subject_For_Student extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		HttpSession se=req.getSession();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con1=null;
			try {
				con1 = (Connection) DriverManager.getConnection("jdbc:mysql:"
						+ "//localhost:3306/online_examination_syatem","root","");
				Statement st=(Statement) con1.createStatement();
				String table=(String) se.getAttribute("Email_Eddress_Table")+"_subject_main_table";
				ResultSet rs=st.executeQuery("select Subject_Name from  "+table);
				int i=0;
				while(rs.next())
				{
					
					se.setAttribute(i+"Avalable_Subject", rs.getString("Subject_Name"));
					
					i++;
				}
				se.setAttribute("avable_subject_number", i);
				res.sendRedirect("available_subject_for_student.jsp");
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
