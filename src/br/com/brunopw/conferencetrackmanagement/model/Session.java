package br.com.brunopw.conferencetrackmanagement.model;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Session {

	private List<Talk> talks;
	private Shift shift;
	private LocalTime nextFreeTime;
	private LocalTime timeLimit;

	public Session(Shift shift, LocalTime startTime, LocalTime finishTime) {
		this.shift = shift;
		this.nextFreeTime = startTime;
		this.timeLimit = finishTime;
		this.talks = new ArrayList<Talk>();
	}

	public void addLunchTime() {
		Talk talk = new Talk("Lunch", 60);
		talk.setTime(LocalTime.NOON);
		talk.setShowTime(false);
		this.talks.add(talk);
	}
	
	public void addNetworkingEvent() {
		Talk talk = new Talk("Networking Event", 60);
		talk.setTime(nextFreeTime);
		talk.setShowTime(false);
		this.talks.add(talk);
	}

	public Shift getShift() {
		return shift;
	}

	public void setShift(Shift shift) {
		this.shift = shift;
	}

	public List<Talk> getTalks() {
		return talks;
	}

	public void setTalks(List<Talk> talks) {
		this.talks = talks;
	}

	public LocalTime getNextFreeTime() {
		return nextFreeTime;
	}

	public void setNextFreeTime(LocalTime nextFreeTime) {
		this.nextFreeTime = nextFreeTime;
	}

	public LocalTime getTimeLimit() {
		return timeLimit;
	}

	public void setTimeLimit(LocalTime timeLimit) {
		this.timeLimit = timeLimit;
	}

	public long getFreeTime() {
		Duration dur = Duration.between(this.nextFreeTime, this.timeLimit);
		return dur.toMinutes();
	}

	public boolean haveFreeTime(long minutes) {
		return this.nextFreeTime.plusMinutes(minutes - 1).isBefore(this.timeLimit);
	}

	public boolean addTalk(Talk talk) {
		if (haveFreeTime(talk.getMinutes())) {
			talk.setTime(this.nextFreeTime);
			this.talks.add(talk);
			this.nextFreeTime = this.nextFreeTime.plusMinutes(talk.getMinutes());
			//System.out.println(talk);
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		for (Talk t : this.talks) {
			sb.append(t.toString() + "\n");
		}
		return sb.toString();
	}

}
