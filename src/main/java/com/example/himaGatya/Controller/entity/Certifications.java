//package com.example.himaGatya.entity;
//
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.List;
//import java.util.Set;
//
//import javax.persistence.Column;
//import javax.persistence.ElementCollection;
//import javax.persistence.Entity;
//import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
//import javax.persistence.FetchType;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.Table;
//import javax.xml.bind.annotation.XmlRootElement;
//
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import com.example.himaGatya.User.Authority;
//
//@Entity
//@Table(name="certifications")
//
//// import javax.xml.bind.annotation.XmlRootElement; を追加
//@XmlRootElement
//
//
//public class Certifications implements UserDetails {
//	
//
//	private static final long serialVersionUID = 1L;
//	@Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column
//	private long id;
//
//	@Column
//	private String password;
//
//	@Column
//	private String mail;
//
//	@Column
//	private String salt;
//	
//    @ElementCollection(fetch = FetchType.EAGER)
//    @Enumerated(EnumType.STRING)
//    @Column(nullable = false)
//    private Set<Authority> authorities;
//
// 
//
//	public long getId() {
//		return id;
//	}
//
//	public void setId(long id) {
//		this.id = id;
//	}
//
//	@Override
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public String getMail() {
//		return mail;
//	}
//
//	public void setMail(String mail) {
//		this.mail = mail;
//	}
//
//	public String getSalt() {
//		return salt;
//	}
//
//	public void setSalt(String salt) {
//		this.salt = salt;
//	}
//
//	protected Certifications() {}
//	
//	public Certifications(String mail, String password) {
//		this.mail = mail;
//		this.password = password;
//	}
//	
//	@Override
//	public Collection<? extends GrantedAuthority> getAuthorities() {
//		// TODO 自動生成されたメソッド・スタブ
//		List<GrantedAuthority> authorities = new ArrayList<>();
//        for (Authority authority : this.authorities) {
//            authorities.add(new SimpleGrantedAuthority("ROLE_" + "USER"/*authority.toString()*/));
//        }
//        return authorities;
//	}
//
//	@Override
//	public String getUsername() {
//		// TODO 自動生成されたメソッド・スタブ
//		return mail;
//	}
//
//	
//	@Override
//	public boolean isAccountNonExpired() {
//		// TODO 自動生成されたメソッド・スタブ
//		return true;
//	}
//
//	@Override
//	public boolean isAccountNonLocked() {
//		// TODO 自動生成されたメソッド・スタブ
//		return true;
//	}
//
//	@Override
//	public boolean isCredentialsNonExpired() {
//		// TODO 自動生成されたメソッド・スタブ
//		return true;
//	}
//
//	@Override
//	public boolean isEnabled() {
//		// TODO 自動生成されたメソッド・スタブ
//		return false;
//	}
//	 
//
//	
//
//}
