<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>관리자</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/admin-report.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/main-style.css">

    <link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-barun-gothic.css" rel="stylesheet">
</head>
<body>

    <main>
        <jsp:include page="/WEB-INF/views/common/header.jsp"/>
        
        <section class="admin-detail">
            
            <div>
                <h2>신고 관리</h2>
            </div>
            <nav class="admin-nav">
                <ul>
                    <li><a href="${contextPath}/admin/list">공지사항</a></li>
                    <li><a href="${contextPath}/admin/report">신고관리</a></li>
                </ul>
            </nav>

            <div class="list-wrapper">
                <div class="list-tab">
                    <ul>
                        <li><a class="current-tab">신고받은 게시글</a></li>
                        <li><a href="#">신고받은 회원</a></li>
                    </ul>
                </div>
                <table class="list-table" border="1">
                    <thead>
                        <tr>
                            <th>번호</th>
                            <th>신고한 회원</th>
                            <th>신고받은 회원</th>
                            <th>신고 게시글</th>
                            <th>사유</th>
                            <th>신고날짜</th>
                            <th colspan="2">관리</th>
                        </tr>
                    </thead>

                    <tbody>
                        <tr>
                            <td>1</td>
                            <td>user01@xx.com</td>
                            <td>user02@xx.com</td>
                            <td>
                                <a href="#">신고 게시글 제목</a>
                            </td>
                            <td>부적절한 게시글</td>
                            <td>2022.05.23</td>
                            <td>
                                <button>정지</button>
                            </td>
                            <td>
                                <button>취소</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>

            <div class="paging">
                <ul>
                    <li><a href="#">&lt;&lt;</a></li>
                    <li><a href="#">&lt;</a></li>
                    <li><a class="current">1</a></li>
                    <li><a href="#">2</a></li>
                    <li><a href="#">3</a></li>
                    <li><a href="#">4</a></li>
                    <li><a href="#">5</a></li>
                    <li><a href="#">&gt;</a></li>
                    <li><a href="#">&gt;&gt;</a></li>
                </ul>
            </div>

            <form action="#" method="get" id="noticeSearch">

                <select name="key">
                    <option value="m">회원</option>
                    <option value="t">게시글</option>
                </select>

                <input type="text" name="query" placeholder="검색어를 입력해주세요.">

                <button>검색</button>

            </form>
        </section>

    </main>

    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>

</body>
</html>