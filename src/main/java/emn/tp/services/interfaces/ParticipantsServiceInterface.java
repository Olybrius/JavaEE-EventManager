package emn.tp.services.interfaces;

import emn.tp.bean.jpa.EventsEntity;

public interface ParticipantsServiceInterface {
	
	public boolean mailParticipatesToEvent(String mail, int eventID);
	public void participate(String mail, String firstName, String name, String company, EventsEntity event);

}
