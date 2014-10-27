package emn.tp.services.interfaces;

import java.util.List;

import emn.tp.bean.jpa.EventsEntity;

public interface EventsServiceInterface {
	
	public List<EventsEntity> getEventsByUser(int userID);

}
