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
					<title>로그인</title>
					<link rel="stylesheet" href="${ pageContext.servletContext.contextPath }/resources/bootstrap/css/bootstrap.css"/>
					<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/jquery-1.12.4.min.js"></script>
					<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/bootstrap/js/bootstrap.js"></script>
					<script type="text/javascript" src="${ pageContext.servletContext.contextPath }/resources/js/member.js"></script>
					<script type="text/javascript" src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.3.js" charset="utf-8"></script>
					<meta name="viewport" content="width=device-width, initial-scale=1">
				</head>
			</head>
			<body>
			<br /><br />
				<div id="contentsArea" align="center">
					<div id="usefulArea">
						<section class="login">
							<form action="loginPost" method="post" style="width: 300px">

								<fieldset>
									<legend>로그인</legend>
									<div class="form-group has-success has-feedback">
										<input class="form-control" type="text" id="userId" name="id" aria-describedby="inputSuccess2Status" required placeholder="아이디">
											<span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>
											<span id="inputSuccess3Status" class="sr-only">(success)</span>
										</div>
									<div class="form-group has-success has-feedback">
										<input  class="form-control" type="password" id="userPw" name="pw" required placeholder="비밀번호">
										<span class="glyphicon glyphicon-ok form-control-feedback" aria-hidden="true"></span>
											<span id="inputSuccess3Status" class="sr-only">(success)</span>
										</div>
										<div  class="form-group">
											<button class="btn btn-primary btn-lg btn-block" type="submit" id="submit">로그인</button>
										</div>
										<div class="form-group">
											<a href="pwfind1">비밀번호 찾기</a>
											/
											<a href="join">회원 가입하기</a>
										</div>
									</fieldset>
									<c:choose>
										<c:when test="${msg!=null}">
											<p style="color: red">${msg }</p>
										</c:when>

									</c:choose>
								
							</form>
						</section>

						<!-- 네이버아이디로로그인 버튼 노출 영역 -->
						<div id="naver_id_login"></div>
						<!-- //네이버아이디로로그인 버튼 노출 영역 -->
						<script type="text/javascript">
							var naver_id_login = new naver_id_login("nZkKTZTbLF_d4yAjblE1", "http://localhost:8081/MovieR/member/callback");
							var state = naver_id_login.getUniqState();
							naver_id_login.setButton("green", 3, 40);
							naver_id_login.setDomain("http://127.0.0.1:8081/MovieR/");
							naver_id_login.setState(state);
							naver_id_login.setPopup();
							naver_id_login.init_naver_id_login();
						</script>
						</div>
					</div>
				</body>
			</html>
