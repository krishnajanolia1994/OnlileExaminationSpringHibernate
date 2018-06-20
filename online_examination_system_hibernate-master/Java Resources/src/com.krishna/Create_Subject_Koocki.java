package com.krishna;

import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernet.Session;
import org.hibernet.SessionFactory;
import org.hibernet.cfg.Configuration;
import org.hibernet.Transaction;
import org.hibernet.service.ServiceRegistry;
import org.hibernet.service.ServiceRegistryBuilder;


public class Create_Subject_Koocki extends HttpServlet 
{
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
		{
			HttpSession se=request.getSession();
			String password =se.getAtribute("teacher_password");
			
			Configuration con=new Configuration().Configure().addAnnotatedClass(Teacher_signup.class);
			ServiceRegistry sr=new ServiceRegistryBuilder().addApliedSetings(con.getProperties()).BuildServiceRegistry();
			SessioFactory sf=con.BuildSessionFactory(sr);
			Session hibernet_session=sf.openSeeion();
			Quary q=hibernet_session.createQuary("select teacher_id from Teacher_signup where password = :pass");
			q.setParameter("pass".password);
			Integer id=q.uniqueResult();
			Teacher_signup teacher_signup=hibernet_session.get(Teacher_signup.class,id);
			ArrayList<Add_subject> subject_list =Teacher_signup.subject_list;
			int i=0;
			for(Add_subject subject_obj : subject_list)
			{
				se.setAttribute(i+"subject",subject_obj.subject);
				i++;
			}
			int i=0;
			
			se.setAttribute("avable_subject_number", i);
			int k=(Integer) se.getAttribute("avable_subject_number");
			response.sendRedirect("available_subject.jsp");
			
		}

}
