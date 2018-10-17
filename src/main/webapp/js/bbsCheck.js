function formCheck(f) {
	var subject = f.subject.value;
	var content = f.content.value;
	var passwd  = f.passwd.value;

	subject = subject.trim();
	content = content.trim();
	passwd  = passwd.trim();

	//1) 제목 글자수 제한
	if(subject.length < 2 || subject.length > 100) {
		alert("제목 이름 2~50글자 이내에서 입력해 주세요");
		f.subject.focus();
		return false;
	}

	//2) 내용 글자수 제한
	if(content.length < 1 || content.length > 255) {
		alert("내용을 1~255 글자 입력해 주세요");
		f.content.focus();
		return false;
	}

	//3) 비밀번호 제한
	if(passwd.length < 4 || passwd.length > 10) {
		alert("비밀번호 4~10 자 입력해 주세요");
		f.passwd.focus();
		return false;
	}
	
	var id = f.id.value;
	
	if (id == "") {
		alert("회원만 작성이 가능합니다.");
		return false;
	}

	return true;  
}

function idCheck(f) {
	var id = f.id.value;
	if (id == "") {
		alert("회원만 작성이 가능합니다.");
		return false;
	}

	return true;
}