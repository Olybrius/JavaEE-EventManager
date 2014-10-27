package emn.tp.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import emn.tp.bean.jpa.EventsEntity;
import emn.tp.bean.jpa.UsersEntity;
import emn.tp.services.implementation.EventsService;
import emn.tp.services.interfaces.EventsServiceInterface;

/**
 * Servlet implementation class Events
 */
@WebServlet("/Events")
public class Events extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Events() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/Events.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Event service (database work)
		EventsServiceInterface serviceEvents = new EventsService();	
		
		// Get the events created by the current user
		UsersEntity user = (UsersEntity)request.getSession().getAttribute("user");	
		System.out.println("EVENTS : Getting the events created by the current user [" + user.getName() + " - " + user.getMail() + "]...");
		List<EventsEntity> events = serviceEvents.getEventsFromBDD(user.getUserid());
		
		// Send the result
		System.out.println("EVENTS : Sending the result to show...");
		request.getSession().setAttribute("eventsNumber", events.size());
		request.getSession().setAttribute("events", events);
		response.sendRedirect("Events");
		
	}

}
