package com.entity;

import java.util.List;
import javax.persistence.*;


@Entity
@Table(name = "sanpham")
public class SanPham {
	@Id
	@Column(name = "masp")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer masp;

	@Column(name = "tensp")
//	@Size(min=1,max=50)
	private String tensp;

	@Column(name = "gia")
//	@Size(min=1,max=50)
	private String gia;

	@Column(name = "khuyenmai")
//	@Size(min=1,max=50)
	private String khuyenmai;

	@OneToMany(mappedBy = "sanpham", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<HoaDon> listSP;

	public SanPham() {
	}

	public Integer getMasp() {
		return masp;
	}

	public void setMasp(int masp) {
		this.masp = masp;
	}

	public String getTensp() {
		return tensp;
	}

	public void setTensp(String tensp) {
		this.tensp = tensp;
	}

	public String getKhuyenmai() {
		return khuyenmai;
	}

	public void setKhuyenmai(String khuyenmai) {
		this.khuyenmai = khuyenmai;
	}

	public List<HoaDon> getListSP() {
		return listSP;
	}

	public void setListSP(List<HoaDon> listSP) {
		this.listSP = listSP;
	}

	public String getGia() {
		return gia;
	}

	public void setGia(String gia) {
		this.gia = gia;
	}

}
