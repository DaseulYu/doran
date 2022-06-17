<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="signup">
    <ul id="signup-list">

        <c:choose> 
            <c:when test="${empty sList}">
                <h4 style="color: #0C71FF;">게시글이 없습니다.<br>첫 가입인사를 작성해주세요.</h4>
            </c:when>

            <c:otherwise>
                <c:forEach var="signup" items="${sList}">
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
                        <div class="write-btn-area">
                         <!--    <c:if test="${loginMember.memberNo != signup.memberNo}">
                                <button id="btn-report">신고</button>
                            </c:if> -->
                            <c:if test="${loginMember.memberNo == signup.memberNo}">
                                <button id="updateBtn" onclick="showUpdateReply(${signup.signupNo}, this)">수정</button>
                                <button id="deleteBtn" onclick="deleteReply(${signup.signupNo})">삭제</button>
                            </c:if>
                        </div>

                    </li>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </ul>
</div>

<div class="message-textarea">
    <textarea maxlength="100" placeholder="내용을 입력해주세요.(100 글자 이하)" id="msgContent"></textarea>
    <button class="btn-reply" id="btn-reply">등록</button>
</div>