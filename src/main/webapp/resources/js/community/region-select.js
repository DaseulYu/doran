function categoryChange(e) {

	var region_seoul = ["강남구", "강동구", "강북구", "강서구", "관악구", "광진구", "구로구", "금천구", "노원구", "도봉구", "동대문구", "동작구", "마포구", "서대문구", "서초구", "성동구", "성북구", "송파구", "양천구", "영등포구", "용산구", "은평구", "종로구", "중구", "중랑구"];
	var region_inchen = ["계양구", "미추홀구", "남동구", "동구", "부평구", "서구", "연수구", "중구", "강화군", "옹진군"];
	var region_gyeonggi = ["수원시 장안구", "수원시 권선구", "수원시 팔달구", "수원시 영통구", "성남시 수정구", "성남시 중원구", "성남시 분당구", "의정부시", "안양시 만안구", "안양시 동안구", "부천시", "광명시", "평택시", "동두천시", "안산시 상록구", "안산시 단원구", "고양시 덕양구", "고양시 일산동구",
                            "고양시 일산서구", "과천시", "구리시", "남양주시", "오산시", "시흥시", "군포시", "의왕시", "하남시", "용인시 처인구", "용인시 기흥구", "용인시 수지구", "파주시", "이천시", "안성시", "김포시", "화성시", "광주시", "양주시", "포천시", "여주시", "연천군", "가평군",
                            "양평군"];
    var region_gangwon = ["춘천시", "원주시", "강릉시", "동해시", "태백시", "속초시", "삼척시", "홍천군", "횡성군", "영월군", "평창군", "정선군", "철원군", "화천군", "양구군", "인제군", "고성군", "양양군"];
	var region_chungcheongnam = ["천안시 동남구", "천안시 서북구", "공주시", "보령시", "아산시", "서산시", "논산시", "계룡시", "당진시", "금산군", "부여군", "서천군", "청양군", "홍성군", "예산군", "태안군"];
    var region_chungcheongbuk = ["청주시 상당구", "청주시 서원구", "청주시 흥덕구", "청주시 청원구", "충주시", "제천시", "보은군", "옥천군", "영동군", "증평군", "진천군", "괴산군", "음성군", "단양군"];
    var region_gyeongsangnam = ["창원시 의창구", "창원시 성산구", "창원시 마산합포구", "창원시 마산회원구", "창원시 진해구", "진주시", "통영시", "사천시", "김해시", "밀양시", "거제시", "양산시", "의령군", "함안군", "창녕군", "고성군", "남해군", "하동군", "산청군", "함양군", "거창군", "합천군" ];
    var region_gyeongsangbuk = ["포항시 남구", "포항시 북구", "경주시", "김천시", "안동시", "구미시", "영주시", "영천시", "상주시", "문경시", "경산시", "군위군", "의성군", "청송군", "영양군", "영덕군", "청도군", "고령군", "성주군", "칠곡군", "예천군", "봉화군", "울진군", "울릉군"];
    var region_jeollanam = ["목포시", "여수시", "순천시", "나주시", "광양시", "담양군", "곡성군", "구례군", "고흥군", "보성군", "화순군", "장흥군", "강진군", "해남군", "영암군", "무안군", "함평군", "영광군", "장성군", "완도군", "진도군", "신안군"];
    var region_jeollabuk = ["전주시 완산구", "전주시 덕진구", "군산시", "익산시", "정읍시", "남원시", "김제시", "완주군", "진안군", "무주군", "장수군", "임실군", "순창군", "고창군", "부안군"];
    var region_daejeon = ["대덕구", "동구", "서구", "유성구", "중구"];
    var region_daegu = ["남구", "달서구", "동구", "북구", "서구", "수성구", "중구", "달성군"];
    var region_busan = ["강서구", "금정구", "남구", "동구", "동래구", "부산진구", "북구", "사상구", "사하구", "서구", "수영구", "연제구", "영도구", "중구", "해운대구", "기장군"];
    var region_ulsan = ["남구", "동구", "북구", "중구", "울주군"];
    var region_gwangju = ["광산구", "남구", "동구", "북구", "서구"];
    var region_sejong = ["세종특별자치시"];
    var region_jeju = ["서귀포시", "제주시"];
    
    var target = document.getElementById("region-2");

	if(e.value == "seoul") var d = region_seoul;
	else if(e.value == "inchen") var d = region_inchen;
	else if(e.value == "gyeonggi") var d = region_gyeonggi;
	else if(e.value == "gangwon") var d = region_gangwon;
	else if(e.value == "chungcheongnam") var d = region_chungcheongnam;
	else if(e.value == "chungcheongbuk") var d = region_chungcheongbuk;
	else if(e.value == "gyeongsangnam") var d = region_gyeongsangnam;
	else if(e.value == "gyeongsangbuk") var d = region_gyeongsangbuk;
	else if(e.value == "jeollanam") var d = region_jeollanam;
	else if(e.value == "jeollabuk") var d = region_jeollabuk;
	else if(e.value == "daejeon") var d = region_daejeon;
	else if(e.value == "daegu") var d = region_daegu;
	else if(e.value == "busan") var d = region_busan;
	else if(e.value == "ulsan") var d = region_ulsan;
	else if(e.value == "gwangju") var d = region_gwangju;
	else if(e.value == "sejong") var d = region_sejong;
	else if(e.value == "jeju") var d = region_jeju;

	target.options.length = 0;

	for (x in d) {
		var opt = document.createElement("option");
		opt.value = d[x];
		opt.innerHTML = d[x];
		target.appendChild(opt);
	}	
}        
