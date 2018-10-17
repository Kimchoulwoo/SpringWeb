<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../../stadium/header.jsp"></jsp:include>
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

function Today(year,mon,day){
     if(year == "null" && mon == "null" && day == "null"){       
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
}
</script>
<div id="content">
	<jsp:include page="stareadTemlate.jsp"></jsp:include>
	<!-- 구장 정보 -->
	<FORM name="test" action="../basket/basketRV.do?stacode=${stacode}&grcode=${grcode}"
		onsubmit="return cost(this)"><input type="hidden"
			name="stacode" value="${staRVlist.stacode}"> <input
			type="hidden" name="grcode" value="${grRVlist.grcode}"> <input
			type="hidden" name='pid' value="${s_id}">
		<table width='100%'>
			<tr>
				<th colspan='3'>경기장 예약</th>
			</tr>
			<tr>
				<th colspan='3'><hr></th>
			</tr>
			<tr>
				<th width='20%'>● 경기장 이름</th>
				<td colspan='2' width='80%'>${grRVlist.grname }</td>
			</tr>
			<tr>
				<th colspan='3'><hr></th>
			</tr>
			<tr>
				<th>● 예약 가능 날짜</th>
				<td><input type="text" name="pyear" value="${year}" readonly>년
					<input type="text" name="pmonth" value="${month}" readonly>월
					<input type="text" name="pday" value="${day}" readonly>일 
					<input type="button" method="post" value="다른 날짜 보기" onclick="javascript:history.back()"></td>
			</tr>
			<tr>
				<th colspan='3'><hr></th>
			</tr>
			<tr>
				<th>● 예약 가능 시간</th>
				<td colspan='2'>
					<%-- 	<!--  x값 지정(카운터) -->
				<c:set var='x' value='4'/>
				<!-- 0~24까지의 +1 반복값  -->
				<c:forEach var='i' begin="0" end="25">
				<!-- 조건문 i랑 x가 같은때 -->
				<c:if test="${x eq i }">
				<!-- 출력  -->
				<input type="checkbox">${x }
				
				
				
				<!-- 출력 후의 x카운터 +1 -->
				<c:set var='x' value='${x+1 }'/>
				</c:if>
				</c:forEach>
				
				 --%> <!--  zz에다가 grtime을 담는다 ex) grtime이 13,14,15 일때 ${zz}를 출력하면 13,14,15출력 -->
					<c:set var='zz' value='${grRVlist.grtime }' /> <c:if
						test="${grRVlist.grtime ne null}">
						<!-- x에 배열을 만드는데 zz값 안에 ,을 기준으로 하나씩 담아서 반복 출력 -->
						<!-- ex) zz에 13,14,15가 있으면 1번에 13 2번에 14 3번에 15가 담긴 후 그것을 반복 출력 -->
						<c:forEach var='x' items="${fn:split(zz,',') }">
							<!-- 값이 있을때 마다 name이 ptime인 체크 박스를 만든다. -->
							<input type="checkbox" name="ptime" value="${x }">${x }:00
				</c:forEach>
					</c:if> <c:if test="${grRVlist.grtime eq null}">
				해당 날짜는 이미 예약이 모두 완료 되었습니다.
				</c:if>
				</td>
			</tr>
			<tr>
				<th colspan='3'><hr></th>
			</tr>
			<tr>
				<td colspan='2'><hr></td>
				<td>가격 : <input type="text" readonly="readonly" id="payresult"
					name='cost'>원
				</td>
			</tr>
			<tr>
				<th colspan='3'><hr></th>
			</tr>
			<tr>
				<td colspan='2' width='80%'></td>
				<td width='20%'><input type="button" value="구장목록"
					onclick="location.href='stalist.do'"> <input type='submit'
					value='예약 하기'></td>
			</tr>
		</table>
	</FORM>
</div>
</div>
<
<script>
	function cost(stapay) {
		//alert (c)
		//alert(f.rvtime[0].checked);

		var cnt = 0;
		var len = f.ptime.length;
		for (a = 0; a < len; a++) {
			if (f.ptime[a].checked == true) {
				cnt++;

			}
		}

		//alert(cnt);
		if (cnt == 0) {
			alert("예약 가능 시간을 선택해 주세요");
			return false;
		} else {
			alert(cost);
			document.test.value = cnt;
		}
	}

	var pay = "${staRVlist.stapay}";
	var count = 0;

	$(document).on(
			"change",
			"input[name='ptime']",
			function() {
				if ($(this).attr("checked") == null) {
					$(this).attr("checked", "checked");
				} else {
					$(this).attr("checked", null);
				}
				count = 0;

				/* alert($("#payparent").find("input[name='ptime']").val()) ; */
				for (var i = 0; i < $(this).parents("div").find(
						"input[name='ptime']").length; i++) {

					if ($(this).parents("div").find("input[name='ptime']")
							.eq(i).attr("checked") == "checked") {
						count++;
					}

				}

				$("#payresult").val(pay * count);
			})
</script>