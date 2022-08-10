<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
	
<link rel="stylesheet" href="/resources/admin/ui_common/css/order.css">

<div class="sec_top" id="" >
	<h2 class="sec_tit">주문관리</h2>
	<ul class="top_tab">
		<li><a href="#">주문관리</a></li>
		<li><a href="#">주문현황</a></li>
		<li><a href="#">주문상세</a></li>
	</ul>
</div>
<div class="sec_cont">
	<div class="content-body content-body01">
		<h4 class="cont_tit">주문정보</h4>

		<table class="cont_table" style="TABLE-layout:fixed">
			<colgroup>
				<col ><col ><col >                                       
			</colgroup>
			<thead>
				<tr style="height:58px;">
					<th><p>주문번호</p></th>
					<th><p>주문일시</p></th>
					<th><p>결제금액</p></th>
				</tr>
			</thead>
			<tbody>
				<tr style="height:58px;">
					<td><p>${result.info.orderNo}</p></td>
					<td><p><span>${result.info.userViewDtm}</span></p></td>
					<td><p><fmt:formatNumber value="${result.info.orderPrice}" type="number"/>원</p></td>
				</tr>
			</tbody>
		</table>

	</div>
                   
	<div class="content-body content-body02">

        <!-- 조회 리스트 -->
		<div class="cont_tit_wrap">
			<h4 class="cont_tit">총 1건 조회</h4>
			<a href=""><span></span></a>
		</div>
        <table class="cont_table cont_table02">
            <colgroup>
                <col width="150px">
				<col width="">
                <col width="12%">
                <col width="10%">
                <col width="15%">
                <col width="15%">
				<col width="15%">
            </colgroup>
            <thead>
                <tr>
                    <th>이미지</th>
					<th>상품명</th>
					<th>주문상태</th>
					<th>수량</th>
					<th>사용쿠폰</th>
					<th>상품금액</th>
					<th>원가</th>
                </tr>
            </thead>
            <tbody>
                <tr class="txt_ct">
                    <td class="prd_img"><img src="/resources/user/images/product/product_img.jpg" alt=""></td>
					<td>${result.info.goodNm}</td>
					<td>
						<select name="orderCd" id="orderCd">
							<c:if test="${result.info.orderCd eq '결제완료'}">
								<option value="결제완료">결제완료</option>
								<option value="배송준비중">배송준비중</option>
							</c:if>
							<c:if test="${result.info.orderCd eq '배송준비중'}">
								<option value="배송준비중">배송준비중</option>
								<option value="배송중">배송중</option>
							</c:if>
							<c:if test="${result.info.orderCd eq '배송중'}">								
								<option value="배송중">배송중</option>
								<option value="배송완료">배송완료</option>
							</c:if>
							<c:if test="${result.info.orderCd eq '배송완료'}">			
								<option value="배송완료">배송완료</option>
							</c:if>
							<c:if test="${result.info.orderCd eq '구매확정'}">			
								<option value="구매확정">구매확정</option>
							</c:if>
							<c:if test="${result.info.orderCd eq '주문취소'}">			
								<option value="주문취소">주문취소</option>
							</c:if>
							<c:if test="${result.info.orderCd eq '주문취소신청'}">			
								<option value="주문취소신청">주문취소신청</option>
								<option value="주문취소완료">주문취소완료</option>
							</c:if>
							<c:if test="${result.info.orderCd eq '반품신청'}">		
								<option value="반품신청">반품신청</option>
								<option value="반품완료">반품완료</option>
							</c:if>
							<c:if test="${result.info.orderCd eq '교환신청'}">		
								<option value="교환신청">교환신청</option>
								<option value="교환완료">교환완료</option>
							</c:if>
						</select>
					</td>
					<td><p><fmt:formatNumber value="${result.info.orderQuantity}" type="number"/> 개</p></td>
					<td><p><fmt:formatNumber value="${result.info.discountPrice}" type="number"/> 원</p></td>
					<td><p><fmt:formatNumber value="${result.info.productPrice}" type="number"/> 원</p></td>
					<td><p><fmt:formatNumber value="${result.info.orderPrice}" type="number"/> 원</p></td>
                 </tr>
                 <tr class="txt_ri">
					<th colspan="5">
						<p>상품 총 금액</p>
					</th>
                    <td colspan="2">
						<p><fmt:formatNumber value="${result.info.productPrice}" type="number"/>원</p>
					</td>
                 </tr>
                 <tr class="txt_ri">
					<th colspan="5">
						<p>배송비 총액</p>
					</th>
                    <td colspan="2">
						<p><fmt:formatNumber value="${result.info.deliveryFee}" type="number"/>원</p>
					</td>
                </tr>
				<tr class="txt_ri">
					<th colspan="5">
						<p>환불금액</p>
					</th>
                    <td colspan="2">
						<p>0원</p>
					</td>
                </tr>
				<tr class="txt_ri">
					<th colspan="5" class="txt_red">
						<p>주문 총 결제금액</p>
					</th>
                    <td colspan="2" class="txt_red">
						<p><fmt:formatNumber value="${result.info.orderPrice}" type="number"/>원</p>
					</td>
                </tr>
            </tbody>                                
        </table>
