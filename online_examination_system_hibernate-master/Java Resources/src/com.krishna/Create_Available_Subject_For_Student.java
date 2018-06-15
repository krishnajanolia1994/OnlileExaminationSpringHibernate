package com.krishna;

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
import com.mysql.jdbc.Statement;


public class Create_Available_Subject_For_Student extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		HttpSession se=req.getSession() ;
		String teacher_email=(String)se.getAttribute("teacher_email");
		Configuration con=new Configuration().Configure().addAnnotatedClass(Add_subject.class).addAnnotatedClass(Add_test.class).addAnnotatedClass(Id.class);
		ServiceRegistry sr=new ServiceRegistryBuilder().addApliedSetings(con.getProperties()).BuildServiceRegistry();
		SessioFactory sf=con.BuildSessionFactory(sr);
		Session hibernet_session=sf.openSeeion();
		Quary q=hibernet_session.createQuary("select teacher_id from Teacher_signup where teacher_email = : add");
		q.setParameter("add"teacher_email);
		Long id=q.uniqueResult();
		Teacher_signup teacher_signup=hibernet_session.get(Teacher_signup.class,id);
		ArrayList<Add_subject> subject_list =teacher_signpu.subject_list;
		int i=0;
		for(Add_subject subject_obj : subject_list)
		{
			se.setAttribute(i+"Avalable_Subject", subject_obj);

			i++;
		}
		
		se.setAttribute("avable_subject_number", i);
		res.sendRedirect("available_subject_for_student.jsp");
			
		
	}

}
