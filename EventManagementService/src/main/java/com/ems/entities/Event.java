package com.ems.entities;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
// @Table(name="Techm_Event") -For reference
public class Event {

	@Id
	private int eventId;
	// @Column( name="event_name",unique=true,nullable=false) -for reference
	private String name;
	private LocalDate date;
	private String time;
	private float price;
	private String imageUrl;

	/*
	 * @Transient private String imran; //Is not eligible for Persistance...Column
	 * does not get created
	 */
	@OneToOne
	@JoinColumn(name = "location_id")
	private Location location;

	@OneToMany(targetEntity = Session.class, mappedBy = "event")
	private List<Session> sessions = new ArrayList<>();

	public Event() {

	}

	public Event(int id, String name, LocalDate date, String time, float price, String imageUrl) {
		super();
		this.eventId = id;
		this.name = name;
		this.date = date;
		this.time = time;
		this.price = price;
		this.imageUrl = imageUrl;

	}
	

	@Override
	public String toString() {
		return "Event [eventId=" + eventId + ", name=" + name + ", date=" + date + ", time=" + time + ", price=" + price
				+ ", imageUrl=" + imageUrl + "]";
	}

	public List<Session> getSessions() {
		return sessions;
	}

	public void setSessions(List<Session> sessions) {
		this.sessions = sessions;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

}