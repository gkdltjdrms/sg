/**
 * 
 */

 
 function checkWirte(){
	 document.getElementById("subjectDiv").innerText = "";
	 document.getElementById("contentDiv").innerText = "";
	 
	 if(document.getElementById("subject").value == "")
	 	document.getElementById("subjectDiv").innerText = "제목 입력";
	 
	  else if(document.getElementById("content").value == "")
	 	document.getElementById("contentDiv").innerText = "내용 입력";
	 	
	 	else
	 	document.guestbookwriteForm.submit();
	 
 }