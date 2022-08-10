<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%-- 
    JSP Name : agreeMan.jsp
    Description : 회원가입 > 약관동의
--%>
<link rel="stylesheet" href="/resources/user/css/menber.css?v=${cacheParam}">

<div id="container">
	<div class="location_bar">
		<ul class="clearfix inner pcTab">
			<li><a href="/index.vc">Home</a></li>
			<li class="active"><a href="#">회원가입</a></li>
		</ul>
		<div class="mo inner">
			<a href=""></a>
		</div>			
	</div>
	<div class="agree">
		<div class="inner">
			<div class="inner_m">
				<h3 class="p_tit02">회원가입</h3>
	
				<div class="step">
					<ul>
						<li class="active"><p><span class="num"><em>01</em></span> <span>약관동의</span></p></li>
						<li><p><span class="num"><em>02</em></span> <span>회원정보 입력</span></p></li>
						<li><p><span class="num"><em>03</em></span> <span>가입완료</span></p></li>
					</ul>
				</div>
			</div>
			<form name="mainForm" method="post" action="/registration/register.vc" onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
				<div class="join">
					<h4 class="cont_tit02"><span>Step 01</span> 약관동의</h4>
					<div class="tb_head">
						<label class="input_checkbox" for="allAgree">
							<input type="checkbox" id="allAgree" class="checkAgreeAll">
							<span>개인정보 수집, 이용, 제공에 모두 동의합니다 (전체동의)</span>
						</label>
					</div>
					<div class="tb_box">
						<div class="agree_check">
							<label class="input_checkbox" for="agree1">
								<input type="checkbox" id="agree1" name="agree1" class="checkbox-agree">
								<span><em class="point_o">[필수]</em><em>만 14세 이상입니다.</em></span>
							</label>
						</div>
						<div class="agree_check">
							<label class="input_checkbox" for="agree2">
								<input type="checkbox" id="agree2" name="agree2" class="checkbox-agree">
								<span><em class="point_o">[필수]</em><em>이용약관</em></span>
							</label>
						</div>
						<div class="agree_cont_box">
							<%@ include file="/WEB-INF/jsp/user/normal/registration/inc_service1.jsp" %>
						</div>
						<div class="agree_check">
							<label class="input_checkbox" for="agree3">
								<input type="checkbox" id="agree3" name="agree3" class="checkbox-agree">
								<span><em class="point_o">[필수]</em><em>개인정보처리방침</em></span>
							</label>
						</div>
						<div class="agree_cont_box">
							<%@ include file="/WEB-INF/jsp/user/normal/registration/inc_service2.jsp" %>
						</div>
						<div class="agree_check">
							<label class="input_checkbox" for="agree4">
								<input type="checkbox" id="agree4" name="agree4" class="checkbox-agree">
								<span><em class="point_o">[필수]</em><em>표준약관</em></span>
							</label>
						</div>
						<div class="agree_cont_box">
							<%@ include file="/WEB-INF/jsp/user/normal/registration/inc_service3.jsp" %>
						</div>
						<div class="agree_check">
							<label class="input_checkbox" for="agree5">
								<input type="checkbox" id="agree5" name="agree5" class="checkbox-agree">
								<span><em class="point_o">[필수]</em><em>이메일무단수집거부</em></span>
							</label>
						</div>
						<div class="agree_cont_box">
							<%@ include file="/WEB-INF/jsp/user/normal/registration/inc_service4.jsp" %>
						</div>
						<div class="agree_check">
							<label class="input_checkbox" for="agree6">
								<input type="checkbox" id="agree6" name="smsYn" class="checkbox-agree" value="Y">
								<span><em class="point_o">[선택]</em><em>이벤트, 프로모션 알림 메일 및 SMS수신</em></span>
							</label>
						</div>
						<!-- <div class="agree_cont_box">
							본 웹사이트에 게시된 이메일 주소가 전자우편 수집 프로그램이나 그 밖의 기술적 장치를 이용하여 무단으로 수집되는 것을 거부하며, 이를 위반 시 정보 통신망법에 의해 형사처벌 됨을 유념하시기 바랍니다.<br><br><br>
							<b>[ 게시일 2007년 4월 1일 ]</b><br><br>
							이메일을 기술적 장치를 사용하여 무단으로 수집, 판매, 유통하거나 이를 이용한 자는 [정보통신망이용촉진 및 정보보호 등에 관현 법률] 제 74조 5규정에 의하여 1년 이하의 징역 또는 1천만원 이하의 벌금에 처해집니다. 만일, 위와 같은 기술적 장치를 사용한 이메일주소 무단수집 피해를 당하신 경우 [불법스팸대응센터] 전용전화 (T.118)나 홈페이지 (www.spamcop.or.kr)의 신고창을 통하여 신고하여 주시기 바랍니다.<br><br>
							<b>제50조의2(전자우편주소의 무단 수집행위 등 금지)</b><br><br>
							① 누구든지 인터넷 홈페이지 운영자 또는 관리자의 사전 동의 없이 인터넷 홈페이지에서 자동으로 전자우편주소를 수집하는 프로그램이나 그 밖의 기술적 장치를 이용하여 전자우편주소를 수집하여서는 아니 된다.<br>
							② 누구든지 제1항을 위반하여 수집된 전자우편주소를 판매·유통하여서는 아니 된다.<br><br>
							③ 누구든지 제1항과 제2항에 따라 수집·판매 및 유통이 금지된 전자우편주소임을 알면서 이를 정보전송에 이용하여서는 아니 된다.<br><br>
						</div> -->
					</div>
					<div class="btn_wrap">
						<a href="#" class="btn btn_pp submit" style="width: 330px;"><span>회원가입</span></a>
					</div>
				</div>
			</form>
		</div>						
	</div>		
</div>

<script type="text/javascript">
$(document).ready(function(){
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
	    		if($('input.checkbox-agree:checked').length == 6){
	    			$('input.checkAgreeAll').prop('checked', true);
	    		}
	    	}
	    }
	});
});

var formSubmitObj = {
	submit : function(form){
        if(!$("input[name=agree1]").is(":checked")){
			alert('만 14세 이상 동의 해주세요.');
			return false;
		}
        
        if(!$("input[name=agree2]").is(":checked")){
			alert('이용약관 동의 해주세요.');
			return false;
		}
        
        if(!$("input[name=agree3]").is(":checked")){
			alert('개인정보처리방침 동의 해주세요.');
			return false;
		}
        
        if(!$("input[name=agree4]").is(":checked")){
			alert('표준약관 동의 해주세요.');
			return false;
		}
        
        if(!$("input[name=agree5]").is(":checked")){
			alert('이메일 무단수집거부 동의 해주세요.');
			return false;
		}

        return true;
	}
};
</script>
