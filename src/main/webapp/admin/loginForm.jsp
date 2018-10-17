<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>관리자로그인</title>
<link rel="stylesheet" href="../css/adminlogin.css">

</head>
<body>
	<section class="container">
		<article class="half">
			<h1>관리자로그인</h1>
			<div class="tabs">

			</div>
			<div class="content">
				<div class="signin-cont cont">
					<form method="post" action="login.do">
						<input type="id" name="id" id="id" class="inpt" required="required" placeholder="아이디"> 
						<label for="id">아이디</label> 
						<input type="password" name="pw" id="pw" class="inpt" required="required" placeholder="비밀번호"> 
						<label for="password">비밀번호</label> 
						
						<div class="submit-wrap">
							<input type="submit" value="로그인" class="submit"> 
						</div>
					</form>
				</div>
			</div>
		</article>
	</section>
</body>

</html>