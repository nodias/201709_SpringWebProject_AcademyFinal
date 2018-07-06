package spring.project.movie.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import spring.project.common.model.MovieListServiceData;
import spring.project.member.dto.UserVO;
import spring.project.movie.service.MovieService;

@RestController
@RequestMapping("/movieData/*")
public class MovieRestController {

	@Autowired
	private MovieService movieService;

	@RequestMapping("/evalMoreLoad.jh")
	public void evalMoreLoad(@RequestParam(value = "genre") String genre, @RequestParam(value = "listSize") int listSize, HttpServletResponse response , HttpServletRequest request) {

		int genre_num = 0;


		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (!genre.equals("평균별점TOP영화")) {
			genre_num = movieService.getGenreNumber(genre);
		}

		// 0이면 평균별점 top영화
		HttpSession session = (HttpSession) request.getSession();
		if (session.getAttribute("login") ==null) {
			return;
		}
		String id = ((UserVO) session.getAttribute("login")).getId();
		System.out.println(id);
		// id는 세션으로 대체하여야 한다.

		MovieListServiceData loadPage = new MovieListServiceData(movieService.getGenMovDataList(genre_num, id), out);
		PrintWriter loadData = loadPage.makeData(listSize);

	}
	
	@RequestMapping("/evaluate.jh")
	public void evaluate(@RequestParam(value = "rating") String temp_rating, @RequestParam(value = "movieNum") String temp_movieNum, HttpServletRequest request){
		
		String rating = temp_rating.trim();
		String movieNum = temp_movieNum.trim();
		
		
		HttpSession session = (HttpSession) request.getSession();
		String id = ((UserVO) session.getAttribute("login")).getId();
		
		movieService.ratingInsert(id, movieNum, rating);
		
		
		
	}

}
