<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>새 모임 추가</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/addMeeting-style.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/main-style.css">

    <script src="https://kit.fontawesome.com/a2e8ca0ae3.js" crossorigin="anonymous"></script>
</head>
<body>
    <main>
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>

        <section class="addMeeting-content">
            
            <form action="#" method="POST" name="addMeeting-form" class="addMeeting-form" onsubmit="return addMeeting()">
                <h1 class="addMeeting">새 모임 추가하기</h1>
                <div class="select area">
                    <a href="#" onclick="location.reload();" id="info">상세정보</a>
                </div>

                <table class="addMeeting-table">
                    <tr>
                        <td><label for="groupName">모임이름 : </label></td>
                        <th><input type="text" id="groupName" name="groupName" placeholder="모임 명을 입력해주세요." maxlength="30" autocomplete="off" required></th>
                    </tr>

                    <tr>
                        <td><label for="groupCategory">카테고리 : </label></td>
                        <th><select name="groupCategory" id="groupCategory">
                            <option selected>카테고리를 선택해주세요</option>
                            <option value="hobby">취미활동</option>
                            <option value="sports">운동/스포츠</option>
                            <option value="music">음악/악기</option>
                            <option value="travel">아웃도어/여행</option>
                            <option value="job">업종/직무</option>
                            <option value="festival">문화/공연/축제</option>
                            <option value="service">봉사활동</option>
                            <option value="connection">사교/인맥</option>
                        </select></th>
                    </tr>

                    <tr>
                        <td><label for="regionCategory">지역선택 : </label></td>
                        <th><select id="regionCategory" name="regionCategory" onchange="categoryChange(this)">
                            <option selected>시</option>
                            <option value="seoul">서울특별시</option>
                            <option value="inchen">인천광역시</option>
                            <option value="gyeonggi">경기도</option>
                            <option value="gangwon">강원도</option>
                            <option value="chungcheongnam">충청남도</option>
                            <option value="chungcheongbuk">충청북도</option>
                            <option value="gyeongsangnam">경상남도</option>
                            <option value="gyeongsangbuk">경상북도</option>
                            <option value="jeollanam">전라남도</option>
                            <option value="jeollabuk">전라북도</option>
                            <option value="daejeon">대전광역시</option>
                            <option value="daegu">대구광역시</option>
                            <option value="busan">부산광역시</option>
                            <option value="ulsan">울산광역시</option>
                            <option value="gwangju">광주광역시</option>
                            <option value="sejong">세종특별자치시</option>
                            <option value="seoul">제주도</option>
                        </select>
                        
                        <select id="region-2" name="regionCategory">
                            <option selected>군/구</option>
                        </select></th>
                    </tr>
                
                    <tr>
                        <td><label for="groupIntroduce">모임소개 : </label></td>
                        <th><textarea type="text" id="groupIntroduce" name="groupIntroduce" placeholder="내용을 입력해주세요."></textarea></th>
                    </tr>   

                </table>

                <div class="addMeeting-button-area">
                    <button id="cancel">취소하기</button>
                    <button type="submit" id="add">추가하기</button>
                </div>
            </form>
        </section>
    </main>

    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    
    <script src="${contextPath}/resources/js/community/addMeeting.js"></script>
    <script src="${contextPath}/resources/js/community/region-select.js"></script>

</body>
</html>