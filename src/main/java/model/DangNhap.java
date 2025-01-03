package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name= "Login") // a'nh xa obj DangNhap vao bang Login
public class DangNhap {
	@Id
	@Column(name = "username")
	private String username;
	@Column(name = "password")
	private String password;
	public DangNhap() {
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
}
