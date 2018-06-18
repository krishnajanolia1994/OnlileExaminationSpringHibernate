package com.krishna;
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


@Entity
@Chachable
@Chache(Usage=ChacheConcarancyStratagy.READ_WRITE)
public class Add_test extends HttpServlet
{
	@Id
	int test_id
	String test_name;
	int number_question;
	int num_hour ;
	int num_min;
	@ManyToOne
	Add_subject subject;
	@OneToMany
	ArrayList<Add_Qustion_Servlet> question_list =new ArrayList<Add_Qustion_Servlet>();
    
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
	{
		
		HttpSession se=req.getSession();
		String test_name=req.getParameter("test_name");
		int number_question =Integer.parseInt(req.getParameter("Number_question"));
		int num_hour=Integer.parseInt(req.getParameter("hours"));
		int num_min=Integer.parseInt(req.getParameter("minute"));
		String Subject=(String) se.getAttribute("subject");
		pr.println(Subject);
		Configuration con=new Configuration().Configure().addAnnotatedClass(Add_subject.class).addAnnotatedClass(Add_test.class).addAnnotatedClass(Id.class);
		ServiceRegistry sr=new ServiceRegistryBuilder().addApliedSetings(con.getProperties()).BuildServiceRegistry();
		SessioFactory sf=con.BuildSessionFactory(sr);
		Session hibernet_session=sf.openSeeion();
		Long id=(Long)se.getAttribute("subject_id");
		Transaction tx=hibernet_session.biginTransection();
		Add_subject subject_obj=hibernet_session.get(Add_subject.class,id);
		ArrayList<Add_test> test_list=subject_obj.test_list;
		for(Add_test test_obj:test_list)
		{
			if(test_obj.test_name.equals(test_name))
				res.sendRedirect("add_testT.jsp");
		}
		Id id_obj=hibernet_session.get(Id.class,1000);
		Add_test new_test =new Add_test();
		 new_test.test_name=test_name();
		 new_test.number_question=number_question;
		 new_test.num_hour=num_hour;
		 new_test.num_min=num_min;
		 new_test.test_id=id_obj.test_id;
		 id_obj.test_id++;
		 test_list.add(new_test);
		 hibernet_session.save(subject_obj);
		 hibernet_session.save(id);
		tx.comit();
		se.setAtribute("test_id",new_test.test_id);
		res.sendRedirect("add_question.jsp");
		
	}

}
