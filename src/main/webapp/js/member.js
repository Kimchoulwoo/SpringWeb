/*
 myscript.js
 */

function pwCheck(f) {
	var pw = f.pw.value;
	if (pw.length != 4) {
		alert("비밀번호를 4글자로 입력해 주세요.");
		f.pw.focus();
		return false;
	}// end

}// pwCheck() end

function memberCheck(f) {
	// 회원가입 유효성 검사

	// 1) 아이디 영문대,소문자 , 숫자 4 ~12 자리
	var id = f.id.value;
	id = id.trim();
	var patternId = /^[A-Za-z0-9]{4,10}$/;
	if (!patternId.test(id)) {
		alert("id는 영어(대,소) , 숫자 포함 4~10 자리로 입력해 주세요.");
		return false;
	}// if end

	// 2) 비밀번호 4~10 글자 이내
	var pw = f.pw.value;
	var patternPw = /^[A-Za-z0-9]{4,10}$/;
	if (!patternPw.test(pw)) {
		alert("pw는 영어(대,소), 숫자 포함 4~10 자리로 입력해 주세요.");
		return false;
	}// if end

	// 3) 2개의 비밀번호가 서로 일치하는지 검사
	var repw = f.repw.value;
	repw = repw.trim();
	if (pw != repw) {
		alert("비밀번호가 동일하지 않습니다.");
		return false;
	}//if end

	// 4) 이름 2글자 이상 한+영
	var name = f.name.value;
	name = name.trim();
	var pattenName = /^[가-힣]{2,4}|[a-zA-Z]{2,10}\s[a-zA-Z]{2,10}$/;
	if (!pattenName.test(name)) {
		alert("이름을 2~10 글자로 입력하세요.(한글,영어만)");
		return false;
	}//if end


	// 5) 이메일 @문자가 있는지 검사
	var mail = f.mail.value;
	mail = mail.trim();
	var pattenMail=/([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/;
	if (!pattenMail.test(mail)) {
		alert("올바른 이메일 형식이 아닙니다.");
		return false;
	}

	// 6) 생일에 숫자만 입력
	var year = f.birthy.value;
	var month = f.birthm.value;
	var date = f.birthd.value;
	var today = new Date(); // 날자 변수 선언
    var yearNow = today.getFullYear();

	var pattenNum = /^[0-9]+$/;

	if(!pattenNum.test(year) && year.leanth!=4){
		alert("년도에 4자리 숫자만 입력하세요.");
		return false;
	}
	if(!pattenNum.test(month) && month.leanth!=2){
		if(month<1 || month>12)
		alert("월에 2자리 숫자만 입력하세요.");
		return false;
	}
	if(!pattenNum.test(date) && year.leanth!=2){
		if(date<1 || date >31)
		alert("일에 2자리 숫자만 입력하세요.");
		return false;
	}
	 if ((month==4 || month==6 || month==9 || month==11) && date==31) {
          alert(month+"월은 31일이 존재하지 않습니다.");
          return false;
     }
     if (month == 2) {
          var isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
          if (date>29 || (date==29 && !isleap)) {
               alert(year + "년 2월은  " + date + "일이 없습니다.");
               return false;
          }
     }//if end

	// 7) 휴대폰 번호 숫자만 입력
	var tel = f.tel.value;
	var pattenNum2 = /^[0-9]+$/;
	if(!pattenNum2.test(tel) && tel.length<13){
		alert("- 를 제외한 숫자만 입력하세요.(최대 11자리)");
		return false;
	}

	// 유효성검사를 통과 했으므로 회원가입폼을 서버로 전송
	return true;

}// memberCheck() end

function updateCheck(f) {
	// 회원가입 유효성 검사
	f.birth.value = f.birth_y.value + f.birth_m.value + f.birth_d.value;
	alert(f.birth.value);
	// 1) 아이디 4~10 글자 이내
	var id = f.id.value;
	id = id.trim();// 공백제거
	if (id.length < 4 || id.length >= 11) {
		alert("아이디를 4~10글자 이내로 입력하세요.");
		return false; // 호출시점으로 되돌아 감
	}

	// 2) 비밀번호 4~10 글자 이내
	var pw = f.pw.value;
	if (pw.length < 4 || pw.length >= 11) {
		alert("비밀번호를 4~10글자 이내로 입력하세요.");
		f.pw.focus();
		return false;
	}

	// 3) 2개의 비밀번호가 서로 일치하는지 검사
	var repw = f.repw.value;
	repw = repw.trim();
	if (repw.length < 4 || repw.length >= 11) {
		alert("비밀번호를 4~10글자 이내로 입력하세요.");
		f.repw.focus();
		return false;
	}

	if (pw != repw) {
		alert("비밀번호가 동일하지 않습니다.");
		return false;
	}

	// 4) 이름 2글자 이상
	var name = f.name.value;
	name = name.trim();
	if (name.length < 2) {
		alert("이름을 2글자 이상 입력하세요.");
		f.name.focus();
		return false;
	}

	// 5) 이메일 @문자가 있는지 검사
	var email = f.email.value;
	email = email.trim();
	if (email.length < 5) {
		alert("입력된 주소가 너무 짧습니다.");
		return false;
	}

	if (email.indexOf("@") < 0) {
		alert("잘못된 이메일 형식입니다.");
		return false;
	}

	if (isNan(f.birth.value)) {
		alert("생일은 숫자만 입력 가능합니다.");
		return false;
	}
	// 유효성검사를 통과 했으므로 회원가입폼을 서버로 전송
	// return true;

	var mess = "진행된 내용은 복구되지 않습니다.\n계속 진행 할까요?";
	if (confirm(mess)) { // 확인 true, 취소 false

		// return true;
	} else {
		// return false;
	}

}// memberCheck() end

function idCheck() {
	// 아이디 중복확인

	// 새창이 출력되는 위치 지정
	var sx = parseInt(screen.width);
	var sy = parseInt(screen.height);
	var x = (sx / 2) + 50;
	var y = (sy / 2) - 25;

	// 새창띄우기
	var win = window.open("duplicateID.do", "idwin", "width=400, height=350");

	// 화면이동
	win.moveTo(x, y);

}// idCheck() end

function emailCheck() {
	// 이메일 중복확인

	// 새창이 출력되는 위치 지정
	var sx = parseInt(screen.width);
	var sy = parseInt(screen.height);
	var x = (sx / 2) + 50;
	var y = (sy / 2) - 25;

	// 새창띄우기
	var win = window.open("emailCheckForm.jsp", "emailwin",
			"width=400,height=350");

	// 화면이동
	win.moveTo(x, y);
}// emailCheck() end

function loginCheck(f) {
	// 로그인 유효성 검사
	// 1) 아이디 4~10 글자 이내
	var id = f.id.value;
	id = id.trim();// 공백제거
	if (id.length < 4 || id.length >= 11) {
		alert("아이디를 4~10글자 이내로 입력하세요.");
		return false; // 호출시점으로 되돌아 감
	}

	// 2) 비밀번호 4~10 글자 이내
	var passwd = f.passwd.value;
	// passwd=passwd.replace(/^\s+|\s+$/gm, '');
	passwd = passwd.trim();
	if (passwd.length < 4 || passwd.length >= 11) {
		alert("비밀번호를 4~10글자 이내로 입력하세요.");
		f.passwd.focus();
		return false;
	}
	return true;
}// loginCheck() end
