package com.krishna;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class add_all_Question {
	public void get(HttpServletRequest req, HttpServletResponse res) throws IOException
	{
		HttpSession se=req.getSession();
		int num=(Integer) se.getAttribute("number_of_question");
		if(num>0)
		{
			num--;
			se.setAttribute("number_of_question", num);
			res.sendRedirect("add_question.jsp");
		}
		else
		{
			se.removeAttribute("number_of_question");
			res.sendRedirect("display_teacher.jsp");
		}
	}

}
