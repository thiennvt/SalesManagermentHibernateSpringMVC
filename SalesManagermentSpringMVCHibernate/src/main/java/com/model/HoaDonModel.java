package com.model;

import java.util.ArrayList;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.entity.HoaDon;

@Repository
@Transactional
public class HoaDonModel {
	@Autowired
	private static SessionFactory sessionFactory;
	private static Session session;
	private static Transaction transaction;
	
	//lấy ra danh sách hóa đơn trong db
	public static ArrayList<HoaDon> dsHoaDon() {
		session = sessionFactory.openSession();
		ArrayList<HoaDon>listHD = new ArrayList<HoaDon>();
        try {
            String hql = "from hoadon";
            transaction = session.beginTransaction();
            listHD = (ArrayList<HoaDon>) session.createQuery(hql).list();
            transaction.commit();
            return listHD;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
	}
	public static ArrayList<HoaDon>listHoaDon(){
		try {
			String hql = "from HoaDon";
			sessionFactory.getCurrentSession().beginTransaction();
			return (ArrayList<HoaDon>) sessionFactory.getCurrentSession().createQuery(hql).list();
					
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//tìm kiếm thông tin hóa đơn thông qua ngày được nhâp vào 
	public static ArrayList<HoaDon>search(HoaDon hoadon){
		session = sessionFactory.openSession();
		ArrayList<HoaDon>listHD = new ArrayList<HoaDon>();
        try {
        	String hql = "from HoaDon h where h.ngayLap like :ngay ";
            transaction = session.beginTransaction();
            listHD = (ArrayList<HoaDon>) session.createQuery(hql)
            									.setParameter("ngay", "%"+hoadon.getNgayLap().trim()+"%")
            									.list();
            transaction.commit();
            return listHD;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
	}
	
	//main function
	public static void main(String[] agrs) {
		ArrayList<HoaDon>list = dsHoaDon();
		System.out.println("danh sách hóa đơn: ");
		for(HoaDon hd  : list) {
			System.out.println(hd);
		}
	}
}
