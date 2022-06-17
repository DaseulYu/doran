// 신고
const btnReport = document.getElementById("btn-report");
const btnSubmission = document.getElementById("report-submission");
const reportCancel = document.getElementById("report-cancel");
const reportCategory = document.getElementById("reportCategory");

// btnReport.addEventListener("click", function(){
//     document.getElementsByClassName("report-popup")[0].style.display = "block";
function showReportPopup(boardNo){


    document.getElementsByClassName("report-popup")[0].style.display = "block";
    
    
    
    document.getElementById("boardNo").value = boardNo;
}



reportCancel.addEventListener("click", function(){
    document.getElementsByClassName("report-popup")[0].style.display = "none";
})

function reportValidation(){
    const reportCategory = document.getElementById("reportCategory");

    if(reportCategory.value == 0){
        alert("신고 사유를 선택해주세요.");
        reportCategory.focus();
        return false;
    }
    return true;
}


// 신고 값
reportCategory.addEventListener("change", function(){
    document.getElementsByName("reportTitle")[0].value = this.value;
});

