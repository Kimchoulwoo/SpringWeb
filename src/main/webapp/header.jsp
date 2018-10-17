<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>PutFoot👣</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/form.css">
<link rel="stylesheet" href="../css/mystyle.css?ver=1">
<link href="https://fonts.googleapis.com/css?family=Lato"
	rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Montserrat"
	rel="stylesheet" type="text/css">
<script src="../js/jquery.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
</head>
<body>
	<div class="wrap">
		<!-- 메인 카테고리 -->
		<header>
		<nav class="navbar navbar-default">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#myNavbar">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="../index.do">PutFoot👣</a>
				</div>
				<div class="collapse navbar-collapse" id="myNavbar">
					<ul class="nav navbar-nav navbar-right">
						<li><a href="../index.do">HOME</a></li>
						<li><a href="../stadium/stadium/stalist.do">경기장</a></li>
						<li><a href="../match/team/teamlist.do">매치</a></li>
						<li><a href="../bbs/list.do">게시판</a></li>
						<c:if test="${empty sessionScope.s_id  }">
							<li><a href="../member/login.do">로그인</a></li>
							<li><a href="../member/agreement.do">회원가입</a></li>
						</c:if>
						<c:if test="${sessionScope.s_id!=null  }">
							<li class="dropdown"><a class="dropdown-toggle"
								data-toggle="dropdown" href="#">${sessionScope.s_id  }님 <span class="caret"></span></a>
								<ul class="dropdown-menu">
										<li><a href="../match/result/mymatch.do">경기내역</a></li>
										<li><a href="../stadium/basket/basketlist.do">장바구니</a></li>
										<li><a href="../stadium/payment/paymentlist.do">결제내역</a></li>
										<li><a href="../member/myinfo.do">마이페이지</a></li>
									<li><a href="../member/logout.do">로그아웃</a></li>
								</ul></li>
						</c:if>
					</ul>
				</div>
			</div>
		</nav>
		</header>
		<section>
		<!-- header 끝 -->

		<div class="container-fluid bg-1 text-center"> <!-- content div 시작 -->