package emn.tp.services.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import emn.tp.bean.jpa.UsersEntity;
import emn.tp.persistence.PersistenceServiceProvider;
import emn.tp.persistence.services.UsersPersistence;
import emn.tp.services.interfaces.LoginServiceInterface;

/**
 * @author Joris & Killian
 *
 */

public class LoginService implements LoginServiceInterface {

	/**
	 * R�cup�re les informations de l'utilisateur.
	 */
	@Override
	public UsersEntity getUsersData(String email, String passwd) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence-unit1");
		EntityManager em = emf.createEntityManager();
		final String QUERY = "SELECT u.userid FROM UsersEntity u WHERE u.mail='"+email+"' AND u.password='"+passwd+"'";
		Query query = em.createQuery(QUERY);
		List<Integer> list = query.getResultList();
		if(list.size() == 1)
		{
			UsersEntity user = em.find(UsersEntity.class, list.get(0));
			return user;
		}
		else
			return null;
	}

	/**
	 * V�rifie la conformit� des champs
	 */
	@Override
	public boolean validateField(String email, String passwd) {
		if((email != null && !email.isEmpty()) && passwd != null && !passwd.isEmpty())
			return true;
		else
			return false;
	}
	
}