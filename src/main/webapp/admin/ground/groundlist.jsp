<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../leftside.jsp"></jsp:include>


<html>
<head>
<meta charset="UTF-8">
<title>media/list.jsp</title>
</head>
<body>
	<div>
		<table width='100%'>
			<tr>
				<th colspan='3'>경기장 목록</th>
			</tr>
			<tr>
				<th colspan='3'><hr></th>
			</tr>
			<tr>
				<th>● 경기장 이름</th>
				<th>● 예약 가능 날짜</th>
				<th>● 예약 가능 시간</th>
			</tr>
			<tr>
				<th colspan='3'><hr></th>
			</tr>
			<c:forEach var="grdto" items="${grlist }">
				<c:if test="${grdto.grlevel eq 'Y'}">
					<tr>
						<td>${grdto.grname }</td>
						<td><c:choose>
								<c:when test="${grdto.grday eq 'all Week'}">
							항시 개장
							</c:when>
							</c:choose></td>
						<td>
								${grdto.grtime}
								</td>
						<td><input type="button" value="수정"
							onclick="location.href='groundupdateForm.do?grcode=${grdto.grcode}&stacode=${grdto.stacode}'">
							<input type="button" value="삭제"
							onclick="return groundDel(${grdto.grcode})">
						</td>
					</tr>
					<tr>
						<th colspan='3'><hr></th>
					</tr>
				</c:if>
			</c:forEach>
			<tr>
				<th colspan='3'><hr></th>
			</tr>
			<tr>
				<td colspan='2'></td>
				<td><input type="button" value="구장 목록"
					onclick="location.href='../stadium/stadiumlist.do'"></td>
		</table>
	</div>
	
	
	
	<script>
	function groundDel(grcode) {
		if (confirm("현재 경기장을 삭제 하시겠습니까?") == true) {
			location.href="grounddelete.do?grcode=" + grcode;
		} else {
			return;
		}
	}
	</script>
	
	
</body>
</html>
