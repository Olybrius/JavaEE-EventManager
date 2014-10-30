package emn.tp.services.interfaces;

import java.util.Date;
import java.util.List;

import emn.tp.bean.jpa.EventsEntity;
import emn.tp.bean.jpa.UsersEntity;

public interface EventsServiceInterface {
	
	public List<EventsEntity> getEventsByUser(int userID);
	public void createEvent(String name, String address, Date startDate, Date endDate, short publish,  UsersEntity user);
	public boolean eventExists(int id);
	public boolean userCreatedEvent(int userID, int eventID);
	public void publishEvent(int eventID);
	public List<EventsEntity> getPublishedEvents();
	public EventsEntity getEventById(int eventID);

}
