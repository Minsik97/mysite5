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
		System.out.println("dao 삭제");
		
		return sqlSession.delete("guestbook.delete", guestbookVo);
	}
	
	//글 저장(selectkey)
	public int insertSelectKey(GuestbookVo guestbookVo) {
		System.out.println("dao insertSelectKey");
		
		System.out.println("dao:xml 실행전----> " + guestbookVo);
		sqlSession.insert("guestbook.insertSelectKey", guestbookVo);
		System.out.println("dao:xml 실행후----> " + guestbookVo);
		return guestbookVo.getNo();
	}
	
	//글 1개 가져오기
	public GuestbookVo selectOne(int no) {
		System.out.println("dao selectOne");
		
		return sqlSession.selectOne("guestbook.select", no);
	}
	
	
}
