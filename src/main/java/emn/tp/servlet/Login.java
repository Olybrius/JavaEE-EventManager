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
		request.getRequestDispatcher("/WEB-INF/jsp/Login.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LoginServiceInterface logService = new LoginService();
		HttpSession session = request.getSession();

		String email = request.getParameter("mail");
		String password = request.getParameter("password");

		if(logService.validateField(email, password)){
			UsersEntity user = logService.getUsersData(email, password);
			if(user != null)
			{
				// Stockage du nom de l'utilisateur s'etant connecte
				if (email.equals(user.getMail()) && password.equals(user.getPassword())){
					session.setAttribute("UserName", user.getName());
					response.sendRedirect("Events");
				}
				else{
					request.setAttribute("erreur", "Identifiant et/ou mot de passe incorrect.");
					response.sendRedirect("Login");
				}
			}
			else
			{
				request.setAttribute("erreur", "Identifiant et/ou mot de passe incorrect.");
				response.sendRedirect("Login");
			}

		}
		else
			System.out.println("Identifiant et/ou mot de passe incorrect.");
		/*user.setMail("test@test.fr");
    	user.setPassword("Test");
    	user.setName("Toto");

    	service.insert(user);*/

	}

}
