package spring.project.common.model;

import java.io.PrintWriter;
import java.util.ArrayList;

import spring.project.movie.dto.MovieInfoDTO;

public class MovieListServiceData {

	ArrayList<Object> movieList;
	PrintWriter out;
	int listSize;
	
	public MovieListServiceData(ArrayList<Object> arrayList, PrintWriter out){
		this.movieList = arrayList;
		this.out = out;
	}

	public PrintWriter makeData(int listSize) {
		// TODO Auto-generated method stub
		
//		out.println(" <link rel='stylesheet' href='../css/hoverImage.css'>");
		out.println(" <link rel='stylesheet' href='http://localhost:8081/MovieR/resources/css/hoverImage.css'>");
		out.println("<link href='http://localhost:8081/MovieR/resources/css/star-rating.min.css' media='all' rel='stylesheet' type='text/css'>");
		out.println("<script src='http://localhost:8081/MovieR/resources/js/star-rating.js' type='text/javascript'></script>");
		out.println("<script src='http://localhost:8081/MovieR/resources/js/evaluate.js' type='text/javascript'></script>");

		
		out.print("<div class='thumbnails'>");
		
		if (movieList.size() < 8 || listSize==0) {
			listSize = movieList.size();
		}
		for (int i = 0; i < listSize; i++) {

			out.print("<div class='col-lg-3' style='min-height:350px;'>");
			out.print("<div class='thumbnail main_thumbnail'>");
			// out.print("<div class='img' style='width:100%; height:
			// 100%;'");
			out.print("<a href='http://www.nate.com'>");
			out.print("<img src='http://localhost:8081/MovieR/resources/movieUpload/" + ((MovieInfoDTO) movieList.get(i)).getPoster()
					+ "' alt='ALT NAME' width='100%' height='100%'>");
			out.print("</a>");
			// 아래 7줄이 숨겨져야 함 
			out.print("<div class='caption'>");
			out.print("<div class='mTitle'>");
			out.print(((MovieInfoDTO) movieList.get(i)).getTitle());
			out.print("<input type='hidden' name='movieName' value='" + ((MovieInfoDTO) movieList.get(i)).getMov_no() +"'>");
			out.print("</div>");
			// out.print("<p>Description</p>");
			// out.print("<div class='starLink'>");
			out.print(
					"<input id='input-4' name='input-4' class='rating rating-loading' data-show-clear='false' data-show-caption='true' data-size='xs'>");
			// out.print("</div>");

			out.print("<p><div class='test'><a href='#'><span class='glyphicon glyphicon-heart'>보고싶어요</span></a><a href='http://www.nate.com'><span class='glyphicon glyphicon-pencil'>코멘트쓰기</span></a></div></p>");
			
			out.print("</div>");

			out.print("</div></div>");
		}
		out.print("</div>");
		
		return out;
	};
	
	
}
