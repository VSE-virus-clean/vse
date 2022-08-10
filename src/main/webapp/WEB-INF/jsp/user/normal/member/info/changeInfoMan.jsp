<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%-- 
    JSP Name : changeInfoMan.jsp
    Description : 회원 > 개인정보변경
--%>
<link rel="stylesheet" href="/resources/user/css/menber.css?v=${cacheParam}">
<style>
	#btnCheckNick { display:none; }
</style>

<div id="container" class="info_check">
	<div class="addinfo">
		<div class="inner">
			<h3 class="p_tit02">회원정보수정</h3>
			<div class="join">
				<div class="info_confirm">
					<h4 class="">사이트 이용정보 확인</h4>
					<a href="javascript:;" id="btnWithdrawal" class="btn02 btn_line"><span>회원탈퇴</span></a>
				</div>
				
				<form name="mainForm" method="post" action="/member/modify-personal-data.vc" onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
				<input type="hidden" name="mbrNickChk" value="Y">
				<input type="hidden" name="mbrPwEnc" value="">
				<input type="hidden" name="genderCd" value="${result.info.genderCd}">
				<input type="hidden" name="mbrBday" value="${result.info.mbrBday}">
				<input type="hidden" name="certMet" value="">
				<input type="hidden" name="ci" value="">
				<input type="hidden" name="di" value="">
					<div class="table-type01">
						<table>
							<colgroup>
								<col style="width: 200px;">
								<col>
							</colgroup>
							<tbody>
								<tr class="id dspflx">
									<th>닉네임 <span class="point_o">*</span></th>
									<td>
										<!-- Change 이벤트로  mbrNickChk 처리할것.-->
										<input type="text" id="mbrNick"  name="mbrNick" maxlength="8" oninput="maxLengthCheck(this)" title="닉네임" data-origin="${result.info.mbrNick}" value="${result.info.mbrNick}" placeholder="닉네임을 입력해주세요"/>
										<a href="javascript:;" id="btnCheckNick" class="btn02 btn_pp"><span>중복확인</span></a>
									</td>
								</tr>
								
								<c:if test="${result.info.snsCd eq 'HOME'}">
									<tr class="id">
										<th>아이디</th>
										<td><p>${result.info.mbrId}</p></td>
									</tr>
									<tr class="pw first">
										<th>비밀번호 <span class="point_o">*</span></th>
										<td>
											<input type="password" id="mbrPw" name="mbrPw" maxlength="20" title="비밀번호" />
											<p class="point_o recheck"><em>※</em><span> 비밀번호를 입력해주세요.</span></p>
										</td>
									</tr>
									<tr class="pw">
										<th>비밀번호 확인 <span class="point_o">*</span></th>
										<td>
											<input type="password" id="mbrPw2" name="mbrPw2" maxlength="20" title="비밀번호 확인" />
											<p class="point_o recheck"><em>※</em><span> 비밀번호 확인을 위해 비밀번호를 다시, 입력해주세요.</span></p>
										</td>
									</tr>
								</c:if>
							</tbody>
						</table>
					</div>	
	
					<h4 class="cont_tit02">개인정보 확인</h4>
					<div class="table-type01">
						<table>
							<colgroup>
								<col style="width: 200px;">
								<col>
							</colgroup>
							<tbody>
								
								<tr>
									<th>성명 <span class="point_o">*</span></th>
									<td><p>${result.info.mbrNm}</p></td>
								</tr>
								<tr>
									<th>전화번호</th>
									<td>
										<div class="phone">
											<input type="hidden" name="mbrTn" title="전화번호" value="${result.info.mbrTn}"/>
											<select name="mbrTn1" style="width:130px;" class="gray" title="전화번호">
												<option value="">선택</option>
											</select>
											<em>-</em><input type="text" pattern="\d*" name="mbrTn2" class="numOnly" maxlength="4" title="전화번호" oninput="maxLengthCheck(this)" />
											<em>-</em><input type="text" pattern="\d*" name="mbrTn3" class="numOnly" maxlength="4" title="전화번호" oninput="maxLengthCheck(this)" />
										</div>
									</td>
								</tr>
								<tr class="id dspflx">
									<th>휴대폰번호 <span class="point_o">*</span></th>
									<td>
										<input type="text" name="mbrHp" title="휴대폰번호" class="gray" readonly="readonly" value="${result.info.mbrHp}"/>
										<a href="javascript:;" id="btnCheckCert" class="btn02 btn_pp"><span>본인확인</span></a>
									</td>
								</tr>
								<tr>
									<th>이메일 <span class="point_o">*</span></th>
									<td> 
										<div class="mail">
											<input type="hidden" name="mbrEml" title="이메일" value="${result.info.mbrEml}">
											<input type="text" id="mbrEml1" name="mbrEml1" maxlength="30" title="이메일" class="gray" value="${fn:substringBefore(result.info.mbrEml, '@')}" oninput="maxLengthCheck(this)"/>
											<em>@</em>
											<input type="text" id="mbrEml2" name="mbrEml2" maxlength="30" title="이메일" class="gray" value="${fn:substringAfter(result.info.mbrEml, '@')}" oninput="maxLengthCheck(this)"/>
											<select name="domain"  class="gray">
					                           	<option value="" >직접입력</option>
						                       	<option value="naver.com" >naver.com</option>
						                       	<option value="daum.net" >daum.net</option>
						                       	<option value="hanmail.net" >hanmail.net</option>
						                       	<option value="gmail.com" >gmail.com</option>
					                       </select>
										</div>
									</td>
								</tr>
								<tr>
									<th>주소 <span class="point_o">*</span></th>
									<td>
										<div id="zipcodeWrap" style="display:none;position:fixed;overflow:hidden;z-index:1;-webkit-overflow-scrolling:touch;">
											<!-- iOS에서는 position:fixed 버그가 있음, 적용하는 사이트에 맞게 position:absolute 등을 이용하여 top,left값 조정 필요 -->
											<img src="//t1.daumcdn.net/postcode/resource/images/close.png" id="btnCloseLayer" style="cursor:pointer;position:absolute;right:-3px;top:-3px;z-index:1" onclick="closeDaumPostcode()" alt="닫기 버튼">
										</div>
										<div class="add">
											<div>
												<input type="text" id="zipCd" name="zipCd" maxlength="8" style="width:170px;" title="우편번호" placeholder="우편번호" value="${result.info.zipCd}" readonly="readonly"/>
												<a href="#" id="btnZipCode" class="btn02 btn_pp add_search"><span>우편번호검색</span></a>
											</div>
											<div>
												<input type="text" id="adrSbc1" name="adrSbc1" style="width:80%;" title="주소1" readonly="readonly" value="${result.info.adrSbc1}" />
											</div>
											<div>
												<input type="text" id="adrSbc2" name="adrSbc2" class="gray" maxlength="100" style="width:80%;" title="상세주소" value="${result.info.adrSbc2}" placeholder="상세주소를 입력해주세요"/>
											</div>
										</div>
									</td>
							</tbody>
						</table>
					</div>
	
					<h4 class="cont_tit02">개인 설정 확인</h4>
					<div class="table-type01">
						<table>
							<colgroup>
								<col style="width: 200px;">
							</colgroup>
							<tbody>
								<tr class="id agree_or">
									<th>수신동의여부 <span class="point_o">*</span></th>
									<td>
										<label class="input_checkbox" for="agreed">
											<input id="smsYn" name="smsYn" type="hidden" value="${result.info.smsYn}">
											<input id="emlYn" name="emlYn" type="hidden" value="${result.info.emlYn}">
											<input id="agreed" name="agreed" type="checkbox" checked="checked">
											<span>이메일 및 SMS 알림 수신동의</span>
										</label>
									</td>
								</tr>
							</tbody>
						</table>
					</div>	
	
					<div class="btn_wrap">
						<a href="#" class="btn btn_pp submit" style="width: 330px;"><span>수정</span></a>
						<a href="/index.vc" class="btn btn_line" style="width: 330px;"><span>취소</span></a>
					</div>
				</form>
			</div>
		</div>						
	</div>		
