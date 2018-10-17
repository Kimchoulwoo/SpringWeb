<%@ page contentType="text/html; charset=UTF-8"%>

<%@ include file="../header.jsp" %>
<!-- 본문 -->
	<div>게시글 보기</div>
	<form>
	<table border="1">
		<tr>
			<th>글번호</th>
			<td>${ntc.noticeno}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>관리자</td>
		</tr>
		<tr>
			<th>글제목</th>
			<td>${ntc.subject}</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${ntc.content}</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${ntc.readcnt}</td>
		</tr>
		<tr>
			<th>작성일</th>
			<td>${ntc.regdt}</td>
		</tr>
	</table>
	<form>
		<input type="hidden" name="id" value="${s_id}">
		<input type="button" value="게시판 목록" onclick="move(this.form, './list.do')">
	</form>
<script>
function move(f, file) {
	f.action = file;
	f.submit();
}
</script>
<!-- 본문 끝 -->
<%@ include file="../footer.jsp" %>
