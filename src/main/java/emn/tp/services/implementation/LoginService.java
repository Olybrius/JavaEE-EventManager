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
	 * R�cup�re les informations de l'utilisateur.
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

	/**
	 * V�rifie la conformit� des champs
	 */
	@Override
	public boolean validateField(String email, String passwd) {
		return ((email != null && !email.isEmpty()) && passwd != null && !passwd.isEmpty()) ;
	}
	
}
