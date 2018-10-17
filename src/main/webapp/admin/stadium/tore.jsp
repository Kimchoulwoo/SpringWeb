<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<style>
.graph {
	position: relative; /* IE is dumb */
	width: 200px;
	border: 1px solid #B1D632;
	padding: 2px;
	font-size: 11px;
	font-family: tahoma;
	margin-bottom: 3px;
}

.graph .bar {
	display: block;
	position: relative;
	background: #B1D632;
	text-align: center;
	color: #333;
	height: 2em;
	line-height: 2em;
}

.graph .bar span {
	position: absolute;
	left: 1em;
}
</style>
※ 구장 누적 예약자 현황
<c:forEach var="tore" items="${list }">
	<div class="graph">
		${tore.id}<strong class="bar" style="width: ${tore.cnt}0%;">${tore.cnt }</strong>
	</div>
</c:forEach>