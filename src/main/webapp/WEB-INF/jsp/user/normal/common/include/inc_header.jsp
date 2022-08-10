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
<c:if test="${pageMenuId eq 'KMA' }">
	<c:if test="${not empty popup.list}">
		<c:forEach var="data" items="${popup.list}">
			<div id="popup_banner" class="popup_${data.blcSn}" style="display:none;">
				<div class="popup_banner_inner">
					<a href="javascript:popClose(${data.blcSn}, 1);" data-sn="${data.blcSn}" class="pop_cbtn">
						<img src="/resources/user/ui_common/images/top_b_close.png" alt="">
					</a>
					<a class="popup_img" href="${data.rltdLk}">${function:printImageFileByList(data.fileSn, "POPUP", data.filNm, data.filNm)}</a>
					<div class="cls_days" onclick="javascript:popClose(${data.blcSn}, 7);">
						<label><input type="checkbox"><span>7일간 다시 보지 않기</span></label>
					</div>
				</div>
			</div>
		</c:forEach>
	</c:if>
	<c:if test="${not empty banner.list and deviceCode eq 'N'}">
		<c:forEach var="data" items="${banner.list}">
		<div class="tap_banner banner_${data.blcSn}" style="display:none;">
			<a href="${data.rltdLk}">${function:printImageFileByList(data.fileSn, "BANNER", data.filNm, data.filNm)}</a>
			<a href="javascript:closeTopBanner(${data.blcSn}, 1);" data-sn="${data.blcSn}" class="t_xbtn">
				<img src="/resources/user/ui_common/images/top_b_close.png" alt="">
			</a>
			<div class="cls_days" onclick="javascript:closeTopBanner(${data.blcSn}, 7);">
				<label><input type="checkbox"><span>7일간 다시 보지 않기</span></label>
			</div>	
		</div>
		</c:forEach>
	</c:if>
</c:if>
<!-- 배너 있을때만 top_b 사용 -->
<header id="heaeder">
	<div class="gnb">
		<div class="inner clearfix">
			<c:choose>
				<c:when test="${pageMenuId eq 'KMA' }">
					<a href="/index.vc" class="logo"><img src="/resources/user/ui_common/images/top_logo.png" alt=""></a>
				</c:when>
				<c:otherwise>
					<a href="/index.vc" class="logo"><img src="/resources/user/ui_common/images/sub_logo.png" alt=""></a>
				</c:otherwise>
			</c:choose>
			
			<div class="right">
				<ul class="right_menu clearfix">
					<li><a href="/shop/virus-clean-lab/view.vc?model=Z-mini">Z-mini</a></li>
					<li><a href="/member/product/list.vc">정품등록</a></li>
					
					<c:choose>
						
					<c:when test="${empty sessionScope.sessionVO}">
						<li><a href="/member/login.vc">로그인</a></li>
					</c:when>
	           		<c:otherwise>
	           			<li class="mypg_btn">
							<a href="javascript:;">${sessionScope.sessionVO.nick}</a>
							<div class="login_box" style="">
								<ul class="inner_sm" style="">
									<li><a href="/member/modify-personal-data.vc"><span >회원정보수정</span></a></li>
									<li><a href="/member.vc"><span >마이페이지</span></a></li>
									<li><a href="/member/logout.vc"><span >로그아웃</span></a></li>
								</ul>
							</div>	
						</li>
	           		</c:otherwise>
	           		</c:choose>
	           		
					<li><a href="/shop/cart.vc">카트</a></li>
					
				</ul>
<!-- 검색창 주석 210603
				<form name="gnbSearchForm" action="/shop/virus-clean-lab/list.vc">
					<input type="search" id="searchKey" style="display: inline-block;" value="">
					<button type="submit"><img src="/resources/user/ui_common/images/search_icon.png" alt=""></button>
				</form>
