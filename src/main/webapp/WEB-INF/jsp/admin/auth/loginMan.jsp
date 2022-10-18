<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
    JSP Name : loginMan.jsp
    Description : 관리자 로그인 화면
--%>
<link rel="stylesheet" href="/resources/admin/ui_common/css/firstpage.css?v=${cacheParam}">
<body>
	<div class="top_bar"></div>	
	<div class="inner">
		<a href="#" class="ad_logo" style="display:block; text-align:center;">
			<img src="/resources/admin/images/common/ad_logo.png" alt="" style="width:450px">
		</a>
		<div class="page_box">
			<form name="mainForm" method="post" action="${contextPath}/loginMan.vc" onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
	        <input type="hidden" name="mgrPwEnc" value="" /> 
	        <input type="hidden" name="returnUrl" value="${param.returnUrl}" />
				<input type="text" name="mgrId" id="mgrId" class="idput engMode noSpace" style="width:300px;" maxlength="20" minlength="2" title="ID"  value="" placeholder="ID"/>
				<input type="password" class="submit" name="mgrPw" id="mgrPw" class="pwdput engMode noSpace" style="width:300px;" maxlength="30" minlength="2" title="pwssword" value="" autocomplete="off" placeholder="password" />
				<div class="btn_center_gorup">
					<button type="submit" class="btn btn_red">Sign in</button>
				</div>
			</form>
		</div>
	</div>

	<iframe id="ifr" noresize="noresize" scrolling="no" frameborder="0" scrolling="auto" marginwidth="0" marginheight="0" title="기능상 필요한 내용 없는 빈 프레임" ></iframe>
	<iframe id="ifr2" noresize="noresize" scrolling="no" frameborder="0" scrolling="auto" marginwidth="0" marginheight="0" title="기능상 필요한 내용 없는 빈 프레임" ></iframe>
	<div id="mask"></div>
	<div id="mask2"></div>
	<div id="loading-wrap"><img src="/resources/admin/images/common/loading.gif" alt="" /></div>
</body>

<script type="text/javascript">
$(document).ready(function(){
    /*
     * ESC선택시 모달 닫기
     */
//     $(document).keydown(function(event){
//         if(event.keyCode == 27){
//             modalUtil.close();
//         }
//     });
});

// var modalUtil = {
//     open : function(){
//         modalComUtil.open();
//     },    
//     close : function(object){
//         $('form[name^=sub]')[0].reset();
//         modalComUtil.close();
//     }
// };
    
var formSubmitObj = {
    submit : function(form){
        
        if(!submitUtil.isEmpty(form.mgrId)){
            return false;
        }
        
        if(!submitUtil.isEmpty(form.mgrPw)){
            return false;
        }else{
            form.mgrPwEnc.value = Base64.encode(form.mgrPw.value);
        } 

        ajaxUtil.postDisableAsync($(form).attr('action'), $(form).serialize(), formSubmitObj.result);
        
        return false;
    },
    result : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        if(json.bindingStatus == undefined && json.result != undefined && json.result.code == 'LOGIN'){
            if(json.result.returnUrl == ''){
                location.replace('${contextPath}' + json.result.firstMenuUrl);
            }else{
                location.replace(json.result.returnUrl);
            }
        }else{
           ajaxUtil.error(json);     
           $('form[name=mainForm] input[type=password]').eq(0).val('').focus();
           
           //비밀번호 수정 팝업
           if(json.result.code == 'A04' || json.result.code == 'A77'){
               location.reload(true);
           }
        }
    }   
};
</script>