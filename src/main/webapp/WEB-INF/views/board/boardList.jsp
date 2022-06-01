<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>모임명-자유게시판</title>
    <link rel="stylesheet" href="${contextPath}/resources/css/board-community.css">

    <link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-barun-gothic.css" rel="stylesheet">
    
</head>
<body>

    <section class="board-community">

        <div>
            <h2>자유게시판</h2>
        </div>
        <div class="btn-area">
            <button id="insertBtn">새글 작성</button>
        </div>

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
                    <tr>
                        <td>6</td>
                        <td>
                            <a href="#">6번째 게시글</a>
                        </td>
                        <td>유저일</td>
                        <td>2022-05-23</td>
                        <td>50</td>
                    </tr>
                    <tr>
                        <td>7</td>
                        <td>
                            <a href="#">7번째 게시글</a>
                        </td>
                        <td>유저일</td>
                        <td>2022-05-23</td>
                        <td>50</td>
                    </tr>
                    <tr>
                        <td>8</td>
                        <td>
                            <a href="#">8번째 게시글</a>
                        </td>
                        <td>유저일</td>
                        <td>2022-05-23</td>
                        <td>50</td>
                    </tr>
                    <tr>
                        <td>9</td>
                        <td>
                            <a href="#">9번째 게시글</a>
                        </td>
                        <td>유저일</td>
                        <td>2022-05-23</td>
                        <td>50</td>
                    </tr>
                    <tr>
                        <td>10</td>
                        <td>
                            <a href="#">10번째 게시글</a>
                        </td>
                        <td>유저일</td>
                        <td>2022-05-23</td>
                        <td>50</td>
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

        <form action="#" method="get" id="boardSearch">

            <select name="key">
                <option value="t">제목</option>
                <option value="c">내용</option>
                <option value="tc">제목+내용</option>
                <option value="w">닉네임</option>
            </select>

            <input type="text" name="query" placeholder="검색어를 입력해주세요.">

            <button>검색</button>

        </form>
    </section>
</body>
</html>