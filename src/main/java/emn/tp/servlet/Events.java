package emn.tp.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import emn.tp.bean.jpa.EventsEntity;
import emn.tp.services.implementation.EventsService;
import emn.tp.services.interfaces.EventsServiceInterface;

/**
 * Servlet implementation class Disconnect
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

		String id = request.getParameter("id");

		// Event service (database work)
		EventsServiceInterface serviceEvents = new EventsService();	

		// Get all the events that are in the database
		System.out.println("EVENTS : GET Published Events");
		List<EventsEntity> eventsPublished = serviceEvents.getPublishedEvents();

		// Send the result
		System.out.println("EVENTS : Sending the events published to show [" + eventsPublished.size() + "]...");
		request.getSession().setAttribute("events", eventsPublished);

		if(id != null){

			int idEvents = Integer.parseInt(id);
			if(!serviceEvents.checkIdEvent(idEvents)){
				System.out.println("EVENTS : Event does not exist");
			}
			else{
				System.out.println("EVENTS : Event with id");
				EventsEntity event = serviceEvents.getEventsById(idEvents);
				request.getSession().setAttribute("eventDisplayed", event);
			}
		}
		else if(eventsPublished.size() != 0){
			System.out.println("EVENTS : First event in the list");
			request.getSession().setAttribute("eventDisplayed", eventsPublished.get(0));
		}
		else{
			System.out.println("EVENTS : No events published");
		}

		// Show the JSP
		System.out.println("EVENTS : Forwarding to Events JSP...");
		request.getRequestDispatcher("/WEB-INF/jsp/Events.jsp").forward(request, response);

	}
}
