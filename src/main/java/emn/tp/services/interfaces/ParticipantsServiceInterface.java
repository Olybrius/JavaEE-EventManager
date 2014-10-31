package emn.tp.services.interfaces;

import emn.tp.bean.jpa.EventsEntity;

/**
 * Interface permettant de d�finir les services utilis�s dans la gestion des participants.
 * 
 * @author Joris & Killian
 *
 */
public interface ParticipantsServiceInterface {
	
	/**
	 * V�rifie si le participant n'est pas d�j� inscrit � l'�v�nement en question.
	 * @param mail 
	 * 			Adresse mail du participant
	 * @param event ID 
	 * 			ID d'un �v�nement
	 * @return bool�en
	 */
	public boolean mailParticipatesToEvent(String mail, int eventID);
	
	/**
	 * R�cup�re un �v�nement dans la base de donn�e via son ID.
	 * @param mail
	 * 			Adresse mail du participant
	 * @param firstName
	 * 			Pr�nom du participant
	 * @param name
	 * 			Nom du participant
	 * @param company
	 * 			Soci�t� du participant
	 * @param event
	 * 			Ev�nement auquel est inscrit le participant
	 */
	public void participate(String mail, String firstName, String name, String company, EventsEntity event);

}
