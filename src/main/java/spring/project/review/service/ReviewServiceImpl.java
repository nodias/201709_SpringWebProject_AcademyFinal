package spring.project.review.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import spring.project.common.model.SearchCriteria;
import spring.project.movie.dto.MovieVO;
import spring.project.review.dao.ReviewDao;
import spring.project.review.dto.ReviewDto;

@Service
public class ReviewServiceImpl implements ReviewService {

	@Inject
	private ReviewDao reviewDao;

	@Override
	public List<ReviewDto> list(SearchCriteria cri, String sort) throws Exception {
		return reviewDao.list(cri, sort);
	}

	@Override
	public int getRow(SearchCriteria cri) throws Exception {
		return reviewDao.getRow(cri);
	}

	@Override
	public ReviewDto view(Integer no, boolean isView) throws Exception {
		if (isView)
			reviewDao.increase(no);
		ReviewDto reviewDto = reviewDao.view(no);
		return reviewDto;
	}

	@Override
	public void write(ReviewDto reviewDto) throws Exception {
		reviewDao.write(reviewDto);
	}

	@Override
	public void update(ReviewDto reviewDto) throws Exception {
		reviewDao.update(reviewDto);
	}

	@Override
	public void delete(Integer no) throws Exception {
		reviewDao.delete(no);

	}

	@Override
	public List<ReviewDto> getMov_no(MovieVO movieVO) throws Exception {
		return reviewDao.getMov_no(movieVO);
	}

}
