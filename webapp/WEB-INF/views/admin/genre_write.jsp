<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<style>
.form-control {
	width: 500px;
}
</style>
<body>

	<div class="container">
		<h2>MOVIER 영화관리(장르)</h2>

		<form action="./GenreWriteProcess.jh" method="post" id="joinForm"
			name="boardform">
			<div class="form-group">
				<label for="inputdefault">장르이름</label> <input class="form-control"
					id="mInfor" type="text" name="name">
			</div>
			

			<div class="form-group">
				<div class="col-lg-offset-2 col-lg-10">
					<button type="submit" class="btn btn-default">등록</button>
				</div>
			</div>




		</form>
	</div>

</body>
</html>