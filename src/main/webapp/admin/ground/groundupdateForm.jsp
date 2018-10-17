<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../leftside.jsp"></jsp:include>
<!-- 본문 시작 -->

<style>

.subject1{
 font-size: 50px;
}
.content1{
 font-size: 30px;
}
.content2{
 font-size: 15px;
}

</style>

<FORM method="post" action="./grupdate.do?grcode=${grUdto.grcode }">

	<table width='100%'>
		<tr>
			<th colspan='3' class='subject1'>경기장 수정</th>
		</tr>
		<tr>
			<th colspan='3'><hr></th>
		</tr>
		<c:if test="${grUdto.grlevel eq 'Y'}">
			<tr>
				<th class='content1'>경기장 이름</th>
				<td><input type='text' name='grname' value="${grUdto.grname }"></td>
				<td></td>
			</tr>
			<tr>
				<th colspan='3'><hr></th>
			</tr>
			<tr>
				<th rowspan='2' class='content1'>경기장 예약 가능 날짜</th>
				<td>현제 예약 가능 날짜 : ${grUdto.grday }</td>
			</tr>
			<tr>
				<td  class='content2'><jsp:include page="daycheck.jsp"></jsp:include></td>
			</tr>
			<tr>
				<th colspan='3'><hr></th>
			</tr>
			<tr>
				<th class='content1'>경기장 예약 가능 시간</th>
				<td class='content2'>
			
					<c:set var="x" value="${stadiuminfo.staopen }" /> 
					<c:set var="y" value="1" /> 
						<c:forEach var="i" begin="${stadiuminfo.staopen }"	end="${stadiuminfo.staclose }">						
							<c:if test="${y%6 eq 0 }">
								<input type="checkbox" name="grtime" value="${x }">${x }:00<br><hr>
							</c:if>
							<c:if test="${y%6 ne 0 }">
								<input type="checkbox" name="grtime" value="${x }">${x }:00
							</c:if>
							<c:set var="y" value="${ y+1 }" />
							<c:set var="x" value="${ x+1 }" />
						</c:forEach>
					
				</td>
			</tr>
		</c:if>
		<tr>
			<th colspan='3'><hr></th>
		</tr>
		<tr>
			<td colspan="3"><input type="submit" value="경기장 수정" /> <input
				type="reset" value="취소"
				onclick="location.href='../stadium/stadiumlist.do'"></td>
		</tr>
		<tr>
			<th colspan='3'><hr></th>
		</tr>
	</table>
</FORM>






<!-- 본문 끝 -->
<jsp:include page="../footer.jsp"></jsp:include>