package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Diem")
public class Diem {

	@Id
	@Column(name = "maMonHoc")
	private String maMonHoc;
	@Column(name = "maSV")
	private String maSV;
	@Column(name = "namHoc")
	private String namHoc;
	@Column(name = "diemChuyenCan")
	private int diemChuyenCan;
	@Column(name = "diemGiuaKy")
	private float diemGiuaKy;
	@Column(name = "diemCuoiKy")
	private float diemCuoiKy;
	
	public Diem() {
		
	}
	
	public void setMaMonHoc(String maMonHoc) {
		this.maMonHoc = maMonHoc;
	}
	public String getMaMonHoc() {
		return maMonHoc;
	}
	public void setMaSV(String maSV) {
		this.maSV = maSV;
	}
	public String getMaSV() {
		return maSV;
	}
	public void setNamHoc(String namHoc) {
		this.namHoc = namHoc;
	}
	public String getNamHoc() {
		return namHoc;
	}
	public void setDiemChuyenCan(int diemChuyenCan) {
		this.diemChuyenCan = diemChuyenCan;
	}
	public int getDiemChuyenCan() {
		return diemChuyenCan;
	}
	public void setDiemGiuaKy(float diemGiuaKy) {
		this.diemGiuaKy = diemGiuaKy;
	}
	public float getDiemGiuaKy() {
		return diemGiuaKy;
	}
	public void setDiemCuoiKy(float diemCuoiKy) {
		this.diemCuoiKy = diemCuoiKy;
	}
	public float getDiemCuoiKy() {
		return diemCuoiKy;
	}
}
