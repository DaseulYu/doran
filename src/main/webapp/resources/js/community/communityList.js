// 검색창에 이전 검색기록 반영하기
(function(){

    
    const input = document.getElementById("query");

    if(input != null){
        const params = new URL(location.href).searchParams;

        const query = params.get("query");

        input.value = query;
    }
})();

function searchValidate(){

    const query = document.getElementById("query");

    if(query.value.trim().length == 0){ // 미작성
        query.value = ""; // 빈칸
        query.focus();

        return false;
    }

    return true;
}