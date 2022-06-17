<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

<c:set var="boardName" value="${map.boardName}" />
<c:set var="pagination" value="${map.pagination}" />
<c:set var="boardList" value="${map.boardList}" />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>모임후기</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/board-detail.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/main-style.css">

    <link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-barun-gothic.css" rel="stylesheet">
    
</head>
<body>

    <main>
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>
    
    
        <form action="report" method="post" class="report-popup" onsubmit="return reportValidation()">
            <div class="report-detail">
                <div>신고 사유를 선택해주세요.</div>
                <select name="reportTitle" id="reportCategory">
                    <option value="0" selected disabled>신고사유 선택</option>
                    <option value="부적절한 게시글">부적절한 게시글</option>
                    <option value="욕설">욕설</option>
                    <option value="사용자 비방">사용자 비방</option>
                </select>
                <button id="report-submission">신고</button>
                <button type="button" id="report-cancel">취소</button>
            </div>

             <!-- hidden -->
             <%-- <input type="hidden" name="type" value="${param.type}">
             <input type="hidden" name="cn" value="${param.cn}">--%>
             <input type="hidden" name="no" id="boardNo">
        </form> 

        <div class="board-detail">
            <div>
                <h2>모임후기</h2>
            </div>
            <div class="btn-area">
                <button id="insertBtn" onclick="location.href='write?mode=insert&cn=${param.cn}&type=${param.type}&cp=${param.cp}'">후기 작성</button>
            </div>

            <c:choose>
                <c:when test="${empty boardList}">
                    <h4 style="color: #0C71FF;">게시글이 없습니다.<br>첫 후기를 작성해주세요.</h4>
                </c:when>

                <c:otherwise>
                    <c:forEach var="boardList" items="${boardList}">

                        <div class="content">
                            <div class="board-title-area">
                                <div class="board-title">
                                    <h2>${boardList.boardTitle}</h2>
                                </div>
                                <div class="write-btn-area">
                                    <c:if test="${loginMember.memberNo != boardList.memberNo}">
                                        <button id="btn-report" onclick="showReportPopup(${boardList.boardNo})">신고</button>
                                    </c:if>
                                    <c:if test="${loginMember.memberNo == boardList.memberNo}">
                                        <button id="updateBtn" onclick="location.href='write?mode=update&no=${boardList.boardNo}&cn=${param.cn}&type=${param.type}&cp=${param.cp}'">수정</button>
                                        <button id="deleteBtn" onclick="boardDelete(${boardList.boardNo})">삭제</button>
                                    </c:if>
                                </div>
                            </div>
                            <div class="userInfo">
                                <div class="user writer">
                                    <c:if test="${empty boardList.profileImage}">
                                        <img src="${contextPath}/resources/images/user.png" id="user">
                                    </c:if>
                                    
                                    <c:if test="${!empty boardList.profileImage}">
                                        <img src="${contextPath}${boardList.profileImage}" id="user">
                                    </c:if>
                                </div>
                                <div class="writerInfo">
                                    <span class="user-nick">${boardList.memberNickname}</span>
                                    <span class="txt-date">${boardList.createDate}</span>
                                </div>
                            </div>
                            <div class="content-txt">
                                ${boardList.boardContent}
                            </div>
                            <div class="content-img-area">
                                <c:if test="${!empty boardList.image0}">
                                    <div class="content-img thumbnail">
                                        <img src="${contextPath}${boardList.image0}">
                                    </div>
                                </c:if>
                                <c:if test="${!empty boardList.image1}">
                                    <div class="content-img">
                                        <img src="${contextPath}${boardList.image1}">
                                    </div>
                                </c:if>
                            </div>

                        </div>
                    </c:forEach>
                </c:otherwise>
            </c:choose>

            <div class="paging">
                <c:set var="url" value="review?cn=${param.cn}&type=${param.type}&cp="/>

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

    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

  
    <script src="${contextPath}/resources/js/board/board-review.js"></script>
    <script src="${contextPath}/resources/js/admin-page/report-form.js"></script>

    
</body>
</html>