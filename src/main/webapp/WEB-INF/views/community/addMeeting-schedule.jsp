<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>새 모임 추가페이지</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/schedule-style.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/main-style.css">

    <script src="https://kit.fontawesome.com/a2e8ca0ae3.js" crossorigin="anonymous"></script>
</head>
<body>
    <main>
    
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>

        <section class="addMeeting-content">

            <form action="#" method="POST" name="addMeeting-form" onsubmit="return addMeetingLast()">
                <h1>새 모임 추가하기</h1>
                <div>
                    <a href="addMeeting.html" id="info">상세정보</a>
                    <a href="#" onclick="location.reload();" id="sch">일정관리</a>
                </div>
                
                <div class="schedule-input-area">
                    <img src="${contextPath}/resources/images/clock_icon.png" id="clock">
                    <input type="date" id="groupTime-1" name="groupTime">
                    <select name="time" id="time-1"></select> <br>
                </div>

                <div class="schedule-input-area">
                    <input type="date" id="groupTime-2" name="groupTime">
                    <select name="time" id="time-2"></select> <br>
                </div>

                <div class="schedule-input-area">
                    <span class="schedule-message confirm">일정 : 2022년 3월 22일 오전 12:00 ~ 2022년 5월 22일 오전 12:00</span>
                </div>

                <div class="schedule-input-area">
                    <img src="${contextPath}/resources/images/map_icon.png" id="map">
                    
                </div>

                <div class="schedule-input-area">
                    <span>위치 : 서울특별시 중구 남대문로1가 19</span>
                </div>

                <div class="schedule-input-area">
                    <label for="limitedPeople">제한인원 : </label>
                    <input type="text" id="limitedPeople" name="limitedPeople" placeholder="인원 수를 입력해주세요">
                </div>

                <div class="schedule-input-area-exp">
                    <label for="scheduleExp">일정 설명 : </label>
                    <textarea type="text" id="scheduleExp" name="scheduleExp" placeholder="내용을 입력해주세요." ></textarea>
                </div>

                <div class="schedule-input-area">
                    <button type="submit">추가하기</button>
                </div>
            </form>
        </section>
    </main>
    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    
    <script src="${contextPath}/resources/js/community/addMeeting-schedule.js"></script>
</body>
</html>