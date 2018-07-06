<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="decorator"
	uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	response.setCharacterEncoding("UTF-8");
%>
<%
	response.setContentType("text/javascript;charset=UTF-8");
%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="${ pageContext.servletContext.contextPath }/resources/js/member.js"></script>
<script type="text/javascript"
	src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js"
	charset="utf-8"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<style>
#all {
	width: 1000px;
	margin-left: 150px;
}
</style>
</head>
<body>
	<div class="content-wrapper img" id="all">
		<img src="${pageContext.servletContext.contextPath }/resources/img/logo2.png" width="200px" hspace="100px">
	</div>
	<decorator:body />
</body>
</html>
