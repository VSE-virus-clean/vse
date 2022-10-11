<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%-- 
    JSP Name : inc_lnb.jsp
    Description : 페이지 왼쪽 메뉴를 장식한다. LNB영역이다.
                - 구조 
                <li class="depth01">
					<a href="javascript:;"><span>게시판관리</span></a>
					<ul class="depth02">
						<li><a href=""><span>게시판관리</span></a></li>
						<li><a href=""><span>게시물관리</span></a></li>
					</ul>
				</li>
--%>

<%-- <c:when test="${pageMenuId eq 'A'}"> --%>
<style>
#lefrMenu > ul > li .depth02 > li.open a{color: #ffdc7c;}
</style>
<div id="side">
	<div class="side_top">
		<a href="${contextPath}/mainMan.vc" class="clearfix">
			<img src="/resources/admin/images/common/logo.png" alt="">
		</a>
	</div>
	<div class="user">
		<a href="${contextPath}/ua/uaMod.vc" class="clearfix">
			<img src="/resources/admin/images/common/user_icon.png" alt="" class="user_icon">
			<span class="id">${sessionScope.sessionVO.name}(${sessionScope.sessionVO.id})</span>
		</a>
	</div>
	<div id="lefrMenu">
		<ul>
			<%-- 
			<li id="snb_A" class="depth01">
				<a href="javascript:;"><span>배너&amp;팝업관리</span></a>
				<ul class="depth02">
					<li id="snb_A1"><a href="${contextPath}/board/banner/list.vc"><span>배너관리</span></a></li>
					<li id="snb_A2"><a href="${contextPath}/board/popup/web/list.vc"><span>WEB 팝업관리</span></a></li>
					<li id="snb_A3"><a href="${contextPath}/board/popup/app/list.vc"><span>APP 팝업관리</span></a></li>
					<li id="snb_A4"><a href="${contextPath}/board/push/list.vc"><span>APP PUSH관리</span></a></li>
				</ul>
			</li>
			<li id="snb_B" class="depth01">
				<a href="javascript:;"><span>상품관리</span></a>
				<ul class="depth02">
					<li id="snb_B1"><a href="${contextPath}/product/info/list.vc"><span>상품목록</span></a></li>
					<li id="snb_B2"><a href="${contextPath}/product/qna/list.vc"><span>상품문의</span></a></li>
					<li id="snb_B3"><a href="${contextPath}/product/review/list.vc"><span>상품후기</span></a></li>
					<li id="snb_B4"><a href="${contextPath}/product/category/list.vc"><span>카테고리</span></a></li>
				</ul>
			</li>
			<li id="snb_C" class="depth01">
				<a href="javascript:;"><span>주문관리</span></a>
				<ul class="depth02">
					<li id="snb_C1"><a href="${contextPath}/shop/order/list.vc"><span>주문관리</span></a></li>
					<li id="snb_C2"><a href="${contextPath}/shop/invoice/list.vc"><span>송장등록</span></a></li>
				</ul>
			</li>
			<li id="snb_D" class="depth01">
				<a href="javascript:;"><span>쿠폰관리</span></a>
				<ul class="depth02">
					<li id="snb_D1"><a href="${contextPath}/shop/coupon/info/list.vc"><span>쿠폰관리</span></a></li>
					<li id="snb_D2"><a href="${contextPath}/shop/coupon/user/list.vc"><span>쿠폰내역</span></a></li>
				</ul>
			</li>
			<li id="snb_E" class="depth01">
				<a href="javascript:;"><span>정품관리</span></a>
				<ul class="depth02">
					<li id="snb_E1"><a href="${contextPath}/shop/origin/list.vc"><span>정품목록</span></a></li>
				</ul>
			</li>
			<li id="snb_F" class="depth01">
				<a href="javascript:;"><span>게시판관리</span></a>
				<ul class="depth02">
					<li id="snb_F1"><a href="${contextPath}/board/notice/list.vc"><span>공지사항</span></a></li>
					<li id="snb_F2"><a href="${contextPath}/board/event/list.vc"><span>이벤트</span></a></li>
					<li id="snb_F3"><a href="${contextPath}/board/faq/list.vc"><span>FAQ</span></a></li>
					<li id="snb_F4"><a href="${contextPath}/product/bbs/list.vc"><span>커뮤니티관리</span></a></li>
				</ul>
			</li>
			<li id="snb_G" class="depth01">
				<a href="javascript:;"><span>고객관리</span></a>
				<ul class="depth02">
					<li id="snb_G1" ><a href="${contextPath}/member/info/list.vc"><span>고객목록</span></a></li>
				</ul>
			</li>
			<li id="snb_H" class="depth01">
				<a href="javascript:;"><span>통계관리</span></a>
				<ul class="depth02">
					<li id="snb_H1"><a href="${contextPath}/stats/sales.vc"><span>매출 통계</span></a></li>
					<li id="snb_H2"><a href="${contextPath}/stats/accounts.vc"><span>정산 통계</span></a></li>
					<li id="snb_H3"><a href="${contextPath}/stats/products.vc"><span>상품관리 통계</span></a></li>
					<!--  <li id="snb_H4"><a href="${contextPath}/stats/origins.vc"><span>정품등록 통계</span></a></li>-->
				</ul>
			</li>
			<li id="snb_J" class="depth01">
				<a href="javascript:;"><span>VSE 관리</span></a>
				<ul class="depth02">
					<li id="snb_J1"><a href="${contextPath}/board/vsenews/list.vc"><span>이벤트&amp;뉴스관리</span></a></li>
					<li id="snb_J2"><a href="${contextPath}/board/vsefaq/list.vc"><span>FAQ관리</span></a></li>
					<li id="snb_J6"><a href="${contextPath}/board/vseshop/list.vc"><span>매장관리</span></a></li>
					<li id="snb_J7"><a href="${contextPath}/board/vsebusiness/list.vc"><span>사업설명회 관리</span></a></li>
					<li id="snb_J3"><a href="${contextPath}/vse/business/list.vc"><span>창업상담 신청관리</span></a></li>
					<li id="snb_J4"><a href="${contextPath}/vse/presentation/list.vc"><span>사업설명회 신청관리</span></a></li>
					<li id="snb_J5"><a href="${contextPath}/vse/application/list.vc"><span>프로 지원서관리</span></a></li>
				</ul>
			</li>
			<li id="snb_I" class="depth01">
				<a href="javascript:;"><span>환경설정</span></a>
				<ul class="depth02">
					<li id="sub_I1"><a href="${contextPath}/board/app/list.vc"><span>펌웨어관리</span></a></li>
					<li id="sub_I2"><a href="${contextPath}/shop/preferences/modify.vc"><span>운영정책관리</span></a></li>
				</ul>
			</li>
			 --%>
			<li class="depth01 vsenews">
				<a href="${contextPath}/board/vsenews/list.vc"><span>NEWS</span></a>
			</li>
			<li class="depth01 vsebusiness">
				<a href="${contextPath}/vse/business/list.vc"><span>Support</span></a>
			</li>
			<li class="depth01 vsefaq">
				<a href="${contextPath}/board/vsefaq/list.vc"><span>FAQ</span></a>
			</li>
			<c:if test="${sessionScope.sessionVO.authLevel eq 'S'}">
				<li class="depth01 manager">
					<a href="${contextPath}/manager/info/list.vc"><span>Admin</span></a>
				</li>
			</c:if>
		</ul>
	</div>
</div>
