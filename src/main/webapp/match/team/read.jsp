<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../header2.jsp" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- 본문 시작 template.jsp -->
<!-- Page Content -->
<div class="container-fluid" id="div-content" style="padding-left: 0px; margin-bottom: 20px;">
	<div class="col-xs-2" style="height: 680px; padding-left: 0px;" id="div_col2">
		<div id="div_left" class="w3-sidebar w3-light-grey w3-bar-block">
			<h3 class="w3-bar-item">매치</h3>
			<a href="../team/teamlist.do" class="w3-bar-item w3-button">팀</a> 
			<a href="../club/clublist.do"	class="w3-bar-item w3-button">클럽</a> 
			<a href="../matching/match.do"	class="w3-bar-item w3-button">매치</a>
		</div>
	</div>
	<div class="col-xs-10" style="margin: 0px; margin-top: 10px; padding-left: 0px;">
		<!-- Page Content -->
		<div id="div_content" style="height: 100%">
<!-- 본문 -->
	<form>
	<table border="1" style="margin:auto; width:300px">
		<tr>
			<th colspan='3' style="text-align:center">
				팀 상세 보기
			</th>
		</tr>
		<tr>
			<th>팀명</th>
			<td colspan="2">${teamLeader.tname}</td>
		</tr>
		<tr>
			<th>클럽명</th>
			<th colspan="2">팀원</th>
		</tr>
		<tr>
			<td>${teamLeader.cname}</td>
			<td>${teamLeader.tid}</td>
			<td>팀장</td>
		</tr>
		<c:forEach var="dto" items="${tmlist}">
			<tr>
				<td>${dto.cname}</td>
				<td>${dto.tid}</td>
				<td><input type="button" value="OUT" onclick="tOut('${dto.tid}', '${s_id}', '${teamLeader.tid}', '${tcode}', '${dto.tmemno}', '${teamLeader.tmatch}')"></td>
			</tr>
		</c:forEach>
		<tr>
			<th>경기 인원</th>
			<td>모집 인원 : ${player}</td>
			<td>현재원 : ${tmcount}</td>
		</tr>
		<tr>
			<th>희망 경기 지역</th>
			<td colspan="2">${teamLeader.tarea}</td>
		</tr>
		<tr>
			<th>경기 등록</th>
			<c:if test ="${teamLeader.tmatch eq 'y'}">
			<td colspan="2">등록 완료</td>
			</c:if>
			<c:if test ="${teamLeader.tmatch eq 'n'}">
			<td colspan="2">등록 대기중</td>
			</c:if>
		</tr>
		<tr>
		<fmt:parseDate value="${teamLeader.ttime}" var="ttime" pattern="yyyyMMddHH"/>
			<th>팀 유지 기간</th>
			<td colspan="2"><fmt:formatDate value="${ttime }" pattern="yyyy.MM.dd HH"/>시</td>
		</tr>
		<tr>
			<td colspan='3' style="text-align:center">
				<form>
				<input type="hidden" name="tcode" value="${tcode}">
				<input type="button" value="팀 목록" onclick="move(this.form, './teamlist.do')">
				<input type="button" value="가입" onclick="tJoin('${teamLeader.tid}', '${s_id}', '${tcode}', '${ccode}', '${alreadyjoin}', '${player}', '${tmcount}', '${teamLeader.tmatch}')">
				<c:set var="s_id" value="${s_id}" />
				<c:set var="tlid" value="${teamLeader.tid}" />
				<c:if test="${s_id eq tlid}">
					<input type="button" value="삭제" onclick="tDelete('${teamLeader.tid}', '${s_id}', '${tcode}', '${teamLeader.tmatch}')">
					<c:if test="${player eq tmcount}">
						<input type="button" value="경기 등록" onclick="ready('${teamLeader.tid}', '${s_id}', '${tcode}', '${teamLeader.tmatch}')">
					</c:if>
				</c:if>
			</form>
			</td>
		</tr>
	</table>
	</form>
	</div>
	</div>
</div>
<script>
function tOut(tid, s_id, tlid, tcode, tmemno, tmatch) {
	// alert(tid + s_id + tlid + tcode + tmemno + tmatch);
	if (tmatch == 'y') {
		alert("경기등록이 완료되어 탈퇴 할 수 없습니다.");
		return;
	}

	if (s_id != tlid && s_id != tid) {
		alert("팀원 추방 권한이 없습니다.");
		return;
	}

	if (tlid == s_id) {
		if (confirm("팀에서 추방 합니다.") == true) {
			location.href="./teamban.do?tcode=" + tcode + "&tid=" + tid + "&tlid=" + tlid + "&tmemno=" + tmemno + "";
		} else {
			return;
		}
	}
	
	if (tid == s_id) {
		if (confirm("팀에서 탈퇴 합니다.") == true) {
			location.href="./teamban.do?tcode=" + tcode + "&tid=" + tid + "&tlid=" + tlid + "&tmemno=" + tmemno + "";
		} else {
			return;
		}
	}
}

function tJoin(tlid, s_id, tcode, ccode, alreadyjoin, player, tmcount, tmatch) {
	if (tmatch == 'y') {
		alert("경기등록이 완료되어 탈퇴 할 수 없습니다.");
		return;
	}

	if (!(player > tmcount)) {
		alert("정원 초과입니다.");
		return;
	}

	if (s_id == "") {
		alert("로그인 후 가입을 해주세요.");
		return;
	}

	if (s_id == alreadyjoin) {
		alert("이미 현재 팀에 가입 되어있습니다.");
		return;
	}

	if (s_id == tlid) {
		alert("현재 팀의 팀장은 현재 팀의 팀원으로 가입이 불가능 합니다.");
	} else {
		if (confirm("현재 팀의 팀원으로 가입 합니다.") == true) {
			location.href="./teamjoin.do?tcode=" + tcode + "";
		} else {
			return;
		}	
	}
}

function tDelete(tlid, s_id, tcode, tmatch) {
	if (tmatch == 'y') {
		alert("경기등록이 완료되어 삭제 할 수 없습니다.");
		return;
	}

	if (s_id != tlid) {
		alert("팀장만 팀을 삭제 할 수 있습니다.");
	} else {
		if (confirm("현재 팀을 삭제 합니다.") == true) {
			location.href="./teamdelete.do?tcode=" + tcode + "";
		} else {
			return;
		}	
	}
}

function ready(tlid, s_id, tcode, tmatch) {
	if (tmatch == 'y') {
		alert("이미 경기등록이 완료되었습니다.");
		return;
	}

	if (s_id != tlid) {
		alert("경기등록 권한은 팀장에게 있습니다.");
	} else {
		if (confirm("팀을 경기 등록합니다. 등록 상태가 되면 팀 탈퇴 및 삭제가 불가능 합니다.") == true) {
			location.href="./ready.do?tcode=" + tcode + "";
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
<%@ include file="../footer2.jsp" %>