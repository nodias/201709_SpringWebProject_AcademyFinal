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
							<form action="login" style="width: 300px">

								<fieldset>
									<legend>이메일이 전송되었습니다.</legend>
									
										<h3>전송된 이메일 속의 링크를 클릭해 주세요.</h3>
										<br />
										<div  class="form-group">
											<button class="btn btn-primary btn-lg btn-block" type="submit" id="submit">확인</button>
										</div>
									</fieldset>
							</form>
						</section>
						</div>
					</div>
				</body>
			</html>
