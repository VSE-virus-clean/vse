<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%-- 
    JSP Name : orderRegisterReg.jsp
    Description : 주문결제 > Step2. 주문서작성
--%>
<link rel="stylesheet" href="/resources/user/css/order.css?v=${cacheParam}">
<div id="container">
	<div class="location_bar">
		<ul class="clearfix inner pcTab">
			<li><a href="/index.vc">Home</a></li>
		</ul>
		<div class="mo inner">
			<a href=""></a>
		</div>	
	</div>
	<div class="order">
		<div class="inner">
			<div class="inner_m">
				<h3 class="p_tit p_tit02">주문·결제</h3>
				<div class="step">
					<ul>
						<li><p><span class="num"><em>01</em></span> <span>장바구니</span></p></li>
						<li class="active"><p><span class="num"><em>02</em></span> <span>주문서작성/결제</span></p></li>
						<li><p><span class="num"><em>03</em></span> <span>주문완료</span></p></li>
					</ul>
				</div>
			</div>

			<div class="order_cont">
				<form name="mainForm" method="post" action="/shop/order/registerReg.vc" onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
					<h4 class="cont_tit02 h401">주문상세내역</h4>
					<div class="order_cart">
						<div class="table-type01 order_det_tbl" style="display:block;">
							<table>
								<colgroup>
									<col width="25%">
									<col width="10%">
									<col width="15%">
									<col width="20%">
									<col width="15%">
								</colgroup>
								<thead>
									<tr>
										<th>상품/옵션명</th>
										<th>수량</th>
										<th>상품금액</th>
										<th>쿠폰 적용 후 금액</th>
										<th>배송비</th>
									</tr>
								</thead>
								<tbody>
									<c:set var="totalQuantity" value="0" scope="page" />
									<c:set var="totalPrice" value="0" scope="page" />
									
									<c:choose>
									<c:when test="${empty result.list}">
					                    <li class="no-data">구매할 상품이 없습니다.</li>
					                </c:when>
					                <c:otherwise>
					                	<c:forEach items="${result.list}" var="data" varStatus="i">
					                		<c:set var="prdPrice" value="${data.salePrice * data.quantity}" scope="page" />
					                		<c:set var="prdDelivery" value="${(data.salePrice * data.quantity) < 70000  ? 3000 : 0}" scope="page" />
					                		<c:set var="totalQuantity" value="${totalQuantity + data.quantity}" scope="page" />
					                		<c:set var="totalPrice" value="${totalPrice + prdPrice}" scope="page" />
					                		
											<tr class="prdSn_${data.prdSn}">
												<td class="pro_is_wrap">
													<div class="pro_img">
														${function:printImageFileByList(data.fileSn, "PRODUCT", data.filNm, data.filNm)}
													</div>	
													<div class="name">
														<p><tag:html value="${data.prdTitl}" attr="NQ" /></p>
													</div>
												</td>
												<td class="qaunt_wrap">
													<div class="qaunt">${data.quantity}개</div>
													<div class="price mo">
														<div class="pay"><fmt:formatNumber value="${prdPrice}" type="number"/> 원</div>
													</div>
												</td>
												<td class="price_wrap">
													<div class="price">
														<input type="hidden" name="prdSn" value="${data.prdSn}" />
														<input type="hidden" name="prdCnt" value="${data.quantity}" />
														<input type="hidden" name="prdTitl" value="${data.prdTitl}" />
														<input type="hidden" name="prdPrice" value="${data.salePrice}" />
														<input type="hidden" name="prdDiscountPrice" value="0" />
														<input type="hidden" name="prdTotalPrice" value="${prdPrice}" />
														<p class="pay"><fmt:formatNumber value="${prdPrice}" type="number"/> 원</p>
														
														<c:if test="${not empty sessionScope.sessionVO}">
															<a href="javascript:;" class="coupon" data-sn="${data.prdSn}" onclick="modalUtil.open('coupon-order', this);">쿠폰적용</a>
														</c:if>
													</div>
												</td>
												<td class="coupon_p_wrap ">
													<c:if test="${not empty sessionScope.sessionVO}">
														<a href="javascript:;" class="coupon coupon_p mo" style="color:#f99858;"data-sn="${data.prdSn}" onclick="modalUtil.open('coupon-order', this);">쿠폰적용</a>
														<div class="coupon_p">0 원</div>
													</c:if>
													<c:if test="${empty sessionScope.sessionVO}">
														<div class="coupon_p">-</div>
													</c:if>
												</td>
												<c:if test="${i.first}">
													<td rowspan="${fn:length(result.list)}" class="delivery_wrap delivery_wrap01 delivery">
														<p class="mo">배송비</p>
														<p class="deliveryPrice"></p>
														<div class="deliv_cont pcTab">
