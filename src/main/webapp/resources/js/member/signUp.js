// 유효성 검사 여부
const checkObj = {
    "memberId": false,
    "memberPw": false,
    "memberName": false,
    "memberTel": false,
    "memberNickname": false,
    "memberPwConfirm" : false
};




// 이메일
const memberId = document.getElementById("memberId");
const emailMessage = document.querySelector("#emailMessage");

memberId.addEventListener("input", function () {

    if (memberId.value.length == 0) {
        emailMessage.innerText = "이메일을 입력해주세요.";

        checkObj.memberId = false;
        return;
    }

    const regExp = /^[\w\-\_]{4,}@[\w\-\_]+(\.\w+){1,3}$/;

    if (!regExp.test(memberId.value)) {
        emailMessage.innerText = "이메일 형식이 올바르지 않습니다.";
        emailMessage.style.color = "red";

        memberId.style.border = "1px solid red";

        checkObj.memberId = false;
        return;
    } else {
        emailMessage.innerText = "사용 가능한 이메일 입니다.";
        emailMessage.style = "";

        memberId.style = "";
        checkObj.memberId = true;

        $.ajax({
            url: "idDupCheck",
            data: { "memberId": memberId.value },
            type: "GET",
            success: function (result) {
                if (result == 1) { // 중복 O
                    emailMessage.innerText = "이미 사용중인 이메일 입니다.";
                    console.log("result : " + result);
                    checkObj.memberId = false; // 유효 X 기록
                } else { // 중복 X
                    emailMessage.innerText = "사용 가능한 이메일 입니다.";

                    console.log("result : " + result);
                    checkObj.memberId = true; // 유효 O 기록
                }
            },

            error: function () {
                console.log("에러 발생");

            }
        });
    }

});


// 비밀번호
const memberPw = document.getElementById("memberPw");
const pwMessage = document.getElementById("pwMessage");
const memberPwConfirm = document.getElementById("memberPwConfirm");
const cPwMessage = document.getElementById("confirmPwMessage");

