/*
 * Created on 28 oct. 2014 ( Time 14:55:36 )
 * Generated by Telosys Tools Generator ( version 2.1.0 )
 */

package emn.tp.persistence.services.jpa;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import emn.tp.bean.jpa.UsersEntity;
import emn.tp.persistence.commons.jpa.GenericJpaService;
import emn.tp.persistence.commons.jpa.JpaOperation;
import emn.tp.persistence.services.UsersPersistence;

/**
 * JPA implementation for basic persistence operations ( entity "Users" )
 * 
 * @author Telosys Tools Generator
 *
 */
public class UsersPersistenceJPA extends GenericJpaService<UsersEntity, Integer> implements UsersPersistence {

	/**
	 * Constructor
	 */
	public UsersPersistenceJPA() {
		super(UsersEntity.class);
	}

	@Override
	public UsersEntity load( Integer id ) {
		return super.load( id );
	}

	@Override
	public boolean delete( Integer id ) {
		return super.delete( id );
	}

	@Override
	public boolean delete(UsersEntity entity) {
		if ( entity != null ) {
			return super.delete( entity.getId() );
		}
		return false ;
	}

	@Override
	public long countAll() {
		// JPA operation definition 
		JpaOperation operation = new JpaOperation() {
			@Override
			public Object exectue(EntityManager em) throws PersistenceException {
				Query query = em.createNamedQuery("UsersEntity.countAll");
				return query.getSingleResult() ;
			}
		} ;
		// JPA operation execution 
		return (Long) execute(operation);
	}

}
