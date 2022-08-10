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
	<a href="#contentWrap">본문 바로가기</a>
	<a href="#header">글로벌 네비게이션 바로가기</a>
</div>


<div id="header">
	<h2 id="gnbAnchor">글로벌 네비게이션 영역</h2>
	<div class="header_wrap">
		<a href="/index.vse" class="logo"><img src="/resources/vse/images/com/header_logo.png" alt="VSE로고" /></a>
		<ul class="gnb">
			<li class="">
				<a href="/product.vse">VSE</a>
			</li>
			<li>
				<a href="/interior.vse">인테리어</a>
			</li>
			<li>
				<a id="GNB-STARTUP" href="/startups.vse">창업</a>
			</li>
			<!--
			<li>
				<a href="/business/presentation.vse">사업 설명회</a>
			</li>
			-->
			<li>
				<a href="javascript:;">고객지원</a>
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
			<li>
				<a href="/teaching.vse">VSE레슨프로</a>
			</li>

		</ul>
		<!-- gnb -->
		<!-- mo_gnb -->
		<div class="mo_header">
			<a href="javascript:;" class="allmenu"></a>
			<div class="mo_gnb">
				<div class="mo_gnb_header">
					<a href="/index.vse" class="logo">보이스캐디<img src="/resources/vse/images/com/symbol_w.png" alt="VSE로고"></a>
					<a href="javascript:;" class="allmenu_close" title="메뉴닫기"></a>
				</div>
				<div  class="mo_gnb_con">
					<ul class="mo_gnb_1">
						<li class="">
							<a href="/product.vse"><span>VSE</span></a>
						</li>
						<li>
							<a href="/interior.vse"><span>인테리어</span></a>
						</li>
						<li>
							<a href="/startups.vse"><span>창업</span></a>			
						</li>
						<!--
						<li>
							<a href="/business/presentation.vse"><span>사업 설명회</span></a>
						</li>
						-->
						<li class="sub">
							<a href="javascript:;"><span>고객지원</span></a>
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
		<a href="https://main.voicecaddie.co.kr/" class="top_link" target="_blank">보이스캐디 바로가기 <img src="/resources/vse/images/icon/icon_link.png" alt="링크이동하기 아이콘" /></a>	
	</div>
</div>

