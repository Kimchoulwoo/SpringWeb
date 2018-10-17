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
			action='stadiumdelete.do?stacode=${stareaddto.stacode}'>
			<table width='100%'>
				<tr>
					<th colspan='3'>구장 삭제</th>
				</tr>
				<tr>
					<th colspan='3'><hr></th>
				</tr>
				<tr>
					<th>● 구장 이름</th>
					<td colspan='2'><input type='text' name='staname'
						value=${stareaddto.staname }></td>
				</tr>
				<tr>
					<th colspan='3'><hr></th>
				</tr>
				<tr>
					<th>● 구장 주소</th>
					<td colspan='2'><input type='text' name='staaddr1'
						value=${stareaddto.staaddr1 }><input type='text'
						name='staaddr2' value=${stareaddto.staaddr2 }></td>
				</tr>
				<tr>
					<th colspan='3'><hr></th>
				</tr>
				<tr>
					<th>● 구장 정보</th>
					<td colspan='2'><input type='text' name='stainfo'
						value=${stareaddto.stainfo }></td>
				</tr>
				<tr>
					<th colspan='3'><hr></th>
				</tr>
				<tr>
					<th>● 대여 가격</th>
					<td colspan='2'><input type='text' name='stapay'
						value=${stareaddto.stapay }></td>
				</tr>
				<tr>
					<th colspan='3'><hr></th>
				</tr>
				<tr>
					<th>● 구장 개장</th>
					<td colspan='2'><input type='text' name='staopen'
						value=${stareaddto.staopen }></td>
				</tr>
				<tr>
					<th colspan='3'><hr></th>
				</tr>
				<tr>
					<th>● 구장 마감</th>
					<td colspan='2'><input type='text' name='staclose'
						value=${stareaddto.staclose }></td>
				</tr>
				<tr>
					<th colspan='3'><hr></th>
				</tr>
				<tr>
					<th>● 구장 등록일</th>
					<td colspan='2'>${stareaddto.stadate }</td>
				</tr>
				<tr>
					<th colspan='3'><hr></th>
				</tr>
				<tr>
					<th width='30%'>● 구장 비밀번호</th>
					<td colspan='2' width='70%'><input type='text'
						name='stapasswd' value=${stareaddto.stapasswd }></td>
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
					<td colspan='2' width='70%'>
					<td width='30%'><input type="button" value="삭제 취소"
						onclick="location.href='stadiumlist.do'"> <input
						type="submit" value="삭제"></td>
				</tr>
			</table>
		</FORM>
	</div>
	<!-- 본문 끝 -->
	<jsp:include page="../footer.jsp"></jsp:include>