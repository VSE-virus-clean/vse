<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%-- 
    JSP Name : applyGoldMan.jsp
    Description : 회원가입 > 신청완료
    author Jeong.hyoungjea
    since 2017.02.01.
    version 1.0
    Modification Information
       since          author              description
    ===========    =============    ===========================
    2017.02.01.   Jeong.hyoungjea     최초 생성
--%>
<style>
#divComplete > div, #divSilverComplete > div {  margin:auto; width:700px; text-align:center; }
.page-message { margin:auto; font-size:3.5em; font-weight:300; line-height:80px; text-align:center; padding-bottom:50px;} 
.sub-message { margin:auto; font-size:1.3em; font-weight:500; line-height:40px; text-align:left; padding:0 30px 30px;} 
.gold-message { margin:auto auto 30px auto; background-color:#F7F7F7; padding:30px 40px; }
.gold-message h3 { font-size:1.4em; font-weight:700; color:#5180B0;}
.gold-message li { text-align:left; }
</style>

<div id="container">
	<div id="pageContents">
		<div id="divComplete" class="center">
			<div>
				<div class="gold-message">
					<h3>GOLD会員になると以下の会員特典がもらえます。</h3>
					<ul>
						<li>(1) GOLD会員限定ウェブコンテンツ</li>
						<li>(2) チケットの先行抽選予約</li>
						<li>(3) ファンクラブ限定イベントの参加申込権</li>
						<li>(4) ファンクラブオリジナルグッズの通信販売購入権</li>
						<li>(5) メールマガジンの配信（不定期）</li>
					</ul>
				</div>	
				<c:choose>
					<c:when test="${sessionScope.sessionVO.authLevel eq 'S'}">
						<div class="sub-message">
							GOLD会員の会員費は 年会費5,400円(税込)です。<br/>
							GOLD会員としての入会をご希望されますか。
						</div>
					</c:when>
					<c:otherwise>
						<div class="sub-message">
							GOLD会員の会員費は入会費1,080円(税込)と年会費5,400円(税込)です。<br/>
							GOLD会員としての入会をご希望されますか。
						</div>
					</c:otherwise>
				</c:choose>
			</div>
			<div class="btnarea btn_center mt30">
				<a id="btn-apply-goldmember" href="/member/myInfo/applyGoldMember" class="btnbx2 bblue" style="margin-right:20px;">希望します。</a>
			</div>
		</div>
	</div>
</div>
<script type="text/javascript">
$(function () {
    $('a#btn-apply-goldmember').on({
        'click' : function(event){
            event.preventDefault();  
            formSubmitObj.submitGoldApply($(this));
        }
    });
    
});

var formSubmitObj = {
	submitGoldApply : function(form){
    	
        ajaxUtil.postDisableAsync($(form).attr('action'), $(form).serialize(), formSubmitObj.resultGoldApply);
        
        return false;
    },
    resultGoldApply : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        if(json.result.status){
           location.replace('/member/myInfo/applyGoldMemberComplete');
        }else{
           ajaxUtil.error(json);
        }
    }
};
</script>