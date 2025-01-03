package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Khoa")
public class Khoa {
	
	@Id
	@Column(name = "maKhoa")
	private String maKhoa;
	@Column(name = "tenKhoa")
	private String tenKhoa;
	
	public Khoa() {
		
	}
	
	public void setMaKhoa(String maKhoa) {
		this.maKhoa = maKhoa;
	}
	public String getMaKhoa() {
		return maKhoa;
	}
	public void setTenKhoa(String tenKhoa) {
		this.tenKhoa = tenKhoa;
	}
	public String getTenKhoa() {
		return tenKhoa;
	}
}
