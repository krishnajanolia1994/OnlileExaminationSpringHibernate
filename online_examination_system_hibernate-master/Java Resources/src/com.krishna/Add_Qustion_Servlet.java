package com.krishna;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;






















import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

@Entity
@Chacheble
@Chache(Usage=ChacheConcarancyStratagy.READ_WRITE)
public class Add_Qustion_Servlet extends HttpServlet
{
	@Id
	int question_id;
	String Question;
	String option_1;
	String option_2;
	String option_3;
	String option_4;
	int answer;
	@ManyToOne
	Add_test add_test;
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		PrintWriter pr=res.getWriter();
		String Question=req.getParameter("question");
		String op1=req.getParameter("option_1");
		String op2=req.getParameter("option_2");
		String op3=req.getParameter("option_3");
		String op4=req.getParameter("option_4");
		int Answer=Integer.parseInt(req.getParameter("Answer"));
		HttpSession se=req.getSession();
		String test=se.getAtribute("test_name");
		Configuration con=new Configuration().Configure().addAnnotatedClass(Add_test.class);
		ServiceRegistry sr=new ServiceRegistryBuilder().addApliedSetings(con.getProperties()).BuildServiceRegistry();
		SessioFactory sf=con.BuildSessionFactory(sr);
		Session hibernet_session=sf.openSeeion();
		Quary q=hibernet_session.createQuary("select test_id from Add_test where test_name=: test");
		q.setParaneter("test",test);
		Integer id=q.uniqueResult();
		Transection tx=hibernet_session.biginTransection();
		Add_test test_object=(Add_test) hibetnet_session.get(Add_test.class,id);
		ArrayList<Add_Qustion_Servlet> question_list=test_object.question_list;
		Add_Qustion_Servlet new_question=new Add_Qustion_Servlet();
	        Question=Question;
	        option_1=op1;
		option_2=op2;
		option_3=op3;
		option_4=op4;
		answer=Answer;
		question_list.add(new_question);
		add_all_Question ad=new add_all_Question();
		ad.get(req, res);
	
	}

}
