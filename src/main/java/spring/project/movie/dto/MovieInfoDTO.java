package spring.project.movie.dto;

public class MovieInfoDTO {

	private int mov_no;
	private String title;
	private String trailer;
	private String poster;
	private String release;
	private String rtime;
	private String grade;
	private int attd;
	private String summary;
	private String reg_date;
	private String mod_date;
	private String country;
	
	//DB와는 상관없는 변수
	private String gen_name;
	private String dir_name;

	public String getDir_name() {
		return dir_name;
	}
	public void setDir_name(String dir_name) {
		this.dir_name = dir_name;
	}
	public String getGen_name() {
		return gen_name;
	}
	public void setGen_name(String gen_name) {
		this.gen_name = gen_name;
	}
	public int getMov_no() {
		return mov_no;
	}
	public void setMov_no(int mov_no) {
		this.mov_no = mov_no;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTrailer() {
		return trailer;
	}
	public void setTrailer(String trailer) {
		this.trailer = trailer;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getRelease() {
		return release;
	}
	public void setRelease(String release) {
		this.release = release;
	}
	public String getRtime() {
		return rtime;
	}
	public void setRtime(String rtime) {
		this.rtime = rtime;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public int getAttd() {
		return attd;
	}
	public void setAttd(int attd) {
		this.attd = attd;
	}
	public String getSummary() {
		return summary;
	}
	public void setSummary(String summary) {
		this.summary = summary;
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
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	
	
}
