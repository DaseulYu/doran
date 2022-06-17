
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<footer>
    <div class="customer-service">
        <p class="cs">고객센터</p>
        <p class="cs">이메일 : cs@doran.com</p>
        <p class="cs">고객센터 : 02-555-5252</p>
        <p class="cs">업무시간 : 평일 9:00 - 18:00 (점심: 12:00 - 13:00)</p>
        
    </div>
    
    <br><hr style="border: solid 1px rgb(230, 230, 230); width: 40%;" >
    <div class="customer-service info">
        <a href="${contextPath}/resources/images/doranInfo.png">소개</a>
        <!-- <span>|</span>
        <a href="#">이용약관</a>
        <span>|</span>
        <a href="#">개인정보처리방침</a> -->
        <span>|</span>
        <a href="#"> ⓒ DORAN</a>
    </div>
</footer>


<%-- session에 message 속성이 존재하는 경우 alert창으로 해당 내용을 출력 --%>
<c:if test="${ !empty sessionScope.message }">
	<script>
                alert("${message}");
        </script>

	<%-- message 1회 출력 후 session에서 제거 --%>
	<c:remove var="message" scope="session" />
</c:if>
