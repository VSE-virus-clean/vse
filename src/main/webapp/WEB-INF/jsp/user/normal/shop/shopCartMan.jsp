<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
    JSP Name : communityList.jsp
    Description : 커뮤니티 목록
--%>
<link rel="stylesheet" href="/resources/user/css/order.css?v=${cacheParam}">
<link rel="stylesheet" href="/resources/user/css/mypage.css?v=${cacheParam}">

<div id="container">
	<div class="location_bar">
		<ul class="clearfix inner pcTab">
			<li><a href="/index.vc">Home</a></li>
		</ul>
		<div class="mo inner">
			<a href=""></a>
		</div>	
	</div>
	<div class="order order_page">
		<div class="inner">
			<div class="inner_m">
				<h3 class="p_tit p_tit02">주문&middot;결제</h3>
	
				<div class="step">
					<ul>
						<li class="active"><p><span class="num"><em>01</em></span> <span>장바구니</span></p></li>
						<li><p><span class="num"><em>02</em></span> <span>주문서작성/결제</span></p></li>
						<li><p><span class="num"><em>03</em></span> <span>주문완료</span></p></li>
					</ul>
				</div>
			</div>

			<form name="subForm" method="post" action="/shop/cart/delete.vc" onsubmit="var rtn = formSubmitObj.delSubmit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
				<div class="order_cont">
					<div class="order_tbl_tit">
						<h4 class="cont_tit02 h401">주문상세내역</h4>
						<div class="btn_wrap">
							<a href="#" class="btn btn_pp submit" style="width: 170px;"><span>선택삭제</span></a>
							<a href="#" class="btn btn_line delSelectAll" style="width: 170px;"><span>전체삭제</span></a>
						</div>
					</div>
					<div class="order_cart">
						<div class="table-type01 order_det_tbl">
							<table>
								<colgroup>
									<col width="5%">
									<col width="31%">
									<col width="18%">
									<col width="18%">
									<col width="18%">
									<col width="10%">
								</colgroup>
								<thead>
									<tr>
										<th class="input_checkbox">
											<input type="checkbox" class="checkbox-selectAll" id="checkbox-selectAll">
										</th>
										<th>상품/옵션명</th>
										<th>수량</th>
										<th>상품금액</th>
										<th>배송비</th>
										<th>관리</th>
									</tr>
								</thead>
								<tbody>
									<c:choose>
									<c:when test="${empty result.list}">
										<tr><td colspan="6" class="no-data">장바구니에 상품이 없습니다.</td></tr>
									</c:when>
					                <c:otherwise>
					                	<c:forEach items="${result.list}" var="data" varStatus="i">
											<tr>
												<td class="input_checkbox">
													<input type="checkbox" id="sn_${data.prdSn}" name="delSeq" class="checkbox-select" value="${data.prdSn}" title="선택" />
													<a href="javascript:;" class="cart_x_btn mo btnRowDelete" data-sn="${data.prdSn}"><img src="/resources/user/images/order/cart_x_btn.png"></a>
												</td>
												<td colspan="2" class="name clearfix">
													${function:printImageFileByList(data.fileSn, "PRODUCT", data.filNm, data.filNm)}
													<p><tag:html value="${data.prdTitl}" attr="NQ" /></p>
												</td>	
												<td class="qaunt">
													<p class="mo">수량</p>
													<div class="quantity_box clearfix">
														<button type="button" class="mn">-</button>
														<input type="hidden" name="prdSn" value="${data.prdSn}" />
														<input type="hidden" name="prdPrice" value="${data.salePrice}" />
														<input type="text" name="prdCnt" value="${data.prdCnt}" class="numOnly" />
														<button type="button" class="pl">+</button>
													</div>
												</td>
												<td class="price">
													<p class="mo">상품금액</p>
													<input type="hidden" name="prdTotalPrice" value="${data.salePrice}" />
													<p class="pay prdTotalPrice"><fmt:formatNumber value="${data.salePrice}" type="number"/> 원</p>
												</td>
												<c:if test="${i.first}">
													<td rowspan="${fn:length(result.list)}" class="delivery clearfix">
														<p class="mo">배송비</p>
														<p class="deliveryPrice"></p>
