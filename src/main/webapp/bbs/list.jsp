<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../header.jsp" %>
<!-- 본문 -->
	<div>게시판 목록</div>
	전체 게시글 수 : <strong>${boardcount}</strong> 건
	<table border="1" style="margin:auto;">
		<tr>
			<th>번호</th>
			<th>제목</th>
			<th>ID</th>
			<th>조회수</th>
			<th>등록일</th>
		</tr>
		<c:forEach var="dto2" items="${noticelist}">
			<tr>
				<td>공지사항</td>
				<td>
					<a href="./noticeread.do?noticeno=${dto2.noticeno}">
					${dto2.subject}
					<c:set var="day" value="${dto2.regdt.substring(0, 10)}" />
					<c:set var="today" value="${today}" />
						<c:if test="${day eq today}">
							<img src='../images/new.gif'>
						</c:if>
					</a>
				</td>
				<td>관리자</td>
				<td>${dto2.readcnt}</td>
				<td>${dto2.regdt.substring(0, 16)}</td>
			</tr>
		</c:forEach>
		<c:forEach var="dto1" items="${bbslist}">
			<tr>
				<td>${dto1.bbsno}</td>
				<td>
				<c:forEach begin="1" end="${dto1.indent}" varStatus="loop">
   				 <img src='../images/reply.gif'>
				</c:forEach>
					<a href="./read.do?bbsno=${dto1.bbsno}&col=${col}&word=${word}&nowPage=${nowPage}">
					${dto1.subject}
					<c:set var="day" value="${dto1.regdt.substring(0, 10)}" />
					<c:set var="today" value="${today}" />
					<c:set var="hot" value="${dto1.readcnt}" />
						<c:if test="${day eq today}">
							<img src='../images/new.gif'>
						</c:if>
						<c:if test="${hot >= 10}">
							<img src='../images/hot.gif'>
						</c:if>
					</a>
				</td>
				<td>${dto1.id}</td>
				<td>${dto1.readcnt}</td>
				<td>${dto1.regdt.substring(0, 16)}</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="5">
			<form action="./list.do">
				<select name="col">
					<option value="id">작성자
					<option value="subject" selected>제목
					<option value="content">내용
				</select>
				<input type="text" name="word">
				<input type="submit" value="검색">
			</form>
			</td>
		</tr>
		<tr>
			<td colspan="5">${paging}</td>
		</tr>
	</table>
	<form action="./create.do" onsubmit="return idCheck(this)">
	<div>
		<input type="hidden" name="id" value="${s_id}">
		<c:if test="${s_id ne null}">
			<input type="submit" value="게시글 등록">
		</c:if>
			
		<input type="button" value="[HOME]"	onclick="location.href='./list.do'">
	</div>
	</form>
<!-- 본문 끝 -->
<%@ include file="../footer.jsp" %>