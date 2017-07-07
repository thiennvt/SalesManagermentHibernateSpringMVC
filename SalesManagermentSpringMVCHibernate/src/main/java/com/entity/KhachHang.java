package com.entity;


import java.util.List;
import javax.persistence.*;

@Entity
@Table(name = "khachhang")
public class KhachHang {

    @Id
    @Column(name = "maKH")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer maKH;

    @Column(name = "fullName")
//    @Size(min=1,max=50)
    private String fullName;

    @Column(name = "email")
//    @Size(min=1,max=50)
    private String email;

    @Column(name = "phone")
//    @Size(min=1,max=20)
    private String phone;

    @Column(name = "diemtich")
//    @Size(min=1,max=10)
    private String diemtich;

    @Column(name = "xephang")
//    @Size(min=1,max=10)
    private String xephang;


    @OneToMany(mappedBy = "khachhang", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<HoaDon> listHD;


    public KhachHang() {
    }

    public Integer getMakh() {
        return maKH;
    }

    public void seMakh(int makh) {
        this.maKH = makh;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDiemtich() {
        return diemtich;
    }

    public void setDiemtich(String diemtich) {
        this.diemtich = diemtich;
    }

    public String getXephang() {
        return xephang;
    }

    public void setXephang(String xephang) {
        this.xephang = xephang;
    }

    public List<HoaDon> getListHD() {
        return listHD;
    }

    public void setListHD(List<HoaDon> listHD) {
        this.listHD = listHD;
    }
    

}

