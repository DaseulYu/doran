<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>



        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>마이 페이지</title>

            <link rel="stylesheet" href="${contextPath}/resources/css/main-style.css">
            <link rel="stylesheet" href="${contextPath}/resources/css/myPage-style.css">


            <link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-barun-gothic.css" rel="stylesheet">
            <script src="https://kit.fontawesome.com/aab319511f.js" crossorigin="anonymous"></script>

        </head>

        <body>

            <body>
                <main>

                    <jsp:include page="/WEB-INF/views/common/header.jsp" />

                    <section class="myPage-content">


                        <jsp:include page="/WEB-INF/views/member/sideMenu.jsp" />

                        <section class="myPage-main">
                            <h1 class="myPage-title"> 내 정보</h1>
                            <span class="myPage-explanation"> 현재 비밀번호를 확인 후 원하는 개인 정보를 수정할 수 있습니다.</span>
                            <form action="info" method="POST" name="myPage-form"
                                onsubmit="return myInfoUpdateValidate()">
                                <div class="myPage-row">
                                    <label>현재 비밀번호</label><input type="password" name="currentPw" id="currentPw">
                                </div>
                                <div class="message-row">
                                    <span id="currentPwMessage"></span>
                                </div>
                                <div class="myPage-row">
                                    <label>닉네임</label><input type="text" name="memberNickname" id="memberNickname"
                                        value="${loginMember.memberNickname}">
                                </div>
                                <div class="message-row">
                                    <span id="nicknameMessage"></span>
                                </div>
                                <div class="myPage-row">
                                    <label>휴대폰 번호</label><input type="text" name="memberPhone" id="memberPhone"
                                        value="${loginMember.memberPhone}" maxlength="11">
                                </div>
                                <div class="message-row">
                                    <span id="phoneMessage"></span>
                                </div>

                                <button type="submit" id="info-update-btn">수정하기</button>
                            </form>

                        </section>
                    </section>


                </main>


                <jsp:include page="/WEB-INF/views/common/footer.jsp" />

                <!-- jQuery 라이브러리 추가(CDN) -->
                <script src="https://code.jquery.com/jquery-3.6.0.min.js"
                    integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>


                <script>
                    const loginMemberNo = "${loginMember.memberNo}";
                </script>


                <script src="${contextPath}/resources/js/myPage.js"></script>
            </body>

        </html>