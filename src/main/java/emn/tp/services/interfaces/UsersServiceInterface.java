package emn.tp.services.interfaces;

import emn.tp.bean.jpa.UsersEntity;

public interface UsersServiceInterface {
	
	public UsersEntity getUser(String email, String passwd);
	public boolean mailExists(String mail);
	public void register(String pseudo, String mail, String password);

}
