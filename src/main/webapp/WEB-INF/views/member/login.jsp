<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>도란도란 로그인 페이지</title>

<link rel="stylesheet" href="${contextPath}/resources/css/main-style.css">
<link rel="stylesheet" href="${contextPath}/resources/css/login-style.css">


<link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-barun-gothic.css" rel="stylesheet">
</head>

<body>
	<main>
		<jsp:include page="/WEB-INF/views/common/header.jsp" />

		<section class="login-content">

			<div class="login-area">
				<h1 class="login-title">로그인</h1>
				<form action="login" method="POST" name="login-form"
					onsubmit="return loginValidate()">

					<div class="login-input-area">
						<input type="text" id="inputEmail" name="inputEmail"
							placeholder="아이디 (이메일)" autocomplete="off" maxlength="40"
							value="${cookie.saveId.value}">
					</div>
					<div class="login-input-area">
						<input type="password" id="inputPw" name="inputPw"
							placeholder="비밀번호" autocomplete="off">
					</div>

					<c:if test="${!empty cookie.saveId.value}">
						<c:set var="chk" value="checked" />
					</c:if>
					<label for="saveId"><input type="checkbox" ${chk}
						id="saveId" name="saveId"> 아이디 저장</label>

					<div class="login-btn-area">
						<button type="submit" class="login-btn">로그인</button>
					</div>
				</form>
				<div class="signUp-find-area">

					<a href="${contextPath}/member/findPw">비밀번호 찾기</a> <span>|</span>
					<a href="${contextPath}/member/signUp">회원가입</a>

				</div>
			</div>

		</section>
	</main>
	<jsp:include page="/WEB-INF/views/common/footer.jsp" />

	<!-- jQuery 라이브러리 추가 (CDN) -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js"
		integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4="
		crossorigin="anonymous"></script>
	<script src="${contextPath}/resources/js/login.js"></script>
</body>

</html>