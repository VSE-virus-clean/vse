<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
    Description : VSE 메인화면
--%>

<div id="contentWrap" class="" style="padding-top:0;">
	<h2 id="contentAnchor">콘텐츠 영역</h2>
	<div class=" _sub_sec2 section01" style="background-image: url(/resources/vse/images/subpage/Residential_Installs.jpg)">
		<div class="_txt_centers" style="text-align: center; align-items: center;">
			<h3 style="margin-bottom: 2rem">Your Complete Station for Play and Practice.</h3>
			<p class="">
				<a href="/product/vse1.vse" class="btn2 btn_round" style="margin: 1rem 0;">CONTACT SALES</a>
				<br>
				You can raise the quality of practice with VSE
				<br>
				And experience a now play with Voce Caddie.
			</p>
		</div>
		<p class="scroll_swing up">
			<img src="/resources/vse/images/com/icon_swing.png" alt="마우스 스크롤 이미지">
			<span>scroll down</span>
		</p>
	</div>
	
	<div>
		<div class="video_wrap" style="margin-bottom: -5px;">
			<video id="MainVideo01" style="min-width:1200px;" autoplay="" loop="" muted="" playsinline="">
				<source src="/resources/vse/videos/videos_01.mp4" type="video/mp4">
			</video>
		</div>
	</div>
	
	<div class="bg_gray tab_wrap vse_pro_wrap" style="overflow:hidden;">
		<div class="inner">
			<div class="vse_pro_hardware">
				<p class="info">
					<em class="icon_pro_more"></em> You can check the details by pressing the button.
				</p>
				<div class="pro_hardware">
					<img src="/resources/vse/images/subpage/vse_pro_hardwareimg.jpg" alt="">
					<a href="javascript:void(0);" class="btn_pro_more pro_more_01" value="pop_pro_hardware01">
						<em class="icon_pro_more"></em>
						<span class="mark">SENSOR</span>
					</a>
					<a href="javascript:;" class="btn_pro_more pro_more_02" value="pop_pro_hardware02">
						<em class="icon_pro_more"></em>
						<span class="mark">Swing Cam</span>
					</a>
					<a href="javascript:;" class="btn_pro_more pro_more_03" value="pop_pro_hardware03">
						<em class="icon_pro_more"></em>
						<span class="mark">KIOSK</span>
					</a>
				</div>
			</div>
			<h3 class="tit">Practice Mode</h3>
			<ul class="product_info">
				<li class="slider-nav">
					<a href="javascript:;">
						<p class="sub_tit">Driving<br>Range</p>
					</a>
				</li>
				<li class="slider-nav">
					<a href="javascript:;">
						<p class="sub_tit">Putting</p>
					</a>
				</li>
				<li class="slider-nav active">
					<a href="javascript:;">
						<p class="sub_tit">Attacking<br>Greens</p>
					</a>
				</li>
				<li class="slider-nav">
					<a href="javascript:;">
						<p class="sub_tit">Course<br>Tee Shot</p>
					</a>
				</li>
				<li class="slider-nav">
					<a href="javascript:;">
						<p class="sub_tit">Course<br>Close to Real</p>
					</a>
				</li>
			</ul>
			<div class="product_slide_01">
				<div class="slide_type_01">
					<ul>
						<li>
							<div class="con">
								<p class="tit">
									<b>Driving Range</b>
								</p>
								<div class="sub_txt">
									Real-life graphic presentation enables practice by club
								</div>
							</div>
							<span class="img">
								<img src="/resources/vse/images/subpage/vse_useimg_01.jpg">
							</span>
						</li>
						<li>
							<div class="con">
								<p class="tit">
									<b>Putting</b>
								</p>
								<div class="sub_txt">
									Optimization of distance practice according to green speed
								</div>
							</div>
							<span class="img">
								<img src="/resources/vse/images/subpage/vse_useimg_02.jpg">
							</span>
						</li>
						<li>
							<div class="con">
								<p class="tit">
									<b>Attacking Greens</b>
								</p>
								<div class="sub_txt">
									Adjustable direction and distance, offers practice mode 
									optimized for realistic approach situations.
								</div>
							</div>
							<span class="img">
								<img class="" src="/resources/vse/images/subpage/vse_useimg_04.jpg">
							</span>
						</li>
						<li>
							<div class="con">
								<p class="tit">
									<b>Course Tee Shot</b>
								</p>
								<div class="sub_txt">
									Golfers can practice tee shots on the course
								</div>
							</div>
							<span class="img">
								<img src="/resources/vse/images/subpage/vse_useimg_03.jpg">
							</span>
						</li>
						<li>
							<div class="con">
								<p class="tit">
									<b>Course Close to Real</b>
								</p>
								<div class="sub_txt">
									Effectively used when you want to practice or review
									the course before or after a round.
								</div>
							</div>
							<span class="img">
								<img class="" src="/resources/vse/images/subpage/vse_useimg_05.jpg">
							</span>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
	
	<div class="bg_black pt60 pb60" style="display: flex; flex-direction: column; align-items: center;">
		<h3 class="tit" style="color:#b50021;">VSE APP</h3>
		<p class="sub_txt mt20" style="width:45%">
			Easy automatic login and checked anytime and anywhere, VSE app saves up to 
			5 swing videos a day, so you can directly check the changing swing. Pitch analysis, 
			various statistics, and analysis data enable it to identify the shortcomings of golfers 
			for customized drills.
		</p>
	</div>
	
	<div class="inner product_slide_02 ">
		<div class="clear slide_type_01">
			<ul>
				<li>
					<div class="con">
						<p class="tit mb40">
							<!-- 
							나의 연습현황, <br class="mo">스윙영상 자동저장</br>
							사용 클럽 별 거리 비교 확인
							-->
							My Practice Report, Swing Video Auto-Save, Check the distance by club
						</p>
						<div class="tab_wrap type01 mb60">
							<ul class="tab_type_01 x_5">
								<li class="active">
									<a href="javascript:;" value="1">Main<br></a>
								</li>
								<li>
									<a href="javascript:;" value="2">Swing analysis<br></a>
								</li>
								<li>
									<a href="javascript:;" value="3">Swing Stats<br></a>
								</li>
								<li>
									<a href="javascript:;" value="4">Cumulative drills</a>
								</li>
								<li>
									<a href="javascript:;" value="5">Analysis by Drill Mode</a>
								</li>
							</ul>
							<ul class="tab_con">
								<li class="tab_list active">연습횟수와 타수,  클럽별 거리 확인으로 나의 실력 확인 </li>
							</ul>
						</div>
						<span class="img">
							<img class="shadow" src="/resources/vse/images/subpage/productapp_slide_01.jpg">
						</span>
						<p class="sub_txt text_center mb30">
							<!-- 플레이스토어/앱스토어에서 <br class="mo"><strong>‘VSE’</strong> 검색 후 다운로드 -->
							Download Google Play store / App store
						</p>
						<div class="btn_wrap">
							<a href="https://play.google.com/store/apps/details?id=com.vcinc.vse&hl=ko" target="_blank" class="btn btn_black pl50 pr50"><img class="mr10" src="/resources/vse/images/icon/icon_google.png">Google Play</a><a href="https://apps.apple.com/kr/app/vse/id1549481027" target="_blank" class="btn btn_black pl50 pr50 ml20"><img class="mr10" src="/resources/vse/images/icon/icon_app.png">APP Store</a>
						</div>
					</div>
				</li>
				<li>
					<div class="con">
						<p class="tit mb40">
							My Practice Report, Swing Video Auto-Save, Check the distance by club
						</p>
						<div class="tab_wrap type01 mb60">
							<ul class="tab_type_01 x_5">
								<li>
									<a href="javascript:;" value="1">Main</a>
								</li>
								<li class="active">
									<a href="javascript:;" value="2">Swing analysis</a>
								</li>
								<li>
									<a href="javascript:;" value="3">Swing Stats</a>
								</li>
								<li>
									<a href="javascript:;" value="4">Cumulative drills</a>
								</li>
								<li>
									<a href="javascript:;" value="5">Analysis by Drill Mode</a>
								</li>
							</ul>
							<ul class="tab_con">
								<li class="tab_list active">회차별 연습현황 확인으로 연습과정 기록</li>
							</ul>
						</div>
						<span class="img">
							<img class="shadow" src="/resources/vse/images/subpage/productapp_slide_02.jpg">
						</span>
						<p class="sub_txt text_center mb30">
							Download Google Play store / App store
						</p>
						<div class="btn_wrap">
							<a href="https://play.google.com/store/apps/details?id=com.vcinc.vse&hl=ko" target="_blank" class="btn btn_black pl50 pr50"><img class="mr10" src="/resources/vse/images/icon/icon_google.png">Google Play</a><a href="https://apps.apple.com/kr/app/vse/id1549481027" target="_blank" class="btn btn_black pl50 pr50 ml20"><img class="mr10" src="/resources/vse/images/icon/icon_app.png">APP Store</a>
						</div>
					</div>
				</li>
				<li>
					<div class="con">
						<p class="tit mb40">
							My Practice Report, Swing Video Auto-Save, Check the distance by club
						</p>
						<div class="tab_wrap type01 mb60">
							<ul class="tab_type_01 x_5">
								<li>
									<a href="javascript:;" value="1">Main</a>
								</li>
								<li>
									<a href="javascript:;" value="2">Swing analysis</a>
								</li>
								<li class="active">
									<a href="javascript:;" value="3">Swing Stats</a>
								</li>
								<li>
									<a href="javascript:;" value="4">Cumulative drills</a>
								</li>
								<li>
									<a href="javascript:;" value="5">Analysis by Drill Mode</a>
								</li>
							</ul>
							<ul class="tab_con">
								<li class="tab_list active">1일 5개 영상세트(정면/측면)가 자동 저장되어 스윙 변화 확인</li>
							</ul>
						</div>
						<span class="img">
							<img class="shadow" src="/resources/vse/images/subpage/productapp_slide_03.jpg">
						</span>
						<p class="sub_txt text_center mb30">
							Download Google Play store / App store
						</p>
						<div class="btn_wrap">
							<a href="https://play.google.com/store/apps/details?id=com.vcinc.vse&hl=ko" target="_blank" class="btn btn_black pl50 pr50"><img class="mr10" src="/resources/vse/images/icon/icon_google.png">Google Play</a><a href="https://apps.apple.com/kr/app/vse/id1549481027" target="_blank" class="btn btn_black pl50 pr50 ml20"><img class="mr10" src="/resources/vse/images/icon/icon_app.png">APP Store</a>
						</div>
					</div>
				</li>
				<li>
					<div class="con">
						<p class="tit mb40">
							My Practice Report, Swing Video Auto-Save, Check the distance by club
						</p>
						<div class="tab_wrap type01 mb60">
							<ul class="tab_type_01 x_5">
								<li>
									<a href="javascript:;" value="1">Main</a>
								</li>
								<li>
									<a href="javascript:;" value="2">Swing analysis</a>
								</li>
								<li>
									<a href="javascript:;" value="3">Swing Stats</a>
								</li>
								<li class="active">
									<a href="javascript:;" value="4">Cumulative drills</a>
								</li>
								<li>
									<a href="javascript:;" value="5">Analysis by Drill Mode</a>
								</li>
							</ul>
							<ul class="tab_con">
								<li class="tab_list active">사용클럽의 거리, 방향성 확인을 통해 실력 확인 가능</li>
							</ul>
						</div>
						<span class="img">
							<img class="shadow" src="/resources/vse/images/subpage/productapp_slide_04.jpg">
						</span>
						<p class="sub_txt text_center mb30">
							Download Google Play store / App store
						</p>
						<div class="btn_wrap">
							<a href="https://play.google.com/store/apps/details?id=com.vcinc.vse&hl=ko" target="_blank" class="btn btn_black pl50 pr50"><img class="mr10" src="/resources/vse/images/icon/icon_google.png">Google Play</a><a href="https://apps.apple.com/kr/app/vse/id1549481027" target="_blank" class="btn btn_black pl50 pr50 ml20"><img class="mr10" src="/resources/vse/images/icon/icon_app.png">APP Store</a>
						</div>
					</div>
				</li>
				<li>
					<div class="con">
						<p class="tit mb40">
							My Practice Report, Swing Video Auto-Save, Check the distance by club
						</p>
						<div class="tab_wrap type01 mb60">
							<ul class="tab_type_01 x_5">
								<li>
									<a href="javascript:;" value="1">Main<br></a>
								</li>
								<li>
									<a href="javascript:;" value="2">Swing analysis<br></a>
								</li>
								<li>
									<a href="javascript:;" value="3">Swing Stats<br></a>
								</li>
								<li>
									<a href="javascript:;" value="4">Cumulative drills</a>
								</li>
								<li class="active">
									<a href="javascript:;" value="5">Analysis by Drill Mode</a>
								</li>
							</ul>
							<ul class="tab_con">
								<li class="tab_list active">Analysis by Drill Mode</li>
							</ul>
						</div>
						<span class="img">
							<img class="shadow" src="/resources/vse/images/subpage/productapp_slide_05.jpg">
						</span>
						<p class="sub_txt text_center mb30">
							Download Google Play store / App store
						</p>
						<div class="btn_wrap">
							<a href="https://play.google.com/store/apps/details?id=com.vcinc.vse&hl=ko" target="_blank" class="btn btn_black pl50 pr50"><img class="mr10" src="/resources/vse/images/icon/icon_google.png">Google Play</a><a href="https://apps.apple.com/kr/app/vse/id1549481027" target="_blank" class="btn btn_black pl50 pr50 ml20"><img class="mr10" src="/resources/vse/images/icon/icon_app.png">APP Store</a>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</div>
	
	<%-- 
	<div class="inner pb100 pt100">
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
	--%>
	
	<div class="_sub_sec_w _up_down_padding">
		<div class="inner">
			<h3 class="sub_tit text_center">CHOOSE YOUR SPACE</h3>
			<p class="sub_tit text_center">Transform Your Space With VSE.</p>
		</div>
	</div>
	<div class="_sub_sec_w _pdb110">
		<div class="inner _sub_sec_mo">
			<div class="_items _sub_sec" style="background-image: url(/resources/vse/images/subpage/Residential_Installs.jpg)">
				<div class="overlay">
					<div class="txt_area">
						<p class="tit"><b>RESIDENTIAL INSTALLS</b></p>
						<div class="sub_txt">
							Practice golf from your home in your comfort zone
						</div>
					</div>
					<a href="/main/support.vse" class="btn2 btn_round" style="margin: 0.7rem 0;">LEARN MORE</a>			
				</div>
			</div>
			<div class="_items _sub_sec" style="background-image: url(/resources/vse/images/subpage/Residential_02.jpg)">
				<div class="overlay">
					<div class="txt_area">
						<p class="tit"><b>Academy or Practice Range Installs</b></p>
						<div class="sub_txt">
							Take your students to the next level with the VSE.
						</div>
					</div>
					<a href="/main/support.vse" class="btn2 btn_round" style="margin: 0.7rem 0;">LEARN MORE</a>			
				</div>
			</div>
			<div class="_items _sub_sec" style="background-image: url(/resources/vse/images/subpage/Residential_03.jpg)">
				<div class="overlay">
					<div class="txt_area">
						<p class="tit"><b>Sports Lounge Installs</b></p>
						<div class="sub_txt">
							Entertain your members in the sports lounge with VSE.
						</div>
					</div>
					<a href="/main/support.vse" class="btn2 btn_round" style="margin: 0.7rem 0;">LEARN MORE</a>			
				</div>
			</div>
			<div class="_items _sub_sec" style="background-image: url(/resources/vse/images/subpage/Residential_Installs.jpg)">
				<div class="overlay">
					<div class="txt_area">
						<p class="tit"><b>Golf Club & Golf Facility</b></p>
						<div class="sub_txt">
							VSE can enhance your golf life to another level.
						</div>
					</div>
					<a href="/main/support.vse" class="btn2 btn_round" style="margin: 0.7rem 0;">LEARN MORE</a>			
				</div>
			</div>
		</div>
	</div>
		
