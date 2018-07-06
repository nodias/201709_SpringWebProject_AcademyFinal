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
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<style>
#mInfor, #attached_file {
	width: 250px;
}

#summary {
	width: 500px;
}
</style>
<script>
	$(function() {
		$('#appendDirector').click(function() {

			var str = '<select class="form-control"	id="mInfor" name="director">';
			str += '<c:forEach var="directorList" items="${directorList}">';
			str += '<option>${directorList.dir_no }:${directorList.name } </option>';
			str += '</c:forEach></select>';

			$("#directorDiv").append(str);

		});
		
		$('#appendMainActor').click(function() {

			var str = '<select class="form-control"	id="mInfor" name="mainActor">';
			str += '<c:forEach var="actorList" items="${actorList}">';
			str += '<option>${actorList.act_no}:${actorList.name}</option>';
			str += '</c:forEach></select>';

			$("#mainActorDiv").append(str);

		});
		
		$('#appendSubActor').click(function() {

			var str = '<select class="form-control"	id="mInfor" name="subActor">';
			str += '<c:forEach var="actorList" items="${actorList}">';
			str += '<option>${actorList.act_no}:${actorList.name}</option>';
			str += '</c:forEach></select>';

			$("#subActorDiv").append(str);
	
		});
		
		$('#appendGenre').click(function() {

			var str = '<select class="form-control"	id="mInfor" name="genre">';
			str += '<c:forEach var="genreList" items="${genreList}">';
			str += '<option>${genreList.gen_no}:${genreList.name}</option>';
			str += '</c:forEach></select>';

			$("#genreDiv").append(str);

		});

	});
</script>
<body>

	<div class="container">
		<h1>영화 등록</h1>
		<form method="post" action="./MovieWriteProcess.jh" enctype="multipart/form-data">
		
			
		
			<div class="form-group">
				<input class="form-control" id="mInfor" type="text" placeholder="제목" name="title">
			</div>
			<div class="form-group">
				<input class="form-control" id="mInfor" type="text"
					placeholder="예고편" name="trailer">
			</div>

			<div class="form-group">
			<label>포스터 사진</label>
				<input class="form-control" id="attached_file" type="file"
					placeholder="포스터SRC" name="upImg">
			</div>

			<div class="form-group">
				<input class="form-control" id="mInfor" type="text"
					placeholder="개봉일" name="release">
			</div>

			<div class="form-group">
				<input class="form-control" id="mInfor" type="text"
					placeholder="러닝타임" name="rtime">
			</div>

			<div class="form-group">
				<input class="form-control" id="mInfor" type="text"
					placeholder="누적관객수" name="attd">
			</div>


			<div class="form-group">
				<input class="form-control" id="mInfor" type="text" placeholder="국가" name="country">
			</div>
			<!--  	주연,조연,장르는 추가를 해야 하는경우 ajax로 처리할 것 , 그리고 jquery로 내용지우고 append하는 형실으로 처리해야할 듯-->

			<div class="form-group">
				<label for="sel1">관람등급</label> <select class="form-control"
					id="mInfor" name="grade">
					<option>12세</option>
					<option>15세</option>
					<option>19세</option>
					<option>전체연령</option>
				</select>
			</div>
	
			

			<div class="form-group" id="directorDiv">
				<label for="sel1">감독</label> <select class="form-control"
					id="mInfor" name="director" >
					<c:forEach var="directorList" items="${directorList }">
						<option>${directorList.dir_no }:${directorList.name } </option>
						
					</c:forEach>
				</select>
				<button id="appendDirector" type="button">추가</button>
			</div>

			<div class="form-group" id="mainActorDiv">
				<label for="sel1">주연</label> <select class="form-control"
					id="mInfor" name="mainActor">
					<c:forEach var="actorList" items="${actorList }">
						<option>${actorList.act_no}:${actorList.name }</option>
					</c:forEach>
				</select>
				<button id="appendMainActor" type="button">추가</button>
			</div>

			<div class="form-group" id="subActorDiv">
				<label for="sel1">조연</label> <select class="form-control"
					id="mInfor" name="subActor">
					<c:forEach var="actorList" items="${actorList }">
						<option>${actorList.act_no}:${actorList.name }</option>
					</c:forEach>
				</select>
				<button id="appendSubActor" type="button">추가</button>
			</div>


			<div class="form-group" id="genreDiv">
				<label for="sel1">장르</label> <select class="form-control"
					id="mInfor" name="genre">
					<c:forEach var="genreList" items="${genreList }">
						<option>${genreList.gen_no}:${genreList.name }</option>
					</c:forEach>


				</select>
				<button id="appendGenre" type="button">추가</button>
			</div>


			<div class="form-group">
				<label for="inputdefault">줄거리</label> <input class="form-control"
					id="summary" type="text" name="summary">
			</div>

			<button type="submit" class="btn btn-success">영화 등록</button>
		</form>
	</div>

</body>
</html>