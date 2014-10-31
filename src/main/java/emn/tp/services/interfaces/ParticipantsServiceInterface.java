package emn.tp.services.interfaces;

import emn.tp.bean.jpa.EventsEntity;

/**
 * Interface permettant de définir les services utilisés dans la gestion des participants.
 * 
 * @author Joris & Killian
 *
 */
public interface ParticipantsServiceInterface {
	
	/**
	 * Vérifie si le participant n'est pas déjà inscrit à l'évènement en question.
	 * @param mail 
	 * 			Adresse mail du participant
	 * @param event ID 
	 * 			ID d'un évènement
	 * @return booléen
	 */
	public boolean mailParticipatesToEvent(String mail, int eventID);
	
	/**
	 * Récupère un évènement dans la base de donnée via son ID.
	 * @param mail
	 * 			Adresse mail du participant
	 * @param firstName
	 * 			Prénom du participant
	 * @param name
	 * 			Nom du participant
	 * @param company
	 * 			Société du participant
	 * @param event
	 * 			Evènement auquel est inscrit le participant
	 */
	public void participate(String mail, String firstName, String name, String company, EventsEntity event);

}
