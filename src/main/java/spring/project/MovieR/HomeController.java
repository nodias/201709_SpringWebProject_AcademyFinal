package spring.project.MovieR;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.project.admin.dto.GenreDTO;
import spring.project.common.model.PageMaker;
import spring.project.common.model.SearchCriteria;
import spring.project.movie.service.MovieService;
import spring.project.review.service.ReviewService;

@Controller
public class HomeController {
	@Inject
	private ReviewService reviewService;
	@Inject
	private MovieService movieService;
	@RequestMapping(value = "/")
	public String home(SearchCriteria cri, Model model, String sort) throws Exception {
		return "indexGo";
	}

	@RequestMapping(value = "index")
	public String index(SearchCriteria cri, Model model, String sort, GenreDTO genreDTO) throws Exception {
		
		System.out.println("더보기 페이지...");
		model.addAttribute("genreList", movieService.getGenreList(genreDTO));

		System.out.println("error Test");
		if (sort==null) {
			sort = "reg_date";
		}
	System.out.println("sort : "+  sort);
	System.out.println("ReviewController.SearchListPage입니다.");
	cri.setPerPageNum(5);
	model.addAttribute("list", reviewService.list(cri,sort));
	PageMaker pageMaker = new PageMaker();
	pageMaker.setDisplayPageNum(5);
	// pageMaker 입력해야할 순서 cri -> totalCount(DAO)
	pageMaker.setCri(cri);
	pageMaker.setTotalCount(reviewService.getRow(cri));
	System.out.println(pageMaker);
	model.addAttribute("pageMaker", pageMaker);
	// /WEB-INF/views/ + review/list + .jsp
		return "index";
	}
}
