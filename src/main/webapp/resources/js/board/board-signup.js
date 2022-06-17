// board-signup.jsp 연결


// 가입인사 조회
function selectSignupList(){

    $.ajax({
        url : contextPath + "/community/board/signup/selectSignupList",
        data : {"cn" : communityNo},
        type : "GET",
        dataType : "JSON", 
        success : function(sList){
            console.log(sList);

            const signupList = document.getElementById("signup-list"); 
            signupList.innerHTML = "";

            for(let signup of sList){

                const signupRow = document.createElement("li");
                signupRow.classList.add("signup-row");

                // 작성자 정보
                const memberInfo = document.createElement("div");
                memberInfo.classList.add("user");

                const profileImage = document.createElement("img");
                profileImage.id = "user";

                const memberNickname = document.createElement("p");
                memberNickname.innerText=signup.memberNickname;

                if(signup.profileImage != null){ // 프로필 이미지가 있을 경우
                    profileImage.setAttribute("src", contextPath + signup.profileImage);
                }else{ // 없을 경우
                    profileImage.setAttribute("src", contextPath + "/resources/images/user.png");
                }
                memberInfo.append(profileImage, memberNickname);


                // 가입인사 내용
                const signupTxtArea = document.createElement("div");
                signupTxtArea.classList.add("message");

                const signupTxt = document.createElement("span");
                signupTxt.classList.add("message-txt");
                signupTxt.innerHTML=signup.signupContent;

                const signupTxtDate = document.createElement("span");
                signupTxtDate.classList.add("message-date");
                signupTxtDate.innerHTML=signup.createDate;

                signupTxtArea.append(signupTxt, signupTxtDate);

                signupRow.append(memberInfo, signupTxtArea);

                // 버튼 영역
                const signupBtnArea = document.createElement("div");
                signupBtnArea.classList.add("write-btn-area");

                // 댓글 작성자가 아닐때 신고 버튼
                // if( loginMemberNo != signup.memberNo ){
                //     const reportBtn = document.createElement("button");
                //     reportBtn.id = "btn-report";
                //     reportBtn.innerText = "신고";

                //     signupBtnArea.append(reportBtn);
                // }
                    

                // 댓글 작성자 일때 수정/삭제 버튼
                if( loginMemberNo == signup.memberNo ){
                    const updateBtn = document.createElement("button");
                    updateBtn.id = "updateBtn";
                    updateBtn.innerText = "수정";
                    updateBtn.setAttribute("onclick", "showUpdateReply("+signup.signupNo+", this)");

                    const deleteBtn = document.createElement("button");
                    deleteBtn.id = "deleteBtn";
                    deleteBtn.innerText = "삭제";
                    deleteBtn.setAttribute("onclick", "deleteReply("+signup.signupNo+")");
                    
                    signupBtnArea.append(updateBtn, deleteBtn);
                }

                signupRow.append(signupBtnArea);
                signupList.append(signupRow);
            }
        },
        error : function(req, status, error){
            console.log("에러발생");
            console.log(req.responseText);
        }
    });
}


// 가입인사 등록
const signupBtn = document.getElementById("btn-reply");
const signupContent = document.getElementById("msgContent");
signupBtn.addEventListener("click", function(){

    if(signupContent.value.trim().length == 0){ 
        alert("내용을 작성한 후 버튼을 클릭해주세요.");
        signupContent.value = ""; 
        signupContent.focus();
        return;
    }

    $.ajax({
        url : contextPath + "/community/board/signup/insert",
        data : {"cn" : communityNo,
                "memberNo" : loginMemberNo, 
                "signupContent" : signupContent.value},
        type : "post",
        success : function(result){
            if(result > 0){
                alert("가입인사가 등록되었습니다.");
                signupContent.value="";
                selectSignupList(); 
            } else{
                alert("가입인사 등록 실패");
            }
        },
        error : function(req, status, error){
            console.log("등록 에러")
            console.log(req.responseText);
        }
    });
});


// 가입인사 삭제
function deleteReply(signupNo){
    if(confirm("작성한 글을 삭제 하시겠습니까?")) {
        $.ajax({
            url : contextPath + "/community/board/signup/delete",
            data : {"signupNo" : signupNo},
            type : "GET",
            success : function(result){
                if(result > 0){
                    alert("글이 삭제되었습니다.");
                    selectSignupList();
                } else  {
                    alert("삭제 실패");
                }
            },
            error : function(req, status, error){
                console.log("삭제 에러");
                console.log(req.responseText);
            }
        });
    }
}


// 가입인사 수정 화면
let beforeSignupRow;
function showUpdateReply(signupNo, btn){

    const updateArea = document.getElementsByClassName("signup-update-area");

    if(updateArea.length > 0){
        if(confirm("다른 글이 수정 중입니다. 현재 글을 수정하시겠습니까?")){
            updateArea[0].parentElement.innerHTML = beforeSignupRow;
        } else {
            return;
        }
    }

    const signupRow = btn.parentElement.parentElement;

    // 댓글 내용 백업
    beforeSignupRow = signupRow.innerHTML;
    let beforeContent = signupRow.children[1].children[0].innerHTML;

    // 수정 눌렀을 때 내용 삭제
    signupRow.innerHTML = "";

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
    const signupUpdateArea = document.createElement("div");
    signupUpdateArea.classList.add("signup-update-area");
    
    // 나머지 버튼 영역
    const signupBtnArea = document.createElement("div");
    signupBtnArea.classList.add("signup-btn-area");

    const updateBtn = document.createElement("button");
    updateBtn.classList.add("btn-update");
    updateBtn.innerText = "수정";
    updateBtn.setAttribute("onclick",  "updateSignup("+signupNo+", this)");

    const cancleBtn = document.createElement("button");
    cancleBtn.classList.add("btn-cancle");
    cancleBtn.innerText = "취소";
    cancleBtn.setAttribute("onclick", "updateCancel(this)");
    
    signupUpdateArea.append(textarea, updateBtn, cancleBtn);
        
    signupRow.append(signupUpdateArea);
}

// 가입인사 수정 취소
function updateCancel(btn){
    if(confirm("댓글 수정을 취소하시겠습니까?")){
        btn.parentElement.parentElement.innerHTML = beforeSignupRow;
    }
}

// 가입인사 수정
function updateSignup(signupNo, btn){
    const signupContent = btn.previousElementSibling.value;

    $.ajax({
        url : contextPath + "/community/board/signup/update",
        data : {"signupNo" : signupNo,
                "signupContent" : signupContent},
        type : "post",
        success : function(result){
            if(result > 0){
                alert ("댓글이 수정되었습니다.");
                selectSignupList();
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