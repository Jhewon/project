package com.interyard.order.service;


import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;
import com.interyard.order.dao.OrderDAO;
import com.interyard.order.vo.OrderVO;

/**
 * 주문 상태 변경 처리를 진행하는 메서드를 가지고 있는 클래스
 */
public class OrderStateUpdateService implements Service {
	private OrderDAO dao;
	
	/**
	 * 주문 상태 변경 처리를 진행하는 메서드
	 */
	@Override
	public Integer service(Object obj) throws Exception {
		// 해당 번호의 주문의 상태를 수정
		return dao.stateUpdate((OrderVO)obj);
	}

	@Override
	public void setDAO(DAO dao) {		
		this.dao = (OrderDAO) dao;
	}

}
