package emn.tp.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import emn.tp.bean.jpa.EventsEntity;
import emn.tp.services.implementation.EventsService;
import emn.tp.services.interfaces.EventsServiceInterface;

/**
 * Servlet relatif aux évènements publiés.
 * @author Joris & Killian
 */
@WebServlet("/Events")
public class Events extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LogManager.getLogger(Events.class);
	
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
		
		HttpSession session = request.getSession();
		
		// Event service (database work)
		EventsServiceInterface serviceEvents = new EventsService();	

		// Get input
		String id = request.getParameter("id");
		
		// Get all the events that are in the database
		logger.debug("EVENTS : Getting published events...");
		List<EventsEntity> eventsPublished = serviceEvents.getPublishedEvents();

		// If there is no event published
		if (eventsPublished.size() == 0) {
		
			logger.debug("EVENTS : No event published...");
			
		// If there are some events published
		}else{
			
			// If an id is passed
			if(id != null){
				try{
					// Parse the id
					logger.debug("EVENTS : Parsing the id...");
					int idEvents = Integer.parseInt(id);
					// Getting the event
					logger.debug("EVENTS : Getting the event by id...");
					EventsEntity event = serviceEvents.getEventById(idEvents);
					if(event == null){
						logger.debug("EVENTS : Event not found...");
						session.setAttribute("eventsError", "L'évènement auquel vous voulez accéder n'existe malheureusement pas.");
						session.removeAttribute("eventDisplayed");
					}else{
						logger.debug("EVENTS : Event found, sending it...");
						session.setAttribute("eventDisplayed", event);
					}
				}catch(NumberFormatException e){	
					// Error due to the id which is not a number
					logger.debug("EVENTS : Id parse error...");
					session.setAttribute("eventsError", "Un problème est survenu quant à l'évènement sélectionné.");	
				}
			// If no id is passed
			}else{
				logger.debug("EVENTS : No id passed...");
				session.removeAttribute("eventDisplayed");
			}
		}

		// Send the result
		logger.debug("EVENTS : Sending the events which are published [" + eventsPublished.size() + "]...");
		session.setAttribute("events", eventsPublished);

		// Show the JSP
		logger.debug("EVENTS : Forwarding to Events JSP...");
		request.getRequestDispatcher("/WEB-INF/jsp/Events.jsp").forward(request, response);
		logger.debug("EVENTS : Removing eventsError session variable...");
		session.removeAttribute("eventsError");

	}
}
