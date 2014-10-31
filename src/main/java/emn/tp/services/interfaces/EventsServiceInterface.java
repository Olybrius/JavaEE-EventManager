package emn.tp.services.interfaces;

import java.util.Date;
import java.util.List;

import emn.tp.bean.jpa.EventsEntity;
import emn.tp.bean.jpa.UsersEntity;

/**
 * Interface permettant de définir les services utilisés dans la gestion des évènements.
 * 
 * @author Joris & Killian
 *
 */

public interface EventsServiceInterface {


	/**
	 * Récupère un évènement dans la base de donnée via son ID.
	 * @param userID
	 * 			ID de l'utilisateur
	 * @return List<EventsEntity>
	 */
	public List<EventsEntity> getEventsByUser(int userID);
	
	/**
	 * Créer un évènement en base de donnée avec les informations passés en paramètre.
	 * @param name
	 * 			Nom de l'évènement
	 * @param address
	 * 			Adresse de l'évènement
	 * @param startDate
	 * 			Date de début de l'évènement
	 * @param endDate
	 * 			Date de fin de l'évènement
	 * @param publish
	 * 			Information sur la publication de l'évènement
	 * @param user
	 * 			Utilisateur ayant créé l'évènement
	 */
	public void createEvent(String name, String address, Date startDate, Date endDate, short publish,  UsersEntity user);
	
	/**
	 * Recherche en base de donnée si l'évènement correspondant à l'id passé en paramètre existe.
	 * @param id
	 * 			ID de l'évènement
	 * @return booléen
	 */
	public boolean eventExists(int id);
	
	/**
	 * Vérifier si le'utilisateur est le créateur de l'évènement.
	 * @param userID
	 * 			ID de l'utilisateur
	 * @param eventID
	 * 			ID de l'évènement
	 * @return booléen
	 */
	public boolean userCreatedEvent(int userID, int eventID);
	
	/**
	 * Publie un évènement
	 * @param eventID
	 * 			ID de l'évènement
	 */
	public void publishEvent(int eventID);
	
	/**
	 * Retourne la liste des évènements publiés.
	 *
	 * @return List<EventsEntity>
	 */
	public List<EventsEntity> getPublishedEvents();
	
	/**
	 * Retourne un évènement.
	 * @param eventID
	 * 			ID de l'évènement
	 * @return EventsEntity
	 */
	public EventsEntity getEventById(int eventID);

}
