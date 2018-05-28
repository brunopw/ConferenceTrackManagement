package br.com.brunopw.conferencetrackmanagement.model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Talk {

	private String name;
	private long minutes;
	private LocalTime time;
	private boolean showTime;

	public Talk(String name, long minutes) {
		this.name = name;
		this.minutes = minutes;
		this.showTime = true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getMinutes() {
		return minutes;
	}

	public void setMinutes(long minutes) {
		this.minutes = minutes;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public boolean showTime() {
		return showTime;
	}

	public void setShowTime(boolean showTime) {
		this.showTime = showTime;
	}

	@Override
	public String toString() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh:mma");
		StringBuilder sb = new StringBuilder();

		sb.append(this.getTime().format(dtf));
		sb.append(" ");
		sb.append(this.getName());

		if (this.showTime) {
			sb.append(" ");
			sb.append(this.getMinutes());
			sb.append("min");
		}

		return sb.toString();
	}

}
