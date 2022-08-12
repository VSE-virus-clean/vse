<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
    Description : 인테리어 > 인테리어 사진
--%>

<div id="contentWrap" class="interior">
	<h2 id="contentAnchor">콘텐츠 영역</h2>
	<div class="inner">
		<div class="move top_video">
			<iframe class="shadow" style="width:100%;height:675px;" src="https://www.youtube.com/embed/uk2G2Lis-RI?rel=0&modestbranding=1" title="YouTube embed" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen ></iframe>
		</div>
	
		<ul class="interior_con">
			<li>
				<p>MODERN COLOR</p>
				<span>블랙, 그레이, 화이트, 레드</span>
				<img alt="MODERN COLOR" src="/resources/vse/images/subpage/interior_icon01.png">
			</li>
			<li>
				<p>DURABLE MATERIAL</p>
				<span>내구성이 강한 소재 칼라</span>
				<img alt="MODERN COLOR" src="/resources/vse/images/subpage/interior_icon02.png">
			</li>
			<li>
				<p>SMART DEVICE</p>
				<span>키오스크, 무선충전, 타석연동 패드</span>
				<img alt="MODERN COLOR" src="/resources/vse/images/subpage/interior_icon03.png">
			</li>
		</ul>
	</div>
	<div class="inner">
		<div class="interior_slide slide_type_01">
			<ul>
				<!--<li><img src="/resources/vse/images/subpage/interior_slide_01.jpg" alt="" /></li>-->
				<!--<li><img src="/resources/vse/images/subpage/interior_slide_04.jpg" alt="" /></li>-->
				<li><img src="/resources/vse/images/subpage/210224_vse1851a1.jpg" alt="" /></li>
				<li><img src="/resources/vse/images/subpage/210224_vse1856a1.jpg" alt="" /></li>
				<li><img src="/resources/vse/images/subpage/210224_vse1770a1.jpg" alt="" /></li>
				<li><img src="/resources/vse/images/subpage/210224_vse1729a1.jpg" alt="" /></li>
				<li><img src="/resources/vse/images/subpage/210224_vse1876a1.jpg" alt="" /></li>
				<li><img src="/resources/vse/images/subpage/210224_vse1882a1.jpg" alt="" /></li>
				<li><img src="/resources/vse/images/subpage/210224_vse1886a1.jpg" alt="" /></li>
			</ul>
		</div>
	</div>
</div>

<script>
$(function(){
	$('#header .gnb > li').eq(1).addClass('active');

});
</script>
