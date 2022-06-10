console.log("login.js loaded.");

function loginValidate(){

    const inputEmail = document.getElementById("inputEmail");

    const inputPw = document.getElementById("inputPw");

    if(inputEmail.value.trim().length==0){
        alert("아이디(이메일)을 입력해주세요.");
        inputEmail.value="";
        inputEmail.focus();

        return false;
    }

    if(inputPw.value.trim().length==0){
        alert("비밀번호를 입력해주세요.");
        inputPw.value="";
        inputPw.focus();
        return false;
    }

    return true;

}

document.getElementById("saveId").addEventListener("change", function(){


    console.log(this.checked)
    

    if( this.checked ){ 

        const str = "개인 정보 보호를 위해 개인 PC에서의 사용을 권장합니다. 개인 PC가 아닌 경우 취소를 눌러주세요.";

        //confirm(str) // 확인(true) / 취소(false) 대화상자

        if( !confirm(str)  ){ // 취소를 눌렀을 때
            this.checked = false; // 체크 해제
        }
    }


});