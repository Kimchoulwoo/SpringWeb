<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../leftside.jsp"></jsp:include>
<script src="https://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript"
	src="../../resources/editor/js/HuskyEZCreator.js" charset="utf-8"></script>

<!-- 본문 시작 -->
<h4>
	<small>공지사항 입력</small>
</h4>
<hr>
<form name="noticefrm" method="post" id="insertBoardFrm"
	enctype="multipart/form-data" action="./create.do"
	onsubmit="return noticeCheck(this)" class="noticefrm">
	
	<input type="text" name='subject'>
	<textarea name="content" id="editor" style="width: 700px; height: 400px;"></textarea>
	<input type="button" id="insertBoard" value="등록" />

</form>

<script>
	function noticeCheck(f) {
		var subject = f.subject.value;
		var content = f.content.value;

		subject = subject.trim();
		content = content.trim();

		if (subject.length<2 || subject.length>20) {
			alert("제목은 2~20글자로만 입력가능합니다.");
			return false;
		}
		if (content.length<2 || subject.length>300) {
			alert("내용은 2~300글자로만 입력가능합니다.");
			return false;
		}
		return true;
	}//noticeCheck() end
</script>

<script type="text/javascript">
	$(function() {
		//전역변수
		var oEditors = [];
		//스마트에디터 프레임생성
		nhn.husky.EZCreator.createInIFrame({
			oAppRef : oEditors,
			elPlaceHolder : "editor",
			sSkinURI : "../../resources/editor/SmartEditor2Skin.html",
			htParams : {
				// 툴바 사용 여부
				bUseToolbar : true,
				// 입력창 크기 조절바 사용 여부
				bUseVerticalResizer : true,
				// 모드 탭(Editor | HTML | TEXT) 사용 여부
				bUseModeChanger : true,
				fOnBeforeUnload : function(){
				
				}
			},
			fOnAppLoad : function(){ 
				//기존 저장된 내용의 text 내용을 에디터상에 뿌려주고자 할때 사용 
				oEditors.getById["editor"].exec("PASTE_HTML", ["공지사항을 입력해주세요"]); },

		});

		//전송버튼
		$("#insertBoard").click(function() {
			//id가 smarteditor인 textarea에 에디터에서 대입
			oEditors.getById["editor"].exec("UPDATE_CONTENTS_FIELD", []);
			//폼 submit
			$("#insertBoardFrm").submit();
		});
	});
</script>



<!-- 본문 끝 -->
<jsp:include page="../footer.jsp"></jsp:include>