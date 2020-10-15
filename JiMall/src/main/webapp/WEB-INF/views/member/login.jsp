<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>

  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">

  <title>Shop Homepage - Start Bootstrap Template</title>

  <!-- Bootstrap core CSS -->
  <!-- Bootstrap core JavaScript -->
  <%@ include file="/WEB-INF/views/common/bootjs.jsp" %>
  
  <script src="/js/login.js"></script>
	
  <!-- Custom styles for this template -->
  <%@ include file="/WEB-INF/views/common/bootcss.jsp" %> 	
 
</head>

<body>

  <!-- Navigation -->
  <%@ include file="/WEB-INF/views/common/top.jsp" %>

  <!-- Page Content -->
  <div class="container">

    <div class="row">
		<!-- 카테고리 메뉴 -->
      <div class="col-lg-3">

        <%@ include file="/WEB-INF/views/common/category.jsp" %>

      </div>
      <!-- /.col-lg-3 -->

      <div class="col-lg-9">
		<!-- main-carousel begin  -->
        <%@ include file="/WEB-INF/views/common/carousel.jsp" %>
		<!-- main-carousel end -->
		
		
        <div class="row">
		
		<!-- 로그인 -->
          
           <div class="col">
           <h2>로그인</h2>
           <form id="loginForm" action="/member/login" method="post">
           		<table class="table table-boardered">
           			<tr>
           				<th>아이디</th>
           				<td>
           					<input type="text" id="spmem_id" class="form-control" name="spmem_id" placeholder="아이디를 입력하세요" >
           					<p id="id_blank" style="color: red;"></p>
           				</td>
           			</tr>
           			<tr>
           				<th>비밀번호</th>
           				<td>
           					<input type="password" id="spmem_pw" class="form-control" name="spmem_pw" placeholder="비밀번호를 입력하세요">
           					<p id="pw_blank" style="color: red;"></p>
           				</td>
           			</tr>
           			<tr>
           				<td colspan="2" class="text-center">
	           				<button type="button"  id="btn_login" class="btn btn-primary">로그인</button>
	           				<button type="reset" id="btn_cancle" class="btn btn-primary">취소</button>
	           			</td>	
           			</tr>
           		</table>
           </form>
    		<!-- 
    		<div class="login-form">
			    <form action="/examples/actions/confirmation.php" method="post">
			        <h2 class="text-center">Log in</h2>       
			        <div class="form-group">
			            <input type="text" class="form-control" placeholder="Username" required="required">
			        </div>
			        <div class="form-group">
			            <input type="password" class="form-control" placeholder="Password" required="required">
			        </div>
			        <div class="form-group">
			            <button type="submit" class="btn btn-primary btn-block">Log in</button>
			        </div>
			        <div class="clearfix">
			            <label class="float-left form-check-label"><input type="checkbox"> Remember me</label>
			            <a href="#" class="float-right">Forgot Password?</a>
			        </div>        
			    </form>
			    <p class="text-center"><a href="#">Create an Account</a></p>
</div>
    
   		   </div>
          -->
		<!-- 로그인 -->
        </div>
        <!-- /.row -->

      </div>
      <!-- /.col-lg-9 -->

    </div>
    <!-- /.row -->

  </div>
  <!-- /.container -->

  <!-- Footer -->
  <%@ include file="/WEB-INF/views/common/bottom.jsp" %>

  

</body>

</html>