<!-- 															<div class="tempry">예약구매 기간입니다. 주문확인 후 순차적으로 발송예정입니다.</div> -->
															<!-- <div class="d_icon">오늘출발</div>
															<span>15:00 이전 결제 시</span>
															<span style="color: #2d0a4f;">${function:isDeliveryDate(resultCode.info.deliveryDate)} 발송 예정</span> -->
														</div>
													</td>
													<td rowspan="${fn:length(result.list)}" class="delivery_wrap delivery_wrap02 delivery">
														<div class="deliv_cont">
<!-- 															<div class="tempry">예약구매 기간입니다. 주문확인 후 순차적으로 발송예정입니다.</div> -->
															<!-- <p class="deliveryPrice pcTab"></p>
															<div class="d_icon">오늘출발</div>
															<span>15:00 이전 결제 시</span>
															<span style="color: #2d0a4f;">${function:isDeliveryDate(resultCode.info.deliveryDate)} 발송 예정</span>-->
														</div>
													</td>
												</c:if>
											</tr>
										</c:forEach>
					                </c:otherwise>
					                </c:choose>
								</tbody>
							</table>
						</div>
						<div class="f_pay">
							<div class="f_pay_inner">
								<ul>
									<li class="price">
										<p class="info">총 상품금액</p>
										<p class="txtTotalPrice"></p>
									</li>
									<li class="delivery">
										<p class="info">총 배송비</p>
										<p class="txtTotalDeliver"></p>
									</li>
									<li class="discount">
										<p class="info">총 할인금액</p>
										<p class="txtTotalDiscount"></p>
									</li>
									<li class="total">
										<p class="info">합계</p>
										<p class="txtTotalOrderPrice"></p>
									</li>
								</ul>
							</div>
						</div>
					</div>
					
					<div class="inner_m">
						<h4 class="cont_tit02 h402 pc">주문하시는 분</h4>
						<h4 class="cont_tit02 h402 mo">주문자</h4>
						<div class="order_customer">
							<div class="table-type01 table_customer01">
								<table>
									<colgroup>
										<col style="width: 200px;">
										<col>
									</colgroup>
									<tbody>
										<tr class="name">
											<th>이름</th>
											<td>
												<input type="text" id="buyerNm" name="buyerNm" maxlength="50" title="이름" placeholder="성명을 입력해주세요" value="${sessionScope.sessionVO.name}" />
											</td>
										</tr>
										<tr class="name">
											<th>핸드폰</th>
											<td>
												<input type="text" pattern="\d*" name="buyerHp" title="핸드폰" class="numOnly" maxlength="11" value="${fn:replace(sessionScope.sessionVO.hp, '-', '')}"  title="주문자의 핸드폰" placeholder="연락처를 입력해주세요" oninput="maxLengthCheck(this)"/>
											</td>
										</tr>
										<tr>
											<th>주소</th>
											<td>
												<div id="zipcodeWrap" style="display:none;position:fixed;overflow:hidden;z-index:1;-webkit-overflow-scrolling:touch;">
													<!-- iOS에서는 position:fixed 버그가 있음, 적용하는 사이트에 맞게 position:absolute 등을 이용하여 top,left값 조정 필요 -->
													<img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnBuyerCloseLayer" style="cursor:pointer;position:absolute;right:-3px;top:-3px;z-index:1" onclick="closeDaumPostcode()" alt="닫기 버튼">
												</div>
												<div class="add">
													<div>
														<input type="text" id="buyerZipCd" name="buyerZipCd" maxlength="8" style="width:170px;" title="주문자의 우편번호" placeholder="우편번호" value="${sessionScope.sessionVO.zipCd}" readonly="readonly"/>
														<a href="javascript:;" id="btnBuyerZipCode" class="btn02 btn_pp add_search"><span>우편번호검색</span></a>
													</div>
													<div>
														<input type="text" id="buyerAdrSbc1" name="buyerAdrSbc1" style="width:80%;" title="주문자의 주소" readonly="readonly" value="${sessionScope.sessionVO.addr1}" />
													</div>
													<div>
														<input type="text" id="buyerAdrSbc2" name="buyerAdrSbc2" class="gray" maxlength="100" style="width:80%;" title="주문자의 상세주소" value="${sessionScope.sessionVO.addr2}" placeholder="상세주소를 입력해주세요"/>
													</div>
												</div>
											</td>
										</tr>
										<tr>
											<th>E-MAIL</th>
											<td>
												<div class="mail">
													<input type="hidden" name="buyerEml" title="이메일" value="${sessionScope.sessionVO.email}">
													<input type="text" id="buyerEml1" name="buyerEml1" maxlength="30" title="주문자의 이메일" class="gray" value="${fn:substringBefore(sessionScope.sessionVO.email, '@')}" oninput="maxLengthCheck(this)"/>
													<em class="">@</em>
													<input type="text" id="buyerEml2" name="buyerEml2" maxlength="30" title="주문자의 이메일" class="gray" value="${fn:substringAfter(sessionScope.sessionVO.email, '@')}" oninput="maxLengthCheck(this)"/>
													<select name="buyerDomain"  class="gray">
							                           	<option value="" >직접입력</option>
								                       	<option value="naver.com" >naver.com</option>
								                       	<option value="daum.net" >daum.net</option>
								                       	<option value="hanmail.net" >hanmail.net</option>
								                       	<option value="gmail.com" >gmail.com</option>
							                       </select>
												</div>
											</td>
										</tr>
									</tbody>
								</table>
							</div>
						</div>
						<h4 class="cont_tit02 h403 pc">받으시는 분</h4>
						<h4 class="cont_tit02 h403 mo">배송지</h4>
						<div class="order_customer">
							<div class="table-type01 table_customer02">
								<table>
									<colgroup>
										<col style="width: 200px;">
										<col>
									</colgroup>
									<tbody>
										<tr>
											<th>배송지 확인</th>
											<td>
												<label class="input_radio" for="d_write">
													<input id="d_write" name="samInfo" type="radio" checked="checked" value="N">
													<span>직접입력</span>
												</label>
												<label class="input_radio"  for="d_same">
													<input id="d_same" name="samInfo" type="radio"  value="Y">
													<span>주문자정보와 동일</span>
												</label>
											</td>
										</tr>
										<tr class="name">
											<th>이름</th>
											<td>
												<input type="text" id="receiverNm" name="receiverNm" maxlength="50" title="이름" placeholder="성명을 입력해주세요" value="" />
											</td>
										</tr> 
										<tr class="name">
											<th>핸드폰</th>
											<td>
												<input type="text" pattern="\d*" name="receiverHp" class="numOnly" maxlength="11" title="핸드폰번호" placeholder="연락처를 입력해주세요" value="" oninput="maxLengthCheck(this)"/>
											</td>
										</tr>
										<tr>
											<th>주소</th>
											<td>
												<div class="add">
													<div>
														<input type="text" id="receiverZipCd" name="receiverZipCd" maxlength="8" style="width:170px;" title="우편번호" placeholder="우편번호" value="" readonly="readonly"/>
														<a href="javascript:;" id="btnReceiverZipCode" class="btn02 btn_pp add_search"><span>우편번호검색</span></a>
													</div>
													<div>
														<input type="text" id="receiverAdrSbc1" name="receiverAdrSbc1" style="width:80%;" title="주소1" readonly="readonly" value="" />
													</div>
													<div>
														<input type="text" id="receiverAdrSbc2" name="receiverAdrSbc2" class="gray" maxlength="100" style="width:80%;" title="상세주소" value="" placeholder="상세주소를 입력해주세요"/>
													</div>
												</div>
											</td>
										</tr>
										<tr>
											<th>E-MAIL</th>
											<td>
												<div class="mail">
													<input type="hidden" name="receiverEml" title="이메일" value="">
													<input type="text" id="receiverEml1" name="receiverEml1" maxlength="30" title="이메일" class="gray" value="" oninput="maxLengthCheck(this)"/>
													<em class="">@</em>
													<input type="text" id="receiverEml2" name="receiverEml2" maxlength="30" title="이메일" class="gray" value="" oninput="maxLengthCheck(this)"/>
													<select name="receiverDomain"  class="gray">
							                           	<option value="" >직접입력</option>
								                       	<option value="naver.com" >naver.com</option>
								                       	<option value="daum.net" >daum.net</option>
								                       	<option value="hanmail.net" >hanmail.net</option>
								                       	<option value="gmail.com" >gmail.com</option>
							                       </select>
												</div>
											</td>
										</tr>
										<tr class="delivery_meme02">
											<th>배송<br class="pc">메모</th>
											<td>
												<select name="selectOrderMemo" style="display:none;">
						                           	<option value="">직접입력</option>
							                       	<option value="배송 전에 미리 연락 바랍니다." >배송 전에 미리 연락 바랍니다.</option>
							                       	<option value="부재시 경비실에 맡겨 주세요." >부재시 경비실에 맡겨 주세요.</option>
							                       	<option value="부재시 전화 주시거나 문자 남겨주세요" >부재시 전화 주시거나 문자 남겨주세요.</option>
						                       	</select>
						                       	<textarea id="orderMemo" name="orderMemo" style="width:100%;height:100px;background:#ddd;margin-top:20px;padding:10px;" placeholder="배송 시 요청사항을 남겨주세요"  title="배송메모"></textarea>
											</td>
										</tr>
										
									</tbody>
								</table>
							</div>
						</div>
						
						
						<h4 class="cont_tit02 h405">이용약관 동의 및 개인정보이용동의</h4>
						<div class="tb_box">
							<label class="input_checkbox" for="allAgree">
								<input id="allAgree" name="allAgree" type="checkbox"  class="checkAgreeAll">
								<span><em class="point_o">[전체동의]</em><em>약관에 모두 동의합니다.</em></span>
							</label>
							<div class="gray_box terms_box">
								<ul class="box_inner">
									<li>
										<div class="agree_tit">
											<label class="input_checkbox" for="agree1">
												<input id="agree1" name="agree1" type="checkbox" class="checkbox-agree">
												<span><em class="point_o">[필수]</em><em>개인정보 수집 및 이용동의</em></span>
											</label>
											<a href="javascript:;" class="btn btn_line all_btn01"><span>전체보기</span></a>
										</div>
										<div class=" gray_box_inner box_01">
											<%@ include file="/WEB-INF/jsp/user/normal/shop/inc_service2.jsp" %>
										</div>
									</li>
									<li>
										<div class="agree_tit">
											<label class="input_checkbox" for="agree2">
												<input id="agree2" name="agree2" type="checkbox" class="checkbox-agree">
												<span><em class="point_o">[필수]</em><em>개인정보 위탁동의</em></span>
											</label>
											<a href="javascript:;" class="btn btn_line all_btn02"><span>전체보기</span></a>
										</div>
										<div class="gray_box_inner box_02">
											<%@ include file="/WEB-INF/jsp/user/normal/shop/inc_service1.jsp" %>
										</div>
									</li>
								</ul>
							</div>
						</div>
						<h4 class="cont_tit02 h406">최종결제금액</h4>
						<div class="m_order_cart fin">
							<div class="f_pay white">
								<div class="f_pay_inner">
									<input type="hidden" name="orderQuantity" value="${totalQuantity}"/>
									<input type="hidden" name="productPrice" value="${totalPrice}"/>
									<input type="hidden" name="discountPrice" value="0"/>
									<input type="hidden" name="deliveryFee" value="0"/>
									<input type="hidden" name="orderPrice" value="0"/>
									<ul>
										<li class="price">
											<p class="info">총 상품금액</p>
											<p class="txtTotalPrice"></p>
										</li>
										<li class="delivery">
											<p class="info">총 배송비</p>
											<p class="txtTotalDeliver"></p>
										</li>
										<li class="discount">
											<p class="info">총 할인금액</p>
											<p class="txtTotalDiscount"></p>
										</li>
										<li class="total">
											<p class="info">합계</p>
											<p class="txtTotalOrderPrice"></p>
										</li>
									</ul>
								</div>
							</div>
						</div>
						<div class="agree_wrap">
							<label class="input_checkbox" for="agree3">
									<input type="checkbox" id="agree3" name="agree3" class="checkbox-agree">
								<span><em class="point_o">[필수]</em><em>구매하실 상품의 결제정보를 확인하였으며, </em><br class="mo"><em class="agree_check_txt">구매진행에 동의합니다.</em></span>
							</label>
						</div>
					
						<div class="btn_wrap mt0">
							<!-- <a href="#" class="btn btn_line submit" style="width: 330px;"><span>쇼핑계속하기</span></a> -->
							<a href="#" class="btn btn_pp submit" style="width: 330px;"><span>결제</span></a>
						</div>
					</div>
				</form>
			
				<c:choose>
				<c:when test="${deviceCode eq 'N'}">
					<form id="paymentForm" name="paymentForm" method="post" onsubmit="var rtn = formSubmitObj.submitPayment(this); if(!rtn){ submitUtil.enable(); } return rtn;">
						<input type="hidden" name="version" value="1.0" />
						<input type="hidden" name="gopaymethod" value="" />
						<input type="hidden" name="acceptmethod" value="CARDPOINT:HPP(1):no_receipt:va_receipt:below1000" />
						<input type="hidden" name="payViewType" value="overlay" />
						<input type="hidden" name="quotabase" value="" >
						<input type="hidden" name="ini_onlycardcode" value="" >
						<input type="hidden" name="ini_cardcode" value="" >
						<input type="hidden" name="ansim_quota" value="" >	
						<input type="hidden" name="INIregno" value="" >
						<input type="hidden" name="currency" value="WON" />
						
						<input type="hidden" name="mKey" value="" />
						<input type="hidden" name="mid" value="" />
						<input type="hidden" name="oid" value="" />
						<input type="hidden" name="goodname" value="" />
						<input type="hidden" name="price" value="" />
						<input type="hidden" name="buyername" value="" />
						<input type="hidden" name="buyertel" value="" />
						<input type="hidden" name="buyeremail" value="" />
						<input type="hidden" name="timestamp" value="" />
						<input type="hidden" name="signature" value="" />
						<input type="hidden" name="returnUrl" value="<spring:eval expression="@global['site.user.domain']" />/api/notify/payment/pc.vc" >
						<input type="hidden" name="closeUrl" value="<spring:eval expression="@global['site.user.domain']" />/close.jsp" >
					</form>
				</c:when>
				<c:when test="${deviceCode ne 'N'}">
					<form id="paymentForm" name="paymentForm" method="post" action="https://mobile.inicis.com/smart/payment/" target="_self" accept-charset="euc-kr" onsubmit="var rtn = formSubmitObj.submitPayment(this); if(!rtn){ submitUtil.enable(); } return rtn;">
						<!-- 지불수단 선택 (신용카드:CARD / 실시간계좌이체:BANK / 가상계좌(무통장입금):VBANK / 휴대폰:MOBILE) -->
						<input type="hidden" name="P_INI_PAYMENT" value="CARD"> 
						<input type="hidden" name="P_RESERVED" value="vbank_receipt=Y&below1000=Y"> 
						<input type="hidden" name="P_MID" value=""> 
						<input type="hidden" name="P_OID" value="">  
						<input type="hidden" name="P_GOODS" value=""> 
						<input type="hidden" name="P_AMT" value=""> 
						<input type="hidden" name="P_UNAME" value=""> 
						<input type="hidden" name="P_MOBILE" value="">
						<input type="hidden" name="P_EMAIL" value="">
						<input type="hidden" name="P_CHARSET" value="utf8">

						<!-- 가상계좌 입금 노티 사용시 필수 -->
						<input type="hidden" name="P_NOTI_URL" value="<spring:eval expression="@global['site.user.domain']" />/api/notify/payment/vbank/mobile.vc">  
						<!-- 리턴받는 가맹점 URL 세팅 -->
						<input type="hidden" name="P_NEXT_URL" value="<spring:eval expression="@global['site.user.domain']" />/api/notify/payment/mobile.vc"> 
					</form>
				</c:when>
				</c:choose>
			</div>
		</div>						
	</div>		
