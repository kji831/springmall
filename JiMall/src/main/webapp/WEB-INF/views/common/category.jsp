<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>   

<%-- 핸들바 템플릿 --%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<script id="subCeteListTemplage" type="text/x-handlebars-template">
    {{#each .}}
        <li><a href="/product/list?cate_thcode={{cate_thcode}}">{{cate_thcode}}</a></li>
    {{/each}}    
</script>

<%-- 2차 카테고리 템플릿 --%>
<script>
    $(document).ready(function(){
        // 1차 카테고리에 따른 2차 카테고리 작업
        $(".mainCategory").one("click", function(){
            var mainCateCode = $(this).val();
            var url = "/product/subCateList/" + mainCateCode

            // REST 방식으로 전송
            $.getJSON(url, function(date){
                // 받은 데이터로 subCategory에 템플릿 적용
                subCateList(data, $("#mainCategory_"+mainCateCode), $("#subCateListTemplate"));

//list-group
            });
        });
    });

    var subCateList = function(subCateStr, target, templateObject){

          var template = Handlebars.compile(templateObject.html());
          var options = template(subCateStr);

          target.append(options);

    }
</script>

 <h1 class="my-5">JI MALL</h1>
       
 
 <aside class="main-sidebar" ">
	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar">

		<!-- Sidebar Menu -->
		<div class="list-group" data-widget="tree">
			<%-- 로그인 안 한 상태 --%>
			<c:if test="${sessionScope.user == null}"> 
				<div class="header">MENU</li>
				<div>
					<a href="/member/join">회원 가입</a>
				</div>
				<div>
					<a href="/member/login">로그인</a>
				</div>		
			</c:if>
			
			<%-- 로그인 한 상태 --%>
			<c:if test="${sessionScope.user != null}"> 
				<!-- 검색 -->
				
				
				<!-- 카테고리 -->
				<div class="list-group" >CATEGORY</div>
				<c:forEach items="${userCategoryList}" var="list">
					<div class="treeview mainCategory"  value="${list.cate_thcode}">
						<a href="/product/list?cate_thcode=${list.cate_thcode}">
							<div class="fa fa-link"></i>
							<span>${list.cate_name}</span>
							<span class="pull-right-container">
								<div class="fa fa-angle-left pull-right"></i>
							</span>
						</a>
						<!--  2차카테고리 자식수준으로 추가작업 -->
						<div class="treeview-menu" id="mainCategory_${list.cate_thcode}"></div>
					</div>
				</c:forEach>
			</c:if>	
		</div>
		<!-- /.sidebar-menu -->
	</section>
	<!-- /.sidebar -->
</aside>