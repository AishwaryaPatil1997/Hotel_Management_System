package com.capgemini.hotelmanagement.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.hotelmanagement.beans.HotelBean;

@Repository
public class HotelDAOImpl implements HotelDAO {
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;
	
	EntityManager entityManager;

	@Override
	public List<HotelBean> showAllHotels() {
		List<HotelBean> hotelList = null;
		entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		String jpql = "from HotelBean";
		Query query = entityManager.createQuery(jpql);
		try {
			entityTransaction.begin();
			hotelList = query.getResultList();
			entityTransaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hotelList;
	}//End of showAllHotels()

	@Override
	public boolean updateHotelDetails(HotelBean hotelBean) {
		boolean isUpdated = false;
		entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		HotelBean hotelBean2 = entityManager.find(HotelBean.class, hotelBean.getHotelId());

		if (hotelBean2 != null) {
			String hotelName = hotelBean.getHotelName();
			if (hotelName != null && !hotelName.isEmpty()) {
				hotelBean2.setHotelName(hotelName);
			}

			String location = hotelBean.getLocation();
			if (location != null && !location.isEmpty()) {
				hotelBean2.setHotelName(hotelName);
			}

			try {
				entityTransaction.begin();
				entityManager.persist(hotelBean2);
				entityTransaction.commit();
				isUpdated = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
			entityManager.close();
		}
		return isUpdated;
	}//End of updateHotelDetails()

	@Override
	public HotelBean searchHotelDetails(String hotelName) {
		HotelBean hotelBean = null;
		entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		String jpql = "from HotelBean where hotelName like :hotelName";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("hotelName", hotelName);
		try {
			entityTransaction.begin();

			entityTransaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hotelBean;
	}//End of searchHotelDetails()

	@Override
	public HotelBean getHotelDetails(int hotelId) {

		entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		HotelBean hotelBean = null;
		try {
			entityTransaction.begin();
			hotelBean = entityManager.find(HotelBean.class, hotelId);
			entityTransaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		entityManager.close();
		return hotelBean;
	}//End of getHotelDetails()

	@Override
	public boolean addHotelDetails(HotelBean hotelBean) {
		boolean isHotelAdded = false;
		entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		try {
			entityTransaction.begin();
			entityManager.persist(hotelBean);
			entityTransaction.commit();
			isHotelAdded = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		entityManager.close();
		return isHotelAdded;
	}//End of addHotelDetails()

	@Override
	public boolean deleteHotelDetails(int hotelId) {
		boolean isHotelDeleted = false;
		entityManager = entityManagerFactory.createEntityManager();

		try {
			EntityTransaction entityTransaction = entityManager.getTransaction();
			entityTransaction.begin();
			HotelBean hotelBean = entityManager.find(HotelBean.class, hotelId);
			entityManager.remove(hotelBean);
			entityTransaction.commit();
			isHotelDeleted = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		entityManager.close();
		return isHotelDeleted;
	}//End of deleteHotelDetails()
	
}// End of Class
