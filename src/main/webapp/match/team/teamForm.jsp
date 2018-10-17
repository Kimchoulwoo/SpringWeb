<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../header2.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt"  uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 본문 시작 template.jsp -->
<!-- Page Content -->
<div class="container-fluid" id="div-content" style="padding-left: 0px; margin-bottom: 20px;">
	<div class="col-xs-2" style="height: 680px; padding-left: 0px;" id="div_col2">
		<div id="div_left" class="w3-sidebar w3-light-grey w3-bar-block">
			<h3 class="w3-bar-item">매치</h3>
			<a href="../team/teamlist.do" class="w3-bar-item w3-button">팀</a> 
			<a href="../club/clublist.do"	class="w3-bar-item w3-button">클럽</a> 
			<a href="../matching/match.do"	class="w3-bar-item w3-button">매치</a>
		</div>
	</div>
	<div class="col-xs-10" style="margin: 0px; margin-top: 10px; padding-left: 0px;">
		<!-- Page Content -->
		<div id="div_content" style="height: 100%">
			<script>
function dateSelect(docForm,selectIndex) {
	watch = new Date(docForm.year.options[docForm.year.selectedIndex].text, docForm.month.options[docForm.month.selectedIndex].value,1);
	hourDiffer = watch - 86400000;
	calendar = new Date(hourDiffer);

	var daysInMonth = calendar.getDate();
		for (var i = 0; i < docForm.day.length; i++) {
			docForm.day.options[0] = null;
		}
		for (var i = 0; i < daysInMonth; i++) {
			docForm.day.options[i] = new Option(i+1);
	}
	document.form1.day.options[0].selected = true;
}
</script>

<script>
function Today(year,mon,day,time){
     if(year == "null" && mon == "null" && day == "null" && time=="null"){       
     today = new Date();
     this_year=today.getFullYear();
     this_month=today.getMonth();
     this_month+=1;
     if(this_month <10) this_month="0" + this_month;
 
     this_day=today.getDate();
     if(this_day<10) this_day="0" + this_day;
     
     this_time=today.getHours();
 	}else{  
     var this_year = eval(year);
     var this_month = eval(mon); 
     var this_day = eval(day);
     var this_time = eval(time);
     }
 
  montharray=new Array(31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31); 
  maxdays = montharray[this_month-1]; 
  //윤달
  if (this_month==2) { 
      if ((this_year/4)!=parseInt(this_year/4)) {
    	  maxdays=28; 
      }else{
    	  maxdays=29;
      }
  } 
 
 document.writeln("<select name='year' size=1 onChange='dateSelect(this.form,this.form.month.selectedIndex);'>");
     for(i=this_year;i<this_year+6;i++){//현재 년도에서 미래로 5년까지를 표시함
         if(i==this_year) document.writeln("<OPTION VALUE="+i+ " selected >" +i); 
         else document.writeln("<OPTION VALUE="+i+ ">" +i); 
     }    
 document.writeln("</select>년");      

 
 document.writeln("<select name='month' size=1 onChange='dateSelect(this.form,this.selectedIndex);'>");
     for(i=1;i<=12;i++){ 
         if(i<10){
             if(i==this_month) document.writeln("<OPTION VALUE=0" +i+ " selected >0"+i); 
             else document.writeln("<OPTION VALUE=0" +i+ ">0"+i);
         }         
         else{
             if(i==this_month) document.writeln("<OPTION VALUE=" +i+ " selected >" +i);  
             else document.writeln("<OPTION VALUE=" +i+ ">" +i);  
         }                     
    }         
 document.writeln("</select>월");
 
 document.writeln("<select name='day' size=1>");
     for(i=1;i<=maxdays;i++){ 
         if(i<10){
             if(i==this_day) document.writeln("<OPTION VALUE=0" +i+ " selected >0"+i); 
             else document.writeln("<OPTION VALUE=0" +i+ ">0"+i); 
         }else{
             if(i==this_day) document.writeln("<OPTION VALUE=" +i+ " selected } >"+i); 
             else document.writeln("<OPTION VALUE=" +i+ ">" +i);  
         }                     
    }         
 document.writeln("</select>일");  

 
 //시간
 document.writeln("<select name='time' size=1>");
 for(i=0;i<=23;i++){ 
     if(i<10){
    	 if(i==this_time) document.writeln("<OPTION VALUE=0" +i+ " selected >0"+i);
    	 else document.writeln("<OPTION VALUE=0" +i+ ">0"+i); 
     }    
     else{
    	 if(i==this_time) document.writeln("<OPTION VALUE=" +i+ " selected } >"+i);
    	 else document.writeln("<OPTION VALUE=" +i+ ">" +i);  
     }                     
}         
document.writeln("</select>시");
}
</script>

