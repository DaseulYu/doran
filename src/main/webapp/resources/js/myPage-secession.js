const checkObj = {
    "currentPw" : false
};

    const currentPw = document.getElementById("currentPw");
    const agree = document.getElementById("agree");
    const currentPwMessage = document.getElementById("currentPwMessage")
    const regExp = /^[\w!@#_-]{6,30}$/;

currentPw.addEventListener("input",function(){
    
    if(currentPw.value.trim().length==0){
        currentPwMessage.innerHTML = "<strong>현재 비밀번호</strong>를 입력해주세요.";
        currentPwMessage.classList.add("error");
        currentPwMessage.classList.remove("confirm");
        checkObj.currentPw = false;
    }

    if(regExp.test(currentPw.value)){ // 현재 비밀번호 유효성 검사, 유효 O
        
        $.ajax({ // 현재 비밀번호 일치 여부 검사 (ajax)
            url : "currentPwConfirmCheck",
            data : {"currentPw" : currentPw.value, 
                    "loginMemberNo" : loginMemberNo},
            type : "POST",
            success : function(result){
                if(result==1){// 현재 비밀번호 일치
                    currentPwMessage.innerHTML = "<strong>현재 비밀번호</strong>가 일치합니다.";
                    currentPwMessage.classList.add("confirm");
                    currentPwMessage.classList.remove("error");
                    checkObj.currentPw = true;

                }else{ // 현재 비밀번호 일치하지 않음
                    currentPwMessage.innerHTML = "<strong>현재 비밀번호</strong>가 일치하지 않습니다.";
                    currentPwMessage.classList.add("error");
                    currentPwMessage.classList.remove("confirm");
                    checkObj.currentPw = false;
                }
            },error(result, req){
                console.log("에러 발생");
                console.log(req.responseText);
            }
        });
    }else{
        currentPwMessage.innerHTML = "<strong>영어, 숫자, 특수문자(!,@,#,-,_) 6~30글자</strong> 사이로 작성해주세요.";
        currentPwMessage.classList.add("error");
        currentPwMessage.classList.remove("confirm");
        checkObj.currentPw = false;
    }
});


//회원탈퇴 유효성 검사
function secessionValidate(){
    if(!checkObj.currentPw){
        return false;
    }
    if(!agree.checked){       
        alert("약관 동의 후 탈퇴 버튼을 클릭해주세요.");
        agree.focus();
        return false;
    }else{
        
    const secessionConfirm = confirm("정말로 탈퇴 하시겠습니까?");
    
        if(!secessionConfirm){
            return false;
        }
    }
    return true;
}