<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>평점 보기</title>
</head>
<body>
<form>
	<table border="1" style="margin:auto; text-align:center;">
		<tr>
			<th>작성자</th><th>슈팅</th><th>패스</th><th>지구력</th><th>인성</th><th>평균</th><th>한줄평</th>
		</tr>
		<c:forEach var="playeravg" items="${playeravg }">
		<tr>
			<c:if test="${!empty playeravg }">
			<td>${playeravg.writer }</td><td>${playeravg.shooting }</td><td>${playeravg.pass }</td><td>${playeravg.stamina }</td><td>${playeravg.manner }</td><td><fmt:formatNumber value="${playeravg.avg }" pattern=".0"/></td><td width="300">${playeravg.comment }</td>
			</c:if>
			<c:if test="${empty playeravg }">
			<td colspan="6">입력된 평가정보가 없습니다.</td>
			</c:if>
		</tr>
		</c:forEach>
	</table>
</form>

</body>
</html>