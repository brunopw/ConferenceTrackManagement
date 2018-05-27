package br.com.brunopw.conferencetrackmanagement.model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import br.com.brunopw.conferencetrackmanagement.utils.FileUtils;

public class ConferenceTrackManagement {
	
	public List<Talk> loadTalks() {
		FileUtils fu = new FileUtils();
		List<Talk> allTalks = new ArrayList<Talk>();

		// Read File and create list of all talks
		try {
			List<String> lines = fu.readListInFile("input.txt");
			// allTalks.forEach(System.out::println);
			lines.forEach(r -> allTalks.add(createTalk(r)));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return allTalks;
	}

	public List<Talk> sortTalksInDescOrder(List<Talk> talks) {
		/*talks = talks.stream().sorted(Comparator.comparing(Talk::getMinutes))
				.collect(Collectors.toList());*/
		talks.sort(Comparator.comparingLong(Talk::getMinutes));
		return talks;
	}

	public Talk createTalk(String line) {
		Talk talk;
		long minutes;

		try {
			minutes = Long.parseLong(line.substring(line.length() - 5, line.length() - 3));
			String name = line.substring(0, line.length() - 6);
			talk = new Talk(name, minutes);
		} catch (NumberFormatException e) {
			talk = new Talk(line, 5);
			talk.setShowTime(false);
		} catch (Exception e) {
			System.out.println("Error in line: " + line);
			return null;
		}

		return talk;
	}

	public List<Track> trackManagement(List<Talk> allTalks) {
		// Create list of all Tracks
		List<Track> allTracks = new ArrayList<Track>();
		
		while(!allTalks.isEmpty()) {
			Track track = new Track();
			boolean trackFree = true;
			
			allTracks.add(track);
			
			while(trackFree && !allTalks.isEmpty()) {
				if(track.addTalk(allTalks.get(allTalks.size()-1))) {
					allTalks.remove(allTalks.size()-1);
				} else {
					trackFree = false;
				}
			}
		}
		
		allTracks = addSpecialEvents(allTracks);
		
		return allTracks;
	}

	private List<Track> addSpecialEvents(List<Track> allTracks) {
		allTracks.forEach(t -> t.addSpecialEvents());

		return allTracks;
	}

}
