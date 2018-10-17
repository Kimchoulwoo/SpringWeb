<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:include page="../header2.jsp"></jsp:include>
<!-- 본문 시작 template.jsp -->
<!-- Page Content -->
<div class="container-fluid" id="div-content"
	style="padding-left: 0px; margin-bottom: 20px;">
	<div class="col-xs-2" style="height: 680px; padding-left: 0px;"
		id="div_col2">
		<div id="div_left" class="w3-sidebar w3-light-grey w3-bar-block">
			<h3 class="w3-bar-item">매치</h3>
			<a href="../team/teamlist.do" class="w3-bar-item w3-button">팀</a> <a
				href="../club/clublist.do" class="w3-bar-item w3-button">클럽</a> <a
				href="../matching/match.do" class="w3-bar-item w3-button">매치</a>
		</div>
	</div>
	${str }
	<div class="col-xs-10"
		style="margin: 0px; margin-top: 10px; padding-left: 0px;">
		<!-- Page Content -->
		<table>
			<tr>
				<td>
					<!-- 검색 -->
					<form method="post" action="./clublist.do"
						onsubmit="return search(this)">
						<select name="col">
							<option value="cname">클럽명
							<option value="carea">활동지역
							<option value="cid">클럽장
						</select> <input type="text" size='10' name="word"> <input
							type="submit" value="검색">
					</form> <!-- 검색 끝 -->

				</td>
				<c:if test="${s_id ne null }">
				<td colspan='2'><input type="button" value="클럽 생성" onclick="location.href='./createclub.do'"></td>
				</c:if>
			</tr>
		</table>
		<div id="div_content" style="height: 100%">
			<div class="w3-container">
				<!-- 클럽목록 없는 경우 -->
				<c:if test="${club eq null }">
					<div class="card mt-4" style="margin-bottom: 10px">
						<!-- 클럽이미지 -->
						<div style="float: left; width: 300px; height: 600px; margin-right: 20px">
							등록된 팀이 없습니다.</div>
					</div>
				</c:if>
				<!-- 클럽목록 있는 경우 -->
				<c:if test="${club ne null }">
					<!-- 클럽정보 반복 시작 -->
					<c:forEach var="dto" items="${club }" varStatus="status">
						<div class="card mt-4" style="margin-bottom: 10px">
							<!-- 클럽이미지 -->
							<div
								style="float: left; width: 300px; height: 300px; margin-right: 20px">
								<img class="card-img-top img-fluid"
									src="./clubimages/${dto.cposter }"
									style="width: 300px; height: 300px;">
							</div>
							<!-- 팀정보 -->
							<div class="card-body"
								style="width: 900px; height: 300px; border: 1px solid">
								<h2 style="color: black; display: inline-block;">${dto.cname }</h2>
								<h4 style="color: black; display: inline-block;"></h4>
								<h4 style="color: black;">클럽장 : ${dto.cid }</h4>
								<h4 style="color: black;">활동지역 : ${dto.carea }</h4>
								<button name="detail" 	onclick="location.href='./read.do?ccode=${dto.ccode }'">상세보기</button>
							</div>
						</div>
					</c:forEach>
				</c:if>
			</div>
			<!-- 클럽 리스트 반복 끝!! -->
		</div>

		<table style="margin: auto">
			<tr>
				<td>${paging }</td>
			</tr>
		</table>
	</div>
</div>
<script type="text/javascript">
	/* 화면 크기 불러와 수정 */
	/* 화면 로드 후 처리
	 ------------------------------------------------------------------------------------------*/
	window.onload = function() {
		// div height 설정
		setDivHeight('div_left', 'div_content');
	}
	/*------------------------------------------------------------------------------------------
	 // div height 설정
	 // objSet : 변경할 div id
	 // objTar : height값을 구할 대상 div id
	 ------------------------------------------------------------------------------------------*/
	function setDivHeight(objSet, objTar) {
		var objSet = document.getElementById(objSet);
		var objTarHeight = document.getElementById(objTar).offsetHeight;
		objSet.style.height = objTarHeight + "px";
	}

	//검색
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
<jsp:include page="../footer2.jsp"></jsp:include>