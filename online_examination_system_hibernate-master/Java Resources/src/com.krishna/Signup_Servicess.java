package com.krishna;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

public class Signup_Servicess {


	public int sevicess(HttpServletRequest req, HttpServletResponse res, String Table_name)
			throws ClassNotFoundException, SQLException, IOException
	{
		Class.forName("com.mysql.jdbc.Driver");

		int k=1;
		PrintWriter pr=res.getWriter();
		String First_name=req.getParameter("First_name");
		String Last_name=req.getParameter("Last_Name");
		String email_adress=req.getParameter("Email_Address");
		String password=req.getParameter("password");
		String Quary1="select Email_Address From  "+ Table_name +" Where Email_Address = "+ "'"+email_adress+"'";
		String Quary2="select Password From  "+ Table_name +" Where Password  =' "+password+"'";
		String Quary3="insert into "+Table_name+"( Last_Name,Email_Address,Password,First_Name) values(?,?,?,?)";
		String Erroe="";
		Connection con=(Connection) DriverManager.getConnection("jdbc:mysql:"
				+ "//localhost:3306/online_examination_syatem", "root","");
		Statement st=(Statement) con.createStatement();
		ResultSet rs=st.executeQuery(Quary1);
		HttpSession session= req.getSession();

		if(rs.next())
		{
			session.setAttribute("Error", "ivalid email Adress");
			pr.println("hi");
			k=0;
		}
		else
		{
			rs=st.executeQuery(Quary2);
			if(rs.next())
			{
				pr.println("hi");

				session.setAttribute("Error", "invalid Password");
				k=0;
			}
			else
			{
				PreparedStatement pst=(PreparedStatement) con.prepareStatement(Quary3);
				pst.setString(1,Last_name);
				pst.setString(2,email_adress);
				pst.setString(3,password);
				pst.setString(4,First_name);
				if(k==1)
					pst.executeUpdate();

				pst.close();
				session.setAttribute("Error","");
				
			}
			st.close();
			rs.close();
		}

		return k;
	}

}
