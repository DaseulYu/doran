// notice-write 연결

// 미리보기 
const inputImage = document.getElementsByClassName("inputImage");
const preview = document.getElementsByClassName("preview");
const deleteImage = document.getElementsByClassName("delete-image");
const deleteList = document.getElementById("deleteList");
const deleteSet = new Set();

for(let i=0; i<inputImage.length; i++){

    inputImage[i].addEventListener("change", function(){

        if(this.files[0] != undefined){
            const reader = new FileReader();
            reader.readAsDataURL(this.files[0]);

            reader.onload = function(e){
                preview[i].setAttribute("src", e.target.result);
                deleteSet.delete(i);
            }
        } else {    
            preview[i].removeAttribute("src");
        }
    });

    // 미리보기 삭제 버튼 클릭
    deleteImage[i].addEventListener("click", function(){
        if(preview[i].getAttribute("src") != ""){
            preview[i].removeAttribute("src");
            inputImage[i].value = "";
            deleteSet.add(i);
        }
    });
}



// 게시글 작성 유효성 검사
function writeValidate(){
    const boardTitle = document.getElementsByName("boardTitle")[0];
    const boardContent = document.querySelector("[id='boardContent']");
    const img0 = document.getElementById("img0");
    const img1 = document.getElementById("img1");

        if(boardTitle.value.trim().length == 0){
            alert("제목을 입력해주세요.")
            boardTitle.value = "";
            boardTitle.focus();
            return false;
        }

        if(boardContent.value.trim().length == 0){
            alert("내용을 입력해주세요.")
            boardContent.value = "";
            boardContent.focus();
            return false;
        }

        deleteList.value = Array.from(deleteSet);
        return true;
}



    