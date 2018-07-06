<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- <link href='../css/star-rating.min.css' media='all' rel='stylesheet' type='text/css'> -->
<!-- <link rel="stylesheet" href="../css/hoverImage.css"> -->
<!-- <link rel="stylesheet" -->
<!-- 	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"> -->

<!-- <script -->
<!-- 	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script> -->

<!-- <script -->
<!-- 	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->
<!-- <script src='../js/star-rating.js' type='text/javascript'></script> -->
<!-- <script src='../js/evaluate.js' type='text/javascript'></script> -->

<style>
.firstTab, .othersTab {
	width: 145px;
}

.changeTest {
	margin-left: 150px;
	margin-top: 10px;
}
</style>

<c:set value="${genreList}" var="genre" />
<c:set value="${genreList}" var="genre2" />
<c:set value="${firstGenre}" var="firstGenre" />

<script>
	$(function() {

		var thest = $(".firstTab").find('a').attr("href");
		
		var genre = $(".firstTab").find("input").val();
		
		$.ajaxSetup({ async:false });
		
		$.ajax({
			url : "../movieData/evalMoreLoad.jh?genre=" + genre+"&listSize=0",
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
						url : "../movieData/evalMoreLoad.jh?genre=" + genre +"&listSize=0",
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
</head>
<body>
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
				begin="1" step="1" end="${genre2.size()}">
				<div id="${genre2.gen_no}" class="tab-pane fade"></div>
			</c:forEach>


		</div>


	</div>




</body>
</html>