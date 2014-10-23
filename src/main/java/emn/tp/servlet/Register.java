package emn.tp.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import emn.tp.bean.jpa.UsersEntity;
import emn.tp.persistence.PersistenceServiceProvider;
import emn.tp.persistence.services.UsersPersistence;
import emn.tp.services.implementation.LoginService;
import emn.tp.services.implementation.RegisterService;
import emn.tp.services.interfaces.LoginServiceInterface;
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
		request.getRequestDispatcher("/WEB-INF/jsp/Register.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RegisterServiceInterface regService = new RegisterService();
		if (!regService.validateField(request.getParameter("pseudo"), request.getParameter("mail"), request.getParameter("password"), 
				request.getParameter("passwordConfirmation"))) {
			//TODO Message d'erreur
		} else if (!regService.validatePassword(request.getParameter("password"), request.getParameter("passwordConfirmation"))) {
			//TODO Message d'erreur
		} else {
			// Ajout en base
			UsersPersistence serviceUsers = PersistenceServiceProvider.getService(UsersPersistence.class);
			UsersEntity user = new UsersEntity();
			user.setMail(request.getParameter("pseudo"));
			user.setMail(request.getParameter("mail"));
	    	user.setPassword(request.getParameter("password"));

	    	serviceUsers.insert(user);

			// Redirection
			response.sendRedirect("Login");

		}
	}

}
