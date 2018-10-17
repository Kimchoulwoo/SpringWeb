<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../header2.jsp"></jsp:include>
<div style="width:100%; height:400px; text-align:center; margin-top:50px">
	<div style="width:40%; height:auto; float:left;">
		<table border="1" style="margin:auto; text-align:center;">
			<tr>
				<td>팀 이름 : </td>
				<td width="50">
					<a href="./insertavg.do?tcode=${homeinfo.tcode }">${homeinfo.tname }</a>
				</td>
			</tr>
			<tr>
				<td>팀장 : </td>
				<td>
					<a href="javascript:read('${homeinfo.tid }', '${mcode }')">${homeinfo.tid }</a>
					<input type="button" value="평가" onclick="insert('${homeinfo.tid }', '${mcode }')">
				</td>
			</tr>
			<tr>
				<td>팀원 : </td>
				<td width="200">
					<c:forEach var="homemem" items="${homemem}">
					<a href="javascript:read('${homemem.tid }', '${mcode }')">${homemem.tid }</a>
					<input type="button" value="평가" onclick="insert('${homemem.tid }', '${mcode }')"><br>
				    </c:forEach>
				</td>
			</tr>
		</table>
	</div>
	<div style="width:15%; height:500px; float:left; text-align:center">
	<br><br><br>
	<table border="1" style="margin:auto; text-align:center;">
			<tr>
				<th>장소 : </th>
				<td>${homeinfo.tarea }</td>
			</tr>
			<tr>
				<th>일자 : </th>
				<td>
				${fn:substring(homeinfo.ttime, 0, 4)}년
				${fn:substring(homeinfo.ttime, 4, 6)}월
				${fn:substring(homeinfo.ttime, 6, 8)}일
				</td>
			</tr>
			<tr>
				<th>점수 : </th>
				<td>
					<c:if test="${hpoint gt apoint }">
					승 ${hpoint } : ${apoint } 패
					</c:if>
					<c:if test="${hpoint eq apoint }">
					무 ${hpoint } : ${apoint } 무
					</c:if>
					<c:if test="${hpoint lt apoint }">
					패 ${hpoint } : ${apoint } 승
					</c:if>
				</td>
			</tr>
	</table>
	</div>
	<div style="width:40%; height:auto; float:left;">
		<table border="1" style="margin:auto; text-align:center;">
			<tr>
				<td>팀 이름 : </td>
				<td  width="50">${awayinfo.tname }</td>
			</tr>
			<tr>
				<td>팀장 : </td>
				<td>
					<a href="javascript:read('${awayinfo.tid }, '${mcode }'')">${awayinfo.tid }</a>
					<input type="button" value="평가 " onclick="insert('${awayinfo.tid }', '${mcode }')">
				</td>
			</tr>
			<tr>
				<td>팀원 : </td>
				<td width="200">
					<c:forEach var="awaymem" items="${awaymem}">
						<a href="javascript:read('${awaymem.tid }, '${mcode }'')">${awaymem.tid }</a>
						<input type="button" value="평가" onclick="insert('${awaymem.tid }', '${mcode }')"><br>
				    </c:forEach>
				</td>
			</tr>
		</table>
	</div>
</div>
<script>
function insert(id, mcode) { 
	var sx = parseInt(screen.width);
	var sy = parseInt(screen.height);
	var x = (sx / 2) + 50;
	var y = (sy / 2) - 25;

	var win = window.open("insertavg.do?id=" + id + "&mcode=" + mcode, "idwin", "width=600, height=300");

	win.moveTo(x, y);
}

function read(id, mcode) { 
	var sx = parseInt(screen.width);
	var sy = parseInt(screen.height);
	var x = (sx / 2) + 50;
	var y = (sy / 2) - 25;

	var win = window.open("playeravg.do?id=" + id + "&mcode=" + mcode, "idwin", "width=600, height=300");

	win.moveTo(x, y);
}
</script>
<jsp:include page="../footer2.jsp"></jsp:include>