<!-- 														<div class="d_icon">오늘출발</div> -->
<!-- 														<span>15:00 이전 결제 시</span> -->
<%-- 														<span style="color: #2d0a4f;">${function:isDeliveryDate(resultCode.info.deliveryDate)} 발송 예정</span> --%>
<!-- 															<span>예약판매로 4월말부터<br class="pcTab" />순차 배송됩니다.</span> -->
													</td>
												</c:if>
												<td class="pcTab manage">
													<a href="javascript:;" class="cart_x_btn btnRowDelete" data-sn="${data.prdSn}"><img src="/resources/user/images/order/cart_x_btn.png"></a>
												</td>
											</tr>
										</c:forEach>
					                </c:otherwise>
					                </c:choose>
								</tbody>
							</table>
						</div>
						
						<c:if test="${not empty result.list}">
							<div class="f_pay">
								<div class="f_pay_inner">
									<ul>
										<li class="price">
											<p class="info">총 상품금액</p>
											<p class="allPrice">0</p>
										</li>
										<li class="delivery">
											<p class="info">총 배송비</p>
											<p class="allDelivery">0</p>
										</li>
										<li class="total">
											<p class="info">합계</p>
											<p class="allTotal">0</p>
										</li>
									</ul>
								</div>
							</div>
						</c:if>
					</div>
					
					<c:if test="${not empty result.list}">
						<div class="btn_wrap" style="position:relative;display:block;">
							<a href="javascript:;" onclick="formSubmitObj.goOrder();" class="btn btn_pp" style="width: 330px;margin:0 auto;"><span>구매하기</span></a>
							<div class="naver" id="naverPayWrap"></div>
						</div>
					</c:if>
	
				</div>
			</form>
		</div>						
	</div>		
</div>

<form name="npayform" method="get" ${deviceCode eq 'N' ? 'target="_blank"' : ''} >
    <input type="hidden" name="ORDER_ID">
    <input type="hidden" name="SHOP_ID">
    <input type="hidden" name="TOTAL_PRICE">
</form>

<script type="text/javascript" src="//pay.naver.com/customer/js/${deviceCode ne 'N' ? 'mobile/' : ''}naverPayButton.js" charset="UTF-8"></script>
<script src="/resources/user/js/order.js"></script>
<script>

var clickOrder = 'N';
var clickAllDel = 'N';

/*
 * 총합계 계산
 */
function cartCalculator(){
	var deliveryFee = parseInt('${empty resultCode.info.deliveryMin ? 0 : resultCode.info.deliveryMin}');
	var deliveryFree = parseInt('${empty resultCode.info.deliveryFree ? 0 : resultCode.info.deliveryFree}');
	var allPrice = 0;
	var allDelivery = 0;
	var allTotal = 0;
	
	$('.order_cart table tbody tr').each(function(){
		allPrice += parseInt($(this).find('input[name=prdTotalPrice]').val());
	});
	
	allDelivery = allPrice >= deliveryFree ? 0 : deliveryFee;
	
	$('p.allPrice').html(numUtil.createComma(allPrice) + ' 원');
	$('p.allDelivery').html(numUtil.createComma(allDelivery) + ' 원');
	$('p.allTotal').html(numUtil.createComma(allPrice + allDelivery) + ' 원');
	$('p.deliveryPrice').html(allDelivery > 0 ? numUtil.createComma(allDelivery) : '배송무료');
}


$(function () {
	$('a.delSelectAll').on({
        click : function(event){
        	event.preventDefault();
        	clickAllDel = 'Y';
        	$(this).parents('form').find('input.checkbox-select').prop('checked', true);
            $(this).parents('form').eq(0).submit();
        }
    });
	
	//prdPrice
	//prdCnt
	$('.quantity_box button').click(function(event){
		event.preventDefault();
		
		var cnt = parseInt($(this).siblings('input[name=prdCnt]').val());
		
		if($(this).hasClass('mn')){
			if(cnt > 1){
				cnt -= 1;
			}
		}else{
			cnt += 1;
		}
		
		$(this).siblings('input[name=prdCnt]').val(cnt).trigger('change');
	});
	
	$('input[name=prdCnt]').change(function(event){
		event.preventDefault();
		
		var tr = $(this).closest('tr');

		var price = parseInt($(tr).find('input[name=prdPrice]').val());
		var totalPrice = parseInt($(tr).find('input[name=prdTotalPrice]').val());
		var cnt = parseInt($(tr).find('input[name=prdCnt]').val());	//수량
		
		if(isNaN(cnt) || cnt < 1){
			cnt = 1;
			$(tr).find('input[name=prdCnt]').val(cnt);
		}
		
		totalPrice = price * cnt;
		
		$(tr).find('p.prdTotalPrice').html( numUtil.createComma(totalPrice) + ' 원' );
		$(tr).find('input[name=prdTotalPrice]').val(totalPrice);
		
		cartCalculator();
		
		if(cnt >= 50){
			alert('대량구매 시 상담 안내\n\n대량 구매를 원하시는 분은,\n1644-0955 또는 syj@vcinc.co.kr 로 연락 주시기 바랍니다');
		}
	});
	
	
	 
    $('a.btnRowDelete').on({
        'click' : function(event){
            event.preventDefault();  
            formSubmitObj.submitDel($(this));
        }
    });
});

