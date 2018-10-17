<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="../leftside.jsp"></jsp:include>
<!-- 본문 시작 -->
<h4>
	<small>공지사항관리</small>
</h4>
<hr>

<table border="1">
	<tr>
		<th>글번호</th>
		<th>제목</th>
		<th>작성자</th>
		<th>작성일</th>
		<th>조회수</th>
	</tr>
<c:forEach var="dto" items="${list }">
	<tr>
		<td>${dto.getNoticeno() }</td>
		<td><a href="./read.do?noticeno=${dto.noticeno }">${dto.getSubject() }</a></td>
		<td>관리자</td>
		<c:set var="regdt" value="${dto.getRegdt() }"></c:set>
		<td>${fn:substring(regdt,0,10) }</td>
		<td>${dto.getReadcnt() }</td>
	</tr>
</c:forEach>
	<tr>
		<td colspan='5'>${paging }</td>
	</tr>
	<tr>
		<td colspan='5' align="right">
			<form method="post" action="./noticelist.do" onsubmit="return search(this)">
				<select name="col">
					<option value="subject" selected>제목
					<option value="content">내용
					<option value="subject_content">제목+내용
				</select>
				<input type="text" size='10' name="word">
				<input type="submit" value="검색">
			</form>
		</td>
	</tr>
	<tr>
		<td colspan='5'><input type="button" value="글쓰기" onclick="location.href='./create.do'"></td>
	</tr>
</table>
  <script>
  function search(f){
		var word = f.word.value;
		word=word.trim();
		if(word.length==0){
			alert("검색어를 입력하세요!");
			f.word.focus();
			return false;
		}else{
			return true;
		}//end
	}//search() end
  </script>
<!-- 본문 끝 -->
<jsp:include page="../footer.jsp"></jsp:include>