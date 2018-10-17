<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../header.jsp"></jsp:include>
<!-- 본문 시작 -->
<!-- 매칭 매뉴 -->
<style>
#LEFTmanu {
	position: absolute;
	top: 50px;
	left: 0px;
	width: 15%;
	height: 92%;
	align: center;
	border: 1;
}

#content {
	position: absolute;
	top: 50px;
	left: 15%;
	width: 85%;
	height: 92%;
	align: center;
}

.subject {
	font-size: 50px;
}

.wornning {
	color: red;
}
</style>
<div id="content">

	<table>
		<tr class='subject'>
			<th>결제 완료 리스트</th>
		</tr>
	</table>
	<hr>


	<c:forEach var="paymentlist" items="${paymentlist }">
		<c:if test="${s_id eq paymentlist.pid }">
			<table width='80%'>
				<tr>
					<td width='20%'>결제코드</td>
					<td width='20%'>${paymentlist.pcode }</td>
					<td width='20%'></td>
					<td width='20%'></td>
					<td width='20%'>결제 가격</td>
				</tr>
				<tr>
					<td>구장 이름</td>
					<td>${stadiuminfo.staname }</td>
					<td>경기장 이름</td>
					<td>${groundinfo.grname }</td>
					<td rowspan='4'>${paymentlist.cost}</td>
				</tr>
				<tr>
					<td>구장 주소</td>
					<td colspan='3'>${stadiuminfo.staaddr1 }
						${stadiuminfo.staaddr2 } ${stadiuminfo.staaddr3 }</td>
				</tr>
				<tr>
					<td>결제일</td>
					<td>${paymentlist.costdate }</td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td>예약 날짜</td>
					<td>${paymentlist.pdate }</td>
					<td>예약 시간</td>
					<td>${paymentlist.ptime }</td>
				</tr>
				<tr>
					<td colspan='4'></td>
					<td>

						<c:if test="${paymentlist.avg eq null }">
							
								<a href="../rating/ratingForm.do?pcode=${paymentlist.pcode }">
									<input	type="button" value='구장 평가'></a>
						</c:if>
	
						</td>
				</tr>
			</table>
			<hr>
		</c:if>

	</c:forEach>
</div>




<!-- 본문 끝 -->
<jsp:include page="../footer.jsp"></jsp:include>