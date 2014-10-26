package emn.tp.servlet;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import emn.tp.bean.jpa.UsersEntity;
import emn.tp.services.implementation.LoginService;
import emn.tp.services.interfaces.LoginServiceInterface;


/**
 * Servlet implementation class Connexion
 */
@WebServlet({"/", "/Login"})
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

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
		System.out.println("LOGIN : Forwarding to Login JSP...");
		request.getRequestDispatcher("/WEB-INF/jsp/Login.jsp").forward(request, response);
		System.out.println("LOGIN : Removing loginError session variable...");
		request.getSession().removeAttribute("loginError");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Login service (database work)
		LoginServiceInterface logService = new LoginService();
		
		// Get inputs
		System.out.println("LOGIN : Getting inputs...");
		String email = request.getParameter("mail");
		String password = request.getParameter("password");

		// If inputs are filled
		System.out.println("LOGIN : Validating fields...");
		if(logService.validateField(email, password)){
			// Try to get a user by the mail and the password written
			System.out.println("LOGIN : Getting user by mail and password...");
			UsersEntity user = logService.getUser(email, password);
			// If a user is found
			if(user != null){
				System.out.println("LOGIN : User found... redirecting...");
				request.getSession().setAttribute("UserName", user.getName());
				response.sendRedirect("Events");
			// If no user is found
			}else{
				System.out.println("LOGIN : Bad mail or password...");
				request.getSession().setAttribute("loginError", "Identifiant et/ou mot de passe incorrect(s).");
				response.sendRedirect("Login");
			}
		// If not all inputs are filled
		}else{
			System.out.println("LOGIN : Inputs not filled...");
			request.getSession().setAttribute("loginError", "Tous les champs doivent être renseignés.");
			response.sendRedirect("Login");
		}

	}

}
