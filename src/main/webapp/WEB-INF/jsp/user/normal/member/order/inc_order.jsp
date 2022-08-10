<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!-- 
	주문취소 영역
		- 교환/반품일 경우 반환배송료 6천원 차감
		/member/order/cancle.vc
-->
<div class="history_cancel history_wap" style="display:none;">
	<form name="subOrderCancleForm" method="post"enctype="multipart/form-data" action="/member/order/cancle.vc" onsubmit="var rtn = formOrderCancleSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
	<input type="hidden" name="orderNo" value="${result.info.orderNo}" />
	<input type="hidden" name="oldOrderCd" value="${result.info.orderCd}" />
	<input type="hidden" name="nowOrderCd" value="" />
	<input type="hidden" name="payAmt" value="${result.info.orderPrice}" />
	<input type="hidden" name="subAmt" value="0" />
	<input type="hidden" name="refAmt" value="0" />
		
		<div class="history_detail_wrap">
			<h4 class="cont_tit02 cont_tit02_first">주문취소 신청</h4>
			<div class="table-type01">
				<table>
					<colgroup>
						<col style="width:20%">
						<col style="width:80%">
					</colgroup>			
					<tbody>
						<tr>
							<th>사유선택</th>
							<td>
								<select name="excSbc1" id="excSbc1" title="사유" placeholder="취소사유를 선택해주세요." class="reason_select">
									<option value="">선택</option>
									<option value="단순변심">단순변심</option>
									<option value="옵션변경">옵션변경</option>
									<option value="상품 추가 후 재주문">상품 추가 후 재주문</option>
									<option value="배송지연">배송지연</option>
									<option value="기타">기타</option>
								</select>
							</td>
						</tr>
						<tr>
							<th>사유입력</th>
							<td >
								<textarea name="excSbc2" id="excSbc2" title="내용" placeholder="내용을 입력해주세요." class="reason_write"></textarea>
							</td>
						</tr>
						<tr>
							<th>사진첨부</th>
							<td class=" write_form">
								${function:printAttachFileRegUser(1, "THUMB", "사진첨부")}
								<p class="cancel_alert">※ 취소사유를 확인할 수 있는 사진을 등록해주시면 보다 신속한 교환 처리를 진행할 수 있습니다.</p>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			
			<c:if test="${result.info.payType eq 'VBANK' and result.info.orderCd ne '입금대기'}">
				<h4 class="cont_tit02">취소 / 환불정보</h4>
				<div class="table-type01">
					<table>
						<colgroup>
							<col style="width:20%">
							<col style="width:80%">
						</colgroup>			
						<tbody>
							<tr>
								<th>은행선택</th>
								<td>
									<input type="hidden" name="refBankNm" id="refBankNm" value="" />
									<select name="refBankCd" id="refBankCd" title="환불계좌 은행" placeholder="환불계좌 은행을 선택해주세요." class="reason_select">
										<option value="">선택</option>
										<option value="20">우리은행</option>
										<option value="23">SC제일은행</option>
										<option value="31">대구은행</option>
										<option value="32">부산은행</option>
										<option value="34">광주은행</option>
										<option value="35">제주은행</option>
										<option value="37">전북은행</option>
										<option value="38">강원은행</option>
										<option value="39">경남은행</option>
										<option value="53">한국씨티은행</option>
										<option value="71">우체국</option>
										<option value="81">하나은행</option>
										<option value="88">신한은행</option>
										<option value="89">케이뱅크</option>
										<option value="90">카카오뱅크</option>
									</select>
								</td>
							</tr>
							<tr>
								<th>계좌주명</th>
								<td>
									<input type="text" name="refAcctNm" id="refAcctNm" maxlength="50" title="환불계좌주명"  placeholder="환불계좌주명" />
								</td>
							</tr>
							<tr>
								<th>계좌번호</th>
								<td>
									<input type="text" name="refBankNum" id="refBankNum" class="numOnly" maxlength="50" title="환불계좌번호" placeholder="환불계좌번호(숫자만 입력)" />
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</c:if>
			
			
			<div class="f_pay">
				<div class="f_pay_inner">
					<ul>
						<li class="price">
							<p class="info">취소상품 금액합계</p>
							<p class="allPrice"><fmt:formatNumber value="${result.info.orderPrice}" type="number"/></p>
						</li>
						<li class="delivery">
							<p class="info">취소금액 차감내역</p>
							<p class="allDelivery">0</p>
						</li>
						<li class="total">
							<p class="info">취소 합계</p>
							<p class="allTotal">0</p>
						</li>
					</ul>
				</div>
<!-- 				<div class="f_pay_inner_b"> -->
<!-- 					<ul> -->
<!-- 						<li class="bg_gray"><span class="cancel_total_txt">취소상품합계</span><span>500,000원</span></li> -->
<!-- 						<li class="bg_gray"><span class="cancel_total_txt">추가배송비</span><span>2,500원</span></li> -->
<!-- 						<li><span class="cancel_total_txt">반품금액</span><span>112,500원</span></li> -->
<!-- 					</ul> -->
<!-- 				</div> -->
			</div>
		</div>
		
		<div class="btn_wrap btn_back">
			<a href="#" class="btn btn_pp submit" style="width: 330px;"><span>신청</span></a>
			<a href="#" class="btn btn_line btnHistoryModalClose" style="width: 330px;"><span>취소</span></a>
		</div>
	</form>
