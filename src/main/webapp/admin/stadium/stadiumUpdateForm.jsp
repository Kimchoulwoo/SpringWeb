<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../leftside.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>stadiumread</title>
<style>
.stainfo {
	width: 100%;
	height: 150px;
}
</style>
<script>
	function deleteAll(f) {
		for (var i = 0; i < f.length; i++) {
			f.options[i] = null;
		}
		f.length = 0;
	}

	function changeRegion(selform) {
		var regionArray = Array();
		var regionNone = Array("시군구선택");
		var regionSeoul = Array("시군구선택", "강남구", "강동구", "강북구", "강서구", "관악구",
				"광진구", "구로구", "금천구", "노원구", "도봉구", "동대문구", "동작구", "마포구",
				"서대문구", "서초구", "성동구", "성북구", "송파구", "양천구", "영등포구", "용산구",
				"은평구", "종로구", "중구", "중랑구");
		var regionIncheon = Array("시군구선택", "계양구", "남구", "남동구", "동구", "부평구",
				"서구", "연수구", "중구", "강화군", "옹진군");
		var regionDaejeon = Array("시군구선택", "대덕구", "동구", "서구", "유성구", "중구");
		var regionGwangju = Array("시군구선택", "광산구", "남구", "동구", "북구", "서구");
		var regionDaegu = Array("시군구선택", "남구", "달서구", "동구", "북구", "서구", "수성구",
				"중구", "달성군");
		var regionUlsan = Array("시군구선택", "남구", "동구", "북구", "중구", "울주군");
		var regionBusan = Array("시군구선택", "강서구", "금정구", "남구", "동구", "동래구",
				"부산진구", "북구", "사상구", "사하구", "서구", "수영구", "연제구", "영도구", "중구",
				"해운대구", "기장군");
		var regionGyeonggi = Array("시군구선택", "고양시", "과천시", "광명시", "광주시", "구리시",
				"군포시", "김포시", "남양주시", "동두천시", "부천시", "성남시", "수원시", "시흥시",
				"안산시", "안성시", "안양시", "양주시", "오산시", "용인시", "의왕시", "의정부시", "이천시",
				"파주시", "평택시", "포천시", "하남시", "화성시", "가평군", "양평군", "여주군", "연천군");
		var regionGangwon = Array("시군구선택", "강릉시", "동해시", "삼척시", "속초시", "원주시",
				"춘천시", "태백시", "고성군", "양구군", "양양군", "영월군", "인제군", "정선군", "철원군",
				"평창군", "홍천군", "화천군", "횡성군");
		var regionChungbuk = Array("시군구선택", "제천시", "청주시", "충주시", "괴산군", "단양군",
				"보은군", "영동군", "옥천군", "음성군", "증평군", "진천군", "청원군");
		var regionChungnam = Array("시군구선택", "계룡시", "공주시", "논산시", "보령시", "서산시",
				"아산시", "천안시", "금산군", "당진군", "부여군", "서천군", "연기군", "예산군", "청양군",
				"태안군", "홍성군");
		var regionJeonbuk = Array("시군구선택", "군산시", "김제시", "남원시", "익산시", "전주시",
				"정읍시", "고창군", "무주군", "부안군", "순창군", "완주군", "임실군", "장수군", "진안군");
		var regionJeonnam = Array("시군구선택", "광양시", "나주시", "목포시", "순천시", "여수시",
				"강진군", "고흥군", "곡성군", "구례군", "담양군", "무안군", "보성군", "신안군", "영광군",
				"영암군", "완도군", "장성군", "장흥군", "진도군", "함평군", "해남군", "화순군");
		var regionGyeongbuk = Array("시군구선택", "경산시", "경주시", "구미시", "김천시", "문경시",
				"상주시", "안동시", "영주시", "영천시", "포항시", "고령군", "군위군", "봉화군", "성주군",
				"영덕군", "영양군", "예천군", "울릉군", "울진군", "의성군", "청도군", "청송군", "칠곡군");
		var regionGyeongnam = Array("시군구선택", "거제시", "김해시", "마산시", "밀양시", "사천시",
				"양산시", "진주시", "진해시", "창원시", "통영시", "거창군", "고성군", "남해군", "산청군",
				"의령군", "창녕군", "하동군", "함안군", "함양군", "합천군");
		var regionJeju = Array("시군선택", "서귀포시", "제주시", "남제주군", "북제주군");

		regionArray[0] = regionNone;
		regionArray[1] = regionSeoul;
		regionArray[2] = regionIncheon;
		regionArray[3] = regionDaejeon;
		regionArray[4] = regionGwangju;
		regionArray[5] = regionDaegu;
		regionArray[6] = regionUlsan;
		regionArray[7] = regionBusan;
		regionArray[8] = regionGyeonggi;
		regionArray[9] = regionGangwon;
		regionArray[10] = regionChungbuk;
		regionArray[11] = regionChungnam;
		regionArray[12] = regionJeonbuk;
		regionArray[13] = regionJeonnam;
		regionArray[14] = regionGyeongbuk;
		regionArray[15] = regionGyeongnam;
		regionArray[16] = regionJeju;

		deleteAll(selform.staaddr2); // 지금 있는 목록 삭제하기.

		sidoindex = selform.staaddr1.selectedIndex;
		gugunlen = regionArray[sidoindex].length;
		cur_sido = selform.staaddr1.options[selform.staaddr1.selectedIndex].value;

		selform.staaddr2.length = gugunlen;

		for (i = 0; i < gugunlen; i++) {
			selform.staaddr2.options[i] = new Option(regionArray[sidoindex][i],
					regionArray[sidoindex][i]);
			if (cur_sido == sstaaddr1 && sstaaddr2 == regionArray[sidoindex][i])
				selform.staaddr2.options[i].selected = true;
		}
	}

	function initRegion() {
		var sido = Array("시도선택", "서울특별시", "인천광역시", "대전광역시", "광주광역시", "대구광역시",
				"울산광역시", "부산광역시", "경기도", "강원도", "충청북도", "충청남도", "전라북도", "전라남도",
				"경상북도", "경상남도", "제주도");
		sidolen = sido.length;
		document.frm.staaddr1.options.length = sidolen;
		for (var i = 0; i < sidolen; i++) {
			document.frm.staaddr1.options[i] = new Option(sido[i], sido[i]);
			if (sstaaddr1 == sido[i]) {
				document.frm.staaddr1.options[i].selected = true;
				document.frm.staaddr1.selectedIndex = i;
			}
		}
	}

	function cityCheck(f) {
		var posterMF1 = f.posterMF1.value;
		var staaddr1 = f.staaddr1.value;
		var staaddr2 = f.staaddr2.value;
		var staaddr3 = f.staaddr3.value;
		if (staaddr1 == '시도선택' || staaddr2 == '시군구선택') {
			alert("구장 주소를 선택 하세요.");
			return false;
		}
		if (staaddr3 == "") {
			alert("구장 상세 주소를 입력 하세요.");
			return false;
		}
		
		if (posterMF1.length == 0) {
			alert("첫번째 사진은 들어가야합니다");
			return false;
		}
	}
