package spring.project.review.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import spring.project.common.model.SearchCriteria;
import spring.project.movie.dto.MovieVO;
import spring.project.review.dto.ReviewDto;

@Repository
public class ReviewDaoImpl implements ReviewDao {

	@Inject
	private SqlSession sqlsession;

	private final String NAMESPACE = "spring.project.mapper.reviewMapper";

	@Override
	public List<ReviewDto> list(SearchCriteria cri, String sort) throws Exception {
		Map<String, Object> map = new HashMap<>();
		map.put("cri", cri);
		map.put("sort", sort);
		System.out.println("ReviewDAO.sort : " + sort);
		System.out.println("ReviewDaoImpl.list");
		return sqlsession.selectList(NAMESPACE + ".list", map);
	}

	@Override
	public int getRow(SearchCriteria cri) throws Exception {
		System.out.println("ReviewDaoImpl.getRow");
		return sqlsession.selectOne(NAMESPACE + ".getRow", cri);
	}

	@Override
	public ReviewDto view(Integer no) throws Exception {
		System.out.println("ReviewDaoImpl.view");
		return sqlsession.selectOne(NAMESPACE + ".view", no);
	}

	@Override
	public void increase(Integer no) throws Exception {
		System.out.println("ReviewDaoImpl.increase");
		sqlsession.selectOne(NAMESPACE + ".increase", no);
	}

	@Override
	public void write(ReviewDto reviewDto) throws Exception {
		System.out.println("ReviewDaoImpl.write");
		sqlsession.insert(NAMESPACE + ".write", reviewDto);
	}

	@Override
	public void update(ReviewDto reviewDto) throws Exception {
		System.out.println("ReviewDaoImpl.update");
		sqlsession.insert(NAMESPACE + ".update", reviewDto);
	}

	@Override
	public void delete(Integer no) throws Exception {
		System.out.println("ReviewDaoImpl.delete");
		sqlsession.delete(NAMESPACE + ".delete", no);

	}

	@Override
	public List<ReviewDto> getMov_no(MovieVO movieVO) throws Exception {
		System.out.println("ReviewDaoImpl.getMov_no");
		return sqlsession.selectList(NAMESPACE + ".getMov_no", movieVO);
	}

}
