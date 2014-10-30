package emn.tp.test.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;

import emn.tp.bean.jpa.*;
import emn.tp.persistence.PersistenceServiceProvider;
import emn.tp.persistence.services.*;

public class EventsServiceTest {
	
	private static EventsServiceTest eventsServiceTest;
	
	private EventsEntity event;
	
	
	List<EventsEntity> listeEvent = new ArrayList<EventsEntity>();
	
	
	EventsPersistence eventPersistence = PersistenceServiceProvider.getService(EventsPersistence.class);
	
	

	@Before
	public void setUp(){
		
		eventsServiceTest = new EventsServiceTest();
		
		//Events creation
		event = new EventsEntity();
		event.setName("Event1");
		event.setAddress("adresse1");
		event.setStartdate(new Date());
		event.setEnddate(new Date());
		event.setPublished((short)1);
		listeEvent.add(event);
		eventPersistence.insert(event);
		
		event = new EventsEntity();
		event.setName("Event2");
		event.setAddress("adresse2");
		event.setStartdate(new Date());
		event.setEnddate(new Date());
		event.setPublished((short)0);
		listeEvent.add(event);
		eventPersistence.insert(event);
		
	}
}
