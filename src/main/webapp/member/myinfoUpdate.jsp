<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../header.jsp" %>
<!-- 본문 시작  -->
<!-- myinfoupdate.jsp -->
   <form method="post" action="myinfoUpdate.do" onsubmit="return pwCheck(this)">   
      <table style="margin:auto">
       <tr>
        <td colspan="2" align="center">비밀번호를 입력하세요.</td>        
       </tr> 
       <tr>
        <th>비밀번호</th>
         <td><input type="password" name="pw" size="10" autofocus required></td>
       </tr>
       <tr>
        <td colspan="2" align="center">
         <input type="submit" value="확인">
        </td>        
       </tr>       
      </table>
  </form>  
<!-- 본문 끝 -->
<%@ include file="../footer.jsp" %>