package com.krishna;a

import java.io.IOException;
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
public class Add_subject extends HttpServlet
{
	String subject;
	@ManyToOne
	Teacher_signup ts=new Teacher_signup ();
	@OneToMany(MappedBy="")
	ArrayList<Add_test> test_list =new ArrayList<>();

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		HttpSession se=(HttpSession) req.getSession();
		Configuration con=new Configuration().Configure().addAnnotatedClass(Teacher_signup.class);
		ServiceRegistry sr=new ServiceRegistryBuilder().addApliedSetings(con.getProperties()).BuildServiceRegistry();
		SessioFactory sf=con.BuildSessionFactory(sr);
		Session hibernet_session=sf.openSeeion();
		Quary q=hibernet_session.createQuary("select teacher_id form Teacher_signup where password = : psaa");
		HttpSession session=req.getSession();
		String pass=(String)session.getAtribute("teacher_password");
		q.setParameter("pass",pass);
		Integer id=(Integer) q.uniqueResult();
		Transection tx=hibernet_session.biginTransection();
		Teacher_signup ts=hibernet_session.get(Teacher_signup,id);
		ArraList<Add_subject> suject_list=ts.subject_list;
		String subject=req.getParameter("subject_name");
		if(subject_list.contains(subject_name))
		{
			se.setAttribute("subject_exist_error","given subject is Alraidy Exist ");
			res.sendRedirect("add_subjectT.jsp");
		}
		
		else
		{
			subject_list.add(subject_name);
			session.setAttribute("subject",subject_name);
			
		}
		tx.comit();
		res.sendRedirect("add_testT.jsp");
			
		
		
	}

}
