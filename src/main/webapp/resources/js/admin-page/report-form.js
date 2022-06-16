// 신고

const btnReport = document.getElementById("btn-report");
const btnSubmission = document.getElementById("report-submission");

btnReport.addEventListener("click", function(){

    // btnSubmission.display = 




})


function reportValidation(){
    if(btnSubmission.value.trim().length == 0){
        alert("신고 사유를 입력해주세요.")
        btnSubmission.value = "";
        btnSubmission.focus();
        return false;
    }
    return true;
}







