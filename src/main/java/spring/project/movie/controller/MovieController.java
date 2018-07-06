package spring.project.movie.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.project.admin.dto.GenreDTO;
import spring.project.member.dto.UserVO;
import spring.project.movie.service.MovieService;

@Controller
@RequestMapping("/movie/*")
public class MovieController {

	@Autowired
	private MovieService movieService;

	@RequestMapping("/evalMore.jh")
	public String evalMore(GenreDTO genreDTO, Model model) {
		System.out.println("더보기 페이지...");
		model.addAttribute("genreList", movieService.getGenreList(genreDTO));

		return "./movie/evalmore";
	}
	
	@RequestMapping("/recommendation.jh")
	public String recommendation(Model model, HttpServletRequest request){
		
		movieService.recommendation(model,request);
		
		return "movie/recommendation";
	}


}
