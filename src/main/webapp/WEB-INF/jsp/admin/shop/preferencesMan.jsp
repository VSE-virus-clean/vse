<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>	

<link rel="stylesheet" href="/resources/admin/ui_common/css/manage.css">
<style>
	form > div.content-body { display:none }
	form > div.content-body.active { display:block; }
</style>

<div class="sec_top" id="" >
	<h2 class="sec_tit">환경설정</h2>
	<ul class="top_tab">
		<li><a href="#">환경설정</a></li>
		<li><a href="#">운영정책관리</a></li>
	</ul>
</div>

<div class="sec_cont">
	<div class="sec_cont_top">
		<ul class="sec_top_tab">
			<li class="big"><a href="#">바이러스 클린 랩</a></li>
			<li class="space"><span>I</span></li>
			<li class="small"><a href="#">http://www.viruscleanlab.com</a></li>
		</ul>
	</div>
</div>

<div class="sec_cont">
	<div class="sec_cont_tab">
		<ul class="tab_wrap clearfix">
			<li class="tab_site active"><a href="#" data-tab="site"><span>사이트 정보</span></a></li>
			<li class="tab_member"><a href="#" data-tab="member"><span>회원설정</span></a></li>
			<li class="tab_coupon"><a href="#" data-tab="coupon"><span>쿠폰설정</span></a></li>
			<li class="tab_delivery"><a href="#" data-tab="delivery"><span>배송정책설정</span></a></li>
		</ul>
	</div>
	
	<form name="mainForm1" method="post" onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
		<input type="hidden" name="parentCd" value="SITE" />
		<div class="content-body tab_site active">
			<h4 class="cont_tit">웹사이트 정보</h4>
			<table class="cont_table" style="table-layout:fixed">
				<colgroup>
					<col width="25%">
					<col width="75%">                                       
				</colgroup>
				<tbody>
					<tr style="height:58px;">
						<th>
							<p>사이트명 명</p>
						</th>
						<td class="txt_le">
							<input type="text" name="siteName" style="width:95%;" value="${result.info.siteName}" >
						</td>
					</tr>
					<tr style="height:58px;">
						<th>
							<p>사이트 TITLE</p>
						</th>
						<td class="txt_le">
							<input type="text" name="siteTitle" style="width:95%;" value="${result.info.siteTitle}" >
						</td>
					</tr>
					<tr style="height:58px;">
						<th>
							<p>사이트 Keyword</p>
						</th>
						<td class="txt_le">
							<input type="text" name="siteKeyword" style="width:95%;" value="${result.info.siteKeyword}" >
						</td>
					</tr>
					<tr style="height:58px;">
						<th>
							<p>사이트 Description</p>
						</th>
						<td class="txt_le">
							<input type="text" name="siteDesc" style="width:95%;" value="${result.info.siteDesc}" >
						</td>
					</tr>
				</tbody>
			</table>
			<div class="btn_center_gorup">
				<button type="submit" class="btn btn_red" >저장</button>
			</div>
		</div>
	</form>
	
	<form name="mainForm2" method="post" onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
		<input type="hidden" name="parentCd" value="MEMBER" />
		<div class="content-body tab_member">
			<h4 class="cont_tit">회원탈퇴 설정</h4>
			<table class="cont_table" style="table-layout:fixed">
				<colgroup>
					<col width="25%">
					<col width="75%">                                     
				</colgroup>
				<tbody>
					<tr style="height:58px;">
						<th>
							<p>탈퇴시 정보삭제</p>
						</th>
						<td class="txt_le">
							<span>회원탈퇴 시, 회원정보를 <input type="text" name="memberOutdel" class="numOnly" value="${result.info.memberOutdel}" > 일 후 자동삭제</span>
						</td>
					</tr>
					<tr style="height:58px;">
						<th>
							<p>탈퇴아이디 사용</p>
						</th>
						<td class="txt_le">
							<span>회원탈퇴 시, 탈퇴한 아이디를 <input type="text" name="memberOutid" class="numOnly" value="${result.info.memberOutid}" > 일 후까지 사용할 수 있도록 처리</span>
						</td>
					</tr>
				</tbody>
			</table>
			<div class="btn_center_gorup">
				<button type="submit" class="btn btn_red" >저장</button>
			</div>
		</div>
	</form>
	
	<form name="mainForm3" method="post" onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
		<input type="hidden" name="parentCd" value="COUPON" />
		<div class="content-body tab_coupon">
			<h4 class="cont_tit">쿠폰결제</h4>
			<table class="cont_table" style="table-layout:fixed">
				<colgroup>
					<col width="25%">
					<col width="75%">                                     
				</colgroup>
				<tbody>
					<tr style="height:58px;">
						<th>
							<p>쿠폰결제 사용여부</p>
						</th>
						<td class="txt_le">
							<label class="radio_box" for="couponY"><input type="radio" name="couponYn" id="couponY" value="Y" /><span>사용</span></label>&nbsp;&nbsp;
		                    <label class="radio_box" for="couponN"><input type="radio" name="couponYn" id="couponN" value="N" /><span>미사용</span></label>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="content-body tab_coupon">
			<h4 class="cont_tit">쿠폰 지급 정책</h4>
			<table class="cont_table" style="table-layout:fixed">
				<colgroup>
					<col width="25%">
					<col width="75%">                                     
				</colgroup>
				<tbody>
					<tr style="height:58px;">
						<th>
							<p>환영쿠폰 설정</p>
						</th>
						<td class="txt_le">
							<c:set var="couponWelcom" value="${fn:split(result.info.couponWelcom, '|')}" />  
							<input type="hidden" name="couponWelcom" value="${result.info.couponWelcom}" >
							<input type="text" name="couponWelcomNm" value="${couponWelcom[1]}" >
							<a href="javascript:;" class="btn btn_gray" style="width:100px;text-align:center;" onclick="modalUtil.open('coupon-list', this);">쿠폰검색</a>
						</td>
					</tr>
				</tbody>
			</table>
			<div class="btn_center_gorup">
				<button type="submit" class="btn btn_red" >저장</button>
			</div>
		</div>
	</form>
	
	<form name="mainForm4" method="post" onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
		<input type="hidden" name="parentCd" value="DELIVERY" />
		<div class="content-body tab_delivery">
			<h4 class="cont_tit">배송료 사용 설정</h4>
			<table class="cont_table" style="table-layout:fixed">
				<colgroup>
					<col width="25%">
					<col width="75%">                                    
				</colgroup>
				<tbody>
					<tr style="height:58px;">
						<th>
							<p>배송료 사용 설정</p>
						</th>
						<td class="txt_le">
							<label class="radio_box" for="deliveryY"><input type="radio" name="deliveryYn" id="deliveryY" value="Y" /><span>사용</span></label>&nbsp;&nbsp;
		                    <label class="radio_box" for="deliveryN"><input type="radio" name="deliveryYn" id="deliveryN" value="N" /><span>미사용(무료)</span></label>
						</td>
					</tr>
				</tbody>
			</table>
		</div>
		<div class="content-body tab_delivery">
			<h4 class="cont_tit">배송정책</h4>
			<table class="cont_table" style="table-layout:fixed">
				<colgroup>
					<col width="25%">
					<col width="75%">                                    
				</colgroup>
				<tbody>
					<tr style="height:58px;">
						<th>
							<p>기본 배송비</p>
						</th>
						<td class="txt_le">
							<span>기본 배송지를 <input type="text" name="deliveryMin" class="numOnly" value="${result.info.deliveryMin}" > 원으로 설정</span>
						</td>
					</tr>
					<tr style="height:58px;">
						<th>
							<p>배송비 무료정책</p>
						</th>
						<td class="txt_le">
							<span><input type="text" name="deliveryFree" class="numOnly" value="${result.info.deliveryFree}" > 원 이상 구매시 배송비 무료 설정</span>
						</td>
					</tr>
					<tr style="height:58px;">
						<th>
							<p>배송 기간안내</p>
						</th>
						<td class="txt_le">
							<span>구매일 기준<input type="text" name="deliveryDate" class="numOnly" value="${result.info.deliveryDate}" > 일 이후 배송처리 안내</span>
						</td>
					</tr>
				</tbody>
			</table>
			<div class="btn_center_gorup">
				<button type="submit" class="btn btn_red" >저장</button>
			</div>
		</div>
	</form>
