package WebApp;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

//import com.google.common.util.concurrent.Service;

import services.Service;
import persons.Person;
/**
 * An class permit of creating new accounts 
 * @author emad
 *
 */
public class SignupS extends HttpServlet {
	
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


	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("/pages/signUp.jsp").forward(req, res);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// Retrieve attribute and parameters
		String email = req.getParameter("email");
		String firstname = req.getParameter("firstname");
		String name = req.getParameter("name");
		String pass = req.getParameter("pass");
		String msg;

		Session hibernateSession = sessionFactory.openSession();
		Transaction tx = hibernateSession.beginTransaction();

		Person person = new Person(name, firstname, email, pass);

		try {
			//check if the data retrieved not null 
			if (firstname == "" || name == "" || pass == "") {
				msg = "error invalid entry please try again";
				req.setAttribute("msg", msg);
				req.getRequestDispatcher("signUp").forward(req, res);
				//check if the person not null and if the email exist already in the DB by the method exists()
			} else if (person != null && !exists(email)) {

				// Insert person into DB
				hibernateSession.persist(person);
				if (!tx.wasCommitted())
					tx.commit();

				HttpSession httpsession = req.getSession();

				httpsession.setAttribute("person", person);
				res.sendRedirect("profile");

			} else {
				msg = "this email already exist";
				req.setAttribute("msg", msg);
				req.getRequestDispatcher("/pages/signUp.jsp").forward(req, res);
			}
		} catch (

		Exception e)

		{
			if (tx != null) {
				tx.rollback();
			}
			throw new ServletException(e);
		} finally

		{
			hibernateSession.close();
		}

	}
/**
 * method for checking if the email that was entered exist in the DB 
 * @param email email
 * @return true if true if the email exist in the DB
 */
	public Boolean exists(String email) {
		Session hibernateSession = sessionFactory.openSession();
		Transaction tx = hibernateSession.beginTransaction();
		Query query = hibernateSession.createQuery("FROM Person P WHERE P.email= :email");
		query.setString("email", email);
		return (query.uniqueResult() != null);
	}


}
