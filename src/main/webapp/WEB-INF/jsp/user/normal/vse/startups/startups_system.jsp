<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
    Description : 창업 > 창업시스템
--%>

<div id="contentWrap" class="pb0">
	<h2 id="contentAnchor">콘텐츠 영역</h2>
	<div class="inner startups_top pb100">
		<div class="move top_video">
			<iframe class="shadow" style="width:100%;height:675px;" src="https://www.youtube.com/embed/Lxe2IuYlcoQ?rel=0&modestbranding=1" title="YouTube embed" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen ></iframe>
		</div>
	</div>
	<div class="bg_gray pb100 pt100">
		<div class="inner">
			<h3 class="tit">VSE 설치·창업가이드</h3>
			<ul class="startups_process text_center pc mt50">
				<li>
					<span class="step">STEP 1</span>
					<img src="/resources/vse/images/icon/icon_startups_01.png" />
					<h4 class="sub_tit">제품문의</h4>
					<ul class="list_type_01">
						<li>콜센터 문의</li>
						<li>제품 설명</li>
					</ul>
				</li>
				<li>
					<span class="step">STEP 2</span>
					<img src="/resources/vse/images/icon/icon_startups_02.png" />
					<h4 class="sub_tit">영업팀 방문 상담</h4>
					<ul class="list_type_01">
						<li>VSE 제품 설명</li>
						<li>설치 장소 검토</li>
						<li>견적 및 계약 협의</li>
						<li>연습장 운영 확인</li>
					</ul>
				</li>
				<li>
					<span class="step">STEP 3</span>
					<img src="/resources/vse/images/icon/icon_startups_03.png" />
					<h4 class="sub_tit">제품 계약</h4>
					<ul class="list_type_01">
						<li>시스템 수/설치일 확정</li>
						<li>홍보 및 운영방안 안내</li>
						<li>제품 발주</li>
					</ul>
				</li>
			</ul>
			<!--startups_process-->
			<ul class="startups_process text_center pc">
				<li>
					<span class="step">STEP 4</span>
					<img src="/resources/vse/images/icon/icon_startups_04.png" />
					<h4 class="sub_tit">인테리어공사/제품설치</h4>
					<ul class="list_type_01">
						<li>인테리어공사 진행</li>
						<li>랜/케이블 전달</li>
						<li>스크린 사이즈 측정</li>
						<li>VSE설치</li>
					</ul>
				</li>
				<li>
					<span class="step">STEP 5</span>
					<img src="/resources/vse/images/icon/icon_startups_05.png" />
					<h4 class="sub_tit">매장 운영 컨설팅</h4>
					<ul class="list_type_01">
						<li>시스템 수/설치일 확정</li>
						<li>홍보 및 운영방안 안내</li>
						<li>운영방법 교육</li>
						<li>타석관리 프로그램</li>
						<li>APP교육</li>
					</ul>
				</li>
				<li>
					<span class="step">STEP 6</span>
					<img src="/resources/vse/images/icon/icon_startups_06.png" />
					<h4 class="sub_tit">케어서비스</h4>
					<ul class="list_type_01">
						<li>시스템 이상유무 확인</li>
						<li>전국 A/S망 운영</li>
					</ul>
				</li>
			</ul>
			<!--startups_process-->
			<div class="startups_process_slide process_slide swiper-container mo">
				<ul class="startups_process swiper-wrapper">
					<li class="swiper-slide ">
						<span class="step">STEP 1</span>
						<img src="/resources/vse/images/icon/icon_startups_01.png" />
						<h4 class="sub_tit">제품문의</h4>
						<ul class="list_type_01">
							<li>콜센터 문의</li>
							<li>제품 설명</li>
						</ul>
					</li>
					<li class="swiper-slide ">
						<span class="step">STEP 2</span>
						<img src="/resources/vse/images/icon/icon_startups_02.png" />
						<h4 class="sub_tit">상담원 방문 상담</h4>
						<ul class="list_type_01">
							<li>VSE 제품 설명</li>
							<li>설치 장소 검토</li>
							<li>견적 및 계약 협의</li>
							<li>연습장 운영 확인</li>
						</ul>
					</li>
					<li class="swiper-slide ">
						<span class="step">STEP 3</span>
						<img src="/resources/vse/images/icon/icon_startups_03.png" />
						<h4 class="sub_tit">제품 계약</h4>
						<ul class="list_type_01">
							<li>시스템 수/설치일 확정</li>
							<li>홍보 및 운영방안 안내</li>
							<li>제품 발주</li>
						</ul>
					</li>
					<li class="swiper-slide ">
						<span class="step">STEP 4</span>
						<img src="/resources/vse/images/icon/icon_startups_04.png" />
						<h4 class="sub_tit">타석공사 / 제품설치</h4>
						<ul class="list_type_01">
							<li>타석공사 진행</li>
							<li>랜/케이블 전달</li>
							<li>스크린 사이즈 측정</li>
							<li>VSE설치</li>
						</ul>
					</li>
					<li class="swiper-slide ">
						<span class="step">STEP 5</span>
						<img src="/resources/vse/images/icon/icon_startups_05.png" />
						<h4 class="sub_tit">매장 운영 컨설팅</h4>
						<ul class="list_type_01">
							<li>시스템 수/설치일 확정</li>
							<li>홍보 및 운영방안 안내</li>
							<li>운영방법 교육</li>
							<li>타석관리 프로그램</li>
							<li>APP교육</li>
						</ul>
					</li>
					<li class="swiper-slide ">
						<span class="step">STEP 6</span>
						<img src="/resources/vse/images/icon/icon_startups_06.png" />
						<h4 class="sub_tit">제품문의</h4>
						<ul class="list_type_01">
							<li>시스템 이상유무 확인</li>
							<li>전국 A/S망 운영</li>
						</ul>
					</li>
				</ul>
				<div class="swiper-scrollbar"></div>
			</div>
			<!--startups_process mo-->
		</div>
	</div>

	<div class="startups_videos pt100 pb40">
		<div class="inner">
			<iframe class="shadow" style="width:100%;height:700px;" src="https://www.youtube.com/embed/pHA5P10C2yk?rel=0&modestbranding=1" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
		</div>
	</div>

	<div class="inner pb100">
		<%@ include file="/WEB-INF/jsp/user/normal/vse/business/business_register01.jsp" %>
	</div>
