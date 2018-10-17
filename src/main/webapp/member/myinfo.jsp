<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../header.jsp" %>
<!-- 본문 시작  -->
<!-- myinfo.jsp -->
  <div class="title">나의 가입정보</div>
   <form>
    <table border=1 style="margin:auto">
      <tr>
        <th>이름</th>
        <td colspan=3>${dto.name }</td>
      </tr>
      <tr>
        <th>생년월일</th>
        <td colspan=3>
         <c:set var="birth" value="${dto.birth }"/>
         <c:set var="year" value="${fn:substring(birth, 0, 4) }"/>
         <c:set var="month" value="${fn:substring(birth, 4, 6) }"/>
         <c:set var="date" value="${fn:substring(birth, 6, 8) }"/>
         <c:out value="${year }년 ${month }월 ${date }일"/>
      </tr>
      <tr>
        <th>전화번호</th>
        <td colspan=3>${dto.tel }</td>
      </tr>
      <tr>
        <th>이메일주소</th>
        <td colspan=3>${dto.mail }</td>
      </tr>
      <tr>
        <th>주소</th>
        <td>${dto.zipcode } ${dto.address1 } ${dto.address2 }</td>
      </tr>
      <tr>
        <th>클럽 이름</th>
        <td colspan=3>${cname }</td>
      </tr>
      <tr>
        <th>회원등급</th>
        <td colspan=3>
         <c:set var="mlevel" value="${dto.mlevel }"/>
         <c:choose>
          <c:when test="${mlevel eq 'A'}">관리자</c:when>
          <c:when test="${mlevel eq 'B'}">구장주</c:when>
          <c:when test="${mlevel eq 'C'}">클럽장</c:when>
          <c:when test="${mlevel eq 'D'}">일반회원</c:when>
         </c:choose>
        </td>
      </tr>
      <tr>
       <td colspan="2">
       <input type="button" value="회원정보 수정" onclick="location.href='./myinfoUpdate.do'">
       <input type="button" value="회원 탈퇴" onclick="location.href='./withdraw.do'">
       </td>
      </tr>
    </table>
   </form>
  
<!-- 본문 끝 -->
<%@ include file="../footer.jsp" %>