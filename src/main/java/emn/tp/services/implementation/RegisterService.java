package emn.tp.services.implementation;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import emn.tp.services.interfaces.RegisterServiceInterface;

public class RegisterService implements RegisterServiceInterface {

	@Override
	public boolean validateField(String name, String mail, String passwd, String passwdConf) {
		return (!name.isEmpty() && (!mail.isEmpty() && mail.contains("@")) && !passwd.isEmpty() && !passwdConf.isEmpty());
	}

	@Override
	public boolean validatePassword(String passwd, String passwdConf){
		return passwd.equals(passwdConf);
	}

	@Override
	public boolean checkMail(String mail) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("persistence-unit1");
		EntityManager em = emf.createEntityManager();
		Query query = em.createNamedQuery("UsersEntity.checkMail");
		query.setParameter("email", mail);
		@SuppressWarnings("unchecked")
		List<String> list = query.getResultList();
		return list.size()== 0;
	}
	
}
