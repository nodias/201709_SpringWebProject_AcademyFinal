<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div id="contentsArea">

		<section id="titlename" class="qnaBoard">
			<h1>감독 등록</h1>

			<div id="infoArea">
				<section class="search">
					<form name="search" action="./BoardSearchList.do" method="post">
						<fieldset>
							<legend>검색</legend>
							<label for="keyword"></label> <select name="keyfield"
								class="b_search">
								<option value="all" selected="selected">전체 검색</option>
								<option value="title">
									<c:if test="${'name' == keyfield}">selected="selected"</c:if>>감독</option>


							</select> <input type="search" id="keyword" name="keyword"
								required="required" placeholder="검색어 입력">
							<button type="submit">검색</button>
						</fieldset>
					</form>
				</section>
			</div>
			<p class="allPost">
				전체 글: &nbsp; <strong><c:out value="${listcount}" /> </strong>개
			</p>
			<table  class="table table-hover">
				<caption>게시판 리스트</caption>
				<c:choose>
					<c:when test="${listcount>0}">
						<thead>
							<tr>
								<th scope="col" class="bbsNumber">번호</th>
								<th scope="col" class="bbsTitle">감독</th>
								<th scope="col" class="bbsDate">등록일</th>
							</tr>
						</thead>
						<c:forEach var="director" items="${dataList}" varStatus="status">&nbsp;&nbsp;&nbsp;
							<tbody>
								<tr>
									<td><c:out value="${director.dir_no}" /></td>
									<td><c:out value="${director.name}" /></td>
									<td>&nbsp;&nbsp;&nbsp;<c:out value="${director.reg_date}" /><a href="./DirectorDeleteProcess.jh?delNum=${director.dir_no}"><button  class="btn btn-danger">삭제</button></a></td>

								</tr>
							</tbody>
						</c:forEach>
					</c:when>
				</c:choose>
			</table>
			<div align="center">
				<table id="boardTableNe" class="boardTableNe">
					<tbody>
						<c:if test="${searchlistcount==0}">
							<tr>
								<td colspan="4"></td>
								<td>등록된 글이 없습니다.</td>
							</tr>
						</c:if>
						<tr>
							<td colspan="5"><c:choose>
									<c:when test="${page <= 1}"> [이전]&nbsp; </c:when>
									<c:otherwise>
										<a href="./DirectorList.jh?page=<c:out value="${page-1}"/>">[이전]</a>&nbsp;
</c:otherwise>
								</c:choose> <c:forEach var="start" begin="${startpage}" end="${endpage}"
									step="1">
									<c:choose>
										<c:when test="${start eq page}"> [<c:out
												value="${start}" />] </c:when>
										<c:otherwise>
											<a href="./DirectorList.jh?page=<c:out value="${start}" />">[<c:out
													value="${start}" />]
											</a>&nbsp;
</c:otherwise>
									</c:choose>
								</c:forEach> <c:choose>
									<c:when test="${page >= maxpage}">[다음] </c:when>
									<c:otherwise>
										<a href="./DirectorList.jh?page=<c:out value="${page+1}" />">[다음]</a>
									</c:otherwise>
								</c:choose></td>
						</tr>
					</tbody>
				</table>
				<div class="btnJoinAreb">

					<a href="./DirectorWrite.jh"><button type="button" value="button"
						 class="btnOk">
						글쓰기</button></a>


				</div>
			</div>
		</section>
	</div>
</body>
</html>