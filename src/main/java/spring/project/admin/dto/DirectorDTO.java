package spring.project.admin.dto;

import org.springframework.web.multipart.MultipartFile;

public class DirectorDTO {
	
	private int dnum;
	private int dir_no;
	private String name;
	private String img;
	private String reg_date;
	private MultipartFile upImg;
	
	public int getDnum() {
		return dnum;
	}
	public void setDnum(int dnum) {
		this.dnum = dnum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImg() {
		return img;
	}
	public void setImg(String img) {
		this.img = img;
	}
	public int getDir_no() {
		return dir_no;
	}
	public void setDir_no(int dir_no) {
		this.dir_no = dir_no;
	}
	public String getReg_date() {
		return reg_date;
	}
	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}
	public MultipartFile getUpImg() {
		return upImg;
	}
	public void setUpImg(MultipartFile upImg) {
		this.upImg = upImg;
	}
	

	
	
	
}