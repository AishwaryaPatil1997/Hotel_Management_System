package com.capgemini.hotelmanagement.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.capgemini.hotelmanagement.beans.UserBean;

@Repository
public class UserDAOImpl implements UserDAO {
	
	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;

	EntityManager entityManager;

	@Override
	public boolean userRegistration(UserBean userBean) {
		entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		boolean isRegister = false;

		try {
			transaction.begin();
			// Storing new user using persist
			entityManager.persist(userBean);
			transaction.commit();
			isRegister = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return isRegister;
	}// End of userRegistration()

	@Override
	public UserBean userLogin(String email, String password) {
		entityManager = entityManagerFactory.createEntityManager();
		UserBean userBean = null;

		String jpql = "FROM UserBean WHERE email =: email AND password =: password";
		Query query = entityManager.createQuery(jpql);
		// setting paramters
		query.setParameter("email", email);
		query.setParameter("password", password);

		try {
			userBean = (UserBean) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userBean;
	}// End of userLogin()

	@Override
	public boolean resetPassword(int userId, long phoneNumber, String password) {
		entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		boolean isUpdated = false;

		try {
			UserBean userBean = entityManager.find(UserBean.class, userId);
			// Checking old password

			String jpql = "UPDATE UserBean SET password =: password WHERE userId =: userId AND phoneNumber =: phoneNumber";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("userId", userId);
			query.setParameter("password", password);
			query.setParameter("phoneNumber", phoneNumber);
			entityTransaction.begin();
			int count = query.executeUpdate();
			if (count > 0) {
				isUpdated = true;
			}
			entityTransaction.commit();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return isUpdated;
	}// End of resetPassword()

	@Override
	public boolean updateProfile(UserBean userBean) {
		entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		UserBean existingUser = entityManager.find(UserBean.class, userBean.getUserId());
		boolean isUpdated = false;

		if (existingUser != null) {
			int userId = userBean.getUserId();
			if (userId > 0) {
				existingUser.setUserId(userId);
			}

			String userName = userBean.getUserName();
			if (userName != null) {
				existingUser.setUserName(userName);
			}

			String email = userBean.getEmail();
			if (email != null) {
				existingUser.setEmail(email);
			}

			long phoneNumber = userBean.getPhoneNumber();
			if (phoneNumber != 0l) {
				existingUser.setPhoneNumber(phoneNumber);
			}

			String nationality = userBean.getNationality();
			if (nationality != null) {
				existingUser.setNationality(nationality);
			}

			String password = userBean.getPassword();
			if (password != null) {
				existingUser.setPassword(password);
			}

			String gender = userBean.getGender();
			if (gender != null) {
				existingUser.setGender(gender);
			}

			String userType = userBean.getUserType();
			if (userType != null) {
				existingUser.setUserType(userType);
			}
		}

		try {
			entityTransaction.begin();
			entityManager.persist(existingUser);
			entityTransaction.commit();
			isUpdated = true;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return isUpdated;
	}// End of updateProfile()

	@Override
	public UserBean showProfile(int userId) {
		entityManager = entityManagerFactory.createEntityManager();
		UserBean userBean = null;
		try {
			String jpql = "FROM UserBean WHERE userId =: userId";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("userId", userId);
			// Getting single user Data
			userBean = (UserBean) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userBean;
	}//End of showProfile()

}//End of Class
