package spring.project.admin.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import spring.project.admin.dao.AdminDAO;
import spring.project.admin.dto.ActorDTO;
import spring.project.admin.dto.AdMovieDTO;
import spring.project.admin.dto.DirectorDTO;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDAO adminDAO;

	@Override
	public void listAll(String rPage, String type, Model model) {

		ArrayList<Object> dataList = new ArrayList<Object>();

		int page = 1;
		int limit = 10;

		if (rPage != null) {
			page = Integer.parseInt(rPage);
		}

		int listcount = adminDAO.getListCount(type);

		System.out.println(type + "사이즈: " + listcount);
		dataList = adminDAO.getDataList(page, limit, type);

		int maxpage = (int) ((double) listcount / limit + 0.95);
		int startpage = (((int) ((double) page / 10 + 0.9)) - 1) * 10 + 1;
		int endpage = startpage + 10 - 1;
		if (endpage > maxpage) {
			endpage = maxpage;
		}

		model.addAttribute("page", page);
		model.addAttribute("maxpage", maxpage);
		model.addAttribute("startpage", startpage);
		model.addAttribute("endpage", endpage);
		model.addAttribute("listcount", listcount);
		model.addAttribute("dataList", dataList);

	}

	@Override
	public void delete() {

	}

	@Override
	public void write() {

	}

	@Override
	public void dataDelete(String dnum, String tableName) {

		int delNum = Integer.parseInt(dnum);
		adminDAO.dataDelete(delNum, tableName);

	}

	// 데이터 삽입
	@Override
	public void dataInsert(HttpServletRequest request, Object dto, String tableName)
			throws IllegalStateException, IOException {

		String realFolder = "";
		String saveFolder = "";
		if (tableName.equals("director")) {
			saveFolder = "./resources/directorUpload";

		} else if (tableName.equals("actor")) {
			saveFolder = "./resources/actorUpload";
		}else if(tableName.equals("movie_info")){
			saveFolder = "./resources/movieUpload";
		}

		realFolder = request.getSession().getServletContext().getRealPath(saveFolder);
		realFolder += "\\";

		System.out.println("경로 이름 : " + realFolder);
		MultipartFile uploadFile = null;

		if (dto instanceof DirectorDTO) {
			uploadFile = ((DirectorDTO) dto).getUpImg();
		} else if (dto instanceof ActorDTO) {
			uploadFile = ((ActorDTO) dto).getUpImg();
		} else if (dto instanceof AdMovieDTO) {
			uploadFile = ((AdMovieDTO) dto).getUpImg();
		}

		if (uploadFile != null && (!uploadFile.isEmpty())) {

			String fileName = uploadFile.getOriginalFilename();

			if (dto instanceof DirectorDTO) {
				((DirectorDTO) dto).setImg(fileName);
			} else if (dto instanceof ActorDTO) {
				((ActorDTO) dto).setImg(fileName);
			} else if (dto instanceof AdMovieDTO){
				((AdMovieDTO)dto).setPoster(fileName);
			}
			uploadFile.transferTo(new File(realFolder + fileName));

		}

		adminDAO.dataInsert(dto, tableName);

	}

	// 해당하는 테이블의 모든 데이터 가져오는 메소드
	@Override
	public Object getAllData(String tableName) {
		return adminDAO.getAllData(tableName);

	}

	//가장 최근에 등록된 영화번호 리턴하는 메소드
	@Override
	public int getRecentMovNum() {
		
		return adminDAO.getRecentMovNum();
	}

	//감독, 주연, 조연, 장르의 복수 등록 처리
	@Override
	public void relationInsert(String[] rel_type, int recentMovieNum, String tableName) {
		for(int i=0; i < rel_type.length; i++){
			String[] relData = rel_type[i].split(":");
			adminDAO.relDataInsert(tableName, recentMovieNum, relData[0]);
			
		}
		
	}

}
