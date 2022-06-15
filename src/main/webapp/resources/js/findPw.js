

const checkObj = {
    "sendEmail": false,
    "checkNumber": false,
    "inputName": false,
    "inputEmail": false,
    "newPw" : false,
    "newPwConfirm" : false 
};

const inputName = document.getElementById("inputName");
const inputEmail = document.getElementById("inputEmail");

const emailMessage = document.getElementById("emailMessage");
const nameMessage = document.getElementById("nameMessage");

const newPw = document.getElementById("newPw");
const newPwConfirm = document.getElementById("newPwConfirm");

const newPwMessage = document.getElementById("newPwMessage");
const newPwConfirmMessage = document.getElementById("newPwConfirmMessage");

const regExp = /^[\w!@#_-]{6,30}$/;

const regExp1 = /^[가-힣]{2,10}$/;
const regExp2 = /^[\w\-\_]{4,}@[\w\-\_]+(\.\w+){1,3}$/;

inputName.addEventListener("input", function () {
    if (inputName.value.length == 0) {
        nameMessage.innerHTML = "<strong>이름</strong>을 입력해주세요.";
        nameMessage.classList.add("error");
        nameMessage.classList.remove("confirm");
        checkObj.inputName = false;
        return;
    }

    if (!regExp1.test(inputName.value)) {
        nameMessage.innerHTML = "유효하지 않는 <strong>이름</strong> 형식입니다.";
        nameMessage.classList.add("error");
        nameMessage.classList.remove("confirm");
        checkObj.inputName = false;
    } else {
        nameMessage.innerHTML = "유효한 <strong>이름</strong> 형식입니다.";
        nameMessage.classList.add("confirm");
        nameMessage.classList.remove("error");
        checkObj.inputName = true;
    }
    checkObj.inputName = true;
});


inputEmail.addEventListener("input", function () {

    if (inputEmail.value.length == 0) {
        emailMessage.innerHTML = "회원 가입시 입력한 <strong>이메일</strong>을 입력해주세요.";
        emailMessage.classList.add("error");
        emailMessage.classList.remove("confirm");
        checkObj.inputEmail = false;
        return;
    }
    if (regExp2.test(inputEmail.value)) { // 이메일이 유효한 경우
        $.ajax({
            url: "emailDupCheck",
            data: { "inputEmail": inputEmail.value },
            type: "GET",

            success: function (result) {
                if (result == 1) {
                    emailMessage.innerHTML = "<strong>이메일</strong>이 확인되었습니다."
                    emailMessage.classList.add("confirm");
                    emailMessage.classList.remove("error");
                    checkObj.inputEmail = true;
                }else{
                    emailMessage.innerHTML = "존재하지 않는 <strong>이메일</strong>입니다.";
                    emailMessage.classList.add("error");
                    emailMessage.classList.remove("confirm");
                    checkObj.inputEmail = false;
                }
            }, error: function (result, req) {
                console("에러 발생");
                console.log(req.responseText);
            }
        });
    } else {
        emailMessage.innerHTML = "유효하지 않는 <strong>이메일</strong> 형식입니다.";
        emailMessage.classList.add("error");
        emailMessage.classList.remove("confirm");
        checkObj.inputEmail = false;
    }
});


// 인증번호 보내기
const sendBtn = document.getElementById("sendBtn");
const cMessage = document.getElementById("cMessage");

// 타이머에 사용될 변수
let checkInterval; // setInterval을 저장할 변수
let min = 4;
let sec = 59;

sendBtn.addEventListener("click", function () {

    if (checkObj.inputEmail) { // 유효한 이메일이 작성되어 있을 경우에만 메일 보내기

        $.ajax({
            url: "sendEmail",
            data: { "inputEmail": inputEmail.value },
            type: "GET",
            success: function (result) {
                console.log("이메일 발송 성공");
                console.log(result);

                // 인증 버튼이 클릭되어 정상적으로 메일이 보내졌음을 checkObj에 기록
                checkObj.sendEmail = true;

            },
            error: function () {
                console.log("이메일 발송 실패")
            }
        });


        // Mail 발송 Ajax 코드는 동작이 느림....
        // -> 메일은 메일대로 보내고, 타이머는 버튼이 클릭 되자 마자 바로 실행
        // --> ajax 코드가 비동기이기 때문에 메일이 보내지는 것을 기다리지 않고
        //      바로 다음 코드가 수행된다!!

        // 5분 타이머
        // setInerval(함수, 지연시간) : 지연시간이 지난 후 함수를 수행 (반복)

        cMessage.innerText = "5:00"; // 초기값 5분
        min = 4;
        sec = 59; // 분, 초 초기화

        cMessage.classList.remove("confirm");
        cMessage.classList.remove("error");

        // 변수에 저장해야지 멈출 수 있음
        checkInterval = setInterval(function () {
            if (sec < 10) sec = "0" + sec;
            cMessage.innerText = min + ":" + sec;

            if (Number(sec) === 0) {
                min--;
                sec = 59;
            } else {
                sec--;
            }

            if (min === -1) { // 만료
                cMessage.classList.add("error");
                cMessage.innerText = "인증번호가 만료되었습니다.";

                clearInterval(checkInterval); // checkInterval 반복을 지움
            }

        }, 1000); // 1초 지연 후 수행


        alert("인증번호가 발송되었습니다. 이메일을 확인해주세요.");
    }
});


// 인증번호 확인 클릭시에 대한 동작
const cNumber = document.getElementById("cNumber");
const cBtn = document.getElementById("cBtn");
// + cMessage, memberEmail 요소도 사용

cBtn.addEventListener("click", function () {

    // 1. 인증번호 받기 버튼이 클릭되어 이메일 발송되었는지 확인
    if (checkObj.sendEmail) {

        // 2. 입력된 인증번호가 6자리가 맞는지 확인
        if (cNumber.value.length == 6) { // 6자리 맞음

            $.ajax({
                url: "checkNumber",
                data: {
                    "cNumber": cNumber.value,
                    "inputEmail": inputEmail.value
                },
                type: "GET",
                success: function (result) {
                    console.log(result);
                    // 1 : 인증번호 일치 O, 시간 만족O
                    // 2 : 인증번호 일치 O, 시간 만족X
                    // 3 : 인증번호 일치 X

                    if (result == 1) {

                        clearInterval(checkInterval); // 타이머 멈춤     

                        cMessage.innerText = "인증되었습니다.";
                        cMessage.classList.add("confirm");
                        cMessage.classList.remove("error");
                        checkObj.checkNumber = true;

                    } else if (result == 2) {
                        alert("만료된 인증 번호 입니다.");

                    } else { // 3
                        alert("인증 번호가 일치하기 않습니다.");
                    }


                },

                error: function () {
                    console.log("이메일 인증 실패")
                }
            });



        } else { // 6자리 아님
            alert("인증번호를 정확하게 입력해주세요.");
            cNumber.focus();
        }

    } else { // 인증번호를 안받은 경우
        alert("인증번호 받기 버튼을 먼저 클릭해주세요.");
    }

});


newPw.addEventListener("input",function(){
    if(newPw.value.length==0){
        newPwMessage.innerHTML = "<strong>새 비밀번호</strong>를 입력해주세요.";
        newPwMessage.classList.add("error");
        newPwMessage.classList.remove("confirm");
        checkObj.newPw = false;
    }

    if(regExp.test(newPw.value)){ // 비밀번호 유효
        
        newPwMessage.innerHTML = "<strong>비밀번호 형식</strong>이 유효합니다.";
        newPwMessage.classList.add("confirm");
        newPwMessage.classList.remove("error");
        checkObj.newPw = true;
        
        if(newPwConfirm.value == 0){
            newPwConfirmMessage.innerHTML="<strong>새 비밀번호 확인</strong>을 입력해주세요.";
            newPwConfirmMessage.classList.add("error");
            newPwConfirmMessage.classList.remove("confirm");
            checkObj.newPwConfirm = false;
        }else{
            checkPw();
        }
    }else{
        newPwMessage.innerHTML = "<strong>영어, 숫자, 특수문자(!,@,#,-,_) 6~30글자</strong> 사이로 작성해주세요.";
        newPwMessage.classList.remove("confirm");
        newPwMessage.classList.add("error");
        checkObj.newPw = false;
    }

});

newPwConfirm.addEventListener("input",checkPw);

function checkPw(){
    if(newPw.value != newPwConfirm.value){ // 새 비밀번호와 비밀번호가 같지 않을 경우
        newPwConfirmMessage.innerHTML = "<strong>새 비밀번호</strong>가 서로 일치하지 않습니다.";
        newPwConfirmMessage.classList.add("error");
        newPwConfirmMessage.classList.remove("confirm");
        checkObj.newPwConfirm = false;
    }else{ 
        newPwConfirmMessage.value="";
        newPwConfirmMessage.innerHTML = "<strong>새 비밀번호</strong>가 서로 일치합니다.";
        newPwConfirmMessage.classList.add("confirm");
        newPwConfirmMessage.classList.remove("error");
        checkObj.newPwConfirm = true;
    }
}

function findPwValidate() {

    let str;
    for (let key in checkObj) {
        if (!checkObj[key]) {
            switch (key) {
                case "sendEmail": str = "인증번호 받기를 클릭해주세요."; break;
                case "checkNumber": str = "인증번호 확인을 진행해주세요."; break;
                case "newPw" : str = "새 비밀번호가 유효하지 않습니다."; break;
                case "newPwConfirm" : str = "새 비밀번호 확인이 유효하지 않습니다."; break;
            }
            alert(str);
            return false;
        }
    }
    return true;
}