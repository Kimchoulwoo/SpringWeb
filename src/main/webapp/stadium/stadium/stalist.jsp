<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../header.jsp"></jsp:include>
<!-- 본문 시작 -->
<style>
#subject {
	width: 86%;
	margin-right: 7%;
	margin-left: 7%;
}

.worrning {
	color: red;
}
</style>
<div id='subject'>
	<table>
		<tr>
			<th colspan='7'>● 구장 리스트</th>
		</tr>
		<tr>
			<td colspan='7'><hr></td>
		</tr>
	</table>
	<c:if test="${list eq null }">
		<div>
			<div
				style="float: left; width: 300px; height: 600px; margin-right: 20px">
				등록된 구장이 없습니다.</div>
		</div>
	</c:if>
	<c:if test="${stadto.stalevel eq 'Y'}">

		<c:forEach var="stadto" items="${list }">
			<table width='100%' border='1'>
				<tr>

					<td rowspan='6' width='30%'><img
						src="../../admin/stadium/storage/${stadto.poster1 }" width='100%'></td>
					<td width='10%' rowspan='2'>구장 이름</td>
					<td width='30%' colspan='3'><a
						href="./staread.do?stacode=${stadto.stacode }">${stadto.staname }</a></td>
					<!-- <td width='25%' colspan='2'>구장 평가 점수</td> -->
				</tr>
				<tr>
					<td class='worrning' colspan='3' height='3%' >※구장 이름을 클릭시 상세보기 가능함</td>
					<%--<td colspan='2'>${rating.stacode }/${rating.staaccess }/${rating.staclean }/${rating.stasafety }/${rating.stafacility }</td>--%>
				</tr>
				<tr>
					<td>구장주 아이디</td>
					<td>${stadto.id }</td>
					<td>대여 가격</td>
					<td>${stadto.stapay }원</td>
				</tr>
				<tr>
					<td>주소</td>
					<td colspan='3'>${stadto.staaddr1 }${stadto.staaddr2 }
						${stadto.staaddr3 }</td>
				</tr>
				<tr>
					<td>개장 시간</td>
					<td colspan='3'>${stadto.staopen }:00~ ${stadto.staclose }:00</td>
				</tr>
				<tr>
					<td>구장 등록일</td>
					<td colspan='3'>${stadto.stadate }</td>
				</tr>

			</table>
			<hr>
		</c:forEach>


	</c:if>
	<table style="margin: auto">
		<tr>
			<td>${paging}</td>
		</tr>
	</table>
	<!-- 검색 -->
	<form method="post" action="./stalist.do"
		onsubmit="return search(this)">
		<select name="col">
			<option value="staname">구장 이름
			<option value="staaddr1">구장 위치(시 검색)
		</select> <input type="text" size='10' name="word"> <input
			type="submit" value="검색">
	</form>

</div>
<!-- 검색 끝 -->
<script>
	function search(f) {
		var word = f.word.value;
		word = word.trim();
		if (word.length == 0) {
			alert("검색어를 입력하세요!");
			f.word.focus();
			return false;
		} else {
			return true;
		}//end
	}//search() end
</script>

<!-- 본문 끝 -->
<jsp:include page="../footer.jsp"></jsp:include>