package com.javaex.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	//리스트2(리스트+검색)
	public List<BoardVo> getBoardList2(String keyword){
		System.out.println("BoardService.getBoardList2()");
		
		List<BoardVo> boardList = boardDao.selectList2(keyword);
		
		return boardList;
	}
	
	public Map<String, Object> getBoardList3(String keyword, int crtPage){
		System.out.println("BoardService.getBoardList3()");
		
		//crtPage --> 시작번호, 끝번호 1 --> 1~10          2 --> 11~20    3 --> 21~30 
		
		///////////////////////
		//리스트 구하기
		///////////////////////
		//페이지당 글 갯수
		int listCnt = 10;
		
		//현재 페이지
		crtPage = (crtPage > 0) ?  crtPage : (crtPage = 1); 
		
		/*
		if(crtPage > 0) {
			crtPage = crtPage;
		}else {
			crtPage = 1;
		}
		*/
		
		//crtPage;
		
		//시작 글 번호 startRnum
		int startRnum = (crtPage-1) * listCnt + 1;
		
		//끝 글 번호 endRnum
		int endRnum = (startRnum + listCnt)  - 1;
		
		List<BoardVo> boardList = boardDao.selectList3(keyword, startRnum, endRnum);
		
			///////////////////
			//페이징 계산
			///////////////////
			
			//페이지당 버튼 갯수
			int pageBtnCount = 5;
			
			//전체 글갯수 구하기
			int totalCount = boardDao.selectTotalCnt(keyword);
			
	
			//마지막 버튼 번호
			int endPageBtnNo = (int)Math.ceil(crtPage/(double)pageBtnCount) * pageBtnCount;  
																		//1/5,0 -->0.2 --> 1.0 --> 1 * 5 --> 5
																		//2/5.0 --> 0.4 --> 1.0 --> 1 * 5 --> 5
																		//5/5.0 --> 1.0 --> 1.0 --> 1 * 5 --> 5
																		//6/5.0 --> 1.2 --> 2.0 --> 2 * 5 --> 10
																		//10/5,0 -- 2.0 --> 2.0 --> 2 * 5 --> 10
																		//11/5.0 --> 2.2 --> 3.0 --> 3 * 5 --> 15
			
			//시작 버튼 번호
			int startPageBtnNo = endPageBtnNo - (pageBtnCount - 1);
			
			//다음 버튼 boolean
			boolean next;
			
			if(endPageBtnNo * listCnt < totalCount) { // 5 * 10 < 51
				next = true;
			}else { 														// 5 * 19 < 35
				next = false;
				endPageBtnNo = (int)Math.ceil(totalCount/(double)listCnt);
			}
			//이전 버튼 boolean
			boolean prev;
			if(startPageBtnNo != 1) {
				prev = true;
			}else {
				prev = false;
			}
			
			// boardList, prev, startPageBtnNo, endPageBtnNo, next -->jsp에 map으로 전달
			Map<String, Object> pMap = new HashMap<String, Object>();
			pMap.put("boardList", boardList);
			pMap.put("prev", prev);
			pMap.put("startPageBtnNo", startPageBtnNo);
			pMap.put("endPageBtnNo", endPageBtnNo);
			pMap.put("next", next);
		
		return pMap;
	}
	
	//등록
	public int insert(BoardVo boardVo) {
		System.out.println("service wirte");
		
		for(int i = 1; i<=1234; i++) {
			boardVo.setTitle(i + "번째 글 제목입니다.");
			boardVo.setContent(i + "번째 글 내용입니다.");
			
		}
		
		return boardDao.insert(boardVo);
	}
	
	//글 읽기
	public BoardVo read(int no) {
		System.out.println("service read");
		
		return boardDao.read(no);
	}
	
	//조회수
	public int hitUp(int no) {
		return boardDao.hitUp(no);
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
