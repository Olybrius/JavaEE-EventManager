package emn.tp.tools;

import java.util.Date;

public class Tools {

	public static boolean validateFieldRegister(String name, String mail, String passwd,
			String passwdConf) {
		
		return (!name.isEmpty() && (!mail.isEmpty() && mail.contains("@")) && !passwd.isEmpty() && !passwdConf.isEmpty());
	}

	public static boolean validatePassword(String passwd, String passwdConf) {
	
		return passwd.equals(passwdConf);
	}

	public static boolean validateFieldEvent(String name, String address,
			Date startDate, Date endDate) {
		return (!name.isEmpty() && !address.isEmpty() && (startDate.before(endDate)));
	}

	public static boolean validateFieldLogin(String email, String passwd) {
		return ((email != null && !email.isEmpty()) && passwd != null && !passwd.isEmpty()) ;
	}

}
