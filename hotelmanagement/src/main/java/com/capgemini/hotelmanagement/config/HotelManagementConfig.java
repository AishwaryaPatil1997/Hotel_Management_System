package com.capgemini.hotelmanagement.config;

import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;

public class HotelManagementConfig {
	public LocalEntityManagerFactoryBean getEntityManagerFactory() {
		LocalEntityManagerFactoryBean localEntityManagerFactoryBean = new LocalEntityManagerFactoryBean();
		localEntityManagerFactoryBean.setPersistenceUnitName("HotelManagementPersistenceUnit");
		return localEntityManagerFactoryBean;
	}

}
