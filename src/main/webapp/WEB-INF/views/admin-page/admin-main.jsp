<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="pagination" value="${map.pagination}" />
<c:set var="noticeList" value="${map.noticeList}" />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>관리자 페이지</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/admin-main.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/main-style.css">

    <link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-barun-gothic.css" rel="stylesheet">
</head>
<body>

    <main>
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>
        
        <section class="admin-detail">
            <div>
                <h2>관리자 페이지</h2>
            </div>
            <nav class="admin-nav">
                <ul>
                    <li><a href="${contextPath}/admin/list">공지사항</a></li>
                    <li><a href="${contextPath}/admin/report">신고관리</a></li>
                </ul>
            </nav>

            <c:if test="${!empty param.key}">
                <c:set var="sURL" value="&key=${param.key}&query=${param.query}" />
            </c:if>
            
            <div class="btn-area">
                <button id="insertBtn" onclick="location.href='write?mode=insert'">새글작성</button>
            </div>

            <div class="list-wrapper">
                <table class="list-table" border="1">
                    <thead>
                        <tr>
                            <th>번호</th>
                            <th>카테고리</th>
                            <th>제목</th>
                            <th>작성일</th>
                            <th colspan="3">게시글 상태<br>(등록:N, 삭제:Y)</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:choose>
                            <c:when test="${empty noticeList}">
                                <tr>
                                    <td colspan="7">게시글이 없습니다.</td>
                                </tr>
                            </c:when>

                            <c:otherwise>
                                <c:forEach var="noticeList" items="${noticeList}">
                                    <tr>
                                        <td>${noticeList.noticeNo}</td>
                                        <td>${noticeList.noticeName}</td>
                                        <td>
                                            <a href="detail?no=${noticeList.noticeNo}&cp=${pagination.currentPage}${sURL}">${noticeList.noticeTitle}</a>
                                        </td>
                                        <td>${noticeList.createDate}</td>
                                        <td id="noticeState">${noticeList.noticeState}</td>
                                        <td>
                                            <button id="noticeInsertBtn" onclick="noticeInsert(${noticeList.noticeNo})">등록</button>
                                        </td>
                                        <td>
                                            <button id="noticeDeleteBtn" onclick="noticeDelete(${noticeList.noticeNo})">삭제</button>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </tbody>
                </table>
            </div>


            <div class="paging">
                <c:set var="url" value="list?cp="/>
                <ul class="pagination">
                    <!-- 첫 페이지로 이동 -->
                    <li><a href="${url}1${sURL}">&lt;&lt;</a></li>
                                        
                    <!-- 이전 목록 마지막 번호로 이동 -->
                    <li><a href="${url}${pagination.prevPage}${sURL}">&lt;</a></li>

                    <!-- 범위가 정해진 일반 for문 사용 -->
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

                <select name="key"  id="search-key">
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

    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

    <script src="${contextPath}/resources/js/admin-page/admin-main.js"></script>

</body>
</html>