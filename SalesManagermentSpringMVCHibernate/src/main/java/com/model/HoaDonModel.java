package com.model;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dao.BaseDao;
import com.entity.HoaDon;

@Repository
@Transactional
public class HoaDonModel extends BaseDao<HoaDon> {

	public HoaDonModel() {
		super(HoaDon.class);
	}

	@Override
	public boolean add(HoaDon instence) {
		return false;
	}

	@Override
	public boolean update(HoaDon instence) {
		return false;
	}

	@Override
	public boolean delete(Object key) {
		return false;
	}

	@Override
	public HoaDon findObject(Object key) {
		return null;
	}

	@Override
	public ArrayList<HoaDon> listObject() {
		session = sessionFactory.openSession();
		ArrayList<HoaDon> listHD = null;
		try {
			String hql = "from HoaDon";
			transaction = session.beginTransaction();
			listHD = (ArrayList<HoaDon>) session.createQuery(hql).list();
			transaction.commit();
			return listHD;
		} catch (Exception e) {
			transaction.rollback();
			e.printStackTrace();
			return null;
		} finally {
			session.close();
		}
	}
	public ArrayList<HoaDon>dsHoaDon(){
		try {
			sessionFactory.getCurrentSession().beginTransaction();
			return (ArrayList<HoaDon>) sessionFactory.getCurrentSession().createQuery("from hoadon").list();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	// tìm kiếm thông tin hóa đơn thông qua ngày được nhâp vào
	public ArrayList<HoaDon> search(HoaDon hoadon) {
		session = sessionFactory.openSession();
		ArrayList<HoaDon> listHD = new ArrayList<HoaDon>();
		try {
			String hql = "from HoaDon h where h.ngayLap like :ngay ";
			transaction = session.beginTransaction();
			listHD = (ArrayList<HoaDon>) session.createQuery(hql)
					.setParameter("ngay", "%" + hoadon.getNgayLap().trim() + "%").list();
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

}
