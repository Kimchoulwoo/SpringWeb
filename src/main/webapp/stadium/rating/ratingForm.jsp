<%@ page contentType="text/html; charset=UTF-8"%>
<jsp:include page="../header.jsp"></jsp:include>
<!-- 본문 시작 template.jsp -->

<FORM method="POST" action="./ratingcreate.do?pcode=${paymentinfo.pcode }">
	<table width='100%' border='1'>
		<tr>
			<th colspan='4'>구장 평가 하기</th>
		</tr>
		<tr>
			<th colspan='4'><hr></th>
		</tr>
		<tr>
			<th width='25%'>구장 이름</th>
			<td width='25%'>${stadiuminfo.staname }</td>
			<td width='25%'></td>
			<td width='25%'></td>
		</tr>
		<tr>
			<th colspan='4'><hr></th>
		</tr>
		<tr>
			<th>결제 코드</th>
			<td>${paymentinfo.pcode }</td>
			<th>평가하는 아이디</th>
			<td><input type='text' name='aid' value='${s_id }'
				readonly></td>
		</tr>
		<tr>
			<th colspan='4'><hr></th>
		</tr>
		<tr>
			<th>접근성</th>
			<th>청결성</th>
			<th>안정성</th>
			<th>시설</th>
		</tr>
		<tr>
			<td><select name="staaccess">
					<option value='1'>1</option>
					<option value='2'>2</option>
					<option value='3'>3</option>
					<option value='4'>4</option>
					<option value='5'>5</option>
			</select></td>
			<td><select name="staclean">
					<option value='1'>1</option>
					<option value='2'>2</option>
					<option value='3'>3</option>
					<option value='4'>4</option>
					<option value='5'>5</option>
			</select></td>
			<td><select name="stasafety">
					<option value='1'>1</option>
					<option value='2'>2</option>
					<option value='3'>3</option>
					<option value='4'>4</option>
					<option value='5'>5</option>
			</select></td>
			<td><select name="stafacility">
					<option value='1'>1</option>
					<option value='2'>2</option>
					<option value='3'>3</option>
					<option value='4'>4</option>
					<option value='5'>5</option>
			</select></td>
		</tr>
		<tr>
			<th colspan='4'><hr></th>
		</tr>
		<tr>
			<th>코멘트</th>
			<td colspan='3'><input type="text" name="stacoment"></td>
		</tr>
		<tr>
			<th colspan='4'><hr></th>
		</tr>
		<tr>
			<td colspan='3'></td>
			<td><input type='submit' value='평가하기'></td>
		</tr>

	</table>
</Form>

<!-- 본문 끝 -->
<jsp:include page="../footer.jsp"></jsp:include>