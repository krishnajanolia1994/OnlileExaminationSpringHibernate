package com.krishna;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;


public class Teacher_signup extends HttpServlet {

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		Signup_Servicess service=new Signup_Servicess();

		String login_table="teacher_login_table";
		int redirect;
		try {
			redirect=service.sevicess(req,res,login_table);
			if(redirect==1)
			{
				String email=req.getParameter("Email_Address");
				String temp1,temp2;
				int index=email.indexOf("@");
				temp1=email.substring(0, index);
				temp2=email.substring(index+1, email.length()-1);
				email=temp1+temp2;
				
				String table=email+"_subject_main_table";
				String str="CREATE TABLE  "+table+"(  Sub_id int AUTO_INCREMENT , "
						+ "Subject_Name varchar(20),PRIMARY KEY(Sub_id))";
				Class.forName("com.mysql.jdbc.Driver");
				Connection con=null;
			    con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/online_examination_syatem", "root", "");
			    Statement st=(Statement) con.createStatement();
			    st.execute(str);
			    con.close();
			    HttpSession se=req.getSession();
			    se.setAttribute("email", email);
				res.sendRedirect("display_teacher.jsp");
				
			}
			else
			{
				res.sendRedirect("sign-up-login-form/index.html");
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
