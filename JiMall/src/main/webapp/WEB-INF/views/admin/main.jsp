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
  
	
  <!-- Custom styles for this template -->
  <%@ include file="/WEB-INF/views/common/bootcss.jsp" %> 	
 
</head>

<body>

  <!-- Navigation -->
  <%@ include file="/WEB-INF/views/common/adminTop.jsp" %>

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
           <h2></h2>
           <form>
           		<table class="table table-boardered">
           			<tr>
           				<td colspan="2" class="text-center">
           					<button type="button"  onclick="location.href='/admin/insert'" class="btn btn-primary"> 상품등록 </button>
	           				<button type="button"  onclick="location.href='/admin/logout'" class="btn btn-primary"> 로그아웃 </button>
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

