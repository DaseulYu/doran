<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>가입인사</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/board-signup.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/header-footer.css">

    <link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-barun-gothic.css" rel="stylesheet">
    
</head>
<body>
    <main>
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>


        <div class="board-signup">
            <div>
                <h2>가입인사</h2>
            </div>
            
            <div class="signup">
                <div class="user">
                    <img src="../../resources/images/user.png" id="user">
                    <p>최대여덟글자까지</p>
                </div>
                <div class="message">
                    <p>
                        <span class="message-txt">안녕하세요.</span>
                        <span class="message-date">2022.05.22</span>
                    </p>
                </div>
                <div class="write-btn-area">
                    <button id="btn-report">신고</button>
                    <button id="updateBtn">수정</button>
                    <button id="deleteBtn">삭제</button>
                </div>
            </div>


            
            <div class="message-textarea">
                <textarea maxlength="100" placeholder="내용을 입력해주세요.(100 글자 이하)" id="msgContent"></textarea>
                <button class="btn-reply" id="btn-reply">등록</button>
            </div>

            
        
        </div>
    </main>

    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>


</body>
</html>