</div>

<div class="popup_wrap pop_pro_hardware pop_pro_hardware01">
	<div class="popup_top"><a href="javascript:;" class="btn_close"></a></div>
	<div class="hardware_slide">
		<div class="hardware_info_wrap">
			<div class="img">
				<img src="/resources/vse/images/subpage/pup_vse_hardwarei_01_2.jpg" alt="" />
			</div>
			<div class="con">
				<h4 class="tit">32-inch FHD Kiosk Screen</h4>
				<p class="txt">
					Full body swing video in V-Mirror Mode (mirror mode)
					presents 3D analysis of golfer's swing in 2 channels.
					(front, side)
				</p>
			</div>
		</div>
		<!--hardware_info_wrap-->
		<div class="hardware_info_wrap">
			<div class="img">
				<img src="/resources/vse/images/subpage/pup_vse_hardwarei_01.jpg" alt="" />
			</div>
			<div class="con">
				<h4 class="tit">V. Motion Solution</h4>
				<p class="txt">
					3D analysis, intuitive posture correction, and swing
					correction are possible through the drawing tool.
				</p>
			</div>
		</div>
		<!--hardware_info_wrap-->
	</div>
	<!--hardware_slide-->
</div>
<!--end popup_wrap :pop_pro_hardware01-->


<div class="popup_wrap pop_pro_hardware pop_pro_hardware02">
	<div class="popup_top"><a href="javascript:;" class="btn_close"></a></div>
	<div class="hardware_slide">
		<div class="hardware_info_wrap">
			<div class="img">
				<img src="/resources/vse/images/subpage/pup_vse_hardwarei_02.jpg" alt="" />
			</div>
			<div class="con">
				<h4 class="tit">Ultrafast Camera Sensor - Mach 401 Sensor</h4>
				<p class="txt">
					3D camera sensor provides accurate information for
					each club/swing and measurement immediately after
					swing improves usability.
				</p>
			</div>
		</div>
		<!--hardware_info_wrap-->
		<div class="hardware_info_wrap">
			<div class="img">
				<img src="/resources/vse/images/subpage/pup_vse_hardwarei_02.jpg" alt="" />
			</div>
			<div class="con">
				<h4 class="tit">Ball Tracking</h4>
				<p class="txt">
					The actual spin amount is measured with a dedicated
					marked ball and two high-speed camera sensors.
					Various trajectories and miss shots accurately measured.
				</p>
			</div>
		</div>
		<!--hardware_info_wrap-->
	</div>	
