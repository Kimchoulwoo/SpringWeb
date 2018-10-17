<%@ page contentType="text/html; charset=UTF-8"%>
<%@ include file="../header2.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!-- 본문 시작 template.jsp -->
<!-- Page Content -->
<div class="container-fluid" id="div-content"
	style="padding-left: 0px; margin-bottom: 20px;">
	<div class="col-xs-2" style="height: 680px; padding-left: 0px;"
		id="div_col2">
		<div id="div_left" class="w3-sidebar w3-light-grey w3-bar-block">
			<h3 class="w3-bar-item">매치</h3>
			<a href="../team/teamlist.do" class="w3-bar-item w3-button">팀</a> <a
				href="../club/clublist.do" class="w3-bar-item w3-button">클럽</a> <a
				href="../matching/match.do" class="w3-bar-item w3-button">매치</a>
		</div>
	</div>
	<div class="col-xs-10"
		style="margin: 0px; margin-top: 10px; padding-left: 0px;">
		<!-- Page Content -->
		<div id="div_content" style="height: 100%">

			<!-- 본문 시작 -->
			<br> <br>
			<!-- form에 enctype="multipart/form-data" 을 안해주면 파일 업로드시 객체 변환 안됐다고 오류 -->
			<form name="teamCreate" method="POST" action="./createclub.do" enctype="multipart/form-data" onsubmit="return clubCheck(this.form)">
				<table>
					<tr>
						<th>클럽 이름 :</th>
						<td><input type="text" name="cname" id="cname" size="20" required></td>
					</tr>
					<tr>
						<th>활동지역 :</th>
						<td><input type="text" name="carea"></td>
					</tr>
					<tr>
						<th>클럽소개</th>
						<td><textarea id='ccontent' name='ccontent' style="width: 700px; height: 400px;"></textarea></td>
					</tr>
					<tr>
						<th>클럽 대표사진</th>
						<td><INPUT type='file' name='posterMF' size='50'></td>
					</tr>
					<tr>
						<td colspan='3'></td>
						<td><input type='submit' value='클럽 등록'> 
							  <a href='./clublist.do'><input type='button' value='취소'></a></td>
					</tr>
				</table>
			</form>

			<script>
				function clubCheck(f) {
					// 클럽명
					var cname= f.cname.value;
					cname = cname.trim();
					if (cname.length<2 || cname.length>20) {
						alert("클럽명은 2~20 자리로 입력해 주세요.");
						return false;
					}// if end
					
					// 활동지역
					var carea= f.carea.value;
					carea = carea.trim();
					if (carea.length<1) {
						alert("활동지역을 입력해주세요");
						return false;
					}// if end
					
					// 클럽소개
					var ccontent= f.ccontent.value;
					ccontent = ccontent.trim();
					if (ccontentlength<2 || cname.length>1000) {
						alert("클럽소개는 2~1000로 입력해 주세요.");
						return false;
					}// if end
					return true;
				}
			</script>
		</div>
	</div>
</div>


<!-- 본문 끝 -->
<jsp:include page="../footer2.jsp"></jsp:include>