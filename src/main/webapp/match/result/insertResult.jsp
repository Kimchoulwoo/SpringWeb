<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>평가</title>
</head>
<body>
<center><h3>점수 입력</h3></center>
<form name="insertresult" method="post" action="./insertresult.do" onsubmit="return numberCheck(this)">
	<input type="hidden" name="mcode" value="${mcode }">
	<table border="1" style="margin:auto; text-align:center;">
		<tr style="font-weight:bold">
			<td>홈 </td>
			<td>어웨이</td>
		</tr>
		<tr>
			<td><input type="text" name="hpoint" placeholder="숫자만 입력하세요." required></td>
			<td><input type="text" name="apoint" placeholder="숫자만 입력하세요." required></td>
		</tr>
		<tr>
			<td colspan="2">
				<input type="submit" value="확인" /> 
  				<input type="button" value="취소" onclick="javascript:window.close();"/>
  			</td>
  		</tr>
	</table>
</form>
<script>
function numberCheck(f) {
	var tel = f.tel.value;
	var pattenNum2 = /^[0-9]+$/;
	if(!pattenNum2.test(tel)){
		alert("숫자만 입력하세요.");
		return false;
	}
	retun true;
}
</script>
</body>
</html>