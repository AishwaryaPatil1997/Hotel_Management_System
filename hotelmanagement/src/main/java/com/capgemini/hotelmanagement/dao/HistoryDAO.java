package com.capgemini.hotelmanagement.dao;

import java.util.List;

import com.capgemini.hotelmanagement.beans.HistoryBean;

public interface HistoryDAO {
	public boolean saveHistory(HistoryBean historyBean);
	
	public List<HistoryBean> showOrder(int userId);

}//End of interface
