package com.krishna;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernet.Session;
import org.hibernet.SessionFactory;
import org.hibernet.cfg.Configuration;
import org.hibernet.service.ServiceRegistry;
import org.hibernet.service.ServiceRegistryBuilder;


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
