<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="../leftside.jsp"></jsp:include>

<!-- 본문 시작 -->
<h4>
	<small>공지사항 상세보기</small>
</h4>
<hr>
<table border='1px'>
	<tr>
		<td>${dto.subject }</td>
		<c:set var="regdt" value="${dto.getRegdt() }"></c:set>
		<td>${fn:substring(regdt,0,10) }</td>
	</tr>
	<tr >
		<td colspan='2'>관리자</td>
	</tr>
	<tr>
		<td colspan='2'>
			${content }
		</td>
	</tr>
	<tr>
		<td colspan='2'>
			<input type="button" value="수정" onclick="onAction(0)">
			<input type="button" value="삭제" onclick="onAction(1)">
		</td>
	</tr>
</table>
<script>
function onAction(value){
	if(value==0){
		var message = "공지사항을 수정하시겠습니까?";
		if(confirm(message)){
			location.href='./update.do?noticeno=${dto.noticeno }';
		}
	}else{
		var message = "공지사항을 삭제하시겠습니까?";
		if(confirm(message)){
			location.href='./delete.do?noticeno=${dto.noticeno }';
		}
	}//if end
}

</script>
<!-- 본문 끝 -->
<jsp:include page="../footer.jsp"></jsp:include>