</div>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script type="text/javascript">
$(document).ready(function(){
    $('form[name=mainForm]').ready(function(){
    	$('input[name=agreed]').prop('checked', ${result.info.smsYn eq 'Y' and result.info.emlYn eq 'Y'}); 
    });
    
    $('select[name=domain]').on('change', function(e){
	    if($(this).val() != ''){
	        $('input[name=mbrEml2]').val($(this).val()).prop('readonly', true);
	    }else{
	        $('input[name=mbrEml2]').val('').prop('readonly', false);
	    }
	});
    
    $('select[name="mbrTn1"]').ready(function(){
        comCodeUtil.getCodeNPrint('COMC', 'mbrTn1', 'select');
        
        var mbrTn = $('input[name=mbrTn]').val().split('-');
        
        try{
	        if(mbrTn.length > 1){
		    	$('select[name=mbrTn1] option[value=' + mbrTn[0] +']').prop('selected', true);
		    	$('input[name=mbrTn2]').val(mbrTn[1]);
				$('input[name=mbrTn3]').val(mbrTn[2]);
	        }
        }catch(e){
        	//ignore
        }
    });
});

$(function () {
	$('a#btnCheckNick').on({
        'click' : function(event){
            event.preventDefault();  
            $('#mbrNick').blur();
            $(this).focus();
            formSubmitObj.submitCheckNick($(this));
        }
    });
	
	$('input[name=mbrNick]').bind("change keyup input", function(event){
       	document.forms.mainForm.mbrNickChk.value = 'N';
       	$('a#btnCheckNick').show();
    });
	
	$('a#btnZipCode, input[name=zipCd]').on({
        'click' : function(event){
            event.preventDefault();  
            execDaumPostcode();
        },
        'focus' : function(event){
            event.preventDefault();  
            execDaumPostcode();
        }
    });
	
	//본인확인
	$('a#btnCheckCert, input[name=mbrHp]').on({
        'click' : function(event){
            event.preventDefault();
            if(confirm('휴대폰 번호를 변경하시겠습니까?')){    
            	userCertObj.init();
            }
        }
    });
	
	$('a#btnWithdrawal').on({
        'click' : function(event){
            event.preventDefault();  
            formSubmitObj.submitWithdrawal($(this));
        }
    });
	
});

