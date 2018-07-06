package spring.project.admin.service;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface AdminService {

	public void delete();
	public void write();
	
	public void listAll(String rPage, String type, Model model);
	public void dataDelete(String dnum, String tableName);
	public void dataInsert(HttpServletRequest request, Object all_dto, String tableName) throws IllegalStateException, IOException;
	public Object getAllData(String tableName);
	public int getRecentMovNum();
	public void relationInsert(String[] rel_type, int recentMovieNum, String tableName);
}