-->
				<a href="javascript:;" class="menu_btn">
					<c:choose>
						<c:when test="${pageMenuId eq 'KMA' }">
							<img src="/resources/user/ui_common/images/menu_btn.png" class="open" alt="">
							<img src="/resources/user/ui_common/images/close_btn.png" class="close" alt="">
						</c:when>
						<c:otherwise>
							<img src="/resources/user/ui_common/images/menu_btn_sub.png" class="open" alt="">
							<img src="/resources/user/ui_common/images/close_btn_w.png" class="close" alt="">
						</c:otherwise>
					</c:choose>
				</a>
			</div>
		</div>
	</div>		
	
	<div class="sub_memu">
		<div class="sub_menu_inner">
			<div class="sub_menu_top">
				<ul class="">
					<li><a href="/shop/virus-clean-lab/view.vc?model=Z-mini">Z-mini</a></li>
					<li><a href="/member/product/list.vc">정품등록</a></li>
					
					<c:choose>
						
					<c:when test="${empty sessionScope.sessionVO}">
						<li><a href="/member/login.vc">로그인</a></li>
					</c:when>
	           		<c:otherwise>
	           			<li class="mypg_btn m">
							<a href="javascript:;">${sessionScope.sessionVO.nick}</a>
						</li>
	           		</c:otherwise>
	           		</c:choose>
	           		
					<li><a href="/shop/cart.vc">카트</a></li>
					
				</ul>
				
				<div class="login_box m" style="">
					<ul class="inner_sm" style="">
						<li><a href="/member/modify-personal-data.vc"><span >회원정보수정</span></a></li>
						<li><a href="/member.vc"><span >마이페이지</span></a></li>
						<li><a href="/member/logout.vc"><span >로그아웃</span></a></li>
					</ul>
				</div>
<!-- 검색창 주석 210603	
				<form name="gnbSearchForm" action="/shop/virus-clean-lab/list.vc">
					<input type="search" id="searchKey" style="display: inline-block;" value="">
					<button type="submit"><img src="/resources/user/ui_common/images/main_search_icon.png" alt=""></button>
				</form>
 -->				
			</div>
			<ul class="sub_menu_cont">
				<li>
					<a href="/shop/virus-clean-lab/view.vc?model=Z-mini"><span class="num">01</span><strong>Shop</strong></a>
<!-- 					<div class="depth02"> -->
<!-- 						<ul> -->
<!-- 							<li><a href="/shop/virus-clean-lab/list.vc">Z-mini</a></li> -->
<!-- 							<li><a href="/shop/accessory/list.vc">액세사리</a></li>	 -->
<!-- 						</ul> -->
<!-- 					</div> -->
				</li>
				<li>
					<a href="/brand.vc"><span class="num">02</span><strong>Brand</strong></a>
				</li>
<!-- 				<li> -->
<!-- 					<a href="/community/list.vc"><span class="num">03</span><strong>Community</strong></a> -->
<!-- 				</li> -->
				<li>
					<a href="/event/ing/list.vc"><span class="num">03</span><strong>Event</strong></a>
				</li>
				<li>
					<a href="#"><span class="num">04</span><strong>Service</strong></a>
					<div class="depth02">
						<ul>
							<li><a href="/service/notice/list.vc">공지사항</a></li>
							<li><a href="/service/app-guide.vc">APP 사용설명</a></li>
							<li><a href="/service/faq/list.vc">FAQ</a></li>
							<li><a href="/service/customer-support.vc">고객센터</a></li>
						</ul>
					</div>
				</li>
			</ul>
		</div>
	</div>
</header>
<a href="#" onclick="javascript:window.open('https://talk.naver.com/ct/w4kv95?ref=' + encodeURIComponent(location.href), 'talktalk', 'width=461, height=640'); return false;" class="talk_btn">
	<img src="/resources/user/ui_common/images/talk_btn.png" alt="">
</a>
<a href="#" onclick="javascript:window.open('https://talk.naver.com/ct/w4kv95?ref=' + encodeURIComponent(location.href) + '#nafullscreen'); return false;" class="talk_btn mo">
	<img src="/resources/user/ui_common/images/talk_btn.png" alt="">
</a>
<a href="${pageMenuId eq 'KMA' ? '#section01' : '#'}" class="top_btn">
	<img src="/resources/user/ui_common/images/top_btn.png" alt="">
</a>
