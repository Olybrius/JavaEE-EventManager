package emn.tp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import emn.tp.bean.jpa.UsersEntity;
import emn.tp.services.implementation.EventsService;
import emn.tp.services.interfaces.EventsServiceInterface;

/**
 * Servlet relatif � la publication d'un �v�nement.
 */
@WebServlet({"/Publish"})
public class Publish extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Publish() {
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
	protected void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		// Event service (database work)
		EventsServiceInterface serviceEvents = new EventsService();
		
		// Get input
		System.out.println("PUBLISH : Getting and parsing id...");
		int eventID = Integer.parseInt(request.getParameter("eventId"));
		UsersEntity user = (UsersEntity) session.getAttribute("user");

		// Tests
		System.out.println("PUBLISH : Checking the event id and if the user created the event...");
		if(!serviceEvents.eventExists(eventID)){
			System.out.println("PUBLISH : Event does not exist...");
			session.setAttribute("publishError", "L'�v�nement que vous avez tent� de publier n'existe pas.");
		}else if(!serviceEvents.userCreatedEvent(user.getId(), eventID)){
			System.out.println("PUBLISH : The user did not create the event...");
			session.setAttribute("publishError", "Vous n'avez pas cr�� l'�v�nement que vous avez tent� de publier.");
		}else{		
			System.out.println("PUBLISH : Event is published ...");
			serviceEvents.publishEvent(eventID);
			response.sendRedirect("MyEvents");
		}
	}
}

