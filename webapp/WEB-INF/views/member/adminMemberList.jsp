<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>회원관리 시스템 관리자모드(회원 목록 보기)</title>
</head>
<body>
	<div id="contentsArea">
		<section id="titlename">
			<h1>회원 목록</h1>
			<table class="table table-hover">
				<thead>
					<tr>
						<th scope="col" class="memberIdDel ">아이디</th>
						<th scope="col" class="memberIdDelOk">회원 삭제</th>
					</tr>
				</thead>
				<tbody>
			
					<c:forEach var="list"  items="${arraylist}" >
						<tr>
							<td>
								<a href="adminMemberInfo?id=${list.id}">
									<c:out value="${list.id}"></c:out>
								</a>
							</td>
							<td>
								<a href="adminMemberDelete?id=${list.id}">삭제</a>
							</td>
						</tr>
					</c:forEach>
				
				</tbody>
			</table>
		</section>
	</div>
</body>
</html>