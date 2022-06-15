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


// // 모임 메인 자유게시판
// const contextPath = "${contextPath}";
// // 게시글 번호
// const communityNo = "${param.cn}"; 
// function mainBoardList(){

//         $.ajax({
//             url : "/community/detail/boardList",
//             data : {"communityNo" : communityNo},
//             type : "get",
//             dataType : "JSON",
//             suceess : function(bList){
//                 console.log(bList);

//             },
//             error : function(req, status, error){
//                 console.log("에러발생");
//                 console.log(req.responseText);
//             }
//         });
// }

const detail_popup = document.getElementById("detail-Popup");
const event_popup = document.getElementById("event-Popup");

detail_popup.addEventListener("click", function(){
    document.getElementById("detail_popup_layer").style.display = "block";
});

event_popup.addEventListener("click", function(){
    document.getElementById("event_popup_layer").style.display = "block";
});

// closePop.addEventListener("click", function(){
//     document.getElementById("popup_layer").style.display = "none";
// });




