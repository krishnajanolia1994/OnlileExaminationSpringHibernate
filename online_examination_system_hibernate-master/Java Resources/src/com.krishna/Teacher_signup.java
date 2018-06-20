package com.krishna;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernet.Session;
import org.hibernet.SessionFactory;
import org.hibernet.cfg.Configuration;
import org.hibernet.Transaction;
import org.hibernet.service.ServiceRegistry;
import org.hibernet.service.ServiceRegistryBuilder;
//entity
import javax.persitence.Entity;
import javax.persitence.Id;
//maping
import javax.persitence.OneToMany;
import javax.persitence.Cacheable;


@Entity
@Chachable
@Chache(Usage=ChacheConcarancyStratagy.READ_WRITE)
public class Teacher_signup extends HttpServlet 
{
	@id
	int teacher_id;
	String first_name;
	String last_name;
	String email_eddress;
	String password;
	@OneToMany(MappedBy="ts")
	ArraList<Add_subject> subject_list=new ArrayList<>();

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		ApplicationContext factory=new AnnotationConfigApplicationContext(AppConfig.class);

		Signup_Servicess service=factory.grtBean("Signup_Servicess");

		String login_table="teacher_login_table";
		int redirect;
		
		redirect=service.sevicess(req,res,login_table);
		if(redirect==1)
		{
			String pass=req.getParameter("password");
		    HttpSession se=req.getSession();
		    se.setAttribute("teacher_password", pass);
			res.sendRedirect("display_teacher.jsp");

		}
		else
		{
			res.sendRedirect("sign-up-login-form/index.html");
		}
		
		
	}

}
