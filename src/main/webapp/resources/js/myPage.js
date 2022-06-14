
const checkObj = {
    "memberNickname": false,
    "memberPhone": false

};


const memberNickname = document.getElementById("memberNickname");
const nicknameMessage = document.getElementById("nicknameMessage");


memberNickname.addEventListener("input", function () {

    const regExp1 = /^[a-zA-Z0-9가-힣]{2,10}$/; // 닉네임 정규표현식

    if (memberNickname.value.length == 0) {
        nicknameMessage.innerHTML = "<strong>닉네임</strong>을 입력해주세요.";
        nicknameMessage.classList.remove("confirm");
        nicknameMessage.classList.add("error");
        checkObj.memberNickname = false;
        return;
    }
    if (regExp1.test(memberNickname.value)) { // 닉네임이 유효한 경우

        $.ajax({
            url: "nicknameDupCheck",
            data: { "memberNickname": memberNickname.value },
            type: "GET",
            success: function (result) {
                if (result == 1) {
                    nicknameMessage.innerHTML = "<strong>이미 사용중인 닉네임</strong>입니다.";
                    nicknameMessage.classList.add("error");
                    nicknameMessage.classList.remove("confirm");
                    checkObj.memberNickname = false;
                } else {
                    nicknameMessage.innerHTML = "<strong>사용 가능한 닉네임</strong>입니다.";
                    nicknameMessage.classList.add("confirm");
                    nicknameMessage.classList.remove("error");
                    checkObj.memberNickname = true;

                }
            },
            error: function (result, req) {
                console.log("에러 발생");
                console.log(req.responseText);
            }
        });

    } else {
        nicknameMessage.innerHTML = "<strong>닉네임 형식</strong>이 유효하지 않습니다.<br> <strong>영어/숫자/한글 2~10글자 사이</strong>로 작성해주세요.";
        nicknameMessage.classList.add("error");
        nicknameMessage.classList.remove("confirm");
        checkObj.memberNickname = false;
    }
});


const memberPhone = document.getElementById("memberPhone");
const phoneMessage = document.getElementById("phoneMessage");

memberPhone.addEventListener("input", function() {

    const regExp2 = /^0(1[01679])\d{3,4}\d{4}$/; // 휴대폰번호 정규표현식

    if(memberPhone.value.length == 0){
        phoneMessage.innerHTML = "<strong>휴대폰 번호</strong>를 입력해주세요.";
        phoneMessage.classList.add("error");
        phoneMessage.classList.remove("confirm");
        checkObj.memberPhone = false;
        return;
    }
    if(regExp2.test(memberPhone.value)){

        $.ajax({
            url : "phoneDupCheck",
            data : {"memberPhone" : memberPhone.value},
            type : "GET",
            success : function(result){
                if(result==1){
                    phoneMessage.innerHTML = "<strong>이미 사용 중인 휴대폰 번호</strong>입니다."
                    phoneMessage.classList.add("error");
                    phoneMessage.classList.remove("confirm");
                    checkObj.memberPhone = false;
                } else{
                    phoneMessage.innerHTML = "<strong>사용 가능한 휴대폰 번호</strong>입니다."
                    phoneMessage.classList.add("confirm");
                    phoneMessage.classList.remove("error");
                    checkObj.memberPhone = true;
                }
            }, error : function(result, req){
                console.log(req.responseText);
            }
        });
    }else{
        
        phoneMessage.innerHTML = "<strong>휴대폰 번호 형식</strong>이 유효하지 않습니다."
        phoneMessage.classList.add("error");
        phoneMessage.classList.remove("confirm");
        checkObj.memberPhone = false;
    }

});

function myInfoUpdateValidate(){

    let str;

    for(let key in checkObj){

        if(!checkObj[key]){
            switch(key){
                case "memberNickname" : str="닉네임이"; break;
                case "memberPhone" : str="휴대폰 번호가"; break;

            }
            str+= " 유효하지 않습니다.";
            alert(str);
            document.getElementById(key).focus();
            return false;
        }
    }
    return true;
}