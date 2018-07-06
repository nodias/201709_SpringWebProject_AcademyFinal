package spring.project.movie.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import spring.project.admin.dto.GenreDTO;
import spring.project.member.dto.UserVO;
import spring.project.movie.dao.MovieDAO;
import spring.project.movie.dto.MovieInfoDTO;
import spring.project.movie.dto.MovieMemberDTO;

@Service
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieDAO movieDAO;

	// 장르들을 가져오는 메소드
	@Override
	public ArrayList<Object> getGenreList(GenreDTO genreDTO) {

		return movieDAO.getGenreList(genreDTO);
	}

	@Override
	public int getGenreNumber(String genre) {
		// TODO Auto-generated method stub
		return movieDAO.getGenreNumber(genre);
	}

	@Override
	public ArrayList<Object> getGenMovDataList(int genre_num, String id) {
		return movieDAO.getGenMovDataList(genre_num, id);
	}

	@Override
	public void ratingInsert(String id, String movieNum, String rating) {

		movieDAO.ratingInsert(id, movieNum, rating);
	}

	@Override
	public void recommendation(Model model, HttpServletRequest request) {

		ArrayList<MovieInfoDTO> genre_recommendedList; // 장르별로 중복제거된 최종 영화
		ArrayList<Integer> recommendatedMov; // 중북된 영화 다 제거하고, 5개단위로 장르별로 분디처리한
												// 값을 저장하는 리스트
		ArrayList<String> mov_nameList;
		ArrayList<String> genre_nameList;

		ArrayList<Integer> director_recommendatedMovieNumList; // 좋아하는 감독 추천 영화
																// 리스트
		ArrayList<Integer> dir_no_list; // 좋아하는 감독 리스트 번호
		ArrayList<String> dir_recommend_name; // 좋아하는 감독 이름
		ArrayList<MovieInfoDTO> dir_recommend_movieInfoList;

		// 좋아하는 장르가 같은 유저들의 정보(아이디와 장르번호)가 들어가는 리스트
		ArrayList<MovieMemberDTO> recommendatedMov_memberInfo = new ArrayList<MovieMemberDTO>();
		int repetition[]; // 중복제거를 위해 반복되는 부분을 집어넣기 위한 배엺
		int like_genre_numArr[]; // 좋아하는 장르들이 들어가는 배열
		// 세션으로 대체 해야함
		HttpSession session = (HttpSession) request.getSession();
		String id = ((UserVO) session.getAttribute("login")).getId();
		// 접속 유저가 좋아하는 장르의 번호들이 들어가는 리스트
		List<Integer> like_gen_list = new ArrayList<Integer>();
		// 좋아하는 장르리스트가 들어가게 된다.
		like_gen_list = movieDAO.findTaste(id);


		// 좋아하는 취향의 장르번호가 들어갈 배열 : 추후 갯수를 정확히 맞추기 위해 배열로 변경처리한다.
		int[] tasteNum = new int[like_gen_list.size()];
		for (int i = 0; i < tasteNum.length; i++) {
			tasteNum[i] = like_gen_list.get(i);
		}

		// 영화의 갯수를 새서 상위 n개의 영화를 가져오기 위한 2차원 배열, 좋아하는 취향이 여러개일 수 있으므로 2차원 배열로
		// 처리함.
		int[][] countArr = new int[like_gen_list.size()][];

		// 각 배열별로 길이는 10000개로 한정하였다. 추후 영화가 10000개가 넘을경우 자유롭게 사이즈를 늘릴수 있도록 관리자
		// 페이지에서 이를 처리해야함.
		for (int i = 0; i < countArr.length; i++) {
			countArr[i] = new int[10000];
		}

		// 나와 취향이 같은 사람의 아이디와 그사람이 좋아하는 해당 장르가 들어가있는 리스트
		recommendatedMov_memberInfo = movieDAO.recommendatedMov_memberInfo(id, tasteNum);

		// countArr배열에 영화의 갯수를 집어 넣어서 인기 순위를 판단한다.
		for (int i = 0; i < recommendatedMov_memberInfo.size(); i++) {

			for (int j = 0; j < tasteNum.length; j++) {
				movieDAO.insertArr(countArr[j], id, tasteNum[j], recommendatedMov_memberInfo.get(i).getId());
			}
		}

		// 추천처리 + null처리
		recommendatedMov = movieDAO.recommendate(id, like_gen_list, recommendatedMov_memberInfo, countArr, tasteNum);

		// 반중복 제거를 위한 배열, 크기를 동기시키기 위해 추천된 영화와 사이즈가 같게 한다.
		repetition = new int[recommendatedMov.size()];

		// 좋아하는 장르의 배열
		like_genre_numArr = new int[like_gen_list.size()];

		for (int i = 0; i < like_genre_numArr.length; i++) {
			like_genre_numArr[i] = like_gen_list.get(i);
		}


		mov_nameList = new ArrayList<String>();
		mov_nameList = movieDAO.findMovName(tasteNum, recommendatedMov);

		
		genre_nameList = new ArrayList<String>();
		genre_nameList = movieDAO.findTasteGenreName(tasteNum, recommendatedMov.size());

	

		// 무의미한곳은 0처리하였기에 그부분 전부 삭제. 단, 아무런 영화도 선택하지 않은 경우고려하여 isEmpty메소드 활용
		int key = 0;
		if (!recommendatedMov.isEmpty()) {
			while (true) {

				if (recommendatedMov.get(key) == 0) {
					recommendatedMov.remove(key);
					mov_nameList.remove(key);
					genre_nameList.remove(key);
				} else {
					key++;
				}

				if (recommendatedMov.size() == key) {
					break;
				}

			}
		} else {// 영화를 추천해달라는 페이지 띄워야함.
			System.out.println("추천할 영화가 없습니다. 영화를 조금더 선택해 주세요! 페이지를 띄워야함");
		}

		System.out.println("장르 추천 영화 테스트");
		for (int i = 0; i < mov_nameList.size(); i++) {

			// 감독추천에서 빼기 위한 부분
			repetition[i] = recommendatedMov.get(i);
		}

		genre_recommendedList = new ArrayList<MovieInfoDTO>();
		genre_recommendedList = movieDAO.makeMovieInfoDto(recommendatedMov, mov_nameList, genre_nameList);

		for (int i = 0; i < genre_recommendedList.size(); i++) {
		}

		// 감독 추천

		director_recommendatedMovieNumList = new ArrayList<Integer>();
		dir_no_list = new ArrayList<Integer>();
		dir_no_list = movieDAO.getFavoriteDirectorList(id);

		for (int i = 0; i < dir_no_list.size(); i++) {
		}

		// 중복 제거된 감독에 의한 추천된 영화가 들어있음
		director_recommendatedMovieNumList = (ArrayList<Integer>) movieDAO.getDirectorRecommendMovieList(id, repetition,
				dir_no_list);
		dir_recommend_name = new ArrayList<String>();
		
		dir_recommend_name = movieDAO.getDirectorNameList(director_recommendatedMovieNumList);
		dir_recommend_movieInfoList = new ArrayList<MovieInfoDTO>();
		dir_recommend_movieInfoList = movieDAO.getMovieInfoList(director_recommendatedMovieNumList);
		
		for (int i = 0; i < dir_recommend_movieInfoList.size(); i++) {
			dir_recommend_movieInfoList.get(i).setDir_name(dir_recommend_name.get(i));
		}
		
		for (int i = 0; i < dir_recommend_movieInfoList.size(); i++) {
		}
		
		model.addAttribute("genre_recommendedList", genre_recommendedList);
		model.addAttribute("director_recommendedList",dir_recommend_movieInfoList);
		
		
	}

}
