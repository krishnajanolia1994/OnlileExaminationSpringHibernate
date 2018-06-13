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
		String email;
		String pasword;
		email=req.getParameter("Email_Address");
		password=req.getParameter("password");
		Integer id;
		Configuration con=new Configuration().Configure().addAnnotatedClass(Teacher_signup.class).addAnnotatedClass(Add_subject.class);
		ServiceRegistry sr=new ServiceRegistryBuilder().addApliedSetings(con.getProperties()).BuildServiceRegistry();
		SessioFactory sf=con.BuildSessionFactory(sr);
		Session hibernet_session=sf.openSeeion();
		Quary q=hibernet_session.createQuary("select teacher_id form Teacher_signup where password = : psaa and email_adress=:add");
		q.setParameter("pass", password);
		q.setParameter("add", email);
		if(id=!null)
		{
			se.setAttribute("teacher_password" , password);
			res.sendRedirect("display_teacher.jsp");
		}
		}
		else
		{
			se.setAttribute("login_eroor", "please enter valid cradantial");
			res.sendRedirect("index2.jsp");
		}
		
	}

}
