package emn.tp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import emn.tp.bean.jpa.UsersEntity;
import emn.tp.persistence.PersistenceServiceProvider;
import emn.tp.persistence.services.UsersPersistence;
import emn.tp.services.implementation.RegisterService;
import emn.tp.services.interfaces.RegisterServiceInterface;

/**
 * Servlet implementation class Register
 */
@WebServlet({"/Register"})
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("REGISTER : Forwarding to Register JSP...");
		request.getRequestDispatcher("/WEB-INF/jsp/Register.jsp").forward(request, response);
		System.out.println("REGISTER : Removing registerError session variable...");
		request.getSession().removeAttribute("registerError");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Register service (database work)
		RegisterServiceInterface regService = new RegisterService();

		// Get inputs
		System.out.println("REGISTER : Getting inputs...");
		String pseudo = request.getParameter("pseudo") ;
		String mail = request.getParameter("mail") ;
		String password = request.getParameter("password") ;
		String passwordConfirmation = request.getParameter("passwordConfirmation") ;
		
		// If they are not all filled
		System.out.println("REGISTER : Validating fields...");
		if (!regService.validateField(pseudo, mail, password, passwordConfirmation)){
			System.out.println("REGISTER : Inputs not filled...");
			request.setAttribute("registerError", "Tous les champs doivent être renseignés.");
			response.sendRedirect("Register");
		// If the two passwords are not the same
		}else if (!regService.validatePassword(request.getParameter("password"), request.getParameter("passwordConfirmation"))) {
			System.out.println("REGISTER : The two passwords are not the same...");
			request.setAttribute("registerError", "Les deux mots de passe doivent correspondre.");
			response.sendRedirect("Register");
		// If mail already exist
		}else if(!regService.checkMail(mail)){
			System.out.println("REGISTER : The mail already exist...");
			request.setAttribute("registerError", "L'adresse mail existe déjà.");
			response.sendRedirect("Register");
			// If inputs are correctly filled
		}else{
			// Insert 
			System.out.println("REGISTER : Creating user entity...");
			UsersPersistence serviceUsers = PersistenceServiceProvider.getService(UsersPersistence.class);
			UsersEntity user = new UsersEntity();
			user.setPseudo(request.getParameter("pseudo"));
			user.setMail(request.getParameter("mail"));
	    	user.setPassword(request.getParameter("password"));
	    	System.out.println("REGISTER : Inserting into databse...");
	    	serviceUsers.insert(user);
			// Redirection
			response.sendRedirect("Login");
		}
		
	}

}
