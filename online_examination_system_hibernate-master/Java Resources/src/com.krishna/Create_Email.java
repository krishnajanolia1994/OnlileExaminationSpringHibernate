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
import javax.persitence.Cacheable;


public class Create_Email extends HttpServlet {
       
    
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession se=request.getSession();
		Configuration con=new Configuration().Configure().addAnnotatedClass(Teacher_signup.class);
		ServiceRegistry sr=new ServiceRegistryBuilder().addApliedSetings(con.getProperties()).BuildServiceRegistry();
		SessioFactory sf=con.BuildSessionFactory(sr);
		Session hibernet_session=sf.openSeeion();
		Quary q=hibernet_session.createQuary("select email_address form Teacher_signup");
		ArryaList<String> email_list=(String)q.list();
		int i=0;
		for(String str : email_list)
		{
			se.setAttribute(i+"Email_of_teacher", str);
			i++;
		}
		
		se.setAttribute("avable_Email_number", i);
		response.sendRedirect("available_teacher_email.jsp");
			
		
	}

}
