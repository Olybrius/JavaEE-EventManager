package emn.tp.services.interfaces;

import emn.tp.bean.jpa.UsersEntity;

/**
 * Interface permettant de définir les services utilisés dans la gestion des utilisateurs.
 * 
 * @author Joris & Killan
 *
 */
public interface UsersServiceInterface {
	
	/**
	 * Récupère l'utilisateur dans la base de donnée.
	 * @param email 
	 * 			Adresse mail de l'utilisateur
	 * @param passwd 
	 * 			Mot de passe de l'utilisateur
	 * @return UsersEntity
	 */
	public UsersEntity getUser(String email, String passwd);
	
	/**
	 * Vérifie si le mail renseigné par l'utilisateur existe déjà.
	 * @param mail 
	 * 			Adresse mail de l'utilisateur
	 * @return booléen
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
