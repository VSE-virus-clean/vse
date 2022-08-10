<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%-- 
    JSP Name : mainMan.jsp
    Description : 메인페이지
--%>
<link type="text/css" rel="stylesheet" href="/resources/user/ui_common/css/jquery_fullpage.css">
<script type="text/javascript" src="/resources/user/ui_common/js/jquery_fullpage.js"></script>

<div class="section section01" data-anchor="section01">
	<div class="inner main_inner">						
		<h4 class="tit02 fade ani01" style="padding-top:20px"><strong>나만의</strong>휴먼 <strong>코로나 안전</strong>지대</h4>
		<h3 class="tit01 fade ani02"><strong>Z-mini</strong></h3>
		<!-- <p class="tit_sub fade ani02"><span>예약 판매 시작!</span></p> -->
		<a href="/shop/virus-clean-lab/view.vc?model=Z-mini" class="btn01 fade ani03"><span>구매하기</span></a>
		<!-- <div class="video fade ani04">
			<iframe width="690" height="390" src="https://www.youtube.com/embed/nWuFq41CdX0" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen></iframe>
		</div> -->
	</div>		
</div>

<div class="section section02" data-anchor="section02">
	<div class="inner">
		<h3 class="tit01 fade ani01"><strong>Z-mini를 켜는 순간</strong></h3>
		<div class="txt_box fade ani02">
			<!-- <h4 class="tit02 "><strong>바이러스 클린 랩</strong>은?</h4> -->
			<p>2억개의 음이온과 양이온을 만들어내는<br class="mo"> 플라즈마 기술로 <br>휴먼 코로나 안전지대를 만들어 줍니다.</p>

		</div>
		<!-- <div class="box_wrap fade ani03">
			<p><strong>B</strong>rand Philosophy</p>
			<ul class="clearfix">
				<li>
					<div class="icon"><img src="/resources/user/images/main/box_icon01.png" alt=""></div>
					<div>
						<p class="tit">Customer</p>
						<p class="info">고객을 위한 가치 창조</p>
					</div>	
				</li>	
				<li>
					<div class="icon"><img src="/resources/user/images/main/box_icon02.png" alt=""></div>
					<div>
						<p class="tit">Connect</p>
						<p class="info">사람과 기술이 함께 <br>어우러지는 가치</p>
					</div>
				</li>	
				<li>
					<div class="icon"><img src="/resources/user/images/main/box_icon03.png" alt=""></div>
					<div>
						<p class="tit">Common</p>
						<p class="info">고객과 회사가 공통의 <br>지향점을 향해 나아가는 가치</p>
					</div>
				</li>	
			</ul>
		</div> -->
	</div>
</div>

<div class="section section03" data-anchor="section03">
	<div class="inner">
		<div class="txt_box">
			<h4 class="tit02 fade ani01"><strong>단 30초로 <br class="mob">안전하게!</strong></h4>
			<P  class="fade ani02">UV-C LED가 바이러스와 세균을 <br>강력하게 살균합니다.</P>
		</div>
	</div>
</div>

<div class="section section04" data-anchor="section04">
	<div class="inner" >
		<div class="txt_box" >
			<h4 class="tit02 fade ani01"><strong>차 안에서도 <br class="mob">걱정 없이</strong></h4>
			<p class="fade ani02">안전하고 쾌적한 공간을 만들어주는  Z-mini</p>
		</div>					
	</div>
</div>

<div class="section section05" data-anchor="section05">
	<div class="inner">
		<div class="txt_box" >
			<h4 class="tit02 fade ani01"><strong>유모차에서도 <br class="mob">안전하게</strong></h4>
			<p  class="fade ani02">소중한 내 아이를 위한 선택, Z-mini</p>
		</div>		
	</div>
</div>
<div class="section section06" data-anchor="section06" >
	<div class="inner">
		<div class="txt_box" >
			<h4 class="tit02 fade ani01"><strong>카페, <br class="mob">음식점에서도</strong></h4>
			<P  class="fade ani02">방심 금물! <br class="mob">안전을 양보하지 마세요.</P>
		</div>	
	</div>
</div>
<div class="section section07 " data-anchor="section07" >
	<div class="inner">
		<div class="txt_box ">
			<h4 class="tit02 fade ani01"><strong>공부에 <br class="mob">집중할 수 있게</strong></h4>
			<p class="fade ani02">마스크로 막을 수 없는 바이러스 불안하셨죠? <br>등교하는 아이를 위한 선택, Z-mini입니다.</p>
			<!-- <a href="/brand.vc" class="more"><span>더 알아보기</span></a> -->
		</div>
	</div>
