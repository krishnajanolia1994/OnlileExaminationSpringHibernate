package com.krishna;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Add_test_to_session_student extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String test_name=req.getParameter("test_name_student");
		HttpSession session=req.getSession();
		session.setAttribute("test_table",test_name);
		res.sendRedirect("create_question_paper.jsp");
	}

}
