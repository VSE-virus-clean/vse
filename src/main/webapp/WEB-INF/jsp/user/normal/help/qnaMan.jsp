<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%-- 
    JSP Name : qnaMan.jsp
    Description : QNA 입력
    author Jeong.hyoungjea
    since 2017.02.01.
    version 1.0
    Modification Information
       since          author              description
    ===========    =============    ===========================
    2017.02.01.   Jeong.hyoungjea     최초 생성
--%>
<style>
.agreebx {border-bottom:1px solid #E5000F;padding:30px 0px; margin-bottom:20px;overflow:hidden;}
</style>

<div id="container">
	<div id="pageContents">
		<div>
			下記のお問合せフォームより、お問合せ内容を出来るだけ詳細にご入力の上、お問合せください。<br/>
			ご返信は営業日から約７日以内に順次返信させて頂きます。<br/>
			返信にお時間がかかる場合がございますこと、あらかじめご了承ください。 
		</div>
		<div>
			※７日経ってもメールが届かない場合は、<br/>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;メールアドレスを誤ってご入力された等の原因が考えられますので、メールアドレスをよくお確かめの上、再度ご質問を送信して頂くか、<br/>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;お急ぎの場合はお電話にてお問合せ頂きます様お願いいたします。
		</div>
		<div>
			※注意事項
			<p>
				下記のメールアドレスを入力する欄にはできるだけPC用のメールアドレスを使用してください。<br/>
				各通信キャリアのメールアドレスでは文字化け、スパムメールとして処理される場合がございます。<br/>
				そのため「会員登録認証メール」が届かない場合がございます。<br/>
				なお、PCの設定により「会員登録認証メール」が送られても迷惑メールとして届かなかったりする場合がございます。<br/>
				現在使っているメールソフトで当サイトからの「 <a href="mailto:kjh@emotionj.com">kjh@emotionj.com</a> 」が受信できるように設定してください。 
			</p>
		</div>
		<div>
			※ファンクラブ会員の方は必ず会員番号をご入力ください。<br/>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;ご入力がない場合はご対応しかねる場合がございますのでご了承下さい。
		</div>
		<div>
			※携帯メールからお問い合わせをされるお客様は携帯電話の方は  <a href="mailto:kjh@emotionj.com">kjh@emotionj.com</a> からのメールを、受信できるように設定してください。 
		</div>
		
		<a href="/role/privacyPolicy" class="btnbx2 bblue" target="_blank">Privacy Policyはこちら</a>
		
		<form name="mainForm" method="post" onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
			<input type="hidden" name="rgstEml" value="">
			<div class="tblbx">
				<table class="tbl_reg">
					<colgroup><col width="200px"><col width="*"></colgroup>
					<tbody>
						<c:choose>
						<c:when test="${not empty sessionScope.sessionVO}">
							<tr>
								<th><em class="red">※</em> <label for="mbrNick">お名前</label> </th>
								<td>
									<input type="text" id="mbrNick" name="mbrNick" maxlength="50" style="width:150px; margin-right:20px;" title="お名前" value="${sessionScope.sessionVO.name}" />
								</td>
							</tr>
							<tr>
								<th><em class="red">※</em> <label for="rgstEml1"> メールアドレス</label></th>
								<td>
									<input type="text" name="rgstEml1" maxlength="100" style="width:300px;" title="メールアドレス" value="${fn:substringBefore(sessionScope.sessionVO.email, '@')}">
									<span class="text">@</span>
									<input type="text" name="rgstEml2" maxlength="100" style="width:200px;" title="メールアドレスの＠以降" value="${fn:substringAfter(sessionScope.sessionVO.email, '@')}">
								</td>
							</tr>
						</c:when>
						<c:otherwise>
							<tr>
								<th><em class="red">※</em> <label for="mbrNick">お名前</label> </th>
								<td>
									<input type="text" id="mbrNick" name="mbrNick" maxlength="50" style="width:150px; margin-right:20px;" title="お名前" value="" />
								</td>
							</tr>
							<tr>
								<th><em class="red">※</em> <label for="rgstEml1"> メールアドレス</label></th>
								<td>
									<input type="text" name="rgstEml1" maxlength="100" style="width:300px;" title="メールアドレス" value="">
									<span class="text">@</span>
									<input type="text" name="rgstEml2" maxlength="100" style="width:200px;" title="メールアドレスの＠以降" value="">
								</td>
							</tr>
						</c:otherwise>
						</c:choose>
						<tr>
							<th><em class="red">※</em> <label for="blcTitl"> お問合せタイトル </label></th>
							<td>
								<input type="text" name="blcTitl" id="blcTitl" style="width:95%;" title="お問合せタイトル">
							</td>
						</tr>
						<tr>
							<th><em class="red">※</em> <label for="blcSbc1"> お問合せ内容 </label></th>
							<td>
								<textarea name="blcSbc1" id="blcSbc1" style="width:95%;height:300px;" title="お問合せ内容"></textarea>
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			
			<div class="btnarea btn_center">
				<a href="#" class="btnbx2 bdark submit">確認する</a>
				<a href="/index" class="btnbx2 bdefault">CANCEL</a>
			</div>
		</form>
	</div>
</div>

<script type="text/javascript">
var formSubmitObj = {
	submit : function(form){
        if(!submitUtil.isEmpty(form.mbrNick)){
            return false;
        }
        
        if(!submitUtil.isEmpty(form.rgstEml1, 'メールアドレスを確認してください。')){
       		return false;
        }else if(!submitUtil.isEmpty(form.rgstEml2, 'メールアドレスを確認してください。')){
      		return false;
      	}else{
      		form.rgstEml.value = form.rgstEml1.value + '@' + form.rgstEml2.value;
        		 
      		if(!submitUtil.isEmail(form.rgstEml, '正しい メールアドレスを確認してください。')){
             	return false;
            }
        }
        
        if(!submitUtil.isEmpty(form.blcTitl)){
            return false;
        }
        
        if(!submitUtil.isEmpty(form.blcSbc1)){
            return false;
        }
           
		ajaxUtil.postDisableAsync($(form).attr('action'), $(form).serialize(), formSubmitObj.result);
        
        return false;
	},
	result : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        if(json.bindingStatus == undefined && json.result != undefined  && json.result.status){
            alert('お問い合わせは受け付けています。迅速に回答いたします。');
        	location.replace('/index');
        }else{
            ajaxUtil.error(json);
        }
    }
};
</script>