</div>
<div id="footer" class="section section08"><!-- fp-auto-height -->
	<div class="footer_info">
		<div class="inner">
			<ul class="clearfix">
				<li>
					<div class="tit_wrap">
						<p class="tit">고객센터</p>
						<div class="icon"><img src="/resources/user/ui_common/images/f_icon01.png" alt=""></div>
					</div>
					<div class="right_info">
						<p class="phone">1644-0955</p>	
						<p class="time">평일 09:00 ~ 18:00    <br class="tab"><span class="pc"></span>    점심 12:00 ~ 13:00</p>
						<p class="day">휴무 토, 일요일/공휴일</p>
					</div>
				</li> 
				<li>
					<div class="tit_wrap">
						<p class="tit">방문 및 택배 접수</p>
						<div class="icon"><img src="/resources/user/ui_common/images/f_icon02.png" alt=""></div>
					</div>
					<div class="right_info">
						<p>서울특별시 서초구  <br class="tabMo"> 논현로 145 <br class="pc">	수냐빌딩 5층</p>
					</div>
				</li>
				<li>
					<div class="tit_wrap">
						<p class="tit">기업 및 대량구매 문의</p>
						<div class="icon"><img src="/resources/user/ui_common/images/f_icon03.png" alt=""></div>
					</div>
					<div class="right_info">
						<p>seungji27@vcinc.co.kr</p>
					</div>
				</li>
			</ul>
		</div>
	</div>
	<div class="footer">
		<div class="footer_top">
			<div class="inner clearfix">
				<img src="/resources/user/ui_common/images/footer_logo.png" alt="">
				<div class="right_menu">
					<ul class="clearfix">
						<li><a href="/role/service-rules.vc">이용약관</a></li>
						<li><a href="/role/privacy-protection-policy.vc">개인정보처리방침</a></li>
						<li><a href="/role/denial-of-unauthorized-collecting-of-email-address.vc">이메일무단수집거부</a></li>
					</ul>
				</div>	
			</div>
		</div>
		<div class="footer_bottom">
			<div class="inner">
				<p>(주)브이씨</p>
				<p>
					<em class="pc">사업장소재지 : </em>서울특별시 강남구 논현로 417 (역삼동) 화원빌딩 3,4층<br class="pc"><em class="pc">      I      
					</em>대표자 : 김준오 <br class="pc"><em class="pc">     I      
					</em>대표번호 : 02-538-3100 / FAX : 02-538-3104,5 <br>
					사업자등록번호 : 209-81-39552<em class="pc">      I      
					</em><br class="pc">정보처리담당자 : 이상윤<br class="pc"><em class="pc">      I      
					</em>통신판매업신고 : 제2019-서울강남-03541호
				</p>
			</div>
		</div>
	</div>
</div>

<div id="mask" class="modal_backdrop"  style="z-index:1000"></div>

<script type="text/javascript">
$(function(){
	//scroll - header 
	$('#container').fullpage({
	    anchors: ['section01', 'section02','section03', 'section04', 'section05' ,'section06' , 'section07'],
	    afterLoad: function(anchorLink, index){
	      if(index == 1){
	    	  $('a.top_btn, a.talk_btn').hide();
	      }else{
	    	  $('a.top_btn, a.talk_btn').show();
	      }
	      
	      if(index == 3 || index == 4 || index == 6){
		      $('#heaeder').addClass('on');
		      $('#heaeder .logo img').attr('src', '/resources/user/ui_common/images/sub_logo.png'); 
			  $('#heaeder .right .menu_btn .open').attr('src', '/resources/user/ui_common/images/menu_btn_sub.png'); 
	      }else{
		      $('#heaeder').removeClass('on');
		      $('#heaeder .logo img').attr('src', '/resources/user/ui_common/images/top_logo.png'); 
			  $('#heaeder .right .menu_btn .open').attr('src', '/resources/user/ui_common/images/menu_btn.png'); 
	      }
	    }
	});
});



$(document).ready(function(){
	<c:if test="${not empty popup.list}">
	<c:forEach var="data" items="${popup.list}">
		if(!cookieUtil.getCookie('popup_${data.blcSn}')){
			popOn('${data.blcSn}');
		}
	</c:forEach>
	</c:if>
	
	<c:if test="${not empty banner.list and deviceCode eq 'N'}">
	<c:forEach var="data" items="${banner.list}">
		if(!cookieUtil.getCookie('banner_${data.blcSn}')){
			onTopBanner('${data.blcSn}');
		}
	</c:forEach>
	</c:if>
});


function closeTopBanner(_sn, _day){
	$('.tap_banner').css('display', 'none');
	$('#heaeder').addClass('no_top');
	
	if(_day){
		cookieUtil.setCookie('banner_' + _sn, 'Y', _day);
	}
}

function onTopBanner(_sn){
	$('.tap_banner').css('display', 'block');
	$('#heaeder').addClass('top_b');
}

function popClose(_sn, _day){
	$('.popup_' + _sn).css('display', 'none');
	
	if(_day){
		cookieUtil.setCookie('popup_' + _sn, 'Y', _day);
	}
}

function popOn(_sn, _day){
	$('.popup_' + _sn).css('display', 'block');
}
</script>