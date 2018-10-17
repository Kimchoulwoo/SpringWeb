<%@page import="java.util.Date"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>결제정보</title>
</head>
<body>
<form name="pcodeList" method="get">
<table>
 <tr>
  <th>결제코드</th>
 </tr>
 <tr>
  <td>
   <select name="kim"  onchange="selected(this.value)">
	<option value="a" selected="selected" >결제코드를 선택하세요.</option>
	<option value="0">결제코드 없음</option>
   <c:forEach var="plist" items="${plist}">
   <c:set var="now" value="<%=new Date() %>"/>
   <c:set var="today" ><fmt:formatDate value="${now }" pattern="yyyyMMddhh" /></c:set>
	<c:if test="${plist.pdate >today }">
		<option value = "${plist.pcode}">${plist.pcode }</option>
	</c:if>
   </c:forEach>
   </select>
   <input type="hidden" name="p_code">
  </td>
  </tr>
  </table>
  <input type="button" value="확인" onclick="javascript:pageChange();"/> 
  <input type="button" value="취소" onclick="javascript:window.close();"/>
   <br>*결제코드가 없는 팀은 매칭신청 받기만 가능합니다.
</form>
</body>
<script>
function selected(val){
	document.pcodeList.p_code.value=val;
}

function pageChange(){
	
	var kim = document.pcodeList.kim.value;
	if(kim!="a"){
	  var pcode=document.pcodeList.p_code.value;
      opener.parent.location.replace("../team/teamForm.do?pcode="+pcode);
 	  self.close();
   }else{
	   alert("결제코드를 선택해주세요")
   }
}
</script>
</html>