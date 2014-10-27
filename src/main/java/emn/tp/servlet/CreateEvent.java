package emn.tp.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import emn.tp.bean.jpa.EventsEntity;
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
		request.getRequestDispatcher("/WEB-INF/jsp/CreateEvent.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		CreateEventServiceInterface serviceCreateEvents = new CreateEventService();
		
		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String stringStartDate = request.getParameter("startDate");
		SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd hh:mi:ss");
		Date startDate = null;
		
		try {
			startDate = sdf.parse(stringStartDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String stringEndDate = request.getParameter("endDate");
		Date endDate = null;
		
		try {
			endDate = sdf.parse(stringEndDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(!serviceCreateEvents.validateField(name, address, startDate, endDate)){
			//TODO : Erreur
		}
		
		else{
			System.out.println("CREATE_EVENT: Creating events entity...");
			
			EventsPersistence serviceEvents = PersistenceServiceProvider.getService(EventsPersistence.class);
			EventsEntity event = new EventsEntity();
			event.setName(name);
			event.setAddress(address);
			event.setStartdate(startDate);
			event.setEnddate(endDate);
			System.out.println("CREATE_EVENT : Inserting into databse...");
			serviceEvents.insert(event);
			//Redirection
			response.sendRedirect("Events");
		}
		
	}

}
