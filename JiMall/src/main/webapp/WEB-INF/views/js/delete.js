$(document).ready(function(){
if("${msg}" == "CHECK_PW_FAIL"){
	alert("비밀번호가 다릅니다.");
}
});

$(document).ready(function(){
	$("#btn_join").on("click", function(){
		if($("#spmem_pw").val()== null || $("#spmem_pw").val()==""){
			alert("비밀번호를 입력해주세요.");
		} else {
			$("#checkPwForm").submit();
		}
	});
});
