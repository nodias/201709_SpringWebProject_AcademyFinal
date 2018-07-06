<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<title>----MovieR Review View----</title>
<script type="text/javascript">

	$(function() {
		
		// 글을 성공적으로 수정하고 돌아온 경우 한번만 경고 창을 띄운다.
		var result = "${msg }";
		if (result == "UPDATESUCCESS")
			alert("글이 성공적으로 수정되었습니다.");

		// 	var no = $("#no").text();
		// 글수정 ,글삭제, 목록 버튼 처리
		$("#updateBtn").on("click", function() {
		location="update?page=${cri.page}&perPageNum=${cri.perPageNum}no="+no; // get 방식으로 이동
			//form tag의 submit을 이용해서 넘긴다. 넘어갈 uri를 action 속성으로 셋팅한다.
			$("#viewForm").attr("action", "update").submit();
		});
		$("#deleteBtn").on("click", function() {
		location="delete?no="+no; // get 방식으로 이동
			// form tag의 submit을 이용해서 넘긴다. 넘어갈 uri를 action 속성으로 셋팅한다.
			$("#viewForm").attr("action", "delete").submit();
		});
		$("#listBtn").on("click", function() {
		location="list"; // get 방식으로 이동
			// form tag의 submit을 이용해서 넘긴다. 넘어갈 uri를 action 속성으로 셋팅한다.
		$("#viewForm").attr("action", "list").submit();
		});

		var no = $("#no").text();
	});
</script>

<script type="text/javascript">
	$(function(){
	$("#clickBtn").click(function(){
		$(this).text("♡");
		$(this).css("background-color","#ff4d4d");
		$(this).css("color","#ffffff");
	});
	});
</script>

<style type="text/css">
#all {
	width: 1000px;
	margin: 0 auto 0 auto;
}

#search {
	/* 	width : 1000px; */
	margin: 0 auto 10 auto;
	float: right;
}

#listBtn {

 	margin-right:5px; 
	float: right;
}

#updateBtn{
	float: center;
	margin-left: 400px;
}

#title{
	float: center;
	margin-left: 800px;
}

</style>
</head>
<body>

	<!-- 넘겨야 할 데이터를 hidden으로 만들어서 안보이게 넘기는 form을 만든다. -->
	<form id="viewForm" method="get">
		<input name="no" type="hidden" value="${view.review_no }" /> 
		<input name="page" type="hidden" value="${cri.page }" /> 
		<input name="perPageNum" type="hidden" value="${cri.perPageNum }" />
	<div id="all">
		<br>
		<h3 id="all">리뷰</h3>
		<div id="listBtn">
		<button class="btn btn-warning" id="listBtn">목록보기<br></button><br>
		
		</div><br><br>
		<table class="table">
			<tr>
			<td>	
			<label id="mov_title" for="mov_title">&nbsp;&nbsp;영   화&nbsp;</label>  
			<font size="4">&nbsp;&nbsp;
			<input name="mov_title" id="select" value="${view.title_1}" 
			style="border: none;" readonly="readonly"></font>		
			</td>
			</tr>
			<tr>
				<!-- 타이틀 -->
				<td id="title">
				<font size="5px">&nbsp;${view.title}</font></td>
				<tr>
				<td><font color="#606060">
				&nbsp;<fmt:parseDate value="${view.reg_date}" var="dateFmt" pattern="yyyy-MM-dd HH:mm:ss"/>
				<fmt:formatDate value="${dateFmt}" pattern="yyyy.MM.dd"/></font></td>
				<tr>
				</tr>
				
				<tr>
				<!-- 작성자 -->
				<td>
				<label id="id" for="id">&nbsp;&nbsp;작성자&nbsp;&nbsp;</label>  
				<input name="id" id="id" value="${view.id}" style="border: none;" readonly="readonly">
					
				
<%-- 				<a href="list${pageMaker.makeQuery() }&no=${review.review_no }"> --%>
<%-- 				${review.id} 님의 모든 리뷰 보기 ▷</a></td> --%>
				<!-- id로 검색해서 ?????????? -->
				
				<tr>
				
				<td> &nbsp;&nbsp;조회 : ${view.hit}  &nbsp; | &nbsp; 추천 : ${view.good}
				&nbsp;&nbsp;&nbsp;&nbsp;
				<button id="clickBtn" class="btn btn-default">추천</button>
				</td>
				</tr>
								
			<tr>
				<!-- 내용 -->
				<td>
				<img src="../resources/movieUpload/${view.poster}" alt="image" width="300"><br>
				<font size="5px">${view.summary}</font><br>
				</td>
			</tr>	
				
		</table>
		</div>
			<div>
				<br>
				<div id="all">
			
				<c:if test="${view.id == sessionScope.login.id && view.id !=null}"> <!-- 성공 -->
					<button class="btn btn-success" id="updateBtn">수정</button>
					<button class="btn btn-danger"id="deleteBtn">삭제</button>
				</c:if>
				</div>
				<br><br>
		</div>
	</form>
</body>
</html>