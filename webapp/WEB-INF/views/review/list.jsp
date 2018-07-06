<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta charset="UTF-8">
<title>----MovieR Review----</title>
<script type="text/javascript">

	var result = "${msg}"
	if (result == "WRITESUCCESS")
		alert("리뷰가 등록되었습니다.");
	else if (result == "DELETESUCCESS")
		alert("리뷰가 삭제되었습니다.");

	$(function() {
		$("#writeBtn").on("click", function() {
			location = "write"; //get방식
		});
		
		$("#sort").on("change", function(){
// 			alert($("#sort").val());
			location.href="list?sort="+$("#sort").val();
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

 padding: 10px;"
}

</style>
</head>
<body>
	
	<div id="all">
		<br><br><strong id="font">&nbsp;&nbsp;MovieR Review</strong>&nbsp;&nbsp;&nbsp;
<%-- 		<c:if test="${sessionScope.id != null }"> --%>
		<button class="btn btn-primary" id="writeBtn">리뷰쓰기</button>
<%-- 		</c:if> --%>
		<form action="list" id="search" >
			<input type="hidden" name="page" value="1" />
			<!-- 검색 페이지 값 1로  -->
			<input type="hidden" name="perPageNum" value="${pageMaker.cri.perPageNum }" />
				<select name="searchType" style="width: 100px; height: 30px;">
<!-- 					<option value="n" -->
<%-- 						${pageMaker.cri.searchType == null or cri.searchType == "n"?"selected='selected'":"" }>---</option> --%>
					<option value="tcw"
						${pageMaker.cri.searchType == "tcw"?"selected='selected'":"" }>전체</option>
					<option value="t"
						${pageMaker.cri.searchType == "t"?"selected='selected'":"" }>제목</option>
					<option value="c"
						${pageMaker.cri.searchType == "c"?"selected='selected'":"" }>내용</option>
					<option value="w"
						${pageMaker.cri.searchType == "w"?"selected='selected'":"" }>작성자</option>
				</select> <input name="keyword" style=" height: 30px;" value="${pageMaker.cri.keyword }" />
				<button class="btn btn-info">검색</button>
		</form>
</div>

	<hr width="1010px">
	<div id="all">
	전체 글: &nbsp;<strong><c:out value="${pageMaker.totalCount}" /></strong>개
		<!-- 정렬 선택 --> 
		<select id="sort" style="width:100px; height: 25px;" name="sort" >
			<option value="reg_date" ${param.sort =='reg_date'?"selected='selected'":"" } >날짜순</option>
			<option value="hit" ${param.sort =='hit'?"selected='selected'":"" }>조회순</option>
			<option value="good" ${param.sort =='good'?"selected='selected'":"" }>추천순</option> 
			</select>
	</div>
	<hr width="1010px">
	
	<div id="all">
		<table>
		
		<c:if test="${empty list }">
		<tr><td colspan="6">데이터가 존재하지 않습니다.</td></tr>
		</c:if>
		<!-- 데이터가 있는 경우에만 여기를 반복 한다. 반복시작 -->
		<c:if test="${! empty list }">
			<div id="imgList">
			<c:forEach var="mov" items="${list }">
				<tr id="title_font" >
					<td rowspan ="2" width="200px">
					<img src="../resources/movieUpload/${mov.poster}" alt="image" width="170px" height="200"></td>
					<td colspan="1"> <br>
					<font color="#000080"><strong>[ ${mov.title_1 } ]</strong></font>&nbsp;&nbsp;
					<a href="view${pageMaker.makeQuery() }&no=${mov.review_no }">${mov.title }</a>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color="#606060"> ${mov.id}
					</font> 
					&nbsp; | &nbsp;
					<fmt:parseDate value="${mov.reg_date}" var="dateFmt" pattern="yyyy-MM-dd HH:mm:ss"/>
					<fmt:formatDate value="${dateFmt}"  pattern="yyyy.MM.dd"/> &nbsp;&nbsp;  
					  |&nbsp;  조회 : ${mov.hit }  &nbsp;  |
					  <font color="red">&nbsp; ♡  : ${mov.good } &nbsp;</font><hr width="800"></td>
				</tr>
				
				<tr>
					<td rowspan="1">
					<c:choose>
						<c:when test="${fn:length(mov.summary)>150 }">
							<br><c:out value="${fn:substring(mov.summary,0,150)}" />.....
						</c:when>
						<c:otherwise>
						<br>${mov.summary }<br>
						</c:otherwise>					
					</c:choose>
					<br><br><br></td>
				</tr>
				
			</c:forEach>
			</div>
			
			<!-- 여기까지 반복 -->
			</c:if>
			
			<tr>
			<td colspan="5">
				<div class="text-center">
					<ul class="pagination">
						<!-- 이전 페이지 처리 -->
						<c:if test="${pageMaker.prev }">
							<li><a href="list?page=${pageMaker.startPage -1 }">&laquo;</a></li>
						</c:if>

						<!-- 시작 페이지부터 끝 페이지까지 반복 처리 -->
						<c:forEach begin="${pageMaker.startPage }"
						 end="${pageMaker.endPage}" var="idx">
							<li ${pageMaker.cri.page==idx?"class='active'":"" }>
								<a href="list${pageMaker.makeQuery(idx) }">${idx }</a>
							</li>
						</c:forEach>

						<!-- 다음 페이지 처리 -->
						<c:if test="${pageMaker.next }">
							<li><a href="list?page=${pageMaker.endPage + 1 }">&raquo;</a></li>
						</c:if>
					</ul>
				</div>
			</td>
		</tr>
		</table>
	</div>
</body>
</html>