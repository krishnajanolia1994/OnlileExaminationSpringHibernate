package com.krishna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernet.Session;
import org.hibernet.SessionFactory;
import org.hibernet.cfg.Configuration;
import org.hibernet.Transaction;
import org.hibernet.service.ServiceRegistry;
import org.hibernet.service.ServiceRegistryBuilder;
import javax.persitence.Cacheable;

public class Signup_Servicess {


	public int sevicess(HttpServletRequest req, HttpServletResponse res, String Table_name)
			throws ClassNotFoundException, SQLException, IOException
	{
		Configuration con=new Configuration().Configure().addAnnotatedClass(Teacher_signup.class);
		ServiceRegistry sr=new ServiceRegistryBuilder().addApliedSetings(con.getProperties()).BuildServiceRegistry();
		SessioFactory sf=con.BuildSessionFactory(sr);
		Session hibernet_session=sf.openSeeion();

		int k=1;
		PrintWriter pr=res.getWriter();
		String First_name=req.getParameter("First_name");
		String Last_name=req.getParameter("Last_Name");
		String email_adress=req.getParameter("Email_Address");
		String password=req.getParameter("password");
		Quary q=hibernet_session.createQuary("select  teacher_id from Teacher_signup where email_eddress= :address and password :pass");
		q.setParameter("address",email_adress);
		q.setParameter("pass",password);
		Integer id=(Integer) q.uniqueResult();
		HttpSession session= req.getSession();
		if(id!=null)
		{
			session.setAttribute("Error", "ivalid email Adress or password");
			k=0;
		}
		else
		{
			k=1;
			Teacher_signup ts=new Teacher_signup();
			ts.first_name= First_nam;
			ts.last_name=Last_name;
			ts.email_eddress=email_adress;
			ts.password=password;
			Transection tc=hibernet_session.biginTransection();
			hibernet_session.save(ts);
			tc.comit();
		}
		

		return k;
	}

}
