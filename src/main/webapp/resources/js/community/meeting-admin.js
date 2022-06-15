const openPop = document.getElementById("openPop");
const closePop = document.getElementById("closePop");

openPop.addEventListener("click", function(){
    document.getElementById("popup_layer").style.display = "block";
});

closePop.addEventListener("click", function(){
    document.getElementById("popup_layer").style.display = "none";
});

function refuseChk(){
    
    if(!confirm("정말 거절하시겠습니까?")){
        return false;
    }
    return true;
}

function entrustChk(){

    if(!confirm("정말 위임하시겠습니까?")){
        return false;
    } else {
        Swal.fire({
            icon: 'success',
            title: '모임장이 위임되었습니다.',
            text: '안녕',
        });
    }
    return true;
}

function outChk(){

    if(!confirm("정말 추방하시겠습니까?")){
        return false;

    } else {
        alert("추방이 완료되었습니다.")
    }
    return true;
}

function deleteMeeting(){

    Swal.fire({
        title: '정말로 삭제하시겠습니까?',
        text: '10일 후에 삭제 및 모든 회원은 자동 강퇴되며, 해당 내용은 각 회원 이메일로 전송됩니다.',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#0C71FF',
        cancelButtonColor: '#FF8339',
        confirmButtonText: '삭제',
        cancelButtonText: '취소'

    }).then((result) => {
        if (result.isConfirmed) {
            Swal.fire(
                '삭제 요청이 확인되었습니다',
                '모임장 위임을 희망하는 회원이 있으면 해당 모임은 유지됩니다',
                'success'
            )
        }
    })


}

