<%@ page contentType="text/html; charset=UTF-8"%>

<%@ include file="../header.jsp" %>
<!-- 본문 -->
	<table border="1" style="margin:auto;">
		<tr>
			<th>글번호</th>
			<td>${dto.bbsno}</td>
		</tr>
		<tr>
			<th>작성자</th>
			<td>${dto.id}</td>
		</tr>
		<tr>
			<th>글제목</th>
			<td>${dto.subject}</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${dto.content}</td>
		</tr>
		<tr>
			<th>조회수</th>
			<td>${dto.readcnt}</td>
		</tr>
		<tr>
			<th>작성일</th>
			<td>${dto.regdt}</td>
		</tr>
	</table>
	<form>
		<input type="hidden" name="id" value="${s_id}">
		<input type="hidden" name="bbsno" value="${dto.bbsno}">
		<input type="hidden" name="col" value="${col}">
		<input type="hidden" name="word" value="${word}">
		<input type="hidden" name="nowPage" value="${nowPage}">
		<input type="button" value="게시판 목록" onclick="move(this.form, './list.do')">
		<c:if test="${s_id ne null}">
			<input type="button" value="답변 쓰기" onclick="move(this.form, './reply.do')">
		</c:if>
		<c:if test="${dto.id eq s_id}">
			<input type="button" value="수정" onclick="bbsUpdate('${dto.bbsno}', '${dto.id}', '${s_id}')">
			<input type="button" value="삭제" onclick="bbsDelete('${dto.bbsno}', '${dto.id}', '${s_id}')">
		</c:if>
	</form>
<script>
function move(f, file) {
	f.action = file;
	f.submit();
}

function bbsUpdate(bbsno, id, s_id) {
	if (id == s_id) {
		location.href="./updateform.do?bbsno=" + bbsno;
	} else {
		alert("게시글 작성자만 수정 가능 합니다.");
		return;
	}
}

function bbsDelete(bbsno, id, s_id) {
	if (id == s_id) {
		if (confirm("게시글을 삭제 합니다.") == true) {
			location.href="./delete.do?bbsno=" + bbsno + "&id=" + id;
		} else {
			return;
		}
	} else {
		alert("게시글 작성자만 삭제 가능 합니다.");
	}
}
</script>
<!-- 본문 끝 -->
<%@ include file="../footer.jsp" %>