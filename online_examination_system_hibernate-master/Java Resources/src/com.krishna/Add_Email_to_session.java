package com.krishna;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Add_Email_to_session extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String Email_Eddress=req.getParameter("Email_Eddress");
		
		HttpSession session=req.getSession();
		session.setAttribute("teachet_email",Email_Eddress);
		res.sendRedirect("create_Avalable_subject_for_student.jsp");
	}

}
