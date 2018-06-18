package com.krishna;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernet.Session;
import org.hibernet.SessionFactory;
import org.hibernet.cfg.Configuration;
import org.hibernet.service.ServiceRegistry;
import org.hibernet.service.ServiceRegistryBuilder;
import  javax.persitence.Cacheable;



public class Create_test_for_student extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		HttpSession se=req.getSession();
		Configuration con=new Configuration().Configure().addAnnotatedClass(Teacher_signup.class).addAnnotatedClass(Add_subject.class).addAnnotatedClass(Id.class);
		ServiceRegistry sr=new ServiceRegistryBuilder().addApliedSetings(con.getProperties()).BuildServiceRegistry();
		SessioFactory sf=con.BuildSessionFactory(sr);
		Session hibernet_session=sf.openSeeion();
		Long subject_id=se.getAttribute("subject_id");
		Add_subjet add_subject =hibernet_session.get(Add_subject.class,subject_id);
		ArrayList <Add_test> test_list=add_subject.test_list;
		int i=0;
		for(Add_test test_obj : test_list)
		{
			se.setAttribute(i+"test_Available", test_obj);
			i++;	
		}
		
		se.setAttribute("avable_test", i);
		res.sendRedirect("available_test_student.jsp");
	
	}

}