memberPw.addEventListener("input", function () {

    if (memberPw.value.length == 0) {
        pwMessage.innerText = "영문, 숫자, 특수문자 조합으로 8자 이상 20글자 이하";

        pwMessage.style.color = "red";
        memberPw.style.border = "1px solid red";
        checkObj.memberPw = false;
        // return;
    }

    const regExp = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[~!@#$%^&*()+|=])[A-Za-z\d~!@#$%^&*()+|=]{8,20}$/;

    if (regExp.test(memberPw.value)) {

        checkObj.memberPw = true;

        pwMessage.innerText = "";
        pwMessage.style.color = "";
        memberPw.style.border = "";

        if (memberPwConfirm.value.length == 0) {
            pwMessage.innerText = "유효한 비밀번호 형식입니다.";

        } else {
            checkPw();
        }
    } else {
        pwMessage.innerText = "비밀번호 형식이 유효하지 않습니다.";

        pwMessage.style.color = "red";
        memberPw.style.border = "1px solid red";
        checkObj.memberPw = false;
        // return;
    }
});


memberPwConfirm.addEventListener("input", checkPw);

function checkPw() {
    if (memberPw.value == memberPwConfirm.value) {
        cPwMessage.innerText = "비밀번호가 일치합니다.";
        cPwMessage.innerText = "";
        cPwMessage.style.color = "";
        memberPwConfirm.style.border = "";
        checkObj.memberPwConfirm = true;

    } else {
        cPwMessage.innerText = "비밀번호가 일치하지 않습니다.";
        cPwMessage.style.color = "red";
        memberPwConfirm.style.border = "1px solid red";
        checkObj.memberPwConfirm = false;
        // return;
    }
}

// 이름
const memberName = document.getElementById("memberName");
const nameMessage = document.getElementById("nameMessage");

memberName.addEventListener("input", function () {

    if (memberName.value.length == 0) {
        nameMessage.innerText = "닉네임을 입력해주세요.";

        nameMessage.style.color = "red";
        memberName.style.border = "1px solid red";
        checkObj.memberName = false;
        // return;
    }

    const regExp = /^[가-힣A-Za-z]{2,}$/;

    if (regExp.test(memberName.value)) {

        checkObj.memberName = true;

        nameMessage.innerText = "";
        nameMessage.style.color = "";
        memberName.style.border = "";

        
    } else {
        nameMessage.innerText = "이름 형식을 확인해주세요.";

        nameMessage.style.color = "red";
        memberName.style.border = "1px solid red";
        checkObj.memberName = false;
        // return;
    }
});

// 닉네임
const memberNick = document.getElementById("memberNickname");
const nickMessage = document.getElementById("nickMessage");

memberNick.addEventListener("input", function () {

    if (memberNick.value.length == 0) {
        nickMessage.innerText = "닉네임을 입력해주세요.";

        nickMessage.style.color = "red";
        memberNick.style.border = "1px solid red";
        checkObj.memberNickname = false;
        // return;
    }

    const regExp = /^[가-힣ㄱ-ㅎa-zA-Z0-9_ -]{2,}$/;

    if (regExp.test(memberNick.value)) {

        checkObj.memberNickname = true;

        nickMessage.innerText = "";
        nickMessage.style.color = "";
        memberNick.style.border = "";

        
    } else {
        nickMessage.innerText = "문자, 숫자 _ - 만 사용 가능합니다.";

        nickMessage.style.color = "red";
        memberNick.style.border = "1px solid red";
        checkObj.memberNickname = false;
        // return;
    }
});

// 휴대폰 번호
const memberTel = document.getElementById("memberTel");
const telMessage = document.getElementById("telMessage");

memberTel.addEventListener("input", function(){

    if(memberTel.value.length == 0){
        telMessage.innerText = "휴대폰 번호(-없이 입력)";

        telMessage.style.color = "red";
        memberTel.style.border = "1px solid red";
        checkObj.memberTel = false;

        return;
    }
    const regExp = /^0(1[01679]|2|[3-6][1-5]|70)\d{3,4}\d{4}$/;

    if(regExp.test(memberTel.value)){
        telMessage.innerText = "유효한 전화번호 형식입니다.";

        telMessage.innerText = "";
        telMessage.style.color = "";
        memberTel.style.border = "";
        checkObj.memberTel = true;
        
    } else{
        telMessage.innerText = "전화번호 형식이 올바르지 않습니다.";
        telMessage.style.color = "red";
        memberTel.style.border = "1px solid red";
        checkObj.memberTel = false; 
        // return;
    }
});





const memberGender = document.getElementsByName("memberGender");
if(
memberGender[0].checked==false &&  memberGender[1].checked==false){
            checkObj.memberGender = false; 
        }





const memberLive = document.getElementById("memberLive");
const state = document.getElementById("state");
state.addEventListener("change", function(){
    
    if(state.value.length == 0){
        checkObj.memberLive = false; 
        console.log("지역 실패")
        // return;
    }else{
        checkObj.memberLive = true;
        console.log("지역 성공")
    }
});







// 캘린더
$(function () {
    $("#memberBirth").datepicker({
        dateFormat: 'yy-mm-dd'
        , showOtherMonths: true
        , showMonthAfterYear: true
        , changeYear: true
        , changeMonth: true
        , buttonImageOnly: true
        , buttonText: "선택"
        , yearSuffix: "년"
        , monthNamesShort: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월']
        , monthNames: ['1월', '2월', '3월', '4월', '5월', '6월', '7월', '8월', '9월', '10월', '11월', '12월']
        , dayNamesMin: ['일', '월', '화', '수', '목', '금', '토']
        , dayNames: ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일']
        , minDate: "-50Y"
        , maxDate: "+0y"
    });

    $('#datepicker').datepicker('setDate', 'today');
});

// 지역

function categoryChange(e) {
    // const state = document.getElementById("state");

    const gangwon = ["강릉시", "동해시", "삼척시", "속초시", "원주시", "춘천시", "태백시", "고성군", "양구군", "양양군", "영월군", "인제군", "정선군", "철원군", "평창군", "홍천군", "화천군", "횡성군"];
    const gyeonggi = ["고양시", "과천시", "광명시", "광주시", "구리시", "군포시", "김포시", "남양주시", "동두천시", "부천시", "성남시", "수원시", "시흥시", "안산시", "안성시", "안양시", "양주시", "오산시", "용인시", "의왕시", "의정부시", "이천시", "파주시", "평택시", "포천시", "하남시", "화성시", "가평군", "양평군", "여주군", "연천군"];
    const gyeongsangnam = ["거제시", "김해시", "마산시", "밀양시", "사천시", "양산시", "진주시", "진해시", "창원시", "통영시", "거창군", "고성군", "남해군", "산청군", "의령군", "창녕군", "하동군", "함안군", "함양군", "합천군"];
    const gyeongsangbuk = ["경산시", "경주시", "구미시", "김천시", "문경시", "상주시", "안동시", "영주시", "영천시", "포항시", "고령군", "군위군", "봉화군", "성주군", "영덕군", "영양군", "예천군", "울릉군", "울진군", "의성군", "청도군", "청송군", "칠곡군"];
    const gwangju = ["광산구", "남구", "동구", "북구", "서구"];
    const daegu = ["남구", "달서구", "동구", "북구", "서구", "수성구", "중구", "달성군"];
    const daejeon = ["대덕구", "동구", "서구", "유성구", "중구"];
    const busan = ["강서구", "금정구", "남구", "동구", "동래구", "부산진구", "북구", "사상구", "사하구", "서구", "수영구", "연제구", "영도구", "중구", "해운대구", "기장군"];
    const seoul = ["강남구", "강동구", "강북구", "강서구", "관악구", "광진구", "구로구", "금천구", "노원구", "도봉구", "동대문구", "동작구", "마포구", "서대문구", "서초구", "성동구", "성북구", "송파구", "양천구", "영등포구", "용산구", "은평구", "종로구", "중구", "중랑구"];
    const ulsan = ["남구", "동구", "북구", "중구", "울주군"];
    const incheon = ["계양구", "남구", "남동구", "동구", "부평구", "서구", "연수구", "중구", "강화군", "옹진군"];
    const jeonnam = ["광양시", "나주시", "목포시", "순천시", "여수시", "강진군", "고흥군", "곡성군", "구례군", "담양군", "무안군", "보성군", "신안군", "영광군", "영암군", "완도군", "장성군", "장흥군", "진도군", "함평군", "해남군", "화순군"];
    const jeonbuk = ["군산시", "김제시", "남원시", "익산시", "전주시", "정읍시", "고창군", "무주군", "부안군", "순창군", "완주군", "임실군", "장수군", "진안군"];
    const jeju = ["서귀포시", "제주시", "남제주군", "북제주군"];
    const chungnam = ['공주시', '논산시', '보령시', '서산시', '아산시', '천안시', '금산군', '당진군', '부여군', '서천군', '연기군', '예산군', '청양군', '태안군', '홍성군'];
    const chungbuk = ["제천시", "청주시", "충주시", "괴산군", "단양군", "보은군", "영동군", "옥천군", "음성군", "증평군", "진천군", "청원군"];


    if (e.value == "강원") {
        add = gangwon;
    } else if (e.value == "경기") {
        add = gyeonggi;
    } else if (e.value == "경남") {
        add = gyeongsangnam;
    } else if (e.value == "경북") {
        add = gyeongsangbuk;
    } else if (e.value == "광주") {
        add = gwangju;
    } else if (e.value == "대구") {
        add = daegu;
    } else if (e.value == "대전") {
        add = daejeon;
    } else if (e.value == "부산") {
        add = busan;
    } else if (e.value == "서울") {
        add = seoul;
    } else if (e.value == "울산") {
        add = ulsan;
    } else if (e.value == "인천") {
        add = incheon;
    } else if (e.value == "전남") {
        add = jeonnam;
    } else if (e.value == "전북") {
        add = jeonbuk;
    } else if (e.value == "제주") {
        add = jeju;
    } else if (e.value == "충남") {
        add = chungnam;
    } else if (e.value == "충북") {
        add = chungbuk;
    }

    state.options.length = 1;
    // 군/구 갯수;

    for (property in add) {
        let opt = document.createElement("option");
        opt.value = add[property];
        opt.innerHTML = add[property];
        state.appendChild(opt);
    }
}
// 지역

const boxes = document.getElementsByClassName("search_boxes")[0];
boxes.style.display = "none";

memberLive.addEventListener("click", function () {
    if (boxes.style.display == "none") {

        boxes.style.display = "flex";
    } else {
        boxes.style.display = "none";
    }
});

// const state = document.getElementById("state");
const local = document.getElementById("local");
state.addEventListener("change", function () {


    boxes.style.display = "none";
    memberLive.value = local.value + " " + state.value;
});


const sendBtn = document.getElementById("sendBtnC");
const cMessage = document.getElementById("cMessage");

let checkInterval;
let min = 4;
let sec = 59;


sendBtn.addEventListener("click", function () {

    if (checkObj.memberId) {

        $.ajax({
            url: "signUpEmail",
            data: { "memberId": memberId.value },
            type: "GET",
            success: function (result) {
                console.log("이메일 발송 성공");
                console.log("result : " + result);
                
                checkObj.signUpEmail = true;
            },
            error: function () {

                console.log("이메일 발송 실패")
            }

        });


        cMessage.innerText = "5:00"; 
        min = 4;
        sec = 59;
        
        checkInterval = setInterval(function () {
            if (sec < 10) sec = "0" + sec;
            cMessage.innerText = min + ":" + sec;


            if (Number(sec) === 0) {
                min--;
                sec = 59;
            } else {
                sec--;
            }


            if (min === -1) { 
                cMessage.innerText = "인증번호가 만료되었습니다.";
                checkObj.checkNumber = false;
                clearInterval(checkInterval);
            }
        }, 1000);

        alert("인증번호가 발송되었습니다. 이메일을 확인해주세요.");
        checkObj.checkNumber = false;
    }
});



const cNumber = document.getElementById("cNumber");
const cBtn = document.getElementById("cBtn");

cBtn.addEventListener("click", function () {

    if (checkObj.signUpEmail) {

        if (cNumber.value.length == 6) { 

            $.ajax({
                url: "signUpCheckNumber",
                data: {
                    "cNumber": cNumber.value,
                    "memberId": memberId.value
                },
                type: "GET",
                success: function (result) {
                    console.log("인증번호 : " + result);

                    if (result == 1) {

                        clearInterval(checkInterval); 
                        
                        cMessage.innerText = "인증되었습니다."
                    } else if (result == 2) {
                        alert("만료된 인증 번호 입니다.");
                    } else { 
                        alert("인증 번호가 일치하지 않습니다.");
                    }
                },
                error: function () {
                    console.log("이메일 인증 실패");
                }
            });
        } else { 
            alert("인증번호를 정확하게 입력해주세요.");
            cNumber.focus();
        }
    } else { 
        alert("이메일 인증 버튼을 먼저 클릭해주세요.");
    }
});

function signUpValidate() {

    let str;

    for (let key in checkObj) {
        if (!checkObj[key]) {
            switch (key) {
                case "memberId": str = "이메일을"; break;
                case "memberPw": str = "비밀번호를"; break;
                case "memberPwConfirm": str = "비밀번호 확인을"; break;
                case "memberName": str = "이름을"; break;
                case "memberTel": str = "휴대폰 번호를"; break;
                case "memberNickname": str = "닉네임을"; break;
               
                str += " 확인 해주세요.";

            alert(str);
            document.getElementById(key).focus();

            return false;
        }
    }
    return true;
}
};
