<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../leftside.jsp"></jsp:include>
<!-- 본문 시작 -->
<table width='100%'>
	<tr>
		<th colspan='6' width='100%'>구장 리스트</th>
	</tr>
	<tr>
		<td colspan='6'><hr></td>
	</tr>
</table>


<c:if test="${list eq null }">
	<div>
		<div
			style="float: left; width: 300px; height: 600px; margin-right: 20px">
			등록된 구장이 없습니다.</div>
	</div>
</c:if>

<c:if test="${list ne null }">
	<table width='100%'>
		<tr>
			<th colspan='6' width='100%'>※ 구장 이름 클릭시 상세 보기 가능</th>
		</tr>
		<tr>
			<td colspan='6'><hr></td>
		</tr>
		<tr>
			<th>구장 이름</th>
			<th>구장주 아이디</th>
			<th>구장 주소</th>
			<th>대여 가격</th>
			<th>구장 개장시간</th>
			<th>구장 등록일</th>
		</tr>
		<tr>
			<td colspan='6'><hr></td>
		</tr>
		<c:forEach var="stadto" items="${list }">
			<c:if test="${s_mlevel eq '구장주' }">
				<c:if test="${stadto.id eq s_id }">
					<c:if test="${stadto.stalevel eq 'Y'}">
						<tr>
							<td><a href="./stadiumread.do?stacode=${stadto.stacode }">${stadto.staname }</a></td>
							<td>${stadto.id }</td>
							<td>${stadto.staaddr1 }${stadto.staaddr2 }${stadto.staaddr3 }</td>
							<td>시간당 ${stadto.stapay } 원</td>
							<td>${stadto.staopen }:00~${stadto.staclose }:00</td>
							<td>${fn:substring(stadto.stadate, 0, 10)}</td>
						</tr>
						<tr>
							<td colspan='6'><hr></td>
						</tr>
					</c:if>
				</c:if>
			</c:if>
			<c:if test="${s_mlevel eq '관리자' }">
				<!-- 등록 된것들 먼저 -->
				<c:if test="${stadto.stalevel eq 'Y'}">
					<tr>
						<td><a href="./stadiumread.do?stacode=${stadto.stacode }">${stadto.staname }</a></td>
						<td>${stadto.id }</td>
						<td>${stadto.staaddr1 }${stadto.staaddr2 }${stadto.staaddr3 }</td>
						<td>시간당 ${stadto.stapay } 원</td>
						<td>${stadto.staopen }:00~${stadto.staclose }:00</td>
						<td>${fn:substring(stadto.stadate, 0, 10)}</td>
					</tr>
					<tr>
						<td colspan='6'>구장 상태 : 등록 상태</td>
					</tr>
				</c:if>
				
				<!-- 삭제 된것들은 뒤로 -->
				<c:if test="${stadto.stalevel eq 'N'}">
					<tr>
						<td><a href="./stadiumread.do?stacode=${stadto.stacode }">${stadto.staname }</a></td>
						<td>${stadto.id }</td>
						<td>${stadto.staaddr1 }${stadto.staaddr2 }${stadto.staaddr3 }</td>
						<td>시간당 ${stadto.stapay } 원</td>
						<td>${stadto.staopen }:00~${stadto.staclose }:00</td>
						<td>${fn:substring(stadto.stadate, 0, 10)}</td>
					</tr>
					<tr>
						<td colspan='6'>구장 상태 : 삭제 상태</td>
					</tr>
				</c:if>
				
				
				<tr>
					<td colspan='6'><hr></td>
				</tr>
			</c:if>
		</c:forEach>
	</table>
</c:if>
<table width='100%''>
	<tr>
		<td width='80%'></td>
		<td><a href="./stadiumForm.do"><input type="button"
				value="등록"></a></td>
	</tr>
</table>


<table style="margin: auto">
	<tr>
		<td>
			<!-- 관리자 일때 --> <c:if test="${s_mlevel eq '관리자' }">
					${paging}				
			</c:if> <!--  구장주 일때 --> <c:if test="${s_mlevel eq '구장주' }">
						${paging2}
			</c:if>
		</td>
	</tr>
</table>
<%-- 
<table style="margin: auto">
	<tr>
		<td>${paging}</td>
	</tr>
</table>--%>
<!-- 검색 -->
<form method="post" action="./stadiumlist.do"
	onsubmit="return search(this)">
	<select name="col">
		<option value="staname">구장 이름
		<option value="staaddr1">구장 위치(시 검색)
	</select> <input type="text" size='10' name="word"> <input type="submit"
		value="검색">
</form>
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