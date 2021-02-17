package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.GuestbookVo;

@Service
public class GuestbookService {
	
	@Autowired
	private GuestbookDao guestbookDao;
	
	//리스트
	public List<GuestbookVo> gbList() {
		System.out.println("service 리스트");
		
		return guestbookDao.selectList();
		
	}
	
	//등록
	public int insert(GuestbookVo guestbookVo) {
		System.out.println("service 등록");
		
		return guestbookDao.insert(guestbookVo);
		
	}
	
	//삭제
	public int delete(GuestbookVo guestbookVo) {
		System.out.println("service 삭제");
		
		return guestbookDao.delete(guestbookVo);
	}
	
	//ajax 글 저장 -->저장된 글 가져오기
	public GuestbookVo writeResultVo(GuestbookVo guestbookVo) {
		//글저장
		//방법1 = int no = guestbookDao.insertSelectKey(guestbookVo);
		System.out.println("service: dao실행전--->" + guestbookVo);
		guestbookDao.insertSelectKey(guestbookVo);
		System.out.println("service: dao실행후--->" + guestbookVo);
		
		int no = guestbookVo.getNo();
		//글 1개 가져오기
		return guestbookDao.selectOne(no);
	}
	

}
