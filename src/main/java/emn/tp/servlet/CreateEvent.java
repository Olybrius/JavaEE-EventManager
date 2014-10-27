package emn.tp.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import emn.tp.bean.jpa.EventsEntity;
import emn.tp.bean.jpa.ParticipantsEntity;
import emn.tp.bean.jpa.UsersEntity;
import emn.tp.persistence.PersistenceServiceProvider;
import emn.tp.persistence.services.EventsPersistence;
import emn.tp.services.implementation.CreateEventService;
import emn.tp.services.interfaces.CreateEventServiceInterface;

/**
 * Servlet implementation class CreateEvent
 */
@WebServlet("/CreateEvent")
public class CreateEvent extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateEvent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("CREATE EVENT : Forwarding to CreateEvent JSP...");
		request.getRequestDispatcher("/WEB-INF/jsp/CreateEvent.jsp").forward(request, response);
		System.out.println("CREATE EVENT : Removing createEventError session variable...");
		request.getSession().removeAttribute("createEventError");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Create event service (database work)
		CreateEventServiceInterface createEventService = new CreateEventService();
		
		// Get inputs
		System.out.println("CREATE EVENTS : Getting inputs...");
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String stringStartDate = request.getParameter("startDate") + " " + request.getParameter("startTime");
		String stringEndDate = request.getParameter("endDate") + " " + request.getParameter("endTime");
		short publish = request.getParameter("publish") != null ? (short)1 : (short)0 ;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		
		try {
			
			// Parse dates
			System.out.println("CREATE EVENTS : Parsing dates [" + stringStartDate + " & " + stringEndDate + "...");
			Date startDate = sdf.parse(stringStartDate);
			Date endDate = sdf.parse(stringEndDate);
			System.out.println("CREATE EVENTS : Validating fields...");
			// If inputs are not well filled
			if(!createEventService.validateField(name, address, startDate, endDate)){
				System.out.println("CREATE EVENTS : Fields not well filled...");
				request.getSession().setAttribute("createEventError", "Tous les champs sont requis et la date de début doit précéder la date de fin de l'évènement.");
				response.sendRedirect("CreateEvent");
			// If inputs are well filled
			}else{		
				// Insert
				System.out.println("CREATE EVENT : Creating event entity...");
				EventsPersistence serviceEvents = PersistenceServiceProvider.getService(EventsPersistence.class);
				EventsEntity event = new EventsEntity();
				System.out.println();
				event.setUrl(createEventService.getNewUrl());
				event.setName(name);
				event.setAddress(address);
				event.setStartdate(startDate);
				event.setEnddate(endDate);
				event.setPublished(publish);
				event.setListOfParticipants(new ArrayList<ParticipantsEntity>());
				event.setUsers((UsersEntity)request.getSession().getAttribute("user"));
				System.out.println("CREATE EVENT : Inserting into databse...");
				serviceEvents.insert(event);
				// Redirection
				response.sendRedirect("Events");
			}
			
		} catch (ParseException e) {
			
			System.out.println("CREATE EVENTS : Error during the parse...");
			request.getSession().setAttribute("createEventError", "Une erreur est survenue durant le découpage des dates.");
			response.sendRedirect("CreateEvent");
		
		}

	}

}