<!-- 		<div class="btn_left_gorup "> -->
<!-- 			<button type="button" class="btn btn_gray" onclick="searchList(1);return false;">주문취소</button> -->
<!-- 			<button type="button" class="btn btn_gray" onclick="searchList(1);return false;">반품완료</button> -->
<!-- 		</div> -->
    </div>
	<div class="content-body content-body03">
		<div class="cont_tit_wrap">
			<h4 class="cont_tit">주문자정보</h4>
			<a href=""><span></span></a>
		</div>
		<table class="cont_table" style="TABLE-layout:fixed">
			<colgroup>
				<col width="15%">
				<col width="20%">    
				<col width="20%">
				<col width="25%">                                   
			</colgroup>
			<thead>
				<tr style="height:58px;">
					<th><p>이름</p></th>
					<th><p>일반전화</p></th>
					<th><p>휴대전화</p></th>
					<th><p>이메일주소</p></th>
				</tr>
			</thead>
			<tbody>
				<tr style="height:58px;">
					<td><p>${result.info.buyerNm}</p></td>
					<td><p>${result.info.buyerTn}</p></td>
					<td><p>${result.info.buyerHp}</p></td>
					<td><p>${result.info.buyerEml}</p></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="content-body content-body03">
		<div class="cont_tit_wrap">
			<h4 class="cont_tit">수취인정보</h4>
			<a href=""><span></span></a>
		</div>
		<table class="cont_table" style="TABLE-layout:fixed">
			<colgroup>
				<col width="15%">
				<col width="20%">    
				<col width="20%">
				<col width="25%">                                   
			</colgroup>
			<thead>
				<tr style="height:58px;">
					<th><p>이름</p></th>
					<th><p>일반전화</p></th>
					<th><p>휴대전화</p></th>
					<th><p>이메일주소</p></th>
				</tr>
			</thead>
			<tbody>
				<tr style="height:58px;">
					<td><p>${result.info.receiverNm}</p></td>
					<td><p>${result.info.receiverTn}</p></td>
					<td><p>${result.info.receiverHp}</p></td>
					<td><p>${result.info.receiverEml}</p></td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="content-body content-body04">
		<div class="cont_tit_wrap">
			<h4 class="cont_tit">배송정보</h4>
			<a href=""><span></span></a>
		</div>
		<table class="cont_table" style="TABLE-layout:fixed">
			<colgroup>
				<col width="">
				<col width="15%">
				<col width="13%">
				<col width="">
			</colgroup>
			<thead>
				<tr style="height:58px;">
					<th><p>주소</p></th>
					<th><p>송장번호</p></th>
					<th><p>배송시간</p></th>
					<th><p>요청사항</p></th>
				</tr>
			</thead>
			<tbody>
				<tr style="height:58px;" class="txt_ct">
					<td><p>[${result.info.receiverZipCd}] ${result.info.receiverAdrSbc1} ${result.info.receiverAdrSbc2} </p></td>
					<td><p>${result.info.deliveryNum}</p></td>
					<td><p>${result.info.deliveryStrDtm}</p></td>
					<td class="txt_le">
						<p><tag:html value="${result.info.orderMemo}" attr="BR" /></p>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	<div class="content-body content-body05">
		<div class="cont_tit_wrap">
			<h4 class="cont_tit">결제정보</h4>
			<a href=""><span></span></a>
		</div>
		<table class="cont_table" style="TABLE-layout:fixed">
			<colgroup>
				<col width="">
				<col width="">
				<col width="">                                       
			</colgroup>
			<thead>
				<tr style="height:58px;">
					<th><p>결제방식</p></th>
					<th><p>결제일시</p></th>
					<th class="txt_red"><p>총 결제 금액</p></th>
				</tr>
			</thead>
			<tbody>
				<tr style="height:58px;">
					<td><p>${result.info.payTypeTxt}</p></td>
					<td><p>${result.info.payDtm}</p></td>
					<td><p><fmt:formatNumber value="${result.info.orderPrice}" type="number"/>원</p></td>
				</tr>
			</tbody>
		</table>
	</div>
	<!--  div class="content-body content-body06">
		<div class="cont_tit_wrap">
			<h4 class="cont_tit">메모</h4>
			<a href=""><span></span></a>
		</div>
		<table class="cont_table" style="TABLE-layout:fixed">
			<colgroup>
				<col width="">                                     
			</colgroup>
			<tbody>
				<tr style="height:58px;">
					<td class="txtar">
						<textarea name="" id="" ></textarea>
					</td>
				</tr>
			</tbody>
		</table>
	</div -->
	
	<div class="content-body content-body07">
		<div class="cont_tit_wrap">
			<h4 class="cont_tit">이력보기</h4>
			<a href=""><span></span></a>
		</div>
		<table class="cont_table" style="TABLE-layout:fixed">
			<colgroup>
				<col width="220px">
				<col width="170px">
				<col width="">
				<col width="300px">
			</colgroup>
			<thead>
				<tr style="height:58px;">
					<th><p>시간</p></th>
					<th><p>상태</p></th>
					<th><p>사유</p></th>
					<th><p>첨부파일</p></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${result.history}" var="data" varStatus="i">
					<tr style="height:58px;">
						<td class="">
							<p>${data.rgstDtm}</p>
						</td>
						<td>
							<p>${data.nowOrderCd}</p>
						</td>
						<td class="txt_le">
							<p>
								<c:if test="${not empty data.excSbc1}">[${data.excSbc1}]<br></c:if>${data.excSbc2}
							</p>
							<c:if test="${not empty data.refBankCd}">
								<p style="padding-top:20px;">
									[환불계좌정보] ${data.refBankNm} / ${data.refAcctNm} / ${data.refBankNum}
								</p>
							</c:if>
						</td>
						<td class="txt_le">
							<p>${function:printImageFileByList(data.fileSn, "ORDER_HIST", data.filNm, data.filNm)}</p>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<div class="btn_center_gorup ">
