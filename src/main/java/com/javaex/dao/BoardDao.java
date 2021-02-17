package com.javaex.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.BoardVo;

@Repository
public class BoardDao {

	@Autowired
	private SqlSession sqlSession;
	
	//리스트
	public List<BoardVo>selectList(){
		System.out.println("dao 리스트");
		
		return sqlSession.selectList("board.selectList");
	}
	
	//글전체 가져오기2(키워드)
	public List<BoardVo>selectList2(String keyword){
		System.out.println("dao selectList2");
		System.out.println("keyword = " + keyword);
		
		List<BoardVo> boardList = sqlSession.selectList("board.selectList2", keyword);
		
		return boardList;
	}
	
	//글전체 가져오기3(키워드+페이징)
	public List<BoardVo>selectList3(String keyword, int startRnum, int endRnum){
		System.out.println("dao selectList3");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyword", keyword);
		map.put("startRnum", startRnum);
		map.put("endRnum", endRnum);
		System.out.println(map);
		
		return sqlSession.selectList("board.selectList3", map);
	}
	
	//전체 글 갯수 가져오기
	public int selectTotalCnt (String keyword) {
		System.out.println("dao.selectTotalCnt");
		
		return sqlSession.selectOne("board.selectTotalCnt", keyword);
	}
	
	//등록
	public int insert(BoardVo boardVo) {
		System.out.println("dao write");
		
		return sqlSession.insert("board.insert", boardVo);
	}
	
	//글 읽기(회원정보 가져오기)
	public BoardVo read(int no) {
		System.out.println("dao read");
		
		return sqlSession.selectOne("board.selectOne", no);
	}
	
	//조회수
	public int hitUp(int no) {
		return sqlSession.update("board.hitUp", no);
	}
	
	//수정
	public int modify(BoardVo boardVo) {
		System.out.println("dao modify");
		System.out.println(boardVo);
		
		return sqlSession.update("board.update", boardVo);
	}
	
	//삭제
	public int delete(int no) {
		System.out.println("dao delete");
		
		return sqlSession.delete("board.delete", no);
	}
	
	
}
