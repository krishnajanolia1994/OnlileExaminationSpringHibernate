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
		String temp1,temp2;
		int index=Email_Eddress.indexOf("@");
		temp1=Email_Eddress.substring(0, index);
		temp2=Email_Eddress.substring(index+1, Email_Eddress.length()-1);
		Email_Eddress=temp1+temp2;
		HttpSession session=req.getSession();
		session.setAttribute("Email_Eddress_Table",Email_Eddress);
		res.sendRedirect("create_Avalable_subject_for_student.jsp");
	}

}
