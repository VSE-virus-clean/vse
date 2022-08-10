<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%-- 
    JSP Name : customerSupportMan.jsp
    Description : 고객센터
--%>
<link rel="stylesheet" href="/resources/user/css/service.css?v=${cacheParam}">
<style>
.center .map{ height: 500px; width: 100%; -webkit-box-shadow: 5px 5px 5px rgba(0,0,0,0.2);
-moz-box-shadow:  5px 5px 5px rgba(0,0,0,0.2);box-shadow:  5px 5px 5px rgba(0,0,0,0.2);}	

@media (max-width:720px) {
	.center .map {height:69.44vw;}
}

</style>
<div id="container">
	<div class="location_bar">
		<ul class="clearfix inner pcTab">
			<li><a href="/index.vc">Home</a></li>
			<li><a href="#">Service</a></li>
			<li><a href="#">고객센터</a></li>
		</ul>
		<div class="mo inner">
			<a href="" ></a>
		</div>			
	</div>
	<div class="center">
		<div class="inner">
			<h3 class="p_tit01">고객센터</h3>
			<div id="daumRoughmapContainer1612256694296" class="map root_daum_roughmap root_daum_roughmap_landing"></div>
			<div class="way_info">
				<p class="add"><span class="info_tit"><img src="/resources/user/images/service/add_icon.png" alt="">Address</span><span>서울특별시 서초구 논현로 145 수냐빌딩 5층</span></p>
				<p class="add"><span class="info_tit"><img src="/resources/user/images/service/tell_icon.png" alt="">TEL</span><span>1644-0955</span>	</p>
				<p class="add"><span class="info_tit"><img src="/resources/user/images/service/mail_icon.png" alt="">E-Mail</span><span>syj@vcinc.co.kr </span>	</p>
				<p class="add"><span class="info_tit"><img src="/resources/user/images/service/time_icon.png" alt="">TIME</span><span>평일 09:00 ~ 18:00 | 점심 12:00 ~ 13:00 | 휴무 토, 일요일/공휴일</span>	</p>
				
			</div>
			<p class="g_info"><span>※ 내방 고객님께서는 주차장이 협소하오니, 가급적 대중교통 이용을 부탁드립니다.</span></p>

			<ul class="way_slide">
				<li class="way">
					<div class="s_tit">
						<a href="javascript:;"><img src="/resources/user/images/service/subway.png" alt=""><span>지하철 + </span><img src="/resources/user/images/service/walk.png" alt=""><span>도보 이용시</span></a>
					</div>
					<div class="s_cont">
						<div class="s_cont_inner">
							<ul>
								<li> <span class="line3">3호선</span> <p>&nbsp;매봉역 4번 출구</p>  <p>도보 10분</p></li>
								<li> <span class="line3">3호선</span> <span class="linered">신분당</span> <p>&nbsp;매봉역 4번 출구</p>  <p>도보 10분</p></li>
							</ul>
						</div>
						
					</div>
				</li>
				<li class="way">
					<div class="s_tit">
						<a href="javascript:;"><img src="/resources/user/images/service/subway.png" alt=""><span>지하철 + </span> <img src="/resources/user/images/service/bus.png" alt=""><span>버스 +</span><img src="/resources/user/images/service/walk.png" alt=""><span>도보 이용시</span></a>
					</div>
					<div class="s_cont">
						<div class="s_cont_inner">
							<ul>
								<li> <span class="line3">3호선</span> <span class="linered">신분당</span> <p>&nbsp;양재역 5번 출구</p></li>
								<li> <p>&nbsp;마을버스 강남10, 서초21</p> <p> 원불교 정류장 하차 </p> <p> 도보 1분</p></li>
							</ul>
						</div>
						
					</div>
				</li>
			</ul>

		</div>
	</div>
</div>
	
<script charset="UTF-8" class="daum_roughmap_loader_script" src="https://ssl.daumcdn.net/dmaps/map_js_init/roughmapLoader.js"></script>
<script charset="UTF-8">
	new daum.roughmap.Lander({
		"timestamp" : "1612256694296",
		"key" : "2494k",
		"mapWidth" : "1320",
		"mapHeight" : "500"
	}).render();

	$(document).ready(function(){
		$('.s_tit a').on('click',function(){
			var target= $(this).parents('.way');
			if(target.hasClass('active')){
				target.removeClass('active');
				target.find('.s_cont').slideUp();
			}else{
				target.addClass('active');
				target.find('.s_cont').slideDown();
			}
		})

		
	});
</script>	
