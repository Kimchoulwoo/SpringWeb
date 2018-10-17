<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../leftside.jsp"%>
<!-- 본문 시작 -->

<FORM method="post" action="./adcreate.do">
	<input type='hidden' name='stacode' value='${stacode }'>

	<table width='100%'>
		<tr>
			<th colspan='2'>경기장 등록</th>
		</tr>
		<tr>
			<th colspan='2'><hr></th>
		</tr>
		<tr>
			<th>●경기장 이름</th>
			<td><input type='text' name='grname'></td>
		</tr>
		<tr>
			<th colspan='2'><hr></th>
		</tr>
		<tr>
			<th>●경기장 예약 가능 날짜(3중 택1)</th>
			<td><jsp:include page="daycheck.jsp"></jsp:include></td>
		</tr>
		<tr>
			<th colspan='2'><hr></th>
		</tr>
		<tr>
			<th>●경기장 예약 가능 시간</th>
			<td>
				<c:set var="x" value="${stadiumdto.staopen }" /> 
				<c:forEach var="i" begin="${stadiumdto.staopen }" end="${stadiumdto.staclose }">
						<input type="checkbox" name="grtime" value="${x }">${x }:00
					<c:set var="x" value="${ x+1 }" />
	
				</c:forEach></td>

		</tr>
		<tr>
			<th colspan='2'><hr></th>
		</tr>
		<tr>
			<td colspan="2"><input type="submit" value="등록" /> <input
				type="reset" value="취소" /></td>
		</tr>
		<tr>
			<th colspan='2'><hr></th>
		</tr>
	</table>
</FORM>







<!-- 본문 끝 -->
<%@ include file="../footer.jsp"%>