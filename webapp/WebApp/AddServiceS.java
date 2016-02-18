package WebApp;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
import com.google.gson.Gson;

//import com.google.common.util.concurrent.Service;

import services.Service;
import persons.Person;
/**
 * An class for adding a Service to the database using Hibernate,
 * and getting all the services exist in the DB and and converted to a json file
 * using  gson-2.2 library then passing this file to addService.jsp 
 * to have an autocomplete functionality.
 * 
 * @author emad
 */
public class AddServiceS extends HttpServlet {
	
	// Gestion des sessions Hibernate =========================================================

	private SessionFactory sessionFactory;
	Transaction tx;
	private static final long serialVersionUID = 1L;

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

	

	/**list of Services that will be retrieved from the DB */
	ArrayList<String> listServices = new ArrayList<String>();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		HttpSession session = req.getSession();
		Person person = (Person) session.getAttribute("person");
		//check if the session is empty if so send the user to login page
		if (person == null) {
			req.getRequestDispatcher("/pages/login.jsp").forward(req, res);
			return;
			//check if the person is an admin or a user by his role
		} else if (person.getRole() == 1) {
			req.setAttribute("msg", "you logged in with admin account please login with user account to add services");
			req.setAttribute("person", person);
			req.getRequestDispatcher("admin").forward(req, res);
		}
		/**
		 * create a Gson object to convert the String of the services names to
		 *  a json file;
		 */
		Gson gson = new Gson();
		listServices = sendListServices();
		String json = gson.toJson(listServices);
		/**
		 * send the person and json attribute to the addService page to have the
		 *  AUTOCOMPLETE functionality
		 */
		
		req.setAttribute("person", person);
		req.setAttribute("json", json);
		req.getRequestDispatcher("/pages/addService.jsp").forward(req, res);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession httpsession = req.getSession();
		// Retrieve attribute and parameters
		Person person = (Person) httpsession.getAttribute("person");
		int pid = person.getId();
		String name = req.getParameter("name");
		String des = req.getParameter("description");
		int type = Integer.parseInt(req.getParameter("type"));
		String from = req.getParameter("datepicker");
		String to = req.getParameter("datepicker2");
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy", Locale.FRANCE);
		Date beginDate = null;
		Date endDate = null;

		try {
			beginDate = df.parse(from);

			endDate = df.parse(to);
		} catch (Exception e) {
		}

		// opens a new session from the session factory
		Session hibernateSession = sessionFactory.openSession();
		tx = hibernateSession.beginTransaction();
		 // Creates an instance of Service from the given data
		Service service = new Service(name, des, type, pid, beginDate, endDate);
		try {
		//insert the data in the DB
			hibernateSession.persist(service);
		//commit if it's not already commited 
			if (!tx.wasCommitted())
				tx.commit();
			res.sendRedirect("profile");

		} catch (Exception e)

		{
			
			throw new ServletException(e);
		} finally

		{//close the session
			hibernateSession.close();
		}
	}

	/**
	 * Terminates the response of this servlet by displaying table of contents
	 * and a message.
	 * 
	 * @param request
	 *            The request for this call
	 * @param response
	 *            The response for this call
	 * @param message
	 *            The message to be forwarded to table of contents
	 */
	protected void terminate(HttpServletRequest req, HttpServletResponse res, String message)
			throws ServletException, IOException {
		res.sendRedirect(res.encodeRedirectURL(req.getContextPath() + "/home.jsp?message=" + message));
	}

	/**
	 * Retrieve all services name from the database and stock it in ArrayList
	 * @return list
	 */
	protected ArrayList<String> sendListServices() {
		// opens a new session from the session factory
		Session session = sessionFactory.openSession();
		 tx = session.beginTransaction();
		Query q = session.createQuery("SELECT S.name FROM Service S");
		ArrayList<String> list = (ArrayList<String>) q.list();
		session.close();
		return list;

	}

}
