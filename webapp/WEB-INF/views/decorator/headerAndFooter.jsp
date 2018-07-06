<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="decorator" uri="http://www.opensymphony.com/sitemesh/decorator"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%response.setCharacterEncoding("UTF-8");  %>
<% response.setContentType("text/javascript;charset=UTF-8");%>


<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
			<meta name="viewport" content="width=device-width, initial-scale=1">
				<title>MOVIER:<decorator:title/></title>
				<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
				<script type="text/javascript">
				
				function fileCheck() {
					var filebing = document.getElementById('file').value;
					
					var fileName = filebing.slice(filebing.indexOf(".") + 1).toLowerCase();
					if (fileName != "jpg" && fileName != "png" && fileName != "gif"
							&& fileName != "bmp") {
						alert('이미지 파일(jpg, png, gif, bmp)만 등록 가능합니다.');
						return;
					} else {
						fileName = filebing.substring(filebing.lastIndexOf("\\") + 1); 
//			 			document.write(fileName);
						for (var i = 0; i < fileName.length; i++) {
							if (fileName.substring(i, i + 1) == "") {
								alert("파일명에 공백을 쓸 수 없습니다.");
								return;
							} else{
//			 					
							}
						}
					}
					Thumnail();
				}
				
				$(function () { //모달 제어
					$('#userConf_btn').click(function () {
						$('#hfModal').modal();
						$('#hfModal').on('hidden.bs.modal', function () {
							  location.reload();
							})
					})
				});
				
				$(function(){
					$("#file").on("change",function(){
						fileCheck();
					})
				})
				
				

				function Thumnail() {
 							var form = $('#log_frm')[0];
 							var formData = new FormData(form);
 							         var form = $('form')[0];
 							         var formData = new FormData(form);
 							             $.ajax({
 							                url: 'http://localhost:8081/MovieR/member/imageThum',
 							                processData: false,
 							                contentType: false,
 							                enctype: "multipart/form-data",
 							                data: formData,
 							                type: 'POST',
 							                success: function(result){
 							                	$('#photo').html("<img src='${ pageContext.servletContext.contextPath }/resources/img/${sessionScope.login.id}_ajax.jpg'>");
 							                }
 							            });
 							}
				
				function formTrans() {
					var str = $( "#userConf_frm" ).serialize();
					$.ajax({
			                url: 'http://localhost:8081/MovieR/member/userConfProcess',
			                dataType : "text",
			                data: str,
			                type: 'POST',
			                success : function(data) {
			                    if (data=='a') {
									alert("비밀번호가 틀립니다.");
									$("#userConf_frm")[0].reset(); 
								} else {
									alert("설정이 적용되었습니다.");
									$("#close_btn").click();
								}
			                }
			            });
				  }

				$(function(){
					$("#submit_btn").on("click",function(){
						if(
								$("#name").val() == '${sessionScope.login.name}' //닉네임 x, 비번 x
							&&$("#pw").val() == ""
							&&$("#new_pw").val() == ""
							&&$("#new_pw_check").val()  == ""){
							$("#close_btn").click();
						
					} else if ($("#name").val() != '${sessionScope.login.name}' //닉네임 o
						) {
							if ($("#name").val().length<2||$("#name").val().length>20) { //닉네임 길이확인 
								alert("닉네임은 최소 2자, 최대 20글자까지 설정 가능합니다");
							} else if ($("#pw").val() == ""
									&&$("#new_pw").val() == ""
									&&$("#new_pw_check").val()  == ""){ //비번 변경여부 확인 x:submit o:다음 else if
									formTrans();
									
								} else {
									if ($("#pw").val() != "") {
										if ($("#new_pw").val().length<6||$("#new_pw").val().length>20) {
											alert("비밀번호는 최소 6자, 최대 20글자까지 설정 가능합니다");
										} else if ($("#new_pw_check").val()  != $("#new_pw").val()) {
											alert("새 비밀번호가 일치하지 않습니다.");
									} else{
										formTrans();
									}
									} else {
										alert("현재 비밀번호를 입력해주세요.");
									}
								}
							} else if ($("#name").val() == '${sessionScope.login.name}') {
								if ($("#pw").val() != "") {
									if ($("#new_pw").val().length<6||$("#new_pw").val().length>20) {
										alert("비밀번호는 최소 6자, 최대 20글자까지 설정 가능합니다");
									} else if ($("#new_pw_check").val()  != $("#new_pw").val()) {
										alert("새 비밀번호가 일치하지 않습니다.");
								} else{
									formTrans();
								}
								} else {
									alert("현재 비밀번호를 입력해주세요.");
								}
								
					}
						
					});
					
					$( "#userConf_frm" ).reset();
				});
				
				</script>   
				<style media="screen">
				#all{
					width: 1000px;
					margin: 0 auto 0 auto;
					
				}
					.dropdown:hover .dropdown-menu {
						display: block;
						margin-top: 0; 
					}
					.dropdown {
						width: 100px;
					}
					.ip {
						
						size: 150px;
					}
					
					#user_name {
						  width: 940px;
 						 .center-block();
					}
					

				#img_p{
					min-width:  40px;
					height : 40px;
					width : 40px;
					max-width: 40px;
				}
				
				#user_name2{
					width: 80px;
				}
				
				#index_btn{
					text-align: left;
					size: 120px;
					margin-top: 10px;
					font-size: 20px;
				}
				</style>

				<decorator:head/>
			</head>

			<body>
					<!-- Trigger the modal with a button -->

					<!-- Modal -->
					<div class="modal fade" id="hfModal" role="dialog" >
						<div class="modal-dialog">

							<!-- Modal content-->
							<div class="modal-content" >
								<div class="modal-body" id="header_modal">
								
								<div>
								<div id="photo"  align="center" >
								<img src="${ pageContext.servletContext.contextPath }/resources/img/${sessionScope.login.id}.jpg" onerror="this.src='${ pageContext.servletContext.contextPath }/resources/img/base.jpg'" border="0">
								 </div>
									<form action="#" id="log_frm" method="post" enctype="multipart/form-data"  name="log_frm">
									<!--@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@-->
										<input multiple="multiple" type="file" name="fileName" id="file" style=" right: 0px; top: 0px; font-family: Arial; font-size: 15px; margin: 0px; padding: 0px; cursor: pointer; opacity: 0;"> <br />
									</form>
									
									<form action="http://localhost:8081/MovieR/member/userConfProcess" method="post" name="userConf_frm" id="userConf_frm"> <!-- 유저 세팅폼  -->
										
										<table class="table table-hover">
										
											<tr>
												<td>
													<label for="id">이메일</label>
												</td>
												<td>${sessionScope.login.id }&nbsp;&nbsp;<button type="button" class="btn btn-default">변경하기</button>
												</td>
											</tr>
											<tr>
												<td>
													<label for="name">닉네임</label>
												</td>
												<td>
													<input type="text" class="ip" id="name" name="name" value="${sessionScope.login.name }" width="px"></td>
												</tr>
												<tr>
													<td rowspan="3" valign="top">
														<label for="password">비밀번호 변경</label>
													</td>
													<td>
														<input type="password" class="ip" id="pw" name="pw" required placeholder="기존 비밀번호 "></td>
													</tr>
													<tr>
														<td>
															<input type="password" class="ip" id="new_pw" name="new_pw" required placeholder=" 새 비밀번호 "></td>
														</tr>
														<tr>
															<td>
																<input type="password" class="ip" id="new_pw_check" name="new_pw_check" required placeholder="새 비밀번호 확인"></td>
															</tr>
														</table>
														<div hidden="true" id="hide"></div>
													</form>
												</div>
												<div class="modal-footer">
										<a href="http://localhost:8081/MovieR/member/withdraw?id=${sessionScope.login.id }" id="withdraw">탈퇴하기</a>

												<button type="button" class="btn btn-default" id="submit_btn">확인</button>
												<button type="button" class="btn btn-default" data-dismiss="modal" id = "close_btn">Close</button>
												</div>
											</div>
