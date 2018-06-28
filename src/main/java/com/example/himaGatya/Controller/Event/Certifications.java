package com.example.himaGatya.Controller.Event;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
//import javax.xml.bind.annotation.XmlRootElement;

import lombok.Getter;
import lombok.Setter;


// import javax.xml.bind.annotation.XmlRootElement; を追加
//@XmlRootElement

@Getter
@Setter
@Entity
@Table(name="certifications")
public class Certifications {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id" , nullable = false , precision = 20 )
	private long id;

	@Column(name = "password" , nullable = true , length = 32)
	private String password;

	@Column(name = "mail" , nullable = true , length = 128 )
	private String mail;

	@Column(name = "salt" , nullable = true , length = 32)
	private String salt;

	@Column
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}





}
