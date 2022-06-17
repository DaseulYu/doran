<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:set var="pagination" value="${map.pagination}" />
<c:set var="reportList" value="${map.reportList}" />

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>관리자 페이지</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/admin-report.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/main-style.css">

    <link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-barun-gothic.css" rel="stylesheet">
</head>
<body>

    <main>
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>
        
        <section class="admin-detail">
            
            <div>
                <h2>신고 관리</h2>
            </div>
            <nav class="admin-nav">
                <ul>
                    <li><a href="${contextPath}/admin/list">공지사항</a></li>
                    <li><a href="${contextPath}/admin/report">신고관리</a></li>
                </ul>
            </nav>

            <div class="list-wrapper">
                <table class="list-table" border="1">
                    <thead>
                        <tr>
                            <th>번호</th>
                            <th>신고한 회원</th>
                            <th>신고받은 게시글</th>
                            <th>신고 사유</th>
                            <th>신고 날짜</th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:choose>

                            <c:when test="${empty reportList}">
                                <tr>
                                    <td colspan="7">게시글이 없습니다.</td>
                                </tr>
                            </c:when>
                            
                            <c:otherwise>
                                <c:forEach var="reportList" items="${reportList}">
                                    <tr>
                                        <td>${reportList.reportNo}</td>
                                        <td>${reportList.memberID}</td>
                                        <td>${reportList.boardTitle}</td>
                                        <td>${reportList.reportTitle}</td>
                                        <td>${reportList.reportDate}</td>
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
        </section>

    </main>

    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

</body>
</html>