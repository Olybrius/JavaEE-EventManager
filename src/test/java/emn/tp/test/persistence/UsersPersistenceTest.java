/*
 * Created on 28 oct. 2014 ( Time 14:00:21 )
 * Generated by Telosys Tools Generator ( version 2.1.0 )
 */
package emn.tp.test.persistence;


import emn.tp.bean.jpa.UsersEntity;
import emn.tp.mock.UsersEntityMock;
import emn.tp.persistence.PersistenceServiceProvider;
import emn.tp.persistence.services.UsersPersistence;

import org.junit.Assert;
import org.junit.Test;

/**
 * JUnit test case for Users persistence service
 * 
 * @author Telosys Tools Generator
 *
 */
public class UsersPersistenceTest 
{
	@Test
	public void test1() {
		
		System.out.println("Test count ..." );
		
		UsersPersistence service = PersistenceServiceProvider.getService(UsersPersistence.class);
		System.out.println("CountAll = " + service.countAll() );
	}
	
	@Test
	public void test2() {
		
		System.out.println("Test Users persistence : delete + load ..." );
		
		UsersPersistence service = PersistenceServiceProvider.getService(UsersPersistence.class);
		
		UsersEntityMock mock = new UsersEntityMock();
		
		// TODO : set primary key values here 
		process( service, mock, 0  );
		// process( service, mock, ... );
	}

	private void process(UsersPersistence service, UsersEntityMock mock, Integer id ) {
		System.out.println("----- "  );
		System.out.println(" . load : " );
		UsersEntity entity = service.load( id );
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

			// No reference : insert is possible 
			// Try to insert the new instance
			System.out.println(" . insert : " + entity );
			service.insert(entity);
			System.out.println("   inserted : " + entity );

			System.out.println(" . delete : " );
			boolean deleted = service.delete( id );
			System.out.println("   deleted = " + deleted);
			Assert.assertTrue(deleted) ;
		}		
	}
}
