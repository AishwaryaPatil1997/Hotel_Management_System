package com.capgemini.hotelmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;

@Configuration
public class HotelManagementConfig {
	@Bean
	public LocalEntityManagerFactoryBean getEntityManagerFactory() {
		LocalEntityManagerFactoryBean localEntityManagerFactoryBean = new LocalEntityManagerFactoryBean();
		localEntityManagerFactoryBean.setPersistenceUnitName("HotelManagementPersistence");
		return localEntityManagerFactoryBean;
	}
}

