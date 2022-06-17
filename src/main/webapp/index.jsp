<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


		<!DOCTYPE html>
		<html lang="en">

		<head>
			<meta charset="UTF-8">
			<meta http-equiv="X-UA-Compatible" content="IE=edge">
			<meta name="viewport" content="width=device-width, initial-scale=1.0">
			<title>다같이 모여, 도란도란</title>

			<link rel="stylesheet" href="${contextPath}/resources/css/member-community.css">
			<link rel="stylesheet" href="${contextPath}/resources/css/board-signup.css">
			<link rel="stylesheet" href="${contextPath}/resources/css/board-list.css">
			<link rel="stylesheet" href="${contextPath}/resources/css/main-style.css">
			<link rel="stylesheet" href="${contextPath}/resources/css/index-style.css">

			<link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-barun-gothic.css" rel="stylesheet">

		</head>

		<body>

			<main>
				<!-- header -->
				<jsp:include page="/WEB-INF/views/common/header.jsp" />


				<div id="body">
					<div class="left">
						<img src="resources/images/test.gif">
					</div>
					<div class="right">
						<span id="doran"> 다같이 모여,<br> 도란도란</span>

						<%-- 로그인 되어 있지 않은 경우 --%>
							<c:choose>
								<c:when test="${empty sessionScope.loginMember}">
									<div id="member">
										<a href="${contextPath}/member/login"><button id="login">로그인</button><br></a>
										<a href="${contextPath}/member/signUp"><button id="signUp">회원가입</button></a>
									</div>
									<span id="boardList">내게 맞는 모임 만나려면 <a href="${contextPath}/community/list?cp=1">모임
											찾기</a></span>
								</c:when>

								<c:otherwise>
									<div id="member">
										<a href="${contextPath}/community/list?cp=1"><button id="login">모임
												보러가기</button></a><br>
								</c:otherwise>
							</c:choose>
					</div>
				</div>
			</main>

			<!-- jQuery 라이브러리 추가 (CDN) -->
			<script src="https://code.jquery.com/jquery-3.6.0.min.js"
				integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

			<!-- footer -->
			<jsp:include page="/WEB-INF/views/common/footer.jsp" />

		</body>

		</html>