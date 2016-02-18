package WebApp;

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import org.hibernate.service.ServiceRegistry;

import persons.*;

import services.Service;
/**
 * An class responsable of signing in for the users 
 * @author emad
 *
 */
public class SigninS extends HttpServlet {
	
	// Gestion des sessions Hibernate =========================================================
	private SessionFactory sessionFactory;
	Person person;

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

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.getRequestDispatcher("/pages/login.jsp").forward(req, res);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setContentType("text/html;charset=UTF-8");
		// Retrieve attribute and parameters
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			HttpSession httpsession = req.getSession();
			/*check if the user has enter a valid information by the method isValid(). 
			if so check the role if ==1 send him to the admin page if ==0 send him to the profile page
			else send him to login page with a error message
			*/
			if (isValid(email, password)) {
				httpsession.setAttribute("person", person);

				if (person.getRole() == 0) {

					resp.sendRedirect("profile");
				} else {

					resp.sendRedirect("admin");
				}
			} else {
				req.setAttribute("msg", "invalid email or password");
				req.getRequestDispatcher("/pages/login.jsp").forward(req, resp);
			}

		} catch (Exception e) {

			this.terminate(req, resp, " (" + e + ").");
			e.printStackTrace();

			return;

		}
	}
/**
 * An method for checking the validation of the user login information
 * @param email the email of the user
 * @param pass the password of the user
 * @return return true if the user entered valid information
 */
	public boolean isValid(String email, String pass) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		String hql = "FROM Person P WHERE P.email = ? and P.password= ?";
		Query q = session.createQuery(hql);
		q.setString(0, email);
		q.setString(1, pass);
		person = (Person) q.uniqueResult();
		if (person != null)
			return true;
		else
			return false;

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

}
