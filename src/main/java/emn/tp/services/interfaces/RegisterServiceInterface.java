package emn.tp.services.interfaces;

public interface RegisterServiceInterface {
	
	public boolean validateField(String name, String mail, String passwd, String passwdConf);
	public boolean validatePassword(String passwd, String passwdConf);

}
