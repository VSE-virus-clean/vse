<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%-- 
    JSP Name : inc_header.jsp
    Description : 페이지 상단을 장식한다.
                  GNB영역이다.
--%>
<div id="skipNav">
	<a href="#contentWrap">contents</a>
	<a href="#header">menu</a>
</div>


<div id="header">
	<h2 id="gnbAnchor">menu</h2>
	<div class="header_wrap">
		<a href="/index.vse" class="logo"><img src="/resources/vse/images/com/logo01_png-white-2.png" alt="VSE" /></a>
		<ul class="gnb">
			<li class="">
				<a href="/product.vse">SIMULATOR</a>
			</li>
			<li>
				<a href="/interior.vse">CHOOSE YOUR SPACE</a>
			</li>
			<!--
			<li>
				<a id="GNB-STARTUP" href="/startups.vse">창업</a>
			</li>
			-->
			<!--
			<li>
				<a href="/business/presentation.vse">사업 설명회</a>
			</li>
			-->
			<li>
				<a href="javascript:;">CS CENTER</a>
				<div class="gnb_sub">
					<ul class="inner">
						<li>
							<a href="/customer/faq.vse">자주하는 질문</a>
						</li>
						<li>
							<a href="/customer/shop-location/list.vse">매장찾기</a>
						</li>
						<li>
							<a href="https://voicecaddie.co.kr/bbs/login.php?url=https%3A%2F%2Fvoicecaddie.co.kr%2Fbbs%2Fqalist.php" target="_blank">제품, 기타 문의</a><!-- customer_inquiry.vse -->
						</li>
						<li>
							<a href="/customer/event/list.vse">이벤트&amp;뉴스</a>
						</li>
					</ul>
				</div>
			</li>
			<!-- 
			<li>
				<a href="/teaching.vse">VSE레슨프로</a>
			</li>
			 -->

		</ul>
		<!-- gnb -->
		<!-- mo_gnb -->
		<div class="mo_header">
			<a href="javascript:;" class="allmenu"></a>
			<div class="mo_gnb">
				<div class="mo_gnb_header">
					<a href="/index.vse" class="logo">VOICE CADDIE<img src="/resources/vse/images/com/symbol_w.png" alt="VSE"></a>
					<a href="javascript:;" class="allmenu_close" title="CLOSE"></a>
				</div>
				<div  class="mo_gnb_con">
					<ul class="mo_gnb_1">
						<li class="">
							<a href="/product.vse"><span>SIMULATOR</span></a>
						</li>
						<li>
							<a href="/interior.vse"><span>CHOOSE YOUR SPACE</span></a>
						</li>
						<!-- 
						<li>
							<a href="/startups.vse"><span>창업</span></a>			
						</li>
						 -->
						<!--
						<li>
							<a href="/business/presentation.vse"><span>사업 설명회</span></a>
						</li>
						-->
						<li class="sub">
							<a href="javascript:;"><span>CS CENTER</span></a>
							<ul class="inner">
								<li>
									<a href="/customer/faq.vse">자주하는 질문</a>
								</li>
								<li>
									<a href="/customer/shop-location.vse">매장찾기</a>
								</li>
								<li>
									<a href="https://voicecaddie.co.kr/bbs/login.php?url=https%3A%2F%2Fvoicecaddie.co.kr%2Fbbs%2Fqalist.php" target="_blank">제품, 기타 문의</a><!-- customer_inquiry.html -->
								</li>
								<li>
									<a href="/customer/event/list.vse">이벤트&뉴스</a>
								</li>
							</ul>
						</li>
						<li>
							<a href="/teaching.vse"><span>VSE레슨프로</span></a>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<!-- END:mo_gnb -->
		<a href="https://voicecaddie.com/" class="top_link" target="_blank"><img src="/resources/vse/images/com/logo.png" alt="voice caddie" /></a>	
	</div>
</div>

