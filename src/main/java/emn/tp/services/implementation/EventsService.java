package emn.tp.services.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import emn.tp.bean.jpa.EventsEntity;
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

}
