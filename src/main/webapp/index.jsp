<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>임시index</title>

            <link rel="stylesheet" href="${contextPath}/resources/css/member-community.css">
            <link rel="stylesheet" href="${contextPath}/resources/css/board-signup.css">
            <link rel="stylesheet" href="${contextPath}/resources/css/board-list.css">
            <link rel="stylesheet" href="${contextPath}/resources/css/main-style.css">
            <link rel="stylesheet" href="${contextPath}/resources/css/index-style.css">

            <link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-barun-gothic.css" rel="stylesheet">

        </head>

        <body>

            <!-- 임시 index => 메인 완성 후 교체 -->

            <main>
                <!-- header -->
                <jsp:include page="/WEB-INF/views/common/header.jsp" />

                <!-- 임시 버튼 -->
                <a href="community/detail?cn=1"> cn=1모임 바로가기 </a>

                <div id="body">
                    <div class="left">
                        <img src="resources/images/test.gif">
                    </div>
                    <div class="right">
                        <span id="doran">
                            다같이 모여,<br>
                            도란도란
                        </span>

                        <div id="member">
                            <button id="login">로그인</button><br>
                            <button id="signUp">회원가입</button>
                        </div>
                        <span id="boardList">내게 맞는 모임 만나려면 <a href="${contextPath}/board/list?cp=1">모임 찾기</a></span>
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