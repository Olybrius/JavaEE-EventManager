/*
 * Created on 30 oct. 2014 ( Time 14:08:50 )
 * Generated by Telosys Tools Generator ( version 2.1.0 )
 */
package emn.tp.test.persistence;


import emn.tp.bean.jpa.ParticipantsEntity;
import emn.tp.mock.ParticipantsEntityMock;
import emn.tp.persistence.PersistenceServiceProvider;
import emn.tp.persistence.services.ParticipantsPersistence;

import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit test case for Participants persistence service
 * 
 * @author Telosys Tools Generator
 *
 */
public class ParticipantsPersistenceTest 
{
	@Test
	public void test1() {
		
		System.out.println("Test count ..." );
		
		ParticipantsPersistence service = PersistenceServiceProvider.getService(ParticipantsPersistence.class);
		System.out.println("CountAll = " + service.countAll() );
	}
	
	@Test
	public void test2() {
		
		System.out.println("Test Participants persistence : delete + load ..." );
		
		ParticipantsPersistence service = PersistenceServiceProvider.getService(ParticipantsPersistence.class);
		
		ParticipantsEntityMock mock = new ParticipantsEntityMock();
		
		// TODO : set primary key values here 
		process( service, mock, 0  );
		// process( service, mock, ... );
	}

	private void process(ParticipantsPersistence service, ParticipantsEntityMock mock, Integer id ) {
		System.out.println("----- "  );
		System.out.println(" . load : " );
		ParticipantsEntity entity = service.load( id );
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
			entity = mock.createInstance( id ) ;
			Assert.assertNotNull(entity);

			// This entity references the following entities : 
			// . Events
			/* Insert only if references are OK
			// Try to insert the new instance
			System.out.println(" . insert : " + entity );
			service.insert(entity);
			System.out.println("   inserted : " + entity );
			*/

			System.out.println(" . delete : " );
			boolean deleted = service.delete( id );
			System.out.println("   deleted = " + deleted);
		}		
	}
}
