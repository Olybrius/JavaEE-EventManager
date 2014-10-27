/*
 * Created on 27 oct. 2014 ( Time 13:31:45 )
 * Generated by Telosys Tools Generator ( version 2.1.0 )
 */
package emn.tp.persistence.services;

import java.util.List;
import java.util.Map;

import emn.tp.bean.jpa.ParticipateEntity;

/**
 * Basic persistence operations for entity "Participate"
 * 
 * This Bean has a composite Primary Key : ParticipateEntityKey
 *
 * @author Telosys Tools Generator
 *
 */
public interface ParticipatePersistence {

	/**
	 * Deletes the given entity <br>
	 * Transactional operation ( begin transaction and commit )
	 * @param participate
	 * @return true if found and deleted, false if not found
	 */
	public boolean delete(ParticipateEntity participate) ;

	/**
	 * Deletes the entity by its Primary Key <br>
	 * Transactional operation ( begin transaction and commit )
	 * @param participantid
	 * @param eventid
	 * @return true if found and deleted, false if not found
	 */
	public boolean delete(Integer participantid, Integer eventid) ;

	/**
	 * Inserts the given entity and commit <br>
	 * Transactional operation ( begin transaction and commit )
	 * @param participate
	 */
	public void insert(ParticipateEntity participate) ;

	/**
	 * Loads the entity for the given Primary Key <br>
	 * @param participantid
	 * @param eventid
	 * @return the entity loaded (or null if not found)
	 */
	public ParticipateEntity load(Integer participantid, Integer eventid) ;

	/**
	 * Loads ALL the entities (use with caution)
	 * @return
	 */
	public List<ParticipateEntity> loadAll() ;

	/**
	 * Loads a list of entities using the given "named query" without parameter 
	 * @param queryName
	 * @return
	 */
	public List<ParticipateEntity> loadByNamedQuery(String queryName) ;

	/**
	 * Loads a list of entities using the given "named query" with parameters 
	 * @param queryName
	 * @param queryParameters
	 * @return
	 */
	public List<ParticipateEntity> loadByNamedQuery(String queryName, Map<String, Object> queryParameters) ;

	/**
	 * Saves (create or update) the given entity <br>
	 * Transactional operation ( begin transaction and commit )
	 * @param participate
	 * @return
	 */
	public ParticipateEntity save(ParticipateEntity participate) ;

	/**
	 * Search the entities matching the given search criteria
	 * @param criteria
	 * @return
	 */
	public List<ParticipateEntity> search( Map<String, Object> criteria ) ;

	/**
	 * Count all the occurrences
	 * @return
	 */
	public long countAll();
	
}
