package emn.tp.services.implementation;

import java.util.HashMap;
import java.util.List;

import emn.tp.bean.jpa.UsersEntity;
import emn.tp.persistence.services.jpa.UsersPersistenceJPA;
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
		
		UsersPersistenceJPA epj = new UsersPersistenceJPA();

		HashMap<String, Object> recupUser = new HashMap<String, Object>();
		recupUser.put("mail", email);
		recupUser.put("password", password);
		List<UsersEntity> list = epj.search(recupUser);

		/*EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence-unit1");
		EntityManager em = emf.createEntityManager();
		final String QUERY = "SELECT u FROM UsersEntity u WHERE u.mail='"+email+"' AND u.password='"+password+"'";
		Query query = em.createQuery(QUERY);
		@SuppressWarnings("unchecked")
		List<UsersEntity> list = query.getResultList();*/
		
		if(list.size() == 1) return list.get(0);
		else return null;
		
	}

	/**
	 * Vérifie la conformité des champs
	 */
	@Override
	public boolean validateField(String email, String passwd) {
		return ((email != null && !email.isEmpty()) && passwd != null && !passwd.isEmpty()) ;
	}
	
}
