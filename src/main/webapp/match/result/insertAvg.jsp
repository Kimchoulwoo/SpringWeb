<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>평가</title>
</head>
<body>
	<form method="post" action="./insertavg.do">
	<input type="hidden" name="mcode" value="${mcode }">
	<input type="hidden" name="writer" value="${id }">
		<table border="1" style="margin:auto; text-align:center;">
			<tr>
				<th>아이디</th><th>슈팅</th><th>패스</th><th>지구력</th><th>인성</th>
			</tr>
			<tr>
				
				<td><input type="text" name="player" value="${player }" size="5" readonly></td>
				<td>
					<select name="shooting" size="1">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
					</select>
				</td>
				<td>
					<select name="pass" size="1">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
					</select>
				</td>
				<td>
					<select name="stamina" size="1">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
					</select>
				</td>
				<td>
					<select name="manner" size="1">
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
					</select>
				</td>
			</tr>
			<tr>
				<th colspan="5">한줄 남기기</th>
			</tr>
			<tr>
				<td colspan="5"><input type=text name="comment" size="50"></td>
			</tr>
		</table>
		<center>
		<input type="submit" value="등록">
		<input type="button" value="닫기" onclick="window.close();">
		</center>
	</form>
</body>
</html>