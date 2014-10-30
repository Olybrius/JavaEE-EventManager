package emn.tp.test.services;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import emn.tp.bean.jpa.UsersEntity;
import emn.tp.persistence.PersistenceServiceProvider;
import emn.tp.persistence.services.UsersPersistence;
import emn.tp.services.implementation.UsersService;

public class UsersServiceTest {

	private static UsersService userService;
	private static UsersEntity user;
	private static UsersPersistence userPersistence = PersistenceServiceProvider.getService(UsersPersistence.class);

	@Before
	public void setUp(){

		userService = new UsersService();
		
		//User creation
		user = new UsersEntity();
		user.setPseudo("Testeur1");
		user.setMail("userTest@Test.fr");
		user.setPassword("mdptest");
		userPersistence.insert(user);
	}
	
	@Test
	public void getUserTest(){
		assertTrue("Error: users are unequals", user.equals(userService.getUser(user.getMail(), user.getPassword())));
	}
	
	@Test
	public void mailExistsTest(){
		assertFalse("Error: mail does already exist in BDD", userService.mailExists(user.getMail()));
	}
	
	
	@After
	public void tearDown(){
		
		int id = user.getId(); 
		userPersistence.delete(id);
	}

}
