function myCommunityList() {

    $.ajax({
        url: "myCommuList",
        data: { "loginMemberNo": loginMemberNo },
        type: "POST",
        dataType: "JSON",
        success: function (commulist) {
            console.log(commulist)

            const commuList = document.getElementById("commuList");
            commuList.innerHTML = "";

            for (let item of commulist) {

                const tr = document.createElement("tr");

                const td1 = document.createElement("td");
                td1.innerText = item.communityName;

                const td2 = document.createElement("td");
                td2.innerText = item.communityArea;

                const td3 = document.createElement("td");
                if (item.communityFlag == 'N') {
                    td3.innerText = "모임 유지";
                } else {
                    td3.innerText = "모임 삭제";
                }
                const td4 = document.createElement("td");
                if (item.applyFlag == 'N') {
                    td4.innerText = "승인 대기";
                } else {
                    td4.innerText = "승인";
                }
                const td5 = document.createElement("td");
                td5.innerText = item.applyDate;

                tr.append(td1, td2, td3, td4, td5);
                commulist.append(tr);
            }


        }, error: function (req) {
            console.log(req.responseText);
        }
    });
}


(function () {
    myCommunityList();
})();