package WebApp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import persons.*;
/**
 * An class to handle the request from the admin page 
 * delete and create new users.
 * @author emad
 *
 */
public class AdminMang extends HttpServlet {
	
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
	private static final long serialVersionUID = 1L;
	/**
	 * respnsable of creating new users account  based on the admin request
	 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		// Retrieve attribute and parameters
		String email = req.getParameter("email");
		String firstname = req.getParameter("firstname");
		String name = req.getParameter("name");
		String pass = req.getParameter("pass");
		
		Session hibernateSession = sessionFactory.openSession();
		tx = hibernateSession.beginTransaction();

		// Creates an instance of Person from the given data
		Person person = new Person(name, firstname, email, pass);

		try {
			//persist Person to the DB
			hibernateSession.persist(person);
			//commit if it's not already commited 
			if(!tx.wasCommitted())
			tx.commit();

			res.sendRedirect("admin");
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new ServletException(e);
		} finally {
			hibernateSession.close();
		}

	}
	
	
	/**
	 * respnsable of deleting users account from the DB based on the admin request
	 */

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		int id = Integer.parseInt(req.getParameter("personSelected"));

		Person person = new Person();

		person.setId(id);
		Session hibernateSession = sessionFactory.openSession();
		tx = hibernateSession.beginTransaction();
		try {
			String hql = "delete Person P WHERE P.id =" + id;
			String hql2 = "delete Service S WHERE S.pid =" + id;

			hibernateSession.createQuery(hql2).executeUpdate();

			if (!tx.wasCommitted())
				hibernateSession.getTransaction().commit();

			Transaction tx2 = hibernateSession.beginTransaction();
			hibernateSession.createQuery(hql).executeUpdate();

			if (!tx2.wasCommitted())
				hibernateSession.getTransaction().commit();
			res.sendRedirect("admin");
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new ServletException(e);
		} finally {
			hibernateSession.close();
		}

	}
	/**
     * Terminates the response of this servlet by displaying table of contents and a message.
     * @param request The request for this call
     * @param response The response for this call
     * @param message The message to be forwarded to table of contents
     * */
	protected void terminate(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.sendRedirect("admin");
	}

}
