package com.entity;

import javax.persistence.*;

@Entity
@Table(name = "hoadon")
public class HoaDon {


	@Id
	@Column(name = "mahd")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer mahd;

	@Column(name = "ngaylap")
//	@Size(min=1,max=50)
	private String ngayLap;

	@Column(name = "soluong")
//	@Size(min=1,max=50)
	private String soluong;

	@Column(name = "vat")
//	@Size(min=1,max=20)
	private String vat;

	@Column(name = "tongtien")
//	@Size(min=1,max=30)
	private String tongtien;

	@ManyToOne
	@JoinColumn(name = "masp")
	private SanPham sanpham;

	@ManyToOne
	@JoinColumn(name = "makh")
	private KhachHang khachhang;

	@ManyToOne
	@JoinColumn(name = "manv")
	private NhanVien nhanvien;

	public HoaDon() {
	}

	public Integer getMahd() {
		return mahd;
	}

	public void setMahd(Integer mahd) {
		this.mahd = mahd;
	}

	public String getNgayLap() {
		return ngayLap;
	}

	public void setNgayLap(String ngayLap) {
		this.ngayLap = ngayLap;
	}

	public String getSoluong() {
		return soluong;
	}

	public void setSoluong(String soluong) {
		this.soluong = soluong;
	}

	public String getVat() {
		return vat;
	}

	public void setVat(String vat) {
		this.vat = vat;
	}

	public String getTongtien() {
		return tongtien;
	}

	public void setTongtien(String tongtien) {
		this.tongtien = tongtien;
	}

	public SanPham getSanpham() {
		return sanpham;
	}

	public void setSanpham(SanPham sanpham) {
		this.sanpham = sanpham;
	}

	public KhachHang getKhachhang() {
		return khachhang;
	}

	public void setKhachhang(KhachHang khachhang) {
		this.khachhang = khachhang;
	}

	public NhanVien getNhanvien() {
		return nhanvien;
	}

	public void setNhanvien(NhanVien nhanvien) {
		this.nhanvien = nhanvien;
	}

	@Override
	public String toString() {
		return "HoaDon [mahd=" + mahd + ", ngayLap=" + ngayLap + ", soluong=" + soluong + ", vat=" + vat + ", tongtien="
				+ tongtien + ", sanpham=" + sanpham + ", khachhang=" + khachhang + ", nhanvien=" + nhanvien + "]";
	}

}
