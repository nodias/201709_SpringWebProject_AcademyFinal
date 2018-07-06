package spring.project.admin.dto;

import org.springframework.web.multipart.MultipartFile;

public class ActorDTO {

	private int anum;
	private int act_no;
	private String name;
	private String img;
	private String reg_date;
	private MultipartFile upImg;
	
	
	public int getAnum() {
		return anum;
	}
	public void setAnum(int anum) {
		this.anum = anum;
	}
	public int getAct_no() {
		return act_no;
	}
	public void setAct_no(int act_no) {
		this.act_no = act_no;
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
