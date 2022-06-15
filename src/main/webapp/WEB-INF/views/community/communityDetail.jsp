<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${comm.communityName}</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/member-community.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/board-signup.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/board-list.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/main-style.css">

    <link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-barun-gothic.css" rel="stylesheet">
    
</head>
<body>

    <main>
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>

        <div class="board-container">
        
            <form class="community-head">
                <div class="head-left">
                    <img src="${contextPath}${comm.communityImage}">
                </div>
                <div class="head-right">
                    <div class="head-title">
                        <div class="community-name">
                            <span>${comm.communityName}</span>
                            <a href="#"><span class="btn-edit">edit</span></a>
                        </div>
                        <div><button class="btn-report">신고</button></div>
                    </div>

                    <div class="head-info">
                        <p>${comm.communityInfo}</p>
                    </div>

                    <div class="head-user">
                        <div>
                            <c:if test="${empty comm.profileImage}">
                                <img src="${contextPath}/resources/images/user.png" id="user">
                            </c:if>
                            
                            <c:if test="${!empty comm.profileImage}">
                                <img src="${contextPath}${comm.profileImage}" id="user">
                            </c:if>
                        </div>
                        
                        <div>${comm.memberNickname}</div>

                    </div>

                    <div class="head-join">
                        <div onclick="pick()">
                            <img src="${contextPath}/resources/images/pick1.png" id="pick" >
                        </div>
                        <div class="btn-join" id="btn-join">모임 가입하기</div>
                    </div>
                    <div id="btn-leave">모임 탈퇴하기</div>
                </div>
            </form>

            <div class="community-nav">
                <ul>
                    <li><a href="#board-detail">공지사항</a></li>
                    <span>|</span>
                    <li><a href="#board-signup">가입인사</a></li>
                    <span>|</span>
                    <li><a href="#board-community">자유게시판</a></li>
                    <span>|</span>
                    <li><a href="#board-review">정모후기</a></li>
                </ul>
            </div>
            
            <section class="community-content">
            
                <!-- 모임 상세정보(모임장 작성) -->
                <div class="board board-detail" id="board-detail">
                    <h3>공지사항</h3>
                    <div class="board-detail-txt">
                        ${comm.communityDetail}
                    </div>

                    <!-- 다음 모임 -->
                    <div class="board-event">
                        <h4>다음모임</h4>
                        <table class="next-event">
                            <tr>
                                <th>일시</th>
                                <td>2022월 05월 13일(금) 18:00</td>
                            </tr>
                            <tr>
                                <th>장소</th>
                                <td>서울시 종로구</td>
                            </tr>
                            <tr>
                                <th>참여인원</th>
                                <td>12명</td>
                            </tr>
                        </table>
                        
                        <button class="btn-event">참여하기</button>
                    </div>

                </div>

                <!-- 가입인사 -->
                <div class="board board-signup" id="board-signup">
                    <h3>가입인사
                        <a href="${contextPath}/community/signup/list?cn=${param.cn}" target="_blank"><span class="board-all">전체 가입인사 보기 &gt;</span></a>
                    </h3>

                    <jsp:include page="/WEB-INF/views/board/board-signup-write.jsp"/>
                    
                    
                </div>


                <!-- 자유게시판 -->
                <div class="board board-list" id="board-list">
                    <h3>자유게시판
                        <a href="${contextPath}/community/board/list?cn=${param.cn}&type=1" target="_blank"><span class="board-all">전체 게시글 보기 &gt;</span></a>
                    </h3>
                    <!-- 자유게시판 연결 -->
                    <jsp:include page="/WEB-INF/views/community/commu-board.jsp"/>
                </div>



                <!-- 모임 후기 -->
                <div class="board board-review" id="board-review">
                    <h3>모임후기
                        <a href="${contextPath}/community/board/review?cn=${param.cn}&type=2" target="_blank"><span class="board-all">전체 후기 보기 &gt;</span></a>
                    </h3>  

                    <div class="review-content">

                        <div class="review">
                            <div class="review-img thumbnail">
                                <img src="${contextPath}/resources/images/sample.jpg">
                            </div>
                            <div class="userInfo">
                                <div>
                                    <img src="${contextPath}/resources/images/user.png" id="user">
                                </div>
                                <span><h4>여덟글자닉네임임</h4></span>
                            </div>
                            <div class="review-text">
                                모임후기1<br>
                                모임후기2<br>
                                모임후기3<br>
                                모임후기4<br>
                                모임후기5<br>
                            </div>
                        </div>
                        
                    </div>
                </div>
                
            </section>
        </div>
    </main>

    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    
    <script src="${contextPath}/resources/js/community/member-community.js"></script>
    
</body>
</html>