</div>


<!-- 쿠폰검색 -->
<div class="modal_window modal_wrap modal-coupon-list">
	<div class="modal_dialog">
		<div class="modal_content cont02">
			<div class="sec_top">
				<h3 class="sec_tit">쿠폰검색</h3>
				<a href="javascript:;" class="close modal_cls_btn"><img src="/resources/admin/images/common/close_btn.png" alt=""></a>
				
				<form name="subSearchForm" method="post" onsubmit="var rtn = formModalSubmitObj.submitSearch(this); if(!rtn){ submitUtil.enable(); } return rtn;">
					<input type="hidden" name="page" value="1" />
					<input type="hidden" name="rowLimit" value="5" />
					<input type="hidden" name="lgrpCd" value="이벤트쿠폰" />
					<div class="check_box" id="text_box_group">
						<input type="hidden" id="searchType" name="searchType" value="0">
						<input type="text" id="searchKey" name="searchKey" title="검색어입력" style="width:450px;" maxlength="100" minlength="2"/>
						<button type="submit">조회</button>
					</div>
				</form>
				
				<div class="content-body">
					<div class="table_tit">
						<div class="table_left_title">
							<span>총 <strong class="totCnt" style=" font-weight: 700;">0</strong>건 조회</span>
						</div>
					</div>

					<table class="cont_table" id="tab" style="TABLE-layout:fixed">
						<colgroup>
							<col style="width:80px;">
							<col style="">  
							<col style="width:10%;">
							<col style="width:10%;">
							<col style="width:15%;">
						<thead>
							<tr>
								<th>선택</th>
								<th>쿠폰명</th>
								<th>할인타입</th>
								<th>발급타입</th>
								<th>등록일</th>
							</tr>
						</thead>
						<tbody>
							<tr><td colspan="5" class="nodata">검색해 주세요.</td></tr>
