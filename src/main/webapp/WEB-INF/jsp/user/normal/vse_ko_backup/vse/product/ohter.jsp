<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
    Description : 제품설명
--%>

<div id="contentWrap" class="">
	<h2 id="contentAnchor">콘텐츠 영역</h2>
	<div class="inner product_top" style="padding-bottom:500px">
		<h3 class="tit move">ohter</h3>
		<p class="sub_txt text_center mt40 move move02">보이스캐디의 정확한 측정기술이 그대로 접목된 <br class="mo">MACH 401센서는 <br class="pc"/>어떠한 상황의 샷도 <br class="mo">일관성 있게 거리정보를 안내합니다.</p>
		<p class="keywords_wrap text_center mt40 move move02">#정확도 #모션센싱 #키오스크</p>
		<div class="product_video move move02">
			<!--<img src="/resources/vse/images/subpage/videos_product.jpg">-->
			<!--
			<video controls width="100%">
				<source src="/resources/vse/videos/videos_01.mp4" type="video/webm">
			</video>
			-->
			<iframe class="shadow" style="width:1200px;height:605px;" src="https://www.youtube.com/embed/rZBaH77Sp_E?rel=0&modestbranding=1" title="YouTube embed" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture" allowfullscreen ></iframe>
			
		</div>
	</div>
	<div class="bg_gray tab_wrap vse_pro_wrap" style="overflow:hidden;">
		<div class="inner">
			<div class="vse_pro_hardware">
				<p class="info">
					<em class="icon_pro_more"></em> 버튼을 누르면 해당 상세내용을 확인할 수 있습니다.
				</p>
				<div class="pro_hardware">
					<img src="/resources/vse/images/subpage/vse_pro_hardwareimg.jpg" alt="" />
					<a href="javascript:void(0);" class="btn_pro_more pro_more_01" value="pop_pro_hardware01"><em class="icon_pro_more"></em></a>
					<a href="javascript:;" class="btn_pro_more pro_more_02" value="pop_pro_hardware02"><em class="icon_pro_more"></em></a>
					<a href="javascript:;" class="btn_pro_more pro_more_03" value="pop_pro_hardware03"><em class="icon_pro_more"></em></a>
				</div>
			</div>
			<h3 class="tit">다양한 연습모드</h3>
			<ul class="product_info">
				<li class="active slider-nav">
					<a href="javascript:;">
						<p class="sub_tit">드라이빙 <br />레인지</p>
					</a>
				</li>
				<li class="slider-nav">
					<a href="javascript:;">
						<p class="sub_tit">퍼팅</p>
					</a>
				</li>
				<li class="slider-nav">
					<a href="javascript:;">
						<p class="sub_tit">코스티샷</p>
					</a>
				</li>
				<li class="slider-nav">
					<a href="javascript:;">
						<p class="sub_tit">그린공략</p>
					</a>
				</li>
				<li class="slider-nav">
					<a href="javascript:;">
						<p class="sub_tit">실전코스</p>
					</a>
				</li>
			</ul>
			<div class="product_slide_01">
				<!--1-->
				<div class="slide_type_01">
					<ul class="">
						<li>
							<div class="con">
								<p class="tit"><b>드라이빙 레인지</b>
								</p>
								<div class="sub_txt">
									고퀄리티 실사 그래픽 구현, 클럽별 연습을 통한 샷 데이터 분석
								</div>
							</div>
							<span class="img">
								<img src="/resources/vse/images/subpage/vse_useimg_01.jpg">
							</span>
						</li>
						<li>
							<div class="con">
								<p class="tit">
									<b>퍼팅</b>
								</p>
								<div class="sub_txt">
									그린 스피드, 거리 설정을 통해 다양한 상황에서의 퍼팅감 향상
								</div>
							</div>
							<span class="img">
								<img src="/resources/vse/images/subpage/vse_useimg_02.jpg">
							</span>
						</li>
						<li>
							<div class="con">
								<p class="tit">
									<b>코스티샷</b>
								</p>
								<div class="sub_txt">
									티잉 에어리어에서의 드라이버 티샷 중점 연습을 통한 실전 감각 유지
								</div>
							</div>
							<span class="img">
								<img src="/resources/vse/images/subpage/vse_useimg_03.jpg">
							</span>
						</li>
						<li>
							<div class="con">
								<p class="tit"><b>그린공략</b></p>
								<div class="sub_txt">
									10m~200m까지의 거리 설정을 통한 어프로치, 세컨샷, 써드샷 연습
								</div>
							</div>
							<span class="img">
								<img class="" src="/resources/vse/images/subpage/vse_useimg_04.jpg">
							</span>
						</li>
						<li>
							<div class="con">
								<p class="tit"><b>실전코스</b></p>
								<div class="sub_txt">
									완벽하게 구현된 9개의 코스 지원과 3홀, 9홀, 18홀 선택 및 라운드 가능
								</div>
							</div>
							<span class="img">
								<img class="" src="/resources/vse/images/subpage/vse_useimg_05.jpg">
							</span>
						</li>
					</ul>
				</div>
			</div>
			<!--product_slide_01-->
		</div>
	</div>
	<div class="bg_black pt60 pb60">
		<h3 class="tit">간편하다, 스마트하다 <br class="mo"><img src="/resources/vse/images/com/logo01_png-white-2.png"> 전용앱</h3>
		<p class="sub_txt text_center mt20">
			연습 현황, 스윙영상으로 자신의 플레이를 확인하세요 <br />
			자신의 클럽별 비거리는 매우 중요합니다. 
		</p>
	</div>
	<div class="inner product_slide_02 ">
		<div class="clear slide_type_01">
			<ul>
				<li>
					<div class="con">
						<p class="tit mb40">
							나의 연습현황, <br class="mo">스윙영상 자동저장</br>
							사용 클럽 별 거리 비교 확인
						</p>
						<div class="tab_wrap type01 mb60">
							<ul class="tab_type_01 x_4">
								<li class="active">
									<a href="javascript:;" value="1">메인</a>
								</li>
								<li>
									<a href="javascript:;" value="2">연습현황</a>
								</li>
								<li>
									<a href="javascript:;" value="3">스윙영상</a>
								</li>
								<li>
									<a href="javascript:;" value="4">통계</a>
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
							플레이스토어/앱스토어에서 <br class="mo"><strong>‘VSE’</strong> 검색 후 다운로드
						</p>
						<div class="btn_wrap">
							<a href="https://play.google.com/store/apps/details?id=com.vcinc.vse&hl=ko" target="_blank" class="btn btn_black pl50 pr50"><img class="mr10" src="/resources/vse/images/icon/icon_google.png">Google Play</a><a href="https://apps.apple.com/kr/app/vse/id1549481027" target="_blank" class="btn btn_black pl50 pr50 ml20"><img class="mr10" src="/resources/vse/images/icon/icon_app.png">APP Store</a>
						</div>
					</div>
				</li>
				<li>
					<div class="con">
						<p class="tit mb40">
							나의 연습현황, <br class="mo">스윙영상 자동저장</br>
							사용 클럽 별 거리 비교 확인
						</p>
						<div class="tab_wrap type01 mb60">
							<ul class="tab_type_01 x_4">
								<li>
									<a href="javascript:;" value="1">메인</a>
								</li>
								<li class="active">
									<a href="javascript:;" value="2">연습현황</a>
								</li>
								<li>
									<a href="javascript:;" value="3">스윙영상</a>
								</li>
								<li>
									<a href="javascript:;" value="4">통계</a>
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
							플레이스토어/앱스토어에서 <br class="mo"><strong>‘VSE’</strong> 검색 후 다운로드
						</p>
						<div class="btn_wrap">
							<a href="https://play.google.com/store/apps/details?id=com.vcinc.vse&hl=ko" target="_blank" class="btn btn_black pl50 pr50"><img class="mr10" src="/resources/vse/images/icon/icon_google.png">Google Play</a><a href="https://apps.apple.com/kr/app/vse/id1549481027" target="_blank" class="btn btn_black pl50 pr50 ml20"><img class="mr10" src="/resources/vse/images/icon/icon_app.png">APP Store</a>
						</div>
					</div>
				</li>
				<li>
					<div class="con">
						<p class="tit mb40">
							나의 연습현황, <br class="mo">스윙영상 자동저장</br>
							사용 클럽 별 거리 비교 확인
						</p>
						<div class="tab_wrap type01 mb60">
							<ul class="tab_type_01 x_4">
								<li>
									<a href="javascript:;" value="1">메인</a>
								</li>
								<li >
									<a href="javascript:;" value="2">연습현황</a>
								</li>
								<li class="active">
									<a href="javascript:;" value="3">스윙영상</a>
								</li>
								<li>
									<a href="javascript:;" value="4">통계</a>
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
							플레이스토어/앱스토어에서 <br class="mo"><strong>‘VSE’</strong> 검색 후 다운로드
						</p>
						<div class="btn_wrap">
							<a href="https://play.google.com/store/apps/details?id=com.vcinc.vse&hl=ko" target="_blank" class="btn btn_black pl50 pr50"><img class="mr10" src="/resources/vse/images/icon/icon_google.png">Google Play</a><a href="https://apps.apple.com/kr/app/vse/id1549481027" target="_blank" class="btn btn_black pl50 pr50 ml20"><img class="mr10" src="/resources/vse/images/icon/icon_app.png">APP Store</a>
						</div>
					</div>
				</li>
				<li>
					<div class="con">
						<p class="tit mb40">
							나의 연습현황, <br class="mo">스윙영상 자동저장</br>
							사용 클럽 별 거리 비교 확인
						</p>
						<div class="tab_wrap type01 mb60">
							<ul class="tab_type_01 x_4">
								<li>
									<a href="javascript:;" value="1">메인</a>
								</li>
								<li >
									<a href="javascript:;" value="2">연습현황</a>
								</li>
								<li >
									<a href="javascript:;" value="3">스윙영상</a>
								</li>
								<li class="active">
									<a href="javascript:;" value="4">통계</a>
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
							플레이스토어/앱스토어에서 <br class="mo"><strong>‘VSE’</strong> 검색 후 다운로드
						</p>
						<div class="btn_wrap">
							<a href="https://play.google.com/store/apps/details?id=com.vcinc.vse&hl=ko" target="_blank" class="btn btn_black pl50 pr50"><img class="mr10" src="/resources/vse/images/icon/icon_google.png">Google Play</a><a href="https://apps.apple.com/kr/app/vse/id1549481027" target="_blank" class="btn btn_black pl50 pr50 ml20"><img class="mr10" src="/resources/vse/images/icon/icon_app.png">APP Store</a>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</div>
	<!--product_slide_02-->
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