<!--
    <div class="inner pb100 pt60">
		<ul class="startups_list clear">
			<li>
				<div class="img">
					<img src="/resources/vse/images/subpage/startups_slide_01.jpg">
					<p class="up">타석 관리 프로그램</p>
				</div>
				<div class="con">
					체계적인 매장 운영을 위한 솔루션 제공<br />
					PC, 모바일 연동으로 어디서든 매장 현황 파악
					<a href="javascript:;" class="btn">주요기능<em class=""></em></a>
				</div>
				<div class="con_sub">
					<ul class="x_3">
						<li>- 타석현황 </li>
						<li>- 고객 타석 예약</li> 
						<li>- 이용내역</li>
						<li>- 매출관리 </li>
						<li>- 회원관리 </li>
					</ul>
				</div>
			</li>
			<li>
				<div class="img">
					<img src="/resources/vse/images/subpage/startups_slide_02.jpg">
					<p class="up">체계적인 케어 서비스</p>
				</div>
				<div class="con">
					상시 모니터링을 통한 시스템 이상유무 파악<br />
					익일 현장 방문을 통한 철저한 AS
				</div>
			</li>
		</ul>
	</div>
-->
	<!--product_slide_01-->
<!--
    <div class="bg_gray slide_type_02 pb100 pt100">
		
		<h3 class="tit">
			보이스캐디 VSE가<br/>
			원스탑으로 창업을<br class="pc" />
			지원합니다. 
		</h3>
		<div class="slide_con">
			<div class="swiper-container">
				<ul class="swiper-wrapper">
					<li class="swiper-slide ">
						<a href="javascript:;">
							<div class="img">
								<img src="/resources/vse/images/subpage/thumbnail_01.jpg" />
							</div>
							<p class="sub_tit">
								성공창업인터뷰 1
							</p>
							<span class="date">2021-07-28</span>
						</a>
					</li>
					<li class="swiper-slide ">
						<a href="javascript:;">
							<div class="img">
								<img src="/resources/vse/images/subpage/thumbnail_02.jpg" />
							</div>
							<p class="sub_tit">
								성공창업인터뷰 2
							</p>
							<span class="date">2021-07-28</span>
						</a>
					</li>
					<li class="swiper-slide ">
					<a href="javascript:;">
							<div class="img">
								<img src="/resources/vse/images/subpage/thumbnail_01.jpg" />
							</div>
							<p class="sub_tit">
								성공창업인터뷰 3
							</p>
							<span class="date">2021-07-28</span>
						</a>
					</li>
				</ul>
				<div class="swiper-button-control swiper-button-next"></div>
				<div class="swiper-button-control swiper-button-prev"></div>
				<div class="swiper-scrollbar"></div>
			</div>
		</div>
	</div>
-->
</div>

<script>
$(function(){
	$('#header .gnb > li').eq(2).addClass('active');
});
</script>
			