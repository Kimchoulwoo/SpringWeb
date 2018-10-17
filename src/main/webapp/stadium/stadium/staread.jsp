<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../../stadium/header.jsp"></jsp:include>
<style>
#content {
	top: 50px;
	left: 5%;
	right: 5%;
	width: 90%;
	height: 150%;
	align: center;
	margin:auto;
}

.subject {
	font-size: 50px;
	align: center;
}
</style>

<div id="content">
<div>
	<table border='1' width='100%'>
		<tr>
			<td width='20%'></td>
			<th class='subject'>★${stareaddto.staname }구장 상세보기★</th>
			<td width='20%'></td>
		</tr>
	</table>

	<table border='1' width='100%'>
		<tr>
			<c:if test="${!empty sstareaddto.poster1  ne null }">
				<th rowspan='6' width='40%'><img
					src="../../admin/stadium/storage/${stareaddto.poster1 }"
					width='100%'></th>
			</c:if> 
			<th width='10%'>구장 이름</th>
			<td width='20%'>${stareaddto.staname }</td>
			<th width='10%'>대여 가격</th>
			<td width='20%'>${stareaddto.stapay }원</td>
		</tr>
		<tr>
			<th>구장 주소</th>
			<td colspan='3'>${stareaddto.staaddr1 }${stareaddto.staaddr2 }
				${stareaddto.staaddr3 }</td>
		</tr>
		<tr>
			<th>구장 정보</th>
			<td colspan='3'>${stareaddto.stainfo }</td>
		</tr>
		<tr>
			<th>구장 개장</th>
			<td colspan='3'>${stareaddto.staopen }:00~ ${stareaddto.staclose }:00</td>
		</tr>
		<tr>
			<th>구장 등록일</th>
			<td>${stareaddto.stadate }</td>
			<td><hr></td>
			<td><hr></td>
		</tr>
		<tr>
			<th width='10%'>구장 평점</th>
			<td colspan='3' width='50%'><c:set var="x"
					value="${totalpoint }" /> <c:choose>
					<c:when test="${x eq 0 }">
						평가가 되지 않았습니다.
						</c:when>
					<c:when test="${x < 0.5 }">
						<img src="../../images/0.png" width='100%'>
					</c:when>
					<c:when test="${x < 1 }">
						<img src="../../images/0.5.png" width='100%'>
					</c:when>
					<c:when test="${x < 1.5 }">
						<img src="../../images/1.png" width='100%'>
					</c:when>
					<c:when test="${x < 2 }">
						<img src="../../images/1.5.png" width='100%'>
					</c:when>
					<c:when test="${x < 2.5 }">
						<img src="../../images/2.png" width='100%'>
					</c:when>
					<c:when test="${x < 3 }">
						<img src="../../images/2.5.png" width='100%'>
					</c:when>
					<c:when test="${x < 3.5 }">
						<img src="../../images/3.png" width='100%'>
					</c:when>
					<c:when test="${x < 4 }">
						<img src="../../images/3.5.png" width='100%'>
					</c:when>
					<c:when test="${x < 4.5 }">
						<img src="../../images/4.png" width='100%'>
					</c:when>
					<c:when test="${x < 5 }">
						<img src="../../images/4.5.png" width='100%'>
					</c:when>
					<c:when test="${x eq 5 }">
						<img src="../../images/5.png" width='100%'>
					</c:when>

				</c:choose></td>
		</tr>
		<tr>
			<td colspan='5'><hr></td>
		</tr>
	</table>
	<!-- 경기장 관련 -->

	<!-- ------------------------------------------------------------------------ -->
		<table style="width:90%; margin:20px;">
			<tr>
				<th colspan='4'>★${stareaddto.staname }구장의 경기장 목록★</th>
			</tr>
			<tr>
				<th colspan='4'><hr></th>
			</tr>
			<tr>
				<th>경기장 이름</th>
				<th>예약 가능 날짜</th>
				<th>예약 가능 시간</th>
				<td></td>
			</tr>
			<tr>
				<th colspan='4'><hr></th>
			</tr>
			<c:forEach var="grdto" items="${groundlist }">
				<c:if test="${grdto.grlevel eq 'Y'}">
					<tr>
						<td>${grdto.grname }</td>
						<td><c:choose>
								<c:when test="${grdto.grday eq 'all Week'}">
							항시 개장
							</c:when>
							</c:choose></td>
						<td>${grdto.grtime }</td>
						<td><a
							href="./groundRV.do?grcode=${grdto.grcode }&stacode=${stareaddto.stacode}">
								<input type='button' value='경기장 예약'>
						</a></td>
					</tr>
					<tr>
						<th colspan='4'><hr></th>
					</tr>
				</c:if>
			</c:forEach>
			<c:if test="${!empty stareaddto.poster2 }">
				<tr>
					<th><img
						src="../../admin/stadium/storage/${stareaddto.poster2 }"
						width="100%"></th>
				</tr>
			</c:if>


			<c:if test="${!empty stareaddto.poster3 }">
				<tr>
					<th><img
						src="../../admin/stadium/storage/${stareaddto.poster3 }"
						width="100%"></th>
				</tr>
			</c:if>



			<c:if test="${!empty stareaddto.poster4 }">
				<tr>
					<th><img
						src="../../admin/stadium/storage/${stareaddto.poster4 }"
						width="100%"></th>
				</tr>
			</c:if>


			<c:if test="${!empty stareaddto.poster5 }">
				<tr>
					<th><img
						src="../../admin/stadium/storage/${stareaddto.poster5 }"
						width="100%"></th>
				</tr>
			</c:if>




			<tr>
				<td colspan='3'></td>
				<td><input type="button" value="구장목록"
					onclick="location.href='./stalist.do'"></td>
			</tr>

		</table>
		</div>
</div>
<!-- 본문 끝 -->
<jsp:include page="../footer.jsp"></jsp:include>

