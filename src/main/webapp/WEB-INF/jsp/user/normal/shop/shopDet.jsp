<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
    JSP Name : shopDet.jsp
    Description : shop 상세
--%>
<link rel="stylesheet" href="/resources/user/css/product.css?v=${cacheParam}">
<div id="container">
	<div class="location_bar">
		<ul class="clearfix inner pcTab">
			<li><a href="/index.vc">Home</a></li>
<!-- 			<li><a href="/shop/virus-clean-lab/list.vc">Shop</a></li> -->
			<c:choose>
			<c:when test="${result.searchInfo.lgrpCd eq 'VCL'}">
				<li><a href="#">Z-mini</a></li>
			</c:when>
			<c:when test="${result.searchInfo.lgrpCd eq 'ACC'}">
				<li><a href="#">액세서리</a></li>
			</c:when>
			</c:choose>
		</ul>
		<div class="mo inner">
			<a href="" ></a>
		</div>			
	</div>
	<div class="detail">
		<!-- 상품정보 -->
		<div class="detail_top ">
			<div class="inner clearfix">
				<div class="product_img">
					<div class="swiper-container gallery-top">
						<div class="swiper-wrapper">
							<div class="swiper-slide">${function:printImageFile("THUMB1", result.file.list)}</div>
							<div class="swiper-slide">${function:printImageFile("THUMB2", result.file.list)}</div>
							<div class="swiper-slide">${function:printImageFile("THUMB3", result.file.list)}</div>
						</div>
					</div>
					<div class="swiper-container gallery-thumbs">
						<div class="swiper-wrapper">
							<div class="swiper-slide">${function:printImageFile("THUMB1", result.file.list)}</div>
							<div class="swiper-slide">${function:printImageFile("THUMB2", result.file.list)}</div>
							<div class="swiper-slide">${function:printImageFile("THUMB3", result.file.list)}</div>
						</div>
						<!-- Add Arrows -->
						<div class="swiper-button-next swiper-button-white"></div>
						<div class="swiper-button-prev swiper-button-white"></div>
					</div>
				</div>
				<form name="subForm" method="post" action="/shop/order/register.vc?prdSn=${result.info.prdSn}" onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
					<div class="detail_right">
						<a href="javascript:;" class="product_list_tit">Virus Clean Lab</a>
						<h3 class="pro_tit"><tag:html value="${result.info.prdTitl}" attr="NQ" /></h3>	
						<div class="pro_price">
							<p class="op_tit">판매가</p>
							<p>
								<span><fmt:formatNumber value="${result.info.salePrice}" type="number"/> 원</span>
								<em><fmt:formatNumber value="${result.info.supplyPrice}" type="number"/> 원</em>
							</p>
						</div>	
						<div class="quantity_op">
							<p class="op_tit"><tag:html value="${result.info.prdTitl}" attr="NQ" /></p>
							<div class="quantity_box clearfix">
								<button type="button" class="mn">-</button>
								<input type="text" name="orderQuantity" value="1" class="numOnly">
								<button type="button" class="pl">+</button>
							</div>
							<p class="q_price"><fmt:formatNumber value="${result.info.salePrice}" type="number"/> 원</p>
						</div>
						<div class="buy_op" style="position:relative;display:block;overflow:hidden;">
							<div style="margin-left: auto;text-align: right;display:inline-block;float:right;">
								<div class="all_price">
									<p class="op_tit">총금액</p>
									<p class="totalPrice"><fmt:formatNumber value="${result.info.salePrice}" type="number"/>원</p>
								</div>
								<p style="font-size:20px;margin-top: 15px;" class="free_delv"><strong>7만원 이상 </strong> 무료배송</p>
							</div>
							<div class="naver" id="naverPayWrap"></div>
						</div>
						<div class="btn_wrap">
