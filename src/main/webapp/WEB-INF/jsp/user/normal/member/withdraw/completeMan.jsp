<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%-- 
    JSP Name : chargingMan.jsp
    Description : 회원 > 탈퇴 완료
    author Jeong.hyoungjea
    since 2017.02.01.
    version 1.0
    Modification Information
       since          author              description
    ===========    =============    ===========================
    2017.02.01.   Jeong.hyoungjea     최초 생성
--%>
<style>
#divContent { margin:auto; width:1000px; text-align:center; }
.page-message { margin:auto; font-size:3.5em; font-weight:300; line-height:80px; text-align:center; padding-bottom:50px;} 
.sub-message { margin:auto; font-size:1.6em; font-weight:400; line-height:50px; text-align:left; padding:60px 30px 60px;} 

</style>

<div id="container">
	<div id="pageContents">
		<div id="divContent">
			<div class="sub-message center">
				カン・ジファン公式ファンクラブから退会完了しました。<br/><br/>
				今までの応援ありがとうございました。
			</div>
			<div class="btnarea center mt30">
				<a href="/index" class="btnbx2 bdefault">OK</a>
			</div>
		</div>
	</div>
</div>