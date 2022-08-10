<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%-- 
    JSP Name : idInquiryMan.jsp
    Description : 아이디찾기 : 이메일주소 | HELP
--%>
<link rel="stylesheet" href="/resources/user/css/order.css?v=${cacheParam}">
<link rel="stylesheet" href="/resources/user/css/menber.css?v=${cacheParam}">

<div id="container">
	<div class="location_bar">
		<ul class="clearfix inner pcTab">
			<li><a href="/index.vc">Home</a></li>
			<li><a href="/help/idInquiry.vc">아이디찾기</a></li>
		</ul>
		<div class="mo inner">
			<a href=""></a>
		</div>			
	</div>
	<div id="requestBox" class="inquiry find ">
		<h3 class="p_tit p_tit02">아이디찾기</h3>
		<p class="find_info">가입한 회원정보(이름, 휴대폰 번호)로 아이디 찾기</p>
		<div class="inner_1 inner_2">							
			<form name="mainForm" method="post" onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
			<input type="hidden" name="certSn" title="" />
				<div class="log_form">
					<input type="text" name="mbrNm" title="이름" placeholder="이름을 입력해주세요"/>
					<div class="certInfo">
						<input type="text" name="mbrHp" class="telNumOnly" title="휴대번호" autocomplete="off" placeholder="휴대폰번호를 입력해주세요" maxlength="13" oninput="maxLengthCheck(this)"/>
						<a href="#" id="btnCertNo" class="btn btn_gray"><span>인증번호받기</span></a>
					</div>
					<div class="certInfo">
						<input type="password" name="certNo" class="numOnly" title="인증번호" autocomplete="off" placeholder="인증번호를 입력해주세요" maxlength="6" oninput="maxLengthCheck(this)"/>
						<div class="time">
							<p>인증번호 유효기간</p>
							<p id="certTimer" class="point_o"></p>
						</div>
					</div>
				</div>
				<div class="btn_wrap">
					<a href="#" class="btn btn_pp log_btn submit"><span>확인</span></a>
				</div>
			</form>
		</div>
	</div>		
		
	<div id="completeBox" class="inquiry find hidden">
		<h3 class="p_tit">아이디찾기</h3>
		<p class="find_info">회원님의 아이디 정보를 확인해주세요</p>
		<div class="inner_1 inner_2">							
			<div class="table-type01 write_form">
				<table>
					<colgroup>
						<col style="width: 200px;">
						<col>
					</colgroup>
					<tbody>							
						<tr>
							<th>아이디</th>
							<td></td>
						</tr>
						<tr>
							<th>이름</th>
							<td></td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="btn_wrap">
				<a href="/help/pwInquiry.vc" class="btn btn_pp "><span>비밀번호 찾기</span></a>
				<a href="/member/login.vc" class="btn btn_line"><span>로그인 하기</span></a>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
$(function () {
	$('a#btnCertNo').on({
        'click' : function(event){
            event.preventDefault();  
            formSubmitObj.submitCertNo($(this));
        }
    });
});

var formSubmitObj = {
	submit : function(form){
		
		if(!submitUtil.isEmpty(form.certSn, '인증번호를 재발송 해주시기 바랍니다.')){
        	return false;
        }else{
			if(!submitUtil.isEmpty(form.mbrNm)){
	            return false;
	        }
			
	        if(!submitUtil.isEmpty(form.mbrHp)){
	        	return false;
	        }else{
		        var regExp = /^(01[016789]{1})-?[0-9]{3,4}-?[0-9]{4}$/;
		        if( !regExp.test(form.mbrHp.value)) {
		        	submitUtil.alertNfocus(form.mbrHp, '휴대폰번호가 잘못 입력되었습니다.\n다시 입력해주세요.');
		            return false;
		        };
	        }
	        
	        if(!submitUtil.isEmpty(form.certNo)){
	        	return false;
        	}
        }
        
        ajaxUtil.postDisableAsync($(form).attr('action'), $(form).serialize(), formSubmitObj.result);

        return false;
	},
	result : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        clearTimer();
        
        if(json.result != undefined){
        	//TODO abc***/ 강** hidden처리해야함.
        	if(json.result.status){
        		$('#requestBox').hide();
        		
        		if(json.result.info.snsCd == 'HOME'){
	        		$('#completeBox').find('td:eq(0)').html(json.result.info.mbrId);
        		}else{
        			$('#completeBox').find('th:eq(0)').html('가입경로');
        			$('#completeBox').find('td:eq(0)').html(json.result.info.snsCd);
        		}
        		
        		$('#completeBox').find('td:eq(1)').html(json.result.info.mbrNm);
        		$('#completeBox').removeClass('hidden').show();
        	}else{
        		document.forms.mainForm.reset();
        		submitUtil.alertNfocus(document.forms.mainForm.mbrNm, json.result.errMsg);
        	}
        }else{
           ajaxUtil.error(json);
        }
        
        clearTimer();
    },
    submitCertNo : function(){
    	var form = document.forms.mainForm;
    	
        if(!submitUtil.isEmpty(form.mbrHp)){
        	return false;
        }else{
	        var regExp = /^(01[016789]{1})-?[0-9]{3,4}-?[0-9]{4}$/;
	        if( !regExp.test(form.mbrHp.value)) {
	        	submitUtil.alertNfocus(form.mbrHp, '휴대폰번호가 잘못 입력되었습니다.\n다시 입력해주세요.');
	            return false;
	        };
        }
        
        var data = { 
       		'hpNo' : form.mbrHp.value
       	}
        
        ajaxUtil.postDisableAsync("/help/sms/certSmsSend.vc", data, formSubmitObj.resultCertNo);

        return false;
	},
	resultCertNo : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        if(json.result != undefined){
        	if(json.result.status){
	        	document.forms.mainForm.certSn.value = json.result.info.certSn;
	        	initTimer(180);
        	}
        }else{
           ajaxUtil.error(json);
        }
    }
};

/**
 * 타이머
 * @param 초
 */
var timer;
function initTimer(iSeconds){
	var min = "";
	var sec = "";
	
	clearTimer();
	
	timer = setInterval(function(){
		min = parseInt(iSeconds / 60);
		sec = iSeconds%60;
		
		$('#certTimer').html(min + ':' + (sec < 10 ? "0" + sec : sec));
		
		iSeconds--;
		
		if(iSeconds < 0){
			clearInterval(timer);
			document.forms.mainForm.certSn.value = '';
        	document.forms.mainForm.certNo.value = '';
			$('#certTimer').html('유효시간이 종료되었습니다. 인증번호를 재발송 해주시기 바랍니다.');
		}
		
	}, 1000);
}

function clearTimer(){
	try{
		clearInterval(timer);
		$('#certTimer').html('');
	}catch(e){ }
}

</script>