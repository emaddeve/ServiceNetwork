package WebApp;

import java.util.*;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import services.Service;
import persons.Person;
/**
 * An class  to get and send all the data required to the profile page
 * @author emad
 *
 */
public class Profile extends HttpServlet {
	// Gestion des sessions Hibernate =========================================================
	private SessionFactory sessionFactory;

	public void init() throws ServletException {
		Configuration configuration = null;
		ServiceRegistry registry = null;
		try {
			configuration = new Configuration().configure();
			registry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
		} catch (Throwable e) {
			StandardServiceRegistryBuilder.destroy(registry);
			throw e;
		}
		this.sessionFactory = configuration.buildSessionFactory(registry);
	}

	public void destroy() {
		if (this.sessionFactory != null) {
			this.sessionFactory.close();
		}
	}

	Transaction tx;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//Retrieve the parameters
		HttpSession httpsession = req.getSession();
		Person person = (Person) httpsession.getAttribute("person");
		//check if session is null if so send the user to login page
		if (person == null) {
			req.getRequestDispatcher("/pages/login.jsp").forward(req, res);

			return;
		}

		Session hibernateSession = sessionFactory.openSession();
		tx = hibernateSession.beginTransaction();

		// deleting services that their end Date came
		try {
			String hql2 = "DELETE Service S WHERE S.endDate < NOW()";
			Query query2 = hibernateSession.createQuery(hql2);
			query2.executeUpdate();
			if (!tx.wasCommitted())
				tx.commit();

			// Retrieve all services of the unique person
			int pid = person.getId();
			String hql = "FROM Service S WHERE S.pid =" + pid;

			Query query = hibernateSession.createQuery(hql);

			List<Service> allServices = (List<Service>) query.list();

			List<Service> servicesDemandes = new ArrayList<Service>();
			List<Service> servicesOffres = new ArrayList<Service>();
			 //split the service in Demands services and Offers Services
			for (Service s : allServices) {
				if (s.getType() == 1)
					servicesDemandes.add(s);
				else
					servicesOffres.add(s);
			}
			//commit if not commited yet
			if (!tx.wasCommitted())
				tx.commit();
			req.setAttribute("listServicesDemandes", servicesDemandes);
			req.setAttribute("listServicesOffers", servicesOffres);
			// check if the person have any services if not redirect him to the
			// login page
			if (servicesDemandes.isEmpty() && servicesOffres.isEmpty()) {
				String message = "it's look like you don't have any services yet";
				req.setAttribute("msg", message);
				req.setAttribute("person", person);
				req.getRequestDispatcher("addService").forward(req, res);
			} else
				req.getRequestDispatcher("/pages/profile.jsp").forward(req, res);

		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new ServletException(e);
		} finally {
			hibernateSession.close();
		}
	}

}
