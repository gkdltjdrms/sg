/**
 * 
 */



 function select(){
				document.writeForm.email2.value = document.writeForm.email3.value;
			}
			
			function checkwrite() {
				  if(document.getElementById("name").value == "") {
				    document.getElementById("nameDiv").innerText = "이름 입력";
				  } else if(document.getElementById("id").value == "") {
				    document.getElementById("idDiv").innerText = "아이디 입력";
				  } else if(document.getElementById("pwd").value == "") {
				    document.getElementById("pwdDiv").innerText = "비밀번호 입력";
				  } else if(document.getElementById("pwd").value != document.getElementById("repwd").value) {
				    document.getElementById("pwdDiv").innerText = "비밀번호가 맞지 않습니다"; 
				  }
				}