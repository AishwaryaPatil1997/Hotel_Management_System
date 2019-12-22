package com.capgemini.hotelmanagement.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.capgemini.hotelmanagement.beans.HistoryBean;
import com.capgemini.hotelmanagement.beans.HotelBean;
import com.capgemini.hotelmanagement.beans.UserBean;

@Repository
public class HistoryDAOImpl implements HistoryDAO {

	@Autowired
	private EntityManagerFactory entityManagerFactory;

	EntityManager entityManager;

	@Override
	public boolean saveHistory(HistoryBean historyBean) {
		entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		boolean isAdded = false;
		try {
			entityTransaction.begin();
			// Getting hotel id and hotel name
			HotelBean hotelBean = entityManager.find(HotelBean.class, historyBean.getRoomId());
			historyBean.setHotelName(hotelBean.getHotelName());

			entityManager.persist(historyBean);
			entityTransaction.commit();
			isAdded = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return isAdded;
	}// End of saveHistory

	@Override
	public List<HistoryBean> showOrder(int userId) {
		entityManager = entityManagerFactory.createEntityManager();
		List<HistoryBean> historyList = null;
		try {
			historyList = (List<HistoryBean>) entityManager.find(HistoryBean.class, userId);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}// End of showOrder()

}// End of Class
