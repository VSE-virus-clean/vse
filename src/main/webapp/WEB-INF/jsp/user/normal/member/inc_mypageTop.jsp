<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="location_bar">
	<ul class="clearfix inner pcTab">
		<li><a href="/index.vc">Home</a></li>
		<li><a href="#">마이페이지</a></li>
	</ul>
	<div class="mo inner">
		<a href="" ></a>
	</div>			
</div>
<h3 class="p_tit">마이페이지</h3>
<div class="ranking">
	<div class="ranking_det">
		<a href="javascript:;" onclick="modalUtil.open('benefit');"><span>자세히 보기&nbsp;&gt;</span></a>
	</div>
	<div class="ranking_tit">
		<p><strong>${memberInfo.info.mbrNm}</strong><span>님의 등급은&nbsp; </span>
		
		<c:choose>
			<c:when test="${memberInfo.info.mbrGrade eq 'NORMAL'}">
				<img src="/resources/user/images/mypage/normal_icon.png" alt=""><strong class="nomal">일반회원 </strong>
			</c:when>
			<c:when test="${memberInfo.info.mbrGrade eq 'BRONZE'}">
				<img src="/resources/user/images/mypage/bronze_img.png" alt=""><strong class="bronze">Bronze </strong>
			</c:when>
			<c:when test="${memberInfo.info.mbrGrade eq 'SILVER'}">
				<img src="/resources/user/images/mypage/silver_icon.png" alt=""><strong class="silver">Silver </strong>
			</c:when>
			<c:when test="${memberInfo.info.mbrGrade eq 'GOLD'}">
				<img src="/resources/user/images/mypage/gold_icon.png" alt=""><strong class="gold">Gold </strong>
			</c:when>
		</c:choose>
		
		<span> &nbsp;입니다.</span></p>
	</div>
	
</div>
<div class="mypg_list_wrap">
	<ul class="mypg_list">
		<li class="${pageMenuId eq 'KMED' or pageMenuId eq 'KMEDA' or pageMenuId eq 'KMEDB'? 'active' : ''}">
			<a href="/member/order/active/list.vc" class="history_tt"><p class="history_icon icon_img"></p><p class="list_txt">주문내역</p></a>
		</li>
		<li class="${pageMenuId eq 'KMEEA' or pageMenuId eq 'KMEEB' ? 'active' : ''}">
			<a href="/member/coupon/active/list.vc" class="coupon_tt"><p class="coupon_icon icon_img"></p><p class="list_txt">쿠폰함</p></a>
		</li>
		<li class="${pageMenuId eq 'KMEF' or pageMenuId eq 'KMEFA' or pageMenuId eq 'KMEFB' or pageMenuId eq 'KMEFC' ? 'active' : ''}">
			<a href="/member/product/list.vc" class="product_tt"><p class="product_icon icon_img"></p><p class="list_txt">정품등록<br class="mo">/보유제품</p></a>
		</li>
		<li class="${pageMenuId eq 'KMEG' ? 'active' : ''}">
			<a href="/member/qna/list.vc" class="qna_tt"><p class="qna_icon icon_img"></p><p class="list_txt">문의내역</p></a>
		</li>
	</ul>
</div>