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
<form method="post" action="stadiumpasswdcheck.do?stacode=${stacode}" onsubmit="return pwCheck(this)">   
      <table width='100%'>
       <tr>
        <th colspan="2">비밀번호를 입력하세요.</th>
        <td rowspan="3">
         <input type="submit" value="확인">
        </td>                
       </tr>
       <tr>
       	<td colspan='2'>구장 등록시 입력한 비밀번호 입니다.</td>
       </tr> 
       <tr>
        <th>비밀번호</th>
         <td><input type="password" name="stapasswd" autofocus required></td>
       </tr>       
      </table>
  </form>  
</body>
</html>
