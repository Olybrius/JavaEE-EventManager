package emn.tp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import emn.tp.bean.jpa.EventsEntity;
import emn.tp.services.implementation.EventsService;
import emn.tp.services.implementation.ParticipantsService;
import emn.tp.services.interfaces.EventsServiceInterface;
import emn.tp.services.interfaces.ParticipantsServiceInterface;

@WebServlet("/Subscribe")
public class Subscribe extends HttpServlet{

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Subscribe() {
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
		System.out.println("SUBSCRIBE : initialisation");
		int eventID = Integer.parseInt(request.getParameter("eventId"));
		String mail = request.getParameter("mail");
		String firstName = request.getParameter("firstName");
		String name = request.getParameter("name");
		String company = request.getParameter("company");

		ParticipantsServiceInterface servicePart = new ParticipantsService();
		EventsServiceInterface serviceEvents = new EventsService();
		EventsEntity event = serviceEvents.getEventsById(eventID);
		
		System.out.println("SUBSCRIBE : initialisation");
		if(event != null && event.getPublished().equals((short)1)){
			if(servicePart.participate(mail, eventID)){
				System.out.println("SUBSCRIBE : Participant already subscribe");
			}
			else{
				System.out.println("SUBSCRIBE : Subscription ...");
				servicePart.subscribeParticipant(mail, firstName, name, company, event);
				response.sendRedirect("Events");
			}
		}

	}
}