/**
 * 본인확인
 */
var userCertObj = {
    init : function(){
	    var certUrl = '/api/kmcis/init.vc';
    	windowUtil.open(certUrl, "KMCISWindow", 450, 570, 0);
    },
    result : function(bProcess, errorMsg, certYn, phoneCorp, name, gender, birthDay, phoneNo, ci, di){
    	if(bProcess){
    		if(certYn == 'N'){
    			alert('본인인증 실패');
    		}else if(certYn == 'F'){
    			alert('본인인증 오류');
    		}else{
    			console.log(name + ' : ' + gender + ' : ' + birthDay + ' : ' + phoneNo);
    			document.forms.mainForm.certMet.value = phoneCorp;
    			document.forms.mainForm.genderCd.value = gender;
    			document.forms.mainForm.mbrBday.value = birthDay;
    			document.forms.mainForm.mbrHp.value = phoneNo;
    			document.forms.mainForm.ci.value = ci;
    			document.forms.mainForm.di.value = di;
    		}
    	}else{
    		alert(errorMsg);
    	}
    }
};

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

            document.forms.mainForm.zipCd.value = data.zonecode;
            document.forms.mainForm.adrSbc1.value = addr;
            document.forms.mainForm.adrSbc2.focus();
            
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

var formSubmitObj = {
	submit : function(form){
			
		if(!submitUtil.isEmpty(form.mbrNick)){
            return false;
        }else{
       		if(form.mbrNickChk.value === 'N'){
	       		submitUtil.alertNfocus(form.mbrNick, '닉네임 중복확인');
	       		return false;
	       	}
        }
            
		<c:if test="${result.info.snsCd eq 'HOME'}">
			if(!submitUtil.isNull(form.mbrPw)){
		        if(!submitUtil.isEmpty(form.mbrPw2)){
		            return false;
		        }
		        
		        if(form.mbrPw.value == form.mbrPw2.value){
		            if(!submitUtil.isPassword(form.mbrPw, '비밀번호 형식 확인')){
		                form.mbrPw.focus();
		                return false;
		            }
		        }else{
		            alert('비번이 같이 않음.');
		            form.mbrPw2.focus();
		            return false;
		        }
		        
		        form.mbrPwEnc.value = Base64.encode(form.mbrPw.value);
			}
	    </c:if>
	        
        if(!(submitUtil.isNull(form.mbrTn1) && submitUtil.isNull(form.mbrTn2) && submitUtil.isNull(form.mbrTn3))){
        	 if(!submitUtil.isEmpty(form.mbrTn1)){
        		 return false;
        	 }else if(!submitUtil.isEmpty(form.mbrTn2)){
        		 return false;
        	 }else if(!submitUtil.isEmpty(form.mbrTn3)){
        		 return false;
        	 }else{
				 form.mbrTn.value = form.mbrTn1.value + '-' + form.mbrTn2.value + '-' + form.mbrTn3.value;
        	 }
        }
        
        if(submitUtil.isNull(form.mbrHp)){
       		submitUtil.alertNfocus(form.mbrHp, '휴대폰 본인인증이 필요합니다.');
       		return false;
        }
        
        if(!submitUtil.isEmpty(form.mbrEml1)){
       		return false;
        }else{
        	 if(!submitUtil.isEmpty(form.mbrEml2, '메일 도메인 등록')){
        		 return false;
        	 }else{
        		 form.mbrEml.value = form.mbrEml1.value + '@' + form.mbrEml2.value;
        		 
        		if(!submitUtil.isEmail(form.mbrEml, '메일 형식 확인')){
                  return false;
              	}
        	 }
        }
        
        if(!submitUtil.isEmpty(form.zipCd)){
            return false;
        }
           
        if(!submitUtil.isEmpty(form.adrSbc1)){
            return false;
        }
        
        if($("input[name=agreed]").is(":checked")){
        	form.smsYn.value = 'Y';
        	form.emlYn.value = 'Y';
        }else{
        	form.smsYn.value = 'N';
        	form.emlYn.value = 'N';
        }
        
        if(confirm('회원 정보를 수정하시겠습니까?')){    
        	ajaxUtil.postDisableAsync($(form).attr('action'), $(form).serialize(), formSubmitObj.result);
		}
          
        return false;
    },
	result : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        if(json.bindingStatus == undefined && json.result != undefined && json.result.status){
            if(json.result.code == 'PW_OK'){
            	alert('비밀번호가 수정되었습니다. 다시 로그인을 해주세요.');
        		location.replace('/member/login.vc');
        	}else if(json.result.code == 'PW_SAME'){
        		alert('기존 비밀번호와 같습니다. 새로운 비밀번호를 입력하여 주십시오.\n\n비밀번호 이외의 변경하신 회원정보는 적용되었습니다.');
        		location.reload();
        	}else{
        		alert('회원 정보가 수정되었습니다.');
        		location.reload();
        	}
        }else{
			ajaxUtil.error(json);	
        }
    },
    submitCheckNick : function(object){
   		if(!submitUtil.isEmpty(document.forms.mainForm.mbrNick)){
            return false;
        }else if(document.forms.mainForm.mbrNickChk.value == 'Y'){
        	//중복체크 할 필요 없음.
        }else{
        	var data = { mbrNick : document.forms.mainForm.mbrNick.value };
            ajaxUtil.postDisableAsync('/member/checkDuplicate.vc', data, formSubmitObj.resultCheckNick);
        }
    },
    resultCheckNick : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        if(json.result != undefined && json.result.status && json.result.check == 'Y'){
        	alert('사용 가능합니다. ');
        	document.forms.mainForm.mbrNickChk.value = 'Y';
        }else{
        	submitUtil.alertNfocus(document.forms.mainForm.mbrNick, '다른 닉네임을 입력해주세요');
        }
    },
    submitWithdrawal : function(object){
    	if(confirm('정말 회원에서 탈퇴 하시겠습니까?')){    
            ajaxUtil.postDisableAsync('/member/membership-withdrawal.vc', {}, formSubmitObj.resultWithdrawal);
        }
    },
    resultWithdrawal : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        if(json.result != undefined && json.result.status){
        	alert( '${result.info.mbrNm}님은 ' + dateUtil.getDate() +'에 회원에서 탈퇴 하셨습니다.');
        	
        	try{
        		//웹뷰 안드로이드 브릿지 호출
        		window.Virus_Clean.onSecession();
        	}catch(e){
        		//ignore
        	}
        	
        	location.replace('/index.vc');
        }else{
        	ajaxUtil.error(json);	
        }
    }
};

</script>