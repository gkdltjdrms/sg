/**
 * 
 */

function checkWrite() {

//if(document.writeForm.name.value == "") alert("이름을 입력하세요")

document.getElementById("nameDiv").innerText = "";

document.getElementById("idDiv").innerText = "";

document.getElementById("pwdDiv").innerText = "";

if (document.getElementById("name").value == "")

document.getElementById("nameDiv").innerText = "이름 입력";

else if (document.getElementById("id").value == "")

document.getElementById("idDiv").innerText = "아이디 입력";

else if (document.getElementById("pwd").value == "")

document.getElementById("pwdDiv").innerText = "비밀번호 입력";

else if (document.getElementById("pwd").value != document.getElementById("repwd").value)

document.getElementById("pwdDiv").innerText = "비밀번호가 맞지 않습니다";

else

document.writeForm.submit();

}


const form = document.loginform;

function check_login() {
	document.getElementById("id_check").innerText = ""
	document.getElementById("pw_check").innerText = ""
	
	if (form.login_id.value == "") {
		document.getElementById("id_check").innerText = "아이디를 입력하세요."
	}
	else if (form.login_pw.value == "") {
		document.getElementById("pw_check").innerText = "비밀번호를 입력하세요."
	}
	else {
		document.loginform.submit();
	}
}





function select() {

document.writeForm.email2.value = document.writeForm.email3.value;

}









/* Daum 우편 번호 */

function execDaumPostcode() {

new daum.Postcode({

oncomplete: function(data) {

// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

// 각 주소의 노출 규칙에 따라 주소를 조합한다.

// 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.

var addr = ''; // 주소 변수

var extraAddr = ''; // 참고항목 변수

//사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.

if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우

addr = data.roadAddress;

} else { // 사용자가 지번 주소를 선택했을 경우(J)

addr = data.jibunAddress;

}

// 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다. => 제거!★

// 우편번호와 주소 정보를 해당 필드에 넣는다.

document.getElementById('zipcode').value = data.zonecode;

document.getElementById("addr1").value = addr;

// 커서를 상세주소 필드로 이동한다.

document.getElementById("addr2").focus();

}

}).open();

}