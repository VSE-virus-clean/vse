<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%-- 
    JSP Name : completeMan.jsp
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
				<div class="page-message">GOLD会員の<br/>お申し込みが完了しました。</div>
				<div class="sub-message">
					GOLD会員登録の案内メールをご登録時のメール先へお送りいたしました。<br/>
					案内メールの内容に従って入金、確認後「GOLD会員入会手続き完了のお知らせ」をメールにてお送り致します。<br/>
					又はサイトへログインし、マイページでのご確認も可能でございます。<br/>
					お問合せがある場合はメールでのお問合せをお願いいたします。<br/>
					ありがとうございます。
				</div>
			</div>
			<div class="btnarea btn_center mt30">
				<a href="/index" class="btnbx2 bdefault">OK</a>
			</div>
		</div>
	</div>
</div>