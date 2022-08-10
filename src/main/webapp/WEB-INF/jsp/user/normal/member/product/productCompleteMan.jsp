<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
    JSP Name : productCompleteMan.jsp
    Description : 마이페이지 > 정품등록 완료
--%>
<link rel="stylesheet" href="/resources/user/css/mypage.css?v=${cacheParam}">

<div id="container">
	
	<div class="order order_main_page mp_prd_page">
		<div class="inner">
		
			<%@ include file="/WEB-INF/jsp/user/normal/member/inc_mypageTop.jsp" %>
			
			<div class="step">
				<ul>
					<li style="width:50%;"><p><span class="num"><em>01</em></span> <span>제품 및 구입처 확인</span></p></li>
<!-- 					<li><p><span class="num"><em>02</em></span> <span>설문조사</span></p></li> -->
					<li class="active" style="width:50%;"><p><span class="num"><em>02</em></span> <span>정품등록 완료</span></p></li>
				</ul>
			</div>

			<div class="mp_prd_wrap">
				<div class="mp_prd_wrap04 active">
					<div class="line_box">
						<div class="line_box_inner">
							<img src="/resources/user/images/menber/joinok.png" alt="">
							<h3>정품등록이 완료 되었습니다.</h3>
							<p class="info">							
								안녕하세요. 휴대용 듀얼 바이러스살균기 Z-mini의 정품등록 해주셔서 진심으로 감사드립니다. <br/> 
								아래 URL을 접속하시면 설문조사를 통해 제품의 문제점 파악 및 운영을 위한 조사를 시행하고자합니다.<br/>
								바쁘시더라도 정확한 정보를 제공해주시면 현황을 파악하고 효과적으로 제품을 개선하겠습니다.<br/>
								감사합니다.<br/>
								
								<a href="https://docs.google.com/forms/d/e/1FAIpQLScrdab3lDQsnpcSuJhDXh9WV1AzilOKrK5fzLSzm7SOmMoTRA/viewform" title="설문 조사 하러 가기" target="_blank" class="btn btn_pp"><span>설문 조사 하러 가기</span></a>
							</p>
						</div>						
					</div>
					<div class="btn_wrap">
						<a href="/member/product/list.vc" class="btn btn_pp" style="background:#999999;border:0;"><span>완료</span></a>
					</div>
				</div>
			</div>

		</div>						
	</div>		
</div>

<%@ include file="/WEB-INF/jsp/user/normal/common/include/inc_modal.jsp" %>

<script src="/resources/user/js/order.js"></script>
<script>

</script>
