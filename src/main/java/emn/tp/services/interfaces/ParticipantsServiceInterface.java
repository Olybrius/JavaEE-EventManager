package emn.tp.services.interfaces;

import emn.tp.bean.jpa.EventsEntity;

public interface ParticipantsServiceInterface {
	
	public boolean participate(String mail, int eventID);
	public void subscribeParticipant(String mail, String firstName, String name, String company, EventsEntity event);

}
