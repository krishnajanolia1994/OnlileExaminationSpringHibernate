package com.krishna;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;


public class Student_signup extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		HttpSession se=req.getSession();
		String login_table="student_login_table";
		Signup_Servicess service=new Signup_Servicess();
		int redirect;
		try {
			redirect=service.sevicess(req,res,login_table);
			if(redirect==1)
			{
				String email=req.getParameter("Email_Address");
				String temp1,temp2;
				int index=email.indexOf("@");
				temp1=email.substring(0, index);
				temp2=email.substring(index+1, email.length()-1);
				email=temp1+temp2;
				se.setAttribute("email_of_student",email);

				String str="CREATE TABLE  "+email+"(  result_id int AUTO_INCREMENT , "
						+ " test_name  varchar(40), maximum_marks int , marks_obtsin int ,PRIMARY KEY(result_id))";
				Connection con=null;
				Statement st=null;
				con=(Connection) DriverManager.getConnection("jdbc:mysql:"
						+ "//localhost:3306/online_examination_syatem", "root", "");
				st=(Statement) con.createStatement();
				st.execute(str);
				con.close();
				st.close();
				res.sendRedirect("display.jsp");
			}
			else
			{
				res.sendRedirect("index.html");
			}
		} catch (ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
