package spring.project.movie.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.project.admin.dto.GenreDTO;
import spring.project.movie.dto.MovieInfoDTO;
import spring.project.movie.dto.MovieMemberDTO;

@Repository
public class MovieDAOImpl implements MovieDAO {

	@Autowired
	private SqlSession sqlSession;

	private final String NAMESPACE = "spring.project.mappers.movieMapper";

	@Override
	public ArrayList<Object> getGenreList(GenreDTO genreDTO) {

		return (ArrayList<Object>) sqlSession.selectList(NAMESPACE + ".movieMapper");
	}

	@Override
	public int getGenreNumber(String genre) {

		// GenreDTO gdto = new GenreDTO();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("genre", genre);
		// paramMap.put("gdto", gdto);
		return sqlSession.selectOne(NAMESPACE + ".getGenreNumber", paramMap);
	}

	@Override
	public ArrayList<Object> getGenMovDataList(int genre_num, String id) {

		Map<String, Object> paramMap = new HashMap<String, Object>();

		if (genre_num != 0) {
			paramMap.put("genre_num", genre_num);
			paramMap.put("id", id);
			return (ArrayList<Object>) sqlSession.selectList(NAMESPACE + ".genreMovieList", paramMap);
		} else {
			paramMap.put("id", id);
			return (ArrayList<Object>) sqlSession.selectList(NAMESPACE + ".topMovieList", paramMap);

		}

	}

	// 별점주기
	@Override
	public void ratingInsert(String id, String movie_no, String rating) {

		System.out.println("에러체크1");
		System.out.println("id : " + id);
		System.out.println("movie_no : " + movie_no);
		System.out.println("rating : " + rating);

		int chkNum = 0;
		int mov_no = Integer.parseInt(movie_no);
		int rat = Integer.parseInt(rating);

		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		paramMap.put("mov_no", mov_no);
		paramMap.put("rat", rat);

		System.out.println("에러체크2");
		System.out.println("id : " + id);
		System.out.println("mov_no : " + mov_no);
		System.out.println("rating : " + rat);

		chkNum = sqlSession.selectOne(NAMESPACE + ".chkRating", paramMap);

		System.out.println("chkNum : " + chkNum);
		// 변화가 있으므로 값이 있다는 뜻
		if (chkNum == 0) {
			sqlSession.insert(NAMESPACE + ".insertRat", paramMap);
			System.out.println(movie_no + "번의 점수가 추가 되었습니다.");

		} else {
			sqlSession.update(NAMESPACE + ".updateRat", paramMap);
			System.out.println(movie_no + "번의 점수가 업데이트 되었습니다.");
		}

	}

	// 사용자가 좋아하는 취향의 장르를 찾는 메소드
	@Override
	public List<Integer> findTaste(String id) {
		// Map<String, Object> paramMap = new HashMap<String, Object>();
		// paramMap.put("id", id);

		return sqlSession.selectList(NAMESPACE + ".findTaste", id);
	}

	@Override
	public ArrayList<MovieMemberDTO> recommendatedMov_memberInfo(String id, int[] tasteNum) {

		// 유저 이외의 유저들을 찾는다.
		List<String> idList_withoutMe = new ArrayList<String>();

		// 최종적으로 나와 취향이 같은 사람들의 아이디와 취들어간다.
		List<MovieMemberDTO> sameTasteMemberList = new ArrayList<MovieMemberDTO>();

		idList_withoutMe = sqlSession.selectList(NAMESPACE + ".withoutMe", id);

		System.out.println(id + "이외의 유저");
		for (int i = 0; i < idList_withoutMe.size(); i++) {
			System.out.println(idList_withoutMe.get(i));
		}

		// id이외의 유저들을 각각 다 조사해서 취향이 같으면 리스트에 담는다. (같은 취향인 사람을 찾는 부분)
		for (int i = 0; i < idList_withoutMe.size(); i++) {
			String researchId = idList_withoutMe.get(i);
			System.out.println(researchId + "의 취향을 조사");

			// 취향번호를 비교할 임시 리스트
			List<Integer> temp_tasteCompareList = new ArrayList<Integer>();

			temp_tasteCompareList = sqlSession.selectList(NAMESPACE + ".findTaste", researchId);

			for (int j = 0; j < temp_tasteCompareList.size(); j++) {

				int compareGen_no = temp_tasteCompareList.get(j);
				// 상대의 취향이 여러개일수도있고, 유저의 취향이 여러개일수도 있기에, 서로를 다 비교해야 한다.
				for (int k = 0; k < tasteNum.length; k++) {
					if (compareGen_no == tasteNum[k]) {
						// 같은 취향을 찾아낼 경우 리스트에 담아야 한다.
						// 그때의 아이디를 리스트에 담는다.
						// 중복문제는 뒷부분에서 해결한다.
						// String sameTasteId = idList_withoutMe.get(i).getId();
						// 취향이 같은 사람을 찾았으므로 리스트에 담는다.
						// ameTasteMemberList.add(sameTasteId);
						MovieMemberDTO mmDTO = new MovieMemberDTO();
						mmDTO.setId(researchId);
						mmDTO.setGen_no(compareGen_no);

						sameTasteMemberList.add(mmDTO);

					}

				}

			}

		}
		for (int i = 0; i < tasteNum.length; i++) {
			System.out.println(id + "가 좋아하는 취향 번호 : " + tasteNum[i]);
		}

		System.out.println("나와 같은 취향의 사람들 ");
		for (int i = 0; i < sameTasteMemberList.size(); i++) {
			System.out.println(" 아이디 : " + sameTasteMemberList.get(i).getId() + ", 취향번호 : "
					+ sameTasteMemberList.get(i).getGen_no());

		}

		return (ArrayList<MovieMemberDTO>) sameTasteMemberList;
	}