</div>
<!--end popup_wrap :pop_pro_hardware02-->

<div class="popup_wrap pop_pro_hardware pop_pro_hardware03">
	<div class="popup_top"><a href="javascript:;" class="btn_close"></a></div>
		<div class="hardware_info_wrap">
			<div class="img">
				<img src="/resources/vse/images/subpage/pup_vse_hardwarei_03.jpg" alt="" />
			</div>
			<div class="con">
				<h4 class="tit">Dual Swing Cam</h4>
				<p class="txt">
					Equipped with the top-of-the-line camera with the
					highest specifications available for golf simulators,
					VSE provides motion recognition and impact slow
					motion functions.
				</p>
			</div>
		</div>
		<!--hardware_info_wrap-->
</div>
<!--end popup_wrap :pop_pro_hardware03-->

<div class="popup_dim"></div>
<script>
$(function(){
	
	$('.popup_wrap .btn_close').on('click',function(){
		$('.popup_wrap,.popup_dim').hide();	
	});
	//pop 내부 슬라이드 
	const hardwareSlide = $('.hardware_slide');
	hardwareSlide.slick({
		slide:'div',
		arrows : false,
		dots : true, 
	});
	
	$('.btn_pro_more').on('click',function(){
		const proMorePup = $(this).attr('value');
		console.log(proMorePup);
		$('.popup_wrap').each(function(event){
			if($(this).hasClass(proMorePup)){
				$('.popup_dim').show();
				$(this).show();	
			}
		});
		hardwareSlide.slick('setPosition');//pop 내부 슬라이드 틀어짐 방지
	});
	
});
</script>
			