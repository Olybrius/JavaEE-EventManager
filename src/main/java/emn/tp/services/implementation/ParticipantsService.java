package emn.tp.services.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import emn.tp.bean.jpa.EventsEntity;
import emn.tp.bean.jpa.ParticipantsEntity;
import emn.tp.persistence.PersistenceServiceProvider;
import emn.tp.persistence.services.ParticipantsPersistence;
import emn.tp.services.interfaces.ParticipantsServiceInterface;

public class ParticipantsService implements ParticipantsServiceInterface{

	@Override
	public boolean mailParticipatesToEvent(String mail, int eventID) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence-unit1");
		EntityManager em = emf.createEntityManager();
		Query query = em.createNamedQuery("ParticipantsEntity.participateOrNot");
		query.setParameter("mail", mail);
		query.setParameter("eventid", eventID);
		@SuppressWarnings("unchecked")
		List<ParticipantsEntity> listePart = query.getResultList();
		
		return (!listePart.isEmpty());
	}

	@Override
	public void participate(String mail, String firstName,
			String name, String company, EventsEntity event) {
		
		System.out.println("SUBSCRIBE : Creating participant entity...");
		ParticipantsPersistence serviceEvents = PersistenceServiceProvider.getService(ParticipantsPersistence.class);
		ParticipantsEntity participant = new ParticipantsEntity();
		System.out.println();
		participant.setMail(mail);
		participant.setFirstname(firstName);
		participant.setName(name);
		participant.setCompany(company);
		participant.setEvents(event);
		System.out.println("SUBSCRIBE : Inserting into databse...");
		serviceEvents.insert(participant);
		
	}

}
