/*
 * Created on 27 oct. 2014 ( Time 13:31:45 )
 * Generated by Telosys Tools Generator ( version 2.1.0 )
 */

package emn.tp.persistence.services.jpa;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import emn.tp.bean.jpa.ParticipantsEntity;
import emn.tp.persistence.commons.jpa.GenericJpaService;
import emn.tp.persistence.commons.jpa.JpaOperation;
import emn.tp.persistence.services.ParticipantsPersistence;

/**
 * JPA implementation for basic persistence operations ( entity "Participants" )
 * 
 * @author Telosys Tools Generator
 *
 */
public class ParticipantsPersistenceJPA extends GenericJpaService<ParticipantsEntity, Integer> implements ParticipantsPersistence {

	/**
	 * Constructor
	 */
	public ParticipantsPersistenceJPA() {
		super(ParticipantsEntity.class);
	}

	@Override
	public ParticipantsEntity load( Integer id ) {
		return super.load( id );
	}

	@Override
	public boolean delete( Integer id ) {
		return super.delete( id );
	}

	@Override
	public boolean delete(ParticipantsEntity entity) {
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
				Query query = em.createNamedQuery("ParticipantsEntity.countAll");
				return query.getSingleResult() ;
			}
		} ;
		// JPA operation execution 
		return (Long) execute(operation);
	}

}