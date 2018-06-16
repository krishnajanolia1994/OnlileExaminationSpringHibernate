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
javax.persitence.Cacheable;



public class Evalute_result extends HttpServlet 
{
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		HttpSession se=req.getSession();
		int num_que_op=(Integer) se.getAttribute("avable_question_option");
		

		
		int i=0;
		PrintWriter pr=res.getWriter();
		
		Configuration con=new Configuration().Configure().addAnnotatedClass(Teacher_signup.class).addAnnotatedClass(Add_subject.class).addAnnotatedClass(Id.class);
		ServiceRegistry sr=new ServiceRegistryBuilder().addApliedSetings(con.getProperties()).BuildServiceRegistry();
		SessioFactory sf=con.BuildSessionFactory(sr);
		Session hibernet_session=sf.openSeeion();
		long test_id=(Long) se.getAttribute("test_id");
		Add_test add_test=hibernet_session.get(Add.test.classw,test_id);
		ArratList<Add_Qustion_Servlet> question_list=Add_test.question_list;
		
		int ans_obtain;
		int right_index;
		String Correct_answer;
		int total_marks=0;
		int marks_obtain=0;
		for(Add_Qustion_Servlet question_obj: question_list)
		{
			ans_obtain= Integer.parseInt(req.getParameter(i+"Question"));
			right_index=question_qbj.answer;
			if(ans_obtain==right_index)
				marks_obtain++;
			total_marks++;
			i+=5;
		}
		se.setAttribute("total_marks", total_marks);
		se.setAttribute("marks_obtain", marks_obtain);
		se.setAttribute("test_name");
		Result_servise servise=new Result_servise();
		servise.servise(req, res);
	}

}
