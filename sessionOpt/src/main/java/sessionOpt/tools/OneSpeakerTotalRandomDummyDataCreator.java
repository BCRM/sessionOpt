package sessionOpt.tools;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import sessionOpt.entities.Session;

public class OneSpeakerTotalRandomDummyDataCreator extends
		TotalRandomDummyDataCreator {
	
	public OneSpeakerTotalRandomDummyDataCreator(Random rng) {
		super(rng);
	}
	
	private int SpeakerCount = 1;

	public List<Session> createDummySessions(List<Date> dates){
		ArrayList<Session> result = new ArrayList<Session>();
		for (int i = 0; i < numberOfSessions; i++){
			String sessionName = topics[r.nextInt(topics.length)] + " " + flavors[r.nextInt(flavors.length)];
			
			String speakerNames = "Speaker " + SpeakerCount++;
			result.add(new Session(sessionName, createRandomSpeakers(speakerNames),createDummyAudience(r.nextInt(9) * 10 + 10, false)));		}
		return result;
	}
}
