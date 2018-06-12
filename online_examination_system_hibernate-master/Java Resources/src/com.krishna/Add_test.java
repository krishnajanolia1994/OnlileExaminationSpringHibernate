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
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;


public class Add_test extends HttpServlet {
       
   

       
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		Connection con=null;
		Statement st=null;
		HttpSession se=req.getSession();
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/online_examination_syatem","root","");
		    st =(Statement) con.createStatement();
			String test_name=req.getParameter("test_name");
			int Number_question =Integer.parseInt(req.getParameter("Number_question"));
			int num_hour=Integer.parseInt(req.getParameter("hours"));
			int num_min=Integer.parseInt(req.getParameter("minute"));
			PrintWriter pr=res.getWriter();
			String Subject=(String) se.getAttribute("subject");
			pr.println(Subject);
			ResultSet rs =st.executeQuery("select Test_Name from  "+se.getAttribute("email")+Subject+"  where Test_Name = '"+test_name+"'");
			if(rs.next())
			{
				se.setAttribute("test_exist_error","given test is Alraidy Exist ");
				res.sendRedirect("add_testT.jsp");
			}
			else
			{
				PreparedStatement pst=(PreparedStatement) con.prepareStatement("insert into "
			+se.getAttribute("email")+Subject+ "( Test_Name ,"
						+ " Number_Question , hour , minute) values(? , ? , ? , ?)");
				pst.setString(1,test_name);
				pst.setInt(2, Number_question);
				pst.setInt(3, num_hour );
				pst.setInt(4, num_min);
				pst.executeUpdate();
				String str="CREATE TABLE  "+se.getAttribute("email")+Subject+test_name+"(  Que_id int AUTO_INCREMENT , "
						+ "Question varchar(80),OptionI varchar(80),OptionII varchar(80)"
						+ ",OptionIII varchar(80),OptionIV varchar(80),Answer int,PRIMARY KEY(Que_id))";
				st=(Statement) con.createStatement();
				st.execute(str);
				se.setAttribute("test",test_name);
				
				if(se.getAttribute("number_of_question")==null)
					se.setAttribute("number_of_question", Number_question);
				pst.close();

				add_all_Question ad=new add_all_Question();
				ad.get(req, res);
			}
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				st.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
