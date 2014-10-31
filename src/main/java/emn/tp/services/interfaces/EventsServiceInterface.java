package emn.tp.services.interfaces;

import java.util.Date;
import java.util.List;

import emn.tp.bean.jpa.EventsEntity;
import emn.tp.bean.jpa.UsersEntity;

/**
 * Interface permettant de d�finir les services utilis�s dans la gestion des �v�nements.
 * 
 * @author Joris & Killian
 *
 */

public interface EventsServiceInterface {


	/**
	 * R�cup�re un �v�nement dans la base de donn�e via son ID.
	 * @param userID
	 * 			ID de l'utilisateur
	 * @return List<EventsEntity>
	 */
	public List<EventsEntity> getEventsByUser(int userID);
	
	/**
	 * Cr�er un �v�nement en base de donn�e avec les informations pass�s en param�tre.
	 * @param name
	 * 			Nom de l'�v�nement
	 * @param address
	 * 			Adresse de l'�v�nement
	 * @param startDate
	 * 			Date de d�but de l'�v�nement
	 * @param endDate
	 * 			Date de fin de l'�v�nement
	 * @param publish
	 * 			Information sur la publication de l'�v�nement
	 * @param user
	 * 			Utilisateur ayant cr�� l'�v�nement
	 */
	public void createEvent(String name, String address, Date startDate, Date endDate, short publish,  UsersEntity user);
	
	/**
	 * Recherche en base de donn�e si l'�v�nement correspondant � l'id pass� en param�tre existe.
	 * @param id
	 * 			ID de l'�v�nement
	 * @return bool�en
	 */
	public boolean eventExists(int id);
	
	/**
	 * V�rifier si le'utilisateur est le cr�ateur de l'�v�nement.
	 * @param userID
	 * 			ID de l'utilisateur
	 * @param eventID
	 * 			ID de l'�v�nement
	 * @return bool�en
	 */
	public boolean userCreatedEvent(int userID, int eventID);
	
	/**
	 * Publie un �v�nement
	 * @param eventID
	 * 			ID de l'�v�nement
	 */
	public void publishEvent(int eventID);
	
	/**
	 * Retourne la liste des �v�nements publi�s.
	 *
	 * @return List<EventsEntity>
	 */
	public List<EventsEntity> getPublishedEvents();
	
	/**
	 * Retourne un �v�nement.
	 * @param eventID
	 * 			ID de l'�v�nement
	 * @return EventsEntity
	 */
	public EventsEntity getEventById(int eventID);

}
