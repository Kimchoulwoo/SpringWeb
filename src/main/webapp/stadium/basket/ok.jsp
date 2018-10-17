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
			<th> 장바구니에 등록 되었습니다. </th>
		</tr>
		<tr>
			<th>장바구니로 이동하시겠습니까?</th>
		</tr>
		
		<tr>
			<td>
				<a href="../stadium/stalist.do"><input type='button' value='구장 목록'></a>
				<a href="./basketlist.do"><input type='button' value='장바구니 가기'></a>
			</td>
		</tr>

	</table>



</div>

