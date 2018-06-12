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


public class Teacher_signup extends HttpServlet 
{
	@id
	int teacher_id;
	String first_name;
	String last_name;
	String email_eddress;
	String password;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		Signup_Servicess service=new Signup_Servicess();

		String login_table="teacher_login_table";
		int redirect;
		
		redirect=service.sevicess(req,res,login_table);
		if(redirect==1)
		{
			String pass=req.getParameter("password");
		    HttpSession se=req.getSession();
		    se.setAttribute("teacher_password", pass);
			res.sendRedirect("display_teacher.jsp");

		}
		else
		{
			res.sendRedirect("sign-up-login-form/index.html");
		}
		
		
	}

}
