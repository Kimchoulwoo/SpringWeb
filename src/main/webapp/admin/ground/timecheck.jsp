<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>

		<c:set var="x" value="${stadiumdto.staopen }" /> <c:forEach
			var="i" begin="${stadiumdto.staopen }" end="${stadiumdto.staclose }">
			<input type="checkbox" name="grtime" value="${x }">${x }:00
					<c:set var="x" value="${ x+1 }" />
		</c:forEach>