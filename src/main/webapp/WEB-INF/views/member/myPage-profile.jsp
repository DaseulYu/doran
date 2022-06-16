<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
            <!DOCTYPE html>
            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta http-equiv="X-UA-Compatible" content="IE=edge">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>마이 페이지 - 프로필</title>

                <link rel="stylesheet" href="${contextPath}/resources/css/main-style.css">
                <link rel="stylesheet" href="${contextPath}/resources/css/myPage-style.css">


                <link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-barun-gothic.css" rel="stylesheet">
                <script src="https://kit.fontawesome.com/aab319511f.js" crossorigin="anonymous"></script>

            </head>

            <body>
                <main>

                    <jsp:include page="/WEB-INF/views/common/header.jsp" />

                    <section class="myPage-content">


                        <jsp:include page="/WEB-INF/views/member/sideMenu.jsp" />
                        <section class="myPage-main">

                            <h1 class="myPage-title">프로필</h1>
                            <span class="myPage-explanation">프로필 이미지를 변경할 수 있습니다.</span>

                            <form action="profile" method="POST" name="myPage-form" enctype="multipart/form-data"
                                onsubmit="return profileValidate()">

                                <div class="profile-image-area">
                                    <c:if test="${empty loginMember.profileImage}">
                                        <img src="${contextPath}/resources/images/user.png" id="profile-image">
                                    </c:if>

                                    <c:if test="${!empty loginMember.profileImage}">
                                        <img src="${contextPath}${loginMember.profileImage}" id="profile-image">
                                    </c:if>

                                    <!-- 프로필 이미지 삭제 버튼 -->
                                    <span id="delete-image">x</span>

                                </div>


                                <div class="profile-btn-area">
                                    <label for="input-image">이미지 선택</label>
                                    <input type="file" name="profileImage" id="input-image" accept="image/*">
                                    <button type="submit">변경하기</button>
                                </div>


                                <div class="myPage-row">
                                    <label>아이디 (이메일) </label>
                                    <span> ${loginMember.memberEmail}</span>
                                </div>
                                <div class="myPage-row">
                                    <label>닉네임</label>
                                    <span>${loginMember.memberNickname}</span>
                                </div>
                                <div class="myPage-row ">
                                    <label>생년월일</label>
                                    <span>${loginMember.memberBirth}</span>
                                </div>
                                <div class="myPage-row">
                                    <label>성별</label>
                                    <c:if test="${loginMember.memberGender eq 'm'}">
                                        <span>남성</span>
                                    </c:if>
                                    <c:if test="${loginMember.memberGender eq 'f'}">
                                        <span>여성</span>
                                    </c:if>
                                </div>

                                <div class="myPage-row">
                                    <label>지역</label>
                                    <span>${loginMember.memberLive}</span>
                                </div>
                                <input type="hidden" name="delete" id="delete" value="0">
                            </form>

                        </section>
                    </section>
                </main>
                <jsp:include page="/WEB-INF/views/common/footer.jsp" />



                <script>
                    const loginMemberNo = "${loginMember.memberNo}";
                    const contextPath = "${contextPath}";
                </script>
                <!-- jQuery 라이브러리 추가(CDN) -->
                <script src="https://code.jquery.com/jquery-3.6.0.min.js"
                    integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>


                <script src="${contextPath}/resources/js/myPage-profile.js"></script>
            </body>

            </html>