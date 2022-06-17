<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
		<c:set var="categoryName" value="${map.categoryName}" />
		<c:set var="categoryList" value="${map.categoryList}" />
		<header>
			<section class="header-content">

				<c:choose>
					<%-- 로그인 되어 있지 않은 경우 --%>
						<c:when test="${empty sessionScope.loginMember}">
							<a href="${contextPath}/member/signUp">회원가입</a>
							<a href="${contextPath}/member/login">로그인</a>
							<a href="${contextPath}/notice">공지사항</a>
						</c:when>

						<c:otherwise>
							<c:if test="${loginMember.memberNo == 0}">
								<a href="${contextPath}/admin/list" target="_blank">admin</a>
							</c:if>
							<a href="${contextPath}/member/myPage/info">${loginMember.memberNickname}님</a>
							<a href="${contextPath}/member/logout">로그아웃</a>
							<a href="${contextPath}/notice">공지사항</a>
						</c:otherwise>
				</c:choose>

			</section>
			<section>

				<button id="category" onclick="dp_menu()" class="button" class="dropdown">
					<img src="${contextPath}/resources/images/menubar.png" id="menubar"><br>
					<div style="display: none;" id="drop-content">
						<a href='${contextPath}/community/category?type=1'>아웃도어/여행</a>
						<a href='${contextPath}/community/category?type=2'>운동/스포츠</a>
						<a href='${contextPath}/community/category?type=3'>업종/직무</a>
						<a href='${contextPath}/community/category?type=4'>문화/공연/축제</a>
						<a href='${contextPath}/community/category?type=5'>음악/악기</a>
						<a href='${contextPath}/community/category?type=6'>봉사활동</a>
						<a href='${contextPath}/community/category?type=7'>사교/인맥</a>
						<a href='${contextPath}/community/category?type=8'>취미활동</a>
					</div>
				</button>

				<a href="${contextPath}">
					<img src="${contextPath}/resources/images/logo.png" id="home-logo">
				</a>


				<c:if test="${empty categoryName}">
					<section class="search-content">
						<article class="search-area">
							<form action="${contextPath}/community/list?" name="search-form"
								onsubmit="return searchValidate()">
								<fieldset class="search-area">
									<input type="search" id="query" name="query" autocomplete="off"
										placeholder="검색어를 입력해주세요.">
									<button type="submit" id="search-btn">
										<img src="${contextPath}/resources/images/search.png" id="search-logo">
									</button>
								</fieldset>
							</form>
						</article>
					</section>
				</c:if>

				<!-- 카테고리가 선택 되었을 때 -->
				<c:if test="${!empty categoryName}">
					<section class="search-content">
						<article class="search-area">
							<form action="${contextPath}/community/category?type=${param.type}" name="search-form"
								onsubmit="return searchValidate()">
								<fieldset class="search-area">
									<input type="hidden" name="type" value="${param.type}">
									<input type="search" id="query" name="query" autocomplete="off"
										placeholder="검색어를 입력해주세요.">
									<button type="submit" id="search-btn">
										<img src="${contextPath}/resources/images/search.png" id="search-logo">
									</button>
								</fieldset>
							</form>
						</article>
					</section>
				</c:if>
			</section>
		</header>
		<script src="${contextPath}/resources/js/header.js"></script>