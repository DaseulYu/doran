<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>가입인사</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/board-signup.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/main-style.css">

    <link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-barun-gothic.css" rel="stylesheet">
    
</head>
<body>
    <main>
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>


        <div class="board-signup">
            <div>
                <h2>가입인사</h2>
            </div>

            
            <jsp:include page="/WEB-INF/views/board/board-signup-write.jsp"/>

        
        </div>
    </main>

    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

    <script>
        const contextPath = "${contextPath}";
        const communityNo = "${param.cn}";
        const loginMemberNo = "${loginMember.memberNo}";
        const communityMemberNo = "${comm.memberNo}";
    </script>

    <script src="${contextPath}/resources/js/board/board-signup.js"></script>

</body>
</html>