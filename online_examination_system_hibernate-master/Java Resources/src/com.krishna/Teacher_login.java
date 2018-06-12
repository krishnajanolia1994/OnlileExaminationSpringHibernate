package com.krishna;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Teacher_login extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		HttpSession se=req.getSession();

		int redirect ;
		Singin_servicess serv =new Singin_servicess();
		try {
			redirect=serv.sevice(req, res, "teacher_login_table");
			if(redirect==1)
			{
				String email=req.getParameter("Email_Address");
				String temp1,temp2;
				int index=email.indexOf("@");
				temp1=email.substring(0, index);
				temp2=email.substring(index+1, email.length()-1);
				email=temp1+temp2;
				se.setAttribute("email", email);
				res.sendRedirect("display_teacher.jsp");
			}
			else
			{
				se.setAttribute("login_eroor", "please enter valid cradantial");
				res.sendRedirect("index2.jsp");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
