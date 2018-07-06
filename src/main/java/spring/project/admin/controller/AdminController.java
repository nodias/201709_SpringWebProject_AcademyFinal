package spring.project.admin.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import spring.project.admin.dto.ActorDTO;
import spring.project.admin.dto.AdMovieDTO;
import spring.project.admin.dto.DirectorDTO;
import spring.project.admin.dto.GenreDTO;
import spring.project.admin.service.AdminService;

@Controller
@RequestMapping("/admin/*")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@RequestMapping(value = "/DirectorList.jh")
	public String DirectorList(@RequestParam(value = "page", required = false) String rPage, Model model) {

		System.out.println("DirectorList Page...");

		adminService.listAll(rPage, "director", model);

		return "admin/directorList";
	}

	
	@RequestMapping("/DirectorWrite.jh")
	public String DirectorWrite(){
		return "admin/director_write";
	}
	
	@RequestMapping("/DirectorWriteProcess.jh")
	public String DirectorWriteProcess(HttpServletRequest request, DirectorDTO dto) throws IllegalStateException, IOException{
		
		String tableName = "director";
		System.out.println("첫번째 에러");
		adminService.dataInsert(request, dto, tableName);
		return "redirect:./DirectorList.jh";
	}
	
	@RequestMapping("/DirectorDeleteProcess.jh")
	public String DirectorDeleteProcess(@RequestParam(value = "delNum") String dnum){
		
		System.out.println("DirectorDeleteProcess...");
		
		adminService.dataDelete(dnum, "director");
	
		return "redirect:./DirectorList.jh";
	}
	
	@RequestMapping(value = "/GenreList.jh")
	public String GenreList(@RequestParam(value = "page", required = false) String rPage, Model model) {

		System.out.println("GenreList Page...");

		adminService.listAll(rPage, "genre", model);

		return "admin/genreList";
	}

	@RequestMapping("/GenreWrite.jh")
	public String GenreWrite(){
		return "admin/genre_write";
	}
	
	@RequestMapping("/GenreWriteProcess.jh")
	public String GenreWriteProcess(HttpServletRequest request, GenreDTO dto) throws IllegalStateException, IOException{
		
		String tableName = "genre";
		System.out.println("genreWriteProcess...");
		adminService.dataInsert(request, dto, tableName);
		return "redirect:./GenreList.jh";
	}
	
	@RequestMapping("/GenreDeleteProcess.jh")
	public String GenreDeleteProcess(@RequestParam(value = "delNum") String dnum){
		
		System.out.println("GenreDeleteProcess...");
		
		adminService.dataDelete(dnum, "genre");
	
		return "redirect:./GenreList.jh";
	}
	
	@RequestMapping(value = "/ActorList.jh")
	public String ActorList(@RequestParam(value = "page", required = false) String rPage, Model model) {

		System.out.println("ActorList Page...");

		adminService.listAll(rPage, "actor", model);

		return "admin/actorList";
	}

	@RequestMapping("/ActorWrite.jh")
	public String ActorWrite(){
		return "admin/actor_write";
	}
	
	@RequestMapping("/ActorWriteProcess.jh")
	public String ActorWriteProcess(HttpServletRequest request, ActorDTO dto) throws IllegalStateException, IOException{
		
		String tableName = "actor";
		System.out.println("actorWriteProcess...");
		adminService.dataInsert(request, dto, tableName);
		return "redirect:./ActorList.jh";
	}
	
	@RequestMapping("/ActorDeleteProcess.jh")
	public String ActorDeleteProcess(@RequestParam(value = "delNum") String dnum){
		
		System.out.println("ActorDeleteProcess...");
		
		adminService.dataDelete(dnum, "Actor");
	
		return "redirect:./ActorList.jh";
	}
	@RequestMapping(value = "/MovieList.jh")
	public String MovieList(@RequestParam(value = "page", required = false) String rPage, Model model) {

		System.out.println("MovieList Page...");

		adminService.listAll(rPage, "movie_info", model);

		return "admin/movieList";
	}
	
	
	@RequestMapping("/MovieWrite.jh")
	public String MovieWrite(Model model){
		System.out.println("MovieWrite...");
		
		model.addAttribute("genreList", adminService.getAllData("genre"));
		model.addAttribute("directorList", adminService.getAllData("director"));
		model.addAttribute("actorList", adminService.getAllData("actor"));
		
		return "admin/movie_write";
	}
	
	
	@RequestMapping("/MovieWriteProcess.jh")
	public String MovieWriteProcess(@RequestParam(value ="director") String[] dir, @RequestParam(value ="mainActor") String[] ma,
			@RequestParam(value ="subActor") String[] sa, @RequestParam(value ="genre") String[] ge, 
			HttpServletRequest request, AdMovieDTO dto) throws IllegalStateException, IOException{

		
		adminService.dataInsert(request, dto, "movie_info");
		System.out.println("영화 기본정보 등록 완료");
		
		//최신으로 등록한 영화 번호 불러오는 메소드
		int recentMovieNum = adminService.getRecentMovNum();
		System.out.println("최근에 등록한 영화 번호 : " + recentMovieNum);
		
		adminService.relationInsert(dir, recentMovieNum, "dir_rel");
		adminService.relationInsert(ma, recentMovieNum, "ma_rel");
		adminService.relationInsert(sa, recentMovieNum, "sa_rel");
		adminService.relationInsert(ge, recentMovieNum, "ge_rel");
		return "redirect:./MovieList.jh";

	}

}
