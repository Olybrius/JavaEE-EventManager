package emn.tp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import emn.tp.bean.jpa.UsersEntity;
import emn.tp.services.implementation.PublishService;
import emn.tp.services.interfaces.PublishServiceInterface;

/**
 * Servlet implementation class Publish
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

	protected void process (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("PUBLISH");
		int eventID = Integer.parseInt(request.getParameter("event"));
		UsersEntity user = (UsersEntity) request.getSession().getAttribute("user");
		PublishServiceInterface servicePublish = new PublishService();

		if(!servicePublish.checkIdEvent(eventID)){
			System.out.println("PUBLISH : Event does not exist");
			//TODO : Message d'erreur
		}
		else if(!servicePublish.validateUser(user.getId(), eventID)){
			System.out.println("PUBLISH : Unvalidate user");
			//TODO : Message d'erreur
		}
		else{		
			System.out.println("PUBLISH : Event is published ...");
			servicePublish.publishEvent(eventID);
			response.sendRedirect("MyEvents");
		}
	}
}

