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
	right : 7%;
	width: 100%;
	height: 92%;
	align: center;
}
</style>
<div id="content">
	
	<table>
		<tr>
			<th> 결제 되었습니다. </th>
		</tr>
		<tr>
			<th>결제 페이지로 이동하시겠습니까?</th>
		</tr>
		<tr>
			<td>
				<a href="../stadium/stalist.do"><input type='button' value='구장 목록 보기'></a>
				<a href="./paymentlist.do?pid=${s_id }"><input type='button' value='결제목록가기'></a>
			</td>
		</tr>

	</table>



</div>