<script>
function deleteAll(f)
{
for (var i=0; i<f.length; i++) {
  f.options[i] = null;
}
f.length = 0;
}

function changeRegion(selform)
{
var regionArray = Array();
var regionNone = Array ( "시군구선택" );
var regionSeoul = Array( "시군구선택", "강남구",  "강동구",  "강북구",
    "강서구",  "관악구",  "광진구",  "구로구",  "금천구",
    "노원구",  "도봉구",  "동대문구",  "동작구",  "마포구",
    "서대문구",  "서초구",  "성동구",  "성북구",  "송파구",
    "양천구",  "영등포구",  "용산구",  "은평구",  "종로구",
    "중구",   "중랑구");
var regionIncheon = Array ( "시군구선택", "계양구",  "남구",   "남동구",
    "동구",   "부평구",  "서구",   "연수구",  "중구",
    "강화군",  "옹진군");
var regionDaejeon = Array ( "시군구선택", "대덕구",  "동구",   "서구",
    "유성구",  "중구");
var regionGwangju = Array ( "시군구선택", "광산구",  "남구",   "동구", 
    "북구",   "서구");
var regionDaegu = Array ( "시군구선택", "남구",   "달서구",  "동구",
    "북구",   "서구",   "수성구",  "중구",   "달성군");
var regionUlsan = Array ( "시군구선택", "남구",   "동구",   "북구",
    "중구",   "울주군");
var regionBusan = Array ( "시군구선택", "강서구",  "금정구",  "남구",
    "동구",   "동래구",  "부산진구",  "북구",   "사상구",
    "사하구",  "서구",   "수영구",  "연제구",  "영도구",
    "중구",   "해운대구",  "기장군");
var regionGyeonggi = Array ("시군구선택", "고양시",  "과천시",  "광명시",
    "광주시",  "구리시",  "군포시",  "김포시",  "남양주시",
    "동두천시",  "부천시",  "성남시",  "수원시",  "시흥시",
    "안산시",  "안성시",  "안양시",  "양주시",  "오산시",
    "용인시",  "의왕시",  "의정부시",  "이천시",  "파주시",
    "평택시",  "포천시",  "하남시",  "화성시",  "가평군",
    "양평군",  "여주군",  "연천군");
var regionGangwon = Array ( "시군구선택", "강릉시",  "동해시",  "삼척시",
    "속초시",  "원주시",  "춘천시",  "태백시",  "고성군",
    "양구군",  "양양군",  "영월군",  "인제군",  "정선군",
    "철원군",  "평창군",  "홍천군",  "화천군",  "횡성군");
var regionChungbuk = Array ("시군구선택", "제천시",  "청주시",  "충주시",
    "괴산군",  "단양군",  "보은군",  "영동군",  "옥천군",
    "음성군",  "증평군",  "진천군",  "청원군");
var regionChungnam = Array ("시군구선택", "계룡시",  "공주시",  "논산시",
    "보령시",  "서산시",  "아산시",  "천안시",  "금산군",
    "당진군",  "부여군",  "서천군",  "연기군",  "예산군",
    "청양군",  "태안군",  "홍성군");
var regionJeonbuk = Array ( "시군구선택", "군산시",  "김제시",  "남원시",
    "익산시",  "전주시",  "정읍시",  "고창군",  "무주군",
    "부안군",  "순창군",  "완주군",  "임실군",  "장수군",  "진안군");
var regionJeonnam = Array ( "시군구선택", "광양시",  "나주시",  "목포시",
    "순천시",  "여수시",  "강진군",  "고흥군",  "곡성군",
    "구례군",  "담양군",  "무안군",  "보성군",  "신안군",
    "영광군",  "영암군",  "완도군",  "장성군",  "장흥군",
    "진도군",  "함평군",  "해남군",  "화순군");
var regionGyeongbuk = Array ("시군구선택", "경산시",  "경주시",  "구미시",
    "김천시",  "문경시",  "상주시",  "안동시",  "영주시",
    "영천시",  "포항시",  "고령군",  "군위군",  "봉화군",
    "성주군",  "영덕군",  "영양군",  "예천군",  "울릉군",
    "울진군",  "의성군",  "청도군",  "청송군",  "칠곡군");
var regionGyeongnam = Array ("시군구선택", "거제시",  "김해시",  "마산시",
    "밀양시",  "사천시",  "양산시",  "진주시",  "진해시",
    "창원시",  "통영시",  "거창군",  "고성군",  "남해군",
    "산청군",  "의령군",  "창녕군",  "하동군",  "함안군",
    "함양군",  "합천군");
var regionJeju = Array ( "시군선택",  "서귀포시",  "제주시",  "남제주군",  "북제주군");

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

deleteAll(selform.wr_gugun); // 지금 있는 목록 삭제하기.
  
sidoindex = selform.wr_sido.selectedIndex;
gugunlen = regionArray[sidoindex].length;
cur_sido = selform.wr_sido.options[selform.wr_sido.selectedIndex].value;

selform.wr_gugun.length=gugunlen;

for (i=0; i<gugunlen; i++) {
  selform.wr_gugun.options[i] = new Option(regionArray[sidoindex][i], regionArray[sidoindex][i]);
  if (cur_sido == swr_sido && swr_gugun == regionArray[sidoindex][i])
   selform.wr_gugun.options[i].selected = true;
}
}

