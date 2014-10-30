package emn.tp.services.implementation;

import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import emn.tp.bean.jpa.UsersEntity;
import emn.tp.persistence.PersistenceServiceProvider;
import emn.tp.persistence.services.UsersPersistence;
import emn.tp.persistence.services.jpa.UsersPersistenceJPA;
import emn.tp.services.interfaces.UsersServiceInterface;

/**
 * @author Joris & Killian
 *
 */

public class UsersService implements UsersServiceInterface {

	/**
	 * Récupère les informations de l'utilisateur.
	 */
	@Override
	public UsersEntity getUser(String email, String password) {
		
		UsersPersistenceJPA epj = new UsersPersistenceJPA();
		HashMap<String, Object> recupUser = new HashMap<String, Object>();
		recupUser.put("mail", email);
		recupUser.put("password", password);
		List<UsersEntity> list = epj.search(recupUser);
		
		if(list.size() == 1) return list.get(0);
		else return null;
		
	}

	@Override
	public boolean mailExists(String mail) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence-unit1");
		EntityManager em = emf.createEntityManager();
		Query query = em.createNamedQuery("UsersEntity.checkMail");
		query.setParameter("email", mail);
		@SuppressWarnings("unchecked")
		List<String> list = query.getResultList();
		return list.size() == 0;
	}

	@Override
	public void register(String pseudo, String mail, String password) {
		System.out.println("REGISTER : Creating user entity...");
		UsersPersistence serviceUsers = PersistenceServiceProvider.getService(UsersPersistence.class);
		UsersEntity user = new UsersEntity();
		user.setPseudo(pseudo);
		user.setMail(mail);
		user.setPassword(password);
    	System.out.println("REGISTER : Inserting into databse...");
    	serviceUsers.insert(user);
	}
	
}
