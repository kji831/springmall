$(document).ready(function(){

	var form = $("#loginForm");
	
	$("#btn_login").on("click", function(){
		
		var spmem_id = $("#spmem_id");
		var spmem_pw = $("#spmem_pw");
		
		if(spmem_id.val()==null || spmem_id.val()==""){
			$("#id_blank").html("아이디를 먼저 입력해주세요.");
			spmem_id.focus();
		
		}else if(spmem_pw.val()==null || spmem_pw.val()==""){
			$("#pw_blank").html("비밀번호를 먼저 입력해주세요.");
			spmem_pw.focus();
		
		}else{
			form.submit();
		}
	});
});

