package com.capgemini.hotelmanagement.service;

import java.util.List;

import com.capgemini.hotelmanagement.beans.HistoryBean;

public interface HistoryService {
public boolean saveHistory(HistoryBean historyBean);
	
	public List<HistoryBean> showOrder(int userId);
}//End of interface