<%-- 							<input type="hidden" name="prdSn" value="${result.info.prdSn}" /> --%>
							<input type="hidden" name="orderCost" value="${result.info.salePrice}" />
							<input type="hidden" name="orderAmount" value="${result.info.salePrice}" />
								
							<c:choose>
							<c:when test="${result.info.sellYn eq 'Y'}">
								<a href="/shop/cart.vc" class="btn btn_line btnAddCart" data-sn="${result.info.prdSn}" style="width:330px;"><span>장바구니</span></a>
								<a href="/shop/order/register.vc" class="btn btn_pp submit" style="width: 330px;"><span>바로구매</span></a>
							</c:when>
							<c:otherwise>
								<a href="#" class="btn btn_gray" style="width: 100%; margin-right: 0;"><span>품절</span></a>
							</c:otherwise>
							</c:choose>
						</div>
						<div class="review review_bott">
							<p class="op_tit">전체평점</p> <p class="sc_num"><fmt:formatNumber value="${result.info.gradeAvg}" type="number"/></p>
							<div class="star">
								<c:forEach begin="1" end="${result.info.gradeAvg}" varStatus="i">
									<img src="/resources/user/images/product/star.png" alt="">
								</c:forEach>
							</div>
							<a href="#" class="review_more"><span><fmt:formatNumber value="${resultReview.searchInfo.totalRow}" type="number"/></span>개의 리뷰</a>
							<div class="share_wrap">
								<a href="javascript:;" class="btn-page-share share_icon"><span><img src="/resources/user/images/product/share.png" alt=""></span></a>
								<a href="#" data-cd="PRODUCT" data-sn="${result.info.prdSn}" class="btn_like like ${result.info.myScrapCnt > 0 ? 'active' : ''}"></a>
							</div>
						</div>	
					</div>
				</form>
			</div>	
		</div>		
		
		<div class="detail_info">
			<div class="tab_ui col_3">
				<div class="tab">
					<ul class="inner clearfix"> 
						<li class="active"><a href="javascript:;">상품정보</a></li>
						<li class=""><a href="javascript:;">상품리뷰<span>(<fmt:formatNumber value="${resultReview.searchInfo.totalRow}" type="number"/>)</span></a></li>
						<li class=""><a href="javascript:;">Q&amp;A<span>(<fmt:formatNumber value="${resultQna.searchInfo.totalRow}" type="number"/>)</span></a></li>
					</ul>
				</div>
				<div class="tab_cont">
					<ul>
						<li class="active"><%@ include file="/WEB-INF/jsp/user/normal/shop/inc_productInfo.jsp" %></li>
						<li class=""><%@ include file="/WEB-INF/jsp/user/normal/shop/inc_productReview.jsp" %></li>						
						<li class="qna_page"><%@ include file="/WEB-INF/jsp/user/normal/shop/inc_productQna.jsp" %></li>
					</ul>
				</div>
			</div>
		</div>
	</div>		
</div>

<%@ include file="/WEB-INF/jsp/user/normal/common/include/inc_modal.jsp" %>

<script type="text/javascript" src="//pay.naver.com/customer/js/${deviceCode ne 'N' ? 'mobile/' : ''}naverPayButton.js" charset="UTF-8"></script>
<form name="npayform" method="get" ${deviceCode eq 'N' ? 'target="_blank"' : ''} >
    <input type="hidden" name="ORDER_ID">
    <input type="hidden" name="SHOP_ID">
    <input type="hidden" name="TOTAL_PRICE">
</form>

<c:choose>
<c:when test="${deviceCode eq 'N'}">
	<form name="npaywishform" method="get" action="https://pay.naver.com/customer/wishlistPopup.nhn" target="npayformpop" >
	    <input type="hidden" name="SHOP_ID">
	    <input type="hidden" name="ITEM_ID">
	</form>
</c:when>
<c:otherwise>
	<form name="npaywishform" method="get" action="https://m.pay.naver.com/mobile/customer/wishList.nhn" >
	    <input type="hidden" name="SHOP_ID">
	    <input type="hidden" name="ITEM_ID">
	</form>
</c:otherwise>
</c:choose>

