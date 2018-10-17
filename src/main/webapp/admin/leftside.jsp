<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>관리자페이지</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link href="https://fonts.googleapis.com/css?family=Lato"
	rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Montserrat"
	rel="stylesheet" type="text/css">

<style>
body {
	font: 400 15px/1.8 Lato, sans-serif;
}

.div {
	font-family: Montserrat, sans-serif;
}

/* Set height of the grid so .sidenav can be 100% (adjust if needed) */
.row.content {
	height: 100%
}

/* Set gray background color and 100% height */
.sidenav {
	background-color: #f1f1f1;
	height: 900px;
}

/* Set black background color, white text and some padding */
footer {
	background-color: #555;
	color: white;
	padding: 15px;
}

/* On small screens, set height to 'auto' for sidenav and grid */
@media screen and (max-width: 767px) {
	.sidenav {
		height: auto;
		padding: 15px;
	}
	.row.content {
		height: auto;
	}
}

.adminbar {
	text-align: right;
	font-family: Montserrat, sans-serif;
	margin-top: 10px;
	font-size: 13px;
	border-bottom: 1px solid #a9a9a9;
	padding-bottom: 8px;
	word-spacing: 3px;
}
</style>
</head>
<body>
	<div class="container-fluid">
		<div class="row content">
			<div class="col-sm-3 sidenav">
				<div
					style="font-family: Montserrat, sans-serif; margin-top: 15px; text-align: center; font-size: 18px;">
					<p style="text-align: center;">
						<a href="../admin.jsp"> <img src="../../images/user.png"
							style="width: 70px; height: 70px;"><br>
						</a>
					</p>
					<strong><%=session.getAttribute("s_id")%><br></strong>
				</div>
				<c:if test="${s_mlevel eq '관리자' }">
					<ul class="nav nav-pills nav-stacked" style="margin-top: 30px;">
						<li class="list"><a href="../notice/noticelist.do">공지사항관리</a></li>
						<li class="list"><a href="../member/memlist.do">회원관리</a></li>
						<li class="list"><a href="../stadium/stadiumlist.do">구장관리</a></li>
					</ul>
				</c:if>
				<c:if test="${s_mlevel eq '구장주' }">
					<ul class="nav nav-pills nav-stacked" style="margin-top: 30px;">
						<li class="list"><a href="../stadium/stadiumlist.do?id=${s_id }">구장관리</a></li>
					</ul>
				</c:if>

				<br>
			</div>

			<div class="col-sm-9">
				<div class="adminbar">
					<img src="../../images/user.png" style="width: 20px; height: 20px;">
					<strong>LV.<%=session.getAttribute("s_mlevel")%></strong>
					<%=session.getAttribute("s_id")%>
					<input type="button" value="로그아웃"
						onclick="location.href='../logout.do'">
				</div>
				<!-- leftside 끝 -->