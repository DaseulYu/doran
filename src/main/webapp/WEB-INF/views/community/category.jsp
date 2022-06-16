<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <c:set var="categoryName" value="${map.categoryName}" />
        <c:set var="pagination" value="${map.pagination}" />
        <c:set var="categoryList" value="${map.categoryList}" />

        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>게시판</title>

            <link rel="stylesheet" href="${contextPath}/resources/css/main-style.css">
            <link rel="stylesheet" href="${contextPath}/resources/css/boardList-style.css">
            <link rel="stylesheet" href="${contextPath}/resources/css/category.css">
            <link rel="stylesheet" href="https://cdn.jsdelivr.net/gh/hung1001/font-awesome-pro@4cac1a6/css/all.css" />
            <link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-barun-gothic.css" rel="stylesheet">

        </head>
    <main>
        <body>
            <jsp:include page="/WEB-INF/views/common/header.jsp" />

            <c:if test="${!empty param.query}">
                <c:set var="sURL" value="&query=${param.query}"/>
            </c:if>
            
            <section class="category-board">
                <div class="category-name">${categoryName}
                    <button onclick="drop_category()" class="button" id="button"><i
                            class="fal fa-chevron-circle-down fa-3x"></i></button>
                    <div style="display: none;" id="dropContent">
                        <div class="drop-category">

                            <div class="category">
                                <img src="${contextPath}/resources/images/category/category1.JPG" width="80px" height="80px">
                                <br>
                                <a href='${contextPath}/community/category?type=1'>아웃도어/여행</a>
                            </div>

                            <div class="category">
                                <img src="${contextPath}/resources/images/category/category2.JPG" width="80px" height="80px">
                                <br>
                                <a href='${contextPath}/community/category?type=2'>운동/스포츠</a>
                            </div>

                            <div class="category">
                                <img src="${contextPath}/resources/images/category/category3.JPG" width="80px" height="80px">
                                <br>
                                <a href='${contextPath}/community/category?type=3'>업종/직무</a>
                            </div>

                            <div class="category">
                                <img src="${contextPath}/resources/images/category/category4.JPG" width="80px" height="80px">
                                <br>
                                <a href='${contextPath}/community/category?type=4'>문화/공연/축제</a>
                            </div>

                        </div>


                        <div class="drop-category">
                            <div class="category">
                                <img src="${contextPath}/resources/images/category/category5.JPG" width="80px" height="80px">
                                <br>
                                <a href='${contextPath}/community/category?type=5'>음악/악기</a>
                            </div>

                            <div class="category">
                                <img src="${contextPath}/resources/images/category/category6.JPG" width="80px" height="80px">
                                <br>
                                <a href='${contextPath}/community/category?type=6'>봉사활동</a>
                            </div>

                            <div class="category">
                                <img src="${contextPath}/resources/images/category/category7.JPG" width="80px" height="80px">
                                <br>
                                <a href='${contextPath}/community/category?type=7'>사교/인맥</a>
                            </div>

                            <div class="category">
                                <img src="${contextPath}/resources/images/category/category8.JPG" width="80px" height="80px">
                                <br>
                                <a href='${contextPath}/community/category?type=8'>취미활동</a>
                            </div>

                        </div>
                    </div>

                </div>


                <section class="board-content">

                    <div class="board-post">
                        <ul class="post">

                            <c:choose>
                            <c:when test="${empty categoryList}">
                                    <span id="noCommunity">게시글이 존재하지 않습니다.</span>
                            </c:when>

                            <c:otherwise>
                                <c:forEach var="category" items="${categoryList}">
                                    <li class="list">
                                        <div class="doran">
                                            <div class="cover">

                                                <c:if test="${!empty category.communityImage}">
                                                            <img width="110px" height="110px"
                                                                src="${contextPath}${category.communityImage}">
                                                        </c:if>
    
                                                        <c:if test="${empty category.communityImage}">
                                                            <img src="${contextPath}/resources/images/thumbnail.png"
                                                                width="110px" height="110px">
                                                        </c:if>
                                            </div>
                                            <div class="doran-block">
                                                <p class="doranLocation"><i class="fal fa-map-marker-alt"></i> ${category.communityArea}</p><br>
                                                <a href="detail?cn=${board.communityNo}${sURL}" class="doranName">${category.communityName}</a><br>
                                                <span class="peopleCount"><i class="fal fa-user"></i> ${category.communityMember}</span> <span class="likeCount"><i class="fas fa-heart"></i> ${category.pick}</span>
        
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
                    <c:set var="url" value="category?type=${param.type}&cp=" />

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




            <jsp:include page="/WEB-INF/views/common/footer.jsp" />
            <script src="${contextPath}/resources/js/community/category.js"></script>
        </body>
    </main>
        </html>