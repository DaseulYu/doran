// communityDetail 연결

// 관심모임 클릭 (수정예정)
function pick(){
    const pick = document.getElementById("pick");
    
    if(memberNo == ""){ 
        alert("로그인 후 이용해주세요.");
        return;
    }

    if(pick.src.match("pick1.png")){
        alert("관심모임으로 선택되었습니다.");
        pick.src = "../resources/images/pick2.png";
    } else {
        alert("관심모임이 취소되었습니다.");
        pick.src = "../resources/images/pick1.png";
    }
}

// 모임 가입하기
function join(communityNo){

    if(memberNo == ""){ 
        alert("로그인 후 이용해주세요.");
        return;
    }

    if(confirm("모임에 가입하시겠습니까?")){
        $.ajax({
            url : "join/insert",
            data : {"cn" : communityNo,
                    },
            type : "post",
            success : function(result){
                if(result>0){
                    alert("모임에 가입되었습니다.");
                    window.location.reload();
                } else  {
                    alert("모입 가입 실패");
                }
            },
            error : function(req, status, error){
                console.log("모임 가입 에러");
                console.log(req.responseText);
            }
        });
    }
}

// 모임 탈퇴하기
function secession(communityNo){

    if(memberNo == ""){ 
        alert("로그인 후 이용해주세요.");
        return;
    }

    if(confirm("모임에 탈퇴하시겠습니까?")){
        $.ajax({
            url : "join/delete",
            data : {"memberNo" : memberNo,
                    "cn" : communityNo,
                    },
            type : "post",
            success : function(result){
                if(result>0){
                    alert("모임에 탈퇴되었습니다.");
                    window.location.reload();
                } else  {
                    alert("모입 탈퇴 실패");
                }
            },
            error : function(req, status, error){
                console.log("모임 탈퇴 에러");
                console.log(req.responseText);
            }
        });
    }
}


const detail_popup = document.getElementById("detail-Popup");

detail_popup.addEventListener("click", function(){
    document.getElementById("detail_popup_layer").style.display = "block";
});

// closePop.addEventListener("click", function(){
//     document.getElementById("popup_layer").style.display = "none";
// });

// 커뮤니티 대표 이미지 등록
const inputImage = document.getElementById("input-image");

if(inputImage != null){
    
    inputImage.addEventListener("change", function(){

        if(this.files[0] != undefined){
            const reader = new FileReader();
            reader.readAsDataURL(this.files[0]);
            reader.onload = function(e){
                const communityImage = document.getElementById("community-image");
                communityImage.setAttribute("src", e.target.result);
            }
        }
    });
}

function communityEdit(){
    const inputImage = document.getElementById("input-image");

    if(inputImage.value == ""){
        alert("이미지를 선택한 후 버튼을 눌러주세요");
        return false;
    }
    return true;
}

