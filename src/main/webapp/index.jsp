<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>임시index</title>

    <link rel="stylesheet" href="resources/css/member-community.css">
    <link rel="stylesheet" href="resources/css/board-signup.css">
    <link rel="stylesheet" href="resources/css/board-list.css">
    <link rel="stylesheet" href="resources/css/header-footer.css">

    <link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-barun-gothic.css" rel="stylesheet">
    
</head>
<body>

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
                    </div>

                    <div class="head-info">
                        <p>모임소개글</p>
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
    </main>

    <!-- footer -->
    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>
    
</body>
</html>