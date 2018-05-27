package br.com.brunopw.conferencetrackmanagement.application;

import java.util.List;

import br.com.brunopw.conferencetrackmanagement.model.ConferenceTrackManagement;
import br.com.brunopw.conferencetrackmanagement.model.Talk;
import br.com.brunopw.conferencetrackmanagement.model.Track;

public class Application {

	public static void main(String[] args) {
		// Create the ConferenceTrackManagement
		ConferenceTrackManagement ctm = new ConferenceTrackManagement();
		
		// Load all talks from file
		List<Talk> allTalks = ctm.loadTalks();
		
		// Sort talks in descending order of duration
		allTalks = ctm.sortTalksInDescOrder(allTalks);
		
		
		// Print all talks
		//allTalks.forEach(System.out::println);
		
		// Planning all talks in tracks
		List<Track> allTracks = ctm.trackManagement(allTalks);
		
		// Print all tracks
		for(int i = 0; i < allTracks.size(); i++) {
			System.out.println("Track " + (i + 1) + ":");
			System.out.println(allTracks.get(i));
		}
		//allTracks.forEach(System.out::println);

	}

}
