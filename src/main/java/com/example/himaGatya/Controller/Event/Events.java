package com.example.himaGatya.Controller.Event;


import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
//import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="Events")

// import javax.xml.bind.annotation.XmlRootElement; を追加
//@XmlRootElement


public class Events {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private long id;

	@Column
	private String name;

	@Column
	private String summary;

	@Column
	private String image;

	@Column
	private String address;

	@Column
	private String place;

	@Column
	private int cost;

	@Column
	private Date start_on;

	@Column
	private Date end_on;

	@Column
	private Timestamp start_at;

	@Column
	private long manager_id;

	@Column
	private String event_url;

	@Column
	private String site_url;

	@Column
	private boolean is_deleted;


	public Events() {
		this.name = "no-name";
		this.summary = "no-data";
		this.address = "no-address";

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public Date getStart_on() {
		return start_on;
	}

	public void setStart_on(Date start_on) {
		this.start_on = start_on;
	}

	public Date getEnd_on() {
		return end_on;
	}

	public void setEnd_on(Date end_on) {
		this.end_on = end_on;
	}

	public Timestamp getStart_at() {
		return start_at;
	}

	public void setStart_at(Timestamp start_at) {
		this.start_at = start_at;
	}

	public long getManager_id() {
		return manager_id;
	}

	public void setManager_id(long manager_id) {
		this.manager_id = manager_id;
	}

	public String getEvent_url() {
		return event_url;
	}

	public void setEvent_url(String event_url) {
		this.event_url = event_url;
	}

	public String getSite_url() {
		return site_url;
	}

	public void setSite_url(String site_url) {
		this.site_url = site_url;
	}

	public boolean isIs_deleted() {
		return is_deleted;
	}

	public void setIs_deleted(boolean is_deleted) {
		this.is_deleted = is_deleted;
	}




}
