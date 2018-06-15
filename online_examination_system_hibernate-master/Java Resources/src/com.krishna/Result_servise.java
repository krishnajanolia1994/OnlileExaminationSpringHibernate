package com.krishna;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class Result_servise 
{
	String test_name;
	int total_marks;
	int marks_obtai;
	@ManyToOne
	Student_signup stud;
	public void servise(HttpServletRequest req, HttpServletResponse res)
	{
		HttpSession se=req.getSession();
		String std_password=(String)se.getAttribute("student_password");
		Configuration con=new Configuration().Configure().addAnnotatedClass(Teacher_signup.class).addAnnotatedClass(Add_subject.class);
		ServiceRegistry sr=new ServiceRegistryBuilder().addApliedSetings(con.getProperties()).BuildServiceRegistry();
		SessioFactory sf=con.BuildSessionFactory(sr);
		Session hibernet_session=sf.openSeeion();
		Quary q=hibernet_session.CreateQuary("select student_id form Student_signup where password=: pass");
		q.setParameter("pass",sts_password);
		long id=(Long)q.uniqueResult();
		Student_signup std_obj =hibernet_session.get(Student_signup.class,id);
		ArrayList<Result_servise> result_list =std_obj.result_list;
				
		int total_marks= Integer.parseInt(se.getAttribute("total_marks"));
		int marks_obtain= Integer.parseInt(se.getAttribute("marks_obtain"));
		String test=(String) se.getAttribute("test_name");
		Result_servise reslt=new Result_servise ();
		result.test_name=test;
		rsult.total_marks=total_marks;
		result.marks_obtain=marks_obtain;
		result_list.add(result);
		Transection tx=hibernets_ession.biginTransection();
		hibernet_session.save(std_obj);
		tx.comit();
		res.sendRedirect("display.jsp");
	}

}
