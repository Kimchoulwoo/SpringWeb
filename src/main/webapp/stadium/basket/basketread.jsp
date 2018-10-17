<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../header.jsp"></jsp:include>
<!-- 본문 시작 -->
<!-- 매칭 매뉴 -->
<style>
#LEFTmanu {
	position: absolute;
	top: 50px;
	wpidth: 15%;
	height: 82%;
	align: center;
	border: 1;
}

.subject {
	font-size: 50px;
}

.wornning {
	color: red;
}

#content {
	position: absolute;
	top: 50px;
	left: 7%;
	right: 7%;
	width: 100%;
	height: 82%;
	align: center;
}
</style>
<div pid="content">
	<FORM name='frm' method='POST'
		action='../payment/paymentplay.do?bno=${Basketdto.bno }&pid=${s_id}'>

		<table width='100%'>

			<tr>
				<th class='subject'>결제 페이지</th>
			</tr>
			<tr>
				<td><hr></td>
			</tr>
		</table>
		<table width='100%'>

			<tr>
				<th>예약한 날짜</th>
				<th>${Basketdto.pdate}</th>
			</tr>
			<tr>
				<th>예약한 시간</th>
				<th>${Basketdto.ptime}</th>
			</tr>
			<tr>
				<th>예약 결제 가격</th>
				<th>${Basketdto.cost}</th>
			</tr>
		</table>
		<table>
			<tr>
				<td><input type='submit' value='결제'></td>
			</tr>
		</table>
	</form>
</div>



<!-- 본문 끝 -->
<jsp:include page="../footer.jsp"></jsp:include>