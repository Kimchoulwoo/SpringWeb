<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../leftside.jsp"></jsp:include>
<!-- 본문 시작 -->
<h4>
	<small>회원관리</small>
</h4>
<hr>
<table border="1">
	<tr>
		<th>아이디</th>
		<th>비밀번호</th>
		<th>이름</th>
		<th>전화번호</th>
		<th>클럽</th>
		<th>등급</th>
		<th>회원탈퇴</th>
	</tr>
<c:forEach var="dto" items="${list }">
	<tr>
		<td>${dto.getId() }</td>
		<td>${dto.getPw() }</td>
		<td>${dto.getName() }</td>
		<td>${dto.getTel() }</td>
		<td>${dto.getCcode() }</td>
		<td>
			<form action="memLevelProc.do">
			<input type="hidden" name="id" value="${dto.getId() }">
			<select name="mlevel" onchange="levelChange(this.form)">
				<option value="A" <c:if test="${dto.getMlevel() eq 'A'}">selected</c:if>>관리자</option>
				<option value="B" <c:if test="${dto.getMlevel() eq 'B'}">selected</c:if>>구장주</option>
				<option value="C" <c:if test="${dto.getMlevel() eq 'C'}">selected</c:if>>클럽장</option>
				<option value="D" <c:if test="${dto.getMlevel() eq 'D'}">selected</c:if>>일반회원</option>
				<option value="E" <c:if test="${dto.getMlevel() eq 'E'}">selected</c:if>>탈퇴회원</option>
			</select>
			</form>
		</td>
		<td>
			<a href="memDelProc.do?id=${dto.getId() }" onclick="deleteMember()">[회원삭제]</a>
		</td>
	</tr>
</c:forEach>
	<tr>
		<td colspan='7'>${paging }</td>
	</tr>
	<tr>
	<td colspan='7' align="right">
			<form method="post" action="./memlist.do" onsubmit="return search(this)">
				<select name="col">
					<option value="id" selected>아이디
					<option value="name">이름
					<option value="mlevel">등급
				</select>
				<input type="text" size='10' name="word">
				<input type="submit" value="검색">
			</form>
	</td>
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