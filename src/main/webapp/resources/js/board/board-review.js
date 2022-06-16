// board-review.jsp 연결

// 후기 게시글 삭제
function boardDelete(boardNo){

    if(confirm("게시글을 삭제 하시겠습니까?")){
        $.ajax({
            url : "delete",
            data : {"no" : boardNo,
                    "cn" : cn,
                    "type" : type
                    },
            type : "GET",
            success : function(result){
                alert("게시글이 삭제되었습니다.");
                window.location.reload();
            },
            error : function(req, status, error){
                console.log("게시글 삭제 에러");
                console.log(req.responseText);
            }
        });
    }
}