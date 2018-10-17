<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
  <meta charset="UTF-8">
  <title>cmemList.jsp</title>
</head>
<body>
<table>
 <tr>
  <th>클럽원 리스트</th>
 </tr>
  <c:forEach var="cmemlist" items="${list}">
	<tr>
	 <td>
	  ${cmemlist.cid } <input type="button" value="선택" onclick='javascript:apply("${cmemlist.cid }","${player }")'>
	 </td>
	</tr>
  </c:forEach> 
</table>
<script>
function apply(id,player){
	opener.document.getElementById(player).value=id;
	window.close();
}
</script>

</body>
</html>