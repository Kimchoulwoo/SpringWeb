<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>pwReset.jsp</title>
</head>
<body>
<form name="pwReset" method="post" action="./pwReset.do" onsubmit="return passwordCheck(this)">
      <input type ="hidden" name = "id" value = "${id}">
      <table border=1 style="margin:auto;">
      <tr>
	<th>비밀번호</th>
	<td><input type="password" name="pw" size="15" required></td>
</tr>
<tr>
	<th>비밀번호 확인</th>
	<td><input type="password" name="repw" size="15" required></td>
</tr>
      <tr>
         <td> 
      <input type='submit' value="확인"> <input type='reset' value="취소">
         </td>
      </tr> 
      </table>         
   </form>
   <script>
   function passwordCheck(f){
	   var pw=f.pw.value;
		if(pw.length<4 || pw.length>=11)	{
	       alert("비밀번호를 4~10글자 이내로 입력하세요.");
	       f.pw.focus();
	       return false;
		}
		
	    //3) 2개의 비밀번호가 서로 일치하는지 검사
		var repw=f.repw.value;
		repw=repw.trim();
		if(repw.length<4 || repw.length>=11)	{
	       alert("비밀번호를 4~10글자 이내로 입력하세요.");
	       f.repw.focus();
	       return false;
		}
		
		if(pw!=repw) {
	       alert("비밀번호가 동일하지 않습니다.");	
	       return false;
		}
		return true;
   }
   </script>
</body>
</html>