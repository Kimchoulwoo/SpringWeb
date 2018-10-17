<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<title>index.jsp</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="../css/bootstrap.min.css">
<link rel="stylesheet" href="../css/mystyle.css?ver=1">
<link href="https://fonts.googleapis.com/css?family=Lato"
	rel="stylesheet" type="text/css">
<link href="https://fonts.googleapis.com/css?family=Montserrat"
	rel="stylesheet" type="text/css">
<script src="../js/jquery.min.js"></script>
<script src="../js/bootstrap.min.js"></script>
<script src="../js/member.js"></script>
<script src="../js/bbsCheck.js"></script>
<script src="https://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript"
	src="../resources/editor/js/HuskyEZCreator.js" charset="utf-8"></script>

</head>

<script type="text/javascript">
	$(function() {
		//전역변수
		var obj = [];
		//스마트에디터 프레임생성
		nhn.husky.EZCreator.createInIFrame({
			oAppRef : obj,
			elPlaceHolder : "editor",
			sSkinURI : "../resources/editor/SmartEditor2Skin.html",
			htParams : {
				// 툴바 사용 여부
				bUseToolbar : true,
				// 입력창 크기 조절바 사용 여부
				bUseVerticalResizer : true,
				// 모드 탭(Editor | HTML | TEXT) 사용 여부
				bUseModeChanger : true,
			}
		});
		//전송버튼
		$("#insertBoard").click(function() {
			//id가 smarteditor인 textarea에 에디터에서 대입
			obj.getById["editor"].exec("UPDATE_CONTENTS_FIELD", []);
			//폼 submit
			$("#insertBoardFrm").submit();
		});
	});
</script>

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
						<a class="navbar-brand" href="../index.do">아이엠그라운드</a>
					</div>
					<div class="collapse navbar-collapse" id="myNavbar">
						<ul class="nav navbar-nav navbar-right">
							<li><a href="../index.do">HOME</a></li>
							<li><a href="">경기장</a></li>
							<li><a href="">매치</a></li>
							<li><a href="../bbs/list.do">게시판</a></li>
							<li><a href="../member/agreement.do">회원가입</a></li>
							<c:if test="${empty sessionScope.s_id  }">
								<li><a href="../member/login.do">로그인</a></li>
							</c:if>
							<c:if test="${sessionScope.s_id!=null  }">
								<li class="dropdown"><a class="dropdown-toggle"
									data-toggle="dropdown" href="#">로그인한 ID <span class="caret"></span></a>
									<ul class="dropdown-menu">
										<li><a href="../member/myinfo.do">마이페이지</a></li>
										<li><a href="logout.do">로그아웃</a></li>
									</ul></li>
							</c:if>
						</ul>
					</div>
				</div>
			</nav>
		</header>
		<secsion> <!-- header 끝 -->

		<div class="container-fluid bg-1" style="margin: auto">
			<DIV>게시글 수정</DIV>
			<FORM method='POST' action='./update.do?bbsno=${dto.bbsno}'
				id='insertBoardFrm' onsubmit="return formCheck(this)">
				<input type="hidden" name="col" value="${col}"> <input
					type="hidden" name="word" value="${word}"> <input
					type="hidden" name="nowPage" value="${nowPage}">
				<TABLE style="width: 900px; height: 500px;">
					<TR>
						<TH>제목</TH>
						<TD><INPUT type='text' name='subject' size='50'
							value="${dto.subject }"></TD>
					</TR>
					<TR>
						<TH>내용</TH>
						<TD><textarea name="content" id="editor"
								style="width: 800px; height: 400px;">
					${dto.content}
					</textarea></TD>
					</TR>
				</TABLE>

				<DIV>
					<input type='submit' id='insertBoard' value='수정'> <input
						type='button' value='목록'
						onclick="location.href='./list.do?bbsno=${dto.bbsno}'">
				</DIV>
			</FORM>
			<!-- 본문 끝 -->
			<%@ include file="../footer.jsp"%>