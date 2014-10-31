package emn.tp.services.interfaces;

import emn.tp.bean.jpa.UsersEntity;

/**
 * Interface permettant de d�finir les services utilis�s dans la gestion des utilisateurs.
 * 
 * @author Joris & Killan
 *
 */
public interface UsersServiceInterface {
	
	/**
	 * R�cup�re l'utilisateur dans la base de donn�e.
	 * @param email 
	 * 			Adresse mail de l'utilisateur
	 * @param passwd 
	 * 			Mot de passe de l'utilisateur
	 * @return UsersEntity
	 */
	public UsersEntity getUser(String email, String passwd);
	
	/**
	 * V�rifie si le mail renseign� par l'utilisateur existe d�j�.
	 * @param mail 
	 * 			Adresse mail de l'utilisateur
	 * @return bool�en
	 */
	public boolean mailExists(String mail);
	
	/**
	 * Permet d'inscrire un utilisateur.
	 * 
	 * @param pseudo
	 * 			Pseudo de l'utilisateur
	 * @param mail 
	 * 			Adresse mail de l'utilisateur
	 * @param password
	 * 			Mot de passe de l'utilisateur
	 */
	public void register(String pseudo, String mail, String password);

}
