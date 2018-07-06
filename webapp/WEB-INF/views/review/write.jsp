<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>----MovieR Review Write----</title>
<script type="text/javascript">
	
	$(function() {
		$("#listBtn").on("click", function() {
			location = "list"; // get 방식으로 이동
		});
	});
	$(function() {
		$("#cancelBtn").on("click", function() {
			location = "list"; // get 방식으로 이동
		});
		
	});
</script>

<style type="text/css">
#all {
	width: 1000px;
	margin: 0 auto 0 auto;
}

#listBtn {
 	margin-right: 5px; 
 	float: right; 
}

#btn {
	float: center;
	margin-left: 400px;
}

#ui {
	list-style: none;
}


</style>
</head>
<body>
	<br>
	<h3 id="all">리뷰쓰기</h3>

	<div id="all">
		<button class="btn btn-warning" id="listBtn">목록보기</button>
	</div>
	<br>
	<form action="write" method="post" id="all" role="form">
		<div>
			<br>
			<br>
			<label id="mov_no" for="mov_no">&nbsp;&nbsp;영 화&nbsp;</label>  
			<select name="mov_no" id="select" class="form-control">	
			<c:forEach var="mov" items="${getMov_no}" varStatus="status">
				<option value="${mov.mov_no}">${mov.title}</option>
			</c:forEach>
			</select>
		</div>

		<div>
			<br> <label for="title">&nbsp;&nbsp;제 목&nbsp;</label> 
			<input 	id="title" name="title" type="text" placeholder="리뷰 제목을 입력하세요."
				style="text-align: left;" required="required" class="form-control"
				pattern=".{4,50}" />
		</div>

		<br>
		<div>
			<br><label for="summary">&nbsp;&nbsp;내 용&nbsp;</label>
			<textarea id="summary" name="summary" cols="100" rows="10"
				class="form-control" placeholder="내용을 입력하세요." required="required"></textarea>
			<br>
		</div>

		<div>
			<label for="id">&nbsp;&nbsp;작성자&nbsp;</label>
			<input type="text" name="id" value="${sessionScope.login.id}" readonly="readonly" 
			class="form-control"/>
			<br>
		</div>
		<br>
<!-- 		<div class="form-group"> -->
<!-- 			<label for="uploadFile">&nbsp;첨부파일</label>  -->
<!-- 			<input id="uploadFile" name="uploadFile" class="form-control"  -->
<%-- 			required="required" type="text" value="${mov.poster}"/> --%>
<!-- 		</div> -->

		<div>
			<br>
			<ul id="ui">
				<li>영화 리뷰는 1,000자 (공백포함)이내로 작성해주시기 바랍니다.
				(첨부파일은 이미지 파일(JPG,GIF,PNG)만 업로드 가능합니다.)</li>
				<li>운영 원칙에 어긋나는 불량게시물로 판단되는 게시물은 적발시 해당 게시물의 삭제와 동시에 글쓰기 금지,
					게시자에 대한 징계 조치가 이루어질 수</li>
				<li>있습니다. 위 내용에도 불구하고 운영자에 의한 게시물 삭제 횟수가 누적이 되는 경우 누적 정도에 따라서
					경고, 일시정지 ,영구 이용정지 등 단계적</li>
				<li>으로 제한될수 있습니다.단,음란게시물 작성 등 그 위반정도가 중한 경우 누적 정도와 관계없이 일시정지 또는
					영구이용정지 될 수 있습니다.</li>
			</ul>
			<br>
		</div>
		<div id="all">
			<button type="submit" id="btn" class="btn btn-default">확인</button>
			<button type="button" class="btn btn-basic" id="cancelBtn">취소</button>
		</div>
		<br> <br>
	</form>
</body>
</html>