<!-- 											Modal 끝 -->
										</div>
									</div>
								</div>
								<header class="row">
								<div class="navbar navbar-default" > 	
								<div id="all">
									<div class="col-md-1" id="#index_btn2"> 
											<a class="nav navbar-nav" id="index_btn"  href="http://localhost:8081/MovieR/index"><b>MOVIER</b></a>
									</div>
									<div class="col-md-8">
											<ul class="nav navbar-nav">
											<li><a  href="http://localhost:8081/MovieR/movie/evalMore.jh">평가늘리기</a></li>
											<li><a  href="http://localhost:8081/MovieR/movie/recommendation.jh">추천영화</a></li>
											<li><a  href="http://localhost:8081/MovieR/review/list">리뷰게시판</a></li>
										
										</ul>
									</div>
										<div class="col-md-3">
										<div class="navbar-brand">
										<div class="dropdown" id="user_name2">
											<a class="dropdown-toggle" href="#" id="user_name" >${sessionScope.login.name }</a>
											<ul class="dropdown-menu">
												<li>
													<a href="#">영화보관함</a>
												</li>
												<li>
													<a href="#" id="userConf_btn">회원설정</a>
													
												</li>
												<li>
													<a href="http://localhost:8081/MovieR/member/logout">로그아웃</a>
												</li>
											</ul>
										</div>
										
										</div>
										<img src="${ pageContext.servletContext.contextPath }/resources/img/${sessionScope.login.id}.jpg" onerror="this.src='${ pageContext.servletContext.contextPath }/resources/img/base.jpg'" border="0" class="img-circle" id="img_p" >
										
									</div>
								</div>
									</div>
									
								</header>
								<div class="clearfix">
								<div class=”pull-left“>
								<article>
									<decorator:body/>
								<div class="container">
					<!-- Trigger the modal with a button -->

					<!-- Modal -->
					<div class="modal fade" id="myModal" role="dialog">
						<div class="modal-dialog">

							<!-- Modal content-->
							<style>
							#login {
							    background-image: url(${pageContext.servletContext.contextPath}/resources/img/base2.jpg);
							}
							#login {
							    width: 760px;
							    height: 560px;
							    position: absolute;
							    left: 50%;
							    top: 50%;
							    margin-left: -395px;
							    margin-top: 0px;
							    text-align: center;
							    padding: 20px;
							}
							#login_text{
								margin-top: 110px;
								font-size: 22px;
								color: white;
							}
							</style>
							<div class="modal-content" id="login">
								<div class="modal-body" id="index_modal">
									<div class="content-wrapper img">
										<img src="${pageContext.servletContext.contextPath }/resources/img/logo.png" align="middle" width="250px" hspace="100px" style="filter:alpha(opacity:''60'');">
									</div>
									 <div class="bg-alpha inner"></div>
								        <div class="content-wrapper" id="login_text">
								       <h4> <span class="login">이미 MOVIER 회원이신가요?&nbsp;&nbsp;<a href="http://localhost:8081/MovieR/member/login">로그인</a></span><br /><br />
								          <span class="service-msg">MOVIER는 오늘 영화 뭐 볼까?를 해결하는 서비스입니다.</span></h4>
								        </div>
								      </div>
								</div>
								<div class="modal-footer"></div>
							</div>
						</div>
					</div>

				</div>
				
				<c:choose>
					<c:when test="${sessionScope.login.id==null }">
						<script>
							$('#myModal').modal({backdrop: 'static', keyboard: false})
						</script>
					</c:when>
					<c:when test="${sessionScope.login.id=='admin' }">
						<a href="http://localhost:8081/MovieR/member/adminMemberList">관리자(회원 목록 보기)</a><br />
						<a href="http://localhost:8081/MovieR/admin/ActorList.jh">관리자(배우 관리)</a><br />
						<a href="http://localhost:8081/MovieR/admin/DirectorList.jh">관리자(감독 관리)</a><br />
						<a href="http://localhost:8081/MovieR/admin/MovieList.jh">관리자(영화 관리)</a><br />
						<a href="http://localhost:8081/MovieR/admin/GenreList.jh">관리자(장르 관리)</a><br />
					</c:when>
				</c:choose>
				<script type="text/javascript">
					var naver_id_login = new naver_id_login("nZkKTZTbLF_d4yAjblE1", "http://127.0.0.1:8081/MovieR/");
					// 접근 토큰 값 출력
					alert(naver_id_login.oauthParams.access_token);
					// 네이버 사용자 프로필 조회
					naver_id_login.get_naver_userprofile("naverSignInCallback()");
					// 네이버 사용자 프로필 조회 이후 프로필 정보를 처리할 callback function
					function naverSignInCallback() {
						alert(naver_id_login.getProfileData('email'));
						alert(naver_id_login.getProfileData('nickname'));
						alert(naver_id_login.getProfileData('age'));
					}
				</script>
								</article>
								</div>
								</div>
								<footer class="navbar navbar-default"  >
								<div class="clearfix" id="all">
								<div class=”pull-left“>
								<div class="row">
									<img src="${pageContext.servletContext.contextPath }/resources/img/logo3.png" width="250px" align="top" style="margin: 0; pause: 0;">
									<div style="font-size: 12px; padding-left: 15px">사업자명 (주식회사 무비어즈) 사업자번호 (211-88-252525) Copyright © 2011-2017 by  Moviers. Inc. All rights reserved</div>
									<div>
											<a class="navbar-brand" href="http://localhost:8081/MovieR/terms/pterms" >MOVIER 서비스 이용약관</a>
											<a class="navbar-brand" href="http://localhost:8081/MovieR/terms/rterms">개인정보 취급방침</a>
										</div>
									</div>
									</div>
									</div>
								</footer>
							</body>
						</html>
