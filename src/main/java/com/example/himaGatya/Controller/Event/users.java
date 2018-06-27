package com.example.entity;


import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
//import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name="users")

// import javax.xml.bind.annotation.XmlRootElement; を追加
//@XmlRootElement
public class users {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column
	private long id;

	@Column
	private String name;

	@Column
	private int sex;

	@Column
	private Timestamp final_rolled_at;

	@Column
	private Timestamp created_at;

	@Column
	private Timestamp update_at;

	@Column
	private Timestamp final_log_in_at;

	@Column
	private int manager_id;

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

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	public Timestamp getFinal_rolled_at() {
		return final_rolled_at;
	}

	public void setFinal_rolled_at(Timestamp final_rolled_at) {
		this.final_rolled_at = final_rolled_at;
	}

	public Timestamp getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Timestamp created_at) {
		this.created_at = created_at;
	}

	public Timestamp getUpdate_at() {
		return update_at;
	}

	public void setUpdate_at(Timestamp update_at) {
		this.update_at = update_at;
	}

	public Timestamp getFinal_log_in_at() {
		return final_log_in_at;
	}

	public void setFinal_log_in_at(Timestamp final_log_in_at) {
		this.final_log_in_at = final_log_in_at;
	}

	public int getManager_id() {
		return manager_id;
	}

	public void setManager_id(int manager_id) {
		this.manager_id = manager_id;
	}




}
