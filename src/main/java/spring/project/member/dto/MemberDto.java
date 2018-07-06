package spring.project.member.dto;

//asd
public class MemberDto {
	private String id;
	private String pw;
	private String new_pw;
	private String name;
	private String img;
	private String reg_date;
	private String mod_date;
	private String grade;
	private String token;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getNew_pw() {
		return new_pw;
	}

	public void setNew_pw(String new_pw) {
		this.new_pw = new_pw;
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

	public String getMod_date() {
		return mod_date;
	}

	public void setMod_date(String mod_date) {
		this.mod_date = mod_date;
	}

	public String getGrade() {
		return grade;
	}

	@Override
	public String toString() {
		return "MemberDto [id=" + id + ", pw=" + pw + ", new_pw=" + new_pw + ", name=" + name + ", img=" + img
				+ ", reg_date=" + reg_date + ", mod_date=" + mod_date + ", grade=" + grade + ", token=" + token + "]";
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
