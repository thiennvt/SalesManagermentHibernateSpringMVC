package com.entity;

import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "nhanvien")
public class NhanVien {

	@Id
	@Column(name = "manv")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer manv;

	@Column(name = "hoten")
//	@Size(min=1,max=50)
	private String hoten;

	@Column(name = "ngaysinh")
//	@Size(min=1,max=20)
	private String ngaysinh;

	@Column(name = "goitinh")
//	@Size(min=1,max=10)
	private String goitinh;

	@Column(name = "diachi")
//	@Size(min=1,max=100)
	private String diachi;

	@Column(name = "sdt")
//	@Size(min=1,max=20)
	private String sdt;

	@Column(name = "email")
//	@Size(min=1,max=50)
	private String email;

	@Column(name = "chucvu")
//	@Size(min=1,max=50)
	private String chucvu;

	@OneToMany(mappedBy = "nhanvien", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<HoaDon> listHD;

	public NhanVien() {
	}

	public Integer getManv() {
		return manv;
	}

	public void setManv(int manv) {
		this.manv = manv;
	}

	public String getHoten() {
		return hoten;
	}

	public void setHoten(String hoten) {
		this.hoten = hoten;
	}

	public String getNgaysinh() {
		return ngaysinh;
	}

	public void setNgaysinh(String ngaysinh) {
		this.ngaysinh = ngaysinh;
	}

	public String getGoitinh() {
		return goitinh;
	}

	public void setGoitinh(String goitinh) {
		this.goitinh = goitinh;
	}

	public String getDiachi() {
		return diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getChucvu() {
		return chucvu;
	}

	public void setChucvu(String chucvu) {
		this.chucvu = chucvu;
	}

	public List<HoaDon> getListHD() {
		return listHD;
	}

	public void setListHD(List<HoaDon> listHD) {
		this.listHD = listHD;
	}

}
