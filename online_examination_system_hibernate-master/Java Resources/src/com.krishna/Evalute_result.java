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

public class Evalute_result extends HttpServlet 
{
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		HttpSession se=req.getSession();
		int num_que_op=(Integer) se.getAttribute("avable_question_option");
		String Subject=(String) se.getAttribute("subject_table");
		String test=(String) se.getAttribute("test_table");
		String email=(String) se.getAttribute("Email_Eddress_Table");
		String table=email+Subject+test;
		try 
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con1=null;
			
			try {
				con1 = (Connection) DriverManager.getConnection("jdbc:mysql:"
						+ "//localhost:3306/online_examination_syatem","root","");
				Statement st=(Statement) con1.createStatement();
				ResultSet rs=st.executeQuery("select OptionI , OptionII, OptionIII , OptionIV , Answer  from  "+table);
				int i=0;
				PrintWriter pr=res.getWriter();
				pr.println(email);
				pr.println(Subject);
				pr.println(test);
				String ans_obtain;
				int right_index;
				String Correct_answer;
				int total_marks=0;
				int marks_obtain=0;
				while(rs.next())
				{
					ans_obtain= req.getParameter(i+"Question");
					right_index=rs.getInt("Answer");
					
					Correct_answer=rs.getString(right_index);
					pr.println(ans_obtain);
					pr.println(right_index);
					pr.println(Correct_answer);
					if(ans_obtain!=null)
						if(ans_obtain.equals(Correct_answer))
						{
							marks_obtain++;
						}
					total_marks++;
					i+=5;
					
					
				}
				se.setAttribute("total_marks", total_marks);
				se.setAttribute("marks_obtain", marks_obtain);
				Result_servise servise=new Result_servise();
				servise.servise(req, res);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		
		
	}

}
