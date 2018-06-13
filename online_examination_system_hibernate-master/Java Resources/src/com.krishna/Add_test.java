package com.krishna;

import java.io.IOException
;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
@Chachable
@Chache(Usage=ChacheConcarancyStratagy.READ_WRITE)
public class Add_test extends HttpServlet
{
	@Id
	int test_id
	String test_name;
	int Number_question;
	int num_hour ;
	int num_min;
	@ManyToOne
	Add_subject subject;
	@OneToMany
	ArrayList<Add_Qustion_Servlet> question_list =new ArrayList<Add_Qustion_Servlet>();
    
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		
		HttpSession se=req.getSession();
		String test_name=req.getParameter("test_name");
		int Number_question =Integer.parseInt(req.getParameter("Number_question"));
		int num_hour=Integer.parseInt(req.getParameter("hours"));
		int num_min=Integer.parseInt(req.getParameter("minute"));
		String Subject=(String) se.getAttribute("subject");
		pr.println(Subject);
		Configuration con=new Configuration().Configure().addAnnotatedClass(Add_subject.class);
		ServiceRegistry sr=new ServiceRegistryBuilder().addApliedSetings(con.getProperties()).BuildServiceRegistry();
		SessioFactory sf=con.BuildSessionFactory(sr);
		Session hibernet_session=sf.openSeeion();
		Quary q=hibernet_session.createQuary("select subject_id from Add_subject where subject =:sub");
		q.setPrameter("sub", Subject);
		Integer id=p.uniqueResult();
		Transection tx=hibernet_session.biginTransection();
		Add_subject add_sub=hibernet_session.get(Add_subject.class,id);
		ArrayList<Add_test> test_list=add_sub.Add_test;
		if(test_list.contais(test_name))
		{
			se.setAttribute("test_exist_error","given test is Alraidy Exist ");
			res.sendRedirect("add_testT.jsp");
		}
		else
		{
			Add_teat new_test=new Add_test();
			new_test.test_name=test_name;
			new_test.Number_question=Number_question;
			new_test.num_hour=num_hour ;
			new_test.num_min=num_min;
			test_list.add(new_test);
			
		}
		tx.comit();
		se.setAtribute("test_name",test_name);
		res.sendRedirect("add_question.jsp");
		
	}

}
