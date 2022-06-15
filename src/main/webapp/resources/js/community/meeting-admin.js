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

function confirmChk(){
    
    if(!confirm("정말 승인하시겠습니까?")){
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

    if(!confirm("모임을 삭제하시겠습니까?")){
        return false;

    } else {
        alert("삭제가 완료되었습니다.")
    }
    return true;
}

