<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

 <header>
	 <section class="header-content">
	    <a href="${contextPath}/admin/list">admin</a>
        <a href="#">회원가입</a>
        <a href="#">로그인</a>
        <a href="${contextPath}/notice">공지사항</a>
	 </section>
	
	 <section class="menu-content">
	     <button id="category">
	         <img src="${contextPath}/resources/images/menubar.png" id="menubar"><br>
	         카테고리
	     </button>
	     <a href="${contextPath}">
	         <img src="${contextPath}/resources/images/logo.png" id="home-logo">
	     </a>
	
	     <section class="search-content">
	         <article class="search-area">
	             <form action="#" name="search-form">
	                 <fieldset class="search-area">
	                     <input type="search" id="query" name="query" 
	                     autocomplete="off" placeholder="검색어를 입력해주세요.">
	                     <button type="submit" id="search-btn">
	                         <img src="${contextPath}/resources/images/search.png" id="search-logo">
	                     </button>
	                 </fieldset>
	             </form>
	         </article>
	     </section>
	</section>
</header>