// board-reply.jsp 연결


// 댓글 조회
function selectReplyList(){

    $.ajax({
        url : contextPath + "/community/board/reply/selectReplyList",
        data : {"no" : boardNo},
        type : "GET",
        dataType : "JSON", 
        success : function(rList){
            console.log(rList);

            const replyList = document.getElementById("reply-list"); 
            replyList.innerHTML = "";

            for(let reply of rList){

                const replyRow = document.createElement("li");
                replyRow.classList.add("reply-row");

                // 작성자 정보
                const replyNick = document.createElement("div");
                replyNick.classList.add("reply-nick");

                const profileImage = document.createElement("img");

                const memberNickname = document.createElement("p");
                memberNickname.innerText=reply.memberNickname;

                if( reply.profileImage != null ){ // 프로필 이미지가 있을 경우
                    profileImage.setAttribute("src", contextPath + reply.profileImage);
                }else{ // 없을 경우
                    profileImage.setAttribute("src", contextPath + "/resources/images/user.png");
                }
                replyNick.append(profileImage, memberNickname);


                // 댓글 내용
                const replyTxtArea = document.createElement("div");
                replyTxtArea.classList.add("reply-txt-area");

                const replyTxt = document.createElement("span");
                replyTxt.classList.add("reply-txt");
                replyTxt.innerHTML=reply.replyContent;

                const replyTxtDate = document.createElement("span");
                replyTxtDate.classList.add("reply-txt-date");
                replyTxtDate.innerHTML=reply.createDate;

                replyTxtArea.append(replyTxt, replyTxtDate);
                
                replyRow.append(replyNick, replyTxtArea);
                

                // 버튼 영역
                const replyBtnArea = document.createElement("div");
                replyBtnArea.classList.add("reply-btn-area");
                
                // 댓글 작성자가 아닐때 신고 버튼
                // if( loginMemberNo != reply.memberNo ){
                //     const reportBtn = document.createElement("button");
                //     reportBtn.id = "btn-report";
                //     reportBtn.innerText = "신고";

                //     replyBtnArea.append(reportBtn);
                // }
                    

                // 댓글 작성자 일때 수정/삭제 버튼
                if( loginMemberNo == reply.memberNo ){
                    const updateBtn = document.createElement("button");
                    updateBtn.id = "updateBtn";
                    updateBtn.innerText = "수정";
                    updateBtn.setAttribute("onclick", "showUpdateReply("+reply.replyNo+", this)");

                    const deleteBtn = document.createElement("button");
                    deleteBtn.id = "deleteBtn";
                    deleteBtn.innerText = "삭제";
                    deleteBtn.setAttribute("onclick", "deleteReply("+reply.replyNo+")");
                    
                    replyBtnArea.append(updateBtn, deleteBtn);
                }
                    
                replyRow.append(replyBtnArea);

                replyList.append(replyRow);
            }
        },
        error : function(req, status, error){
            console.log("에러발생");
            console.log(req.responseText);
        }
    });
}



// 댓글 등록
const replyBtn = document.getElementById("btn-reply");
const replyContent = document.getElementById("msgContent");
replyBtn.addEventListener("click", function(){

    if(loginMemberNo == ""){ 
        alert("로그인 후 이용해주세요.");
        return;
    }

    if(replyContent.value.trim().length == 0){ 
        alert("댓글을 작성한 후 버튼을 클릭해주세요.");
        replyContent.value = ""; 
        replyContent.focus();
        return;
    }

    $.ajax({
        url : contextPath + "/community/board/reply/insert",
        data : {"no" : boardNo,
                "memberNo" : 0,
                "memberNo" : loginMemberNo,
                "replyContent" : replyContent.value},
        type : "post",
        success : function(result){
            if(result > 0){
                alert("댓글이 등록되었습니다.");
                replyContent.value="";
                selectReplyList(); 
            } else{
                alert("댓글 등록 실패");
            }
        },
        error : function(req, status, error){
            console.log("댓글 등록 실패")
            console.log(req.responseText);
        }
    });
});


// 댓글 삭제
function deleteReply(replyNo){
    if(confirm("댓글을 삭제 하시겠습니까?")) {
        $.ajax({
            url : contextPath + "/community/board/reply/delete",
            data : {"replyNo" : replyNo},
            type : "GET",
            success : function(result){
                if(result>0){
                    alert("댓글이 삭제되었습니다.");
                    selectReplyList();
                } else  {
                    alert("삭제 실패");
                }
            },
            error : function(req, status, error){
                console.log("댓글 삭제 실패");
                console.log(req.responseText);
            }
        });
    }
}




// 댓글 수정 화면 전환
let beforeReplyRow; // 수정 전 행
function showUpdateReply(replyNo, btn){

    const updateArea = document.getElementsByClassName("reply-update-area");

    if(updateArea.length > 0){
        if(confirm("다른 댓글이 수정 중입니다. 현재 댓글을 수정하시겠습니까?")){
            updateArea[0].parentElement.innerHTML = beforeReplyRow;
        } else {
            return;
        }
    }

    const replyRow = btn.parentElement.parentElement;

    // 댓글 내용 백업
    beforeReplyRow = replyRow.innerHTML;
    let beforeContent = replyRow.children[1].children[0].innerHTML;

    // 수정 눌렀을 때 내용 삭제
    replyRow.innerHTML = "";

    const textarea = document.createElement("textarea");
    textarea.classList.add("updateContent");

    // XSS 방지 처리 해제
    beforeContent = beforeContent.replaceAll("&amp;", "&");
    beforeContent = beforeContent.replaceAll("&lt;", "<");
    beforeContent = beforeContent.replaceAll("&gt;", ">");
    beforeContent = beforeContent.replaceAll("&quot;", "\"");
    // 개행문자 처리 해제
    beforeContent = beforeContent.replaceAll("<br>", "\n");

    textarea.value = beforeContent;

    // 댓글 내용
    const replyUpdateArea = document.createElement("div");
    replyUpdateArea.classList.add("reply-update-area");
    
    // 나머지 버튼 영역
    const replyBtnArea = document.createElement("div");
    replyBtnArea.classList.add("reply-btn-area");

    const updateBtn = document.createElement("button");
    updateBtn.classList.add("btn-update");
    updateBtn.innerText = "수정";
    updateBtn.setAttribute("onclick",  "updateReply("+replyNo+", this)");

    const cancleBtn = document.createElement("button");
    cancleBtn.classList.add("btn-cancle");
    cancleBtn.innerText = "취소";
    cancleBtn.setAttribute("onclick", "updateCancel(this)");
    
    replyUpdateArea.append(textarea, updateBtn, cancleBtn);
        
    replyRow.append(replyUpdateArea);
}

// 댓글 수정 취소
function updateCancel(btn){
    if(confirm("댓글 수정을 취소하시겠습니까?")){
        btn.parentElement.parentElement.innerHTML = beforeReplyRow;
    }
}

// 댓글 수정
function updateReply(replyNo, btn){
    const replyContent = btn.previousElementSibling.value;

    $.ajax({
        url : contextPath + "/community/board/reply/update",
        data : {"replyNo" : replyNo,
                "replyContent" : replyContent},
        type : "post",
        success : function(result){
            if(result > 0){
                alert ("댓글이 수정되었습니다.");
                selectReplyList();
            }else{
                alert ("댓글 수정 실패");
            }
        },
        error : function(req, status, error){
            console.log("댓글 수정 실패");
            console.log(req.responseText);
        }
    });
}
