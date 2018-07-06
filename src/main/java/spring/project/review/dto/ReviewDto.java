package spring.project.review.dto;

import org.springframework.format.annotation.DateTimeFormat;

public class ReviewDto {
	private int review_no;
	private String title;
	private String summary;
	private int hit;
	private int good;
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private String reg_date;
	private String id;
	private int mov_no;
	private String title_1;
	private String poster;
	

	public String getTitle_1() {
		return title_1;
	}

	public void setTitle_1(String title_1) {
		this.title_1 = title_1;
	}
	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public int getReview_no() {
		return review_no;
	}

	public void setReview_no(int review_no) {
		this.review_no = review_no;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public int getHit() {
		return hit;
	}

	public void setHit(int hit) {
		this.hit = hit;
	}

	public int getGood() {
		return good;
	}

	public void setGood(int good) {
		this.good = good;
	}

	public String getReg_date() {
		return reg_date;
	}

	public void setReg_date(String reg_date) {
		this.reg_date = reg_date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getMov_no() {
		return mov_no;
	}

	public void setMov_no(int mov_no) {
		this.mov_no = mov_no;
	}

	
	@Override
	public String toString() {
		return "ReviewDto [review_no=" + review_no + ", title=" + title + ", summary=" + summary + ", hit=" + hit
				+ ", good=" + good + ", reg_date=" + reg_date + ", id=" + id + ", mov_no=" + mov_no + ", title_1="
				+ title_1 + ", poster=" + poster + "]";
	}

}
