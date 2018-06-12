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
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;


public class Add_subject extends HttpServlet {
	

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		Connection con=null;
		Statement st=null;
		HttpSession se=(HttpSession) req.getSession();
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/online_examination_syatem","root","");
			st =(Statement) con.createStatement();
			String subject=req.getParameter("subject_name");
			String table=se.getAttribute("email")+"_subject_main_table";
			ResultSet rs =st.executeQuery("select Subject_Name from "+table+" where Subject_Name = '"+subject+"'");
			if(rs.next())
			{
				se.setAttribute("subject_exist_error","given subject is Alraidy Exist ");
				res.sendRedirect("add_subjectT.jsp");
			}
			else
			{
				PreparedStatement pst=(PreparedStatement) con.prepareStatement("insert into "
			+se.getAttribute("email")+"_subject_main_table (Subject_Name) values (?)");
				pst.setString(1,subject);
				pst.executeUpdate();
				String create_table="CREATE TABLE "+se.getAttribute("email")+subject+""
						+ "( Test_id int AUTO_INCREMENT , Test_Name varchar(20),Number_Question int,hour int,"
						+ " minute int , PRIMARY KEY(Test_id))";
				st.execute(create_table);
				se.setAttribute("subject",subject);
				pst.close();
				res.sendRedirect("add_testT.jsp");
				
				
			}
			
		} 
		catch (ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally 
		{
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
