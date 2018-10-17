<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="../header2.jsp"></jsp:include>
<!-- 본문 시작 template.jsp -->
<!-- Page Content -->
<div class="container-fluid" id="div-content"
	style="padding-left: 0px; margin-bottom: 20px;">
	<div class="col-xs-2" style="height: 680px; padding-left: 0px;"
		id="div_col2">
		<div id="div_left" class="w3-sidebar w3-light-grey w3-bar-block">
			<h3 class="w3-bar-item">매치</h3>
			<a href="../team/teamlist.do" class="w3-bar-item w3-button">팀</a> <a
				href="../club/clublist.do" class="w3-bar-item w3-button">클럽</a>
			<li class="w3-bar-item w3-button">매치
				<ul style="list-style:none; border:0px; margin-left:10px; padding:0px">
					<li><a href="../matching/match.do">매치신청</a></li>
					<li><a href="../matching/agreematch.do">매치수락</a></li>
				</ul>
			</li>
		</div>
	</div>
	<div class="col-xs-10"
		style="margin: 0px; margin-top: 10px; padding-left: 0px;">
		<!-- Page Content -->
		<div id="div_content" style="height: 100%">
			<!-- 팀선택 select -->
			<div style="width: 300px; height: 100px">
				<c:if test="${empty sessionScope.s_id }">
				로그인 후 이용해주세요
				</c:if>
				<c:if test="${sessionScope.s_id!=null }">
					<form name="tnameList" action="./match.do">
						<select name="tcode" onchange="selected(this.value)">
							<option value="0" selected="selected">팀을 선택해주세요.</option>
							<c:if test="${teamlist eq null }">
							<option value="1">등록된 팀이 없습니다.</option>
							</c:if>
							<c:forEach var="teamlist" items="${teamlist }">
								<option value="${teamlist.tcode}">${teamlist.tname }</option>
							</c:forEach>
						</select>
					</form>
				</c:if>
			</div>
			<!-- 팀 정보 출력 -->
			<c:if test="${hometeam ne null }">
			<div style="width:950px; height:400px; text-align: center">
				<div style="width:40%; height:500px; float:left;">
					<img src="../club/clubimages/${hometeam.cposter }" style='width:350px; height: 350px;'>
					<h3>${hometeam.tname }</h3>					
				</div>
				<div style="width:15%; height:500px; float:left; text-align: center">
					<br><br><br><br><br>
					<button onclick='tleader("${hometeam.tid}","${sessionScope.s_id }","${hometeam.tcode }", "${now }", ${awaycnt })' style='margin:auto;'>상대검색</button><br><br>
					<button onclick='match("${hometeam.tcode }","${awaylist.tcode }")' style='margin:auto;'>매치신청</button>
				</div>
				<c:if test="${awaylist ne null }">
				<div style="width:40%; height:500px; float:left;">
					<img src="../club/clubimages/${awaylist.cposter }" style='width:350px; height: 350px;'>
					<h3>${awaylist.tname }</h3>
				</div>
				</c:if>
			</div>
			</c:if>
			${alert }
			
		</div>
	</div>
</div>

<script>
	function selected(val) {
		document.tnameList.submit(tcode(val));
	}//selected() end

	//선택된 팀코드 전송
	function tcode(val) {
		if (val == "0") {
			alert("팀을 선택해주세요.")
			return false;
		} else if (val == "1") {
			alert("등록된 팀이 없습니다.")
			return false;
		}
		return true;
	}//tcode() end
	
	//상대검색 팀장만
	function tleader(tid, s_id, tco,now,awaycnt){
		if(tid==s_id){
			if(awaycnt==0){
				alert("조건에 맞는팀이 없습니다.");			
			}else{
				if(now.length==0) {
				   now=0;
				   location.href="./search.do?tcode="+tco+"&now=" + now;
			    }else {
			    	if(now<awaycnt){
			    		now=parseInt(now);
			    		if(now+1<awaycnt){
			    			location.href="./search.do?tcode="+tco+"&now="+(now+1);
			    		}else{
			    			alert("더 이상 조건에 맞는 팀이 없습니다. \n처음으로 돌아갑니다.");
			    			now=0;
							location.href="./search.do?tcode="+tco+"&now=" + now;
			    		}//if end
			    	}//if end
			    }//if end
			}//if end			
		}else{
			alert("팀장만 가능합니다.");
		}//if end
	}//tleader() end
	
	function match(homecode, awaycode){
		location.href="./comeon.do?homecode="+homecode+"&awaycode="+awaycode;
	}//match() end
	
</script>

<!-- 본문 끝 -->
<jsp:include page="../footer2.jsp"></jsp:include>