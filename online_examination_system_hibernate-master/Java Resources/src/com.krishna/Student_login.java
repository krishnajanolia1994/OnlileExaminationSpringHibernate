package com.krishna;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Student_login extends HttpServlet {
	

	
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		
		HttpSession se=(HttpSession) req.getSession();
		String add=req.getPerameter("Email_Address");
		String pass=req.getPerameter("password");
		Configuration con=new Configuration().Configure().addAnnotatedClass(Student_login.class);
		ServiceRegistry sr=new ServiceRegistryBuilder().addApliedSetings(con.getProperties()).BuildServiceRegistry();
		SessioFactory sf=con.BuildSessionFactory(sr);
		Session hibernet_session=sf.openSeeion();
		Quary q=hibernet_session.createQuary("select teacher_id form Teacher_signup where email_address=:add password = : pass");
		q.setParameter("add":add);
		q.setParameter("pass",pass);
		if(id!=null)
		{
			se.setAttribute("student_password", pass);
			res.sendRedirect("display.jsp");
		}
		else
			res.sendRedirect("index1_login.jsp");

	}

}
