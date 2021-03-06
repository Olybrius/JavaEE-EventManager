package emn.tp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import emn.tp.bean.jpa.UsersEntity;
import emn.tp.services.implementation.UsersService;
import emn.tp.services.interfaces.UsersServiceInterface;
import emn.tp.tools.Tools;


/**
 * Servlet relatif � l'authentification de l'utilisateur.
 * @author Joris & Killian
 */
@WebServlet({"/", "/Login"})
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(Login.class);

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		logger.debug("LOGIN : Forwarding to Login JSP...");
		request.getRequestDispatcher("/WEB-INF/jsp/Login.jsp").forward(request, response);
		logger.debug("LOGIN : Invalidating session...");
		session.invalidate();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		// Login service (database work)
		UsersServiceInterface logService = new UsersService();
		
		// Get inputs
		logger.debug("LOGIN : Getting inputs...");
		String email = request.getParameter("mail");
		String password = request.getParameter("password");

		// If inputs are filled
		logger.debug("LOGIN : Validating fields...");
		if(Tools.validateFieldLogin(email, password)){
			// Try to get a user by the mail and the password written
			logger.debug("LOGIN : Getting user by mail and password...");
			UsersEntity user = logService.getUser(email, password);
			// If a user is found
			if(user != null){
				logger.debug("LOGIN : User found... redirecting...");
				session.setAttribute("user", user);
				response.sendRedirect("MyEvents");
			// If no user is found
			}else{
				logger.debug("LOGIN : Bad mail or password...");
				session.setAttribute("loginError", "Identifiant et/ou mot de passe incorrect(s).");
				response.sendRedirect("Login");
			}
		// If not all inputs are filled
		}else{
			logger.debug("LOGIN : Inputs not filled...");
			session.setAttribute("loginError", "Tous les champs doivent �tre renseign�s.");
			response.sendRedirect("Login");
		}

	}

}
