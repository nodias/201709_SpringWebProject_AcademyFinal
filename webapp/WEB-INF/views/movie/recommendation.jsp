<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" href="../resources/css/hoverImage.css">
<link rel="stylesheet" href="../resources/css/star-rating.min.css" media="all"
	type="text/css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="../resources/js/star-rating.js" type="text/javascript">
	
</script>
<script src="../resources/js/evaluate.js" type="text/javascript"></script>
<style>
.wrappedContent {
	width: 1100px;
	margin: 0 auto;
}

.thumbnail {
	width: 170px;
	height: 250px;
	padding: 0;
}
.test{
font-size: 8pt;
}
</style>
</head>
<body>


	<div class="wrappedContent">
	
		<div class="thumbnails">

			<c:forEach items="${genre_recommendedList }" var="movie">
			
				<div class="col-lg-2">
					<div class='thumbnail'>
						<a href="http://www.nate.com"> <img
							src='../resources/movieUpload/${movie.getPoster() }' alt='ALT NAME'
							width="165px" height="245px">
						</a>
						<div class='caption'>
							<div class="mTitle">${movie.getTitle() }
								<input type="hidden" value="${movie.getMov_no() }">
							</div>
							<input id='input-4' name='input-4' class='rating rating-loading'
								data-show-clear='false' data-show-caption='true' data-size='xs'>
								<div class="test"><a href='#'><span class="glyphicon glyphicon-heart">보고싶어요</span></a><a href='http://www.nate.com'><span class="glyphicon glyphicon-pencil">코멘트쓰기</span></a></div>
							

						</div>
					</div>
					<p align="center">${sessionScope.login.id}님께서 좋아하는 좋아하는 ${movie.getGen_name()}장르</p>
					<%-- 					<p align="center">${sessionScope.id }님께서 좋아하는 좋아하는 장르 추천</p> --%>
				</div>
			</c:forEach>

			<c:forEach items="${director_recommendedList }" var="movie">
				<div class="col-lg-2">
					<div class='thumbnail'>
						<a href="http://www.nate.com"> <img
							src='../resources/movieUpload/${movie.getPoster() }' alt='ALT NAME'
							width="165px" height="245px">
						</a>
						<div class='caption'>
							<div class="mTitle">${movie.getTitle() }
							<input type="hidden" value="${movie.getMov_no() }"></div>
							<input id='input-4' name='input-4' class='rating rating-loading'
								data-show-clear='false' data-show-caption='true' data-size='xs'>
						<div class="test"><a href='http://www.naver.com'><span class="glyphicon glyphicon-heart">보고싶어요</span></a><a href='http://www.nate.com'><span class="glyphicon glyphicon-pencil">코멘트쓰기</span></a></div>

						</div>
					</div>
					<p align="center">admin님께서 좋아하는 ${movie.getDir_name() }감독의 작품</p>
					<%-- 					<p align="center">${sessionScope.id }님께서 좋아하는 좋아하는 장르 추천</p> --%>
				</div>
			</c:forEach>


		</div>
		
	</div>

	


</body>
</html>