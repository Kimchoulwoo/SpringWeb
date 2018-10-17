<%@ page contentType="text/html; charset=UTF-8"%>

	<div>
		<table width='100%'>
			<tr>
				<th colspan='3'>★${staRVlist.staname }구장 ${grRVlist.grname }
					 ★</th>
			</tr>
			<%-- <tr>
				<th colspan='3'>구장 사진</th>
			</tr>
			<tr>
				<th colspan='3'><img src="./storage/${dto.poster1 }" width="400"></th>
			</tr>
			<td colspan='3'><hr></td>
			</tr> --%>
			<tr>
				<th>● 구장 이름</th>
				<td colspan='2'>${staRVlist.staname }</td>
			</tr>
			<tr>
				<th>● 구장 주소</th>
				<td colspan='2'>${staRVlist.staaddr1 } ${staRVlist.staaddr2 }</td>
			</tr>
			<tr>
				<th>● 구장 정보</th>
				<td colspan='2'>${staRVlist.stainfo }</td>
			</tr>
			<tr>
				<th>● 대여 가격</th>
				<td colspan='2'>${staRVlist.stapay }</td>
			</tr>
			<tr>
				<th>● 구장 개장</th>
				<td colspan='2'>${staRVlist.staopen }</td>
			</tr>
			<tr>
				<th>● 구장 마감</th>
				<td colspan='2'>${staRVlist.staclose }</td>
			</tr>
			<tr>
				<th>● 구장 등록일</th>
				<td colspan='2'>${staRVlist.stadate }</td>
			</tr>
			<tr>
				<td colspan='3'><hr></td>
			</tr>
<%-- 			<tr>
				<td>로그인 아이디</td>
				<td colspan='2'><input type="text" name='pid' value=${s_id } readonly></td>
			</tr> --%>

		</table>