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
		<a href="/index.vse" class="logo"><img src="/resources/vse/images/com/logo02_png-white-2.png" alt="VSE" /></a>
		<ul class="gnb">
			<li class=<c:out value="${pageMenuId == 'product' ? 'active':'' }" />>
				<a href="/product/vse1.vse">VSE</a>
				<div class="gnb_sub">
					<ul class="inner"> 
						<li>
							<a href="/product/vse1.vse">VSE1</a>
						</li>
						<!-- 
						<li>
							<a href="/product/vse2.vse">VSE2</a>
						</li>
						 -->
						<li>
							<a href="/product/ohter.vse">Ohter</a>
						</li>
					</ul>
				</div>
			</li>
			<li class=<c:out value="${pageMenuId == 'space' ? 'active':'' }" />>
				<a href="/space/main.vse">CHOOSE YOUR SPACE</a>
				<div class="gnb_sub">
				<ul class="inner">
					<li>
						<a href="/space/residential_installs.vse">Residential Installs</a>
					</li>
					<li>
						<a href="/space/academy_or_practice_range.vse">Academy or Practice Range</a>
					</li>
					<li>
						<a href="/space/golf_club_golf_facility.vse">Golf Club &amp; Golf Facility</a>
					</li>
					<li>
						<a href="/space/sports_lounge.vse">Sports Lounge</a>
					</li>
				</ul>
				</div>
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
			
			<li class=<c:out value="${pageMenuId == 'customer' ? 'active':'' }" />>
				<a href="/customer/faq.vse">CS CENTER</a>
				<div class="gnb_sub">
					<ul class="inner">
						<li>
							<a href="/customer/faq.vse">FAQ</a>
						</li>
						<li>
							<a href="/customer/shop-location/list.vse">LOCATION</a>
						</li>
						<li>
							<a href="/customer/request_more_information.vse">REQUEST MORE INFORMATION</a>
						</li>
						<li>
							<a href="/customer/event/list.vse">NOTICE&amp;NEWS</a>
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
					<a href="/index.vse" class="logo"><img src="/resources/vse/images/com/logo02_png-white-2.png" alt="VSE"></a>
					<a href="javascript:;" class="allmenu_close" title="CLOSE"></a>
				</div>
				<div  class="mo_gnb_con">
					<ul class="mo_gnb_1">
						<li class="">
							<a href="#none"><span>VSE</span></a>
							<ul class="inner"> 
								<li>
									<a href="/product/vse1.vse">VSE1</a>
								</li>
								<!-- 
								<li>
									<a href="/product/vse2.vse">VSE2</a>
								</li>
								 -->
								<li>
									<a href="/product/ohter.vse">Ohter</a>
								</li>
							</ul>
						</li>
						<li>
							<a href="/space/main.vse"><span>CHOOSE YOUR SPACE</span></a>
							<ul class="inner">
								<li>
									<a href="/space/residential_installs.vse">Residential Installs</a>
								</li>
								<li>
									<a href="/space/academy_or_practice_range.vse">Academy or Practice Range</a>
								</li>
								<li>
									<a href="/space/golf_club_golf_facility.vse">Golf Club &amp; Golf Facility</a>
								</li>
								<li>
									<a href="/space/sports_lounge.vse">Sports Lounge</a>
								</li>
							</ul>
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
							<a href="#none"><span>CS CENTER</span></a>
							<ul class="inner">
								<li>
									<a href="/customer/faq.vse">FAQ</a>
								</li>
								<li>
									<a href="/customer/shop-location/list.vse">LOCATION</a>
								</li>
								<li>
									<a href="/customer/request_more_information.vse">REQUEST MORE INFORMATION</a>
								</li>
								<li>
									<a href="/customer/event/list.vse">NOTICE&amp;NEWS</a>
								</li>
							</ul>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<!-- END:mo_gnb -->
		<a href="https://voicecaddie.com/" class="top_link" target="_blank"><img src="/resources/vse/images/com/logo.png" alt="voice caddie" /></a>	
	</div>
</div>

