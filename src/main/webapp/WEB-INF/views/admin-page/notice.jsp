<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="noticeName" value="${map.noticeName}" />
<c:set var="pagination" value="${map.pagination}" />
<c:set var="noticeList" value="${map.noticeList}" />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>공지사항</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/notice.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/main-style.css">

    <link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-barun-gothic.css" rel="stylesheet">
</head>
<body>

    <main>
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>
        
        <section class="notice-content">
            <div>
                <h2>
                    <c:choose>
                        <c:when test="${empty param.type}">
                            전체 게시글
                        </c:when>
                        <c:otherwise>
                            ${noticeName}
                        </c:otherwise>
                    </c:choose>
                </h2>
            </div>

            <nav class="notice-nav">
                <ul>
                    <li><a href="${contextPath}/notice">전체</a></li>
                    <li><a href="${contextPath}/notice/list?type=1">공지사항</a></li>
                    <li><a href="${contextPath}/notice/list?type=2">이벤트</a></li>
                    <li><a href="${contextPath}/notice/list?type=3">당첨자 발표</a></li>
                </ul>
            </nav>

            <c:if test="${!empty param.key}">
                <c:set var="sURL" value="&key=${param.key}&query=${param.query}" />
            </c:if>

            <div class="list-wrapper">
                <table class="list-table">
                    <thead>
                        <tr>
                            <th>분류</th>
                            <th>제목</th>
                            <th>작성일</th>
                            <th>작성자</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:choose>
                            <c:when test="${empty noticeList}">
                                <tr>
                                    <td colspan="4">게시글이 없습니다.</td>
                                </tr>
                            </c:when>

                            <c:otherwise>
                                <c:forEach var="noticeList" items="${noticeList}">

                                    <tr>
                                        <td>${noticeList.noticeName}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${empty param.type}">
                                                    <a href="notice/detail?no=${noticeList.noticeNo}&cp=${pagination.currentPage}${sURL}">${noticeList.noticeTitle}</a>
                                                </c:when>
                                                <c:otherwise>
                                                    <a href="detail?no=${noticeList.noticeNo}&cp=${pagination.currentPage}&type=${param.type}${sURL}">${noticeList.noticeTitle}</a>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>${noticeList.createDate}</td>
                                        <td>${noticeList.memberNickname}</td>
                                    </tr>

                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </tbody>
                </table>
            </div>

            <div class="paging">

                <c:choose>
                    <c:when test="${empty param.type}">
                        <c:set var="url" value="notice?cp="/>
                    </c:when>
                    <c:otherwise>
                        <c:set var="url" value="list?type=${param.type}&cp="/>
                    </c:otherwise>
                </c:choose>


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

            <form action="#" method="get" id="noticeSearch" onsubmit="return searchValidate()">
                <input type="hidden" name="type" value="${param.type}">
                <select name="key" id="search-key">
                    <option value="t">제목</option>
                    <option value="c">내용</option>
                    <option value="tc">제목+내용</option>
                </select>

                <input type="text" name="query" id="search-query" placeholder="검색어를 입력해주세요.">

                <button>검색</button>
            </form>

        </section>

    </main>

    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>

    <script src="${contextPath}/resources/js/admin-page/notice.js"></script>

</body>
</html>