<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>비밀번호 찾기</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/main-style.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/findPw-style.css">

    
	<link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-barun-gothic.css" rel="stylesheet">
</head>

<body>
    <main>
        <jsp:include page="/WEB-INF/views/common/header.jsp" />


        <section class="findPw-content">

            
            
            <div class="findPw-area">
            <h1 class="findPw-title">비밀번호 찾기</h1>
            <h4 class="findPw-subtitle"> 회원 가입시 입력한 이메일을 통해 인증 후, 새 비밀번호를 설정 할 수 있습니다.</h4>
                <form action="findPw" method="POST" name="findPw-form" onsubmit="return findPwValidate()">
                    
                    <div class="findPw-input-area">
                    <label for="inputName">이름</label>
                        <input type="text" id="inputName" name="inputName" placeholder="이름" autocomplete="off" maxlength="10">
                    </div>
                    <div class="message-row">
                    <span id="nameMessage"></span>
                </div>                
                    <div class="findPw-input-area">
                    <label for="inputEmail">아이디(이메일)</label>    
                    <input type="text" id="inputEmail" name="inputEmail" placeholder="회원 가입시 입력한 이메일" autocomplete="off">
                <button type="button" id="sendBtn">인증번호 받기</button>
                </div>
                <div class="message-row">
                <span class="findPw-message" id="emailMessage"></span>
            </div>
                    
                    <div class="findPw-input-area">
                    <label for="checkConfirm">인증번호</label>   
                    <input type="text" id="cNumber" name="cNumber" placeholder="인증번호 6자리 숫자 입력" maxlength="6" autocomplete="off">
                    <button type="button" id="cBtn">인증하기</button>
                    </div>
                    <div class="message-row">
                    <span class="findPw-message" id="cMessage"></span>
                </div>

                <div class="findPw-input-area">
                    <label>새 비밀번호</label><input type="password" name="newPw" id="newPw" maxlength="30">
                </div>
                
                <div class="message-row">
                    <span id="newPwMessage"></span>
                </div>
                <div class="findPw-input-area">
                    <label>새 비밀번호 확인</label><input type="password" name="newPwConfirm" id="newPwConfirm" maxlength="30">
                </div>
                <div class="message-row">
                    <span id="newPwConfirmMessage"></span>
                </div>

                    <div class="findPw-btn-area">
                        <button type="submit" class="findPw-btn">비밀번호 재설정</button>
                    </div>
                </form>
        </section>


        <jsp:include page="/WEB-INF/views/common/footer.jsp" />

        <script src="https://code.jquery.com/jquery-3.6.0.min.js"
        integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

        <script src="${contextPath}/resources/js/findPw.js"></script>

</body>

</html>