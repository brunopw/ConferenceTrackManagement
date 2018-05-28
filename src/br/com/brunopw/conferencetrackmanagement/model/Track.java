package br.com.brunopw.conferencetrackmanagement.model;

import java.time.LocalTime;

public class Track {
	
	private Session morningSession;
	private Session afternoonSession;
	
	public Track() {
		this.morningSession = new Session(Shift.MORNING,LocalTime.of(9, 0),LocalTime.of(12,0));
		this.afternoonSession = new Session(Shift.AFTERNOON,LocalTime.of(13, 0),LocalTime.of(17,0));
	}
	
	public boolean addTalk(Talk talk) {
		if(this.morningSession.getFreeTime() >= talk.getMinutes()) {
			return this.morningSession.addTalk(talk);
		} else if(this.afternoonSession.getFreeTime() >= talk.getMinutes()) {
			return this.afternoonSession.addTalk(talk);
		} else {
			return false;
		}
	}
	
	public void addSpecialEvents() {
		morningSession.addLunchTime();
		afternoonSession.addNetworkingEvent();
	}
	
	public long getFreeTime() {
		return this.morningSession.getFreeTime() + this.afternoonSession.getFreeTime();
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append(morningSession.toString());
		sb.append(afternoonSession.toString());
		
		return sb.toString();
	}
	
}
