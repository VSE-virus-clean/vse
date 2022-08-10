<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
    JSP Name : uaMod.jsp
    Description : 개인정보수정 폼
--%>


<div class="sec_top">
	<h3 class="sec_tit">비밀번호 수정</h3>
</div>

<div class="sec_cont">
	<div class="r_search_box">
	    <form name="mainForm" method="post"  onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
	        <input type="hidden" name="mgrPwEnc" value="" />
	        <input type="hidden" name="mgrNewPwEnc" value="" />
	        <table class="r_search_table">
	            <colgroup>
	                <col width="190px" /><col width="*" />
	            </colgroup>
	            <tr>    
	                <th>아이디</th>
	                <td>${sessionScope.sessionVO.id}</td> 
	            </tr>
	            <tr>    
	                <th>이름</th>
	                <td>${sessionScope.sessionVO.name}</td>    
	            </tr>
	            <tr>    
	                <th>현재 비밀번호</th>
	                <td>
	                    <input type="password" name="mgrPw" class="noSpace engMode" style="width:270px;" maxlength="30" minlength="2" title="현재 비밀번호" />
	                </td>   
	            </tr>
	            <tr>    
	                <th>새로운 비밀번호</th>
	                <td>
	                    <input type="password" name="mgrNewPw" class="noSpace engMode" style="width:270px;" maxlength="20" minlength="8" title="새로운 비밀번호" />
	                </td>   
	            </tr>
	            <tr>    
	                <th>새로운 비밀번호 확인</th>
	                <td>
	                    <input type="password" name="mgrNewPw2" class="noSpace engMode" style="width:270px;" maxlength="20" minlength="8" title="새로운 비밀번호 확인" />
	                </td>   
	            </tr>
	        </table>
	        <div class="btn_center_gorup clearfix">
				<div class="right">
					<button type="submit" class="btn btn_red submit">수정</button>
<!--                 	<button type="button" class="btn btn_gray reset">취소</button> -->
				</div>
			</div>
	    </form>
    </div>
</div>
                

<script type="text/javascript">
var formSubmitObj = {
    submit : function(form){
        if(!submitUtil.isEmpty(form.mgrPw)){
            return false;
        }
        
        if(!submitUtil.isEmpty(form.mgrNewPw)){
            return false;
        }
        
        //비밀번호 패턴확인
        if(!submitUtil.isPassword(form.mgrNewPw, '새로운 비밀번호')){
            form.mgrNewPw.value = '';
            form.mgrNewPw.focus();
            return false;
        }
        
        if(!submitUtil.isEmpty(form.mgrNewPw2)){
            return false;
        }
        
        //비밀번호 패턴확인
        if(!submitUtil.isPassword(form.mgrNewPw2, '새로운 비밀번호 확인')){
            form.mgrNewPw2.value = '';
            form.mgrNewPw2.focus();
            return false;
        }
        
        if($(form.mgrNewPw).val() != $(form.mgrNewPw2).val()){
        	alert('입력한 비밀번호가 일치하지 않습니다. 확인해주세요.');
        	form.mgrNewPw.value = '';
        	form.mgrNewPw2.value = '';
            form.mgrNewPw.focus();
            return false;
        }
        
        if(confirm('<spring:message code="msg.C01" />')){
            form.mgrPwEnc.value = Base64.encode(form.mgrPw.value);
            form.mgrNewPwEnc.value = Base64.encode(form.mgrNewPw.value);
            ajaxUtil.postDisableAsync($(form).attr('action'), $(form).serialize(), formSubmitObj.result);
        }
        
        return false;
    },
    result : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        if(json.bindingStatus == undefined && json.result != undefined && json.result.status){
           location.replace('${contextPath}/logoutMan.vc');
        }else{
           ajaxUtil.error(json);
        }
    }
};
</script>