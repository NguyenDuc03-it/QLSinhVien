package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Lop")
public class Lop {
	@Id
	@Column(name = "maLop")
	private String maLop;
	@Column(name = "tenLop")
	private String tenLop;
	@Column(name = "heDaoTao")
	private String heDaoTao;
	@Column(name = "namNhapHoc")
	private String namNhapHoc;
	@Column(name = "maKhoa")
	private String maKhoa;
	@Column(name = "siSo")
	private int siSo;
	
	public Lop() {
		
	}
	
	public void setHeDaoTao(String heDaoTao) {
		this.heDaoTao = heDaoTao;
	}
	public String getHeDaoTao() {
		return heDaoTao;
	}
	public void setTenLop(String tenLop) {
		this.tenLop = tenLop;
	}
	public String getTenLop() {
		return tenLop;
	}
	public void setMaLop(String maLop) {
		this.maLop = maLop;
	}
	public String getMaLop() {
		return maLop;
	}
	public void setNamNhapHoc(String namNhapHoc) {
		this.namNhapHoc = namNhapHoc;
	}
	public String getNamNhapHoc() {
		return namNhapHoc;
	}
	public void setMaKhoa(String maKhoa) {
		this.maKhoa = maKhoa;
	}
	public String getMaKhoa() {
		return maKhoa;
	}
	public void setSiSo(int siSo) {
		this.siSo = siSo;
	}
	public int getSiSo() {
		return siSo;
	}
}
