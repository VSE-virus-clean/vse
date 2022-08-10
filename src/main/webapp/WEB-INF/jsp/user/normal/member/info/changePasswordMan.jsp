<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%-- 
    JSP Name : changePasswordMan.jsp
    Description : 회원 > 개인정보변경 > 비밀번호학인
    author Jeong.hyoungjea
    since 2017.02.01.
    version 1.0
    Modification Information
       since          author              description
    ===========    =============    ===========================
    2017.02.01.   Jeong.hyoungjea     최초 생성
--%>
<style>
#contents > div { text-align:left; }
p{max-height:100%;display:block;font-size:14px;margin-bottom:18px;line-height:1.8em;}
p.t{margin-top:5px; margin-bottom:0px;height:23px;line-height:1;font-weight:bold;}

#requestBox {border-bottom:1px solid #E5000F;padding:30px 0px; margin-bottom:20px;overflow:hidden;}
#requestBox table.tbl { margin:40px auto;}
#requestBox table.tbl td input{height:20px;line-height:20px;}
#requestBox table.tbl td .btn_submit{ border:0px;height:37px;line-height:40px; margin:auto auto auto 20px; padding:0px 20px; color:#fff;}
#requestBox table.tbl th label { font-weight:bold; }
#requestBox table.tbl td label { margin-right:10px;}
#requestBox table.tbl td span.desc { display:block; font-size:-1px; }
#requestBox table.tbl td span.text { margin:auto 2px; }
.disabled, .readonly { background-color:#F3E6E6;}
#ui-datepicker-div { width:300px; }
img.ui-datepicker-trigger { cursor: pointer;width: 22px;vertical-align: middle; margin-left:10px; }
</style>

<div id="container">
		<div id="pageContents">
			<h3>パスワード変更</h3>
			<p>
				新しいパスワードとパスワード確認を入力して下さい。
			</p>
			
			<form name="mainForm" method="post" onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
				<input type="hidden" name="mbrPwEnc" value="">
				
				<div class="tblbx">
					<table class="tbl_reg">
						<colgroup><col width="350px"><col width="*"></colgroup>
						<tbody>
							<tr>
								<th>&nbsp;&nbsp;&nbsp;&nbsp; 会員ID</th>
								<td>${result.info.mbrId}</td>
							</tr>
							<tr>
								<th>&nbsp;&nbsp;&nbsp;&nbsp; ハンドルネーム</th>
								<td>${result.info.mbrNick}</td>
							</tr>
							<tr>
								<th><em class="red">※</em> <label for="mbrPw">パスワード</label> </th>
								<td>
									<input type="password" id="mbrPw" name="mbrPw" maxlength="10" style="width:345px;" title="パスワード " />
									<span class="desc mt10">※ 半角英数字 4~10文字。</span>
								</td>
							</tr>
							<tr>
								<th><em class="red">※</em> <label for="mbrPw2">パスワード確認 </label> </th>
								<td>
									<input type="password" id="mbrPw2" name="mbrPw2" maxlength="10" style="width:345px;" title="パスワード確認" />
									<span class="desc mt10">※ 半角英数字 4~10文字。</span>
								</td>
							</tr>
						</tbody>
					</table>
				</div>
				
				<div class="btnarea btn_center">
					<a href="#" class="btnbx2 bblue submit">変更する</a>
					<a href="/index" class="btnbx2 bdark">CANCEL</a>
				</div>
			</form>
		</div>
</div>
<script type="text/javascript">
var formSubmitObj = {
	submit : function(form){
            
		if(!submitUtil.isEmpty(form.mbrPw)){
            return false;
        }
        if(!submitUtil.isEmpty(form.mbrPw2)){
            return false;
        }
        if(form.mbrPw.value == form.mbrPw2.value){
        	if(!submitUtil.isPassword(form.mbrPw, 'パスワードは4文字以上10文字以下、半角英数字で入力してください。')){
            	form.reset();
                form.mbrPw.focus();
                return false;
            }
        }else{
	        alert('パスワードが一致しません。');
	        form.mbrPw2.focus();
	        return false;
        } 
          
        if(confirm('変更しますか？')){
        	form.mbrPwEnc.value = Base64.encode(form.mbrPw.value);
        	ajaxUtil.postDisableAsync($(form).attr('action'), $(form).serialize(), formSubmitObj.result);
		}
          
        return false;
    },
	result : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        if(json.bindingStatus == undefined && json.result != undefined && json.result.status){
        	alert('変更されました。');
        	location.replace(json.result.returnUrl);
        	//location.replace('/index');
        }else{
			ajaxUtil.error(json);	
        }
    }
};
</script>