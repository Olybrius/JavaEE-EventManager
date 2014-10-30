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

		// Event service (database work)
		EventsServiceInterface serviceEvents = new EventsService();	

		// Get input
		String id = request.getParameter("id");
		
		// Get all the events that are in the database
		System.out.println("EVENTS : Getting published events...");
		List<EventsEntity> eventsPublished = serviceEvents.getPublishedEvents();

		// If there is no event published
		if (eventsPublished.size() == 0) {
		
			System.out.println("EVENTS : No event published...");
			request.getSession().setAttribute("eventsError", "Aucun �v�nement n'est disponible pour le moment. Revenez un peu plus tard !");
			
		// If there are some events published
		}else{
			
			// If an id is passed
			if(id != null){
				try{
					// Parse the id
					System.out.println("EVENTS : Parsing the id...");
					int idEvents = Integer.parseInt(id);
					// Getting the event
					System.out.println("EVENTS : Getting the event by id...");
					EventsEntity event = serviceEvents.getEventsById(idEvents);
					if(event == null){
						System.out.println("EVENTS : Event not found...");
						request.getSession().setAttribute("eventsError", "L'�v�nement auquel vous voulez acc�der n'existe malheureusement pas.");
						request.getSession().removeAttribute("eventDisplayed");
					}else{
						System.out.println("EVENTS : Event found, sending it...");
						request.getSession().setAttribute("eventDisplayed", event);
					}
				}catch(NumberFormatException e){	
					// Error due to the id which is not a number
					System.out.println("EVENTS : Id parse error...");
					request.getSession().setAttribute("eventsError", "Un probl�me est survenu quant � l'�v�nement s�lectionn�.");	
				}
			// If no id is passed
			}else{
				System.out.println("EVENTS : No id passed...");
				request.getSession().removeAttribute("eventDisplayed");
			}
		}

		// Send the result
		System.out.println("EVENTS : Sending the events which are published [" + eventsPublished.size() + "]...");
		request.getSession().setAttribute("events", eventsPublished);

		// Show the JSP
		System.out.println("EVENTS : Forwarding to Events JSP...");
		request.getRequestDispatcher("/WEB-INF/jsp/Events.jsp").forward(request, response);
		System.out.println("EVENTS : Removing eventsError session variable...");
		request.getSession().removeAttribute("eventsError");

	}
}
