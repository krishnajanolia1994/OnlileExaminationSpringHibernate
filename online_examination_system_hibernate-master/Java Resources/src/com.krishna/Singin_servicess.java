package com.krishna;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.Statement;

public class Singin_servicess 
{
	public int sevice(HttpServletRequest req, HttpServletResponse res,String table) throws ClassNotFoundException, SQLException
	{
		
		Class.forName("com.mysql.jdbc.Driver");

		int k=1;
		String email_adress=req.getParameter("Email_Address");
		String password=req.getParameter("password");
		String Quary1="select Email_Address From "+table+" Where Email_Address = "+ "'"+email_adress+"'";
		String Quary2="select Password From "+table +" Where Password =  '"+password+"'";
		String Erroe="";
		Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:"
				+ "3306/online_examination_syatem", "root","");
		Statement st=(Statement) con.createStatement();
		ResultSet rs=st.executeQuery(Quary1);
		HttpSession session= req.getSession();
		if(rs.next())
		{
			
			rs=st.executeQuery(Quary2);
			if(rs.next())
			{
				
				k=1;
			}
			else
				k=0;
		}
		else 
			k=0;
		return k;
	}
}
