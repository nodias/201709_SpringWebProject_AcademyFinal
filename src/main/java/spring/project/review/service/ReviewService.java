package spring.project.review.service;

import java.util.List;

import spring.project.common.model.SearchCriteria;
import spring.project.movie.dto.MovieVO;
import spring.project.review.dto.ReviewDto;

public interface ReviewService {

	// 모든 리스트 보기(페이지 처리 + 검색)
	public List<ReviewDto> list(SearchCriteria cri, String sort) throws Exception;

	// 전체 데이터의 개수 구하기
	public int getRow(SearchCriteria cri) throws Exception;

	// 게시판 상세 보기
	public ReviewDto view(Integer no, boolean isView) throws Exception;

	// 글쓰기
	public void write(ReviewDto reviewDto) throws Exception;

	// 글 수정
	public void update(ReviewDto reviewDto) throws Exception;

	// 글삭제
	public void delete(Integer no) throws Exception;
	
	// 영화 번호 가져오기
	public List<ReviewDto> getMov_no(MovieVO movieVO) throws Exception;

}
