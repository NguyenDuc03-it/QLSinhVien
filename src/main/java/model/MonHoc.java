package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "MonHoc")
public class MonHoc {

	@Id
	@Column(name = "maMonHoc")
	private String maMonHoc;
	@Column(name = "tenMonHoc")
	private String tenMonHoc;
	@Column(name = "soTinChi")
	private int soTinChi;
	
	public MonHoc() {
		
	}
	
	public void setMaMonHoc(String maMonHoc) {
		this.maMonHoc = maMonHoc;
	}
	public String getMaMonHoc() {
		return maMonHoc;
	}
	public void setTenMonHoc(String tenMonHoc) {
		this.tenMonHoc = tenMonHoc;
	}
	public String getTenMonHoc() {
		return tenMonHoc;
	}
	public void setSoTinChi(int soTinChi) {
		this.soTinChi = soTinChi;
	}
	public int getSoTinChi() {
		return soTinChi;
	}
}
