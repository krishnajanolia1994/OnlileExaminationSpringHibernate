package com.krishna;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class Create_Email extends HttpServlet {
       
    
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		HttpSession se=request.getSession();
		Configuration con=new Configuration().Configure().addAnnotatedClass(Teacher_signup.class);
		ServiceRegistry sr=new ServiceRegistryBuilder().addApliedSetings(con.getProperties()).BuildServiceRegistry();
		SessioFactory sf=con.BuildSessionFactory(sr);
		Session hibernet_session=sf.openSeeion();
		Quary q=hibernet_session.createQuary("select email_address form Teacher_signup");
		ArryaList<String> email_list=(String)q.list();
		int i=0;
		for(String str : email_list)
		{
			se.setAttribute(i+"Email_of_teacher", str);
			i++;
		}
		
		se.setAttribute("avable_Email_number", i);
		response.sendRedirect("available_teacher_email.jsp");
			
		
	}

}
