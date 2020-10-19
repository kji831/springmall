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
  
  <script src="/ckeditor/ckeditor.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>

  <%-- 핸들바 --%>
  <script id="subCateListTemplate" type="text/x-handlebars-template">
		<option value="default">2차 카테고리 선택</option>
		{{#each .}}
		<option value="{{cate_thcode}}">{{cate_name}}</option>
		{{/each}}
  </script>

  <%-- ckEditor랑 2차 카테고리 처리 이벤트 --%>
  <script>
	  $(document).ready(function(){
			// ckeditor 작업
			// json문법 사용
			var ckeditor_config = {
					resize_enabled : false,
					enterMode : CKEDITOR.ENTER_BR,
					shiftEnterMode : CKEDITOR.ENTER_P,
					toolbarCanCollapse : true,
					removePlugins : "elementspath",
					// 파일 업로드 기능 추가
					// ckEditor 이용해서 업로드 시 이게 들어가야 됨
					filebrowserUploadUrl : '/admin/product/imgUpload'
			};
			CKEDITOR.replace("prod_detailint", ckeditor_config);
			// config.js의 설정 사용
			// CKEDITOR.replace("desc", "");
			
			// 1차 카테고리에 따른 2차 카테고리 작업
			$("#mainCatego0ry").on("change", function(){
				var mainCateCode = $(this).val(); // 선택 한 1차 카테고리
				var url = "/admin/product/subCateList" + mainCateCode // url매핑주소를 경로형태로 사용 @PathVarialbe
				
				// Rest 방식으로 전송
				$.getJSON(url, function(data){
					// 받은 데이터로 subCategory에 탬플릿 적용
					subCateList(data, $("#subCategory"), $("#subCateListTemplate"))
				});
			});
	  });
  </script>

  <%-- 2차 카테고리 템플릿 적용함수 --%>
  <script>
	  var subCateList = function(subCateStr, target, templateObject){

		var template = Handlebars.compile(templateObject.html());
		var options = template(subCateStr); // 템플릿에 2차 카테고리데이터가 바인딩 된 소스

		// 기존 option 제거 (누적방지)
		$("$#subCategory option").remove();
		target.append(options);
		
	  }
  </script>

  <script src="/js/admin/insert.js"></script>
	
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
           <h2>상품 등록</h2>
           <form id='registerForm' role="form" action="/admin/insert" method="post" enctype="multipart/form-data">
								<div class="box-body">
									<div class="form-group">
										<label for="exampleInputEmail1" style="width:30%; margin-right:20px;" >1차 카테고리</label>
										<label for="exampleInputEmail1" style="width:30%;" >2차 카테고리</label> <br />
										<select class="form-control" id="mainCategory" name="cate_pracode" style="width:30%; margin-right:10px; display: inline-block;" >
										  <option value="default">1차 카테고리 선택</option>
										  <c:forEach items="${cateList}" var="vo">
										  	<option value="${vo.cate_thcode}">${vo.cate_name}</option>
										  </c:forEach>
										</select>
										<select class="form-control" id="subCategory" name="cate_thcode" style="width: 30%; display: inline-block;">
										 	<option value="default">2차 카테고리 선택</option>
										</select>
									</div>
									<div class="form-group">
										<label for="exampleInputEmail1">상품명</label> <input
											type="text" id="prod_name" name="prod_name" class="form-control"
											placeholder="Enter Product name">
									</div>
									<div class="form-group">
										<label for="exampleInputEmail1">회사명</label> <input
											type="text" id="prod_develop" name="prod_develop" class="form-control"
											placeholder="Enter company">
									</div>
									<div class="form-group">
										<label for="exampleInputEmail1" style="width:40%; margin-right:10px;">가격</label> 
										<label for="exampleInputEmail1" style="width:40%;">할인율</label> 
										<input style="width:40%; margin-right:10px; display: inline-block;"
											type="text" id="prod_price" name="prod_price" class="form-control" 
											placeholder="Enter price" />
										<input style="width:40%; display: inline-block;"
											type="text" id="prod_discount" name="prod_discount" class="form-control "
											placeholder="Enter discounted price" />
									</div>
									<div class="form-group">
										<label for="exampleInputPassword1">상세소개</label>
										<textarea class="form-control" id="prod_detailint" name="prod_detailint" rows="8"
											placeholder="Enter ..."></textarea>
									</div>

									<div class="form-group">
										<label for="exampleInputEmail1">상품 이미지</label> <input
											type="file" id="file1" name="file1" class="form-control" />
									</div>
									
									<div class="form-group">
										<label for="exampleInputEmail1" style="width:30%; margin-right:10px;">남은 수량</label> 
										<label for="exampleInputEmail1" style="width:15%;">상품 구매 가능</label><br /> 
										<input style="width:30%; margin-right:10px; display: inline-block;"
											type="text" id="prod_cominven" name='prod_cominven' class="form-control" 
											placeholder="Enter Amount" />
										<select class="form-control" id="prod_buypst" name="prod_buypst" style="width: 15%; display: inline-block;">
										  <option>Y</option>
										  <option>N</option>
										</select>
									</div>
								</div>

								<!-- /.box-body -->

								<div class="box-footer">
									<div>
										<hr>
									</div>

									<ul class="mailbox-attachments clearfix uploadedList">
									</ul>

									<button id="btn_submit" type="button" class="btn btn-primary">상품 등록</button>

								</div>
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