	@Override
	public void insertArr(int[] countArr, String id, int tasteNum, String others) {

		List<Integer> likeMovieList = new ArrayList<Integer>();
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("id", id);
		paramMap.put("tasteNum", tasteNum);
		paramMap.put("others", others);

		likeMovieList = sqlSession.selectList(NAMESPACE + ".researchTasteMovieNo", paramMap);

		for (int i = 0; i < likeMovieList.size(); i++) {
			// 본 영화의 갯수들을 카운팅 시켜준다.
			countArr[likeMovieList.get(i)]++;
		}

	}

	@Override
	public ArrayList<Integer> recommendate(String id, List<Integer> like_gen_list,
			ArrayList<MovieMemberDTO> recommendatedMov_memberInfo, int[][] countArr, int[] tasteNum) {

		// 인기순으로 추천된 영화가 들어간다.5개씩
		ArrayList<Integer> recommendedMovList;

		int recommndMov_no[][] = null; // 추천받은 각 장르별 상위 5개의 영화를 받을 2차원 배열
		recommndMov_no = new int[tasteNum.length][]; // 2차원배열을 취향의 갯수만큼 초기화 한다.
		for (int i = 0; i < recommndMov_no.length; i++) {
			recommndMov_no[i] = new int[5];
		}

		recommendedMovList = new ArrayList<Integer>();

		// 중복 제거 처리
		for (int i = 0; i < recommndMov_no.length; i++) {
			for (int j = 0; j < recommndMov_no[i].length; j++) {

				// 가장 인기가 많은 영화 첫번째를 불러오는 메소드
				int tempMov_no = recommendMov(countArr[i]);

				for (int test = 0; test < recommndMov_no.length; test++) {
					// 2번째 줄부터 검사를해서 만약 이전줄과 중복되는게 있어버리면 의미없는값 -1을 넣어버려서 중복해결
					if (i != 0 && tempMov_no == recommndMov_no[i - 1][test]) {
						recommndMov_no[i][j] = 0;
					} else {
						recommndMov_no[i][j] = tempMov_no;
					}
				}
				// 아무것도 추천안되면 0으로 처리해버린다. 0,1,2,3,4는 없는 영화번호임 그러므로 추후에 시퀀스를 쓴다고
				// 하더라도 영화번호는 시작을 5번부터해야 문제가 발생하지 않음
				if (recommndMov_no[i][j] == 0 || recommndMov_no[i][j] == 1 || recommndMov_no[i][j] == 2
						|| recommndMov_no[i][j] == 3 || recommndMov_no[i][j] == 4) {
					recommndMov_no[i][j] = 0;
				}

				recommendedMovList.add(recommndMov_no[i][j]);

			}

		}

		return recommendedMovList;
	}

	private int recommendMov(int[] countArr) {
		// 버블 sort 형태로 최상위의 영화를 가져온다.
		// 가져온 최상위 값은 -1로 세팅하여 추후에도 최상위가 되지 않게끔 설정함.
		// 퀵 정렬 고민해봐야할 사항!

		int maxNum = -1;
		int result = 0;
		for (int i = 0; i < countArr.length; i++) {
			if (countArr[i] > maxNum) {
				maxNum = countArr[i];
				result = i;
			}

		}

		countArr[result] = -1;
		return result;
	}

	// 영화 제목 찾기
	@Override
	public ArrayList<String> findMovName(int[] tasteNum, ArrayList<Integer> recommendatedMov) {

		Map<String, Object> paramMap = new HashMap<String, Object>();
		ArrayList<String> mov_nameList = new ArrayList<String>();

		for (int i = 1; i <= tasteNum.length; i++) {

			int end = (5 * i) - 1;
			int start = end - 4;

			for (int j = start; j <= end; j++) {
				paramMap.put("recommendatedMov", recommendatedMov.get(j));
				paramMap.put("tasteNum", tasteNum[i - 1]);
				// 있나 없나부터 체크... count 활용해야함...

				int chk = sqlSession.selectOne(NAMESPACE + ".chkTitle", paramMap);

				if (chk != 0) {// 내용이 있으면

					String movie_name = sqlSession.selectOne(NAMESPACE + ".findMovName", paramMap);

					mov_nameList.add(movie_name);
				} else {
					System.out.println("아무것도 없는 영화");
					mov_nameList.add(null);
				}

			}
		}

		return mov_nameList;
	}

