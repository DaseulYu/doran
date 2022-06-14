<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>자유게시판</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/board-detail.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/header-footer.css">

    <link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-barun-gothic.css" rel="stylesheet">

</head>
<body>
    <main>
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>

        <div class="board-detail">
            <div>
                <h2>${detail.boardName}</h2>
            </div>
            <div class="btn-area">
                <button id="goToListBtn">목록으로</button>
            </div>

            <c:if test="${empty param.cp}">
                <c:set var="cp" value="1" />
            </c:if>
            <c:if test="${!empty param.cp}">
                <c:set var="cp" value="${param.cp}" />
            </c:if>

            <div class="content">
                <div class="board-title-area">
                    <div class="board-title">
                        <h2>${detail.boardTitle}</h2>
                    </div>
                    <div class="write-btn-area">
                        <button id="btn-report">신고</button>
                        <button id="updateBtn" onclick="location.href='write?mode=update&no=${param.no}&cn=${param.cn}&type=${param.type}&cp=${cp}'">수정</button>
                        <button id="deleteBtn">삭제</button>
                    </div>
                </div>
                <div class="userInfo">
                    <div class="user writer">
                        <c:if test="${empty detail.profileImage}">
                            <img src="${contextPath}/resources/images/user.png" id="user">
                        </c:if>
                        
                        <c:if test="${!empty detail.profileImage}">
                            <img src="${contextPath}${detail.profileImage}" id="user">
                        </c:if>
                    </div>
                    <div class="writerInfo">
                        <span class="user-nick">${detail.memberNickname}</span>
                        <span class="txt-date">${detail.createDate}</span>
                    </div>
                </div>
                <div class="content-txt">
                    ${detail.boardContent}
                </div>

                <c:if test="${!empty detail.imageList}">
                    <!-- 첨부파일1 변수 생성 -->
                    <c:if test="${detail.imageList[0].imageLevel == 0}">
                        <c:set var="thumbnail" value="${detail.imageList[0]}" />
                    </c:if>

                    <div class="content-img-area">
                        <!-- 첨부파일1 -->
                        <c:if test="${!empty thumbnail}">
                            <div class="content-img thumbnail">
                                <img src="${contextPath}${thumbnail.imageName}">
                            </div>
                        </c:if>

                        <c:if test="${empty thumbnail}">
                            <c:set var="start" value="0"/>
                        </c:if>
                        <c:if test="${!empty thumbnail}">
                            <c:set var="start" value="1"/>
                        </c:if>

                        <!-- 첨부파일2 -->
                        <c:if test="${fn:length(detail.imageList) > start}">
                            <c:forEach var="i" begin="${start}" end="${fn:length(detail.imageList)-1}">
                                <div class="content-img">
                                    <img src="${contextPath}${detail.imageList[i].imageName}">
                                </div>
                            </c:forEach>
                        </c:if>
                    </div>
                </c:if>
            </div>

        </div>

    </main>

    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>

    <script src="${contextPath}/resources/js/board/board.js"></script>
    
</body>
</html>