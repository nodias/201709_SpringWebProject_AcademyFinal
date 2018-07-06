package spring.project.movie.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import spring.project.admin.dto.GenreDTO;

public interface MovieService {

	public ArrayList<Object> getGenreList(GenreDTO genreDTO);

	public int getGenreNumber(String genre);

	public ArrayList<Object> getGenMovDataList(int genre_num, String id);

	public void ratingInsert(String id, String movieNum, String rating);

	

	public void recommendation(Model model, HttpServletRequest request);
	
	
}
