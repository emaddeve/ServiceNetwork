package WebApp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class ServiceMang extends HttpServlet {

	/**
	 * An class to delete services based on the user request
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		int id = Integer.parseInt(req.getParameter("serviceSelected"));

		// creating configuration object

		Configuration configuration = new Configuration().configure();
		// creating service registry object

		ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties()).build();
		// creating session object
		SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);

		// opens a new session from the session factory
		Session session = sessionFactory.openSession();
		// begin Transaction
		Transaction tx = session.beginTransaction();
		try {

			String hql = "delete Service S WHERE S.id =" + id;

			Query q = session.createQuery(hql);
			q.executeUpdate();
			if (!tx.wasCommitted())
				session.getTransaction().commit();
			session.close();

			res.sendRedirect("profile");
		} catch (Exception e) {
			this.terminate(req, res);
			return;
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
	 */
	protected void terminate(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.sendRedirect("profile");
	}
}
