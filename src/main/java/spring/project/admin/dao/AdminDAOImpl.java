package spring.project.admin.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.project.admin.dto.GenreDTO;

@Repository
public class AdminDAOImpl implements AdminDAO {

	@Autowired
	private SqlSession sqlSession;

	private final String NAMESPACE = "spring.project.mappers.adminMapper";

	@Override
	public void dataDelete(int delNum, String tableName) {

		if (tableName.equals("genre")) {
			sqlSession.delete(NAMESPACE + ".genreDelete", delNum);
		} else if (tableName.equals("actor")) {
			sqlSession.delete(NAMESPACE + ".actorDelete", delNum);
		} else if (tableName.equals("director")) {
			sqlSession.delete(NAMESPACE + ".directorDelete", delNum);
		}

	}

	@Override
	public void dataInsert(Object dto, String tableName) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		// paramMap.put("dto", dto);
		// paramMap.put("tableName", tableName);

		if (tableName.equals("director")) {
			sqlSession.insert(NAMESPACE + ".directorInsert", dto);
		} else if (tableName.equals("actor")) {
			sqlSession.insert(NAMESPACE + ".actorInsert", dto);
		} else if (tableName.equals("genre")) {
			sqlSession.insert(NAMESPACE + ".genreInsert", dto);
		} else if (tableName.equals("movie_info")) {
			sqlSession.insert(NAMESPACE + ".movie_infoInsert", dto);
		}

	}

	// 페이징 처리된 데이터 리스트 가져오는 메소드
	@Override
	public ArrayList<Object> getDataList(int page, int limit, String tableName) {
		// TODO Auto-generated method stub

		int startrow = (page - 1) * 10 + 1;
		int endrow = startrow + limit - 1;

		System.out.println("startrow : " + startrow);
		System.out.println("endrow : " + endrow);

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("startrow", startrow);
		paramMap.put("endrow", endrow);

		if (tableName.equals("genre")) {

			return (ArrayList<Object>) sqlSession.selectList(NAMESPACE + ".genreList", paramMap);
		} else if (tableName.equals("actor")) {
			return (ArrayList<Object>) sqlSession.selectList(NAMESPACE + ".actorList", paramMap);
		} else if (tableName.equals("director")) {
			return (ArrayList<Object>) sqlSession.selectList(NAMESPACE + ".directorList", paramMap);
		} else if (tableName.equals("movie_info")) {
			return (ArrayList<Object>) sqlSession.selectList(NAMESPACE + ".movieInfoList", paramMap);
		}
		return null;

	}

	// 모든 데이터 가져오는 메소드
	@Override
	public ArrayList<Object> getAllData(String tableName) {
		// TODO Auto-generated method stub
		System.out.println("가져올 테이블 이름 : " + tableName);

		if (tableName.equals("genre")) {

			return (ArrayList<Object>) sqlSession.selectList(NAMESPACE + ".getAllGenre");
		} else if (tableName.equals("director")) {
			return (ArrayList<Object>) sqlSession.selectList(NAMESPACE + ".getAllDirector");
		} else if (tableName.equals("actor")) {
			return (ArrayList<Object>) sqlSession.selectList(NAMESPACE + ".getAllActor");
		}

		return null;
	}

	@Override
	public ArrayList<GenreDTO> getGenreList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getListCount(String tableName) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tableName", tableName);
		return sqlSession.selectOne(NAMESPACE + ".getListCount", paramMap);
	}

	@Override
	public int getMoveNum() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void relDataInsert(String tableName, int movieNum, String splitData) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("tableName", tableName);
		paramMap.put("movieNum", movieNum);
		paramMap.put("splitData", Integer.parseInt(splitData));
		
		sqlSession.insert(NAMESPACE+".relInsert", paramMap);
		
		
//		if (tableName.equals("dir_rel")) {
//			sqlSession.insert(NAMESPACE + ".");
//		}else if(tableName.equals("ma_rel")){
//			
//		}else if(tableName.equals("sa_rel")){
//			
//		}else if(tableName.equals("ge_rel")){
//			
//		}
	}

	@Override
	public int getRecentMovNum() {

		return sqlSession.selectOne(NAMESPACE + ".recentMovNum");
	}

}
