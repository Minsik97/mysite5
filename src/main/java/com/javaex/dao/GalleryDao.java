package com.javaex.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.javaex.vo.GalleryVo;

@Repository
public class GalleryDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	//리스트 출력
	public List<GalleryVo>selectList(){
		System.out.println("[GalleryDao.selectList()]");
		
		return sqlSession.selectList("gallery.selectList");
	}
	
	//파일 등록
	public int fileInsert(GalleryVo galleryVo) {
		System.out.println("[GalleryDao.fileInsert()]");
		
		return sqlSession.insert("gallery.fileInsert", galleryVo);
	}
	
	//파일 읽기
	public GalleryVo read(int no) {
		System.out.println("[GalleryDao.read()]");
		
		return sqlSession.selectOne("gallery.selectOne", no);
	}
	
	//파일 삭제
	public int delete(int no) {
		
		return sqlSession.delete("gallery.delete", no);
	}
	
}