</script>

</head>
<body>
	<FORM name='frm' method='POST'
		action='update.do?stacode=${stareaddto.stacode}'
		enctype='multipart/form-data'>

		<table width='100%'>
			<tr>
				<th colspan='3'>구장 수정</th>
			</tr>
			<tr>
				<th colspan='3'><hr></th>
			</tr>
			<tr>
				<th>● 구장 이름</th>
				<td colspan='2'><input type='text' name='staname'
					value=${stareaddto.staname }></td>
				<td></td>
			</tr>
			<tr>
				<th colspan='3'><hr></th>
			</tr>
			<tr>
				<th>● 구장주 아이디</th>
				<th colspan='2'><input name='id' value='${stareaddto.id }'></th>
			</tr>
			<tr>
				<th colspan='3'><hr></th>
			</tr>
			<tr>
				<th>● 구장 주소</th>
				<td>기존 주소 : ${stareaddto.staaddr1 } ${stareaddto.staaddr2 }
					${stareaddto.staaddr3 }<br> <select name="staaddr1"
					OnChange="changeRegion(frm)"></select> <select name="staaddr2"></select>*
					지역을 선택하세요.<br> <input type='text' name='staaddr3'>상세
					주소를 입력하시오
				</td>
			</tr>
			<tr>
				<th colspan='3'><hr></th>
			</tr>
			<tr>
				<th>●구장 정보</th>
				<td colspan='3'><jsp:include page="./smartedit2.jsp"></jsp:include>
				</td>
			</tr>
			<tr>
				<th colspan='3'><hr></th>
			</tr>
			<tr>
				<th>● 대여 가격</th>
				<td colspan='2'><input type='text' name='stapay'
					value=${stareaddto.stapay } readonly></td>
			</tr>
			<tr>
				<th colspan='3'><hr></th>
			</tr>
			<tr>
				<th>● 구장 개장</th>
				<td colspan='2'><input type='text' name='staopen'
					value=${stareaddto.staopen }></td>
			</tr>
			<tr>
				<th colspan='3'><hr></th>
			</tr>
			<tr>
				<th>● 구장 마감</th>
				<td colspan='2'><input type='text' name='staclose'
					value=${stareaddto.staclose }></td>
			</tr>
			<tr>
				<th colspan='3'><hr></th>
			</tr>
			<tr>
				<th>● 구장 등록일</th>
				<td colspan='2'>${stareaddto.stadate }</td>
			</tr>
			<tr>
				<th colspan='3'><hr></th>
			</tr>
			<tr>
				<th>●비밀번호</th>
				<td><input type="password" name='stapasswd' value='${s_pw }'
					autofocus required readonly></td>

			</tr>

			<tr>
				<th colspan='3'><hr></th>
			</tr>

			<TR>
				<TH>● 구장 사진</TH>
			</TR>
			<TR>				
				<TD><INPUT type='file' name='posterMF1' size='50'></TD>
			</TR>
			<TR>				
				<TD><INPUT type='file' name='posterMF2' size='50'></TD>
			</TR>
			<TR>				
				<TD><INPUT type='file' name='posterMF3' size='50'></TD>
			</TR>
			<TR>				
				<TD><INPUT type='file' name='posterMF4' size='50'></TD>
			</TR>
			<TR>				
				<TD><INPUT type='file' name='posterMF5' size='50'></TD>
				
			</TR>


			<tr>
				<th colspan='3'><hr></th>
			</tr>
			<tr>
				<td colspan='2' width='70%'></td>
				<td width='30%'><input type="button" value="수정 취소"
					onclick="location.href='stadiumlist.do'"> <input
					type="submit" value="수정" onclick="return cityCheck(this.form)"></td>
			</tr>
		</table>
	</FORM>


	<script>
		// php 변수 staaddr1와 staaddr2에 저장된 값을 기본 선택값으로 초기화한다.
		// staaddr1와 staaddr2에 저장된 값이 없으면 첫 항목으로 초기화한다.
		var sstaaddr1 = "<?=$staaddr1?>"; // php 변수를 자바스크립트 변수로 저장 - 현재 선택된 시도.
		var sstaaddr2 = "<?=$staaddr2?>"; // php 변수를 자바스크립트 변수로 저장 - 현재 선택된 시군구.
		initRegion();
		changeRegion(frm);
	</script>
	<!-- 본문 끝 -->
	<jsp:include page="../footer.jsp"></jsp:include>