<<<<<<< HEAD
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

=======
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
>>>>>>> origin/YDS


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>임시index</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/member-community.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/board-signup.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/board-list.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/main-style.css">
<<<<<<< HEAD
=======

>>>>>>> origin/YDS
    <link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-barun-gothic.css" rel="stylesheet">
    
</head>
<body>

<<<<<<< HEAD
    
    <jsp:include page="/WEB-INF/views/common/header.jsp" />

    <a href="${contextPath}/member/login">로그인</a>
    <div class="board-container">
    
        <form class="community-head">
            <div class="head-left">
                <img src="${contextPath}/resources/images/sample.jpg">
            </div>
            <div class="head-right">
                <div class="head-title">
                    <div class="community-name">
                        <span>도란도란</span>
                        <a href="#"><span class="btn-edit">edit</span></a>
=======
    <!-- 임시 index => 메인 완성 후 교체 -->

    <main>
        <!-- header -->
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>

        <!-- 임시 버튼 -->
        <a href="community/detail?cn=1"> cn=1모임 바로가기 </a>
    
        <div class="board-container">

            <form class="community-head">
                <div class="head-left">
                    <img src="resources/images/sample.jpg">
                </div>
                <div class="head-right">
                    <div class="head-title">
                        <div class="community-name">
                            <span>INDEX</span>
                            <a href="#"><span class="btn-edit">edit</span></a>
                        </div>
                        <div><button class="btn-report">신고</button></div>
>>>>>>> origin/YDS
                    </div>

<<<<<<< HEAD
                <div class="head-info">
                    <p>모임소개글</p>
                </div>

                <div class="head-user">
                    <div><img src="${contextPath}/resources/images/user.png" id="user"></div>
                    <div>닉네임</div>
                </div>

                <div class="head-join">
                    <div>
                        <img src="${contextPath}/resources/images/pick1.png" id="pick"></a>
=======
                    <div class="head-info">
                        <p>모임소개글</p>
>>>>>>> origin/YDS
                    </div>

                    <div class="head-user">
                        <div><img src="resources/images/user_sample.jpg" id="user"></div>
                        <div>닉네임</div>
                    </div>

                    <div class="head-join">
                        <div>
                            <img src="resources/images/pick1.png" id="pick"></a>
                        </div>
                        <div class="btn-join">모임 가입하기</div>
                    </div>
                </div>
            </form>
        </div>
    </main>

    <!-- jQuery 라이브러리 추가 (CDN) -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

