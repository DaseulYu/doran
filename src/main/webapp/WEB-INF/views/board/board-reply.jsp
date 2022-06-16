<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<div class="reply-list-area">
    <ul id="reply-list">

        <c:forEach var="reply" items="${rList}">
            <li class="reply-row">

                <div class="reply-nick">
                    <c:if test="${empty reply.profileImage}">
                        <img src="${contextPath}/resources/images/user.png">
                    </c:if>
                    
                    <c:if test="${!empty reply.profileImage}">
                        <img src="${contextPath}${reply.profileImage}">
                    </c:if>
                    <p>${reply.memberNickname}</p>
                </div>
                <div class="reply-txt-area">
                    <span class="reply-txt">${reply.replyContent}</span>
                    <span class="reply-txt-date">${reply.createDate}</span>
                </div>
                
                <div class="reply-btn-area">
                    <!-- <c:if test="${loginMember.memberNo != reply.memberNo}">
                        <button id="btn-report">신고</button>
                    </c:if> -->
                    <c:if test="${loginMember.memberNo == reply.memberNo}">
                        <button id="updateBtn" onclick="showUpdateReply(${reply.replyNo}, this)">수정</button>
                        <button id="deleteBtn" onclick="deleteReply(${reply.replyNo})">삭제</button>
                    </c:if>
                </div>

            </li>
        </c:forEach>
    </ul>
</div>

<div class="reply-write-area">
    <textarea maxlength="100" placeholder="내용을 입력해주세요.(100 글자 이하)" id="msgContent"></textarea>
    <button class="btn-reply" id="btn-reply">등록</button>
</div>