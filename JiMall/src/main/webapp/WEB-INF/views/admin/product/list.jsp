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

  
	
  <!-- Custom styles for this template -->
  <%@ include file="/WEB-INF/views/common/bootcss.jsp" %> 	
 
</head>

<body>

  <!-- Navigation -->
  <%@ include file="/WEB-INF/views/common/adminTop.jsp" %>

  <!-- Page Content -->
  <div class="container">

	<!-- row -->
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
		
          
           <div class="col">
           <h2>상품 등록</h2>
           <div class="col-md-12">
						<div class="row text-center">
							<div style="display: inline-block; float: left; margin-left:15px;">
							<select name="searchType" style="width:180px; height:26px;">
								<option value="null"
									<c:out value="${cri.searchType == null?'selected':''}"/>>검색조건 선택</option>
								<option value="name"
									<c:out value="${cri.searchType eq 'name'?'selected':''}"/>>상품명</option>
								<option value="detailin"
									<c:out value="${cri.searchType eq 'detailint'?'selected':''}"/>>내용</option>
								<option value="develop"
									<c:out value="${cri.searchType eq 'develop'?'selected':''}"/>>제조사</option>
								<option value="name_detailint"
									<c:out value="${cri.searchType eq 'name_detailint'?'selected':''}"/>>상품명+내용</option>
								<option value="name_develop"
									<c:out value="${cri.searchType eq 'name_develop'?'selected':''}"/>>상품명+제조사</option>
								<option value="all"
									<c:out value="${cri.searchType eq 'all'?'selected':''}"/>>상품명+내용+제조사</option>
							</select> 
							<input type="text" name='keyword' id="keyword" style="width:250px; padding-left:5px;" value='${cri.keyword}' />
							<button id="btn_search" class="btn btn-default">검색</button>
							</div>
							<div style="display: inline-block; float: right; margin-right:15px;">
							<button id="btn_edit_check"  class="btn btn-default" >선택 상품 수정</button>
							<button id="btn_delete_check"  class="btn btn-default" >선택 상품 삭제</button>
							<button class="btn btn-primary"
								onClick="location.href='/admin/product/insert'">상품 등록</button>
							</div>	
						</div>	
							<br>
			<div class="box" style="border: none;">
				<div class="box-body">
					<table class="table table-striped text-center">
						<tr>
							<th><input type="checkbox" id="checkAll" /></th>
							<th>상품 번호</th>
							<th>상품 이미지</th>
							<th>상품명</th>
							<th>판매 가격</th>
							<th>할인율</th>
							<th>제조사</th>
							<th>남은 수량</th>
							<th>판매 여부</th>
							<th>수정/삭제</th>
						</tr>

						<%-- 상품 리스트 출력하기 --%>
						<c:forEach items="${productList}" var="productVO">
							<tr>
								<td><input type="checkbox" name="check" class="check" value="${productVO.prod_num}" /></td>
								<td class="col-md-1">${productVO.prod_num}</td>
								<td class="col-md-2">
									<img src="/admin/product/displayFile?fileName=${productVO.prod_img}" />

									<img type="hidden" name="img_${productVO.prod_num}" value="${productVO.prod_img}" />

								</td>
								
							</tr>
						</c:forEach>
					</table>
				</div>

			</div>				
		</div>
		</div>
		</div>					
		<!-- 로그인 -->
        </div>
        <!-- /.row -->
	  </div>
	  
      <!-- /.col-lg-9 -->
    </div>
  
  <!-- /.container -->
  </div>

  <!-- Footer -->
  <%@ include file="/WEB-INF/views/common/bottom.jsp" %>

  

</body>

</html>

