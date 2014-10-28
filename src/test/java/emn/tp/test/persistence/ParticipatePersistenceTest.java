/*
 * Created on 28 oct. 2014 ( Time 14:55:36 )
 * Generated by Telosys Tools Generator ( version 2.1.0 )
 */
package emn.tp.test.persistence;


import emn.tp.bean.jpa.ParticipateEntity;
import emn.tp.mock.ParticipateEntityMock;
import emn.tp.persistence.PersistenceServiceProvider;
import emn.tp.persistence.services.ParticipatePersistence;

import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit test case for Participate persistence service
 * 
 * @author Telosys Tools Generator
 *
 */
public class ParticipatePersistenceTest 
{
	@Test
	public void test1() {
		
		System.out.println("Test count ..." );
		
		ParticipatePersistence service = PersistenceServiceProvider.getService(ParticipatePersistence.class);
		System.out.println("CountAll = " + service.countAll() );
	}
	
	@Test
	public void test2() {
		
		System.out.println("Test Participate persistence : delete + load ..." );
		
		ParticipatePersistence service = PersistenceServiceProvider.getService(ParticipatePersistence.class);
		
		ParticipateEntityMock mock = new ParticipateEntityMock();
		
		// TODO : set primary key values here 
		process( service, mock, 0 , 0  );
		// process( service, mock, ... );
	}

	private void process(ParticipatePersistence service, ParticipateEntityMock mock, Integer participantid, Integer eventid ) {
		System.out.println("----- "  );
		System.out.println(" . load : " );
		ParticipateEntity entity = service.load( participantid, eventid );
		if ( entity != null ) {
			// Found 
			System.out.println("   FOUND : " + entity );
			
			// Save (update) with the same values to avoid database integrity errors  
			System.out.println(" . save : " + entity );
			service.save(entity);
			System.out.println("   saved : " + entity );
		}
		else {
			// Not found 
			System.out.println("   NOT FOUND" );
			// Create a new instance 
			entity = mock.createInstance( participantid, eventid ) ;
			Assert.assertNotNull(entity);

			/* NB : this entity is a "Join Table" 
			System.out.println(" . insert : " + entity );
			service.insert(entity);
			System.out.println("   inserted : " + entity );
			*/

			System.out.println(" . delete : " );
			boolean deleted = service.delete( participantid, eventid );
			System.out.println("   deleted = " + deleted);
		}		
	}
}
