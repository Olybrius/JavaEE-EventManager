/*
 * Created on 21 oct. 2014 ( Time 08:51:15 )
 * Generated by Telosys Tools Generator ( version 2.1.0 )
 */

package emn.tp.persistence.services.jpa;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

import emn.tp.bean.jpa.ParticipantEntity;
import emn.tp.persistence.commons.jpa.GenericJpaService;
import emn.tp.persistence.commons.jpa.JpaOperation;
import emn.tp.persistence.services.ParticipantPersistence;

/**
 * JPA implementation for basic persistence operations ( entity "Participant" )
 * 
 * @author Telosys Tools Generator
 *
 */
public class ParticipantPersistenceJPA extends GenericJpaService<ParticipantEntity, String> implements ParticipantPersistence {

	/**
	 * Constructor
	 */
	public ParticipantPersistenceJPA() {
		super(ParticipantEntity.class);
	}

	@Override
	public ParticipantEntity load( String mail ) {
		return super.load( mail );
	}

	@Override
	public boolean delete( String mail ) {
		return super.delete( mail );
	}

	@Override
	public boolean delete(ParticipantEntity entity) {
		if ( entity != null ) {
			return super.delete( entity.getMail() );
		}
		return false ;
	}

	@Override
	public long countAll() {
		// JPA operation definition 
		JpaOperation operation = new JpaOperation() {
			@Override
			public Object exectue(EntityManager em) throws PersistenceException {
				Query query = em.createNamedQuery("ParticipantEntity.countAll");
				return query.getSingleResult() ;
			}
		} ;
		// JPA operation execution 
		return (Long) execute(operation);
	}

}
