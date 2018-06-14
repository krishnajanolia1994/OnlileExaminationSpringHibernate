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
		Configuration con=new Configuration().Configure().addAnnotatedClass(Add_test.class).addAnnotatedClass(Add_Qustion_Servlet.class);
		ServiceRegistry sr=new ServiceRegistryBuilder().addApliedSetings(con.getProperties()).BuildServiceRegistry();
		SessioFactory sf=con.BuildSessionFactory(sr);
		Session hibernet_session=sf.openSeeion();
		Long id=(Long)se.getAttribute("test_id");
		Add_test add_test =hibetnet_session.get(Add_test.class,id);
		ArraList<Add_Qustion_Servlet> question_list=add_test.question_list;
		Add_Qustion_Servlet new_question =new Add_Qustion_Servlet();
		new_question.Question=Question;
	        new_question.option_1=op1;
	        new_question.option_2=op2;
	        new_question.option_3=op3;
	        new_question.option_4=op4;
		new_question.answer=Answer;
		question_list.add(new_question);
		Transection tx=hibernet_session.biginTransection();
		hibernet_session.save(add_test)
		tx.comit();
		add_all_Question ad=new add_all_Question();
		ad.get(req, res);
	
	}

}
