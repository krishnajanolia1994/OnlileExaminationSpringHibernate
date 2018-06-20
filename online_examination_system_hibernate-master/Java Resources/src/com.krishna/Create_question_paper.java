package com.krishna;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import org.hibernet.Session;
import org.hibernet.SessionFactory;
import org.hibernet.cfg.Configuration;
import org.hibernet.service.ServiceRegistry;
import org.hibernet.service.ServiceRegistryBuilder;
import javax.persitence.Cacheable;



public class Create_question_paper extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		HttpSession se=req.getSession();
		String Subject=(String) se.getAttribute("subject_table");
		String test=(String) se.getAttribute("test_table");
		int i=0;
		Configuration con=new Configuration().Configure().addAnnotatedClass(Teacher_signup.class).addAnnotatedClass(Add_subject.class).addAnnotatedClass(Id.class);
		ServiceRegistry sr=new ServiceRegistryBuilder().addApliedSetings(con.getProperties()).BuildServiceRegistry();
		SessioFactory sf=con.BuildSessionFactory(sr);
		Session hibernet_session=sf.openSeeion();
		Long id=(Long)se.getAttribute("test_id");
		Add_test add_test = hibernet_session.get(Add_test.class,id);
		ArrayList<Add_Qustion_Servlet> question_list=add_test.question_list;
		for(Add_Qustion_Servlet question_obj : question_list)
		{
			se.setAttribute(i+"Question",question_obj.Question );
			se.setAttribute(i+1+"OptionI", question_obj.option_1);
			se.setAttribute(i+2+"OptionII", question_obj.option_2);
			se.setAttribute(i+3+"OptionIII", question_obj.option_3);
			se.setAttribute(i+4+"OptionIV", question_obj.option_4);
			i+=5;
		}
		int hour= add_test.num_hour;
		int minut=add_test.num_min;
		int time_in_sec=hour*3600 + minut*60;
		se.setAttribute("time_in_sec", time_in_sec);
		se.setAttribute("avable_question_option", i);
		res.sendRedirect("start_test.jsp");

			
	}

}
