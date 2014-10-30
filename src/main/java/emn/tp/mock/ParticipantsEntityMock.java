
/*
 * Created on 30 oct. 2014 ( Time 13:56:37 )
 * Generated by Telosys Tools Generator ( version 2.1.0 )
 */
package emn.tp.mock;

import java.util.LinkedList;
import java.util.List;

import emn.tp.bean.jpa.ParticipantsEntity;
import emn.tp.mock.tool.MockValues;

public class ParticipantsEntityMock {

	private MockValues mockValues = new MockValues();
	
	/**
	 * Creates an instance with random Primary Key
	 * @return
	 */
	public ParticipantsEntity createInstance() {
		// Primary Key values

		return createInstance( mockValues.nextInteger() );
	}
	
	/**
	 * Creates an instance with a specific Primary Key
	 * @param id1
	 * @return
	 */
	public ParticipantsEntity createInstance( Integer id ) {
		ParticipantsEntity entity = new ParticipantsEntity();
		// Init Primary Key fields
		entity.setId( id) ;
		// Init Data fields
		entity.setMail( mockValues.nextString(30) ) ; // java.lang.String 
		entity.setName( mockValues.nextString(30) ) ; // java.lang.String 
		entity.setFirstname( mockValues.nextString(30) ) ; // java.lang.String 
		entity.setCompany( mockValues.nextString(30) ) ; // java.lang.String 
		// Init Link fields (if any)
		// setEvents( TODO ) ; // Events 
		return entity ;
	}
	
	/**
	 * Creates a list of instances
	 * @param count number of instances to be created
	 * @return
	 */
	public List<ParticipantsEntity> createList(int count) {
		List<ParticipantsEntity> list = new LinkedList<ParticipantsEntity>();		
		for ( int i = 1 ; i <= count ; i++ ) {
			list.add( createInstance() );
		}
		return list;
	}
}
