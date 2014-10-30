package emn.tp.services.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import emn.tp.bean.jpa.EventsEntity;
import emn.tp.services.interfaces.PublishServiceInterface;

public class PublishService implements PublishServiceInterface {

	@Override
	public boolean checkIdEvent(int id) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence-unit1");
		EntityManager em = emf.createEntityManager();
		EventsEntity event = em.find(EventsEntity.class, id);
		
		return (event!= null);
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

}
