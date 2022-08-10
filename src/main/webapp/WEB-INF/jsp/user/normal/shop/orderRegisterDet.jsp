<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%-- 
    JSP Name : registerDet.jsp
    Description : 회원가입 > 메일등록 / 정보확인
    author Jeong.hyoungjea
    since 2017.02.01.
    version 1.0
    Modification Information
       since          author              description
    ===========    =============    ===========================
    2017.02.01.   Jeong.hyoungjea     최초 생성
--%>
<style>
p{max-height:100%;display:block;font-size:14px;margin-bottom:18px;line-height:1.8em;}
p.t{margin-top:5px; margin-bottom:0px;height:23px;line-height:1;font-weight:bold;}

.orderSubTitle {display: inline-block;height: 50px;line-height: 50px;font-size: 1.3em;font-weight: 500;text-align: left;}
.orderSubTitleLine { display:inline-block; line-height:50px; margin:auto; margin-right:20px; text-align:center; width:40px; height:2px; background-color:#2E54AB; vertical-align:+6px; }

</style>

<div id="container">
	<div id="pageContents">
		<h3>STEP 2. 注文情報 確認</h3>
		<p>
			<em class="red">※ は入力必須です。</em>
		</p>
		
		<form name="mainForm" method="post" action="/shop/order/registerReg" onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
			<input type="hidden" name="cotnSn" value="${result.cotnSn}">
			<input type="hidden" name="orderPwEnc" value="${result.orderPwEnc}">
			<input type="hidden" name="buyerNm" value="${result.buyerNm}">
			<input type="hidden" name="buyerNmPn" value="${result.buyerNmPn}">
			<input type="hidden" name="buyerTn" value="${result.buyerTn}">
			<input type="hidden" name="buyerEml" value="${result.buyerEml}">
			<input type="hidden" name="receiverNm" value="${result.receiverNm}">
			<input type="hidden" name="receiverNmPn" value="${result.receiverNmPn}">
			<input type="hidden" name="receiverTn" value="${result.receiverTn}">
			<input type="hidden" name="receiverEml" value="${result.receiverEml}">
			<input type="hidden" name="zipCd" value="${result.zipCd}">
			<input type="hidden" name="adrPref" value="${result.adrPref}">
			<input type="hidden" name="adrCity" value="${result.adrCity}">
			<input type="hidden" name="adrSbc1" value="${result.adrSbc1}">
			<input type="hidden" name="adrSbc2" value="${result.adrSbc2}">
			<input type="hidden" name="orderCost" value="${result.orderCost}">
			<input type="hidden" name="orderAmount" value="${result.orderAmount}">
			<input type="hidden" name="orderQuantity" value="${result.orderQuantity}">
			<input type="hidden" name="payTid" value="">
			<input type="hidden" name="token" value="">
			<input type="hidden" name="maskeCardNumber" value="">
			<input type="hidden" name="validUntil" value="">
			<input type="hidden" name="fingerprint" value="">
			<input type="hidden" name="hc" value="">
			
			<div class="tblbx">
				<table class="tbl_reg">
					<colgroup><col width="350px"><col width="*"></colgroup>
					<tbody>
						<tr>
							<th>商品名</th>
							<td><tag:html value="${resulItem.info.blcTitl}" attr="NQ" /></td>
						</tr>
						<tr>
							<th>注文数量</th>
							<td><em class="red">${result.orderQuantity} 個</em></td>
						</tr>
						<tr>
							<th>決済価格</th>
							<td><em class="red"><fmt:formatNumber value="${result.orderAmount}" type="number"/> 円</em></td>
						</tr>
					</tbody>
				</table>
				
				<span class="orderSubTitleLine"></span><span class="orderSubTitle">注文者情報</span>
				<table class="tbl_reg">
					<colgroup><col width="350px"><col width="*"></colgroup>
					<tbody>
						<c:choose>
						<c:when test="${empty sessionScope.sessionVO}">
							<tr>
								<th><em class="red">※</em>注文 パスワード </th>
								<td>**********</td>
							</tr>
						</c:when>
						</c:choose>
						<tr>
							<th><em class="red">※</em> お名前（漢字）</th>
							<td>${result.buyerNm}</td>
						</tr>
						<tr>
							<th><em class="red">※</em> お名前（カナ）</th>
							<td>${result.buyerNmPn}</td>
						</tr>
						<tr>
							<th><em class="red">※</em> 電話番号</th>
							<td>${result.buyerTn}</td>
						</tr>
						<tr>
							<th><em class="red">※</em> メールアドレス </th>
							<td>${result.buyerEml}</td>
						</tr>
					</tbody>
				</table>
				
				<span class="orderSubTitleLine"></span><span class="orderSubTitle">お届け先</span>
				<table class="tbl_reg">
					<colgroup><col width="350px"><col width="*"></colgroup>
					<tbody>	
						<tr>
							<th><em class="red">※</em> お名前（漢字）</th>
							<td>${result.receiverNm}</td>
						</tr>
						<tr>
							<th><em class="red">※</em> お名前（カナ）</th>
							<td>${result.receiverNmPn}</td>
						</tr>
						<tr>
							<th><em class="red">※</em> 電話番号</th>
							<td>${result.receiverTn}</td>
						</tr>
						<tr>
							<th><em class="red">※</em> メールアドレス </th>
							<td>${result.receiverEml}</td>
						</tr>
						
						<tr>
							<th><em class="red">※</em> 郵便番号 </th>
							<td>${result.zipCd}</td>
						</tr>
						<tr>
							<th><em class="red">※</em> 都道府県</th>
							<td>${result.adrPref}</td>
						</tr>
						<tr>
							<th><em class="red">※</em> 市区町村</th>
							<td>${result.adrCity}</td>
						</tr>
						<tr>
							<th><em class="red">※</em> 番地</th>
							<td>${result.adrSbc1}</td>
						</tr>
						<tr>
							<th>&nbsp;&nbsp;&nbsp;&nbsp; 建物名等</th>
							<td>${result.adrSbc2}</td>
						</tr>
					</tbody>
				</table>
				
				<span class="orderSubTitleLine"></span><span class="orderSubTitle">注文関連要請事項</span>
				<table class="tbl_reg">
					<colgroup><col width="350px"><col width="*"></colgroup>
					<tbody>	
						<tr>
							<th colspan="2">
								<textarea name="orderMemo" id="orderMemo" style="width:90%;height:50px;" title="注文関連要請事項">${result.orderMemo}</textarea>
							</th>
						</tr>
					</tbody>
				</table>
				
				<c:choose>
				<c:when test="${empty sessionScope.sessionVO or sessionScope.sessionVO.no ne '77038'}">
					<input type="hidden" name="payType" value="1" />
				</c:when>
				<c:otherwise>
					<div>
						<span class="orderSubTitleLine"></span><span class="orderSubTitle">결제방법 선택</span>
						<div style="margin-top:20px;">
							<input type="radio" name="payType" id="payType1" value="1" checked="checked" /><label for="payType1">계좌이체</label>
							<input type="radio" name="payType" id="payType2" value="2" style="margin-left:20px;"/><label for="payType2">카드</label>
						</div>
					</div>
					
					<div id="orderCardInfoBox" style="margin-top:30px; display:none;" >
						<span class="orderSubTitleLine"></span><span class="orderSubTitle">카드정보입력</span>
						<table class="tbl_reg">
							<colgroup><col width="350px"><col width="*"></colgroup>
							<tbody>	
								<tr>
									<th>Card Number</th>
									<td><input type="text" name="card_number" value="" title="Card Number"></td>
								</tr>
								<tr>
									<th>Expiry Date</th>
									<td>
										<span style="padding:0 10px;">Year : </span><input type="text" name="expire_year" style="width:60px" maxlength="2" value="" title="Expiry Date Year">
										<span style="padding:0 10px; padding-left:20px;">Month : </span><input type="text" name="expire_month"  style="width:60px" maxlength="2" value="" title="Expiry Date Month">
									</td>
								</tr>
								<tr>
									<th>Security Code(CVC)</th>
									<td><input type="text" name="cvc" value="" title="Security Code"></td>
								</tr>
								<tr>
									<th>Cardholder Name</th>
									<td><input type="text" name="name" value="" title="Cardholder Name"></td>
								</tr>
							</tbody>
						</table>
					</div>
				</c:otherwise>
				</c:choose>
			</div>
			
			<div class="btnarea btn_center">
				<a href="#" class="btnbx2 bdark submit">注文する</a>
				<a href="javascript:history.go(-1);" class="btnbx2 bdefault">キャンセル</a>
			</div>
		</form>
	</div>
</div>
<script type="text/javascript" src="https://token.paygent.co.jp/js/PaygentToken.js" charset="UTF-8"></script>  
<!-- <script type="text/javascript" src="https://sandbox.paygent.co.jp/js/PaygentToken.js" charset="UTF-8"></script>   -->
<script type="text/javascript">
$(function () {
	$('input[name=payType]').on('change', function(e) {
		if($(this).val() == 2) {
			$('#orderCardInfoBox').show();
		} else {
			$('#orderCardInfoBox').hide(); 
		}
	});
});

var merchantId = '43326';
var tokenKey = 'live_Wgbd1RIJl5V5bCtc3aPaFIPR';
// var merchantId = '38603';
// var tokenKey = 'test_cMtA6Mvs9ia6kl0Bgczd47kM';
var formSubmitObj = {
	submit : function(form){
		if(form.payType.value == 2){
			
			if(!submitUtil.isEmpty(form.card_number)){
	            return false;
	        }
			
			if(!submitUtil.isEmpty(form.expire_year)){
	            return false;
	        }
			
			if(!submitUtil.isEmpty(form.expire_month)){
	            return false;
	        }
			
			if(!submitUtil.isEmpty(form.cvc)){
	            return false;
	        }
			
			if(!submitUtil.isEmpty(form.name)){
	            return false;
	        }
			
			var paygentToken = new PaygentToken(); 
			paygentToken.createToken(        
				merchantId,
				tokenKey,
				{                                                               
					'card_number' : form.card_number.value,      
					'expire_year' : form.expire_year.value,         
					'expire_month' :  form.expire_month.value,  
					'cvc' : form.cvc.value,                                  
					'name' : form.name.value                            
				},
				formSubmitObj.payType2                                         
		     );    
		}else{
			formSubmitObj.payType1(form);
		}

        return false;
	},
	payType1 : function(form){
		ajaxUtil.postDisableAsync($(form).attr('action'), $(form).serialize(), formSubmitObj.result);
	},
	payType2 : function(response){
		var form = document.mainForm;
		
		if (response.result == '0000') {       
			form.payTid.value = response.tokenizedCardObject.token;
			form.token.value = response.tokenizedCardObject.token;
			form.maskeCardNumber.value = response.tokenizedCardObject.masked_card_number;
			form.validUntil.value = response.tokenizedCardObject.valid_until;
			form.fingerprint.value = response.tokenizedCardObject.fingerprint; 
			form.hc.value = response.hc; 
			
			ajaxUtil.postDisableAsync($(form).attr('action'), $(form).serialize(), formSubmitObj.result);
		}else{
			var errorMessage = [
					{'code' : '1300', 'msg':'カードナンバー入力エラー　－　カードナンバーを再入力してください'},
					{'code' : '1301', 'msg':'カードナンバーフォーマット入力エラー－カ　－　ドナンバーに数字以外の文字が含まれています'},
					{'code' : '1400', 'msg':'有効期限(年)入力エラー　－　正しい有効期限を再入力してください'},
					{'code' : '1401', 'msg':'有効期限(年)フォーマット入力エラー　－有効期限に数字以外の文字が含まれています'},
					{'code' : '1500', 'msg':'有効期限(月)入力エラー　－　正しい有効期限を再入力してください'},
					{'code' : '1501', 'msg':'有効期限(月)フォーマット入力エラー　－01～12まで数字を再移入力してください'},
					{'code' : '1502', 'msg':'有効期限(年/月)が間違っています'},
					{'code' : '1600', 'msg':'セキュリティーコード入力エラー　－　数字以外の文字が含まれています'},
					{'code' : '1700', 'msg':'カード名義入力エラー　－　半角英数字、カナ、スペース以外の文字を含む'},
					{'code' : '7000', 'msg':'ご利用のブラウザーは現在サポートされていません'},
					{'code' : '7001', 'msg':'決済の途中エラーが生じしました'},
					{'code' : '8000', 'msg':'このシステムはただ今メンテナンス中です'},
					{'code' : '9000', 'msg':'決済システム内部エラーです'}
			];
		}
	},
	result : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        if(json.bindingStatus == undefined && json.result != undefined && json.result.status){
           location.replace(json.result.returnUrl);
        }else{
			ajaxUtil.error(json);	
        }
    }
};
</script>