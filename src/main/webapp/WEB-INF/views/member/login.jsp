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

    <link rel="stylesheet" href="../resources/css/main-style.css">
    <link rel="stylesheet" href="../resources/css/login-style.css">
</head>

<body>
    <main>
       <jsp:include page="/WEB-INF/views/common/header.jsp"/>

        <section class="login-content">

            <div class="login-area">
                <h1 class="login-title">로그인</h1>
                <form action="member/login" method="POST" name="login-form" onsubmit="return loginValidate()">

                    <div class="login-input-area">
                        <input type="text" id="inputEmail" name="inputEmail" placeholder="아이디 (이메일)" autocomplete="off" maxlength="40" value="${cookie.saveId.value}">                  
                    </div>
                    <div class="login-input-area">
                        <input type="password" id="inputPw" name="inputPw" placeholder="비밀번호" autocomplete="off">
                    </div>

                    <c:if test="${!empty cookie.saveId.value}">
                        <c:set var="chk" value="checked"/>
                    </c:if>
                    <label for="saveId"><input type="checkbox" ${chk} id="saveId" name="saveId">
                                           아이디 저장</label>
                    <div class="message-area">
                       <!--  <span><strong>아이디(이메일)를</strong>를 입력해주세요.</span> -->
                    </div>
                    <div class="login-btn-area">
                        <button type="submit" class="login-btn" >로그인</button>
                    </div>
                </form>
                <div class="signUp-find-area">

                    <a href="#">아이디 찾기</a>
                    <span>|</span>
                    <a href="#">비밀번호 찾기</a>
                    <span>|</span>
                    <a href="#">회원가입</a>

                </div>
        </section>

        <jsp:include page="/WEB-INF/views/common/footer.jsp"/>

        <script src="../resources/js/login.js"></script>
</body>

</html>