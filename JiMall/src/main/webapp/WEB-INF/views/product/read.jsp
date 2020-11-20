<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">

<title>Shop Homepage - Start Bootstrap Template</title>

<!-- Bootstrap core CSS -->
<!-- Bootstrap core JavaScript -->
<%@ include file="/WEB-INF/views/common/bootjs.jsp" %>

<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/3.0.1/handlebars.js"></script>
<script type="text/javascript" src="/js/product/read.js"></script>

<script id="template" type="text/x-handlebars-template">
	{{#each .}}
		<li class="replyLi" data-rev_num={{rev_num}}>
        	<i class="fa fa-comments bg-blue"></i>
            <div class="timeline-item" >
                <span class="time">
                	<i class="fa fa-clock-o"></i>{{prettifyDate rev_regdate}}
                </span>
                <h3 class="timeline-header">
					<strong>{{checkRating rev_score}} <p class='rev_score' style="display:inline-block;">{{rev_score}}</p></strong> 
					</h3>
                <div class="timeline-body">
					NUM: {{rev_num}} <p style="float:right;">작성자: {{spmem_id}}</p> <br>
					<p id='rev_content'>{{rev_content}}</p> </div>
				<div class="timeline-footer" style="float:right;">
					{{eqReplyer spmem_id rev_num rev_score}}
				</div>
	         </div>			
         </li>
	{{/each}}
</script>

<script>
    $(document).ready(function(){

        $("#btn_list").on("click", function(){
            location.href="/product/list${pm.makeQuery(pm.cri.page)}&cate_thcode=${list.cate_thcode}";
        });

        Handlebars.registerHelper("prettifyDate", function(timeValue){
            var dateObj = new Date(timeValue);
            var year = dateObj.getFullYear();
            var month = dateObj.getMonth() + 1;
            var date = dateObj.getDate();

            return year + "/" + month + "/" + date;
        });

        Handlebars.registerHelper("checkRating", function(rating){
            var stars = "";
			switch(rating){
				case 1:
					 stars="★☆☆☆☆";
					 break;
				case 2:
					 stars="★★☆☆☆";
					 break;
				case 3:
					 stars="★★★☆☆";
					 break;
				case 4:
					 stars="★★★★☆";
					 break;
				case 5:
					 stars="★★★★★";
					 break;
				default:
					stars="☆☆☆☆☆";
			}
			return stars;
        });

        Handlebars.registerHelper("eqReplyer", function(replyer, rev_num, rev_score){
            var btnHtml = '';
            var spmem_id = "${sessionScope.user.spmem_id}";
            if(replyer == "${user.spmem_id}"){
                btnHtml = "<button class='btn btn-primary btn-xs' name='modify' data-rev_num='" + rev_num + "' data-rev_score='" + rev_score + "'>"
                        + "MODIFY</button>"
                        + "<button class='btn btn-danger btn-xs' style='margin-left:5px;'"
                        + "onclick='deleteReview("+rev_num+");'"
                        + "type='button' >DELETE</button>";
            }
            return new Handlebars.SafeString(btnHtml);
        });
    });
    
</script>

  <!-- Custom styles for this template -->
  <%@ include file="/WEB-INF/views/common/bootcss.jsp" %>

  <%-- 스타일 --%>
  <style>
       #star_grade a{
           font-size:22px;
          text-decoration: none;
          color: lightgray;
      }
      #star_grade a.on{
          color: black;
      }
      
      #star_grade_modal a{
           font-size:22px;
          text-decoration: none;
          color: lightgray;
      }
      #star_grade_modal a.on{
          color: black;
      }
      
      .popup {position: absolute;}
      .back { background-color: gray; opacity:0.5; width: 100%; height: 300%; overflow:hidden;  z-index:1101;}
      .front { 
         z-index:1110; opacity:1; boarder:1px; margin: auto; 
        }
       .show{
         position:relative;
         max-width: 1200px; 
         max-height: 800px; 
         overflow: auto;       
       } 
  </style>  
  
</head>