$(document).ready(function() {
	cartCalculator();
});

var formSubmitObj = {
	goOrder : function(event){
		if(!loginObj.isLoginCheck()){
    		if(confirm('로그인 후 구매 하시겠습니까?')){
    			loginObj.moveLogin(location.pathname + location.search); 
    			return false;
    		}
    	}
		
		clickOrder = 'Y';
		$('form[name=subForm]').find('input.checkbox-select').prop('checked', true);
		$('form[name=subForm]').attr('action', '/shop/order/register.vc');
		$('form[name=subForm]').submit();   
	},
	submitDel : function(object){
		var msg = '상품을 삭제 하시겠습니까?'
	    if(confirm(msg)){
	        var data = { prdSn : $(object).data('sn') };
	        ajaxUtil.postDisableAsync('/shop/cart/delete.vc', data, formSubmitObj.delResult);    
	    }
	},
	delSubmit : function(form){
		if(clickOrder == 'N'){
	        if($('input.checkbox-select:checked').length > 0){
	        	var msg = '선택한 상품을 삭제 하시겠습니까?';
	        	
	        	if(clickAllDel == 'Y'){
	        		msg = '장바구니를 모두 비우시겠습니까?';
	        	}
	        	if (confirm(msg)) {
	            	ajaxUtil.postDisableAsync('/shop/cart/delete.vc', $(form).serialize(), formSubmitObj.delResult);
	        	}else{
	        		$('form[name=subForm]').find('input.checkbox-select').prop('checked', false);
	        	}
	        }else{
	            alert('삭제할 상품을 선택해 주세요.');
	        }

	        return false;
		}
    },
    delResult : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        if(json.result != undefined && json.result.status){
            location.reload(true);
        }else{
            ajaxUtil.error(json);
        }
    },
  	//네이버 주문정보 조회
	npaySubmit : function()	{
		var msg = '1. 일반 택배 배송만 가능하며, 배송 관련 조회는 네이버에서만 가능합니다.\n';
		msg += '2. 제주, 도서지역의 경우 네이버 페이 결제 시 추가 운송료가 별도로 발생할 수 있습니다.\n';
		msg += '3. 비회원인 상태로 네이버 N페이 결제 시, 바이러스클린랩 홈페이지 행사에서 제외됩니다.\n\n';
		msg += '※ 주의 사항을 확인 했고, N페이 결제를 진행하시겠습니까?';
		
		if(confirm(msg)){
			
			submitUtil.enable();
			
			$('form[name=subForm]').find('input.checkbox-select').prop('checked', true);
			
			ajaxUtil.postDisableAsync('/shop/npay/order.vc', $('form[name=subForm]').serialize(), formSubmitObj.npayResult);
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
	}
};
</script>
<script type="text/javascript">
<c:if test="${not empty result.list}">
	naver.NaverPayButton.apply({
        EMBED_ID: "naverPayWrap",
        BUTTON_KEY: '05BC4411-4E80-46C9-8F8F-0E83AAF6125E', // 네이버페이에서 제공받은 버튼 인증 키 입력
        TYPE: "${deviceCode eq 'N' ? 'C' : 'MA'}", // 버튼 모음 종류 설정
        COLOR: 2, // 버튼 모음의 색 설정
        COUNT: 1, // 버튼 개수 설정. 구매하기 버튼만 있으면(장바구니 페이지) 1, 찜하기 버튼도 있으면(제품 상세 페이지) 2를 입력.
	 	ENABLE: "Y", // 품절 등의 이유로 버튼 모음을 비활성화할 때에는 "N" 입력
        BUY_BUTTON_HANDLER: formSubmitObj.npaySubmit,// 구매하기 버튼 이벤트 Handler 함수 등록, 품절인 경우 not_buy_nc 함수 사용
    });
</c:if>
</script>
<script type="text/javascript"> 
	wcs_do();
</script>
