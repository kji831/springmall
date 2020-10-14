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
  
  <script src="/js/changePw.js"></script>
  
  <!-- Custom styles for this template -->
<%@ include file="/WEB-INF/views/common/bootcss.jsp" %>
  

</head>

<body>

  <!-- Navigation -->
  <%@ include file="/WEB-INF/views/common/top.jsp" %>

  <!-- Page Content -->
  <div class="container">

    <div class="row">

      <div class="col-lg-3">

        <%@ include file="/WEB-INF/views/common/category.jsp" %>

      </div>
      <!-- /.col-lg-3 -->

      <div class="col-lg-9">
		<!-- main-carousel begin  -->
        <%@ include file="/WEB-INF/views/common/carousel.jsp" %>
		<!-- main-carousel end -->
		
		
        <div class="row">
		
		
    <h2>비밀번호 확인</h2>
    <form id="checkPwForm"   method="post">
        <table class="table table-boardered">
            <tr>
            	<td>
					<input type="hidden" name="url" value="${url}" />
					<input type="password" class="form-control" id="spmem_pw" name="spmem_pw" placeholder="비밀번호를 입력해주세요.">            
            	</td>
            </tr>
            <tr>
            	<td>
            		<input type="button" id="btn_join" class="btn btn-primary" value="확인">
            	</td>
            </tr>
        </table>
    </form>
    
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