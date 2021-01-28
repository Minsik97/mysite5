package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GuestbookVo;

@Repository
public class GuestbookDao {

	@Autowired
	private SqlSession sqlSession;
	
	//리스트
	public List<GuestbookVo> selectList(){
		System.out.println("dao 리스트");
		
		return  sqlSession.selectList("guestbook.selectList");
		
	}
	
	//등록
	public int insert(GuestbookVo guestbookVo) {
		System.out.println("dao 등록");
		
		return sqlSession.insert("guestbook.insert", guestbookVo);
	}
	
	//삭제
	public int delete(GuestbookVo guestbookVo) {
		System.out.println("dao 등록");
		
		return sqlSession.delete("guestbook.delete", guestbookVo);
	}
	
	
}
