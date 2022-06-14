// board-signup.jsp 연결


// 가입인사 조회
function selectSignupList(){

    $.ajax({
        url : contextPath + "/community/board/signup/selectReplyList",
        data : {"cn" : communityNo},
        type : "GET",
        dataType : "JSON", 
        success : function(sList){
            console.log(sList);

        },
        error : function(req, status, error){
            console.log("에러발생");
            console.log(req.responseText);
        }
    });
}