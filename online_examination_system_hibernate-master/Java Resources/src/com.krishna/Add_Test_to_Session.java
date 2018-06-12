package com.krishna;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Add_Test_to_Session extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
	
		String test_name=req.getParameter("test_name");
		HttpSession session=req.getSession();
		session.setAttribute("test",test_name);
		Cookie cookie[]=req.getCookies();
		cookie=null;
		res.sendRedirect("add_question.jsp");
	}

}