</div>

<%@ include file="/WEB-INF/jsp/user/normal/common/include/inc_modal.jsp" %>

<script type="text/javascript" src="//stdpay.inicis.com/stdjs/INIStdPay.js" charset="UTF-8"></script>
<script type="text/javascript" src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript" src="/resources/user/js/order.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	
	try{
		orderPriceCalculator.initPrice(parseInt('${totalPrice}'));
	}catch(e){
		console.error('[ERROR] orderPriceCalculator.initPrice 호출 에러입니다.');
	}
	
	$('select[name=selectOrderMemo]').on('change', function(event){
    	$('textarea[name=orderMemo]').val($(this).val());
	});
	
    $('select[name=buyerDomain]').on('change', function(e){
	    if($(this).val() != ''){
	        $('input[name=buyerEml2]').val($(this).val()).prop('readonly', true);
	    }else{
	        $('input[name=buyerEml2]').val('').prop('readonly', false);
	    }
	});
    
    $('select[name=receiverDomain]').on('change', function(e){
	    if($(this).val() != ''){
	        $('input[name=receiverEml2]').val($(this).val()).prop('readonly', true);
	    }else{
	        $('input[name=receiverEml2]').val('').prop('readonly', false);
	    }
	});
    
    
    $('a#btnBuyerZipCode, input[name=buyerZipCd]').on({
        'click' : function(event){
            event.preventDefault();  
            execDaumPostcode();
        },
        'focus' : function(event){
            event.preventDefault();  
            execDaumPostcode();
        }
    });
    
    $('a#btnReceiverZipCode, input[name=receiverZipCd]').on({
        'click' : function(event){
            event.preventDefault();  
            execDaumPostcode2();
        },
        'focus' : function(event){
            event.preventDefault();  
            execDaumPostcode2();
        }
    });
    
    $('input.checkAgreeAll').on({
	    change : function(event){
	        $('input.checkbox-agree').prop('checked', $(this).is(':checked'));
	    }
	});
	
	$('input.checkbox-agree').on({
	    change : function(event){
	    	if(!$(this).is(':checked')){
	    		$('input.checkAgreeAll').prop('checked', false);
	    	}else{
	    		if($('input.checkbox-agree:checked').length == 3){
	    			$('input.checkAgreeAll').prop('checked', true);
	    		}
	    	}
	    }
	});
});


