<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<jsp:include page="../header2.jsp"></jsp:include>
<!-- 본문 시작 template.jsp -->
<center>
	<h3>나의 경기내역</h3>
</center>
<form>
	<table border="1" style="margin: auto; text-align: center;">
		<tr style="font-weight: bold">
			<td width="300">일자</td>
			<td width="300">팀 이름</td>
			<td width="300">상세보기</td>
		</tr>
		<c:forEach var="mlist" items="${mlist}">
			<tr>
				<td>${fn:substring(mlist.ttime, 0, 4)}년
					${fn:substring(mlist.ttime, 4, 6)}월 ${fn:substring(mlist.ttime, 6, 8)}일
				</td>
				<td>${mlist.tname }</td>
				<c:if test="${mlist.input=='n' }">
					<c:if test="${mlist.tid==sessionScope.s_id }">
						<td><a href="javascript:insert(${mlist.mcode });">[결과 입력]</a>
						</td>
					</c:if>
					<c:if test="${mlist.tid!=sessionScope.s_id }">
						<td>입력 대기중</td>
					</c:if>
				</c:if>
				<c:if test="${mlist.input=='y' }">
					<td><a href="./matchresult.do?mcode=${mlist.mcode }">[보기]</a>
					</td>
				</c:if>
			</tr>
		</c:forEach>
	</table>
	<br>
	<br>
	<br>
	<center>
		<h3>나의 평가내역(평균 : <fmt:formatNumber value="${totalavg }" pattern=".0"/>)</h3>
	</center>
	<table border="1" style="margin: auto; text-align: center;">
		<tr style="font-weight: bold">
			<td>작성자</td>
			<td>슈팅</td>
			<td>패스</td>
			<td>지구력</td>
			<td>인성</td>
			<td>평균</td>
			<td width="300">한줄평</td>
		</tr>
		<c:forEach var="myavg" items="${myavg }">
		<tr>
			<c:if test="${!empty myavg }">
				<td width="100">${myavg.writer }</td><td width="100">${myavg.shooting }</td><td width="100">${myavg.pass }</td><td width="100">${myavg.stamina }</td><td width="100">${myavg.manner }</td><td width="100"><fmt:formatNumber value="${myavg.avg }" pattern="0.0"/></td><td width="300">${myavg.comment }</td>
			</c:if>
			<c:if test="${empty myavg }">
				<td colspan="6">입력된 평가정보가 없습니다.</td>
			</c:if>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="7">${paging}</td>
		</tr>
	</table>
</form>
<script>
	function insert(mcode) {
		var sx = parseInt(screen.width);
		var sy = parseInt(screen.height);
		var x = (sx / 2) + 50;
		var y = (sy / 2) - 25;

		var win = window.open("insertresult.do?mcode=" + mcode, "idwin",
				"width=400, height=350");

		win.moveTo(x, y);
	}
</script>
<jsp:include page="../footer2.jsp"></jsp:include>

