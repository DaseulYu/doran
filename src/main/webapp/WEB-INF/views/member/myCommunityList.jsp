<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>




        <!DOCTYPE html>
        <html lang="en">
    
        <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>마이 페이지 - 내 모임 신청 내역</title>


            <link rel="stylesheet" href="${contextPath}/resources/css/main-style.css">
            <link rel="stylesheet" href="${contextPath}/resources/css/myPage-style.css">

            <link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-barun-gothic.css" rel="stylesheet">


            <script src="https://kit.fontawesome.com/aab319511f.js" crossorigin="anonymous"></script>
        </head>

        <body>
            <main>

                
	
		       
                <jsp:include page="/WEB-INF/views/common/header.jsp" />


                <section class="myPage-content">

                    
    
                    <jsp:include page="/WEB-INF/views/member/sideMenu.jsp"/>
                
                    
                    <div class="list-wrapper">

                        <h1 class="myPage-title"> 내 모임 신청 내역</h1>
                        <table class="list-table">
                            <thead>
                                <tr>
                                    <th>모임명</th>
                                    <th>모임지역</th>
                                    <th>모임상태</th>
                                    <th>승인여부</th>
                                    <th>신청날짜</th>
                                    
                                </tr>
                            </thead>

                            <tbody id="commuList">
                                <tr>
                                    <td>
                                        <a href="#">모임명</a>
                                    </td>
                                    <td>서울시 종로구</td>
                                    <td>승인 거절</td>
                                    <td>모임 유지</td>
                                    <td>모임 유지</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>

                </section>
            </main>

            <jsp:include page="/WEB-INF/views/common/footer.jsp" />
       
            
        <!-- jQuery 라이브러리 추가(CDN) -->
        <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
        

            <script>
                const loginMemberNo = "${loginMember.memberNo}";
            </script>

            <script src="${contextPath}/resources/js/myCommunityList.js"></script>

        </body>

        </html>