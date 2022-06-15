<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>모임장 관리 페이지</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/meeting-admin-style.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/main-style.css">

    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@10"></script>

</head>
<body>
    <main>
        
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>

        <section class="meeting-admin-content">
            <form action="#" method="POST" name="meeting-admin-form">
                <span class="meetingname-admin">${comm.communityName} 관리</span>

                <div class="meeting-request-area">
                    <h4>모임 신청 내역</h4>
                    <c:choose>
                        <c:when test="${empty applyMemberList}">
                            <h4 style="color : gray;">모임을 신청한 회원이 존재하지 않습니다.</h4>
                        </c:when>

                        <c:otherwise>
                            <c:forEach var="ca" items="${applyMemberList}">
                                <div class="req-member-area">
                                    <div class="nickname-area">
                                        <a href="#" id="openPop"><img src="${contextPath}${loginMember.profileImage}" id="nickname-icon"></a>
                                        <span class="req-nickname">${loginMember.memberNickname}</span>
                                    </div>
                                    <button id="confirm" onclick="return confirmChk()">승인</button>
                                    <button id="refuse" onclick="return refuseChk()">거절</button>
                                </div>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </div>

                    <!-- <div class="req-member-area">
                        <a href="#" id="openPop"><img src="${contextPath}/resources/images/profile_icon.png" id="profile"></a>
                        <div class="nickname-area">
                            <img src="${contextPath}/resources/images/nickname_icon.png" id="nickname-icon">
                            <span class="req-nickname">닉네임</span>
                        </div>
                        <button id="confirm">승인</button>
                        <button id="refuse">거절</button>
                    </div>

                    <div class="req-member-area">
                        <a href="#" id="openPop"><img src="${contextPath}/resources/images/profile_icon.png" id="profile"></a>
                        <div class="nickname-area">
                            <img src="${contextPath}/resources/images/nickname_icon.png" id="nickname-icon">
                            <span class="req-nickname">닉네임</span>
                        </div>
                        <button id="confirm">승인</button>
                        <button id="refuse">거절</button>
                    </div>

                    <div class="req-member-area">
                        <a href="#" id="openPop"><img src="${contextPath}/resources/images/profile_icon.png" id="profile"></a>
                        <div class="nickname-area">
                            <img src="${contextPath}/resources/images/nickname_icon.png" id="nickname-icon">
                            <span class="req-nickname">닉네임</span>
                        </div>
                        <button id="confirm">승인</button>
                        <button id="refuse">거절</button>
                    </div> -->


                <div class="member-admin-area">
                    <h4>회원 관리</h4> 
                    <c:choose>
                        <c:when test="${empty commMemberList}">
                            <h4 style="color : gray;">모임 회원이 존재하지 않습니다.</h4>
                        </c:when>

                        <c:otherwise>
                            <c:forEach var="cm" items="${commMemberList}">
                                <div class="member-area">
                                    <div class="member-nickname-area">
                                        <img src="${contextPath}${loginMember.profileImage}" id="member-nickname-icon">
                                        <span class="member-nickname">${loginMember.memberNickname}</span>
                                    </div>
                                    <div class="member-area-button">
                                        <button id="entrust" onclick="return entrustChk()">모임장 위임하기</button>
                                        <button id="out" onclick="return outChk()">추방</button>
                                    </div>
                                </div>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>
                </div>

                    <!-- <div class="member-area">
                        <div class="member-nickname-area">
                            <img src="${contextPath}/resources/images/nickname_icon.png" id="member-nickname-icon">
                            <span class="member-nickname">닉네임</span>
                        </div>
                        <div class="member-area-button">
                            <button id="entrust">모임장 위임하기</button>
                            <button id="out">추방</button>
                        </div>
                    </div>

                    <div class="member-area">
                        <div class="member-nickname-area">
                            <img src="${contextPath}/resources/images/nickname_icon.png" id="member-nickname-icon">
                            <span class="member-nickname">닉네임</span>
                        </div>
                        <div class="member-area-button">
                            <button id="entrust">모임장 위임하기</button>
                            <button id="out">추방</button>
                        </div>
                    </div>

                    <div class="member-area">
                        <div class="member-nickname-area">
                            <img src="${contextPath}/resources/images/nickname_icon.png" id="member-nickname-icon">
                            <span class="member-nickname">닉네임</span>
                        </div>
                        <div class="member-area-button">
                            <button id="entrust">모임장 위임하기</button>
                            <button id="out">추방</button>
                        </div>
                    </div>

                    <div class="member-area">
                        <div class="member-nickname-area">
                            <img src="${contextPath}/resources/images/nickname_icon.png" id="member-nickname-icon">
                            <span class="member-nickname">닉네임</span>
                        </div>
                        <div class="member-area-button">
                            <button id="entrust">모임장 위임하기</button>
                            <button id="out">추방</button>
                        </div>
                    </div>

                    <div class="member-area">
                        <div class="member-nickname-area">
                            <img src="${contextPath}/resources/images/nickname_icon.png" id="member-nickname-icon">
                            <span class="member-nickname">닉네임</span>
                        </div>
                        <div class="member-area-button">
                            <button id="entrust">모임장 위임하기</button>
                            <button id="out">추방</button>
                        </div>
                    </div>

                    <div class="member-area">
                        <div class="member-nickname-area">
                            <img src="${contextPath}/resources/images/nickname_icon.png" id="member-nickname-icon">
                            <span class="member-nickname">닉네임</span>
                        </div>
                        <div class="member-area-button">
                            <button id="entrust">모임장 위임하기</button>
                            <button id="out">추방</button>
                        </div>
                    </div>

                    <div class="member-area">
                        <div class="member-nickname-area">
                            <img src="${contextPath}/resources/images/nickname_icon.png" id="member-nickname-icon">
                            <span class="member-nickname">닉네임</span>
                        </div>
                        <div class="member-area-button">
                            <button id="entrust">모임장 위임하기</button>
                            <button id="out">추방</button>
                        </div>
                    </div>

                    <div class="member-area">
                        <div class="member-nickname-area">
                            <img src="${contextPath}/resources/images/nickname_icon.png" id="member-nickname-icon">
                            <span class="member-nickname">닉네임</span>
                        </div>
                        <div class="member-area-button">
                            <button id="entrust">모임장 위임하기</button>
                            <button id="out">추방</button>
                        </div>
                    </div>

                    <div class="member-area">
                        <div class="member-nickname-area">
                            <img src="${contextPath}/resources/images/nickname_icon.png" id="member-nickname-icon">
                            <span class="member-nickname">닉네임</span>
                        </div>
                        <div class="member-area-button">
                            <button id="entrust">모임장 위임하기</button>
                            <button id="out">추방</button>
                        </div>
                    </div>

                    <div class="member-area">
                        <div class="member-nickname-area">
                            <img src="${contextPath}/resources/images/nickname_icon.png" id="member-nickname-icon">
                            <span class="member-nickname">닉네임</span>
                        </div>
                        <div class="member-area-button">
                            <button id="entrust">모임장 위임하기</button>
                            <button id="out">추방</button>
                        </div>
                    </div>

                    <div class="member-area">
                        <div class="member-nickname-area">
                            <img src="${contextPath}/resources/images/nickname_icon.png" id="member-nickname-icon">
                            <span class="member-nickname">닉네임</span>
                        </div>
                        <div class="member-area-button">
                            <button id="entrust">모임장 위임하기</button>
                            <button id="out">추방</button>
                        </div>
                    </div>
                </div> -->

                <a href="#" id="deleteMeeting" onclick="return deleteMeeting()">모임 삭제하기</a>

                <div class="popup_layer" id="popup_layer" style="display: none;">
                    <div class="popup_box">
                        <div style="height: 10px; width: 375px; float: top;">
                            <a href="closePop();" class="popUp-close" width="30px" height="30px"></a>
                        </div>

                        <!--팝업 컨텐츠 영역-->
                        <div class="popup_cont">
                            <div class="popup_content">
                            <img src="${contextPath}${loginMember.profileImage}" id="nickname-icon-pop">
                            <span class="req-nickname-pop">${loginMember.memberNickname}</span>
                            </div>
                            <p>
                                이름 : <span>${loginMember.memberName}</span> <br>
                                성별 : <span>${loginMember.memberGender}</span> <br>
                                생년월일 : <span>${loginMember.memberBirth}</span>
                            </p>
                            <div class="popup_button">
                            <a href="#" id="closePop">확인</a>
                            </div>
                        </div>                    
                    </div>
                    </div>
            </form>
        </section>
    </main>

    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>

    <script src="${contextPath}/resources/js/community/meeting-admin.js"></script>
</body>
</html>