	@Override
	public ArrayList<String> findTasteGenreName(int[] taste_genre_num, int size) {

		ArrayList<String> taste_genre_name = new ArrayList<String>();

		for (int i = 0; i < taste_genre_num.length; i++) {
			for (int j = 0; j < 5; j++) {
				int taste_gNum = taste_genre_num[i];
				// 체크부터 해줘야함...
				// int chk = sqlSession.selectOne(NAMESPACE + ".chkGenName",
				// taste_gNum);
				// if (chk != 0) {
				String gen_name = sqlSession.selectOne(NAMESPACE + ".findGenreName", taste_gNum);
				taste_genre_name.add(gen_name);
				// }
			}
		}

		return taste_genre_name;
	}

	@Override
	public ArrayList<MovieInfoDTO> makeMovieInfoDto(ArrayList<Integer> recommendatedMov, ArrayList<String> mov_nameList,
			ArrayList<String> genre_nameList) {

		ArrayList<MovieInfoDTO> genre_recommendedList = new ArrayList<MovieInfoDTO>();
		for (int i = 0; i < recommendatedMov.size(); i++) {
			System.out.println("*** 몇인데..." + recommendatedMov.get(i));
		}

		for (int i = 0; i < recommendatedMov.size(); i++) {
			int mov_no = recommendatedMov.get(i);
			MovieInfoDTO movieInfoDTO = new MovieInfoDTO();
			movieInfoDTO = sqlSession.selectOne(NAMESPACE + ".makeMovieInfoDTO", mov_no);
			movieInfoDTO.setGen_name(genre_nameList.get(i));
			genre_recommendedList.add(movieInfoDTO);

		}

		return genre_recommendedList;
	}

	@Override
	public ArrayList<Integer> getFavoriteDirectorList(String id) {

		List<Integer> taste_dir_no = new ArrayList<Integer>();

		taste_dir_no = sqlSession.selectList(NAMESPACE + ".getFavoriteDirector", id);

		return (ArrayList<Integer>) taste_dir_no;
	}

	@Override
	public List<Integer> getDirectorRecommendMovieList(String id, int[] repetition, ArrayList<Integer> dir_no_list) {

		List<Integer> uniqueDirectorRecommendMovieList = null;
		// 반복이 몇번되는지에 대한 셋트를 구한다.
		int repetitionLength = repetition.length + 1;
		System.out.println("리피티션 렝스 : " + repetitionLength);
		List<Integer> director_recomm_movieList = new ArrayList<Integer>();
		HashMap<String, Object> paramMap = new HashMap<String, Object>();

		paramMap.put("id", id);
		paramMap.put("repetition", repetition);
		paramMap.put("dir_no_list", dir_no_list);
		paramMap.put("repetitionLength", repetitionLength);
		paramMap.put("repetitionFirst", repetition[0]);

		director_recomm_movieList = sqlSession.selectList(NAMESPACE + ".getDirectorRecommendMovieList", paramMap);

		// 중복 다 제거해버림...
		uniqueDirectorRecommendMovieList = new ArrayList<Integer>(new HashSet<Integer>(director_recomm_movieList));

		for (int i = 0; i < uniqueDirectorRecommendMovieList.size(); i++) {
			System.out.println(uniqueDirectorRecommendMovieList.get(i));
		}

		return uniqueDirectorRecommendMovieList;
	}

	@Override
	public ArrayList<String> getDirectorNameList(ArrayList<Integer> director_recommendatedMovieNumList) {
		List<String> dir_name_list = new ArrayList<String>();
		//HashMap<String, Object> paramMap = new HashMap<String, Object>();
	
		for(int i=0; i < director_recommendatedMovieNumList.size(); i++){
			int mov_no = director_recommendatedMovieNumList.get(i);
			String director_name = sqlSession.selectOne(NAMESPACE+".getDirectorNameList", mov_no);
			dir_name_list.add(director_name);
		}
		
	

		System.out.println("좋아하는 감독 이름");
		for (int i = 0; i < dir_name_list.size(); i++) {
			System.out.println(dir_name_list.get(i));
		}
		return (ArrayList<String>) dir_name_list;

	}

	@Override
	public ArrayList<MovieInfoDTO> getMovieInfoList(ArrayList<Integer> director_recommendatedMovieNumList) {
		
		List<MovieInfoDTO> movieInfoList = new ArrayList<MovieInfoDTO>();
		
		for (int i = 0; i < director_recommendatedMovieNumList.size(); i++) {
			int mov_no = director_recommendatedMovieNumList.get(i);
			MovieInfoDTO movieInfoDTO = new MovieInfoDTO();
			movieInfoDTO = sqlSession.selectOne(NAMESPACE + ".makeMovieInfoDTO", mov_no);
			movieInfoList.add(movieInfoDTO);

		}
		return (ArrayList<MovieInfoDTO>) movieInfoList;
		
		
		
	}

}
