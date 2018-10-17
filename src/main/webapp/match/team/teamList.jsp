<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
	<div class="col-xs-10"
		style="margin: 0px; margin-top: 10px; padding-left: 0px;">
		<!-- Page Content -->
		<table>
			<tr>
				<td>
					<!-- 검색 -->
					<form method="post" action="./teamlist.do"
						onsubmit="return search(this)">
						<select name="col">
							<option value="tid">팀장
							<option value="tarea">지역
							<option value="tname">팀이름
							<option value="ttime">경기시간
							<option value="player">경기인원
						</select> <input type="text" size='10' name="word"> <input
							type="submit" value="검색">
					</form> <!-- 검색 끝 -->

				</td>
				<td colspan='2'><input type="button" value="팀 생성" onclick="selectPcode('${sessionScope.s_id }')"></td>
			</tr>
		</table>
		<div id="div_content" style="height: 100%">
			<div class="w3-container">
				<!-- 팀목록 없는 경우 -->
				<c:if test="${list eq null }">
					<div class="card mt-4" style="margin-bottom: 10px">
						<!-- 클럽이미지 -->
						<div style="float: left; width: 300px; height: 600px; margin-right: 20px">
							등록된 팀이 없습니다.</div>
					</div>
				</c:if>
				<!-- 팀목록 있는 경우 -->
				<c:if test="${list ne null }">
					<!-- 팀 정보 반복 시작 -->
					<c:forEach var="dto" items="${list }" varStatus="status">
						<div class="card mt-4" style="margin-bottom: 10px">
							<!-- 클럽이미지 -->
							<div style="float: left; width: 300px; height: 300px; margin-right: 20px">
								<img class="card-img-top img-fluid" src="../club/clubimages/${dto.cposter }" style="width: 300px; height: 300px;">
							</div>
							<!-- 팀정보 -->
							<div class="card-body"
								style="width: 900px; height: 300px; border: 1px solid">
								<h2 style="color: black; display: inline-block;">${dto.tname }(${dto.tarea }, ${dto.player }명)</h2>
								<h4 style="color: black; display: inline-block;">
									<c:forEach var="i" begin="1" end="${teamavg[status.index] }">
								&#9733;
							</c:forEach>
								</h4>
								<fmt:parseDate value="${dto.ttime }" var="ttime"
									pattern="yyyyMMddHH" />
								<h4 style="color: black;">
									일시 :
									<fmt:formatDate value="${ttime }" pattern="yyyy.MM.dd HH" />
									시
									<c:if test="${dto.pcode ne 'null' }">(구장 예약완료)</c:if>
								</h4>
								<div
									style="overflow-x: hidden; overflow-y: scroll; height: 140px; width: 510px;">
									<!-- 팀원 정보 반복 시작 -->

									<c:forEach var="map" items="${tm[status.index ] }">
										<div
											style="display: inline-block; text-align: center; margin-top: 0px; margin-bottom: 0px;">
											<div
												style="border: 1px solid grey; float: left; width: 120px; padding-top: 3px;">${map.tm_id }</div>
											<div
												style="border: 1px solid grey; border-left: 0px; float: left; width: 120px; padding-top: 3px;">${map.tm_birth }</div>
											<div
												style="border: 1px solid grey; border-left: 0px; float: left; width: 120px; padding-top: 3px;">${map.tm_cname }</div>
											<div
												style="border: 1px solid grey; border-left: 0px; float: left; width: 120px; padding-top: 3px;">
												<c:set var="avg" value="${map.tm_playeravg }" />
												<c:forEach var="i" begin="0" end="4">
													<c:choose>
														<c:when test="${avg eq 0 }">
										&#9734;
									</c:when>
														<c:when test="${avg ne 0 }">
										&#9733;
										<c:set var="avg" value="${avg-1 }" />
														</c:when>
													</c:choose>
												</c:forEach>
											</div>
										</div>
									</c:forEach>
									<!-- 팀원 정보 반복 끝 -->
								</div>
								<button name="detail"
									onclick="location.href='./teamread.do?tcode=${dto.tcode }'">상세보기</button>
							</div>
						</div>
						<!-- 팀정보 끝 -->
					</c:forEach>
				</c:if>
			</div>

			<!-- 팀 리스트 반복 끝!! -->
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

<script>
	function selectPcode(se) {
		if (se != "") {
			var sx = parseInt(screen.width);
			var sy = parseInt(screen.height);
			var x = (sx / 2) + 50;
			var y = (sy / 2) - 25;
			var win = window.open("teamCreate.do", "idwin",
					"width=400, height=350");

			win.moveTo(x, y);
		} else {
			alert("로그인 후 이용해주세요");
		}

	} //selectPcode
</script>


<!-- 본문 끝 -->
<jsp:include page="../footer2.jsp"></jsp:include>