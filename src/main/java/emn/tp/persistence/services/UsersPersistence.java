/*
 * Created on 28 oct. 2014 ( Time 14:55:36 )
 * Generated by Telosys Tools Generator ( version 2.1.0 )
 */
package emn.tp.persistence.services;

import java.util.List;
import java.util.Map;

import emn.tp.bean.jpa.UsersEntity;

/**
 * Basic persistence operations for entity "Users"
 * 
 * This Bean has a basic Primary Key : Integer
 *
 * @author Telosys Tools Generator
 *
 */
public interface UsersPersistence {

	/**
	 * Deletes the given entity <br>
	 * Transactional operation ( begin transaction and commit )
	 * @param users
	 * @return true if found and deleted, false if not found
	 */
	public boolean delete(UsersEntity users) ;

	/**
	 * Deletes the entity by its Primary Key <br>
	 * Transactional operation ( begin transaction and commit )
	 * @param id
	 * @return true if found and deleted, false if not found
	 */
	public boolean delete(Integer id) ;

	/**
	 * Inserts the given entity and commit <br>
	 * Transactional operation ( begin transaction and commit )
	 * @param users
	 */
	public void insert(UsersEntity users) ;

	/**
	 * Loads the entity for the given Primary Key <br>
	 * @param id
	 * @return the entity loaded (or null if not found)
	 */
	public UsersEntity load(Integer id) ;

	/**
	 * Loads ALL the entities (use with caution)
	 * @return
	 */
	public List<UsersEntity> loadAll() ;

	/**
	 * Loads a list of entities using the given "named query" without parameter 
	 * @param queryName
	 * @return
	 */
	public List<UsersEntity> loadByNamedQuery(String queryName) ;

	/**
	 * Loads a list of entities using the given "named query" with parameters 
	 * @param queryName
	 * @param queryParameters
	 * @return
	 */
	public List<UsersEntity> loadByNamedQuery(String queryName, Map<String, Object> queryParameters) ;

	/**
	 * Saves (create or update) the given entity <br>
	 * Transactional operation ( begin transaction and commit )
	 * @param users
	 * @return
	 */
	public UsersEntity save(UsersEntity users) ;

	/**
	 * Search the entities matching the given search criteria
	 * @param criteria
	 * @return
	 */
	public List<UsersEntity> search( Map<String, Object> criteria ) ;

	/**
	 * Count all the occurrences
	 * @return
	 */
	public long countAll();
	
}
