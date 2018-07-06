package spring.project.movie.dao;

import java.util.ArrayList;
import java.util.List;

import spring.project.admin.dto.GenreDTO;
import spring.project.movie.dto.MovieInfoDTO;
import spring.project.movie.dto.MovieMemberDTO;

public interface MovieDAO {

	ArrayList<Object> getGenreList(GenreDTO genreDTO);

	int getGenreNumber(String genre);

	ArrayList<Object> getGenMovDataList(int genre_num, String id);

	void ratingInsert(String id, String movieNum, String rating);

	List<Integer> findTaste(String id);

	ArrayList<MovieMemberDTO> recommendatedMov_memberInfo(String id, int[] tasteNum);

	void insertArr(int[] countArr, String id, int tasteNum, String others);

	ArrayList<Integer> recommendate(String id, List<Integer> like_gen_list,
			ArrayList<MovieMemberDTO> recommendatedMov_memberInfo, int[][] countArr, int[] tasteNum);

	ArrayList<String> findMovName(int[] tasteNum, ArrayList<Integer> recommendatedMov);

	ArrayList<String> findTasteGenreName(int[] tasteNum, int size);

	ArrayList<MovieInfoDTO> makeMovieInfoDto(ArrayList<Integer> recommendatedMov, ArrayList<String> mov_nameList,
			ArrayList<String> genre_nameList);

	ArrayList<Integer> getFavoriteDirectorList(String id);

List<Integer> getDirectorRecommendMovieList(String id, int[] repetition, ArrayList<Integer> dir_no_list);

ArrayList<String> getDirectorNameList(ArrayList<Integer> director_recommendatedMovieNumList);

ArrayList<MovieInfoDTO> getMovieInfoList(ArrayList<Integer> director_recommendatedMovieNumList);

	
}
