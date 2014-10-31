package emn.tp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import emn.tp.bean.jpa.EventsEntity;
import emn.tp.services.implementation.EventsService;
import emn.tp.services.implementation.ParticipantsService;
import emn.tp.services.interfaces.EventsServiceInterface;
import emn.tp.services.interfaces.ParticipantsServiceInterface;
import emn.tp.tools.Tools;

/**
 * Servlet relatif à la gestion des participants à un évènement.
 * @author Joris & Killian
 */

@WebServlet("/Participate")
public class Participate extends HttpServlet{

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Participate() {
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

		// Services (database work)
		ParticipantsServiceInterface servicePart = new ParticipantsService();
		EventsServiceInterface serviceEvents = new EventsService();

		// Get inputs
		System.out.println("PARTICIPATE : Getting inputs...");
		int eventID = Integer.parseInt(request.getParameter("eventId"));
		String mail = request.getParameter("mail");
		String firstName = request.getParameter("firstName");
		String name = request.getParameter("name");
		String company = request.getParameter("company");

		//Validate field
		if(Tools.validateFieldParticipant(firstName, name, mail, company)){

			// Get the event by id
			System.out.println("PARTICIPATE : Getting the event by id...");
			EventsEntity event = serviceEvents.getEventById(eventID);
			// If an event is found and published
			if(event != null && event.getPublished().equals((short)1)){
				// If the user already participates to this event
				if(servicePart.mailParticipatesToEvent(mail, eventID)){
					System.out.println("PARTICIPATE : The participant already participates to this event...");
					session.setAttribute("eventsError", "L'adresse mail renseignée est déjà inscrite sur cet évènement.");
					// Otherwise
				}else{
					System.out.println("PARTICIPATE : Inserting the participant...");
					servicePart.participate(mail, firstName, name, company, event);
				}
				// Otherwise
			}else{
				System.out.println("PARTICIPATE : The event does not exist or not published...");
				session.setAttribute("eventsError", "L'évènement auquel vous avez essayé de vous inscrire n'existe pas ou n'est pas publié.");
			}

			// Redirection
			System.out.println("PARTICIPATE : Redirecting to Events servlet...");
			response.sendRedirect("Events?id=" + request.getParameter("eventId"));
		}
		else{
			session.setAttribute("eventsError", "Les informations renseignés sont incorrectes.");
			response.sendRedirect("Events?id=" + request.getParameter("eventId"));
		}
	}
}