<body>

    <!-- Navigation -->
    <%@ include file="/WEB-INF/views/common/top.jsp" %>
  
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
             <h2>상품 정보</h2>
             
             <div class="box-body">
                <div class="form-group container" style="margin:30px 0px; height:350px;" >
                    <div style="float:left; width:30%; height:100%;">
                        <img src="/product/displayFile?fileName=${vo.prod_img}" style="display: inline; width:90%;">
                    </div>
                    <div style="display: inline-block; margin-left:20px;">
                        <label for="exampleInputEmail1">상품 이름</label><br>
                        <span>${vo.prod_name}</span><br><br>
                        
                        <label for="exampleInputEmail1">제작사</label><br>
                        <span>${vo.prod_develop}</span><br><br>
                        
                        <div>
                            <label for="exampleInputEmail1" style="width:100px; margin-right:10px;">가격</label> 
                            <label for="exampleInputEmail1" style="width:100px;">할인율</label> <br>
                            <span style="width:100px; margin-right:10px; display:inline-block;">
                                <fmt:formatNumber value="${vo.prod_price}" pattern="###,###,###" />원
                            </span>
                            <span style="width:100px; display:inline-block;">
                                <fmt:formatNumber value="${vo.prod_discount}" pattern="###,###,###" />%
                            </span>
                        </div>
                        <br>
                        
                        <div>
                            <form method="get" action="/order/buy" >
                                <label for="exampleInputEmail1">개수</label><br>
                                <input type="number" id="ode_puamount" name="ode_puamount" value="1" /><br><br>
                                <input type="hidden" id="prod_num" name="prod_num" value="${vo.prod_num}" />
                                <button type="submit" id="btn_buy" class="btn btn-primary">구매하기</button>
                                <!-- 장바구니 기능으로 진행 -->
                                <button type="button" id="btn_cart" class="btn btn-primary">장바구니</button>
                            </form>
                        </div>
                        
                    </div>
                </div>
                <!-- 상품 상세 -->
                <label for="detail">상품 내용</label><br>
                <div contenteditable="false" style="border: 1px solid grey; padding: 20px;">
                    ${vo.prod_detailint}
                </div>
                <br>
                
                <%-- 상품 후기 --%>
                <div class='popup back' style="display:none;"></div>
                <div id="popup_front" class='popup front' style="display:none;">
                     <img id="popup_img">
                </div>
                <form role="form" action="modifyPage" method="post">
                    
                    <input type='hidden' name='page' value="${cri.page}"> 
                    <input type='hidden' name='perPageNum' value="${cri.perPageNum}">
                    <%-- 
                    <input type='hidden' name='searchType' value="${cri.searchType}">
                    <input type='hidden' name='keyword' value="${cri.keyword}">
                     --%>
                </form>
                
                <div>
                    <!-- 상품후기쓰기 부분 -->
                     <div>
                        <label for="review">Review</label><br>
                        <div class="rating">
                            <p id="star_grade">
                                <a href="#">★</a>
                                <a href="#">★</a>
                                <a href="#">★</a>
                                <a href="#">★</a>
                                <a href="#">★</a>
                            </p>
                        </div>
                        <textarea id="reviewContent" rows="3" style="width:100%;"></textarea><br>
                    
                        <!-- 상품 후기 리스트 -->
                        <ul class="timeline">
                            <!-- timeline time label -->
                          <li class="time-label" id="repliesDiv">
                              <span class="btn btn-default">
                                  상품후기 보기 <small id='replycntSmall'> [ ${totalReview} ] </small>
                              </span>
                              <button class="btn btn-primary" id="btn_write_review" type="button">상품후기쓰기</button>
                          </li>
                          <li class="noReview" style="display:none;">
                              <i class="fa fa-comments bg-blue"></i>
                              <div class="timeline-item" >
                                   <h3 class="timeline-header">
                                      상품후기가 존재하지 않습니다.<br>
                                      상품후기를 입력해주세요.</h3>
                              </div>
                          </li>
                           
                        </ul>
                        <!-- 상품 후기 리스트 페이지부분 -->  
                        <div class='text-center'>
                            <ul id="pagination" class="pagination pagination-sm no-margin "></ul>
                         </div>
                     </div>
                     
                     
                     <%-- Modal : 상품후기 수정/삭제 팝업 --%>
                    <div id="modifyModal" role="dialog" style="display:none;">
                      <div class="modal-dialog">
                        <!-- Modal content-->
                        <div class="modal-content">
                          <div class="modal-header" >
                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                            <div class="modal-title">
                                <p id="star_grade_modal">
                                    <a href="#">★</a>
                                    <a href="#">★</a>
                                    <a href="#">★</a>
                                    <a href="#">★</a>
                                    <a href="#">★</a>
                                </p>
                            </div>
                          </div>
                          <div class="modal-body" data-rev_num>
                            <p><input type="text" id="replytext" class="form-control"></p>
                          </div>
                          <div class="modal-footer">
                            <button type="button" class="btn btn-info" id="btn_modal_modify">수정</button>
                            <button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
                          </div>
                        </div>
                      </div>
                    </div>      
            </div>
            
            
            <!-- /.box-body -->

            <div class="box-footer">
                <div>
                    <hr>
                </div>

                <ul class="mailbox-attachments clearfix uploadedList">
                </ul>

                <button id="btn_list" type="button" class="btn btn-primary" >목록으로</button>
            </div>
        
        </div>

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