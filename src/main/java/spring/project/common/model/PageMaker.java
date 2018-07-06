package spring.project.common.model;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

public class PageMaker {

	private int totalCount; // 전체 데이터의 갯수 ********* // 2번으로 입력한다.-cri먼저
	private int totalPage; // 전체 페이지 계산한다. 계산한다.
	private int startPage; // 화면 하단 페이지 표시부분의 시작 페이지. 계산한다.
	private int endPage; // 화면 하단 페이지 표시부분의 끝 페이지 . 계산한다.
	private boolean prev; // 시작 페이지의 이전  페이지가 있다. 계산한다.
	private boolean next; // 끝 페이지의 다음 페이지가 있다. 계산한다.

	private int displayPageNum = 10; // 하단 부분에 보여줄 페이지의 갯수

	// 해당 페이지에 해당되는 시작 끝이 들어 있는 객체 *********
	// 1번째으로 입력한다. setter 이용.
//	private Criteria cri;
	private SearchCriteria cri;

	// uri 뒤에 붙여서 넘겨야 하는 데이터 처리 - page를 넘겨 받지 않는다.
	public String makeQuery() {
		return makeQuery(cri.getPage());
	}

	// uri 뒤에 붙여서 넘겨야 하는 데이터 처리 - page를 넘겨 받아서 처리
	public String makeQuery(int page) {
//		public String makeQuery(int page) { // 교재 --> cri에  page가 존재해서
		UriComponents uriComponents
		= UriComponentsBuilder.newInstance()
		//	.path(path) - uri가 계속 달라지므로 jsp에서 처리 : ?k=v&k=v... 가 남는다.
			.queryParam("page", page)
			.queryParam("perPageNum", cri.getPerPageNum())
			.queryParam("searchType", cri.getSearchType())
			.queryParam("keyword", cri.getKeyword())
			.build();
//		System.out.println(uriComponents.toUriString());
		return uriComponents.toUriString();
	}


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

	public SearchCriteria getCri() {
		return cri;
	}

	public void setCri(SearchCriteria cri) {
		this.cri = cri;
	}

	@Override
	public String toString() {
		return "PageMaker [totalCount=" + totalCount + ", totalPage=" + totalPage + ", startPage=" + startPage
				+ ", endPage=" + endPage + ", prev=" + prev + ", next=" + next + ", displayPageNum=" + displayPageNum
				+ ", cri=" + cri + "]";
	}

}