function initRegion()
{
var sido = Array ( "시도선택",  "서울특별시", "인천광역시", "대전광역시",
      "광주광역시", "대구광역시", "울산광역시", "부산광역시",
      "경기도",  "강원도",  "충청북도",  "충청남도",
      "전라북도",  "전라남도",  "경상북도",  "경상남도",  "제주도");
sidolen = sido.length;
document.teamCreate.wr_sido.options.length = sidolen;
for(var i=0; i<sidolen; i++) {
  document.teamCreate.wr_sido.options[i] = new Option(sido[i], sido[i]);
  if (swr_sido == sido[i]) {
   document.teamCreate.wr_sido.options[i].selected = true;
   document.teamCreate.wr_sido.selectedIndex = i;
  }
}
}
</script>
<!-- 본문 시작 -->
<br>
<br>
<form name="teamCreate" method="POST" action="./teamCreate.do" onsubmit="return teamCheck(this)">
	<table border="1" style="margin:auto; width:500px">
		<tr>
			<th>팀 이름 :</th>
			<td><input type="text" name="tname" id="id" size="20" placeholder="Team Name" required></td>
		</tr>
		<tr>
			<th>팀장 :</th>
			<td><input type="text" name="tid" value="${s_id }" readonly></td>
		</tr>		
		<tr>
			<th>경기시간 :</th>				
			<c:if test="${pcode=='0' }">
			<input type="hidden" name="pcode" value=null>			
 			<td><script> Today('null','null','null','null'); </script></td>
			</c:if>
			<c:if test="${pcode!='0' }">
			<td>
			<input type="hidden" name="pcode" value="${pcode }">
			 <input type="text" name="year" value="${fn:substring(dto.pdate, 0, 4)}" size="5" readonly>년
			 <input type="text" name="month" value="${fn:substring(dto.pdate, 4, 6)}" size="3" readonly>월
			 <input type="text" name="day" value="${fn:substring(dto.pdate, 6, 8)}" size="3" readonly>일
			 <input type="text" name="time" value="${fn:substring(dto.ptime, 0, fn:indexOf(dto.ptime, ','))}" size="3" readonly>시
			</td>
			</c:if>						
		</tr>
		<tr>
		    <th>경기희망지역 :</th>
		    <c:if test="${pcode=='0' }">
		    <td>
		        <select name="wr_sido" OnChange="changeRegion(teamCreate)"></select>
                <select name="wr_gugun"></select>    
		    </td>
		    </c:if>
		    <c:if test="${pcode!='0' }">
		    <td>
		    <input type="text" name = "wr_sido" value="${fn:substring(dto.staaddr1, 0, fn:indexOf(dto.staaddr1, ' '))}" size="10" readonly>
		    <input type="text" name = "wr_gugun" value="${fn:substring(dto.staaddr2, fn:indexOf(dto.staaddr2, ' ')+1, fn:length(dto.staaddr2)) }" size="10" readonly>
		    </td>
		    </c:if>
		    
		</tr>
		<tr>
			<th>팀 인원 :</th>
			<td>
			    <select name="player" id="player" onchange="addPlayer(this.value)">
					<option value="0">팀원 수</option>
					<option value="3">3명</option>
					<option value="4">4명</option>
					<option value="5">5명</option>
			    </select>
			</td>
		</tr>
		<tr>
			<th>팀원 :</th>
			<td><div id="mtable"></div></td>
		</tr>
		<tr>
	    <td colspan="2">
		<input type="submit" value="팀 생성"/>
		<input type="reset"  value="취소" onclick="location.href='../team/teamlist.do'"/>
		<input type="button" value="결제내역 불러오기" onclick="selectPcode()">
	</td>
