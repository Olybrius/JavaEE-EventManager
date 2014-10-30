package emn.tp.test.services;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import emn.tp.bean.jpa.EventsEntity;
import emn.tp.bean.jpa.UsersEntity;
import emn.tp.persistence.PersistenceServiceProvider;
import emn.tp.persistence.services.EventsPersistence;
import emn.tp.persistence.services.UsersPersistence;
import emn.tp.services.implementation.EventsService;

public class EventsServiceTest {
	
	private static EventsService eventsService;
	private static UsersEntity user;
	private static EventsEntity event;
	private static List<EventsEntity> listeEvent = new ArrayList<EventsEntity>();
	private static EventsPersistence eventPersistence = PersistenceServiceProvider.getService(EventsPersistence.class);
	private static UsersPersistence userPersistence = PersistenceServiceProvider.getService(UsersPersistence.class);
	
	@BeforeClass
	public static void setUp(){
		
		eventsService = new EventsService();
		
		//User creation
		user = new UsersEntity();
		user.setPseudo("Testeur1");
		user.setMail("userTest@Test.fr");
		user.setPassword("mdptest");
		userPersistence.insert(user);
		
		//Events creation
		event = new EventsEntity();
		event.setName("Event1");
		event.setAddress("adresse1");
		event.setStartdate(new Date());
		event.setEnddate(new Date());
		event.setPublished((short)1);
		event.setUsers(user);
		listeEvent.add(event);
		eventPersistence.insert(event);
		
		event = new EventsEntity();
		event.setName("Event2");
		event.setAddress("adresse2");
		event.setStartdate(new Date());
		event.setEnddate(new Date());
		event.setPublished((short)0);
		event.setUsers(user);
		listeEvent.add(event);
		eventPersistence.insert(event);
		
	}
	
	@Test
	public void userCreatedEventTest(){
		assertTrue("Error: User did not create the event", eventsService.userCreatedEvent(user.getId(), listeEvent.get(0).getId()));
	}
	
	@Test
	public void eventExistsTest(){
		assertTrue("Error: Event does not exist", eventsService.eventExists(listeEvent.get(1).getId()));
	}
	
	@Test
	public void getEventsByUserTest(){
		assertTrue("Error: No user with this ID", listeEvent.equals(eventsService.getEventsByUser(user.getId())));		
	}

	@AfterClass
	public static void tearDown(){
	
		int id1 = listeEvent.get(0).getId(); 
		int id2 = listeEvent.get(1).getId(); 
		listeEvent.clear();
		eventPersistence.delete(id1);
		eventPersistence.delete(id2);
		
		int idUser = user.getId();
		userPersistence.delete(idUser);
	}
}