<script src="/resources/user/js/product.js"></script>
<script type="text/javascript">
$(function(){
	comCodeUtil.getCodeNPrint('QNA', 'mgrpCd', 'select');
	
	$('.btn-page-share').attr('data-clipboard-text', document.location.href); 
    var clipboard = new Clipboard('.btn-page-share');
    clipboard.on('success', function(e) {
        alert('링크가 복사 되었습니다.');
    });	
});


var orderCost = parseInt($('input[name="orderCost"]').val());		//단가
$(document).ready(function(){
	submitQnaViewUrl = '/shop/qna/view.vc';
	
	$(".review_more").click(function(event){
		event.preventDefault();
		$('.tab > ul> li a').eq(1).trigger('click');
		fn_moveScrollTop($('#wrap-review').offset().top - 100);
	});
	
	$(".reviews_tit").click(function(event){
		event.preventDefault();
		var $target= $(this).parents('.revie_wrap');
		if($target.hasClass("active")){
			$target.removeClass("active");
		}else{
			$('.revie_wrap').removeClass("active");
			$target.addClass("active");
		}
	});
	
	/*
	 * 포토리뷰 보기
	 */
	$('.tabMobile a.more_btn').click(function(event){
		event.preventDefault();
		$('input#showPhotoReview').prop('checked', true).trigger('change');
		fn_moveScrollTop($('#wrap-review').offset().top - 100);
	});
	
	$('a.goto-photo-review').click(function(event){
		event.preventDefault();
		$('.li-review-' + $(this).data('sn')).addClass("active").siblings().removeClass('active');;
		fn_moveScrollTop($('.li-review-' + $(this).data('sn')).offset().top - 100);
	});
	
	$('input#showPhotoReview').on({
        'change' : function(event){
            if( $(this).prop('checked')){
            	$('ul.review_lists li').hide();
            	$('ul.review_lists li.photo-review').show();
            }else{
            	$('ul.review_lists li').show();
            }
        }
    });
	
	$('.quantity_box button').click(function(event){
		event.preventDefault();
		
		var orderQuantity = parseInt($('input[name=orderQuantity]').val());	//수량
		if($(this).hasClass('mn')){
			if(orderQuantity > 1){
				orderQuantity -= 1;
			}
		}else{
			orderQuantity += 1;
		}
		
		$('input[name=orderQuantity]').val(orderQuantity).trigger('change');
	});

	$('input[name=orderQuantity]').change(function(event){
		var orderAmount = parseInt($('input[name=orderAmount]').val());	//합계
		var orderQuantity = parseInt($('input[name=orderQuantity]').val());	//수량
		
		if(isNaN(orderQuantity) || orderQuantity < 1){
			orderQuantity = 1;
			$('input[name=orderQuantity]').val(orderQuantity);
		}
		
		orderAmount = orderCost * orderQuantity;
		
		$('p.totalPrice').html( numUtil.createComma(orderAmount) + '원' );
		$('input[name=orderAmount]').val(orderAmount);
		
		if(orderQuantity >= 50){
			alert('대량구매 시 상담 안내\n\n대량 구매를 원하시는 분은,\n1644-0955 또는 syj@vcinc.co.kr 로 연락 주시기 바랍니다');
		}
	});
	
	<c:if test="${not empty param.orderQuantity}">
		$('input[name=orderQuantity]').val('${param.orderQuantity}').trigger('change');
	</c:if>
	
});

