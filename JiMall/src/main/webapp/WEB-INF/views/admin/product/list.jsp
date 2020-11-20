<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
  
  <script>
    $(document).ready(function(){
        /* 검색버튼 클릭 시*/
        $("#btn_search").on("click", function(){
            self.location = "list"
                + '${pm.makeQuery(1)}'
                + "&searchType="
                + $("select option:selected").val()
                + "&keyword=" + $('#keyword').val();
        });

        // 전체선택 체크박스 클릭 시
        $("#checkAll").on("click", function(){
            // 전체선택 클릭 여부로 다른 체크박스 선택
            $(".check").prop('checked', this.checked);
        });

        // 체크박스 중 선택 안 된 체크박스 존재 시 전체선택 해제
        $(".check").on("click", function(){
            $("#checkAll").prop('checked', false);
        });

        // 선택 상품 수정 버튼 클릭 시
        $("#btn_edit_check").on("click", function(){

            // 체크 여부 유효성 검사
            if($("input[name='check']:checked").length==0){
              alert("수정 할 상품을 선택 해주세요.");
              return;
            }

            var checkArr = [];
            var cominvenArr = [];
            var buyposArr = [];

            var prod_cominven = $("#prod_cominven").val();
            var prod_buypos = $("#prod_buypos:selected").val();

            // 체크 된 상품의 value(prod_num)을 가져옴
            $("input[name='check']:checked").each(function(i){
                var prod_num = $(this).val();
                var prod_cominven = $("input[name='cominven_" + prod_num +"']").val();
                var prod_buypos = $("select[name='buypos_" + prod_num + "']").val();

                checkArr.push(prod_num);
                cominvenArr.push(prod_cominven);
                buyposArr.push(prod_buypos);

            });

            $.ajax({
                url: '/admin/product/editChecked',
                type: 'post',
                dataType: 'text',
                data: {
                        checkArr : checkArr,
                        cominvenArr : cominvenArr,
                        buyposArr : buyposArr
                },
                success : function(data){
                  alert("수정이 완료되었습니다.");
                  location.href="/admin/product/list${pm.makeSearch(pm.cri.page)}";
                }
            });

        });

        // 선택 상품 삭제 버튼 클릭 시
        $("#btn_delete_check").on("click", function(){

            // 체크여부 유효성 검사
            if($("input[name='check']:checked").length==0){
              alert("삭제 할 상품을 선택 해주세요.")
              return;
            }

            // 체크 된 상품이 존재 할 경우 진행
            var result = confirm("선택 한 상품을 삭제하시겠습니까??");
            if(result){

                var checkArr = [];
                var imgArr = [];

                // 체크 된 상품의 value(prod_num)을 가져 옴
                $("input[name='check']:checked").each(function(i){
                    var prod_num = $(this).val();
                    var prod_img = $("input[name='img_" + prod_num + "']").val();

                    checkArr.push(prod_num);
                    imgArr.push(prod_img);
                });

                $.ajax({
                    url: '/admin/product/deleteChecked',
                    type: 'post',
                    dataType: 'text',
                    data:{
                            checkArr : checkArr,
                            imgArr : imgArr
                    },
                    success : function(data){
                      alert("삭제가 완료되었습니다.");
                      location.href = "/admin/product/list${pm.makeSearch(pm.cri.page)}";
                    }
                });
            } else{}
        });

        // 상품 리스트 삭제 버튼 클릭 시
        $("btn_delete_${prductVO.prod_num}").on("click", function(){
            var result = confirm("이 상품을 삭제하시겠습니까??");
            if(result){
              $(".deleteForm").submit();
            } else{}
        });

    });
  </script>

  <script>
    // 상품 수정 버튼 클릭 시
    var clickEdit = function(prod_num){
      var url = '/admin/product/edit${pm.makeSearch(pm.cri.page)}&prod_num=' + prod_num;
      location.href = url;
    };
  </script>
  
  <!-- Custom styles for this template -->
<%@ include file="/WEB-INF/views/common/bootcss.jsp" %>
  

</head>

