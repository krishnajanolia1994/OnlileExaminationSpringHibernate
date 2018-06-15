package com.krishna;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

@Entity
@Chachable
@Chache(Usage=ChacheConcarancyStratagy.READ_WRITE)
public class Student_signup extends HttpServlet 
{
	@Id
	int student_id;
	String first_name;
	String last_Name;
	String email_address;
	String password;
	@ManyToOne(MappedBy="stud")
	ArrayList<Result_servise> result_list=new ArryaList<Result_servise>();
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		String fname=req.getParameter("First_name");
		String lname=req.getParameter("Last_Name");
		String add=req.getParameter("Email_Address");
		String pass=req.getParameter("password");
		HttpSession se=req.getSession();
		String login_table="student_login_table";
		Configuration con=new Configuration().Configure().addAnnotatedClass(Teacher_signup.class).addAnnotatedClass(Add_subject.class);
		ServiceRegistry sr=new ServiceRegistryBuilder().addApliedSetings(con.getProperties()).BuildServiceRegistry();
		SessioFactory sf=con.BuildSessionFactory(sr);
		Session hibernet_session=sf.openSeeion();
		Quary q=hibernet_session.createQuary("select student_id form student_signup where password = : psaa or email_address= :add");
		q.setParameter("pass",pass);
		q.setParameter("add",add);
		Integer id=q.uniqueResult();
		if(id!=null)
		{
			res.sendRedirect("index1.jsp");
		}
		else
		{
			Student_signup new_sttuden=new Student_signup ();
			new_sttuden.first_name=fname;
			new_sttuden.last_name=lname;
			new_sttuden.email_address=add;
			new_sttuden.password=pass;
			Transection tx=hibernet_session.biginTransection();
			hibernet_session.save(new_student);
			tx.comit();
			se.setAttribute("student_password", pass);
			res.sendRedirect("display.jsp");
			
		}
		
		
		
	}

}
