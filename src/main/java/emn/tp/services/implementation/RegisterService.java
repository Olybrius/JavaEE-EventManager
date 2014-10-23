package emn.tp.services.implementation;

import emn.tp.services.interfaces.RegisterServiceInterface;

public class RegisterService implements RegisterServiceInterface {

	@Override
	public boolean validateField(String name, String mail, String passwd,
			String passwdConf) {

		return (!name.isEmpty() && !mail.isEmpty() && !passwd.isEmpty() && !passwdConf.isEmpty());
	}

	@Override
	public boolean validatePassword(String passwd, String passwdConf){
		return passwd.equals(passwdConf);
	}
	
}
