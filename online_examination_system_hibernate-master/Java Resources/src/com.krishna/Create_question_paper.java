package com.krishna;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;


public class Create_question_paper extends HttpServlet {
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		HttpSession se=req.getSession();
		String Subject=(String) se.getAttribute("subject_table");
		String test=(String) se.getAttribute("test_table");
		String Table=Subject+test;
		int i=0;
		Connection con1=null;
		try {
			con1 = (Connection) DriverManager.getConnection("jdbc:mysql:"
					+ "//localhost:3306/online_examination_syatem","root","");
			Statement st=(Statement) con1.createStatement();
			ResultSet rs=st.executeQuery("select Question , OptionI ,OptionII ,OptionIII ,OptionIV   from  "
			+se.getAttribute("Email_Eddress_Table")+Table);
			while(rs.next())
			{
				
				se.setAttribute(i+"Question", rs.getString("Question"));
				se.setAttribute(i+1+"OptionI", rs.getString("OptionI"));
				se.setAttribute(i+2+"OptionII", rs.getString("OptionII"));
				se.setAttribute(i+3+"OptionIII", rs.getString("OptionIII"));
				se.setAttribute(i+4+"OptionIV", rs.getString("OptionIV"));
				i+=5;
			}
			Table=se.getAttribute("Email_Eddress_Table")+Subject;
			PrintWriter pr=res.getWriter();
			pr.println(Table);
			rs=st.executeQuery("select hour , minute from  "+Table+"  where Test_Name =  '"+test+"'");
			rs.next();
			int hour= rs.getInt("hour");
			int minut=rs.getInt("minute");
			int time_in_sec=hour*3600 + minut*60;
			se.setAttribute("time_in_sec", time_in_sec);
			se.setAttribute("avable_question_option", i);
			res.sendRedirect("start_test.jsp");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
	}

}
