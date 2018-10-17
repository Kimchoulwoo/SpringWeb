<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../header.jsp" %>

<script src="../js/member.js"></script>
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
function Today(year,mon,day){
     if(year == "null" && mon == "null" && day == "null"){       
     today = new Date();
     this_year=today.getFullYear();
     this_month=today.getMonth();
     this_month+=1;
     if(this_month <10) this_month="0" + this_month;
 
     this_day=today.getDate();
     if(this_day<10) this_day="0" + this_day;     
 }
 else{  
     var this_year = eval(year);
     var this_month = eval(mon); 
     var this_day = eval(day);
     }
 
  montharray=new Array(31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31); 
  maxdays = montharray[this_month-1]; 
//아래는 윤달을 구하는 것
  if (this_month==2) { 
      if ((this_year/4)!=parseInt(this_year/4)) maxdays=28; 
      else maxdays=29; 
  } 
 
 document.writeln("<select name='year' size=1 onChange='dateSelect(this.form,this.form.month.selectedIndex);'>");
     for(i=this_year-100;i<this_year+1;i++){//현재 년도에서 과거로 100년부터 현재까지를 표시함
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
 
 document.writeln("<select name='date' size=1>");
     for(i=1;i<=maxdays;i++){ 
         if(i<10){
             if(i==this_day) document.writeln("<OPTION VALUE=0" +i+ " selected >0"+i); 
             else document.writeln("<OPTION VALUE=0" +i+ ">0"+i); 
         }
         
         else{
             if(i==this_day) document.writeln("<OPTION VALUE=" +i+ " selected } >"+i); 
             else document.writeln("<OPTION VALUE=" +i+ ">" +i);  
         }                     
    }         
 document.writeln("</select>일"); 
   
}
</script>
<!-- 본문 시작 -->
<br><br>
<form method="post" action="./join.do" onsubmit="return memberCheck(this)">
<br>
<table border=1 style="margin:auto;">
<tr>
	<th>아이디</th>
	<td>
      <input type="text" name="id" id="id" size="15"  readonly>
      <input type="button" value="ID중복확인" onclick="idCheck()">	
	</td>
</tr>
<tr>
	<th>비밀번호</th>
	<td><input type="password" name="pw" size="15" required></td>
</tr>
<tr>
	<th>비밀번호 확인</th>
	<td><input type="password" name="repw" size="15" required></td>
</tr>
<tr>
	<th>이름</th>
	<td><input type="text" name="name" size="15" required></td>
</tr>
<tr>
	<th>생일</th>
	<td colspan=3>
	 <script> Today('null','null','null'); </script>
    </td>

</tr>
    <tr>
	<th>휴대폰번호</th>
	<td><input type="text" name="tel" size="15"></td>
</tr>
<tr>
	<th>이메일</th>
	<td>
      <input type="text" name="mail" size="30"  required> 
	</td>
</tr>

<tr>
	<th>우편번호</th>
	<td>
      <input type="text" name="zipcode" id="zipcode" size="34" maxlength="7" readonly>
      <input type="button" value="주소찾기" onclick="DaumPostcode()">	
	</td>
</tr>
<tr>  
  <th>주소</th>
  <td><input type="text" name="address1" id="address1" size="45" required></td>
</tr>
<tr>  
  <th>나머지주소</th>
  <td><input type="text" name="address2" id="address2" size="45"></td>
</tr>

<tr>
	<td colspan="2">
		<input type="submit" value="회원가입"/>
		<input type="reset"  value="취소" onclick="location.href='../'"/>
	</td>
</tr>
</table>

<!-- ----- DAUM 우편번호 API 시작 ----- -->
<div id="wrap" style="display:none;border:1px solid;width:500px;height:300px;margin:5px 110px;position:relative">
  <img src="//i1.daumcdn.net/localimg/localimages/07/postcode/320/close.png" id="btnFoldWrap" style="cursor:pointer;position:absolute;right:0px;top:-1px;z-index:1" onclick="foldDaumPostcode()" alt="접기 버튼">
</div>

<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<script>
    // 우편번호 찾기 화면을 넣을 element
    var element_wrap = document.getElementById('wrap');

    function foldDaumPostcode() {
        // iframe을 넣은 element를 안보이게 한다.
        element_wrap.style.display = 'none';
    }

    function DaumPostcode() {
        // 현재 scroll 위치를 저장해놓는다.
        var currentScroll = Math.max(document.body.scrollTop, document.documentElement.scrollTop);
        new daum.Postcode({
            oncomplete: function(data) {
                // 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

                // 각 주소의 노출 규칙에 따라 주소를 조합한다.
                // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
                var fullAddr = data.address; // 최종 주소 변수
                var extraAddr = ''; // 조합형 주소 변수

                // 기본 주소가 도로명 타입일때 조합한다.
                if(data.addressType === 'R'){
                    //법정동명이 있을 경우 추가한다.
                    if(data.bname !== ''){
                        extraAddr += data.bname;
                    }
                    // 건물명이 있을 경우 추가한다.
                    if(data.buildingName !== ''){
                        extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                    }
                    // 조합형주소의 유무에 따라 양쪽에 괄호를 추가하여 최종 주소를 만든다.
                    fullAddr += (extraAddr !== '' ? ' ('+ extraAddr +')' : '');
                }

                // 우편번호와 주소 정보를 해당 필드에 넣는다.
                document.getElementById('zipcode').value = data.zonecode; //5자리 새우편번호 사용
                document.getElementById('address1').value = fullAddr;

                // iframe을 넣은 element를 안보이게 한다.
                // (autoClose:false 기능을 이용한다면, 아래 코드를 제거해야 화면에서 사라지지 않는다.)
                element_wrap.style.display = 'none';

                // 우편번호 찾기 화면이 보이기 이전으로 scroll 위치를 되돌린다.
                document.body.scrollTop = currentScroll;
                
                $('#address2').focus();
            },
            // 우편번호 찾기 화면 크기가 조정되었을때 실행할 코드를 작성하는 부분. iframe을 넣은 element의 높이값을 조정한다.
            onresize : function(size) {
                element_wrap.style.height = size.height+'px';
            },
            width : '100%',
            height : '100%'
        }).embed(element_wrap);

        // iframe을 넣은 element를 보이게 한다.
        element_wrap.style.display = 'block';
    }
</script>
<!-- ----- DAUM 우편번호 API 종료----- -->
</form>
<!-- 본문 끝 -->
<%@ include file="../footer.jsp" %>