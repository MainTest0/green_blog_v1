<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>그린스 블로그</title>
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
	<sec:authorize access="isAuthenticated()">
    Welcome Back, <sec:authentication property="name" />
    Welcome Back, <sec:authentication property="principal" var="" />
	</sec:authorize>
	<nav class="navbar navbar-expand-md bg-dark navbar-dark">
		<a class="navbar-brand" href="/">Home</a>

		<!-- Toggler/collapsibe Button -->
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>

		<!-- Navbar links -->
		<div class="collapse navbar-collapse" id="collapsibleNavbar">
			<ul class="navbar-nav">
				<c:choose>
					<c:when test="${empty principal }">
						<li class="nav-item"><a class="nav-link"
							href="/auth/loginPage">로그인</a></li>
						<li class="nav-item"><a class="nav-link"
							href="/auth/joinPage">회원가입</a></li>
					</c:when>
					<c:otherwise>
						<li class="nav-item"><a class="nav-link" href="#">글쓰기</a></li>
						<li class="nav-item"><a class="nav-link" href="#">회원정보</a></li>
						<li class="nav-item"><a class="nav-link" href="/logout">로그아웃</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</nav>
	<br>