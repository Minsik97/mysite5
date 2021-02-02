package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.RboardVo;

@Repository
public class RboardDao {
		
	@Autowired
	private SqlSession sqlsession;
	
	//리스트
	public List<RboardVo>list(){
		System.out.println("dao list");
		
		return sqlsession.selectList("rboard.selectList");
	}

	//등록
	public int write (RboardVo rboardVo) {
		System.out.println("dao write");
		
		return sqlsession.insert("rboard.insert", rboardVo);
	}
	
}
