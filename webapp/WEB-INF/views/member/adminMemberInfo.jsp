<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>회원관리 시스템 관리자모드(회원 정보 보기)</title>
</head>
<body>
	<div id="contentsArea">
		<section>
			<h1>회원정보</h1>
			<table class="boardTable">
				<tr>
					<td>아이디</td>
					<td>${dto.id }</td>
				</tr>
				<tr>
					<td>비밀번호</td>
					<td>${dto.pw }</td>
				</tr>
				<tr>
					<td>이름</td>
					<td>${dto.name }</td>
				</tr>
				<tr>
					<td>프사</td>
					<td>${dto.img }</td>
				</tr>
				<tr>
					<td>등록날짜</td>
					<td>${dto.reg_date }</td>
				</tr>
				<tr>
					<td>수정날짜</td>
					<td>${dto.mod_date }</td>
				</tr>
				<tr>
					<td>등급</td>
					<td>${dto.grade }</td>
				</tr>
			</table>
			<div class="btnJoinArea">
				<a href="adminMemberList">
					<button type="submit" class="btnOk">회원 목록</button>
				</a>
			</div>
		</section>
	</div>
</body>
</html>
