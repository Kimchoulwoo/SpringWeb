<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
		<div id="div_content" style="height: 100%">
			<div class="w3-container">
				<form>
					<table border="1" style="margin: auto; width: 300px">
						<tr>
							<td colspan="3">
								<img src="./clubimages/${dto.cposter }" style="width: 300px; height: 300px;"></td>
						</tr>
						<tr>
							<th>클럽명</th>
							<td colspan="2">${dto.cname}</td>
						</tr>
						<tr>
							<th>클럽장</th>
							<th colspan="2">${dto.cid }</th>
						</tr>
						<tr>
							<th>활동지역</th>
							<td colspan='2'>${dto.carea }</td>
						</tr>
						<tr>
							<th>클럽소개</th>
							<td colspan='2'>${dto.ccontent }</td>
						</tr>
						<c:forEach var="cmem" items="${cmem}">
							<tr>
								<td>${cmem.cid}</td>
									<c:if test="${empty s_id}">
										<td colspan='2'><fmt:formatNumber value="${cmem.avg}" pattern="0.0"/></td>
									</c:if>
									<c:if test="${!empty s_id}">
										<td><fmt:formatNumber value="${cmem.avg}" pattern="0.0"/></td>
										<td>
										<input type="button" value="OUT" onclick="cOut('${cmem.cid}', '${s_id}','${dto.cid }','${dto.ccode }')">
										</td>
									</c:if>
								
							</tr>
						</c:forEach>

						<tr>
							<td colspan='3' style="text-align: center">
								<form>
									<input type="hidden" name="ccode" value="${dto.ccode}"> 
									<input type="button" value="클럽 목록" onclick="move(this.form, './clublist.do')"> 
									<input type="button" value="가입" onclick="cJoin('${s_id}','${dto.cid }','${dto.ccode }')">
									<c:set var="s_id" value="${s_id}" />
								</form>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>


	</div>
</div>
<script>
function cOut(cid, s_id, clubid,ccode) {
	// alert(tid + s_id + tlid + tcode + tmemno + tmatch);
	if (s_id != clubid && s_id != cid) {
		alert("클럽원 추방 권한이 없습니다.");
		return;
	}
	if (clubid==cid) {
		alert("클럽장은 탈퇴할 수 없습니다.");
		return;
	}
	if (clubid == s_id && clubid!=cid) {
		if (confirm("클럽에서 추방 합니다.") == true) {
			location.href="./clubban.do?ccode=" + ccode + "&cid=" + cid;
		} else {
			return;
		}
	}	
	if (cid == s_id && clubid!=s_id) {
		if (confirm("클럽에서 탈퇴 합니다.") == true) {
			location.href="./clubban.do?ccode=" + ccode + "&cid=" + cid;
		} else {
			return;
		}
	}
}//cOut() end

function cJoin(s_id,clubid,ccode) {
	if (s_id == "") {
		alert("로그인 후 가입을 해주세요.");
		return;
	}
	
	if (s_id == clubid) {
		alert("현재 클럽장입니다.");
	} else {
		if (confirm("현재 클럽의 클럽원으로 가입 합니다.") == true) {
			location.href="./clubjoin.do?ccode="+ccode;
		} else {
			return;
		}	
	}
}

function move(f, file) {
	f.action = file;
	f.submit();
}

</script>

<!-- 본문 끝 -->
<jsp:include page="../footer2.jsp"></jsp:include>