<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<header>
	<section class="header-content">

		<c:choose>
			<%-- 로그인 되어 있지 않은 경우 --%>
			<c:when test="${empty sessionScope.loginMember}">
				<a href="#">회원가입</a>
				<a href="${contextPath}/member/login">로그인</a>
				<a href="#">공지사항</a>
			</c:when>
			
		<c:otherwise>
      <a href="${contextPath}/admin/list">admin</a>
			<a href="${contextPath}/member/myPage/info">${loginMember.memberNickname}님</a>
			<a href="${contextPath}/member/logout">로그아웃</a>
			<a href="${contextPath}/notice">공지사항</a>
		</c:otherwise>
		</c:choose>
			
	</section>
	<section>
		<button id="category">
			<img src="${contextPath}/resources/images/menubar.png" id="menubar"><br>
			카테고리
		</button>
		<a href="${contextPath}"> 
<<<<<<< HEAD
            <img src="${contextPath}/resources/images/logoV2.png" id="home-logo">
=======
            <img src="${contextPath}/resources/images/logo.png" id="home-logo">
>>>>>>> 83ec08e11e74ddc626d93807c15c048b32d2e7c5
		</a>

		<section class="search-content">
			<article class="search-area">
				<form action="#" name="search-form">
					<fieldset class="search-area">
						<input type="search" id="query" name="query" autocomplete="off"
							placeholder="검색어를 입력해주세요.">
						<button type="submit" id="search-btn"
							class="fa-solid fa-magnifying-glass">
							<img src="${contextPath}/resources/images/search.png"
								id="search-logo">
						</button>
					</fieldset>
				</form>
			</article>
		</section>
<<<<<<< HEAD


=======
    
>>>>>>> 83ec08e11e74ddc626d93807c15c048b32d2e7c5
	</section>
</header>