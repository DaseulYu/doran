// notice-detail 연결 

// 사용자 공지사항

// 게시글 조회
// 목록으로(전체)
(function(){
    const goToListBtn = document.getElementById("aGoToListBtn");
    if(goToListBtn != null){
        goToListBtn.addEventListener("click", function(){

            const pathname = location.pathname;
            // /doran/notice/detail

            let url = pathname.substring(0, pathname.indexOf("/",1));
            //  /doran

            url += "/notice?";  
            //  /doran/notice?

            const params = new URL(location.href).searchParams;

            let cp;
            if(params.get("cp") != null){
                cp = "cp=" + params.get("cp"); 
            } else{
                cp = "cp=1";
            }
            url += cp;
            
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

// 목록으로(카테고리별)
(function(){
    const goToListBtn = document.getElementById("cGoToListBtn");
    if(goToListBtn != null){
        goToListBtn.addEventListener("click", function(){

            const pathname = location.pathname;
            // /doran/notice/detail

            let url = pathname.substring(0, pathname.indexOf("/",1));
            //  /doran

            url += "/notice/list?";  
            //  /doran/notice/list?

            const params = new URL(location.href).searchParams;

            const type = "type=" + params.get("type"); 

            let cp;
            if(params.get("cp") != null){
                cp = "cp=" + params.get("cp"); 
            } else{
                cp = "cp=1";
            }
            url += type + "&" + cp;
            // /doran/notice/list?type=1&cp=1
            
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