<<<<<<< HEAD
            </div>

            <!-- 가입인사 -->
            <div class="board board-signup">
                <h3>가입인사
                    <a href="board-signup.html" target="_blank"><span class="board-all">전체 가입인사 보기 &gt;</span></a>
                </h3>

                <div class="signup">
                    <div class="user">
                        <img src="resources/images/user.png" id="user">
                        <p>닉네임입니다</p>
                    </div>
                    <div class="message">
                        <p>
                            <span class="message-txt">안녕하세요.</span>
                            <span class="message-date">2022.05.22</span>
                        </p>
                    </div>
                </div>
                <div class="signup">
                    <div class="user">
                        <img src="resources/images/user.png" id="user">
                        <p>닉네임입니다</p>
                    </div>
                    <div class="message">
                        <p>
                            <span class="message-txt">안녕하세요.</span>
                            <span class="message-date">2022.05.22</span>
                        </p>
                    </div>
                </div>
                <div class="signup">
                    <div class="user">
                        <img src="resources/images/user.png" id="user">
                        <p>닉네임입니다</p>
                    </div>
                    <div class="message">
                        <p>
                            <span class="message-txt">안녕하세요.</span>
                            <span class="message-date">2022.05.22</span>
                        </p>
                    </div>
                </div>
                <div class="message-textarea">
                    <textarea maxlength="100" placeholder="내용을 입력해주세요.(100 글자 이하)"></textarea>
                    <button class="btn-reply">등록</button>
                </div>
            </div>


            <!-- 자유게시판 -->
            <!-- <form action="board/community" method="post" name="community-summary"> -->
                <div class="board board-list">
                    <h3>자유게시판
                        <a href="/SemiProject/community/" target="_blank"><span class="board-all">전체 게시글 보기 &gt;</span></a>
                    </h3>

                    <div class="list-wrapper">
                        <table class="list-table">
                            <thead>
                                <tr>
                                    <th>번호</th>
                                    <th>제목</th>
                                    <th>작성자</th>
                                    <th>작성일</th>
                                    <th>조회수</th>
                                </tr>
                            </thead>
            
                            <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>
                                        <a href="#">1번째 게시글</a>
                                    </td>
                                    <td>유저일</td>
                                    <td>2022-05-23</td>
                                    <td>50</td>
                                </tr>
                                <tr>
                                    <td>2</td>
                                    <td>
                                        <a href="#">2번째 게시글</a>
                                    </td>
                                    <td>유저일</td>
                                    <td>2022-05-23</td>
                                    <td>50</td>
                                </tr>
                                <tr>
                                    <td>3</td>
                                    <td>
                                        <a href="#">3번째 게시글</a>
                                    </td>
                                    <td>유저일</td>
                                    <td>2022-05-23</td>
                                    <td>50</td>
                                </tr>
                                <tr>
                                    <td>4</td>
                                    <td>
                                        <a href="#">4번째 게시글</a>
                                    </td>
                                    <td>유저일</td>
                                    <td>2022-05-23</td>
                                    <td>50</td>
                                </tr>
                                <tr>
                                    <td>5</td>
                                    <td>
                                        <a href="#">5번째 게시글</a>
                                    </td>
                                    <td>유저일</td>
                                    <td>2022-05-23</td>
                                    <td>50</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
            <!-- </form> -->



            <!-- 모임 후기 -->
            <div class="board board-review">
                <h3>모임후기
                    <a href="board-review.html" target="_blank"><span class="board-all">전체 후기 보기 &gt;</span></a>
                </h3>  

                <div class="review-content">

                    <div class="review">
                        <div class="review-img thumbnail">
                            <img src="resources/images/sample.jpg">
                        </div>
                        <div class="userInfo">
                            <div>
                                <img src="resources/images/user.png" id="user">
                            </div>
                            <span><h4>닉네임</h4></span>
                        </div>
                        <div class="review-text">
                            모임후기1<br>
                            모임후기2<br>
                            모임후기3<br>
                            모임후기4<br>
                            모임후기5<br>
                        </div>
                    </div>

                    <div class="review">
                        <div class="review-img thumbnail">
                            <img src="resources/images/sample.jpg">
                        </div>
                        <div class="userInfo">
                            <div>
                                <img src="resources/images/user.png" id="user">
                            </div>
                            <span><h4>닉네임</h4></span>
                        </div>
                        <div class="review-text">
                            모임후기1<br>
                            모임후기2<br>
                            모임후기3<br>
                            모임후기4<br>
                            모임후기5<br>
                        </div>
                    </div>

                    <div class="review">
                        <div class="review-img thumbnail">
                            <img src="resources/images/sample.jpg">
                        </div>
                        <div class="userInfo">
                            <div>
                                <img src="resources/images/user.png" id="user">
                            </div>
                            <span><h4>닉네임</h4></span>
                        </div>
                        <div class="review-text">
                            모임후기1<br>
                            모임후기2<br>
                            모임후기3<br>
                            모임후기4<br>
                            모임후기5<br>
                        </div>
                    </div>

                    <div class="review">
                        <div class="review-img thumbnail">
                            <img src="resources/images/sample.jpg">
                        </div>
                        <div class="userInfo">
                            <div>
                                <img src="resources/images/user.png" id="user">
                            </div>
                            <span><h4>닉네임</h4></span>
                        </div>
                        <div class="review-text">
                            모임후기1<br>
                            모임후기2<br>
                            모임후기3<br>
                            모임후기4<br>
                            모임후기5<br>
                        </div>
                    </div>

                </div>

            </div>

        </section>

    </div>
    <!-- jQuery 라이브러리 추가 (CDN) -->
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
=======
    <!-- footer -->
    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    
>>>>>>> origin/YDS
</body>


	
</html>