<!-- 							<tr> -->
<!-- 								<td><input type="checkbox" name="check" class="check2" value="00000000000000001234" title="선택">	</td> -->
<!-- 								<td>ksm174</td> -->
<!-- 								<td>홍명진</td> -->
<!-- 								<td>010-4155-****</td> -->
<!-- 								<td>2019-05-28</td> -->
<!-- 							</tr> -->
						</tbody>
					</table>
					
					<div class="paging_wrap modal-paging" style="padding:20px 0; text-align: center;"></div>
					
					<div class="btn_center_gorup clearfix">
						<button type="button" class="btn btn_red btnSelectCoupon">선택</button>
						<button type="button" class="btn btn_gray modal_cls_btn">취소</button>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<div class="modal_backdrop"></div>

<script>
$(function () {
	$('input[name=couponYn]').ready(function(){
		$('input[name=couponYn][value=${empty result.info.couponYn ? "Y" : result.info.couponYn}]').prop('checked', true).trigger('click');
	});
	$('input[name=deliveryYn]').ready(function(){
		$('input[name=deliveryYn][value=${empty result.info.deliveryYn? "Y" : result.info.deliveryYn}]').prop('checked', true).trigger('click');
	});
	
	$('.sec_cont_tab ul li a').on('click',function(event){
		event.preventDefault();  
		var tab = $(this).data("tab");
		
		$('.sec_cont_tab ul li').removeClass('active');
		$('div.content-body').removeClass("active");

		$('.sec_cont_tab ul li.tab_' + tab).addClass('active');
		$('div.tab_' + tab).addClass('active');
	});
	
	/*
	 * 모달 닫기
	 */
	$('.modal_cls_btn').on('click', function(event){
		event.preventDefault();  
		modalUtil.close();
	});
	
	$('.modal-paging a').live('click', function(event){
		event.preventDefault();  
		
		if($(this).attr('href') !== '#'){
			$('form[name=subSearchForm] input[name=page]').val($(this).attr('href'));
			$('form[name=subSearchForm]').submit();
		}
	});
	
	//쿠폰선택
	$('.btnSelectCoupon').on('click', function(event){
		event.preventDefault();  
		if($('input[name=selectCoupon]:checked').length > 0){
			$('input[name=couponWelcom]').val($('input[name=selectCoupon]:checked').val());
			$('input[name=couponWelcomNm]').val($('input[name=selectCoupon]:checked').data('name'));
			modalUtil.close();
		}else{
			alert('쿠폰을 선택해 주세요.');
		}
	});
	
});


