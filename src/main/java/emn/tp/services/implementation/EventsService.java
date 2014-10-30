package emn.tp.services.implementation;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import emn.tp.bean.jpa.EventsEntity;
import emn.tp.bean.jpa.UsersEntity;
import emn.tp.persistence.PersistenceServiceProvider;
import emn.tp.persistence.services.EventsPersistence;
import emn.tp.persistence.services.jpa.EventsPersistenceJPA;
import emn.tp.services.interfaces.EventsServiceInterface;

public class EventsService implements EventsServiceInterface {

	@Override
	public List<EventsEntity> getEventsByUser(int userID) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence-unit1");
		EntityManager em = emf.createEntityManager();
		Query query = em.createNamedQuery("EventsEntity.getAllWithUserId");
		query.setParameter("userid", userID);
		@SuppressWarnings("unchecked")
		List<EventsEntity> listEvents = query.getResultList();

		return listEvents;
	}

	@Override
	public void createEvent(String name, String address, Date startDate, Date endDate, short publish, UsersEntity user) {
		
		System.out.println("CREATE EVENT : Creating event entity...");
		EventsPersistence serviceEvents = PersistenceServiceProvider.getService(EventsPersistence.class);
		EventsEntity event = new EventsEntity();
		System.out.println();
		event.setName(name);
		event.setAddress(address);
		event.setStartdate(startDate);
		event.setEnddate(endDate);
		event.setPublished(publish);
		event.setUsers(user);
		System.out.println("CREATE EVENT : Inserting into databse...");
		serviceEvents.insert(event);
	}

	@Override
	public boolean checkIdEvent(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence-unit1");
		EntityManager em = emf.createEntityManager();
		EventsEntity event = em.find(EventsEntity.class, id);
		
		return (event != null);
	}

	@Override
	public boolean validateUser(int userID, int eventID) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence-unit1");
		EntityManager em = emf.createEntityManager();
		Query query = em.createNamedQuery("EventsEntity.checkUserId");
		query.setParameter("eventid", eventID);
		@SuppressWarnings("unchecked")
		List<Integer> userid = query.getResultList();
		
		return (userID == (userid.get(0)));
	}

	@Override
	public void publishEvent(int eventID) {
		EventsPersistenceJPA epj = new EventsPersistenceJPA();
		EventsEntity event = epj.load(eventID);
		event.setPublished((short)1);
		epj.save(event);
	}

	@Override
	public List<EventsEntity> getPublishedEvents() {
		EventsPersistenceJPA epj = new EventsPersistenceJPA();
		HashMap<String, Object> recupEvent = new HashMap<String, Object>();
		recupEvent.put("published", (short) 1);
		List<EventsEntity> listEventPublished = epj.search(recupEvent);
		return listEventPublished;
	}

	@Override
	public EventsEntity getEventsById(int eventID) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence-unit1");
		EntityManager em = emf.createEntityManager();
		EventsEntity event = em.find(EventsEntity.class, eventID);
		
		return event;
	}

}
