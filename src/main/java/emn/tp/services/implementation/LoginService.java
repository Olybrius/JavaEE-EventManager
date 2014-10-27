package emn.tp.services.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import emn.tp.bean.jpa.UsersEntity;
import emn.tp.services.interfaces.LoginServiceInterface;

/**
 * @author Joris & Killian
 *
 */

public class LoginService implements LoginServiceInterface {

	/**
	 * Récupère les informations de l'utilisateur.
	 */
	@Override
	public UsersEntity getUser(String email, String password) {
		
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence-unit1");
		EntityManager em = emf.createEntityManager();
		final String QUERY = "SELECT u.id FROM UsersEntity u WHERE u.mail='"+email+"' AND u.password='"+password+"'";
		Query query = em.createQuery(QUERY);
		@SuppressWarnings("unchecked")
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
	 * Vérifie la conformité des champs
	 */
	@Override
	public boolean validateField(String email, String passwd) {
		return ((email != null && !email.isEmpty()) && passwd != null && !passwd.isEmpty()) ;
	}
	
}
