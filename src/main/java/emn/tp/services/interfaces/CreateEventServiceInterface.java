package emn.tp.services.interfaces;

import java.util.Date;

public interface CreateEventServiceInterface {
	
	public boolean validateField(String name, String address, Date startDate, Date endDate);

}
