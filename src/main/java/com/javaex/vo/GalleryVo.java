package com.javaex.vo;

public class GalleryVo {
	
	//필드
	private int no;
	private int user_no;
	private String content;
	private String filepath;
	private String orgName;
	private String saveName;
	private long fileSize;
	private String name;
	
	
	//생성자
	public GalleryVo() {
	}
	
	public GalleryVo(int no, int user_no, String content, String filepath, String orgName, String saveName,
			long fileSize, String name) {
		super();
		this.no = no;
		this.user_no = user_no;
		this.content = content;
		this.filepath = filepath;
		this.orgName = orgName;
		this.saveName = saveName;
		this.fileSize = fileSize;
		this.name = name;
	}
	
	//메소드g/s
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public int getUser_no() {
		return user_no;
	}

	public void setUser_no(int user_no) {
		this.user_no = user_no;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getSaveName() {
		return saveName;
	}

	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	//메소드일반
	@Override
	public String toString() {
		return "GalleryVo [no=" + no + ", user_no=" + user_no + ", content=" + content + ", filepath=" + filepath
				+ ", orgName=" + orgName + ", saveName=" + saveName + ", fileSize=" + fileSize + ", name=" + name + "]";
	}
	
	
	
}
