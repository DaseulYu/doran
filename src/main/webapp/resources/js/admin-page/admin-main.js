// admin-main 연결
// admin-notice-detail 연결

// 게시글 상세조회
// 비공개 게시글 포함 전체 목록
(function(){
    const goToListBtn = document.getElementById("goToListBtn");
    if(goToListBtn != null){
        goToListBtn.addEventListener("click", function(){

            const pathname = location.pathname;

            let url = pathname.substring(0, pathname.indexOf("/",1));
            url += "/admin/list?";  

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


// 게시글 삭제
(function(){
    const deleteBtn = document.getElementById("deleteBtn"); 
    if(deleteBtn != null){ 
        deleteBtn.addEventListener("click", function(){
            
            let url = "delete";
            const params = new URL(location.href).searchParams;

            // 변수 저장
            const no = "?no=" + params.get("no"); // ?no=23
            url += no; // delete?no=23

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




// 관리자 공지 등록/삭제
const noticeNo = "${noticeList.noticeNo}";
// 등록
function noticeInsert(noticeNo){
    if(confirm("게시글을 등록 하시겠습니까?")){
        $.ajax({
            url : "list/insert",
            data : {"noticeNo" : noticeNo},
            type : "GET",
            success : function(result){
                if(result>0){
                    alert("게시글이 등록되었습니다.");
                    window.location.reload();
                } else  {
                    alert("게시글 등록 실패");
                }
            },
            error : function(req, status, error){
                console.log("게시글 등록 에러");
                console.log(req.responseText);
            }
        });
    }
}

// 삭제
function noticeDelete(noticeNo){
    if(confirm("게시글을 삭제 하시겠습니까?")){
        $.ajax({
            url : "list/delete",
            data : {"noticeNo" : noticeNo},
            type : "GET",
            success : function(result){
                if(result>0){
                    alert("게시글이 삭제되었습니다.");
                    window.location.reload();
                } else  {
                    alert("게시글 삭제 실패");
                }
            },
            error : function(req, status, error){
                console.log("게시글 삭제 에러");
                console.log(req.responseText);
            }
        });
    }
}


