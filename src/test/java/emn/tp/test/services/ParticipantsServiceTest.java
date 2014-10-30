package emn.tp.test.services;

import java.util.ArrayList;
import java.util.List;

import emn.tp.bean.jpa.ParticipantsEntity;
import emn.tp.persistence.PersistenceServiceProvider;
import emn.tp.persistence.services.ParticipantsPersistence;

public class ParticipantsServiceTest {

	private static ParticipantsServiceTest participantsServiceTest;
	private static ParticipantsEntity participant;
	private static List<ParticipantsEntity> listeParticipant = new ArrayList<ParticipantsEntity>();
	private static ParticipantsPersistence participantPersistence = PersistenceServiceProvider.getService(ParticipantsPersistence.class);

	public void setUp(){
		participantsServiceTest = new ParticipantsServiceTest();
		
		//Participant creation
		participant = new ParticipantsEntity();
		participant.setName("Toto");
		participant.setFirstname("Un");
		participant.setMail("toto@un.fr");
		participant.setCompany("Lego");
		listeParticipant.add(participant);
		participantPersistence.insert(participant);

		participant = new ParticipantsEntity();
		participant.setName("Tutu");
		participant.setFirstname("deux");
		participant.setMail("tutu@deux.fr");
		participant.setCompany("PlayMobil");
		listeParticipant.add(participant);
		participantPersistence.insert(participant);

	}

}