<body>

  <!-- Navigation -->
  <%@ include file="/WEB-INF/views/common/adminTop.jsp" %>

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
		
		
   <!-- left column -->
   <%-- 검색 조건 설정 및 페이지 이동에도 해당 값 유지 --%>
   <div class="col-md-12">
     <div class="row text-center">
       <div style="display: inline-block; float: left; margin-left:15px;">
       <select name="searchType" style="width:180px; height:26px;">
         <option value="null"
           <c:out value="${cri.searchType == null?'selected':''}" />>검색조건 선택</option>
         <option value="name"
           <c:out value="${cri.searchType eq 'name'?'selected':''}" />>상품명</option>
         <option value="detail"
           <c:out value="${cri.searchType eq 'detailint'?'selected':''}" />>내용</option>
         <option value="company"
           <c:out value="${cri.searchType eq 'develope'?'selected':''}" />>제조사</option>
         <option value="name_detail"
           <c:out value="${cri.searchType eq 'name_detailint'?'selected':''}" />>상품명+내용</option>
         <option value="name_company"
           <c:out value="${cri.searchType eq 'name_develope'?'selected':''}" />>상품명+제조사</option>
         <option value="all"
           <c:out value="${cri.searchType eq 'all'?'selected':''}" />>상품명+내용+제조사</option>
       </select> 
       <input type="text" name='keyword' id="keyword" style="width:250px; padding-left:5px;" value='${cri.keyword}' />
       <button id="btn_search" class="btn btn-default">검색</button>
       </div>
       <div style="display: inline-block; float: right; margin-right:15px;">
       <button id="btn_edit_check"  class="btn btn-primary" >선택 상품 수정</button>
       <button id="btn_delete_check"  class="btn btn-primary" >선택 상품 삭제</button>
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
            <th>판매가</th>
            <th>할인가</th>
            <th>제조사</th>
            <th>수량</th>
            <th>판매여부</th>
            <th>수정/삭제</th>
          </tr>
          
          <%-- 상품 리스트 출력 --%>
          <!-- 이 empty구문은 필요 없는 구문이지만 empty라는걸 한 번 써보기 위해서 사용했다는 걸 기억하기-->
          <c:if test="${empty productList}">
            <tr>
              <td colspan="10"> 
                <p style="padding:50px 0px; text-align: center;"><td colspan="10"> 
              <p style="padding:50px 0px; text-align: center;">등록된 상품이 존재하지 않습니다.</p>
            </td></p>
              </td>
            <tr>
          </c:if>
          <c:forEach items="${productList}" var="productVO">
            <tr>
              <td><input type="checkbox" name="check" class="check" value="${productVO.prod_num}" style=""></td>
              <td class="col-md-1">${productVO.prod_num}</td>
              <td class="col-md-2">
                <img src="/admin/product/displayFile?fileName=${productVO.prod_img}" style="width:80px;">
                

                <!--  용도?-->
                <input type="hidden" name="img_${productVO.prod_num}" value="${productVO.prod_img}" />



              </td>
              <td class="col-md-2"><a
                href="/admin/product/read${pm.makeSearch(pm.cri.page)}&prod_num=${productVO.prod_num}"
                style="color: black;"> ${productVO.prod_name} </a></td>
              <td class="col-md-1">${productVO.prod_price}</td>
              <td class="col-md-1">${productVO.prod_discount}</td>
              <td class="col-md-2">${productVO.prod_develop}</td>
              
              <!-- 상품 전시(보임/숨김)기능 -->
              <td><input name="cominven_${productVO.prod_num}" type="number" style="width:80px; height:34px; padding-left:5px;" value="${productVO.prod_cominven}" /></td>
              <td>
                <select class="form-control" name="buypos_${productVO.prod_num}" style="width: 60px; display: inline-block;">
                  <option <c:out value="${productVO.prod_buypos == 'Y'?'selected':''}"/>>Y</option>
                  <option <c:out value="${productVO.prod_buypos == 'N'?'selected':''}"/>>N</option>
                </select>
              </td>
              <td class="col-md-2">
                <form class="deleteForm" method="post"
                  action="/admin/product/delete${pm.makeSearch(pm.cri.page)}" value="${prod_num}">
                  <!-- 상품 코드 -->
                  <input type="hidden" name="prod_num"
                    value="${productVO.prod_num}">
                  <!-- 파일 이미지명 -->
                  <input type="hidden" name="prod_img"
                    value="${productVO.prod_img}">
                    <!-- 수정기능 -->
                  <button type="button" name="btn_edit" class="btn btn-primary" onclick="clickEdit(${productVO.prod_num});">수정</button>
                  
                  <!-- 삭제기능 -->
                  <button type="button" name="btn_delete" class="btn btn-danger">삭제</button>
                </form>
              </td>
            </tr>

          </c:forEach>
        </table>
      </div>
      <!-- /.box-body -->


      <!-- 페이징 기능 -->
      <div class="box-footer">

        <div class="text-center">
          <ul class="pagination">
            <!-- 이전표시 여부  [이전] -->
            <c:if test="${pm.prev}">
              <li>
                <a href="list${pm.makeSearch(pm.startPage-1)}"></a>
              </li>
            </c:if>
            <!-- 페이지목록번호 :  1  2  3  4  5  -->
            <c:forEach begin="${pm.startPage}" end="5"
              var="idx">
              <li <c:out value="${pm.cri.page == idx?'class=active':''}" />>
                <a href="list${pm.makeSearch(idx)}">${idx}</a>
              </li>
            </c:forEach>
            <!-- 다음표시 여부  [다음]-->
            <c:if test="${pm.next && pm.endPage > 0}">
              <li><a href="list${pm.makeSearch(pm.endPage +1)}"></a>
              </li>
            </c:if>
          </ul>  
          
        </div>

      </div>
      <!-- /.box-footer-->
    </div>

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