var formSubmitObj = {
	submit : function(form){
		if(!submitUtil.isEmpty(form.orderQuantity)){
            return false;
        }
		
		if(!loginObj.isLoginCheck()){
    		if(confirm('로그인 하시겠습니까?\n취소를 누르면 비회원으로 진행됩니다.')){
    			loginObj.moveLogin(location.pathname + location.search + '&orderQuantity=' + $('input[name=orderQuantity]').val()); 
    			return false;
    		}
    	}

        return true;
	},
	//네이버 주문정보 조회
	npaySubmit : function()	{
		var msg = '1. 일반 택배 배송만 가능하며, 배송 관련 조회는 네이버에서만 가능합니다.\n';
		msg += '2. 제주, 도서지역의 경우 네이버 페이 결제 시 추가 운송료가 별도로 발생할 수 있습니다.\n';
		msg += '3. 비회원인 상태로 네이버 N페이 결제 시, 바이러스클린랩 홈페이지 행사에서 제외됩니다.\n\n';
		msg += '※ 주의 사항을 확인 했고, N페이 결제를 진행하시겠습니까?';
		
		if(confirm(msg)){
			
			submitUtil.enable();
			
			var data = {
				prdSn : '${result.info.prdSn}',
				orderQuantity :  parseInt($('input[name=orderQuantity]').val())
			};
		
			ajaxUtil.postDisableAsync('/shop/npay/order.vc', data, formSubmitObj.npayResult);
		}
	},
	npayResult : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        console.table(json);
        
        if(json.result.npay.members.status == 200){
        	document.npayform.action = json.result.orderUrl;
            document.npayform.ORDER_ID.value = json.result.npay.members.response;
            document.npayform.SHOP_ID.value = json.result.shopId;
            document.npayform.TOTAL_PRICE.value = json.result.totalPrice;
            document.npayform.submit();
        }else{
        	alert('네이버페이 연결에 오류가 있습니다. 잠시후에 다시 시도해 주세요.');
        }
	},
	wishSubmit : function(){
		submitUtil.enable();
		
		var data = {
			prdSn : '${result.info.prdSn}'
		};
	
		ajaxUtil.postDisableAsync('/shop/npay/wish.vc', data, formSubmitObj.wishResult);
		
	},
	wishResult : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        console.table(json);
        
        if(json.result.npay.members.status == 200){
        	<c:if test="${deviceCode eq 'N'}">
        		window.open("","npayformpop","width=500,height=500");
        	</c:if>

            document.npaywishform.SHOP_ID.value = json.result.shopId;
            document.npaywishform.ITEM_ID.value = json.result.npay.members.response;
            document.npaywishform.submit();
        }else{
        	alert('네이버페이 연결에 오류가 있습니다. 잠시후에 다시 시도해 주세요.');
        }
	},
};
</script>

<script type="text/javascript">
	naver.NaverPayButton.apply({
        EMBED_ID: "naverPayWrap",
        BUTTON_KEY: '05BC4411-4E80-46C9-8F8F-0E83AAF6125E', // 네이버페이에서 제공받은 버튼 인증 키 입력
        TYPE: "${deviceCode eq 'N' ? 'C' : 'MA'}", // 버튼 모음 종류 설정
        COLOR: 2, // 버튼 모음의 색 설정
        COUNT: 2, // 버튼 개수 설정. 구매하기 버튼만 있으면(장바구니 페이지) 1, 찜하기 버튼도 있으면(제품 상세 페이지) 2를 입력.
        
        <c:choose>
		<c:when test="${result.info.sellYn eq 'Y'}">
		 	ENABLE: "Y", // 품절 등의 이유로 버튼 모음을 비활성화할 때에는 "N" 입력
	        BUY_BUTTON_HANDLER: formSubmitObj.npaySubmit,// 구매하기 버튼 이벤트 Handler 함수 등록, 품절인 경우 not_buy_nc 함수 사용
	        WISHLIST_BUTTON_HANDLER: formSubmitObj.wishSubmit // 찜하기 버튼 이벤트 Handler 함수 등록
		</c:when>
		<c:otherwise>
		 	ENABLE: "N", // 품절 등의 이유로 버튼 모음을 비활성화할 때에는 "N" 입력
	        BUY_BUTTON_HANDLER: not_buy_nc,// 구매하기 버튼 이벤트 Handler 함수 등록, 품절인 경우 not_buy_nc 함수 사용
	        WISHLIST_BUTTON_HANDLER: not_buy_nc // 찜하기 버튼 이벤트 Handler 함수 등록
		</c:otherwise>
		</c:choose>
    });
	
	function not_buy_nc()
	{
	    alert("품절 상품입니다.");
	    return;
	}
</script>
