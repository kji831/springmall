<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
  
 <script type="text/javascript" src=""></script>
  
  <!-- Custom styles for this template -->
<%@ include file="/WEB-INF/views/common/bootcss.jsp" %>
  
<script type="text/javascript" src="/js/cart/list.js"></script>
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
   <div class="box" style="border: none;">
    <form method="post" action="/order/buyFromCart">
    <div class="btn-container" style="display: inline-block; float: right; margin:20px 10px 5px 5px;">
      <button id="btn_buy_check"  class="btn btn-primary" type="submit" >선택 상품 구매</button>
      <button id="btn_delete_check" class="btn btn-default" type="button" >선택 상품 삭제</button>
    </div>
    <div class="box-body">
      <table class="table table-striped text-center">
        <tr>
          <th><input type="checkbox" id="checkAll" checked="checked"/></th>
          <th>번호</th>
          <th>상품 이미지</th>
          <th>상품명</th>
          <th>판매가</th>
          <th>할인가</th>
          <th>수량</th>
          <th>구매/삭제</th>
        </tr>
        
        <%-- 상품이 존재하지 않는 경우 --%>
        <c:if test="${empty cartProductList}">
          <tr>
            <td colspan="10"> 
              <p style="padding:50px 0px; text-align: center;">장바구니에 담긴 상품이 없습니다.</p>
            </td>
          <tr>
        </c:if>
        
        <%-- 상품이 존재하는 경우,  리스트 출력 --%>
        <%--JSTL 변수 선언 --%>
        <c:set var="i" value="${fn:length(cartProductList)}" />
        <c:forEach items="${cartProductList}" var="cartProductVO">
          <tr>
            <td class="col-md-1">
              <input type="checkbox" name="check" class="check" value="${cartProductVO.cart_code}" checked="checked" >
              <input type="hidden" id="prod_num_${cartProductVO.cart_code}" name="prod_num" value="${cartProductVO.prod_num}" >
              <input type="hidden" name="cart_puamount" value="${cartProductVO.cart_puamount}" >
              <input type="hidden" name="cart_code" value="${cartProductVO.cart_code}" >
            </td>
            <td class="col-md-1">${i}</td>
            <td class="col-md-2">
              <a href="/product/read?prod_num=${cartProductVO.prod_num}&cate_thcode=${cate_thcode}">
                <img src="/product/displayFile?fileName=${cartProductVO.prod_img}" style="width:100px;">
              </a>
            </td>
            <td class="col-md-2">
              <a href="/product/read?prod_num=${cartProductVO.prod_num}&cate_thcode=${cate_thcode}"
                style="color: black;"> ${cartProductVO.prod_name} </a>
            </td>
            <td class="col-md-1">
              <p>${cartProductVO.prod_price}</p>
              <input type="hidden" name="price_${cartProductVO.cart_code}" value="${cartProductVO.prod_price}" /></td>
            <td class="col-md-1">
              <p>${cartProductVO.prod_discount}</p>
              <input type="hidden" name="discount_${cartProductVO.cart_code}" value="${cartProductVO.prod_discount}" /></td>
            <td class="col-md-2">
              <input type="number" name="cart_puamount_${cartProductVO.cart_code}"
                style="width:60px; height:34px; padding-left:5px;" value="${cartProductVO.cart_puamount}" />
              <button type="button" name="btn_modify" class="btn btn-default" value="${cartProductVO.cart_code}" >변경</button>
            </td>
            <td class="col-md-2">
              <button type="button" name="btn_buy" class="btn btn-primary" value="${cartProductVO.cart_code}"
                onclick="clickBuyBtn(${cartProductVO.prod_num}, ${cartProductVO.cart_code});">구매</button>
              <button type="button" name="btn_delete" class="btn btn-default" value="${cartProductVO.cart_code}" >삭제</button>
            </td>
            <c:set var="i" value="${i-1}" ></c:set>
          </tr>

        </c:forEach>
      </table>
    </div>
    </form>
    <div class="box">
      <table class="table table-striped text-center" >
        <tr>
          <td class="col-md-1">총 상품금액</td>
          <td class="col-md-1">결제 예정 금액</td>
        </tr>
        <tr >
          <td class="col-md-1" style="height:50px; text-align: center;"><p id="totalPrice">0</p></td>
          <td class="col-md-1" style="height:50px; text-align: center;"><p id="totalDiscount">0</p></td>
        </tr>
      </table>
    </div>
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