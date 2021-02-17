package com.javaex.service;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.javaex.dao.GalleryDao;
import com.javaex.vo.GalleryVo;

@Service
public class GalleryService {
	
	@Autowired
	private GalleryDao galleryDao; 
	
	//리스트 출력
	public List<GalleryVo>list() {
		System.out.println("[GalleryService.list()]");
		
		return galleryDao.selectList();
	}
	
	//파일 업로드
	public String upLoad(MultipartFile file, GalleryVo galleryVo) {
		System.out.println("[GalleryService.restore()]");
		System.out.println(file.getOriginalFilename());
		
		//db저장할 정보 수집
		String saveDir = "C:\\javaStudy\\upload";
		
		//오리지널 파일이름
		String orgName = file.getOriginalFilename();
		System.out.println("orgName:" + orgName);
		
		//확장자
		String exName = orgName.substring(orgName.lastIndexOf("."));
		System.out.println("exName:" + exName);
		
		//서버 저장파일 이름
		String saveName = System.currentTimeMillis()+UUID.randomUUID().toString() + exName;
		System.out.println("saveName:  " + saveName);
		
		//서버 파일패스 --> 저장경로
		String filePath = saveDir + "\\" + saveName;
		System.out.println("filePath: " + filePath);
		
		//파일 사이즈
		long fileSize = file.getSize();
		System.out.println("fileSize: " + fileSize);
		
		//vo담기
		galleryVo.setSaveName(saveName);
		galleryVo.setOrgName(orgName);
		galleryVo.setFilepath(filePath);
		galleryVo.setFileSize(fileSize);
		
		galleryDao.fileInsert(galleryVo);
		
		//서버 하드디스크에 저장
		try {
			byte[] fileData = file.getBytes();
			OutputStream out = new FileOutputStream(filePath);
			BufferedOutputStream bos = new BufferedOutputStream(out);
			
			bos.write(fileData);
			bos.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return saveName;
		
	}
	
	//파일 읽기
	public GalleryVo read(int no) {
		System.out.println("[GalleryService.read()]");
		
		return galleryDao.read(no);
	}
	
	//파일 삭제
	public int delete(int no) {
		System.out.println("[GalleryService.delete()]");
		
		return galleryDao.delete(no);
	}

}
