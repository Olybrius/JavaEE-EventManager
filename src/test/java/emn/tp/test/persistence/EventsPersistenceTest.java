/*
 * Created on 21 oct. 2014 ( Time 08:51:15 )
 * Generated by Telosys Tools Generator ( version 2.1.0 )
 */
package emn.tp.test.persistence;


import emn.tp.bean.jpa.EventsEntity;
import emn.tp.mock.EventsEntityMock;
import emn.tp.persistence.PersistenceServiceProvider;
import emn.tp.persistence.services.EventsPersistence;

import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit test case for Events persistence service
 * 
 * @author Telosys Tools Generator
 *
 */
public class EventsPersistenceTest 
{
	@Test
	public void test1() {
		
		System.out.println("Test count ..." );
		
		EventsPersistence service = PersistenceServiceProvider.getService(EventsPersistence.class);
		System.out.println("CountAll = " + service.countAll() );
	}
	
	@Test
	public void test2() {
		
		System.out.println("Test Events persistence : delete + load ..." );
		
		EventsPersistence service = PersistenceServiceProvider.getService(EventsPersistence.class);
		
		EventsEntityMock mock = new EventsEntityMock();
		
		// TODO : set primary key values here 
		process( service, mock, 0  );
		// process( service, mock, ... );
	}

	private void process(EventsPersistence service, EventsEntityMock mock, Integer eventid ) {
		System.out.println("----- "  );
		System.out.println(" . load : " );
		EventsEntity entity = service.load( eventid );
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
			entity = mock.createInstance( eventid ) ;
			Assert.assertNotNull(entity);

			// This entity references the following entities : 
			// . Users
			/* Insert only if references are OK
			// Try to insert the new instance
			System.out.println(" . insert : " + entity );
			service.insert(entity);
			System.out.println("   inserted : " + entity );
			*/

			System.out.println(" . delete : " );
			boolean deleted = service.delete( eventid );
			System.out.println("   deleted = " + deleted);
		}		
	}
}
