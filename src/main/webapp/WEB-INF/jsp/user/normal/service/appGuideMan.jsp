<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%-- 
    JSP Name : appGuideMan.jsp
    Description : APP사용설명
--%>
<style>
	.app_down{padding: 100px 0; text-align: center;}
	.app_down p{font-size: 40px;margin-top: 50px;}
	.app_down p+p{font-size: 24px; color: #43097c;margin-top: 15px;}	
	.app_down .down_btn_wrap {margin-top: 60px; display: flex; justify-content: center;}
	.app_down .down_btn_wrap a{ width: 300px; cursor:pointer;	}
	.app_down .down_btn_wrap a + a{margin-left: 20px;}
	.app_down .down_btn_wrap a span{display: inline-block;padding-left: 40px;background-repeat: no-repeat; line-height: 55px;}
	.app_down .down_btn_wrap a.goole span{background-image: url('/resources/user/images/service/google_icon.png'); background-position: 0 14px ;}
	.app_down .down_btn_wrap a.ios span{background-image: url('/resources/user/images/service/ios_icon.png'); background-position:0 10px;}
	.appinfo > img{position: relative; left: 50%; margin-left: -960px;min}
	.pc {display:block !important; padding:0 !important;}
	.mo {display:none !important}
	.tab {display:none !important}
	.pc1330 {display:none;}
	
	@media (max-width:1330px) {
		.pc1330 {display:block;}
	
		.appinfo > img {position:static; margin-left:0; width:100%;}
		.appinfo h3.p_tit {margin-top:65px;}
		.app_down {padding:60px;}
		.app_down p {margin-top:40px;}
		.app_down p+p {margin-top: 20px;}
		.app_down .down_btn_wrap {margin-top: 45px;}
		
	}
	
	@media (max-width:1024px) {
		.pc {display:none !important;}
		.tab {display:block !important}
	
		.appinfo > img {position:static; margin-left:0; width:100%;}
		.appinfo h3.p_tit {margin-top:65px; font-weight: 800;}
		.app_down {padding:60px 0;}
		.app_down p {margin-top:40px;}
		.app_down p+p {margin-top: 20px;}
		.app_down .down_btn_wrap {margin-top: 45px;}
		
	}
	@media (max-width:720px){
		.tab {display:none !important}
		.mo {display:block !important}
	
		.appinfo h3.p_tit {margin-top:3.47vw; font-size:8.33vw;}
		.app_down {padding:10.42vw 0 8.33vw;}
		.app_down img {width:29.58vw;}
		.app_down p {margin-top:4.17vw; font-size:5.56vw;}
		.app_down p+p {margin-top: 3.47vw; font-size:3.33vw}
		.app_down .down_btn_wrap {margin-top: 8.33vw;;}
		.app_down .down_btn_wrap a {width:41.67vw; }
		.app_down .down_btn_wrap a.goole span {font-size:4.17vw; padding-left:4vw; line-height: 10vw; background-position: 0 3.3vw; background-size: 3.5vw;}
		.app_down .down_btn_wrap a.ios span {font-size:4.17vw; padding-left:5vw; line-height: 10vw; background-position: 0 2.5vw; background-size: 3.5vw;} 
		
	}
	
</style>
<link rel="stylesheet" href="/resources/user/css/service.css?v=${cacheParam}">
<div id="container">
	<div class="location_bar">
		<ul class="clearfix inner pcTab">
			<li><a href="/index.vc">Home</a></li>
			<li><a href="#">Service</a></li>
			<li><a href="#">APP 사용설명</a></li>
		</ul>
		<div class="mo inner">
			<a href="" ></a>
		</div>			
	</div>
	<div class="appinfo"> 
		<div class="inner">
			<h3 class="p_tit">APP 사용설명</h3>
			<div class="app_down">
				<img src="/resources/user/images/service/app_img.png" alt="">

				<p>바이러스클린랩 Z-mini는 <br class="pc1330">어플리케이션(APP)이 지원됩니다.</p>
				<p class="pp">※ 플레이스토어/앱스토어에서 ‘바이러스클린랩’을 <br class="pc1330">검색하여 설치해 주세요.</p>
				<div class="down_btn_wrap">
					<a href="https://play.google.com/store/apps/details?id=com.viruscleanlab.contents" target="_blank" class="btn02 btn_line down goole"><span>구글 플레이</span></a>
					<a href="https://apps.apple.com/kr/app/%EB%B0%94%EC%9D%B4%EB%9F%AC%EC%8A%A4%ED%81%B4%EB%A6%B0%EB%9E%A9/id1559099722" target="_blank" class="btn02 btn_line down ios"><span>앱 스토어</span></a>
				</div>	
			</div>
		</div>
		<img src="/resources/user/images/service/app.jpg" alt="" class="pc">
		<img src="/resources/user/images/service/tab_app.jpg" alt="" class="tab">
		<img src="/resources/user/images/service/mo_app.jpg" alt="" class="mo">
	</div>
</div>
