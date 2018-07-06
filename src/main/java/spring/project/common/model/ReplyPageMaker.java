package spring.project.common.model;

public class ReplyPageMaker {

	private int totalCount; // 전체 데이터의 갯수 ********* // 2번으로 입력한다.-cri먼저
	private int totalPage; // 전체 페이지 계산한다. 계산한다.
	private int startPage; // 화면 하단 페이지 표시부분의 시작 페이지. 계산한다.
	private int endPage; // 화면 하단 페이지 표시부분의 끝 페이지 . 계산한다.
	private boolean prev; // 시작 페이지의 이전  페이지가 있다. 계산한다.
	private boolean next; // 끝 페이지의 다음 페이지가 있다. 계산한다.

	private int displayPageNum = 10; // 하단 부분에 보여줄 페이지의 갯수

	// 해당 페이지에 해당되는 시작 끝이 들어 있는 객체 *********
	// 1번째으로 입력한다. setter 이용.
	private Criteria cri;

	// getter & setter
	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		// 전체 페이지 구하기
		totalPage = (totalCount-1) / cri.getPerPageNum()+1;
		// 화면에 보여줄 시작 페이지와 끝 페이지 구하기
		calcData();
	}

	// 화면에 보여줄 시작 페이지와 끝 페이지 구하기
	private void calcData() {
		// 시작페이지 구하기
		startPage = (cri.getPage()-1) / displayPageNum * displayPageNum + 1;
		// 끝페이지 구하기
		endPage = startPage + displayPageNum - 1;
		// 끝페이지 전체 페이지(totalPage) 보다 크지 못한다.
		if(endPage>totalPage) endPage = totalPage;

		// 이전 다음 계산해서 넣는다. setter는 만들지 않는다.
		// 처음 페이지가 1이면 앞에 페이지는 없다.
		prev = startPage ==1 ? false : true;
		// 끝 페이지가 전체페이지와 같으면 뒤에 페이지는 없다.
		next = endPage >= totalPage ? false : true;

	}

	public int getTotalPage() {
		return totalPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isPrev() {
		return prev;
	}

	public boolean isNext() {
		return next;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}

	public Criteria getCri() {
		return cri;
	}

	public void setCri(Criteria cri) {
		this.cri = cri;
	}

	@Override
	public String toString() {
		return "PageMaker [totalCount=" + totalCount + ", totalPage=" + totalPage + ", startPage=" + startPage
				+ ", endPage=" + endPage + ", prev=" + prev + ", next=" + next + ", displayPageNum=" + displayPageNum
				+ ", cri=" + cri + "]";
	}

}
