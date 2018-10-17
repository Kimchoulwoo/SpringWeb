<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			<th colspan='5'>비밀번호 확인 결과</th>
		</tr>
		<tr>
			<td colspan='5'><hr></td>
		</tr>
		<tr>
			<td colspan='5'>비밀 번호가 일치 합니다</td>
		</tr>
		<tr>
			<td colspan='5'><hr></td>
		</tr>
		<tr>
			<td width='60%' colspan='3'></td>
			<td colspan='2'><input type="button" value="취소" onclick="location.href='stadiumread.do?stacode=${stadto.stacode}'">
			<input type="button" value="구장수정" onclick="location.href='stadiumUpdateForm.do?stacode=${stadto.stacode}'">
			<input type="button" value="구장삭제" onclick="location.href='stadiumdeleteForm.do?stacode=${stadto.stacode}'">
			<input type="button" value="경기장 수정 및 삭제" onclick="location.href='../ground/groundlist.do?stacode=${stadto.stacode}'">
			</td>
		</tr>
		<tr>
			<td colspan='5'><hr></td>
		</tr>
	</table>
	</div>
</body>
</html>
