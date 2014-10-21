/*
 * Created on 21 oct. 2014 ( Time 08:51:15 )
 * Generated by Telosys Tools Generator ( version 2.1.0 )
 */

package emn.tp.persistence.services.jpa;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import emn.tp.bean.jpa.EventsEntity;
import emn.tp.persistence.commons.jpa.GenericJpaService;
import emn.tp.persistence.commons.jpa.JpaOperation;
import emn.tp.persistence.services.EventsPersistence;

/**
 * JPA implementation for basic persistence operations ( entity "Events" )
 * 
 * @author Telosys Tools Generator
 *
 */
public class EventsPersistenceJPA extends GenericJpaService<EventsEntity, Integer> implements EventsPersistence {

	/**
	 * Constructor
	 */
	public EventsPersistenceJPA() {
		super(EventsEntity.class);
	}

	@Override
	public EventsEntity load( Integer eventid ) {
		return super.load( eventid );
	}

	@Override
	public boolean delete( Integer eventid ) {
		return super.delete( eventid );
	}

	@Override
	public boolean delete(EventsEntity entity) {
		if ( entity != null ) {
			return super.delete( entity.getEventid() );
		}
		return false ;
	}

	@Override
	public long countAll() {
		// JPA operation definition 
		JpaOperation operation = new JpaOperation() {
			@Override
			public Object exectue(EntityManager em) throws PersistenceException {
				Query query = em.createNamedQuery("EventsEntity.countAll");
				return query.getSingleResult() ;
			}
		} ;
		// JPA operation execution 
		return (Long) execute(operation);
	}

}
