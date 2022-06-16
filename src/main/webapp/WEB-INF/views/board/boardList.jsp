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
    <title>${boardName}</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/board-list.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/main-style.css">

    <link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-barun-gothic.css" rel="stylesheet">
    
</head>
<body>

    <main>
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>

        <c:if test="${!empty param.key}">
            <c:set var="sURL" value="&key=${param.key}&query=${param.query}" />
        </c:if>

        <section class="board-list">
            <div>
                <h2>${boardName}</h2>
            </div>

            <div class="btn-area">
                    <button id="insertBtn" onclick="location.href='write?mode=insert&cn=${param.cn}&type=${param.type}&cp=${param.cp}'">새글 작성</button>
            </div>

            <div class="list-wrapper">
                <table class="list-table">
                    <thead>
                        <tr>
                            <th>번호</th>
                            <th>제목</th>
                            <th>작성자</th>
                            <th>작성일</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:choose>
                            <c:when test="${empty boardList}">
                                <tr>
                                    <td colspan="5">게시글이 없습니다.</td>
                                </tr>
                            </c:when>

                            <c:otherwise>
                                <c:forEach var="boardList" items="${boardList}">
                                    <tr>
                                        <td>${boardList.boardNo}</td>
                                        <td>
                                            <a href="detail?no=${boardList.boardNo}&type=${param.type}&cn=${param.cn}&cp=${pagination.currentPage}">${boardList.boardTitle}</a>
                                        </td>
                                        <td>${boardList.memberNickname}</td>
                                        <td>${boardList.createDate}</td>
                                    </tr>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                </table>
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

            <form action="#" method="get" id="noticeSearch" onsubmit="return searchValidate()">
                <input type="hidden" name="type" value="${param.type}">
                <input type="hidden" name="cn" value="${param.cn}">

                <select name="key" id="search-key">
                    <option value="t">제목</option>
                    <option value="c">내용</option>
                    <option value="tc">제목+내용</option>
                    <option value="w">닉네임</option>
                </select>

                <input type="text" name="query" id="search-query" placeholder="검색어를 입력해주세요.">

                <button>검색</button>

            </form>
        </section>
        
    </main>

    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>

    <script src="${contextPath}/resources/js/board/board.js"></script>
    <script src="${contextPath}/resources/js/board/board-write.js"></script>
    
</body>
</html>