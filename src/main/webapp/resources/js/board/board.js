// boardList 연결
// board-detail 연결
// board-write 연결

// 목록으로
(function(){
    const goToListBtn = document.getElementById("goToListBtn");
    if(goToListBtn != null){
        goToListBtn.addEventListener("click", function(){

            const pathname = location.pathname;
            let url = pathname.substring(0, pathname.indexOf("/",1));
            url += "/community/board/list?";  
            // /doran/community/board/detail?no=1&cn=1&type=1

            // /doran/community/board/list?cn=1&type=1

            const params = new URL(location.href).searchParams;

            const cn = "cn=" + params.get("cn");
            const type = "type=1"; 

            let cp;
            if(params.get("cp") != null){
                cp = "cp=" + params.get("cp"); 
            } else{
                cp = "cp=1";
            }
            url += cn + "&" + type + "&" + cp;
            
            // 검색
            if(params.get("key") != null){
                const key = "&key=" + params.get("key");
                const query = "&query=" + params.get("query");

                url += key + query; 
            }
            location.href = url;
        });
    }
})();


// 자유 게시글 삭제
(function(){
    const deleteBtn = document.getElementById("deleteBtn"); 
    if(deleteBtn != null){ 
        deleteBtn.addEventListener("click", function(){
            
            let url = "delete";
            const params = new URL(location.href).searchParams;

            // 변수 저장
            const cn = "?cn=" + params.get("cn");
            const no = "&no=" + params.get("no");
            const type = "&type=" + params.get("type");
            url += cn + no + type;
            
            if(confirm("게시글을 삭제 하시겠습니까?")){
                location.href = url; 
            }
        });
    }
})();




// 검색
(function(){
    const select = document.getElementById("search-key");
    const option = document.querySelectorAll("#search-key > option")
    const input = document.getElementById("search-query");

    if(select != null){ 
        const params = new URL(location.href).searchParams;
        const key = params.get("key");
        const query = params.get("params");
        input.value = query;

        for(let op of option){
            if(op.value == key){
                op.selected = true;
            }
        }
    }
})();


// 검색어 입력 확인
function searchValidate(){
    const query = document.getElementById("search-query");

    if(query.value.trim().length == 0){
        alert("검색어를 입력해주세요.")
        query.value = "";
        query.focus();
        return false;
    }
    return true;
}

