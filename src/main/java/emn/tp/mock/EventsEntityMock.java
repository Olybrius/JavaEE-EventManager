
/*
 * Created on 29 oct. 2014 ( Time 20:28:06 )
 * Generated by Telosys Tools Generator ( version 2.1.0 )
 */
package emn.tp.mock;

import java.util.LinkedList;
import java.util.List;

import emn.tp.bean.jpa.EventsEntity;
import emn.tp.mock.tool.MockValues;

public class EventsEntityMock {

	private MockValues mockValues = new MockValues();
	
	/**
	 * Creates an instance with random Primary Key
	 * @return
	 */
	public EventsEntity createInstance() {
		// Primary Key values

		return createInstance( mockValues.nextInteger() );
	}
	
	/**
	 * Creates an instance with a specific Primary Key
	 * @param id1
	 * @return
	 */
	public EventsEntity createInstance( Integer id ) {
		EventsEntity entity = new EventsEntity();
		// Init Primary Key fields
		entity.setId( id) ;
		// Init Data fields
		entity.setName( mockValues.nextString(30) ) ; // java.lang.String 
		entity.setStartdate( mockValues.nextDate() ) ; // java.util.Date 
		entity.setEnddate( mockValues.nextDate() ) ; // java.util.Date 
		entity.setAddress( mockValues.nextString(30) ) ; // java.lang.String 
		entity.setPublished( mockValues.nextShort() ) ; // java.lang.Short 
		// Init Link fields (if any)
		// setUsers( TODO ) ; // Users 
		// setListOfParticipants( TODO ) ; // List<Participants> 
		return entity ;
	}
	
	/**
	 * Creates a list of instances
	 * @param count number of instances to be created
	 * @return
	 */
	public List<EventsEntity> createList(int count) {
		List<EventsEntity> list = new LinkedList<EventsEntity>();		
		for ( int i = 1 ; i <= count ; i++ ) {
			list.add( createInstance() );
		}
		return list;
	}
}
