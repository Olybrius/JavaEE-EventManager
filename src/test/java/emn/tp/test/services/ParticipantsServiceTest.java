package emn.tp.test.services;

import static org.junit.Assert.assertTrue;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import emn.tp.bean.jpa.EventsEntity;
import emn.tp.bean.jpa.ParticipantsEntity;
import emn.tp.bean.jpa.UsersEntity;
import emn.tp.persistence.PersistenceServiceProvider;
import emn.tp.persistence.services.EventsPersistence;
import emn.tp.persistence.services.ParticipantsPersistence;
import emn.tp.persistence.services.UsersPersistence;
import emn.tp.services.implementation.ParticipantsService;

public class ParticipantsServiceTest {

	private static ParticipantsService participantsService;
	private static ParticipantsEntity participant;
	private static EventsEntity event;
	private static UsersEntity user;
	private static ParticipantsPersistence participantPersistence = PersistenceServiceProvider.getService(ParticipantsPersistence.class);
	private static EventsPersistence eventPersistence = PersistenceServiceProvider.getService(EventsPersistence.class);
	private static UsersPersistence userPersistence = PersistenceServiceProvider.getService(UsersPersistence.class);

	@Before
	public void setUp(){
		participantsService = new ParticipantsService();

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
		eventPersistence.insert(event);

		//Participant creation
		participant = new ParticipantsEntity();
		participant.setName("Toto");
		participant.setFirstname("Un");
		participant.setMail("toto@un.fr");
		participant.setCompany("Lego");
		participant.setEvents(event);
		participantPersistence.insert(participant);

	}

	@Test
	public void mailParticipatesToEvent(){
		assertTrue("Error: ", participantsService.mailParticipatesToEvent(participant.getMail(), participant.getEvents().getId()));
	}

	@After
	public void tearDown(){

		int id = participant.getId(); 
		participantPersistence.delete(id);

		int idEvent = event.getId();
		eventPersistence.delete(idEvent);

		int idUser = user.getId();
		userPersistence.delete(idUser);


	}

}
