package WebApp;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Hashtable;
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

public class ExchangeService extends HttpServlet {
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
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		HttpSession httpsession = req.getSession();

		// If the user is not connected, he is redirected to the login page
		Person person = (Person) httpsession.getAttribute("person");
		if (person == null) {
			res.sendRedirect(req.getContextPath() + "/logIn");
			return;
		}

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession httpsession = req.getSession();

		Person person = (Person) httpsession.getAttribute("person");
		Session hibernateSession = sessionFactory.openSession();
		tx = hibernateSession.beginTransaction();

		Service service = null;

		int id = Integer.parseInt(req.getParameter("serviceSelected"));

		String hql = "FROM Service S WHERE S.id = " + id;
		Query query = hibernateSession.createQuery(hql);
		service = (Service) query.uniqueResult();

		Query query2 = hibernateSession.createQuery("FROM Person P WHERE P.role ="+0);
		Collection<Person> persons = (Collection<Person>) query2.list();
		
		hibernateSession.getTransaction().commit();
		Hashtable<Person, List> listServicesDemands = new Hashtable<Person, List>();
		Hashtable<Person, List> listServicesOffers = new Hashtable<Person, List>();

		// We retrieve every services of a person
		for (Person personUnique : persons) {
			String hql3 = "FROM Service S WHERE S.pid= " + personUnique.getId();
			Query query3 = hibernateSession.createQuery(hql3);
			List<Service> ListServices = (List<Service>) query3.list();
			// List<Service> ListPsa = new
			// DBHandler().SQLPersonServiceDB.retrieveAllByPerson(personUnique);
			List<Service> servicesDemands = new ArrayList<Service>();
			List<Service> servicesOffers = new ArrayList<Service>();
			for (Service s : ListServices) {
				// Service serviceUnique = new
				// DBHandler().SQLServiceDB.retrieve(psa.getIdService());
				if (s.getType() == 1)
					servicesDemands.add(s);
				else
					servicesOffers.add(s);
			}

			listServicesDemands.put(personUnique, servicesDemands);
			listServicesOffers.put(personUnique, servicesOffers);
		}
		Algo algo = new Algo();
		String cycle = algo.getCyclic(listServicesOffers, service, persons, person);

		req.setAttribute("cycle", cycle);
		req.getRequestDispatcher("/pages/Cyclic.jsp").forward(req, res);
		
	}
	protected void terminate(HttpServletRequest req, HttpServletResponse res, String message)
			throws ServletException, IOException {
		res.sendRedirect(res.encodeRedirectURL(req.getContextPath() + "/home.jsp?message=" + message));
	}
	

}