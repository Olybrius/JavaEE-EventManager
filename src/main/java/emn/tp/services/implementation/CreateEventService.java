package emn.tp.services.implementation;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import emn.tp.services.interfaces.CreateEventServiceInterface;

public class CreateEventService implements CreateEventServiceInterface {

	@Override
	public boolean validateField(String name, String address, Date startDate, Date endDate) {
		return (!name.isEmpty() && !address.isEmpty() && (startDate.before(endDate)));
	}

	@Override
	public String getNewUrl() {
		SecureRandom random = new SecureRandom();
		String url ;
		do{
			url = new BigInteger(130, random).toString(32);
		}while(urlExists(url));
		return url;
	}

	private boolean urlExists(String url){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence-unit1");
		EntityManager em = emf.createEntityManager();
		Query query = em.createNamedQuery("EventsEntity.checkUrl");
		query.setParameter("url", url);
		@SuppressWarnings("unchecked")
		List<String> list = query.getResultList();
		return list.size()!=0;
	}
	
}
