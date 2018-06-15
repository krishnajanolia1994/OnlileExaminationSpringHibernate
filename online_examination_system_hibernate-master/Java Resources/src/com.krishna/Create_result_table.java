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


public class Create_result_table extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		HttpSession se=req.getSession();
		String password=(String) se.getAttribute("student_password");
		Configuration con=new Configuration().Configure().addAnnotatedClass(Teacher_signup.class).addAnnotatedClass(Add_subject.class);
		ServiceRegistry sr=new ServiceRegistryBuilder().addApliedSetings(con.getProperties()).BuildServiceRegistry();
		SessioFactory sf=con.BuildSessionFactory(sr);
		Session hibernet_session=sf.openSeeion();
		Quary q=hibernet_session.createQquary("select student_id from Student_signup  where password =:pass");
		q.setParameter("pass",password);
		long id=q.uniqueResult();
		Student_signup  std_obj=hibernet_session.get(Student_signup .class,id);
		ArrayList<Result_servise> result_list =std_obj.result_list;
		int i=0;
		for(Result_servise result:result_list)
		{
			se.setAttribute(i+"test_name_tab",result.test_name);
			se.setAttribute(i+"max_mark", result.total_marks);
			se.setAttribute(i+"marks_obtain", result.marks_obtain);

			i++;
		}
		
		se.setAttribute("avable_test_number", i);		
		res.sendRedirect("final_result_table.jsp");
		
	}

}
