const next = document.getElementById("next");
const groupCategory = document.getElementById("groupCategory");
const regionCategory = document.getElementById("regionCategory");
const region_2 = document.getElementById("region-2");
const addMeeting_form = document.getElementsByName("addMeeting-form");

// function nextSchedule(){

//     for(let i=0; i<groupCategory.length; i++){
//         if(groupCategory[i].value == 0){
//             groupCategory.focus();
//             return false;
//         }
//     }

//     if(groupCategory.length == 0){
//         alert("카테고리를 선택해주세요");
//         groupCategory.focus();
//         return false;
//     }

//     if(regionCategory.length == 0 || region_2.length == 0){
//         alert("지역을 선택해주세요");
//         regionCategory.focus();
//         return false;
//     }

//     location.href="/WEB-INF/views/community/addMeeting-schedule.jsp";
//     return true;
// }

function addMeeting(){

    for(let i=0; i<groupCategory.length; i++){
        if(groupCategory[i].value == 0){
            groupCategory.focus();
            return false;
        }
    }

    if(regionCategory.length == 0 || region_2.length == 0){
        alert("지역을 선택해주세요");
        regionCategory.focus();
        return false;
    }

    if(!confirm("정말 추가하시겠습니까?")){
        return false;
    }
    return true;
}
