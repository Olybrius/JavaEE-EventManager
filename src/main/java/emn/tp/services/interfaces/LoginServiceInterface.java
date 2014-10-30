package emn.tp.services.interfaces;

import emn.tp.bean.jpa.UsersEntity;

public interface LoginServiceInterface {
	
	public UsersEntity getUser(String email, String passwd);
	public boolean checkMail(String mail);
	public void register(String pseudo, String mail, String password);

}
