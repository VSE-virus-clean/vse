<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%-- 
    JSP Name : brandMan.jsp
    Description : 브랜드
--%>
<style>
	.brand img{position: relative; left: 50%; margin-left: -960px;}
	.brand img.pc {display:block !important;}
	.brand img.tab {display:none !important;}
	.brand img.mo {display:none !important;}
	
	@media (max-width:1024px){
		
		.brand img {position:static; margin-left:0; width:100%; padding:0;}
		.brand img.tab{display:block !important;}
		.brand img.pc {display:none !important;}
		.brand img.mo {display:none !important;}
	}
	
	@media (max-width:720px){
		.brand img.pc {display:none !important;}
		.brand img.tab {display:none !important;}
		.brand img.mo {display:block !important;}
	}
</style>
<link rel="stylesheet" href="/resources/user/css/community.css?v=${cacheParam}">
<div id="container">
	<div class="location_bar">
		<ul class="clearfix inner pcTab">
			<li><a href="/index.vc">Home</a></li>
			<li><a href="#">Brand</a></li>
			<!-- <li><a href="#">브랜드 &amp; 기술 소개</a></li> -->
		</ul>
		<div class="mo inner">
			<a href="" ></a>
		</div>			
	</div>
	<div class="brand">
		<img src="/resources/user/images/brand/brand.jpg" alt="" class="pc">
		<img src="/resources/user/images/brand/tab_brand.jpg" alt="" class="tab">
		<img src="/resources/user/images/brand/mo_brand.jpg" alt="" class="mo">
	</div>
</div>
