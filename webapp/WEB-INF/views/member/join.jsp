<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>회원가입</title>
<link rel="stylesheet" href="${ pageContext.servletContext.contextPath }/resources/bootstrap/css/bootstrap.css"/>
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/member.js"></script>
</head>
<body>
<br /><br />
	<div id="contentsArea" align="center">
		<section id="titlename">
			<form action="join" id="joinForm" method="post"
				name="reg_frm" style="width: 300px">
				<fieldset>
					<legend>회원가입</legend>
			
					<c:choose>
						<c:when test="${param.id !=null }">
							<div class="form-group has-success has-feedback">
								<input class="form-control" type="text" id="id" name="id" value="${param.id }" readonly="readonly">
								<span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>
								<span id="inputSuccess3Status" class="sr-only">(success)</span>
							</div>
						</c:when>
						<c:otherwise>
						<div class="form-group has-success has-feedback">
							<input class="form-control" type="text" id="id" name="id" required placeholder="아이디" >
							<span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>
								<span id="inputSuccess3Status" class="sr-only">(success)</span>
							</div>
						</c:otherwise>
					</c:choose>
					
						<div class="form-group has-success has-feedback"> 
						<input  class="form-control" type="password" id="password" name="pw" required placeholder="비밀번호(6자 이상)">
							<span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>
								<span id="inputSuccess3Status" class="sr-only">(success)</span>
							</div>
					
					<div class="form-group has-success has-feedback"> 
						<input class="form-control" type="password" id="password" name="pw_check" required	placeholder="비밀번호 확인">
							<span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>
								<span id="inputSuccess3Status" class="sr-only">(success)</span>
								</div>
					<c:choose>
						<c:when test="${param.name !=null }">
						<div class="form-group has-success has-feedback"> 
							<input class="form-control" type="text" id="name" name="name" value="${param.name }" readonly="readonly">
							<span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>
							<span id="inputSuccess3Status" class="sr-only">(success)</span>
						</div>
						</c:when>
						<c:otherwise>
						<div class="form-group has-success has-feedback"> 
							<input class="form-control" type="text" id="name" name="name" required placeholder="이름">
							<span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>
							<span id="inputSuccess3Status" class="sr-only">(success)</span>
						</div>
						</c:otherwise>
					</c:choose>
						
					<div class="btnJoinArea">
						<button class="btn btn-primary btn-lg btn-block" type="button" class="btnOk" onclick="infoConfirm()"
							id="submit_btn">회원가입</button>
					</div>
					<br /><a href="login">돌아가기</a>
				</fieldset>
			</form>
			
		</section>
	</div>
</body>
</html>
