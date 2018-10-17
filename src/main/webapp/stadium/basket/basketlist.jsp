<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../header.jsp"></jsp:include>
<!-- 본문 시작 -->
<!-- 매칭 매뉴 -->
<style>
.subject {
	font-size: 50px;
}

.subject2 {
	font-size: 30px;
}

.subject3 {
	font-size: 20px;
}

.wornning {
	color: red;
	font-size: 25px;
}

#content {
	position: absolute;
	left: 5%;
	right: 7%;
	top: 50px;
	width: 90%;
	height: 92%;
	align: center;
}
</style>
	<table width='100%'>
		<tr>
			<th colspan='8' class='subject'>장바구니 목록</th>
		</tr>
	</table>
	<c:forEach var="badto" items="${baslist }">
		<c:if test="${badto.bno ne null }">
			<c:choose>
				<c:when test="${badto.pid eq s_id }">
					<table width='100%' border='1'>
						<tr>
							<th class='subject2' width='20%'>장바구니 목록</th>
							<th colspan='3'></th>
							<th width='20%' class='subject3'>가격</th>
						</tr>
						<tr>
							<th class='subject3'>구장 명</th>
							<td class='subject3'>${stadiuminfo.staname }</td>
							<th class='subject3'>경기장 명</th>
							<td class='subject3'>${groundinfo.grname }</td>
							<th rowspan='4' class='subject3'>${badto.cost }원</th>
						</tr>
						<tr>
							<th class='subject3'>아이디</th>
							<td class='subject3'>${badto.pid }</td>
							<th colspan='2'></th>
						</tr>
						<tr>
							<th colspan='4' class='subject2'>예약 정보</th>
						</tr>
						<tr>
							<th class='subject3'>날짜</th>
							<td class='subject3'>${badto.pdate }</td>
							<th class='subject3'>시간</th>
							<td class='subject3'>${badto.ptime }</td>
						</tr>
						<tr>
							<th class='subject3'>장바구니 등록일</th>
							<td colspan='3' class='subject3'>${fn:substring(badto.basketdate, 0, 10)}</td>
							<td><a href=""><input type='button' value='목록삭제'></a>
								<a href="./basketread.do?bno=${badto.bno }&pid=${s_id}"><input
									type='button' value='결제하기'></a></td>
						</tr>
					</table>
					<hr>

				</c:when>
				<c:when test="${s_id == '' }">
					<table border='1'>
						<tr>
							<td class='subject3'>로그인이 되지 않았습니다.<br> 로그인 해주시기 바랍니다.
							</td>
						</tr>
						<tr>
							<td><a href="../../member/login.do"><input type="button"
									value="로그인"></a>
						</tr>
					</table>
				</c:when>
			</c:choose>
		</c:if>


	</c:forEach>

	<table width='100%'>
		<tr>
			<th width='80%'><hr></th>
			<td width='20%'><a href="../stadium/stalist.do"><input
					type="button" value='구장 목록'></a></td>
		</tr>

	</table>



<!-- 본문 끝 -->
<jsp:include page="../footer.jsp"></jsp:include>