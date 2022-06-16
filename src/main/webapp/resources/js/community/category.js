function drop_category(){
	let click = document.getElementById("dropContent");
	let button = document.getElementById("button");
	let button180 = document.getElementById("button180");
	if(click.style.display == "none"){
		click.style.display = "block";
		
		button.innerHTML = "<i class='fal fa-chevron-circle-up fa-3x'></i>";
		
	}else{
		click.style.display = "none";
		button.innerHTML = "<i class='fal fa-chevron-circle-down fa-3x'></i>";

	}
}

// let heart = document.getElementById("heart");
// heart.addEventListener("click", function(){

// 	if(heart.innerHTML = "<i id='heart' class='fal fa-heart'></i>"){
// 		heart.style.color = "red";
// 	} else{
// 		heart.style.color = "blue";
// 	}
// });

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