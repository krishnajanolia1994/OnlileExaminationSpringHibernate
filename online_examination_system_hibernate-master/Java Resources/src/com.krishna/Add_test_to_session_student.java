package com.krishna;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Add_test_to_session_student extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String test_id=req.getParameter("test_id");
		HttpSession session=req.getSession();
		Long id=Long.ParseLong(test_id);
		session.setAttribute("test_id",id);
		res.sendRedirect("create_question_paper.jsp");
	}

}
