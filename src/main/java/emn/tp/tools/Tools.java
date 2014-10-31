package emn.tp.tools;

import java.util.Date;

/**
 * Classe permettant de tester et valider les diverses champs. 
 * 
 * @author Joris & Killian
 *
 */
public class Tools {

	/**
	 * Valide les champs lors de l'inscription d'un utilisateur.
	 * @param name
	 * 			Nom de l'utilisateur
	 * @param mail
	 * 			Mail de l'utilisateur
	 * @param passwd
	 * 			Mot de passe de l'utilisateur
	 * @param passwdConf
	 * 			Validation du mot de passe de l'utilisateur
	 * @return bool�en
	 * 			True si champs valide, False sinon
	 */
	public static boolean validateFieldRegister(String name, String mail, String passwd,
			String passwdConf) {
		
		return (!name.isEmpty() && (!mail.isEmpty() && mail.contains("@")) && !passwd.isEmpty() && !passwdConf.isEmpty());
	}

	/**
	 * Valide la similitude des deux mot de passe lors de l'inscription.
	 * @param passwd
	 * 			Mot de passe de l'utilisateur
	 * @param passwdConf
	 * 			Validation du mot de passe de l'utilisateur
	 * @return bool�en
	 * 			True si champs valide, False sinon
	 */
	public static boolean validatePassword(String passwd, String passwdConf) {
	
		return passwd.equals(passwdConf);
	}

	/**
	 * Valide les champs lors de la cr�ation d'un �v�nement.
	 * @param name
	 * 			Nom de l'�v�nement
	 * @param address
	 * 			Adresse de l'�v�nement
	 * @param startDate
	 * 			Date de d�but de l'�v�nement
	 * @param endDate
	 * 			Date de fin de l'�v�nement
	 * @return bool�en
	 * 			True si champs valide, False sinon
	 */
	public static boolean validateFieldEvent(String name, String address,
			Date startDate, Date endDate) {
		return (!name.isEmpty() && !address.isEmpty() && (startDate.before(endDate)));
	}

	/**
	 * Valide les champs lors de l'authentification de l'utilisateur.
	 * @param email
	 * 			Mail de l'utilisateur
	 * @param passwd
	 * 			Mot de passe de l'utilisateur
	 * @return bool�en
	 * 			True si champs valide, False sinon
	 */
	public static boolean validateFieldLogin(String email, String passwd) {
		return ((email != null && !email.isEmpty()) && passwd != null && !passwd.isEmpty()) ;
	}
	

	/**
	 * Valide les champs lors de l'inscription d'un participant � un �v�nement.
	 *  
	 * @param firstName
	 * 			Pr�nom du participant
	 * @param name
	 * 			Nom du participant
	 * @param mail
	 * 			Adresse mail du participant
	 * @param company
	 * 			Soci�t� du participant
	 * @param event
	 * 			Ev�nement auquel est inscrit le participant
	 * @return bool�en
	 * 			True si champs valide, False sinon
	 */
	public static boolean validateFieldParticipant(String firstName, String name, String mail, String company){
		return ((firstName != null && !firstName.isEmpty() && (name != null && !name.isEmpty()) 
				&& (mail != null && !mail.isEmpty()) && (company != null && !company.isEmpty())));
	}

}