var modalUtil = {
	open : function(_id, object){
		ajaxUtil.postDisableAsync('${contextPath}/shop/preferences/coupon/list.vc', $('form[name=subSearchForm]').serialize(), formModalSubmitObj.resultSearch);
		
		$('body').addClass('modal_open');
		modalComUtil.open(_id);
	},    
	close : function(object){
		try{
			$('form[name^=sub]')[0].reset();
			$('.modal-coupon-list .totCnt').html(0);
			$('.modal-coupon-list table tbody').html('');
			$('.modal-coupon-list .modal-paging').html('');
			$('.id_box > ul').html('');
		}catch(e){
			//ignore
		}
		
		$('body').removeClass('modal_open');
		modalComUtil.close();
	}
};


var formModalSubmitObj = {
	submitSearch : function(form){
		ajaxUtil.postDisableAsync('${contextPath}/shop/preferences/coupon/list.vc', $(form).serialize(), formModalSubmitObj.resultSearch);
        return false;
	},
	resultSearch : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        debugger;
        if(json.result != undefined){
			var data = json.result.list;
			var htmlTxt = '';
			
			if(data && data.length > 0){
				for(var i = 0; i < data.length; i++)
				{
					htmlTxt += ' <tr>'
						    + ' 	<td>'
						    + ' 		<input type="radio" name="selectCoupon" data-name="' + data[i].cupTitl +'" value="'+ (data[i].cupMetaSn +'|'+ data[i].cupTitl) +'" title="선택">'
						    + ' 	</td>'
						    + ' 	<td>' + data[i].cupTitl + '</td>'
						    + ' 	<td>' + ( data[i].mgrpCd == 'WON' ? numUtil.createComma(data[i].price) + ' 원' : data[i].price + ' %' ) + '</td>'
						    + ' 	<td>' + data[i].lgrpCd + '</td>'
						    + ' 	<td>' + data[i].rgstDtm + '</td>'
						    + ' </tr>';
				}
			}else{
				htmlTxt = '<tr><td colspan="5" class="nodata">검색 데이터가 없습니다.</td></tr>';
			}
			
			
			ajaxPagging.create('', 'modal-paging', json.result.searchInfo.totalRow,  json.result.searchInfo.page, json.result.searchInfo.rowLimit, json.result.searchInfo.pageLimit);
			
			$('.modal-coupon-list .totCnt').html(json.result.searchInfo.totalRow);
			$('.modal-coupon-list table tbody').html(htmlTxt);
        }else{
            ajaxUtil.error(json);
        }
    },
    submitCouponReg : function(form){
    	//쿠폰선택
    },
   
};


var formSubmitObj = {
    submit : function(form){
        ajaxUtil.formSubmit($(form), formSubmitObj.result);
        return false;
    },
    result : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        if(json.bindingStatus == undefined && json.result != undefined && json.result.status ){
        	alert('저장 되었습니다.');
        }else{
           ajaxUtil.error(json);
        }
    }
};
</script>
