$(document).ready(function(){
    
    var form = $("#modifyForm");

    // 아이디 중복체크 기능 사용여부 확인 변수
    var checkId = "true";

    // 이메일 변경 시 이메일 인증 활성화
    $("#spmem_email").on("change", function(){
        $("#btn_sendAuthcode").show
        checkEmail = "false";
    });

    // 이메일 인증 클릭시
    $("#btn_sendAuthCode").on("click", function(){
        
        var receiveMail = $("spmem_email").val();

        if($("#spmem_email").val()=="" || $("#spmem_email").val()==null){
            $("#authcode_status").html("이메일을 먼저 입력하세요");
            return;
        }

        $.ajax({
            url: '/email/send',
            type: 'post',
            dataType: 'text',
            data: {receiveMail : receiveMail},
            success: function(data){
                $("email_authcode").show();
                $("#authocode_status").css("color", "black");
                $("#authcode_status").html("메일이 전송되었습니다.  입력하신 이메일주소에서 인증번호를 확인 후 입력해주세요.");
            }
        });
    });

    // 인증코드 입력 후 확인 클릭 시
    $("#btn_checkAuthCode").on("click", function(){
        var code = $("#mem_authcode").val()
    });

    $.ajax({
        url: '/member/checkAuthcode',
        type: 'post',
        dataType: 'text',
        data: {code : code},
        success: function(data){
            if(data == 'SUCCESS'){
                $("#email_authcode").hide();
                $("#authcode_status").css("color", "blue");
                $("#authcode_status").html("인증성공");

                checkEmail = "true";

                return;

            }else{
                $("#email_authcode").hide();
                $("#authcode_status").css("color", "red");
                $("#authcode_status").html("인증 실패.  다시 시도해주세요.");
                return;
            }
        }
    });

    // 회원수정 버튼 클릭 시
    $("btn_join").on("click", function(){
    
    	alert("눌리냐?")

        var spmem_id = $("#spmem_id");
        var spmem_pw = $("#spmem_pw");
        var spmem_pw_check = $("#spmem_pw_check");
        var spmem_nick = $("#spmem_nick");
        var spmem_email = $("#spmem_email");
        var mem_authcode =$("#mem_authcode");
        var spmem_postcode = $("#spmem_postcode");
        var spmem_add = $("#spmem_add");
        var spmem_deAdd = $("#spmem_deAdd");
        var spmem_phonNum = $("#spmem_phonNum");

            
        if (spmem_id.val()=="" || spmem_id.val()==null){
            alert("아이디를 입력해주세요.");
            spmem_id.focus();
            
        }else if(checkId=="false"){
            alert("아이디 중복체크를 해주세요.");
            $("#btn_checkId").focus();
            
        }else if(spmem_pw.val()=="" || spmem_pw.val()==null){
            alert("비밀번호를 입력해주세요.");
            spmem_pw.focus();
            
        }else if(spmem_pw_check.val()=="" || spmem_pw_check.val()==null){
            alert("비밀번호 확인을 해주세요.");
            spmem_pw_check.focus();
            
        }else if(spmem_nick.val()=="" || spmem_nick.val()==null){
            alert("닉네임을 입력해주세요.");
            spmem_nick.focus();
            
        }else if(spmem_email.val()=="" || spmem_email.val()==null){
        	alert("이메일을 입력해주세요.");
        	spmem_email.focus();
        
        }else if(mem_authcode.val()=="" || mem_authcode.val()==null){
        	alert("이메일 인증번호를 확인해주세요.");
        	mem_authcode.focus();
        
        }else if(checkEmail=="false"){
            alert("이메일 인증을 해주세요.");
            $("#btn_sendAuthCode").focus();
            
        }else if(spmem_postcode.val()=="" || spmem_postcode.val()==null){
            alert("우편번호를 입력해주세요.");
            spmem_postcode.focus();
            
        }else if(spmem_add.val()=="" || spmem_add.val()==null){
            alert("주소를 입력해주세요.");
            spmem_add.focus();
            
        }else if(spmem_deAdd.val()=="" || spmem_deAdd.val()==null){
            alert("상세주소를 입력해주세요.");
            spmem_deAdd.focus();
            
        }else if(spmem_phonNum.val()=="" || spmem_phonNum.val()==null){
            alert("핸드폰 번호를 입력해주세요.");
            spmem_phonNum.focus();
            
        }else{
            form.submit();
        }
    }); 
   
    // 현재 비밀번호 확인 검사
    var spmem_pw_val = spmem_pw.val();
    $.ajax({
        url: "/member/checkPwAjax",
        type: "post",
        datatype: "text",
        data: {spmem_pw : spmem_pw_val},
        success: function(data){
            if(data == "SUCCESS"){
                form.submit();
            }else{
                alert("비밀번호가 다릅니다.");
                spmem_pw.val("");
                spmem_pw.focus();
            }
        }
    });

    $("btn_cancle").on("click", function(){

        var result = confirm("회원 수정을 취소하시겠습니까??");
        if(result){
            location.href="/";
        }else{}
    });
});