</tr>
	</table>
</form>

<script>
	function selectTime(idx, value){
		if (idx != 0){
			var str = "<input type='text' name='ttime' value='" + value + "' readonly>";
			document.getElementById("ttime").innerHTML = str;
		} else{
			document.getElementById("ttime").innerHTML = "";
		}
	}
</script>

<script>
	function addPlayer(cnt) {
		if (cnt != 0) {
			var str = "<input type='text' name='player1', id='player1' value='${s_id }' readonly><br>";
			for (n = 2; n <= cnt; n++) {
	            var player= "player" + n;
				str += "<input type='text' name='" + player + "' id='" + player + "' placeholder='" + player + "' readonly><input type='button' name='search' value='검색' onclick='searchID(\"" + player + "\")'><br>";
			}
			str += "<input type='hidden' name='memcnt' id='memcnt' value='" + cnt + "'>";
			document.getElementById("mtable").innerHTML = str;
		} else {
			document.getElementById("mtable").innerHTML = "";
		}
	}
</script>

<script>
// php 변수 wr_sido와 wr_gugun에 저장된 값을 기본 선택값으로 초기화한다.
// wr_sido와 wr_gugun에 저장된 값이 없으면 첫 항목으로 초기화한다.
var swr_sido = "<?=$wr_sido?>"; // php 변수를 자바스크립트 변수로 저장 - 현재 선택된 시도.
var swr_gugun = "<?=$wr_gugun?>"; // php 변수를 자바스크립트 변수로 저장 - 현재 선택된 시군구.
initRegion();
changeRegion(teamCreate);
</script>

<script>
function searchID(player) { 
	var sx = parseInt(screen.width);
	var sy = parseInt(screen.height);
	var x = (sx / 2) + 50;
	var y = (sy / 2) - 25;

	var win = window.open("searchID.do?player=" + player, "idwin", "width=400, height=350");

	win.moveTo(x, y);
}

function selectPcode() {
	var sx = parseInt(screen.width);
	var sy = parseInt(screen.height);
	var x = (sx / 2) + 50;
	var y = (sy / 2) - 25;

	var win = window.open("teamCreate.do", "idwin", "width=400, height=350");

	win.moveTo(x, y);

} //selectPcode

function teamCheck(f){		
	var wr_sido = f.wr_sido.value;		
	var wr_gugun = f.wr_gugun.value;	
	if(wr_sido=='시도선택'||wr_gugun=='시군구선택'){
		alert("경기희망지역을 선택하세요.");	
		return false;		
	}
	var player = f.player.value;	
	if(player=='0'){
		alert("팀 인원을 선택하세요.");	
		return false;		
		}		
	return true;	
	}
</script>
		</div>
	</div>
</div>


<!-- 본문 끝 -->
<jsp:include page="../footer2.jsp"></jsp:include>