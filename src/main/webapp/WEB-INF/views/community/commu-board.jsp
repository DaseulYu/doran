<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="list-wrapper">
    <table class="list-table">
        <thead>
            <tr>
                <th>번호</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일</th>
                <!-- <th>조회수</th> -->
            </tr>
        </thead>

        <tbody>
            <c:choose>
                <c:when test="${empty bList}">
                    <tr>
                        <td colspan="5">게시글이 없습니다.</td>
                    </tr>
                </c:when>

                <c:otherwise>
                    <c:forEach var="boardList" items="${bList}">
                        <tr>
                            <td>${boardList.boardNo}</td>
                            <td>${boardList.boardTitle}</td>
                            <td>${boardList.memberNickname}</td>
                            <td>${boardList.createDate}</td>
                            <!-- <td>${boardList.readCount}</td> -->
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
    </table>
</div>