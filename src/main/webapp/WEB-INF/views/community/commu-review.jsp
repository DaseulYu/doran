<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="review-content">

    <c:choose>
        <c:when test="${empty rList}">
            <h4 style="color: #0C71FF;">게시글이 없습니다.<br>첫 후기를 작성해주세요.</h4>
        </c:when>

        <c:otherwise>
            <c:forEach var="reviewList" items="${rList}">

                <div class="review">
                    <div class="review-img thumbnail">
                        <img src="${contextPath}${reviewList.image0}">
                    </div>
                    <div class="userInfo">
                        <c:if test="${empty reviewList.profileImage}">
                            <img src="${contextPath}/resources/images/user.png" id="user">
                        </c:if>
                        
                        <c:if test="${!empty reviewList.profileImage}">
                            <img src="${contextPath}${reviewList.profileImage}" id="user">
                        </c:if>
                        <span><h4>${reviewList.memberNickname}</h4></span>
                    </div>
                    <div class="review-text">
                        <h4>[${reviewList.boardTitle}]</h4><br>
                        <div>${reviewList.boardContent}</div>
                    </div>
                </div>
            </c:forEach>
        </c:otherwise>
    </c:choose>
</div>