<!-- 			<button type="button" class="btn btn_red" onclick="searchList(1);return false;">주문취소</button> -->
			<button type="button" class="btn btn_gray" onclick="location.href='${contextPath}/${requestUri}/list.vc?${function:searchQuery(result.searchInfo)}'; ">목록</button>
		</div>
	</div>
</div>

<div class="modal_window01" style="z-index: 1010; display: none;" id="popup01">
	<div class="modal_window_wrap ">
		<div class="modal_tit txt_ct clearfix">
			<h3 class="">사유확인</h3>
			<a href="javascript:;" class="btn_cancel "><img src="/resources/user/ui_common/images/close_x_btn.png" alt=""></a>
		</div>
		<div class="inner_pop">
			<div class="modal_cont">
				<h4 class="cont_tit">주문자정보</h4>
				<table class="cont_table" style="TABLE-layout:fixed">
					<colgroup>
						<col width="20%">
						<col width="80%">                                       
					</colgroup>
					<tbody>
						<tr style="height:58px;">
							<th>
								<p>검색어</p>
							</th>
							<td class="txt_le">
								<select name="" id="" >
									<option value="">배송지연</option>
									<option value="">배송지연</option>
									<option value="">배송지연</option>
									<option value="">배송지연</option>
								</select>
							</td>
						</tr>
						<tr style="height:58px;">
							<th>
								<p>사유확인</p>
							</th>
							<td class="txt_le ">
								<textarea name="" id="" placeholder="상품에 하자가 있어 교환 신청 드려요." class="bg_gray"></textarea>	
							</td>
						</tr>
						<tr style="height:58px;">
							<th>
								<p>첨부파일</p>
							</th>
							<td class="k">
								<p>교환신청.jpg</p>
							</td>
						</tr>

					</tbody>
				</table>
				<div class="btn_center_gorup ">
					<button type="button" class="btn btn_red btn_cancel" onclick="">닫기</button>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="modal_backdrop" style="display: none;" ></div>

<form name="subOrderCancleForm" method="post" enctype="multipart/form-data" action="${contextPath}/shop/order/status/modify.vc" onsubmit="var rtn = formOrderCancleSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
	<input type="hidden" name="orderNo" value="${result.info.orderNo}" />
	<input type="hidden" name="oldOrderCd" value="${result.info.orderCd}" />
	<input type="hidden" name="nowOrderCd" value="" />
	<input type="hidden" name="excSbc2" value="" />
</form>	
<script>

$(document).ready(function(){
	$('.btn_open').on('click',function(){
		event.preventDefault();
		$('.modal_window').show();
		$('.modal_backdrop').show();
	});
		
	$('.btn_cancel').on('click',function(){
		$('.modal_window, .modal_window01').hide();
		$('.modal_backdrop').hide();
	});

	
	$('select[name=orderCd]').on('change', function(event){
		var msg = '주문상태를 ${result.info.orderCd} 에서 ' + $(this).val() + ' 으로 변경'
		if(confirm(msg + ' 하시겠습니까?')){
			$('form[name=subOrderCancleForm] input[name=nowOrderCd]').val($(this).val());
			$('form[name=subOrderCancleForm] input[name=excSbc2]').val('[${sessionScope.sessionVO.name}]' + msg);
			$('form[name=subOrderCancleForm]').submit();
		}
	});
	
});

function openPop(){	
	$('body').addClass('modal_open');
	$('.modal_backdrop').show();
	$('#popup01').show();
}//팝업열기


var formOrderCancleSubmitObj = {
	submit : function(form){
        ajaxUtil.formSubmit($(form), formOrderCancleSubmitObj.result);
        return false;
	},
	result : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        if(json.bindingStatus == undefined && json.result != undefined && json.result.status){
        	location.reload(true);
        }else{
            ajaxUtil.error(json);
        }
    }
}
</script>
</html>
