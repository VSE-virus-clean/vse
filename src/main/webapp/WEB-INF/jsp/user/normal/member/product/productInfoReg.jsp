<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
    JSP Name : productInfoReg.jsp
    Description : 마이페이지 > 정품등록 폼
--%>
<link rel="stylesheet" href="/resources/user/css/mypage.css?v=${cacheParam}">

<div id="container">
	<div class="order order_main_page mp_prd_page">
		<div class="inner">
		
			<%@ include file="/WEB-INF/jsp/user/normal/member/inc_mypageTop.jsp" %>
			
			<div class="step">
				<ul>
					<li style="width:50%" class="active" ><p><span class="num"><em>01</em></span> <span>제품 및 구입처 확인</span></p></li>
<!-- 					<li><p><span class="num"><em>02</em></span> <span>설문조사</span></p></li> -->
					<li style="width:50%"><p><span class="num"><em>02</em></span> <span>정품등록 완료</span></p></li>
				</ul>
			</div>

			<div class="mp_prd_wrap">
				<div class="mp_prd_wrap02 active">
					<form name="mainForm" method="post" enctype="multipart/form-data" onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
						<h4 class="cont_tit03">정품등록</h4>
						<div class="table-type01">
							<table>
								<colgroup>
									<col style="width:20%">
									<col style="width:80%">
								</colgroup>
								<tbody>
									<tr style="display:none;">
										<th>제품<br class="mo">선택</th>
										<td class="write_form">
											<span>
												<input type="hidden" id="prdSn" name="prdSn" value="5" />
												<input type="text" id="prdTitl" name="prdTitl" title="제품"  placeholder="제품을 선택해 주세요." readonly="readonly" value="Z-mini" />
												<a class="btn02 btn_pp add_search"><span>제품검색</span></a>
											</span>
										</td>
									</tr>
									<tr>
										<th>시리얼<br class="mo">번호</th>
										<td class="serial_numb">
											<input type="text" id="serialNo" name="serialNo" title="시리얼번호" value="" maxlength="13" />
										</td>
									</tr>
									<tr>
										<th>구입처</th>
										<td id="makCd" class="pay_radio clearfix"><!-- 구입처 목록 --></td>
									</tr>
									<tr class="trOrgSbc hidden">
										<th>사유<br class="mo">입력</th>
										<td class="flex_td">
											<textarea name="orgSbc" id="orgSbc" cols="30" rows="10" title="사유" placeholder="사유를 입력해주세요."></textarea>
										</td>
									</tr>
									<tr>
										<th>사진<br class="mo">첨부</th>
										<td class=" write_form">
											${function:printAttachFileRegUser(1, "THUMB", "사진첨부")}
											<p class="point_o"><em>※ </em> <span> 구매 영수증 등 구매 증빙 사진을 첨부해주세요.</span></p>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="btn_wrap">
							<a href="#" class="btn btn_pp submit" style="width: 330px;"><span>다음</span></a>
							<a href="/member/product/list.vc" class="btn btn_line" style="width: 330px;"><span>취소</span></a>
						</div>
					</form>
				</div>
			</div>

		</div>						
	</div>		
</div>

<%@ include file="/WEB-INF/jsp/user/normal/common/include/inc_modal.jsp" %>

<script src="/resources/user/js/order.js"></script>
<script>
$(function () {
    $('form[name=mainForm]').ready(function(){
    	comCodeUtil.getCodeNPrint('MARKET', 'makCd', 'radio');
    });
    
    //구입처 선택에 따라서 사유영역 노출/비노출
    $('input[name=makCd]').live('click', function(event){
    	if($(this).val() == 'MARKET-05'){
    		$('tr.trOrgSbc').removeClass('hidden');
    	}else{
    		$('tr.trOrgSbc').addClass('hidden');
    	}
    });
});

var formSubmitObj = {
    submit : function(form){
        if(!submitUtil.isEmpty(form.prdTitl)){
            return false;
        }
        
    	if(!submitUtil.isEmpty(form.serialNo, '시리얼번호를 입력해 주세요.')){
            return false;
        }else{
        	var pattern = /^[a-zA-Z0-9]{0,13}$/;
        	
            if(!pattern.test(form.serialNo.value)){
                return submitUtil.alertNfocus(form.serialNo, '시리얼번호는 영문/숫자만 입력 가능합니다.');
            }
        }

    	if(!submitUtil.isChecked(form.makCd, '구입처를 선택해 주세요.')){
            return false;
        }else{
        	//기타는 사유입력해야함.
        	if(form.makCd.value == 'MARKET-05'){
	        	if(!submitUtil.isEmpty(form.orgSbc, '사유를 입력해 주세요.')){
	                return false;
	            }
        	}
        }
        
        
        if(!submitUtil.isNull(form.attachFile1)){
            if(!submitUtil.isAttachFile(form.attachFile1, 'IMG')){
                return false;
            }
        }
        
        ajaxUtil.formSubmit($(form), formSubmitObj.result);
        
        return false;
    },
    result : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        if(json.bindingStatus == undefined && json.result != undefined && json.result.uploadInfo.status ){
            location.replace('${contextPath}/${requestUri}/complete.vc');
        }else{
        	alert('정상 시리얼번호가 아닙니다 확인바랍니다.');
        }
    }
};
</script>
