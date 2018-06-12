package com.krishna;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class Add_Qustion_Servlet extends HttpServlet
{
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		PrintWriter pr=res.getWriter();
		String Question=req.getParameter("question");
		String op1=req.getParameter("option_1");
		String op2=req.getParameter("option_2");
		String op3=req.getParameter("option_3");
		String op4=req.getParameter("option_4");
		int Answer=Integer.parseInt(req.getParameter("Answer"));
		HttpSession se=req.getSession();
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://"
					+ "localhost:3306/online_examination_syatem","root","");
			String Subject=(String) se.getAttribute("subject");
			String test=(String) se.getAttribute("test");
			String email=(String) se.getAttribute("email");
			String table=email+Subject+test;
			pr.println(table);
					
			PreparedStatement pst=(PreparedStatement) con.prepareStatement("insert into "+table+"( Question , "
					+ "OptionI , OptionII, OptionIII , OptionIV, Answer ) values(? , ? , ? , ? , ? , ?)");
			pst.setString(1,Question);
			pst.setString(2,op1);
			pst.setString(3,op2);
			pst.setString(4,op3);
			pst.setString(5,op4);
			pst.setInt(6,Answer);
			pst.executeUpdate();
			pst.close();
			add_all_Question ad=new add_all_Question();
			ad.get(req, res);

		} 
		catch (ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		


		
	}

}
