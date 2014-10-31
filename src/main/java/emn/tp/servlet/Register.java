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

import emn.tp.services.implementation.UsersService;
import emn.tp.services.interfaces.UsersServiceInterface;
import emn.tp.tools.Tools;

/**
 * Servlet relatif à l'inscription d'un utilisateur.
 */
@WebServlet({"/Register"})
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(Register.class);
       
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
		logger.debug("REGISTER : Forwarding to Register JSP...");
		request.getRequestDispatcher("/WEB-INF/jsp/Register.jsp").forward(request, response);
		logger.debug("REGISTER : Removing registerError session variable...");
		request.getSession().removeAttribute("registerError");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		// Register service (database work)
		UsersServiceInterface serviceLog = new UsersService();

		// Get inputs
		logger.debug("REGISTER : Getting inputs...");
		String pseudo = request.getParameter("pseudo") ;
		String mail = request.getParameter("mail") ;
		String password = request.getParameter("password") ;
		String passwordConfirmation = request.getParameter("passwordConfirmation") ;
		
		// If they are not all filled
		logger.debug("REGISTER : Validating fields...");
		if (!Tools.validateFieldRegister(pseudo, mail, password, passwordConfirmation)){
			logger.debug("REGISTER : Inputs not filled...");
			session.setAttribute("registerError", "Tous les champs doivent être renseignés.");
			response.sendRedirect("Register");
		// If the two passwords are not the same
		}else if (!Tools.validatePassword(request.getParameter("password"), request.getParameter("passwordConfirmation"))) {
			logger.debug("REGISTER : The two passwords are not the same...");
			session.setAttribute("registerError", "Les deux mots de passe doivent correspondre.");
			response.sendRedirect("Register");
		// If mail already exists
		}else if(!serviceLog.mailExists(mail)){
			logger.debug("REGISTER : Mail already exists...");
			session.setAttribute("registerError", "Un compte existe déjà pour l'adresse mail renseignée.");
			response.sendRedirect("Register");
		// If inputs are correctly filled
		}else{
			// Insert 
			serviceLog.register(pseudo, mail, passwordConfirmation);
			// Redirection
			response.sendRedirect("Login");
		}
		
	}

}