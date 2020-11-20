$(document).ready(function() {
	// 처음에 가격 업데이트
	updatePrice();

	// 전체 선택 체크박스 클릭 시
	$("#checkAll").on("click", function(){
		$(".check").prop('checked', this.checked);
		updatePrice();
	});

	// 체크박스 중 선택 안 된 체크박스 존재 시 전체선택 해제
	$(".check").on("click", function(){
		$("#checkAll").prop('checked', false);
		updatePrice();
	});

	// 선택 상품 삭제 버튼 클릭 시
	$("#btn_delete_check").on("click", function(){

		// 체크여부 유효성 검사
		if($("input[name='check']:checked").length == 0) {
			alert("삭제할 상품을 선택해 주세요.");
			return;
		}

		// 체크 된 상품이 존재 할 경우
		var result = confirm("선택한 상품을 삭제하시겠습니까??");

		if(result) {
			var checkArr = [];

			// 체크 된 상품의 value(cart_code) 를 가져옴
			$("input[name='check']:checked").each(function(i) {
				var cart_code = $(this).val();
				checkArr.push(cart_code);
			});

			// 배열값을 ajax전송 시 스프링에서 받는 파라미터 처리 구문
			$.ajax({
				url: '/cart/deleteChecked',
				type: 'post',
				dataType: 'text',
				data: {
					checkArr : checkArr
				},
				success : function(data) {
					location.href = "/cart/list";
				}
			});
		}else{
		}
	});

	// 상품 리스트 - 상품 수량 변경 클릭 시
	$("button[name=btn_modify]").on("click", function(){

		var cart_code = $(this).val();
		var cart_puamount = $("input[name='cart_puamount_]" + cart_code + "']").val();

		$.ajax({
			url: "/cart/modify",
			type: "post",
			dataType: "text",
			data: {
				cart_code : cart_code,
				cart_puamount : cart_puamount
			},
			success : function(data) {
				alert("수량이 변경되었습니다.");
				location.href = "/cart/list";
			}
		});
	});
	
	// 상품 리스트 삭제
	$("button[name=btn_delete]").on("click", function(){
		var cart_code = $(this).val();
		$.ajax({
			url: "/cart/delete",
			type: "post",
			data: {
				cart_code : cart_code
			},
			dataType: "text",
			success: function(data) {
				location.href = "/cart/list";
			}
		});
	});
});


var updatePrice = function(){

	var size = $("input[name='check']:checked").length;
	var totalPrice = 0;
	var totalDiscount = 0;

	$("input[name='check']:checked").each(function(i){
		var cart_code = $(this).val();
		var amount = $("input[name='cart_puamount_" + cart_code +"']").val();
		var test = $("input[name='price_" + cart_code + "']").val();

		totalPrice += parseInt($("input[name='price_" + cart_code + "']").val()) * amount;
		totalDiscount += parseInt($("input[name='discount_" + cart_code + "']").val()) * amount;
	});

	$("#totalPrice").html(numberFormat(totalPrice) + "원");
	$("#totalDiscount").html(numberFormat(totalDiscount) + "원");
}

function numberFormat(inputNumber) {
	return inputNumber.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

var clickBuyBtn = function(prod_num, cart_code) {

	var cart_puamount = $("input[name='cart_puamount_" + cart_code + "']").val();
	var url = "/order/buyFromCart?prod_num=" + prod_num + "&ode_puamount=" + cart_puamount;
	location.href = url;
}