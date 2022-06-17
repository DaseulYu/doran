// const openPop = document.getElementById("openPop");
// const closePop = document.getElementById("closePop");

// openPop.addEventListener("click", function(){
//     document.getElementById("popup_layer").style.display = "block";
// });

// closePop.addEventListener("click", function(){
//     document.getElementById("popup_layer").style.display = "none";
// });

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

function selectApply(){

    $.ajax({
        url : "admin/applySelect",
        data : {"cn" : cn},
        type : "POST",
        dataType : "JSON",

        success : function(commApplyList){

            const applyList = document.getElementById("applyList");
            applyList.innerHTML = "";

            for (let item of commApplyList) {

                const tr = document.createElement("tr");

                const td1 = document.createElement("td");
                td1.innerText = item.memberNickname;

                const td2 = document.createElement("td");
                const btn = document.createElement("button");
                btn.setAttribute("id", "confirm");
                btn.innerText = "승인";

                td2.append(btn);

                tr.append(td1, td2);
                applyList.append(tr);
            }
        },
        error : function(request){
            console.log("AJAX 에러 발생");
        }
    })
}

function selectMember(){

    $.ajax({
        url : "admin/select",
        data : {"cn" : cn},
        type : "POST",
        dataType : "JSON",

        success : function(commMemberList){
            const memberList = document.getElementById("memberList");
            memberList.innerHTML = "";

            for (let item of commMemberList) {

                const tr = document.createElement("tr");

                const td1 = document.createElement("td");
                td1.innerText = item.memberNickname;

                const td2 = document.createElement("td");
                const btn = document.createElement("button");
                btn.setAttribute("id", "entrust");
                btn.innerText = "위임";
                td2.append(btn);
                const td3 = document.createElement("td");
                const btn2 = document.createElement("button");
                btn2.setAttribute("id", "out");
                btn2.innerText = "추방";
                td3.append(btn2);

                tr.append(td1, td2, td3);
                memberList.append(tr);
            }
        },
        error : function(request){
            console.log("AJAX 에러 발생");
        }
    })
}

(function () {
    selectApply();
})();

(function () {
    selectMember();
})();
