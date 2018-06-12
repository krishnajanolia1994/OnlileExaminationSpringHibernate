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


public class Create_result_table extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		HttpSession se=req.getSession();
		String table=(String) se.getAttribute("email_of_student");
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con;
			try {
				con = (Connection) DriverManager.getConnection("jdbc:mysql:"
						+ "//localhost:3306/online_examination_syatem","root","");
				Statement st=(Statement) con.createStatement();
				ResultSet rs=st.executeQuery("select test_name , maximum_marks , marks_obtsin from  "+table);
				int i=0;
				while(rs.next())
				{
					
					se.setAttribute(i+"test_name_tab", rs.getString("test_name"));
					se.setAttribute(i+"max_mark", rs.getInt("maximum_marks"));
					se.setAttribute(i+"marks_obtain", rs.getInt("marks_obtsin"));
					
					i++;
				}
				se.setAttribute("avable_test_number", i);
				res.sendRedirect("final_result_table.jsp");
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
