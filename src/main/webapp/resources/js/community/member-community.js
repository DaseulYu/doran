// communityDetail 연결

// 관심모임 클릭
function pick(){
    const pick = document.getElementById("pick");

    if(pick.src.match("pick1.png")){
        alert("관심모임으로 선택되었습니다.");
        pick.src = "../resources/images/pick2.png";
    } else {
        alert("관심모임이 취소되었습니다.");
        pick.src = "../resources/images/pick1.png";
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
const updateImage = document.getElementById("update-image");

if(updateImage != null){
    updateImage.addEventListener("change", function(){

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
