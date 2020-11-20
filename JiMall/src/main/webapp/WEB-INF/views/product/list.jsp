<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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
    // 장바구니 버튼 클릭 시
    var cart_click = function(prod_num){
        $.ajax({
            url: "/cart/add",
            type: "post",
            dataType: "text",
            date: {prod_num : prod_num},
            success: function(data){
              var result = confirm("장바구니에 추가되었습니다. \n지금 확인하시겠습니까??");
              if(result){
                location.href="/cart/list";
              }else{}
            }
        });
    }
  </script>
  
  <style>
    ul{
        list-style: none;
        padding-left: 0px;
    } 
    .product{
      display: inline-block;
      margin:10px;
      padding:22px 40px;
    }
    .description{
      margin: 10px;
    }
  </style>

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
		
		
   <!-- left column -->
   <%-- 검색 조건 설정 및 페이지 이동에도 해당 값 유지 --%>
   <div class="box" style="border: none;">
    <div class="box-body">
      <table class="table table-striped text-center">
        <tr>
          <th>상품 번호</th>
          <th>상품 이미지</th>
          <th>상품명</th>
          <th>판매가</th>
          <th>할인가</th>
          <th>제조사</th>
          <th>수량</th>
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
              <img src="/product/displayFile?fileName=${productVO.prod_img}" style="width:80px;">
              

              <!--  용도?-->
              <input type="hidden" name="img_${productVO.prod_num}" value="${productVO.prod_img}" />



            </td>
            <td class="col-md-2"><a
              href="/product/read${pm.makeQuery(pm.cri.page)}&prod_num=${productVO.prod_num}"
              style="color: black;"> ${productVO.prod_name} </a></td>
            <td class="col-md-1">${productVO.prod_price}</td>
            <td class="col-md-1">${productVO.prod_discount}</td>
            <td class="col-md-2">${productVO.prod_develop}</td>
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
              <a href="list${pm.makeQuery(pm.startPage-1)}&cate_thcode=${cate_thcode}">&laquo;</a>
            </li>
          </c:if>
          <!-- 페이지목록번호 :  1  2  3  4  5  -->
          <c:forEach begin="${pm.startPage}" end="5"
            var="idx">
            <li <c:out value="${pm.cri.page == idx?'class=active':''}" />>
              <a href="list${pm.makeQuery(idx)}&cate_thcode=${cate_thcode}">${idx}</a>
            </li>
          </c:forEach>
          <!-- 다음표시 여부  [다음]-->
          <c:if test="${pm.next && pm.endPage > 0}">
            <li><a href="list${pm.makeQuery(pm.endPage +1)}&cate_code=${cate_thcode}">&laquo;</a>
            </li>
          </c:if>
        </ul>  
        
      </div>

    </div>
    <!-- /.box-footer-->
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