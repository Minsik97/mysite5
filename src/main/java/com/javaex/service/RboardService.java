package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.RboardDao;
import com.javaex.vo.RboardVo;

@Service
public class RboardService {
	
	@Autowired
	private RboardDao rboardDao;
	
	//리스트
	public List<RboardVo> list(){
		System.out.println("service list");
		
		return rboardDao.list();
	}
	
	//등록
	public int write (RboardVo rboardVo) {
		System.out.println("service write");
		
		return rboardDao.write(rboardVo);
	}
	
}