</div>

<div class="popup_wrap pop_pro_hardware pop_pro_hardware01">
	<div class="popup_top"><a href="javascript:;" class="btn_close"></a></div>
	<div class="hardware_slide">
		<div class="hardware_info_wrap">
			<div class="img">
				<div class="con pc">
					<h4 class="tit">32인치 FHD<br />키오스크 화면</h4>
					<p class="txt">
						비교할 수 없는 세로형 화면으로 스윙을 실제처럼 확인 가능
					</p>
				</div>
				<img src="/resources/vse/images/subpage/pup_vse_hardwarei_01.jpg" alt="" />
			</div>
			<div class="con">
				<div class="mo">
					<h4 class="tit">32인치 FHD<br />키오스크 화면</h4>
					<p class="txt">
						비교할 수 없는 세로형 화면으로 스윙을 실제처럼 확인 가능
					</p>
				</div>
				<h4 class="tit">V. Motion <br />Solution</h4>
				<p class="txt">
					인공지능 동작인식, 솔루션을 통한
					자세분석 기능, 셋업, 헤드 업, 
					체중이동, 스윙플레인, 어깨선 정렬 등
					스윙의 중요요소 체크가능
				</p>
			</div>
		</div>
		<!--hardware_info_wrap-->
		<div class="hardware_info_wrap">
			<div class="img">
				<img src="/resources/vse/images/subpage/pup_vse_hardwarei_01_2.jpg" alt="" />
			</div>
			<div class="con">
				<h4 class="tit">V. Mirror Mode</h4>
				<p class="txt">
					거울모드를 통해 2채널(정면/측면)로
					본인의 스윙을 정확히 확인
					영상 자동 리플레이로 스윙 점검
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
		<div class="hardware_info_wrap">
			<div class="img">
				<img src="/resources/vse/images/subpage/pup_vse_hardwarei_02.jpg" alt="" />
			</div>
			<div class="con">
				<h4 class="tit">초고속 카메라 센서</h4>
				<p class="txt">
					초당 3,800 프레임  초고속 3D
					카메라 센서로 볼/클럽을 측정하여
					정확한 데이터 제공
					빠른 측정으로 임팩트 후 실시간 볼
					움직임 구현
				</p>
			</div>
		</div>
		<!--hardware_info_wrap-->
</div>
<!--end popup_wrap :pop_pro_hardware02-->

<div class="popup_wrap pop_pro_hardware pop_pro_hardware03">
	<div class="popup_top"><a href="javascript:;" class="btn_close"></a></div>
		<div class="hardware_info_wrap">
			<div class="img">
				<img src="/resources/vse/images/subpage/pup_vse_hardwarei_03.jpg" alt="" />
			</div>
			<div class="con">
				<h4 class="tit">듀얼스윙 카메라</h4>
				<p class="txt">
					FHD 120FPS 카메라 탑재로 
					생생한 스윙영상 확인이 가능하고,
					동작인식, 임팩트 슬로우,
					거울모드에 최적화
				</p>
			</div>
		</div>
		<!--hardware_info_wrap-->
</div>
<!--end popup_wrap :pop_pro_hardware03-->

<div class="popup_dim"></div>
<script>
$(function(){
	$('#header .gnb > li').eq(0).addClass('active');
	
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
			