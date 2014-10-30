package emn.tp.test.services;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

import emn.tp.bean.jpa.UsersEntity;
import emn.tp.persistence.PersistenceServiceProvider;
import emn.tp.persistence.services.UsersPersistence;

public class UsersServiceTest {

	private static UsersServiceTest userServiceTest;
	private static UsersEntity user;
	private static List<UsersEntity> listeUser = new ArrayList<UsersEntity>();
	private static UsersPersistence userPersistence = PersistenceServiceProvider.getService(UsersPersistence.class);

	@Before
	public void setUp(){

		userServiceTest = new UsersServiceTest();
		
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

}
