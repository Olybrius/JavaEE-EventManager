package emn.tp.services.implementation;

import java.util.Date;

import emn.tp.services.interfaces.CreateEventServiceInterface;

public class CreateEventService implements CreateEventServiceInterface {

	@Override
	public boolean validateField(String name, String address, Date startDate,
			Date endDate) {
		
		return (!name.isEmpty() && !address.isEmpty() && (startDate.before(endDate)));
	}

	
}
