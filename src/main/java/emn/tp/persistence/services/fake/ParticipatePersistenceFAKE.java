/*
 * Created on 28 oct. 2014 ( Time 14:55:36 )
 * Generated by Telosys Tools Generator ( version 2.1.0 )
 */
package emn.tp.persistence.services.fake;

import java.util.List;
import java.util.Map;

import emn.tp.bean.jpa.ParticipateEntity;
import emn.tp.persistence.commons.fake.GenericFakeService;
import emn.tp.persistence.services.ParticipatePersistence;

/**
 * Fake persistence service implementation ( entity "Participate" )
 *
 * @author Telosys Tools Generator
 */
public class ParticipatePersistenceFAKE extends GenericFakeService<ParticipateEntity> implements ParticipatePersistence {

	public ParticipatePersistenceFAKE () {
		super(ParticipateEntity.class);
	}
	
	protected ParticipateEntity buildEntity(int index) {
		ParticipateEntity entity = new ParticipateEntity();
		// Init fields with mock values
		entity.setParticipantid( nextInteger() ) ;
		entity.setEventid( nextInteger() ) ;
		return entity ;
	}
	
	
	public boolean delete(ParticipateEntity entity) {
		log("delete ( ParticipateEntity : " + entity + ")" ) ;
		return true;
	}

	public boolean delete( Integer participantid, Integer eventid ) {
		log("delete ( PK )") ;
		return true;
	}

	public void insert(ParticipateEntity entity) {
		log("insert ( ParticipateEntity : " + entity + ")" ) ;
	}

	public ParticipateEntity load( Integer participantid, Integer eventid ) {
		log("load ( PK )") ;
		return buildEntity(1) ; 
	}

	public List<ParticipateEntity> loadAll() {
		log("loadAll()") ;
		return buildList(40) ;
	}

	public List<ParticipateEntity> loadByNamedQuery(String queryName) {
		log("loadByNamedQuery ( '" + queryName + "' )") ;
		return buildList(20) ;
	}

	public List<ParticipateEntity> loadByNamedQuery(String queryName, Map<String, Object> queryParameters) {
		log("loadByNamedQuery ( '" + queryName + "', parameters )") ;
		return buildList(10) ;
	}

	public ParticipateEntity save(ParticipateEntity entity) {
		log("insert ( ParticipateEntity : " + entity + ")" ) ;
		return entity;
	}

	public List<ParticipateEntity> search(Map<String, Object> criteria) {
		log("search (criteria)" ) ;
		return buildList(15) ;
	}

	@Override
	public long countAll() {
		return 0 ;
	}

}
