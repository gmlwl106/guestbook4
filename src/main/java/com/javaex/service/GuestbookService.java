package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Service
public class GuestbookService {
	
	@Autowired
	private GuestbookDao gbDao;

	public List<GuestbookVo> getGuestList() {
		return gbDao.getGuestList();
	}
	
	public int guestInsert(GuestbookVo gbVo) {
		return gbDao.guestInsert(gbVo);
	}
	/*
	public GuestbookVo getGuest(int no) {
		return gbDao.getGuest(no);
	}
	*/
	public int guestDelete(int no, String password) {
		Map<String, Object> gbMap = new HashMap<String, Object>();
		gbMap.put("no", no);
		gbMap.put("password", password);
		return gbDao.guestDelete(gbMap);
	}

	

	



}
