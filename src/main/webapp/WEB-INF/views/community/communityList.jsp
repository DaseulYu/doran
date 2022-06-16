<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <c:set var="pagination" value="${map.pagination}" />
        <c:set var="boardList" value="${map.boardList}" />
        <c:set var="comm" value="${comm}" />


        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>모임 찾기</title>

            <link rel="stylesheet" href="${contextPath}/resources/css/main-style.css">
            <link rel="stylesheet" href="${contextPath}/resources/css/boardList-style.css">
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/hung1001/font-awesome-pro@4cac1a6/css/all.css" />
            <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css" rel="stylesheet">
            <link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-barun-gothic.css" rel="stylesheet">
        </head>
        <main>

            <body>
                <jsp:include page="/WEB-INF/views/common/header.jsp" />
                <c:if test="${!empty param.query}">
                    <c:set var="sURL" value="&query=${param.query}" />
                </c:if>


                <section class="board">

                    <c:if test="${!empty sessionScope.loginMember}">
                        <div class="create"><a href="${contextPath}/community/addMeeting"><i
                                    class="fas fa-plus-circle"></i> 모임 만들기</a></div>
                    </c:if>
                    <section class="board-content">

                        <div class="board-post">

                            <ul class="post">
                                <c:choose>
                                    <c:when test="${empty boardList}">
                                        <span id="noCommunity">게시글이 존재하지 않습니다.</span>
                                    </c:when>
                                    <c:otherwise>
                                        <c:forEach var="board" items="${boardList}">
                                            <li class="list">
                                                <div class="doran">
                                                    <div class="cover">
    
                                                        <c:if test="${!empty board.communityImage}">
                                                            <img width="110px" height="110px"
                                                                src="${contextPath}${board.communityImage}">
                                                        </c:if>
    
                                                        <c:if test="${empty board.communityImage}">
                                                            <img src="${contextPath}/resources/images/thumbnail.png"
                                                                width="110px" height="110px">
                                                        </c:if>
                                                    </div>
                                                    <div class="doran-block">
                                                        <p class="doranLocation"><i class="fal fa-map-marker-alt"></i> ${board.communityArea}</p><br>
                                                        <a href="detail?cn=${board.communityNo}${sURL}" class="doranName">${board.communityName}</a><br>
                                                        <a href="${contextPath}/community/category?type=${board.categoryNo}" class="categoryName">${board.categoryName}</a><br>
                                                        <span class="peopleCount"><i class="fal fa-user"></i> ${board.communityMember}</span> 
                                                        <span class="likeCount"><i class="fas fa-heart"></i> ${board.pick}</span>
                                                    </div>
                                                </div>
                                            </li>
                                        </c:forEach>
                                    </c:otherwise>
                                </c:choose>
                            </ul>
                        </div>

                    </section>

                    <div class="pagination-area">
                        <c:set var="url" value="list?cp=" />


                        <ul class="pagination">
                            <li><a href="${url}1${sURL}">&lt;&lt;</a></li>

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

                            <li><a href="${url}${pagination.nextPage}${sURL}">&gt;</a></li>

                            <li><a href="${url}${pagination.maxPage}${sURL}">&gt;&gt;</a></li>

                        </ul>
                    </div>
                </section>
                <script src="${contextPath}/resources/js/community/communityList.js"></script>
            </body>
        </main>
        <jsp:include page="/WEB-INF/views/common/footer.jsp" />

        </html>