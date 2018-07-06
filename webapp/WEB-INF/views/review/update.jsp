<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>----MovieR Review Update----</title>


<style type="text/css">
#all {
	width: 1000px;
	margin: 0 auto 0 auto;
}

#listBtn {
	float: center;
	margin-right: 170px;
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

<script type="text/javascript">
	$(function() {
		$("#cancelBtn").on("click", function() {
			location = "list"; // get 방식으로 이동
			// form tag의 submit을 이용해서 넘긴다. 넘어갈 uri를 action 속성으로 셋팅한다.
		});
	});
</script>

</head>

<body>
	<h3 id="all">리뷰수정</h3>
	<br>
	<div>
		<br>
		<form action="update" method="post" role="form" class="form-horizontal">
			<input type="hidden" name="review_no" value="${view.review_no }" />
			<input type="hidden" name="page" value="${cri.page }" /> <input
				type="hidden" name="perPageNum" value="${cri.perPageNum }" />
			<div>

				<div id="all">
					<br> <br> 
					<label id="mov_no" for="mov_no">&nbsp;&nbsp;영 화&nbsp;</label> 
					<select name="mov_no" id="select" class="form-control">
						<c:forEach var="mov" items="${getMov_no}" varStatus="status">
							<option value="${mov.mov_no}">${mov.title}</option>
						</c:forEach>
					</select>
				</div>
				<br>
				<div id="all">
					<label for="title">&nbsp;&nbsp;제 목&nbsp;</label>
					<div>
						<input id="title" name="title" class="form-control"
							placeholder="제목을 입력하세요." required="required" pattern=".{4,100}"
							value="${view.title }" />
					</div>
				</div>
				<div id="all">
					<br>
					<label for="summary">&nbsp;&nbsp;내 용&nbsp;</label><br>
					<textarea id="summary" name="summary" cols="100" rows="10"
						required="required" class="form-control">${view.summary}</textarea>
				</div>
			</div>

			<br>
			
			<div id="all">
				<br>
				<button id="btn" class="btn btn-primary" id="updateBtn">수정</button>
				<button type="button" class="btn btn-danger" id="cancelBtn">취소<br>
				</button>
				<br> <br>
			</div>
			<br>
		</form>
	</div>
</body>
</html>