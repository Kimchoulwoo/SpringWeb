<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="../leftside.jsp"></jsp:include>
<!-- 본문 시작 -->
<h4>
	<small>구장관리</small>
</h4>
<hr>
<table border="1">
	<tr>
		<th>구장코드</th>
		<th>구장이름</th>
		<th>주소</th>
		<th>가격</th>
		<th>전화번호</th>
		<th>오픈</th>
		<th>클로즈</th>
	</tr>
	<c:forEach var="dto" items="${list }">
	<tr>
		<td>${dto.getStacode() }</td>
		<td>${dto.getStaname() }</td>
		<td>${dto.getStaaddr() }</td>
		<td>${dto.getStapay() }</td>
		<td>${dto.getStatel() }</td>
		<td>${dto.getStaopen() }</td>
		<td>${dto.getStaclose() }</td>
	</tr>
</c:forEach>
	<tr>
		<td colspan='7'>${paging }</td>
	</tr>
	<tr>
	<td colspan='3'>
		<form action="./stalist.do?sort=${sort }">
			<select name="sort" onchange="fnsort(this.form)">
				<option value="stacodeAsc" <c:if test="${sort eq '' || sort eq 'stacodeAsc'}">selected</c:if>>구장코드 오름차순</option>
				<option value="stacodeDesc" <c:if test="${sort eq 'stacodeDesc'}">selected</c:if>>구장코드 내림차순</option>
				<option value="stanameAsc" <c:if test="${sort eq 'stanameAsc'}">selected</c:if>>구장이름 오름차순</option>
				<option value="stanameDesc" <c:if test="${sort eq 'stanameDesc'}">selected</c:if>>구장이름 내림차순</option>
			</select>
		</form>
	</td>
	<td colspan='4' align="right">
			<form method="post" action="./stalist.do" onsubmit="return search(this)">
				<select name="col">
					<option value="stacode" selected>구장코드
					<option value="staname">구장이름
				</select>
				<input type="text" size='10' name="word">
				<input type="submit" value="검색">
			</form>
		</td>
	</tr>
	<tr>
		<td colspan='7'><input type="button" value="글쓰기" onclick="location.href='./create.do'"></td>
	</tr>
</table>
<script>
	function levelChange(f) {
		var message = "회원등급을 변경할까요?";
		if (confirm(message)) {
			f.submit();
		}
	}//levelChange() end
	
	function deleteMember() {
		var message = "회원을 삭제할까요?";
		if (confirm(message)) {
			submit();
		}
	}//deleteMember() end
	
	function fnsort(f) {
		f.submit();
	}//sort() end

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