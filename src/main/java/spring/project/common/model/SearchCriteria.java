package spring.project.common.model;

public class SearchCriteria extends Criteria{

	private String searchType; // 검색의 항목 저장
	private String keyword; // 검색 데이터 저장

	public String getSearchType() {
		return searchType;
	}
	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	// 제목으로 검색하는 아닌지 알아 내는 getter
	public boolean isTitleType() {
		if(searchType.indexOf("t")<0) return false; // 없는 경우
		else return true; // 있는 경우
	}

	// 내용으로 검색하는 아닌지 알아 내는 getter
	public boolean isContentType() {
		if(searchType.indexOf("c")<0) return false; // 없는 경우
		else return true; // 있는 경우
	}

	// 작성자로 검색하는 아닌지 알아 내는 getter
	public boolean isWriterType() {
		if(searchType.indexOf("w")<0) return false; // 없는 경우
		else return true; // 있는 경우
	}

	@Override
	public String toString() {
		return "SearchCriteria [searchType=" + searchType + ", keyword=" + keyword
				+ "]+,"+super.toString();
	}

}
