<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="signup" style="width: 100%;">
    <ul id="signup-list">

        <c:choose> 
            <c:when test="${empty bSignup}">
                <h4 style="color: #0C71FF;">게시글이 없습니다.<br>첫 가입인사를 작성해주세요.</h4>
            </c:when>

            <c:otherwise>
                <c:forEach var="signup" items="${bSignup}">
                    
                    <li class="signup-row">

                        <div class="user">
                            <c:if test="${empty signup.profileImage}">
                                <img src="${contextPath}/resources/images/user.png"  id="user">
                            </c:if>
                            <c:if test="${!empty signup.profileImage}">
                                <img src="${contextPath}${signup.profileImage}"  id="user">
                            </c:if>

                            <p>${signup.memberNickname}</p>
                        </div>
                        <div class="message">
                            <span class="message-txt">${signup.signupContent}</span>
                            <span class="message-date">${signup.createDate}</span>
                        </div>
                        
                    </li>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </ul>
</div>
