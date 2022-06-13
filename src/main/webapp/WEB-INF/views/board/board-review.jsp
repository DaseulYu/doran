<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>모임명-모임후기</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/board-detail.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/header-footer.css">

    <link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-barun-gothic.css" rel="stylesheet">
    
</head>
<body>

    <main>
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>

        <div class="board-detail">
            <div>
                <h2>모임후기</h2>
            </div>
            <div class="btn-area">
                <button id="insertBtn">후기 작성</button>
            </div>


            <div class="content">
                <div class="userInfo">
                    <div class="user writer">
                        <img src="../../resources/images/user_sample.jpg" id="user">
                    </div>
                    <div class="writerInfo">
                        <span class="user-nick">최대여덟글자까지</span><br>
                        <span class="txt-date">2022.05.22</span>
                    </div>
                    <div class="write-btn-area">
                        <button id="btn-report">신고</button>
                        <button id="updateBtn">수정</button>
                        <button id="deleteBtn">삭제</button>
                    </div>
                </div>
                <div class="content-txt">
                    모임후기1<br>
                    내용입력<br>
                    내용입력<br>
                </div>
                <div class="content-img-area">
                    <div class="content-img thumbnail">
                        <img src="../../resources/images/sample.jpg">
                    </div>
                    <div class="content-img">
                        <img src="../../resources/images/sample.jpg">
                    </div>
                </div>

                <div class="reply-list-area">
                    <ul id="reply-list">
                        <li class="reply-row">
                            <div class="reply-nick">
                                <img src="../../resources/images/user_sample.jpg" id="user">최대여덟글자까지
                            </div>
                            <div class="reply-txt-area">
                                <span class="reply-txt">
                                    가나다라마바사아자차카타파하
                                    가나다라마바사아자차카타파하
                                    가나다라마바사아자차카타파하
                                    가나다라마바사아자차카타파하
                                    가나다라마바사아자차카타파하
                                    가나다라마바사아자차카타파하
                                    가나다라마바사아자차카타파하하하
                                    <span class="reply-txt-date">2022.05.22</span>
                                </span>
                            </div>
                            
                            <div class="reply-btn-area">
                                <button id="btn-report">신고</button>
                                <button id="updateBtn">수정</button>
                                <button id="deleteBtn">삭제</button>
                            </div>
                        </li>

                        <li class="reply-row">
                            <div class="reply-nick">
                                <img src="../../resources/images/user.png" id="user">닉네임
                            </div>
                            <div class="reply-txt-area">
                                <span class="reply-txt">
                                    안녕하세요.
                                    <span class="reply-txt-date">2022.05.22</span>
                                </span>
                            </div>
                            
                            <div class="reply-btn-area">
                                <button id="btn-report">신고</button>
                                <button id="updateBtn">수정</button>
                                <button id="deleteBtn">삭제</button>
                            </div>
                        </li>
                    </ul>
                </div>

                <div class="reply-write-area">
                    <textarea maxlength="100" placeholder="내용을 입력해주세요.(100 글자 이하)"></textarea>
                    <button class="btn-reply">등록</button>
                </div>
            </div>

            <div class="content">
                <div class="userInfo">
                    <div class="user writer">
                        <img src="../../resources/images/user_sample.jpg" id="user">
                    </div>
                    <div class="writerInfo">
                        <span class="user-nick">최대여덟글자까지</span><br>
                        <span class="txt-date">2022.05.22</span>
                    </div>
                    <div class="write-btn-area">
                        <button id="btn-report">신고</button>
                        <button id="updateBtn">수정</button>
                        <button id="deleteBtn">삭제</button>
                    </div>
                </div>
                <div class="content-txt">
                    모임후기1<br>
                    내용입력<br>
                    내용입력<br>
                </div>
                <div class="content-img-area">
                    <div class="content-img thumbnail">
                        <img src="../../resources/images/sample.jpg">
                    </div>
                    <div class="content-img">
                        <img src="../../resources/images/sample.jpg">
                    </div>
                </div>

                <div class="reply-list-area">
                    <ul id="reply-list">
                        <li class="reply-row">
                            <div class="reply-nick">
                                <img src="../../resources/images/user.png" id="user">닉네임
                            </div>
                            <div class="reply-txt-area">
                                <span class="reply-txt">
                                    안녕하세요.
                                    <span class="reply-txt-date">2022.05.22</span>
                                </span>
                            </div>
                            
                            <div class="reply-btn-area">
                                <button id="btn-report">신고</button>
                                <button id="updateBtn">수정</button>
                                <button id="deleteBtn">삭제</button>
                            </div>
                        </li>
                    </ul>
                </div>

                <div class="reply-write-area">
                    <textarea maxlength="100" placeholder="내용을 입력해주세요.(100 글자 이하)"></textarea>
                    <button class="btn-reply">등록</button>
                </div>
            </div>



            <div class="paging">
                <c:set var="url" value="list?cn=${param.cn}&type=${param.type}&cp="/>

                <ul class="pagination">
                    <!-- 첫 페이지로 이동 -->
                    <li><a href="${url}1${sURL}">&lt;&lt;</a></li>
                                        
                    <!-- 이전 목록 마지막 번호로 이동 -->
                    <li><a href="${url}${pagination.prevPage}${sURL}">&lt;</a></li>

                    <c:forEach var="i" begin="${pagination.startPage}" end="${pagination.endPage}" step="1">
                        <c:choose>
                            <c:when test="${i == pagination.currentPage}">
                                <li><a class="current">${i}</a></li>
                            </c:when>

                            <c:otherwise>
                                <li><a href="${url}${i}${sURL}">${i}</a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                    <!-- 다음 목록 시작 번호로 이동 -->
                    <li><a href="${url}${pagination.nextPage}${sURL}">&gt;</a></li>

                    <!-- 끝 페이지로 이동 -->
                    <li><a href="${url}${pagination.maxPage}${sURL}">&gt;&gt;</a></li>
                </ul>
            </div>
            
        </div>
    
    </main>

    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    
</body>
</html>