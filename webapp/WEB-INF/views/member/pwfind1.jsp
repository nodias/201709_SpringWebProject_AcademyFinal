<%@ page import="java.net.URLEncoder" %>
<%@ page import="java.net.URL" %>
<%@ page import="java.net.HttpURLConnection" %>
<%@ page import="java.io.BufferedReader" %>
<%@ page import="java.io.InputStreamReader" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<head>
			<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
				<meta name="viewport" content="width=device-width, initial-scale=1">
					<title>비밀번호찾기1</title>
					<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
					<link rel="stylesheet" href="${ pageContext.servletContext.contextPath }/resources/bootstrap/css/bootstrap.css"/>
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/jquery-1.12.4.min.js"></script>
<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/bootstrap/js/bootstrap.js"></script>
				</head>
			</head>
			<body>
			<div>
			<br /><br />
			</div>
				<div id="contentsArea" align="center">
					<div id="usefulArea">
						<section class="login">
							<form action="pwfindProcess" method="post" style="width: 300px">

								<fieldset>
									<legend>비밀번호 찾기</legend>
									<div class="form-group has-success has-feedback">
										<input class="form-control" type="text" id="userId" name="id" aria-describedby="inputSuccess2Status" required placeholder="이메일">
											<span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>
											<span id="inputSuccess3Status" class="sr-only">(success)</span>
										</div>
										가입하신 이메일 주소를 입력해주시면 임시 주소가 발송됩니다. 임시주소로 들어오신 뒤 새로운 비밀번호를 입력하세요.
										<div  class="form-group">
											<button class="btn btn-primary btn-lg btn-block" type="submit" id="submit">확인</button>
										</div>
										<br /><a href="login">돌아가기</a>
									</fieldset>
									<c:choose>
										<c:when test="${msg!=null}">
											<p style="color: red">${msg }</p>
										</c:when>

									</c:choose>
								
							</form>
						</section>
						</div>
					</div>
				</body>
			</html>
