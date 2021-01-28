package com.javaex.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.UserVo;

@Repository
public class UserDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//회원가입-->회원정보 저장
	public int insert(UserVo userVo) {
		System.out.println(userVo.toString());
		
		return sqlSession.insert("user.insert", userVo);
	}
	
	//로그인 --> 회원정보 가져오기
	public UserVo selectUser(UserVo userVo) {
		System.out.println("user dao selectUser");
		System.out.println(userVo.toString());
		
		UserVo vo = sqlSession.selectOne("user.selectUser", userVo);
		System.out.println(vo.toString());
		
		//vo  생략하고 return에 바로 넣어도 됨 
		
		return vo;
	}
	
	//회원정보 수정
	public int modify(UserVo userVo) {
		System.out.println("user dao modify");
		
		int count = sqlSession.update("user.modify", userVo);
		
		return count;
	}
	
	//회원정보 가져오기
	public UserVo getUser(int no) {
		System.out.println("dao:" + no);
		
		return sqlSession.selectOne("user.selectOne", no);
	}
	
	
}
