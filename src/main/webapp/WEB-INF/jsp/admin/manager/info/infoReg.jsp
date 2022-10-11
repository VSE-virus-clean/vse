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
	<h3 class="sec_tit">Manager Registration</h3>
	<!-- 
	<ul class="top_tab">
		<li><a href="#">운영자 관리</a></li>
	</ul>
	-->
</div>
<div class="sec_cont">
    <form name="mainForm" method="post"  onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
    <input type="hidden" name="mgrIdChk" value="N">
    <input type="hidden" name="mgrPwEnc" value="" />
    
<!-- 	<h4 class="cont_tit">정보입력</h4> -->
		<div class="r_search_box">
    
	        <table class="r_search_table">
	            <colgroup>
	                <col width="170px" /><col width="*" /><col width="170px" /><col width="*" />
	            </colgroup>
	            <tr>    
	                <th>Name<span class="ess">*</span></th>
	                <td>
	                    <input type="text" id="mgrNm" name="mgrNm" style="width:250px;" title="name" />
	                </td>     
	            </tr>
	            <tr> 
	                <th>ID<span class="ess">*</span></th>
	                <td>
	                    <input type="text" id="mgrId" name="mgrId" style="width:250px;" title="id" />
	                    <a href="${contextPath}/${requestUri}/checkId.vc" id="btnCheckID" class="btn btn_bk">ID Duplicate Verification</a>
	                    <ul class="warn">
	                     	<li>- The ID is your company email information.</li>
	                     	<li>- ID must be verified for duplication.</li>
	                    </ul>
	                </td>
	            </tr>
	            <tr>    
	                <th>Password<span class="ess">*</span></th>
	                <td colspan="3">
	                	<input type="password" name="mgrPw" maxlength="30" style="width:250px;" class="engMode noSpace" title="password" />
	                	<ul class="warn">
	                     	<li>- Passwords can only contain at least 4 alphanumeric characters, numbers, and special characters(!@#$%^&*-_+=).</li>
	                    </ul>
	               	</td>  
	            </tr>
	            <tr>  	
	               	<th>Confirm password<span class="ess">*</span></th>
	                <td>
	                	<input type="password" name="mgrPwConf" maxlength="30" style="width:250px;" class="engMode noSpace" title="confirm password" />
	               		<ul class="warn">
	                     	<li>- Passwords can only contain at least 4 alphanumeric characters, numbers, and special characters(!@#$%^&*-_+=).</li>
	                    </ul>
	               	</td>        
	            </tr>
	            <tr>   
	                <th>E-mail<span class="ess">*</span></th>
	                <td>
	                    <input type="text" id="mgrEml" name="mgrEml" style="width:250px;" title="E-mail" />
	                </td>
	            </tr>
	            <tr>    
	                <th>Department</th>
	                <td>
	                    <input type="text" id="mgrOpsNm" name="mgrOpsNm" style="width:250px;" title="department" />
	                </td>
	            </tr>
	            <tr>
					<th>Permission type</th>
					<td>
						<label class="radio_box" for="mgrAuthCdA"><input type="radio" name="mgrAuthCd" id="mgrAuthCdA" value="A" /><span>General Manager</span></label>&nbsp;
	                   	<label class="radio_box" for="mgrAuthCdS"><input type="radio" name="mgrAuthCd" id="mgrAuthCdS" value="S" /><span>Operations manager</span></label>
					</td>
				</tr>
	            <tr> 
	                <th>Usage status</th>
	                <td>
	                    <label class="radio_box" for="stCdY"><input type="radio" name="stCd" id="stCdY" value="Y" /><span>Y</span></label>&nbsp;
	                    <label class="radio_box" for="stCdN"><input type="radio" name="stCd" id="stCdN" value="N" /><span>N</span></label>
	                </td> 
	            </tr>
	        </table>
        </div>
        
<!--         <h4 class="cont_tit showAuthU" style="margin-top:50px;">메뉴권한설정</h4> -->
		<div class="r_search_box showAuthU" style="display:none">
	        <%@ include file="/WEB-INF/jsp/admin/manager/info/inc_menu.jsp" %>
        </div>
        <div class="r_search_box">
	        <div class="btn_center_gorup clearfix">
				<div class="left">
					<button type="button" class="btn btn_gray" onclick="location.href='${contextPath}/${requestUri}/list.vc'; ">List</button>
				</div>
				<div class="right">
					<button type="submit" class="btn btn_red">Save</button>
				</div>
			</div>
		</div>
    </form>
</div>

<script type="text/javascript">

$(document).ready(function() {
	$('#J').prop('checked', true);
	eventChecked($('#J'));
});

$(function(){
	 $('form[name=mainForm]').ready(function(){
		 $('input[name=stCd][value=Y]').prop('checked', true).trigger('click'); 
		 $('input[name=mgrAuthCd][value=A]').prop('checked', true).trigger('click'); 
		 
     });
	 
	 /* 
	 $('input[name=mgrAuthCd]').on('change', function(e){
    	 if($(this).val() == 'A'){
    		 $('.showAuthU').show();
    	 }else{
    		 $('.showAuthU').hide();
    	 }
     });
	 */
     
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
        		submitUtil.alertNfocus(form.mgrId, 'Please check the duplicate ID.');
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
            alert('The password you entered does not match.');
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
   		if(!submitUtil.isEmpty(document.forms.mainForm.mgrId, 'Please enter your ID.')){
               return false;
        }else{
           	if(submitUtil.isEmail(document.forms.mainForm.mgrId, 'The ID is in email format.')){
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
        	alert('The available ID.');
        	document.forms.mainForm.mgrIdChk.value = 'Y';
        }else{
        	submitUtil.alertNfocus(document.forms.mainForm.mgrId, 'The ID is already in use.');
        }
    }
};
</script>