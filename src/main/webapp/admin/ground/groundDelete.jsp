<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../leftside.jsp"></jsp:include>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>template</title>
</head>
<body>
	<div>
		<FORM name='frm' method='POST'
			action='grounddelete.do?grcode=${grdelOdto.grcode  }'>
			<table width='100%'>
				<tr>
					<th colspan='3'>경기장 삭제</th>
				</tr>
				<tr>
					<th colspan='3'><hr></th>
				</tr>
				<tr>
					<th width='30%'>● 경기장 이름</th>
					<td colspan='2' width='70%'>${grdelOdto.grname }</td>
				</tr>
				<tr>
					<th colspan='3'><hr></th>
				</tr>
				<tr>
					<th colspan='3'>삭제 하시겠습니까?</th>
				</tr>
				<tr>
					<th colspan='3'><hr></th>
				</tr>
				<tr>
					<td colspan='2' width='70%'></td>
					<td width='30%'><input type="button" value="삭제 취소"
						onclick="location.href='../stadium/stadiumlist.do'"> <input
						type="submit" value="삭제"></td>
				</tr>
			</table>
		</FORM>
	</div>
</body>
</html>
