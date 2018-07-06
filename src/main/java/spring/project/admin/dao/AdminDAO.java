package spring.project.admin.dao;

import java.util.ArrayList;

import spring.project.admin.dto.GenreDTO;

public interface AdminDAO {

	
	public void dataDelete(int delNum, String tableName);
	
	public void dataInsert(Object dto, String tableName);
	
	public ArrayList<Object> getDataList(int page, int limit, String tableName);
	
	public ArrayList<Object> getAllData(String tableName);
	
	public ArrayList<GenreDTO> getGenreList();
	
	public int getListCount(String tableName);
	
	public int getMoveNum();
	
	public void relDataInsert(String tableName, int movieNum, String splitData);

	public int getRecentMovNum();
}
