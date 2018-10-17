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
	<FORM name="test" method="post" action="./grounddate.do?grcode=${grRVlist.grcode }&stacode=${staRVlist.stacode}"
		onsubmit="return cost(this)">
		<input type="hidden" name="rvpay"> <input type="hidden"
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
				<td><script> Today('null', 'null', 'null'); </script><input type='submit' value='해당 날짜 시간 보기'></td>
			</tr>
			<tr>
				<th colspan='3'><hr></th>
			</tr>
			
			<tr>
				<th colspan='3'><hr></th>
			</tr>
			<tr>
				<td colspan='2' width='80%'></td>
				<td width='20%'>
				<input type="button" value="구장목록" onclick="location.href='stalist.do'">
				</td>
			</tr>
		</table>
	</FORM>
</div>
</div>

<script>
	/* 
	function afterDate(year, month, day) {
		alert(year+month+day);
		return false;
	}
	 */

	function cost(f) {
		//alert (c)
		//alert(f.rvtime[0].checked);

		var cnt = 0;
		var costpay = 0;
		var stapay = 35000;
		var len = f.rvtime.length;
		for (a = 0; a < len; a++) {
			if (f.rvtime[a].checked == true) {
				cnt++;

			}
		}

		costpay = (stapay) * cnt;
		//alert(cnt);
		if (cnt == 0) {
			alert("예약 가능 시간을 선택해 주세요");
			return false;
		} else {
			document.test.rvpay.value = costpay;
		}
	}
</script>
<!-- 본문 끝 -->
<jsp:include page="../footer.jsp"></jsp:include>