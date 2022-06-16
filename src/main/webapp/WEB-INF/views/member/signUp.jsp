<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>회원가입</title>

            <link rel="stylesheet" href="${contextPath}/resources/css/signUp-style.css">
            <link rel="stylesheet" href="${contextPath}/resources/css/main-style.css">
        </head>
        <main>

            <body>

                <!-- header -->
                <jsp:include page="/WEB-INF/views/common/header.jsp" />


                <section class="signUp-content">
                    <form action="signUp" method="Post" name="signUp-form" onsubmit="return signUpValidate()">
                        <div class="title">
                            <sapn>회원가입</sapn>
                        </div>
                        <div class="signUp-input-area">

                            <input type="text" id="memberId" name="memberId" class="singUp-input" placeholder="이메일"
                                maxlength="30" autocomplete="off">

                            <button class="certified" type="button" id="sendBtnC">이메일 인증</button>

                        </div>
                        <p class="signUp-message" id="emailMessage">이메일을 입력해주세요.</p>

                        <div class="signUp-input-area">
                            <input type="text" id="cNumber" class="singUp-input" placeholder="인증번호를 입력해주세요"
                                maxlength="6" autocomplete="off">

                            <button class="certified" type="button" id="cBtn">인증번호 확인</button>
                        </div>
                        <p class="signUp-message" id="cMessage"></p>

                        <div class="signUp-input-area">
                            <input type="password" id="memberPw" name="memberPw" class="singUp-input" placeholder="비밀번호"
                                maxlength="30">
                            <br>
                        </div>
                        <p class="signUp-message" id="pwMessage">영문, 숫자, 특수문자 조합으로 8자 이상 20글자 이하</p>

                        <div class="signUp-input-area">
                            <input type="password" id="memberPwConfirm" class="singUp-input" placeholder="비밀번호"
                                maxlength="30">
                            <br>
                        </div>
                        <p class="signUp-message" id="confirmPwMessage"></p>




                        <div class="signUp-input-area">
                            <input type="text" id="memberName" name="memberName" class="singUp-input" placeholder="이름"
                                maxlength="10">
                        </div>
                        <span class="signUp-message" id="nameMessage"></span>
                        <div class="signUp-input-area">
                            <input type="text" id="memberTel" name="memberTel" class="singUp-input"
                                placeholder="휴대폰 번호(-없이 입력)" maxlength="11">
                        </div>
                        <span class="signUp-message" id="telMessage"></span>
                        <div class="signUp-input-area">
                            <input type="text" id="memberNickname" name="memberNickname" class="singUp-input"
                                placeholder="닉네임" maxlength="8">
                            <div class="form-radio">
                                <div class="form-flex">
                                    <input type="radio" name="memberGender" value="m" id="male">
                                    <label for="male">남</label>
                                    <input type="radio" name="memberGender" value="f" id="female">
                                    <label for="female">여</label>
                                </div>
                            </div>
                        </div>
                        <span class="signUp-message" id="nickMessage"> </span>
                        <br><br>
                        <div class="signUp-input-area birthLive">
                            <input type="text" name="memberBirth" id="memberBirth" class="singUp-input"
                                placeholder="생년월일" aria-invalid="false" autocomplete="off">
                            <input type="text" name="memberLive" id="memberLive" placeholder="지역" class="singUp-input"  autocomplete="off">
                        </div>

                        <div class="search_boxes">
                            <div class="search_box">
                                <select name="local" id="local" onchange="categoryChange(this)">
                                    <option value>시/도 선택</option>
                                    <option value="강원">강원</option>
                                    <option value="경기">경기</option>
                                    <option value="경남">경남</option>
                                    <option value="경북">경북</option>
                                    <option value="광주">광주</option>
                                    <option value="대구">대구</option>
                                    <option value="대전">대전</option>
                                    <option value="부산">부산</option>
                                    <option value="서울">서울</option>
                                    <option value="울산">울산</option>
                                    <option value="인천">인천</option>
                                    <option value="전남">전남</option>
                                    <option value="전북">전북</option>
                                    <option value="제주">제주</option>
                                    <option value="충남">충남</option>
                                    <option value="충북">충북</option>
                                </select>
                            </div>

                            <div class="search_box">
                                <select name="state" id="state">
                                    <option>군/구 선택</option>
                                </select>
                            </div>
                        </div>

                        <br>
                        <!-- <div>
                        <span class="signUp-category">관심 카테고리(선택)</span>
                        <br>
                        <div class="category">
                            <div class="category">
                                <label for="outdoor">
                                    <img src="/images/category.JPG" width="80px" height="80px">
                                    <br>
                                    <span>아웃도어/여행</span>
                                    <br>
                                    <input type="checkbox" id="outdoor" name="memberCategory" value="1" />
                                </label>
                            </div>



                            <div class="category">
                                <label for="sport">
                                    <img src="/images/category.JPG" width="80px" height="80px">
                                    <br>
                                    <span>운동/스포츠</span>
                                    <br>
                                    <input type="checkbox" id="sport" name="memberCategory" value="2" />
                                </label>
                            </div>

                            <div class="category">
                                <label for="job">
                                    <img src="/images/category.JPG" width="80px" height="80px">
                                    <br>
                                    <span>업종/직무</span>
                                    <br>
                                    <input type="checkbox" id="job" name="memberCategory" value="3" />
                                </label>
                            </div>

                            <div class="category">
                                <label for="festival">
                                    <img src="/images/category.JPG" width="80px" height="80px">
                                    <br>
                                    <span>문화/공연/축제</span>
                                    <br>
                                    <input type="checkbox" id="festival" name="memberCategory" value="4" />
                                </label>
                            </div>



                        </div>


                        <br>

                        <div class="category">
                            <div class="category">
                                <label for="music">
                                    <img src="/images/category.JPG" width="80px" height="80px">
                                    <br>
                                    <span>음악/악기</span>
                                    <br>
                                    <input type="checkbox" id="music" name="memberCategory" value="5" />
                                </label>
                            </div>

                            <div class="category">
                                <label for="volunteer">
                                    <img src="/images/category.JPG" width="80px" height="80px">
                                    <br>
                                    <span>봉사활동</span>
                                    <br>
                                    <input type="checkbox" id="volunteer" name="memberCategory" value="6" />
                                </label>
                            </div>

                            <div class="category">
                                <label for="society">
                                    <img src="/images/category.JPG" width="80px" height="80px">
                                    <br>
                                    <span>사교/인맥</span>
                                    <br>
                                    <input type="checkbox" id="society" name="memberCategory" value="7" />
                                </label>
                            </div>

                            <div class="category">
                                <label for="hobby">
                                    <img src="/images/category.JPG" width="80px" height="80px">
                                    <br>
                                    <span>취미활동</span>
                                    <br>
                                    <input type="checkbox" id="hobby" name="memberCategory" value="8" />
                                </label>
                            </div>

                        </div>
                    </div> -->
                        <button class="next">가입하기</button>
                        </div>
                        <!-- footer -->
                    </form>
                </section>


                <!-- jQuery -->
                <link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
                <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
                <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
                
                <!-- js -->
                <script src="${contextPath}/resources/js/member/signUp.js"></script>
            </body>
        </main>
        <jsp:include page="/WEB-INF/views/common/footer.jsp" />

        </html>