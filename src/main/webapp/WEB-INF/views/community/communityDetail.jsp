<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>${comm.communityName}</title>

    <link rel="stylesheet" href="${contextPath}/resources/css/member-community.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/board-signup.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/board-list.css">
    <link rel="stylesheet" href="${contextPath}/resources/css/main-style.css">

    <link href="https://hangeul.pstatic.net/hangeul_static/css/nanum-barun-gothic.css" rel="stylesheet">

</head>
<body>

    <main>

        <jsp:include page="/WEB-INF/views/common/header.jsp"/>
        
        <div class="board-container">

            <form action="report" method="post" class="report-popup" onsubmit="return reportValidation()">
                <div class="report-detail">
                    <div>신고 사유를 선택해주세요.</div>
                    <select name="reportTitle" id="reportCategory">
                        <option value="0" selected disabled>신고사유 선택</option>
                        <option value="부적절한 게시글">부적절한 게시글</option>
                        <option value="욕설">욕설</option>
                        <option value="사용자 비방">사용자 비방</option>
                    </select>
                    <button id="report-submission">신고</button>
                    <button type="button" id="report-cancel">취소</button>
                </div>
    
                 <!-- hidden -->
                 <%-- <input type="hidden" name="type" value="${param.type}">
                 <input type="hidden" name="cn" value="${param.cn}">--%>
                 <input type="hidden" name="no" id="boardNo">
            </form> 

            <form action="detail/image" method="POST" name ="detail-form" enctype = "multipart/form-data" class="community-head" onsubmit="return communityEdit()">
                
                <input type="hidden" name="cn" value="${param.cn}">

                <div class="head-left">

                    <c:if test="${empty comm.communityImage}">
                        <img src="${contextPath}/resources/images/community.jpg" id="community-image">
                    </c:if>

                    <c:if test="${!empty comm.communityImage}">
                        <img src="${contextPath}${comm.communityImage}" id="community-image">
                    </c:if>

                    <c:if test="${comm.communityAdmin == loginMember.memberNo}">
                        <label for="input-image">&nbsp;&nbsp;</label>
                        <input type="file" name="communityImage" id="input-image" accept="image/*">
                        <button type="submit" id="img-btn">+</button>
                    </c:if>
                </div>

                <div class="head-right">
                    <div class="head-title">
                        <div class="community-name">
                            <span>${comm.communityName}</span>
                            <c:if test="${comm.communityAdmin == loginMember.memberNo}">
                                <a href="admin?cn=${param.cn}"><span class="btn-edit">edit</span></a>
                            </c:if>
                        </div>
                     <div> <!-- <button type="button" class="btn-report" onclick="showReportPopup(${boardList.boardNo})">신고</button> --></div>
                </div>

                <div class="head-info">
                    <p>${comm.communityInfo}</p>
                </div>
            </form>

                    <div class="head-user">
                        <div>
                            <c:if test="${empty comm.profileImage}">
                                <img src="${contextPath}/resources/images/user.png" id="user">
                            </c:if>

                            <c:if test="${!empty comm.profileImage}">
                                <img src="${contextPath}${comm.profileImage}" id="user">
                            </c:if>
                        </div>

                        <div>${comm.memberNickname}</div>

                    </div>
            
            <div class="head-join">
                <div onclick="pick()">
                    <img src="${contextPath}/resources/images/pick1.png" id="pick">
                </div>

                <c:if test="${!empty comm.memberNo}">
                    <div class="btn-join" id="btn-join" onclick="join(${param.cn})">모임 가입하기</div>
                </c:if>

                <c:if test="${!empty commApplyList.memberNo}">
                    <div>모임 신청완료 승인 대기</div>
                </c:if>

            </div>

            <c:if test="${empty comm.memberNo}">
                <div id="btn-leave" onclick="secession(${param.cn})">모임 탈퇴하기</div>
            </c:if>
        </div>
    

            <div class="community-nav">
                <ul>
                    <li><a href="#board-detail">공지사항</a></li>
                    <span>|</span>
                    <li><a href="#board-signup">가입인사</a></li>
                    <span>|</span>
                    <li><a href="#board-community">자유게시판</a></li>
                    <span>|</span>
                    <li><a href="#board-review">정모후기</a></li>
                </ul>
            </div>

            <section class="community-content">

                <!-- 모임 상세정보(모임장 작성) -->
                <div class="board board-detail" id="board-detail">
                    <h3>공지사항
                      <c:if test="${comm.communityAdmin == loginMember.memberNo}">
                          <a href="#" id="detail-Popup">edit</a>
                      </c:if>
                      </h3>

                    <div class="board-detail-txt">
                        ${comm.communityNotice}
                    </div>

                </div>

                <!-- 가입인사 -->
                <div class="board board-signup" id="board-signup">
                    <h3>가입인사
                        <a href="${contextPath}/community/signup/list?cn=${param.cn}" target="_blank"><span class="board-all">전체 가입인사 보기 &gt;</span></a>
                    </h3>

                    <!-- 가입인사 연결 -->
                    <jsp:include page="/WEB-INF/views/community/commu-signup.jsp"/>

                </div>


                <!-- 자유게시판 -->
                <div class="board commu-list" id="board-list">
                    <h3>자유게시판
                        <a href="${contextPath}/community/board/list?cn=${param.cn}&type=1" target="_blank"><span class="board-all">전체 게시글 보기 &gt;</span></a>
                    </h3>
                    <!-- 자유게시판 연결 -->
                    <jsp:include page="/WEB-INF/views/community/commu-board.jsp"/>
                </div>



                <!-- 모임 후기 -->
                <div class="board board-review" id="board-review">
                    <h3>모임후기
                        <a href="${contextPath}/community/board/review?cn=${param.cn}&type=2" target="_blank"><span class="board-all">전체 후기 보기 &gt;</span></a>
                    </h3>

                    <!-- 모임게시판 연결 -->
                    <jsp:include page="/WEB-INF/views/community/commu-review.jsp"/>

                </div>

                <!-- 공지 수정 팝업 -->
                <div class="detail_popup_layer" id="detail_popup_layer" style="display: none;">
                    <div class="detail_popup_box">
                        <div style="height: 10px; width: 375px; float: top;">
                            <!-- <a href="closePop();" class="popUp-close" width="30px" height="30px"></a> -->
                        </div>

                        <div class="detail_popup_cont">
                            <h4>모임 상세정보 수정하기</h4>
                        </div>
                        <textarea type="text" id="detailUpdate" name="detailUpdate" placeholder="수정할 내용을 입력해주세요."></textarea>
                            <div class="detail_popup_button">
                                <a href="#" id="detail-cancelBtn">취소</a>
                                <a href="#" id="detail-updateBtn">수정</a>
                            </div>
                        </div>
                    </div>
            </section>
        </div>
    </main>

    <jsp:include page="/WEB-INF/views/common/footer.jsp"/>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>

    <script>
      const memberNo = "${loginMember.memberNo}";
      const communityNo = "${param.cn}";
    </script>

    <script src="${contextPath}/resources/js/community/member-community.js"></script>
    <script src="${contextPath}/resources/js/admin-page/report-form.js"></script>

</body>
</html>

