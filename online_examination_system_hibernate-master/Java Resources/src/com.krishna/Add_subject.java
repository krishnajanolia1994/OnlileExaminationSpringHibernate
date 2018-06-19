package com.krishna;a
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernet.Session;
import org.hibernet.SessionFactory;
import org.hibernet.cfg.Configuration;
import org.hibernet.Transaction;
import org.hibernet.service.ServiceRegistry;
import org.hibernet.service.ServiceRegistryBuilder;
//entity
import javax.persitence.Entity;
import javax.persitence.Id;
//maping
import javax.persitence.OneToMany;
import javax.persitence.ManyToOne;
import javax.persitence.Cacheable;

import Org.springframwork.beans.factory.annotation.Autowired;
import org.springframwork.context.ApplicationContext;
import Org.springframwork.context.annotation.AnnotationConfigApplicationContext;


@Entity
@Chachable
@Chache(Usage=ChacheConcarancyStratagy.READ_WRITE)
public class Add_subject extends HttpServlet
{
	@Id
	int subject_id;
	String subject;
	@ManyToOne
	@Autowired
	Teacher_signup ts;
	@OneToMany(MappedBy="subject")
	@Autowired
	ArrayList<Add_test> test_list;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException 
	{
		ApplicationContext factory=new AnnotationConfigApplicationContext(AppConfig.class);
		HttpSession se=(HttpSession) req.getSession();
		Configuration con=new Configuration().Configure().addAnnotatedClass(Teacher_signup.class).addAnnotatedClass(Add_subject.class).addAnnotatedClass(Id.class);
		ServiceRegistry sr=new ServiceRegistryBuilder().addApliedSetings(con.getProperties()).BuildServiceRegistry();
		SessioFactory sf=con.BuildSessionFactory(sr);
		Session hibernet_session=sf.openSeeion();
		Quary q=hibernet_session.createQuary("select teacher_id form Teacher_signup where password = : psaa")  ;
		HttpSession session=req.getSession();
		String pass=(String)session.getAtribute("teacher_password");
		q.setParameter("pass",pass);
		Integer id=(Integer) q.uniqueResult();
		Transaction tx=hibernet_session.biginTransection();
		Teacher_signup ts=hibernet_session.get(Teacher_signup,id);
		ArraList<Add_subject> suject_list=ts.subject_list;
		String subject=req.getParameter("subject_name");
		for(Add_subject obj: subject_list)
		{
			if(obj.subject.equals(subject_name))
			{
				se.setAttribute("subject_exist_error","given subject is Alraidy Exist ");
				res.sendRedirect("add_subjectT.jsp");
			}
		}
		Add_subject new_subject = factory.getBean(add_subject);
		new_subject.subject=subject_name;
		Id id=hibernet_session.get(Id.class,1000);
		long subject_id=id.subject_id;
		new_subject.subject_id=subject_id;
		id.subject_id++;
		hibernet_session.save(id);
		hibernet.session.save(ts);
		se.setAttribute("subject_id",subject_id);
		tx.comit();
		res.sendRedirect("add_testT.jsp");
			
		
		
	}

}
