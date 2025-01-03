package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "SinhVien")
public class SinhVien {
	
	@Id
	@Column(name = "maSV")
	private String maSV;
	@Column(name = "Ten")
	private String Ten;
	@Column(name = "ngaySinh")
	private String ngaySinh;
	@Column(name = "gioiTinh")
	private String gioiTinh;
	@Column(name = "maLop")
	private String maLop;
	@Column(name = "nienKhoa")
	private String nienKhoa;
	@Column(name = "SDT")
	private String SDT;
	@Column(name = "noiSinh")
	private String noiSinh;
	
	public SinhVien() {
		
	}
	
	public void setMaSV(String maSV) {
		this.maSV = maSV;
	}
	public String getMaSV() {
		return maSV;
	}
	public void setTen(String ten) {
		this.Ten = ten;
	}
	public String getTen() {
		return Ten;
	}
	public void setNgaySinh(String ngaySinh) {
		this.ngaySinh = ngaySinh;
	}
	public String getNgaySinh() {
		return ngaySinh;
	}
	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}
	public String getGioiTinh() {
		return gioiTinh;
	}
	public void setMaLop(String maLop) {
		this.maLop = maLop;
	}
	public String getMaLop() {
		return maLop;
	}
	public void setNienKhoa(String nienKhoa) {
		this.nienKhoa = nienKhoa;
	}
	public String getNienKhoa() {
		return nienKhoa;
	}
	public void setSDT(String sDT) {
		this.SDT = sDT;
	}
	public String getSDT() {
		return SDT;
	}
	public void setNoiSinh(String noiSinh) {
		this.noiSinh = noiSinh;
	}
	public String getNoiSinh() {
		return noiSinh;
	}
	
}