//우편번호 찾기 찾기 화면을 넣을 element
var zipcodeWrap = document.getElementById('zipcodeWrap');
function closeDaumPostcode() {
    zipcodeWrap.style.display = 'none';
}

function initLayerPosition(){
    var width = 350; //우편번호서비스가 들어갈 element의 width
    var height = 400; //우편번호서비스가 들어갈 element의 height
    var borderWidth = 3; //샘플에서 사용하는 border의 두께

    // 위에서 선언한 값들을 실제 element에 넣는다.
    zipcodeWrap.style.width = width + 'px';
    zipcodeWrap.style.height = height + 'px';
    zipcodeWrap.style.border = borderWidth + 'px solid';
    // 실행되는 순간의 화면 너비와 높이 값을 가져와서 중앙에 뜰 수 있도록 위치를 계산한다.
    zipcodeWrap.style.left = (((window.innerWidth || document.documentElement.clientWidth) - width)/2 - borderWidth) + 'px';
    zipcodeWrap.style.top = (((window.innerHeight || document.documentElement.clientHeight) - height)/2 - borderWidth) + 'px';
}

function execDaumPostcode() {
    var currentScroll = Math.max(document.body.scrollTop, document.documentElement.scrollTop);
    new daum.Postcode({
        oncomplete: function(data) {
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            if(data.userSelectedType === 'R'){
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                addr += extraAddr;
            }

            document.forms.mainForm.buyerZipCd.value = data.zonecode;
            document.forms.mainForm.buyerAdrSbc1.value = addr;
            document.forms.mainForm.buyerAdrSbc2.focus();
            
            zipcodeWrap.style.display = 'none';

            document.body.scrollTop = currentScroll;
        },
        onresize : function(size) {
	        // 우편번호 찾기 화면 크기가 조정되었을때 실행할 코드를 작성하는 부분. iframe을 넣은 element의 높이값을 조정한다.
	        zipcodeWrap.style.height = '400px';
	    },
	    width : '100%',
	    height : '400px',
        maxSuggestItems : 5
    }).embed(zipcodeWrap);

    // iframe을 넣은 element를 보이게 한다.
    zipcodeWrap.style.display = 'block';
    
 	// iframe을 넣은 element의 위치를 화면의 가운데로 이동시킨다.
    initLayerPosition();
}

