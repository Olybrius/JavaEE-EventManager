package emn.tp.test.services;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import emn.tp.bean.jpa.UsersEntity;
import emn.tp.persistence.PersistenceServiceProvider;
import emn.tp.persistence.services.UsersPersistence;
import emn.tp.services.implementation.UsersService;

public class UsersServiceTest {

	private static UsersService userService;
	private static UsersEntity user;
	private static List<UsersEntity> listeUser = new ArrayList<UsersEntity>();
	private static UsersPersistence userPersistence = PersistenceServiceProvider.getService(UsersPersistence.class);

	@Before
	public void setUp(){

		userService = new UsersService();
		
		//Users creation
		user = new UsersEntity();
		user.setPseudo("Testeur1");
		user.setMail("userTest@Test.fr");
		user.setPassword("mdptest");
		listeUser.add(user);
		userPersistence.insert(user);

		user = new UsersEntity();
		user.setPseudo("Testeur2");
		user.setMail("userTest2@Test.fr");
		user.setPassword("mdptest2");
		listeUser.add(user);
		userPersistence.insert(user);
	}
	
	@Test
	public void getUserTest(){
		assertTrue("Ok", user.equals(userService.getUser(user.getMail(), user.getPassword())));
		//assertNull(user);
	}
	
	@After
	public void tearDown(){
		
		int id = user.getId();
		userPersistence.delete(id);
	}

}
