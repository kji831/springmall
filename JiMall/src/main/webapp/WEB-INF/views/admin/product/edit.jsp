<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>	
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

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
		<option value="default">2차 카테고리</option>
		{{#each .}}
		<option value="{{cate_thcode}}">{{cate_name}}</option>
		{{/each}}
  </script>

  <%-- ckEditor랑 2차 버튼 클릭 이벤트 --%>
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
			$("#mainCategory").on("change", function(){
				var mainCateList = $(this).val(); // 선택 한 1차 카테고리
				var url = "/admin/product/subCateList/" + mainCateList; // url매핑주소를 경로형태로 사용 @PathVarialbe
				
				// Rest 방식으로 전송
				$.getJSON(url, function(data){
					// 받은 데이터로 subCategory에 탬플릿 적용
					subCateList(data, $("#subCategory"), $("#subCateListTemplate"))
				});
			});

			// 상품 목록 버튼 클릭 시 
			$("#btn_list").on("click", function(){
				var result = confirm("내용을 저장하지 않고, 목록으로 돌아가시겠습니까??");

				if(result){
					location.href="/admin/product/list${pm.makeSearch(pm.cri.page)}";
				}else{}
			});
	  });
  </script>

  <%-- 2차 카테고리 템플릿 적용함수 --%>
  <script>
	  var subCateList = function(subCateStr, target, templateObject){

		var template = Handlebars.compile(templateObject.html());
		var options = template(subCateStr); // 템플릿에 2차 카테고리데이터가 바인딩 된 소스

		// 기존 option 제거 (누적방지)
		$("#subCategory option").remove();
		target.append(options);
		
	  }
  </script>

  <script>
	  var fileChange = function(fcs){
		  var str = fcs.value;
		  $("#fileName").html("파일이 변경 됨");
	  }
  </script>

  <!--<%--<script src="/js/admin/insert.js"></script>--%>-->
	
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
           <h2>상품 수정</h2>
           <form id='editForm' role="form" action="/admin/product/edit" method="post" enctype="multipart/form-data">
								<div class="box-body">
									
									<div>
										<input type="hidden" name="page" value="${cri.page}" />
										<input type="hidden" name="perPageNum" value="${cri.perPageNum}" />
										<input type="hidden" name="searchType" value="${cri.searchType}" />
										<input type="hidden" name="keyword" value="${cri.keyword}" />
									</div>

									<div class="form-group">
										<input name="prod_num" type="hidden" value="${vo.prod_num}" />
										<label for="exampleInputEmail1" style="width:30%; margin-right:20px;" >1차 카테고리</label>
										<label for="exampleInputEmail1" style="width:30%;" >2차 카테고리</label> <br />
										<select class="form-control" id="mainCategory" name="cate_pracode" style="width:30%; margin-right:10px; display: inline-block;" >
										  <option value="default">1차 카테고리 선택</option>
										  <c:forEach items="${cateList}" var="list">
										  	<option value="${list.cate_thcode}"<c:out value="${vo.cate_pracode ==list.cate_thcode?'selected':''}" />>${list.cate_name}</option>
										  </c:forEach>
										</select>
										<select class="form-control" id="subCategory" name="cate_thcode" style="width: 30%; display: inline-block;">
											 <option value="default">2차 카테고리 선택</option>
											 <c:forEach items="${subCateList}" var="subList">
												 <option value="${subList.cate_thcode}"<c:out value="${vo.cate_thcode == subList.cate_thcode?'selected':''}" />>${subList.cate_name}</option>
											 </c:forEach>
										</select>
									</div>
									<div class="form-group">
										<label for="exampleInputEmail1">상품명</label> <input
											type="text" id="prod_name" name="prod_name" class="form-control"
											value="${vo.prod_name}">
									</div>
									<div class="form-group">
										<label for="exampleInputEmail1">회사명</label> <input
											type="text" id="prod_develop" name="prod_develop" class="form-control"
											value="${vo.prod_develop}">
									</div>
									<div class="form-group">
										<label for="exampleInputEmail1" style="width:40%; margin-right:10px;">가격</label> 
										<label for="exampleInputEmail1" style="width:40%;">할인율</label> 
										<input style="width:40%; margin-right:10px; display: inline-block;"
											type="text" id="prod_price" name="prod_price" class="form-control" 
											value="${vo.prod_price}" />
										<input style="width:40%; display: inline-block;"
											type="text" id="prod_discount" name="prod_discount" class="form-control "
											value="${vo.prod_discount}" />
									</div>
									<div class="form-group">
										<label for="exampleInputPassword1">상세소개</label>
										<textarea class="form-control" id="prod_detailint" name="prod_detailint" rows="8"
											value="prod_detailint"></textarea>
									</div>

									<div class="form-group">
										<input type="hidden" name="prod_img" value="${vo.prod_img}" />
										<label for="exampleInputEmail1">상품 이미지</label> 
										<span id="fileName" style="margin-left:5px; font-size:14px;">현재 등록 된 파일 : <c:out value="${originFile}" /></span>
										<input	type="file" id="file1" name="file1" class="form-control" />
									</div>
									
									<div class="form-group">
										<label for="exampleInputEmail1" style="width:30%; margin-right:10px;">남은 수량</label> 
										<label for="exampleInputEmail1" style="width:15%;">상품 구매 가능</label><br /> 
										<input style="width:30%; margin-right:10px; display: inline-block;"
											type="text" id="prod_cominven" name='prod_cominven' class="form-control" 
											value="${prod_cominven}" />
										<select class="form-control" id="prod_buypos" name="prod_buypos" style="width: 15%; display: inline-block;">
										  <option <c:out value="${vo.prod_buypos == 'Y'?'selected':''}" />>Y</option>
										  <option <c:out value="${vo.prod_buypos == 'N'?'selected':''}" />>N</option>
										</select>
									</div>
									<div>
										<label for="exampleInputEmail1" style="width:40%; margin-right:10px;">Submit Date</label>
										<label for="exampleInputEmail1" style="width:40%; ">Update Date</label>
										<span class="form-control" style="width:40%; margin-right:10px; display:inline-block;">
											<fmt:formatDate value="${vo.prod_regisdate}" pattern="yyyy-MM-dd HH:mm:ss" />
										</span>
										<span class="form-control" style="width:40%; display:inline-block;">
											<fmt:formatDate value="${vo.prod_updateDate}" pattern="yyyy-MM-dd HH:mm:ss" />
										</span> 
									</div>
								</div>

								<!-- /.box-body -->

								<div class="box-footer">
									<div>
										<hr>
									</div>

									<ul class="mailbox-attachments clearfix uploadedList">
									</ul>

									<button id="btn_submit" type="submit" class="btn btn-primary">상품 수정</button>
									<button id="btn_list" type="button" class="btn btn-primary">목록으로</button>

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