function execDaumPostcode2() {
    var currentScroll = Math.max(document.body.scrollTop, document.documentElement.scrollTop);
    new daum.Postcode({
        oncomplete: function(data) {
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }

            if(data.userSelectedType === 'R'){
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                addr += extraAddr;
            }

            document.forms.mainForm.receiverZipCd.value = data.zonecode;
            document.forms.mainForm.receiverAdrSbc1.value = addr;
            document.forms.mainForm.receiverAdrSbc2.focus();
            
            zipcodeWrap.style.display = 'none';

            document.body.scrollTop = currentScroll;
        },
        onresize : function(size) {
	        // 우편번호 찾기 화면 크기가 조정되었을때 실행할 코드를 작성하는 부분. iframe을 넣은 element의 높이값을 조정한다.
	        zipcodeWrap.style.height = '400px';
	    },
	    width : '100%',
	    height : '400px',
        maxSuggestItems : 5
    }).embed(zipcodeWrap);

    // iframe을 넣은 element를 보이게 한다.
    zipcodeWrap.style.display = 'block';
    
 	// iframe을 넣은 element의 위치를 화면의 가운데로 이동시킨다.
    initLayerPosition();
}



$(function () {
	$('input[name=samInfo]').on({
        'change' : function(event){
            event.preventDefault();  
            if($(this).val() == 'Y'){
            	$('input[name=receiverNm]').val($('input[name=buyerNm]').val());
            	$('input[name=receiverHp]').val($('input[name=buyerHp]').val());
            	$('input[name=receiverZipCd]').val($('input[name=buyerZipCd]').val());
            	$('input[name=receiverAdrSbc1]').val($('input[name=buyerAdrSbc1]').val());
            	$('input[name=receiverAdrSbc2]').val($('input[name=buyerAdrSbc2]').val());
            	$('input[name=receiverEml1]').val($('input[name=buyerEml1]').val());
            	$('input[name=receiverEml2]').val($('input[name=buyerEml2]').val());
            }else{
            	$('input[name=receiverNm]').val('');
            	$('input[name=receiverHp]').val('');
            	$('input[name=receiverZipCd]').val('');
            	$('input[name=receiverAdrSbc1]').val('');
            	$('input[name=receiverAdrSbc2]').val('');
            	$('input[name=receiverEml1]').val('');
            	$('input[name=receiverEml2]').val('');
            }
        }
	});
});

