<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<jsp:include page="../leftside.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>stadiumread</title>

</head>
<body>
	<div>
		<table width='100%'>
			<tr>
				<th colspan='3'>★${stareaddto.staname }구장 상세보기★</th>
			</tr>
			<tr>
				<th colspan='3'>구장 사진</th>
			</tr>
			<tr>
				<th colspan='3'><img src="./storage/${stareaddto.poster1 }"
					width="400"></th>


			</tr>
			<td colspan='3'><hr></td>
			</tr>
			<tr>
				<th width='20%'>● 구장 이름</th>
				<td colspan='2'>${stareaddto.staname }</td>
			</tr>
			<tr>
				<th width='20%'>● 구장주 아이디</th>
				<td colspan='2'>${stareaddto.id }</td>
			</tr>
			<tr>
				<th>● 구장 주소</th>
				<td colspan='2'>${stareaddto.staaddr1 }${stareaddto.staaddr2 }
					${stareaddto.staaddr3 }</td>
			</tr>
			<tr>
				<th>● 구장 정보</th>
				<td colspan='2'>${stareaddto.stainfo }</td>
			</tr>
			<tr>
				<th>● 대여 가격</th>
				<td colspan='2'>${stareaddto.stapay }원(시간당 가격)</td>
			</tr>
			<tr>
				<th>● 구장 개장</th>
				<td colspan='2'>${stareaddto.staopen }시</td>
			</tr>
			<tr>
				<th>● 구장 마감</th>
				<td colspan='2'>${stareaddto.staclose }시</td>
			</tr>
			<tr>
				<th>● 구장 등록일</th>
				<td colspan='2'>${fn:substring(stareaddto.stadate, 0, 10)}</td>
			</tr>
			<tr>
				<td colspan='3'><hr></td>
			</tr>
		</table>
		<!-- 경기장 관련 -->

		<!-- ------------------------------------------------------------------------ -->

		<div>
			<table width='100%'>
				<tr>
					<th colspan='3'>★${stareaddto.staname }구장의 경기장 목록★</th>
				</tr>
				<tr>
					<th colspan='3'><hr></th>
				</tr>
				<tr>
					<th widht='40%'>● 경기장 이름</th>
					<th widht='30%'>● 예약 가능 날짜</th>
					<th widht='30%'>● 예약 가능 시간</th>
				</tr>
				<tr>
					<th colspan='3'><hr></th>
				</tr>
				<c:forEach var="grdto" items="${groundlist }">
					<c:if test="${grdto.grlevel eq 'Y'}">
						<tr>
							<td>${grdto.grname }</td>
							<td><c:choose>
									<c:when test="${grdto.grday eq 'all Week'}">
							항시 개장
							</c:when>
								</c:choose></td>
							<td>${grdto.grtime }</td>
							<td></td>

						</tr>
						<tr>
							<th colspan='3'><hr></th>
						</tr>
					</c:if>
				</c:forEach>
			</table>
			<table width='100%'>
				<c:if test="${!empty stareaddto.poster2 }">
					<tr>
						<th colspan='2'><img
							src="../../admin/stadium/storage/${stareaddto.poster2 }"
							width="70%"></th>
					</tr>
				</c:if>


				<c:if test="${!empty stareaddto.poster3 }">
					<tr>
						<th><img
							src="../../admin/stadium/storage/${stareaddto.poster3 }"
							width="70%"></th>
					</tr>
				</c:if>



				<c:if test="${!empty stareaddto.poster4 }">
					<tr>
						<th><img
							src="../../admin/stadium/storage/${stareaddto.poster4 }"
							width="70%"></th>
					</tr>
				</c:if>


				<c:if test="${!empty stareaddto.poster5 }">
					<tr>
						<th><img
							src="../../admin/stadium/storage/${stareaddto.poster5 }"
							width="70%"></th>
					</tr>
				</c:if>
				<tr>
					<td width='60%'></td>
					<td><input type="button" value="구장목록"
						onclick="location.href='stadiumlist.do'"> <input
						type="button" value="경기장 등록"
						onclick="location.href='../ground/groundForm.do?stacode=${stareaddto.stacode}&staopen=${stareaddto.staopen }&staclose=${stareaddto.staclose }'">
						<input type="button" value="구장수정"
						onclick="location.href='stadiumUpdateForm.do?stacode=${stareaddto.stacode}'">
						<input type="button" value="구장삭제"
						onclick="return stadiumDel('${stareaddto.stacode}')"> <input
						type="button" value="경기장 수정 및 삭제"
						onclick="location.href='../ground/groundlist.do?stacode=${stareaddto.stacode}'">
						
						<input type="button" value="누적 이용자 집계 현황" onclick="ToRe(${stareaddto.stacode})">
					</td>
				</tr>

			</table>
		</div>
		
		<script>
		function ToRe(stacode) {
			// 새창이 출력되는 위치 지정
			var sx = parseInt(screen.width);
			var sy = parseInt(screen.height);
			var x = (sx / 2) + 50;
			var y = (sy / 2) - 25;

			// 새창띄우기
			var win = window.open("ToRe.do?stacode=" + stacode, "torewin", "width=400, height=350");

			// 화면이동
			win.moveTo(x, y);

		}// idCheck() end
		
			function stadiumDel(stacode) {
				if (confirm("현재 구장을 삭제 하시겠습니까?") == true) {
					location.href = "stadiumdelete.do?stacode=" + stacode;
				} else {
					return;
				}
			}
		</script>
		<!-- 본문 끝 -->
		<jsp:include page="../footer.jsp"></jsp:include>