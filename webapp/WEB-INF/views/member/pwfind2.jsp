<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
				<meta name="viewport" content="width=device-width, initial-scale=1">
					<title>비밀번호 찾기</title>
					<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
				<link rel="stylesheet" href="${ pageContext.servletContext.contextPath }/resources/bootstrap/css/bootstrap.css"/>
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/bootstrap/js/bootstrap.js"></script>
</head>
<body>
<br /><br />
	<div id="contentsArea" align="center">
		<section id="titlename">
			<form action="pwfindProcess2" id="joinForm" method="post"
				name="reg_frm" style="width: 300px">
				<fieldset>
					<legend>비밀번호 변경</legend>
			
							<div class="form-group has-success has-feedback">
								<input class="form-control" type="text" id="id" name="id" value="${param.id }" readonly="readonly">
								<span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>
								<span id="inputSuccess3Status" class="sr-only">(success)</span>
							</div>
						
					
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
						<input type="text" value=${param.token } name="token" hidden="true"/>
						
					<div class="btnJoinArea">
						<button class="btn btn-primary btn-lg btn-block" type="submit" class="btnOk"
							id="submit_btn">확인</button>
					</div>
					<br /><a href="login">로그인페이지로...</a>
				</fieldset>
			</form>
			
		</section>
	</div>
</body>
</html>