package com.krishna;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class Result_servise {
	public void servise(HttpServletRequest req, HttpServletResponse res)
	{
		HttpSession se=req.getSession();
		try 
		{
			Connection Con1=null;
			Class.forName("com.mysql.jdbc.Driver");
			try {
				Con1 = (Connection) DriverManager.getConnection("jdbc:mysql:"
						+ "//localhost:3306/online_examination_syatem","root","");
				String table=(String) se.getAttribute("email_of_student");
				int total_marks=(Integer) se.getAttribute("total_marks");
				int marks_obtain=(Integer) se.getAttribute("marks_obtain");
				String test=(String) se.getAttribute("test_table");

				PreparedStatement pst=(PreparedStatement) Con1.prepareStatement("insert into  "+table+  "(  test_name , maximum_marks , marks_obtsin) values(? , ? , ? )");
				pst.setString(1, test);
				pst.setInt(2, total_marks);
				pst.setInt(3, marks_obtain);
				pst.executeUpdate();
				pst.close();
				try {
					res.sendRedirect("display.jsp");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (SQLException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			finally
			{
				try {
					Con1.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		} 
		catch (ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
