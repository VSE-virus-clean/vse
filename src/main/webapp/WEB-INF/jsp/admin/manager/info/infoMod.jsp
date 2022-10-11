<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
    JSP Name : infoMod.jsp
    Description : 사이트 관리자 수정 / 상세보기
--%>
<style>
.showAuthU { display:none; }
.showAuthU table tbody td label { padding-left:none; }
</style>

<div class="sec_top">
	<h3 class="sec_tit">Admin Modify</h3>
	<!-- 
	<ul class="top_tab">
		<li><a href="#">운영자 관리</a></li>
	</ul>
	-->
</div>
<div class="sec_cont">
    <form name="mainForm" method="post"  onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
    <input type="hidden" name="mgrId" value="${result.info.mgrId}" />
    <input type="hidden" name="mgrSn" value="${result.info.mgrSn}" />
    
<!-- 	<h4 class="cont_tit">정보입력</h4> -->
		<div class="r_search_box">
	        <table class="r_search_table">
	            <%-- 
	            <colgroup>
	                <col width="170px" /><col width="*" /><col width="170px" /><col width="*" />
	            </colgroup>
	            --%>
	            <tr>    
	                <th>Name</th>
	                <td>
	                	<input type="text" id="mgrNm" name="mgrNm" style="width:250px;" title="name" value="${result.info.mgrNm}" />
	               	</td>   
	           	</tr>
	            <tr>    
	                <th>Id</th>
	                <td colspan="3">${result.info.mgrId}</td>
	            </tr>
	            <tr>    
	                <th>Password</th>
	                <td colspan="3">
	                    <a href="${contextPath}/${requestUri}/resetPassword.vc" class="btn btn_gray btn_resetPw">Password initialization</a> <span id="resetPw" class="hidden">Changed password : <em></em></span>
	                </td>   
	            </tr>
	            <tr>   
	                <th>E-mail<span class="ess">*</span></th>
	                <td>
	                    <input type="text" id="mgrEml" name="mgrEml" style="width:250px;" title="E-mail" value="${result.info.mgrEml}" />
	                </td>
	            </tr>
	            <tr>    
	                <th>Department</th>
	                <td>
	                    <input type="text" id="mgrOpsNm" name="mgrOpsNm" style="width:250px;" title="department" value="${result.info.mgrOpsNm}"  />
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
<!--         <h4 class="cont_tit showAuthU">메뉴권한설정</h4> -->
		<div class="r_search_box showAuthU" style="display:none">
			<%@ include file="/WEB-INF/jsp/admin/manager/info/inc_menu.jsp" %>
	    </div>
        <div class="r_search_box">    
	        <div class="btn_center_gorup clearfix">
				<div class="left">
					<button type="button" class="btn btn_gray" onclick="location.href='${contextPath}/${requestUri}/list.vc?${function:searchQuery(result.searchInfo)}'; ">List</button>
				</div>
				<div class="right">
					<button type="button" class="btn btn_gray btn_adminDel">Delete</button>
					<button type="submit" class="btn btn_red">Save</button>
				</div>
			</div>
		</div>
    </form>
</div>


<script type="text/javascript">
/* 
$(document).ready(function(){
	<c:if test="${result.info.mgrAuthCd eq 'A'}">
		<c:forTokens var="menu" items="${result.info.menuCd}" delims="|">
			$("input[name=menuCds][id=${menu}]").prop("checked", true);
		</c:forTokens>
	
		 $('.showAuthU').show();
	</c:if>
});
*/

$(function(){
    $('form[name=mainForm]').ready(function(){
        $('input[name=stCd][value=${result.info.stCd}]').prop('checked', true).trigger('click'); 
        $('input[name=mgrAuthCd][value=${result.info.mgrAuthCd}]').prop('checked', true).trigger('click'); 
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
    
    /**
     * 비밀번호 초기화
     */
    $('.btn_resetPw').on({
        click : function(event){
            event.preventDefault();
            formSubmitObj.submitResetPw($(this));
        } 
    });
    
    $('.btn_adminDel').on({
        click : function(event){
            event.preventDefault();
            formSubmitObj.submitDel($(this));
        } 
    });
    
});

var formSubmitObj = {
    submitDel : function(object){
        if(confirm('<spring:message code="msg.C02" />')){
            var data = { mgrSn : '${result.info.mgrSn}' };
            ajaxUtil.postDisableAsync('${contextPath}/${requestUri}/delete.vc', data, formSubmitObj.resultDel);
        } 
    },
    resultDel : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        if(json.bindingStatus == undefined && json.result != undefined && json.result.status){
           location.replace('${contextPath}/${requestUri}/list.vc?${function:searchQuery(result.searchInfo)}');
        }else{
           ajaxUtil.error(json);     
        }
    },
    submitResetPw : function(object){
        if(confirm('비밀번호를 초기화 하시겠습니까?')){
            var data = { mgrSn : '${result.info.mgrSn}' };
            ajaxUtil.postDisableAsync($(object).attr('href'), data, formSubmitObj.resultResetPw);
        } 
    },
    resultResetPw : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        if(json.bindingStatus == undefined && json.result != undefined && json.result.status){
        	$('#resetPw').show().find('em').html(json.result.pwd);
        }else{
            ajaxUtil.error(json);     
        }
    },
    submit : function(form){
    	if(!submitUtil.isEmpty(form.mgrNm)){
            return false;
        }
    	
    	if(!submitUtil.isEmpty(form.mgrEml)){
            return false;
        }else if(!submitUtil.isEmail(form.mgrEml)){
            return false;
        }
        
        if(confirm('정보를 수정하시겠습니까?')){
            ajaxUtil.postDisableAsync($(form).attr('action'), $(form).serialize(), formSubmitObj.result);
        }
        
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
};
    
</script>