</div>	

<script>

$(function(){
	$('a.btnHistoryModalShow').on('click', function(event){
		event.preventDefault();  
		$('.history_detail').hide();
		$('.history_cancel').show();
		
		$('form[name=subOrderCancleForm] input[name=nowOrderCd]').val($(this).data('cd'));
		var payAmt = parseInt($('form[name=subOrderCancleForm] input[name=payAmt]').val());
		
		if($(this).data('cd') == '주문취소'){
			var subAmt = 0;
			var refAmt = payAmt - subAmt;
			
			$('form[name=subOrderCancleForm] input[name=subAmt]').val(subAmt);
			$('div.f_pay p.allDelivery').html(numUtil.createComma(subAmt)) ;
			
			$('form[name=subOrderCancleForm] input[name=refAmt]').val(refAmt);
			$('div.f_pay p.allTotal').html(numUtil.createComma(refAmt)) ;
			
			$('.history_detail_wrap h4.cont_tit02_first').html('주문취소');
		}
		
		if($(this).data('cd') == '주문취소신청'){
			var subAmt = 0;
			var refAmt = payAmt - subAmt;
			
			$('form[name=subOrderCancleForm] input[name=subAmt]').val(subAmt);
			$('div.f_pay p.allDelivery').html(numUtil.createComma(subAmt)) ;
			
			$('form[name=subOrderCancleForm] input[name=refAmt]').val(refAmt);
			$('div.f_pay p.allTotal').html(numUtil.createComma(refAmt)) ;
			
			$('.history_detail_wrap h4.cont_tit02_first').html('주문취소 신청');
		}
		
		if($(this).data('cd') == '반품신청'){
			var subAmt = 6000;
			var refAmt = payAmt - subAmt;
			
			if(refAmt < 0 ){
				refAmt = 0;
			}
			
			$('form[name=subOrderCancleForm] input[name=subAmt]').val(subAmt);
			$('div.f_pay p.allDelivery').html(numUtil.createComma(subAmt)) ;
			
			$('form[name=subOrderCancleForm] input[name=refAmt]').val(refAmt);
			$('div.f_pay p.allTotal').html(numUtil.createComma(refAmt)) ;
			
			$('.history_detail_wrap h4.cont_tit02_first').html('반품 신청');
		}
		
		if($(this).data('cd') == '교환신청'){
			var subAmt = 6000;
			var refAmt = payAmt - subAmt;
			
			if(refAmt < 0 ){
				refAmt = 0;
			}
			
			$('form[name=subOrderCancleForm] input[name=subAmt]').val(subAmt);
			$('div.f_pay p.allDelivery').html(numUtil.createComma(subAmt)) ;
			
			$('form[name=subOrderCancleForm] input[name=refAmt]').val(refAmt);
			$('div.f_pay p.allTotal').html(numUtil.createComma(refAmt)) ;
			
			$('.history_detail_wrap h4.cont_tit02_first').html('교환 신청');
		}
	});
	
	
	$('a.btnHistoryMody').on('click', function(event){
		event.preventDefault();  
		
		var msg = '';
		if($(this).data('cd') == '구매확정'){
			msg = '구매확정 하시겠습니까?';
		}else{
			msg = '주문취소철회를 진행 하시겠습니까?';
		}
		
		if(confirm(msg)){
			$('form[name=subOrderCancleForm] input[name=nowOrderCd]').val($(this).data('cd'));
			$('form[name=subOrderCancleForm] input[name=payAmt]').val(0);
			$('form[name=subOrderCancleForm] input[name=subAmt]').val(0);
			$('form[name=subOrderCancleForm] input[name=refAmt]').val(0);
			btnHistoryMody = 'Y';
			$('form[name=subOrderCancleForm]').submit();
		}
	});
	
	
	$('a.btnHistoryModalClose').on('click', function(event){
		event.preventDefault();  
		$('.history_wap').hide();
		$('.history_detail').show();
	});
	
	$('form[name=subOrderCancleForm] select[name=refBankCd]').live('change', function(event){
 		$('form[name=subOrderCancleForm] input[name=refBankNm]').val($(this).find('option:selected').text());
	});
});

var btnHistoryMody = 'N';
var formOrderCancleSubmitObj = {
	submit : function(form){
		if(btnHistoryMody == 'N'){
	        if(!submitUtil.isEmpty(form.excSbc1)){
	            return false;
	        }
	        
	        if(!submitUtil.isEmpty(form.excSbc2)){
	            return false;
	        }
	        
	        if(!submitUtil.isNull(form.attachFile1)){
	            if(!submitUtil.isAttachFile(form.attachFile1, 'IMG')){
	                return false;
	            }
	        }
		}
        
        ajaxUtil.formSubmit($(form), formOrderCancleSubmitObj.result);
        
        return false;
	},
	result : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        if(json.bindingStatus == undefined && json.result != undefined && json.result.status){
        	//alert($('form[name=subOrderCancleForm] input[name=nowOrderCd]').val());
        	location.reload(true);
        }else{
            ajaxUtil.error(json);
        }
    }
}
</script>
