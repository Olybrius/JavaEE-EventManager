package emn.tp.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import emn.tp.bean.jpa.EventsEntity;
import emn.tp.bean.jpa.UsersEntity;
import emn.tp.services.implementation.EventsService;
import emn.tp.services.interfaces.EventsServiceInterface;

/**
 * Servlet reltif aux évènements créés par un utilisateur.
 */
@WebServlet("/MyEvents")
public class UserEvents extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserEvents() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	/**
	 * process
	 */
	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		// Event service (database work)
		EventsServiceInterface serviceEvents = new EventsService();	

		// Get the events created by the current user
		UsersEntity user = (UsersEntity)session.getAttribute("user");	
		System.out.println("USER EVENTS : Getting the events created by the current user [" + user.getPseudo() + " - " + user.getMail() + "]...");
		List<EventsEntity> events = serviceEvents.getEventsByUser(user.getId());

		// Send the result
		System.out.println("USER EVENTS : Sending the events to show [" + events.size() + "]...");
		request.getSession().setAttribute("userEvents", events);
		
		// Show the JSP
		System.out.println("USER EVENTS : Forwarding to UserEvents JSP...");
		request.getRequestDispatcher("/WEB-INF/jsp/UserEvents.jsp").forward(request, response);
		System.out.println("USER EVENTS : Removing publishError session variable...");
		request.getSession().removeAttribute("publishError");
		
	}

}