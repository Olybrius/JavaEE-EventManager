package emn.tp.services.interfaces;

import emn.tp.bean.jpa.UsersEntity;

public interface LoginServiceInterface {
	
	public UsersEntity getUsersData(String email, String passwd);
	public boolean validateField(String email, String passwd);

}
