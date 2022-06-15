
// 비밀번호 변경시 유효성 검사
const checkObj2 = {
    "currentPw" : false,
    "newPw" : false,
    "newPwConfirm" : false 
}; 

    const currentPw = document.getElementById("currentPw");
    const newPw = document.getElementById("newPw");
    const newPwConfirm = document.getElementById("newPwConfirm");

    const currentPwMessage = document.getElementById("currentPwMessage");
    const newPwMessage = document.getElementById("newPwMessage");
    const newPwConfirmMessage = document.getElementById("newPwConfirmMessage");

    const regExp = /^[\w!@#_-]{6,30}$/;
currentPw.addEventListener("input",function(){
    
    if(currentPw.value.trim().length==0){
        currentPwMessage.innerHTML = "<strong>현재 비밀번호</strong>를 입력해주세요.";
        currentPwMessage.classList.add("error");
        currentPwMessage.classList.remove("confirm");
        checkObj2.currentPw = false;
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
                    checkObj2.currentPw = true;

                }else{ // 현재 비밀번호 일치하지 않음
                    currentPwMessage.innerHTML = "<strong>현재 비밀번호</strong>가 일치하지 않습니다.";
                    currentPwMessage.classList.add("error");
                    currentPwMessage.classList.remove("confirm");
                    checkObj2.currentPw = false;
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
        checkObj2.currentPw = false;
    }
});

newPw.addEventListener("input",function(){
    if(newPw.value.length==0){
        newPwMessage.innerHTML = "<strong>새 비밀번호</strong>를 입력해주세요.";
        newPwMessage.classList.add("error");
        newPwMessage.classList.remove("confirm");
        checkObj2.newPw = false;
    }

    if(regExp.test(newPw.value)){ // 비밀번호 유효
        
        newPwMessage.innerHTML = "<strong>비밀번호 형식</strong>이 유효합니다.";
        newPwMessage.classList.add("confirm");
        newPwMessage.classList.remove("error");
        checkObj2.newPw = true;

        if(currentPw.value == newPw.value){
            newPwMessage.innerHTML = "<strong>새 비밀번호</strong>는 <strong>현재 비밀번호</strong>와 같게 설정 할 수 없습니다.";
            newPwMessage.classList.add("error");
            newPwMessage.classList.remove("confirm");
            checkObj2.newPw = false;
        }
        
        if(newPwConfirm.value == 0){
            newPwConfirmMessage.innerHTML="<strong>새 비밀번호 확인</strong>을 입력해주세요.";
            newPwConfirmMessage.classList.add("error");
            newPwConfirmMessage.classList.remove("confirm");
            checkObj2.newPwConfirm = false;
        }else{
            checkPw();
        }
    }else{
        newPwMessage.innerHTML = "<strong>영어, 숫자, 특수문자(!,@,#,-,_) 6~30글자</strong> 사이로 작성해주세요.";
        newPwMessage.classList.remove("confirm");
        newPwMessage.classList.add("error");
        checkObj2.newPw = false;
    }

});

newPwConfirm.addEventListener("input",checkPw);

function checkPw(){
    if(newPw.value != newPwConfirm.value){ // 새 비밀번호와 비밀번호가 같지 않을 경우
        newPwConfirmMessage.innerHTML = "<strong>새 비밀번호</strong>가 서로 일치하지 않습니다.";
        newPwConfirmMessage.classList.add("error");
        newPwConfirmMessage.classList.remove("confirm");
        checkObj2.newPwConfirm = false;
    }else{ 
        newPwConfirmMessage.value="";
        newPwConfirmMessage.innerHTML = "<strong>새 비밀번호</strong>가 서로 일치합니다.";
        newPwConfirmMessage.classList.add("confirm");
        newPwConfirmMessage.classList.remove("error");
        checkObj2.newPwConfirm = true;
    }
}


function changePwValidate(){

    let str;

    for(let key in checkObj2){
        if(!checkObj2[key]){
            switch(key){
                case "currentPw": str = "현재 비밀번호가"; break;
                case "newPw" : str = "새 비밀번호가";break;
                case "newPwConfirm" : str="새 비밀번호 확인이"; break;
            }
                str += " 유효하지 않습니다.";
                alert(str);
                document.getElementById(key).focus();
                return false;
            }
        }
        return true;
    }


