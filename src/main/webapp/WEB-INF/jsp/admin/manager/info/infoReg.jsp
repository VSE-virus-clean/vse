<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
    JSP Name : infoReg.jsp
    Description : 사이트 관리자 등록
--%>
<style>
	.showAuthU table tbody td label { padding-left:none; }
</style>

<div class="sec_top">
	<h3 class="sec_tit">운영자 등록</h3>
	<ul class="top_tab">
		<li><a href="#">운영자 관리</a></li>
	</ul>
</div>
<div class="sec_cont">
    <form name="mainForm" method="post"  onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
    <input type="hidden" name="mgrIdChk" value="N">
    <input type="hidden" name="mgrPwEnc" value="" />
    
		<h4 class="cont_tit">정보입력</h4>
		<div class="r_search_box">
    
	        <table class="r_search_table">
	            <colgroup>
	                <col width="170px" /><col width="*" /><col width="170px" /><col width="*" />
	            </colgroup>
	            <tr>    
	                <th>이름<span class="ess">*</span></th>
	                <td colspan="3">
	                    <input type="text" id="mgrNm" name="mgrNm" style="width:250px;" title="이름" />
	                </td>     
	            </tr>
	            <tr> 
	                <th>아이디<span class="ess">*</span></th>
	                <td colspan="3">
	                    <input type="text" id="mgrId" name="mgrId" style="width:250px;" title="아이디" />
	                    <a href="${contextPath}/${requestUri}/checkId.vc" id="btnCheckID" class="btn btn_bk">아이디 중복확인</a>
	                    <ul class="warn">
	                     	<li>- 아이디는 사내 이메일 정보입니다.</li>
	                     	<li>- 아이디는 중복확인을 반드시 해야 합니다.</li>
	                    </ul>
	                </td>
	            </tr>
	            <tr>    
	                <th>비밀번호<span class="ess">*</span></th>
	                <td colspan="3">
	                	<input type="password" name="mgrPw" maxlength="30" style="width:250px;" class="engMode noSpace" title="비밀번호" />
	                	<ul class="warn">
	                     	<li>- 비밀번호는 4자 이상의 영문 대/소문자, 숫자, 특수문자(!@#$%^&*-_+=)만 사용할 수 있습니다.</li>
	                    </ul>
	               	</td>  
	            </tr>
	            <tr>  	
	               	<th>비밀번호 확인<span class="ess">*</span></th>
	                <td colspan="3">
	                	<input type="password" name="mgrPwConf" maxlength="30" style="width:250px;" class="engMode noSpace" title="비밀번호 확인" />
	               		<ul class="warn">
	                     	<li>- 비밀번호는 4자 이상의 영문 대/소문자, 숫자, 특수문자(!@#$%^&*-_+=)만 사용할 수 있습니다.</li>
	                    </ul>
	               	</td>        
	            </tr>
	            <tr>   
	                <th>E-mail<span class="ess">*</span></th>
	                <td colspan="3">
	                    <input type="text" id="mgrEml" name="mgrEml" style="width:250px;" title="E-mail" />
	                </td>
	            </tr>
	            <tr>    
	                <th>부서</th>
	                <td colspan="3">
	                    <input type="text" id="mgrOpsNm" name="mgrOpsNm" style="width:250px;" title="부서" />
	                </td>
	            </tr>
	            <tr>
					<th>권한타입</th>
					<td colspan="3">
						<label class="radio_box" for="mgrAuthCdA"><input type="radio" name="mgrAuthCd" id="mgrAuthCdA" value="A" /><span>일반관리자</span></label>&nbsp;
	                   	<label class="radio_box" for="mgrAuthCdS"><input type="radio" name="mgrAuthCd" id="mgrAuthCdS" value="S" /><span>운영관리자</span></label>
					</td>
				</tr>
	            <tr> 
	                <th>계정상태</th>
	                <td colspan="3">
	                    <label class="radio_box" for="stCdY"><input type="radio" name="stCd" id="stCdY" value="Y" /><span>사용</span></label>&nbsp;
	                    <label class="radio_box" for="stCdN"><input type="radio" name="stCd" id="stCdN" value="N" /><span>사용중지</span></label>
	                </td> 
	            </tr>
	        </table>
        </div>
        
        <h4 class="cont_tit showAuthU" style="margin-top:50px;">메뉴권한설정</h4>
		<div class="r_search_box showAuthU">
	        <%@ include file="/WEB-INF/jsp/admin/manager/info/inc_menu.jsp" %>
        </div>
        <div class="r_search_box">
	        <div class="btn_center_gorup clearfix">
				<div class="left">
					<button type="button" class="btn btn_gray" onclick="location.href='${contextPath}/${requestUri}/list.vc'; ">목록</button>
				</div>
				<div class="right">
					<button type="submit" class="btn btn_red">등록</button>
				</div>
			</div>
		</div>
    </form>
</div>

<script type="text/javascript">
$(function(){
	 $('form[name=mainForm]').ready(function(){
		 $('input[name=stCd][value=Y]').prop('checked', true).trigger('click'); 
		 $('input[name=mgrAuthCd][value=A]').prop('checked', true).trigger('click'); 
		 
     });
	 
	 $('input[name=mgrAuthCd]').on('change', function(e){
    	 if($(this).val() == 'A'){
    		 $('.showAuthU').show();
    	 }else{
    		 $('.showAuthU').hide();
    	 }
     });
	 
     
    $('a#btnCheckID').on({
        'click' : function(event){
            event.preventDefault();  
            formSubmitObj.submitCheckID($(this));
        }
	});
	$('input[name=mgrId]').on({
        'keypress' : function(event){
        	document.forms.mainForm.mgrIdChk.value = 'N';
        }
    });
});

var formSubmitObj = {
    submit : function(form){
        
        if(!submitUtil.isEmpty(form.mgrNm)){
            return false;
        }
        
        if(!submitUtil.isEmpty(form.mgrId)){
            return false;
        }else{
        	if(form.mgrIdChk.value === 'N'){
        		submitUtil.alertNfocus(form.mgrId, '아이디 중복 체크를 해주세요.');
        		return false;
        	}
         }
        
        if(!submitUtil.isEmpty(form.mgrPw)){
            return false;
        }
        if(!submitUtil.isEmpty(form.mgrPwConf)){
            return false;
        }
        
        if(form.mgrPw.value == form.mgrPwConf.value){
            if(!submitUtil.isPassword(form.mgrPw)){
                form.mgrPw.focus();
                return false;
            }
        }else{
            alert('입력하신 비밀번호와 일치하지 않습니다.');
            form.mgrPwConf.focus();
            return false;
        } 
        
        if(!submitUtil.isEmpty(form.mgrEml)){
            return false;
        }else if(!submitUtil.isEmail(form.mgrEml)){
            return false;
        }
        
        form.mgrPwEnc.value = Base64.encode(form.mgrPw.value);
      
        ajaxUtil.postDisableAsync($(form).attr('action'), $(form).serialize(), formSubmitObj.result);
        
        return false;
    },
    result : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        if(json.bindingStatus == undefined && json.result != undefined && json.result.status){
           location.replace('${contextPath}/${requestUri}/list.vc');
        }else{
           ajaxUtil.error(json);
        }
    },
    /**
     * 중복:아이디
     */
    submitCheckID : function(object){
   		if(!submitUtil.isEmpty(document.forms.mainForm.mgrId, '아이디를 입력해 주세요.')){
               return false;
        }else{
           	if(submitUtil.isEmail(document.forms.mainForm.mgrId, '아이디는 이메일 형식입니다.')){
	            var data = { mgrId : document.forms.mainForm.mgrId.value };
	            ajaxUtil.postDisableAsync('${contextPath}/${requestUri}/checkIDDuplicate.vc', data, formSubmitObj.resultCheckID);
           	}
        }
    },
    resultCheckID : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        if(json.bindingStatus == undefined && json.result != undefined && json.result.status && json.result.check == 'Y'){
        	alert('사용 가능한 아이디입니다. ');
        	document.forms.mainForm.mgrIdChk.value = 'Y';
        }else{
        	submitUtil.alertNfocus(document.forms.mainForm.mgrId, '이미 사용중인 아이디입니다.');
        }
    }
};
</script>