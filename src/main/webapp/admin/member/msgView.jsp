<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>admin/msgview.jsp</title>
<link rel="stylesheet" href="../css/adminlogin.css">

</head>
<body>
	<section class="container">
		<article class="half">
			<h1>알림</h1>
			<div class="tabs"></div>
			<div class="content">
				<div class="signin-cont cont">
					<div style="text-align:center;">
						${msg1 != null ? img : ""}${msg1 }<br>
						${msg2 != null ? img : ""}${msg2 }<br>
					</div>					
					<div style="margin-top:50px;">
						<p>${link1 } ${link2 } ${link3 }</p> 
					</div>
				</div>
			</div>
		</article>
	</section>
</body>
</html>
