package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.BoardDao;
import com.javaex.vo.BoardVo;

@Service
public class BoardService {
	
	@Autowired
	private BoardDao boardDao;
	
	//리스트
	public List<BoardVo> list(){
		
		return boardDao.selectList();
	}
	
	//등록
	public int insert(BoardVo boardVo) {
		System.out.println("service wirte");
		
		return boardDao.insert(boardVo);
	}
	
	//글 읽기
	public BoardVo read(int no) {
		System.out.println("service read");
		
		return boardDao.read(no);
	}
	
	//조회수
	public int hitUp(int hitUp) {
		return boardDao.hitUp(hitUp);
	}
	
	//수정
	public int modify(BoardVo boardVo) {
		System.out.println("service modify");
		
		return boardDao.modify(boardVo);
	}
	
	//삭제
	public int delete(int no) {
		System.out.println("service delete");
		
		return boardDao.delete(no);
	}

}
