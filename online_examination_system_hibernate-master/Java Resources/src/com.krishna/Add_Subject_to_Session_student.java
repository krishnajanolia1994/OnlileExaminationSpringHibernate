package com.krishna;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class Add_test_to_session_student extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String subject_id=req.getParameter("subject_id");
		HttpSession session=req.getSession();
		Long sub_id=Long.paseLong(subject_id);
		session.setAttribute("subject_id",sub_id);
		res.sendRedirect("create_test_for_student.jsp");
	}

}
