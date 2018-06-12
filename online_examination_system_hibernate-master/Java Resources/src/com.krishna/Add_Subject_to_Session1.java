package com.krishna;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

 
public class Add_Subject_to_Session1 extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		String subject_name=req.getParameter("subject_name");
		HttpSession session=req.getSession();
		session.setAttribute("subject",subject_name);
		Cookie cookie[]=req.getCookies();
		cookie=null;
		res.sendRedirect("create_test_koocki.jsp");
	}

}
