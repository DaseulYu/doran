const openPop = document.getElementById("openPop");
const closePop = document.getElementById("closePop");

openPop.addEventListener("click", function(){
    document.getElementById("popup_layer").style.display = "block";
});

closePop.addEventListener("click", function(){
    document.getElementById("popup_layer").style.display = "none";
});

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

        const pathname = location.pathname;
        let url = pathname.substring(0,  pathname.indexOf("/", 1));

        url += "/community/list?cp=1"; 
        const params = new URL(location.href).searchParams;

        location.href = url;
    }
    return true;
}

document.getElementById("selectApply").addEventListener("click", function(){

    $.ajax({
        url : "community/admin/applySelect",
        data : {"communityNo" : cp},
        type : "POST",
        dataType : "JSON",

        success : function(comm){
            
        },
        error : function(request){
            console.log("AJAX 에러 발생");
        }
    })
});

document.getElementById("selectMember").addEventListener("click", function(){
    
    $.ajax({
        url : "community/admin/select",
        data : {"communityNo" : cp},
        type : "POST",
        dataType : "JSON",

        success : function(comm){

        },
        error : function(request){
            console.log("AJAX 에러 발생");
        }
    })
})