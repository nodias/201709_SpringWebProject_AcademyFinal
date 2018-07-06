<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>----MovieR Review----</title>
<!-- <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> -->

<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> -->

<!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->
<!-- <link rel='stylesheet' href='./resources/css/hoverImage.css'> -->
<!-- <link href='./resources/css/star-rating.min.css' media='all' rel='stylesheet' type='text/css'> -->
<!-- <script src='./resources/js/star-rating.js' type='text/javascript'></script> -->
<!-- <script src='./resources/js/evaluate.js' type='text/javascript'></script> -->
<script type="text/javascript">

	var result = "${msg}"
	if (result == "WRITESUCCESS")
		alert("리뷰가 등록되었습니다.");
	else if (result == "DELETESUCCESS")
		alert("리뷰가 삭제되었습니다.");
	
	$(function() {
		$("#reviewBtn").on("click", function() {
			location = "review/list"; //get방식
		});
		
		$(function() {
			$("#evalBtn").on("click", function() {
				location = "movie/evalMore.jh"; //get방식
			});
		                           
		$("#sort").on("change", function(){
// 			alert($("#sort").val());
			location.href="review/list?sort="+$("#sort").val();
			});
		
	});
	
	});
	$(function() {

		var thest = $(".firstTab").find('a').attr("href");
		
		var genre = $(".firstTab").find("input").val();
		
		$.ajaxSetup({ async:false });
		
		$.ajax({
			url : "./movieData/evalMoreLoad.jh?genre=" + genre+"&listSize=8",
			success : function(data) {
				
				$(".nav-pills li[class=active]").removeClass(
						'active'), $(this).attr({
					"class" : "active"

				}), $('#' + thest.substr(1)).html(data)

			}, 
			error:function(){
				alert("AJAX Failed!");
			}
		});
		
		
		
		
		
		$(".othersTab, .firstTab").click(
				function() {

					
					var thest = $(this).find('a').attr("href");
					//alert(thest);

					// 					var test = ${movieSize};
					var genre = $(this).find("input").val();
					// 					alert(test);

					$.ajax({
						//type : "get",
						//data : genre,
						url : "./movieData/evalMoreLoad.jh?genre=" + genre+"&listSize=8",
						success : function(data) {
							 
							$(".nav-pills li[class=active]").removeClass(
									'active'), $(this).attr({
								"class" : "active"

							}), $('#' + thest.substr(1)).html(data)

						}, 
						error:function(){
							alert("AJAX Failed!");
						}

					});

				});

	});
</script>

<c:set value="${genreList}" var="genre" />
<c:set value="${genreList}" var="genre2" />
<c:set value="${firstGenre}" var="firstGenre" />

<style type="text/css">
.firstTab, .othersTab {
	width: 145px;
}

.changeTest {
	margin-left: 150px;
	margin-top: 10px;
}

#all {
	width: 1000px;
	margin: 0 auto 0 auto;
}

#search {
/* 	width : 1000px; */
	margin: 0 auto 10 auto;
	float: right; 	
}

#sort {
/* 	width : 1000px; */
	margin: 0 auto 10 auto;
	float: right; 	
}

#font{
	font-size: 18pt;
}

#title_font{
	font-size: 12pt;
}

#imgList{
	margin: 0;
	padding: 0;
}

.pagination{
	float: right;
}

.frame{border:1px solid #F1F1F1;
	background-color : #F9F9F9;
	width: 450px;
	margin: 0;
	padding: 0;
	border-radius: 10px;
	float: left;
}

.frame2{border:1px solid #F1F1F1;
	background-color : #F9F9F9;
	bac
	width: 450px;
	margin: 0;
	padding: 0;
	border-radius: 10px;
	float: right;
}

.frame3{border:1px solid #F1F1F1;
	background-color : #F9F9F9;
	width: 1200px;
	margin: 0;
	padding: 0;
	border-radius: 10px;
	float: none;
}


</style>
</head>
<body>
<div id="all">
	<div class="frame3">
	<strong id="font">&nbsp;&nbsp; 영화 평가를 늘려주세요</strong>&nbsp;&nbsp;&nbsp;
		<button class="btn btn-primary" id="evalBtn">더 보기</button>
		<div class="container">

		<div class="col-lg-1 sidenav"> 
			<ul class="nav nav-pills">
				<li class="firstTab active"><a data-toggle="tab"
					href="#0"><input class="tt" type="hidden"
						name="평균별점TOP영화" value="평균별점TOP영화">평균별점TOP영화</a></li>
				<c:forEach items="${genre}" var="genre" varStatus="status" begin="1" 
					step="1">
					<li class="othersTab"><a data-toggle="tab"
						href="#${genre.gen_no}"><input class="tt" type="hidden"
							name="${genre.name}" value="${genre.name}">${genre.name}</a></li>
				</c:forEach>



			</ul>
		</div>
		<div class="tab-content changeTest">


			<div id="0" class="tab-pane fade in active "></div>
	
			<c:forEach items="${genre2}" var="genre2" varStatus="status"
				begin="1" step="1">
				<div id="${genre2.gen_no}" class="tab-pane fade"></div>
			</c:forEach>
		</div>
	</div>
	</div>
	</div>
	<br />
<div id="all">
	<div class="frame">
		<strong id="font">&nbsp;&nbsp;새로올라온 리뷰</strong>&nbsp;&nbsp;&nbsp;
		<button class="btn btn-primary" id="reviewBtn">더 보기</button>
		<table class="frame">
		
		<c:if test="${empty list }">
		<tr><td colspan="6">데이터가 존재하지 않습니다.</td></tr>
		</c:if>
		<!-- 데이터가 있는 경우에만 여기를 반복 한다. 반복시작 -->
		<c:if test="${! empty list }">
			<div id="imgList">
			<c:forEach var="mov" items="${list }">
				<tr id="title_font" >
					<td rowspan ="2" width="100px">
					<img src="./resources/movieUpload/${mov.poster}" alt="image" width="100px" height="120px"></td>
					<td colspan="1">
					<font color="#000080"><strong>[ ${mov.title_1 } ]</strong></font>&nbsp;&nbsp;
					<a href="review/view${pageMaker.makeQuery() }&no=${mov.review_no }">${mov.title }</a>
				</tr>
				
				<tr>
					<td rowspan="1">
					<c:choose>
						<c:when test="${fn:length(mov.summary)>80 }">
							<c:out value="${fn:substring(mov.summary,0,80)}" />.....
						</c:when>
						<c:otherwise>
						<br>${mov.summary }<br>
						</c:otherwise>					
					</c:choose>
					<br>
				</tr>
				
			</c:forEach>
			</div>
			
			<!-- 여기까지 반복 -->
			</c:if>
			
			
		</table>
	</div>
	</div>
	
	
	
	
	
	
	
	<br />
	<br />
</body>
</html>