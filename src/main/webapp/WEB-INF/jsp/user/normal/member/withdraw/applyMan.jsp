<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%-- 
    JSP Name : applyMan.jsp
    Description : 회원 > 탈퇴신청
--%>
<style>
#divContent { margin:auto; width:1000px; text-align:center; }
.page-message { margin:auto; font-size:3.5em; font-weight:300; line-height:80px; text-align:center; padding-bottom:50px;} 
.sub-message { margin:auto; font-size:1.6em; font-weight:400; line-height:50px; text-align:left; padding:60px 30px 60px;} 

#loginBox > div > span { display:inline-block; height:65px; padding-left:35px; width:90px; }
#loginBox > div > input { width:310px;}
#loginBox > div:nth-child(1), #loginBox > div:nth-child(2) { margin:auto; width:458px; height:65px; line-height:65px; border:1px solid #DDD; padding:0 20px; text-align:left; }
#loginBox > div:nth-child(2) { margin-top:-1px; }
#loginBox > div:nth-child(1) > span{ background:url('/resources/user/images/bg_userid.gif') 0 50% no-repeat; }
#loginBox > div:nth-child(2) > span{ background:url('/resources/user/images/bg_password.gif') 0 50% no-repeat; }

</style>

<div id="container">
	<div id="pageContents">
		<form name="mainForm" method="post" onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
		<input type="hidden" name="mbrPwEnc" value="">
			<div id="divContent">
				<div class="sub-message center">
					退会の手続きを続けたい方は下記のIDとPWを入力の後退会確認ボタンを押して下さい。<br/><br/>
					退会確認ボタンを押した後は退会のキャンセルはできません。
				</div>
				<div id="loginBox">
					<div>
						<span>会員ID</span>
						<input type="text" name="mbrId" class="idput engMode noSpace"  title="会員ID" minlength="2" />
					</div>
					<div>
						<span>パスワード</span>
						<input type="password" name="mbrPw" class="pwdput engMode noSpace" title="パスワード" minlength="2"  value="" autocomplete="off" />
					</div>
				</div>
				<div class="btnarea mt50 mb50" style="text-align:center;">
					<a href="#" class="btnbx2 bdark submit">退会確認</a>
					<a href="/member/myInfo/changeInfo" class="btnbx2 bdefault ml5">キャンセル</a>
				</div>
			</div>
		</form>
	</div>
</div>

<script type="text/javascript">

var formSubmitObj = {
	form : new Object(),
    submit : function(form){
    	if(!submitUtil.isEmpty(form.mbrId)){
	    	return false;
		}
		
       	if(!submitUtil.isEmpty(form.mbrPw)){
            return false;
        } else {
       		form.mbrPwEnc.value = Base64.encode(form.mbrPw.value);
       	}
       	
        if(confirm('退会確認ボタンを押した後は退会のキャンセルはできません。')){
			ajaxUtil.postDisableAsync($(form).attr('action'), $(form).serialize(), formSubmitObj.result);	
        }
        
        return false;
    },
    result : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        if(json.bindingStatus == undefined && json.result != undefined && json.result.status ){
        	location.replace("/member/myInfo/withdraw/complete");
        }else{
           ajaxUtil.error(json);
        }
    }
};
</script>