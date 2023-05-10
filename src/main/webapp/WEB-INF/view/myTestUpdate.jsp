<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div>
		<form action="/update/user" method="put">
			<div class="form-group">
				<label for="username">username: </label> <input type="text"
					name="username" id="username" class="form-control" value="항1">
			</div>
			<div class="form-group">
				<label for="password">password: </label> <input type="password"
					name="password" id="password" class="form-control" value="1234">
			</div>
			<div class="form-group">
				<label for="email">email: </label> <input type="text" name="email"
					id="email" class="form-control" value="a@naver.com">
			</div>
		</form>

		<button id="join--submit" class="btn btn-primary">수정하기</button>
	</div>
	<script type="text/javascript">
		$(document).ready(function(){
			$.ajax({
				type: 'PUT',
				url: '/update/user/{id}',
				contentType: 'application/json; charset=utf-8',
				data: JSON.Stringify(data),
				dataType: 'json'
			}).done(function(response){
				alert('회원정보 수정 완료');
				location.href='/temp/index';
			}).fail(function(error){
				alert('서버오류');
			});
		});
	</script>

</body>
</html>