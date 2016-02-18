package WebApp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import persons.Person;
import services.Service;
/**
 * An class  to get and send all the data required to the admin page
 * @author emad
 *
 */
public class AdminServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession httpsession = req.getSession();
		Person person = (Person) httpsession.getAttribute("person");
		if (person == null) {
			req.getRequestDispatcher("/pages/login.jsp").forward(req, res);
			return;
		}

		  //creating configuration object  
		
		Configuration configuration = new Configuration().configure();
		//creating service registry object  

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
	    //creating session object  
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

		// opens a new session from the session factory
		Session session = sessionFactory.openSession();
		Transaction tx;
		session.beginTransaction();
		try {
			
			String hql = "FROM Person";
			Query query = session.createQuery(hql);

			List<Person> allPersons = (List<Person>) query.list();
			session.close();
			req.setAttribute("allPersons", allPersons);

			req.getRequestDispatcher("/pages/admin.jsp").forward(req, res);

		} catch (Exception e) {
			if (session.getTransaction() != null) {
				session.getTransaction().rollback();
				
				
			}
			throw e;
		} finally {
			session.close();
		}
	}
}