var formSubmitObj = {
	submitPayment : function(form){
		//
	},
    submit : function(form){
		
        if(!submitUtil.isEmpty(form.buyerNm)){
            return false;
        }
        
    	if(!submitUtil.isEmpty(form.buyerHp)){
            return false;
        }
       	
        if(!submitUtil.isEmpty(form.buyerEml1, '이메일을 입력해 주세요.')){
       		return false;
        }else if(!submitUtil.isEmpty(form.buyerEml2, '이메일을 입력해 주세요.')){
    		return false;
    	}else{
      		form.buyerEml.value = form.buyerEml1.value + '@' + form.buyerEml2.value;
        		 
      		if(!submitUtil.isEmail(form.buyerEml, '이메일 형식을 확인해 주세요.')){
            	return false;
            }
        }
        
        if(!submitUtil.isEmpty(form.buyerZipCd)){
            return false;
        }
        if(!(submitUtil.isEmpty(form.buyerAdrSbc1) || submitUtil.isEmpty(form.buyerAdrSbc2) )){
            return false;
        }
        
        
        if(!submitUtil.isEmpty(form.receiverNm)){
            return false;
        }
    	
    	if(!submitUtil.isEmpty(form.receiverHp)){
            return false;
        }
            
        if(!submitUtil.isEmpty(form.receiverEml1, '이메일을 입력해 주세요.')){
       		return false;
        }else if(!submitUtil.isEmpty(form.receiverEml2, '이메일을 입력해 주세요.')){
    		return false;
    	}else{
      		form.receiverEml.value = form.receiverEml1.value + '@' + form.receiverEml2.value;
        		 
      		if(!submitUtil.isEmail(form.receiverEml, '이메일 형식을 확인해 주세요.')){
            	return false;
            }
        }
        
        if(!submitUtil.isEmpty(form.receiverZipCd)){
            return false;
        }
        if(!(submitUtil.isEmpty(form.receiverAdrSbc1) || submitUtil.isEmpty(form.receiverAdrSbc2) )){
            return false;
        }
        
        if(!$("input[name=agree1]").is(":checked")){
			alert('개인정보 수집 및 이용 동의 해주세요.');
			return false;
		}
        
        if(!$("input[name=agree2]").is(":checked")){
			alert('개인정보 위탁 동의 해주세요.');
			return false;
		}
        
        if(!$("input[name=agree3]").is(":checked")){
			alert('구매진행에 동의 해주세요.');
			return false;
		}
        
        //결제 금액 최종 계산
        orderPriceCalculator.paymentPrice();
        
        ajaxUtil.postDisableAsync(form.action, $(form).serialize(), formSubmitObj.orderResult);
           
        return false;
    },
    orderResult : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        if(json.result != undefined && json.result.status){
        	var form = document.paymentForm;
        	var data = json.result.paymentInfo;
        	
        	<c:choose>
			<c:when test="${deviceCode eq 'N'}">
	        	form.buyername.value = $('input[name=buyerNm]').val();
	        	form.buyertel.value = $('input[name=buyerHp]').val();
	        	form.buyeremail.value = $('input[name=buyerEml]').val();
	        	form.goodname.value = data.goodName;
	        	form.mKey.value = data.mKey;
	       		form.mid.value = data.mid;
	      		form.oid.value = data.orderNo;
	     		form.price.value = data.price;
	        	form.timestamp.value = data.timestamp;
	        	form.signature.value = data.signature;
	        	
	        	INIStdPay.pay('paymentForm');
        	</c:when>
        	<c:when test="${deviceCode ne 'N'}">
	        	form.P_UNAME.value = $('input[name=buyerNm]').val();
	        	form.P_MOBILE.value = $('input[name=buyerHp]').val();
	        	form.P_EMAIL.value = $('input[name=buyerEml]').val();
	        	form.P_GOODS.value = data.goodName;
	       		form.P_MID.value = data.mid;
	      		form.P_OID.value = data.orderNo;
	     		form.P_AMT.value = data.price;
	        	
	        	form.submit();
        	</c:when>
        	</c:choose>
        }else{
            ajaxUtil.error(json);
        }
    }
};

$(document).ready(function(){
	//이용약관
	$('.terms_box a.all_btn01').on('click',function(){
    	$('.terms_box .gray_box_inner.box_01').slideToggle();
    });
	$('.terms_box a.all_btn02').on('click',function(){
    	$('.terms_box .gray_box_inner.box_02').slideToggle();
    });
});
</script>
