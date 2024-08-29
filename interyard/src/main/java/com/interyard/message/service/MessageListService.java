package com.interyard.message.service;

import java.util.List;

import com.interyard.main.dao.DAO;
import com.interyard.main.service.Service;
import com.interyard.message.dao.MessageDAO;
import com.interyard.message.vo.MessageVO;
import com.webjjang.util.page.PageObject;

public class MessageListService implements Service {
	
	private MessageDAO dao;
	
	@Override
	public void setDAO(DAO dao) {
		// TODO Auto-generated method stub
		this.dao = (MessageDAO) dao;
	}

	@Override
	public List<MessageVO> service(Object obj) throws Exception {
		// TODO Auto-generated method stub
		PageObject pageObject = (PageObject) obj;
		pageObject.setTotalRow(dao.getTotalRow(pageObject));
		return dao.list(pageObject);
	}

}
