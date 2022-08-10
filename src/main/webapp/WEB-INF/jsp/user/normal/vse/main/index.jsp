<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
    Description : 메인페이지
--%>


<script>

	$(document).ready(function(){
		var headerHeight =$('.header_wrap').height();
		console.log(headerHeight)
		$('.btn_section05').click(function(){
			//let offset = $('#section05').offset(); //선택한 태그의 위치를 반환
			let offset = $('#section05').offset().top
			let offsetT = offset - headerHeight;
	        $('html').animate({scrollTop : offsetT}, 800);
		});

	});

</script>

<style>
p.vsebusiness-image img { width:100%; }
</style>
<div id="contentWrap" class="main">
	<h2 id="contentAnchor">콘텐츠 영역</h2>
	<div class="section01 section">
		<div class="video_wrap">
			<video id="MainVideo01" style="min-width:1200px;" autoplay loop muted  playsinline>
				<source src="/resources/vse/videos/videos_01.mp4" type="video/mp4">
			</video>
		</div>
		<div class="video_text up move">
			<h3 class="">‘VSE와 세로보다’<br />새로운 창업을 경험하다</h3>
			<a href="javascript:;" id="MAIN-STARTUP" class="btn btn_round btn_section05">창업상담</a>
		</div>
		<p class="scroll_swing up">
			<img src="/resources/vse/images/com/icon_swing.png" alt="마우스 스크롤 이미지" />
			<span>scroll down</span>
		</p>
	</div>
	
	<!-- main_contant -->
	<div class="main_contant">
		<!--section01-->
		<div class="section02 section">
			<div class="inner">
				<h3 class="tit"><img src="/resources/vse/images/com/logo01_png-full.png" alt="vse voice caddie" style ="height:80px" /></h3>
				<p class="sub_tit text_center ">GOLF EXPERIENCE, ADVANCED</p>
				<p class="sub_txt text_center mt40">보이스캐디의 정확함이 그대로 <br />이제는 쉽게 영상으로 골프를 즐기세요.   </p>
			</div>
		</div>
		<!--section02-->
		<div class="section03 section">
			<div class="inner">
				<div class="con text_center">
					<h3 class="tit move">매출의 차이를 만드는 <br />보이스캐디 VSE의 인테리어</h3>
					<a href="/interior.vse" class="btn btn_round move move02">자세히보기</a>
				</div>
			</div>
		</div>
		<!--section03-->
		<div class="section04 section ">
			<div class="inner">
				<h3 class="tit move">NO 로열티! NO 가맹비!</h3>
				<p class="sub_txt text_center mt30 move move02">창업부터 프로섭외 운영노하우까지 밀착 서비스를 제공합니다.  <br/><strong>골프를 모르셔도 OK!</strong></p>
				<div class="process">
					<ul>
						<li>매출증대<br /> 밀착 컨설팅</li>
						<li class="process_round process_tit"><img src="/resources/vse/images/com/logo01_png-white-2.png" alt="vse심볼이미지" style="width:60%;height:auto;"  /></li>
						<li>프로섭외 /<br />보이스캐디 제품대여</li>
					</ul>
					<ul>
						<li class="process_round process_01"><img src="/resources/vse/images/main/icon_process_01.png" alt="가맹주 아이콘" /><br/>가맹주</li>
						<li class="">타석 연동 매장관리 프로그램</li>
						<li class="process_round process_02"><img src="/resources/vse/images/main/icon_process_02.png" alt="매장 아이콘" /><br/>매장</li>
					</ul>
				</div>
			</div>
		</div>
		<!--section04-->
		<div class="section05 section" id="section05">
			<div class="tab_wrap inner">
				<h3 class="tit move move01">보이스캐디 VSE로<br />쉬운 창업</h3>
				<ul class="tab_type_01 tab_btn x_2">
					<li class="active" style="width:100% !important"><a href="javascript:;">창업상담</a></li>	
					<!--<li class=""><a href="javascript:;">사업설명회</a></li>	-->
				</ul>
				<div class="tab_con mt40">
					<div class="tab_list active">
						<h4 class="sub_tit">
			     			전화상담 : 1544 - 4667
						</h4>
						<p class="sub_txt">평일 09 : 00 ~ 18 : 00</p>
						<h3 class="sub_tit mt40">창업상담 신청</h3>
						<p class="sub_txt">신청서를 작성해주시면 빠른 시일내로 연락드리겠습니다.</p>
						<div class="table_type_01 mt20">
							<form name="mainForm1" method="post" action="/apply/register.vse" enctype="multipart/form-data" onsubmit="var rtn = formSubmitObj1.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
							<input type="hidden" name="lgrpCd" value="BUSINESS" />
								<table>
									<colgroup>
										<col style="width:180px;" />
										<col style="width:auto"/>
									</colgroup>
									<tbody>
										<tr>
											<th><label for="rgstName">이름 <span class="color_red">*</span></label></th>
											<td class="pr0">
												<p class="x_1">
													<input type="text" id="rgstName" name="rgstName" maxlength="100" title="이름" value="" />
												</p>
											</td>
										</tr>
										<tr>
											<th><label for="rgstHp">연락처 <span class="color_red">*</span></label></th>
											<td class="pr0">
												<p class="x_1">
													<input type="text" id="rgstHp" name="rgstHp" class="numOnly" maxlength="100" title="연락처" value="" />
												</p>
											</td>
										</tr>
										<tr>
											<th><label for=item1>직업 <span class="color_red">*</span></label></th>
											<td class="pr0">
												<p class="x_1">
													<select name="item1" class="select_type_01" id="item1" title="직업">
														<option value="">선택해주세요.</option>
														<option value="프로">프로</option>
														<option value="일반인">일반인</option>
													</select>
												</p>
											</td>
										</tr>
										<tr>
											<th><label for="dist1">창업지역 <span class="color_red">*</span></label></th>
											<td class="pr0">
												<p class="x_2">
													<select name="dist1" class="select_type_01" id="dist1" title="창업지역">
														<option value="">시/도 선택</option>
													</select>
													<select name="dist2" class="select_type_01" title="창업지역">
														<option value="">시/군/구 선택</option>
													</select>
												</p>
											</td>
										</tr>
										<tr>
											<th><label for="item2">인지경로 <span class="color_red">*</span></label></th>
											<td class="pr0">
												<p class="x_1">
													<select name="blcSbc1" class="select_type_01" id="item2" title="인지경로">
														<option value="">선택해주세요.</option>
														<option value="TV광고">TV광고</option>
														<option value="인터넷 광고">인터넷 광고</option>
														<option value="인터넷 검색">인터넷 검색</option>
														<option value="우편">우편</option>
													</select>
												</p>
											</td>
										</tr>
										<tr>
											<th><label for="CounselingReason">창업사유 <span class="color_red">*</span></label></th>
											<td class="pr0">
												<p class="x_1">
													<select name="blcSbc2" class="select_type_01" id="CounselingReason">
														<option value="">선택해주세요.</option>
														<option value="신규창업(투잡)">신규창업(투잡)</option>
														<option value="기존 타석 스크린 교체">기존 타석 스크린 교체</option>
														<option value="은퇴 후 창업">은퇴 후 창업</option>
													</select>
												</p>
											</td>
										</tr>
										<!-- 2차범위 -->
										<tr>
											<th><label for="CounselingHope">희망타석</label></th>
											<td class="pr0">
												<p class="x_1">
													<select  name="item3" class="select_type_01" id="CounselingHope">
														<option value="">선택해주세요.</option>
														<option value="1-4개">1-4개</option>
														<option value="5개-15개">5개-15개</option>
														<option value="16개 이상">16개 이상</option>
													</select>
												</p>
											</td>
										</tr>
									</tbody>
								
								</table>
								<div class="privacy_wrap flex-center mt10">
									<p class="mb5"><em class="mr10">개인정보 수집 안내</em><span class="color_red sub_txt_s">* 아래 약관을 읽은 후 이용가능합니다.</span></p>
									<div id="CounselingprivacyScroll" class="privacy_wrap_info " style="resize:none;">
										<%@ include file="/WEB-INF/jsp/user/normal/vse/common/include/inc_privateTextarea.jsp" %>
									</div>	
								</div>
								<div class="form_box ckinfo_wrap mt15" id="CounselingInfoRCkWarp">
									<label for="CounselingInfoRCk" class="ck_type_01">
										<input type="checkbox" id="CounselingInfoRCk" name="agreePersonal" class=""/><span class="mr10"></span><b class="color_red">[필수]</b> 개인정보 수집안내 동의 및 안내
									</label>
								</div>
								<div class="btn_wrap mt60">
									<a href="#" class="btn wd_250 submit">신청하기</a> <a href="https://www.vse.co.kr/startups.vse" class="btn wd_250 ml20">가맹안내 확인</a>
								</div>
							</form>
						</div>
						<!--table_type_01-->

					</div>
					<!--tab_con 01-->
					<div class="tab_list">
						<form name="mainForm2" method="post" action="/apply/register.vse" enctype="multipart/form-data" onsubmit="var rtn = formSubmitObj2.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
						<input type="hidden" name="lgrpCd" value="PRESENTATION" />
							<p class="x_1" style="padding:0 25px;">
								<select name="mgrpCd" class="select_type_01">
									<option value="">코로나 대응 4단계에서 완화될 시 설명회를 진행하겠습니다.</option>
									
									<c:forEach items="${result.list}" var="data" varStatus="i">
										<option value="${data.blcTitl}" data-sn="${data.blcSn}">${data.blcTitl}</option>
									</c:forEach>
								</select>
							</p>
							
							<c:forEach items="${result.list}" var="data" varStatus="i">
								<div id="sn_${data.blcSn}" class="vsebusinessInfo" style="display:none;">
									<h3 class="sub_tit mt40">사업설명회 정보</h3>
									<div class="map_wrap mt10">
										<div class="table_type_01 ">
											<table>
												<colgroup>
													<col style="width:100px;">
													<col style="width:auto">
												</colgroup>
												<tbody>
													<tr>
														<th class="pr0">주소</th>
														<td class="color_gray5">${data.blcSbc1}</td>
													</tr>
													<tr>
														<th class="pr0">연락처</th>
														<td class="color_gray5">${data.blcSbc2}</td>
													</tr>
													<tr>
														<th class="pr0">운영시간</th>
														<td class="color_gray5">${data.blcSbc3}</td>
													</tr>
													<tr>
														<th class="pr0">시설</th>
														<td class="color_gray5">${data.blcSbc4}</td>
													</tr>
												</tbody>	
											</table>
										</div>
										<p class="mt10 vsebusiness-image">
											${function:printImageFileByList(data.fileSn, data.lgrpCd, data.filNm, data.filNm)}
										</p>
									</div>
								</div>
							</c:forEach>
							
							<h3 class="sub_tit mt40">사업설명회 신청</h3>
							<p class="sub_txt">신청서를 작성해주시면 빠른 시일내로 연락드리겠습니다.</p>
							<div class="table_type_01 mt20">
								<table>
									<colgroup>
										<col style="width:180px;" />
										<col style="width:auto"/>
									</colgroup>
									<tbody>
										<tr>
											<th><label for="rgstName">이름 <span class="color_red">*</span></label></th>
											<td class="pr0">
												<p class="x_1">
													<input type="text" id="rgstName" name="rgstName" maxlength="100" title="이름" value="" />
												</p>
											</td>
										</tr>
										<tr>
											<th><label for="rgstHp">연락처 <span class="color_red">*</span></label></th>
											<td class="pr0">
												<p class="x_1">
													<input type="text" id="rgstHp" name="rgstHp" class="numOnly" maxlength="100" title="연락처" value="" />
												</p>
											</td>
										</tr>
										<tr>
											<th><label for=item1>직업 <span class="color_red">*</span></label></th>
											<td class="pr0">
												<p class="x_1">
													<select name="item1" class="select_type_01" id="item1" title="직업">
														<option value="">선택해주세요.</option>
														<option value="프로">프로</option>
														<option value="일반인">일반인</option>
													</select>
												</p>
											</td>
										</tr>
										<tr>
											<th><label for="dist1">창업지역 <span class="color_red">*</span></label></th>
											<td class="pr0">
												<p class="x_2">
													<select name="dist1" class="select_type_01" id="dist1" title="창업지역">
														<option value="">시/도 선택</option>
													</select>
													<select name="dist2" class="select_type_01" title="창업지역">
														<option value="">시/군/구 선택</option>
													</select>
												</p>
											</td>
										</tr>
										<tr>
											<th><label for="item2">인지경로 <span class="color_red">*</span></label></th>
											<td class="pr0">
												<p class="x_1">
													<select name="blcSbc1" class="select_type_01" id="item2" title="인지경로">
														<option value="">선택해주세요.</option>
														<option value="TV광고">TV광고</option>
														<option value="인터넷 광고">인터넷 광고</option>
														<option value="인터넷 검색">인터넷 검색</option>
														<option value="우편">우편</option>
													</select>
												</p>
											</td>
										</tr>
										<tr>
											<th><label for="CounselingReason">창업사유 <span class="color_red">*</span></label></th>
											<td class="pr0">
												<p class="x_1">
													<select name="blcSbc2" class="select_type_01" id="CounselingReason">
														<option value="">선택해주세요.</option>
														<option value="신규창업(투잡)">신규창업(투잡)</option>
														<option value="기존 타석 스크린 교체">기존 타석 스크린 교체</option>
														<option value="은퇴 후 창업">은퇴 후 창업</option>
													</select>
												</p>
											</td>
										</tr>
										<!-- 2차 범위 -->
										<tr>
											<th><label for="BusinessHope">희망타석</label></th>
											<td class="pr0">
												<p class="x_1">
													<select  name="item3" class="select_type_01" id="BusinessHope">
														<option value="">선택해주세요.</option>
														<option value="1-4개">1-4개</option>
														<option value="5개-15개">5개-15개</option>
														<option value="16개 이상">16개 이상</option>
													</select>
												</p>
											</td>
										</tr>
									</tbody>
								
								</table>
								<div class="privacy_wrap flex-center mt10">
									<p class="mb5"><em class="mr10">개인정보 수집 안내</em><span class="color_red sub_txt_s">* 아래 약관을 읽은 후 이용가능합니다.</span></p>
									<div id="BusinessprivacyScroll" class="privacy_wrap_info" style="resize:none;">
										<%@ include file="/WEB-INF/jsp/user/normal/vse/common/include/inc_privateTextarea.jsp" %>
									</div>	
								</div>
								<div class="form_box ckinfo_wrap mt15" id="BusinessInfoRCkWarp">
									<label for="BusinessInfoRCk" class="ck_type_01">
										<input type="checkbox" id="BusinessInfoRCk" name="agreePersonal" class=""/><span class="mr10"></span><b class="color_red">[필수]</b> 개인정보 수집안내 동의 및 안내
									</label>
								</div>
								<div class="btn_wrap mt60">
<!-- 									<a href="#" class="btn wd_250 submit">신청하기</a> -->
									<a href="#" class="btn wd_250" onclick="alert('코로나 4단계에서 완화 시 설명회를 진행하겠습니다.'); return false;">신청하기</a>
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
		<!--section05-->
		<div class="section06 section">
			<!--인스타api 구역-->
                <div class="instar_wrap">
                    <div class="inner_sol">
                        <h3 class="tit">INSTARGRAM</h3>
                        <p class="sub">&commat;voicecaddie_vse</p>
                        <div class="slide mt50">
                            <a href="javascript:;" class="section05_slide_arrow prev pc_show">Prev<em></em></a>
                            <a href="javascript:;" class="section05_slide_arrow next pc_show">Next<em></em></a>
                                                  
                            <!--인스타 사진 부분-->
                            <ul class="sec05_sl_outer pc_show" id="pc">
	                            <script>instafeeder();</script>
                            </ul>
                          
                        </div>
                        <div class="clear sns_linkwrap">
                            <span class="t_copperplate">FOLLOW US</span>
                            <ul class="clear">
                                <li><a class="icon_sns_i" href="https://instagram.com/voicecaddie_vse?utm_medium=copy_link" title="인스타그램 새창 열기" target="_blank"></a></li>
                                <li><a class="icon_sns_f" href="https://www.facebook.com/voicecaddie.VSE/" title="페이스북 새창 열기" target="_blank"></a></li>
                                <li><a class="icon_sns_y" href="https://www.youtube.com/channel/UCLMJLbX6Pur9ukrnJ52LPLQ" title="유투브 새창 열기" target="_blank"></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
		</div>
	</div>
</div>
<script>
$(function(){
	//$('#header .gnb > li').eq(3).addClass('active');
	
	$('form[name=mainForm1] select[name=dist1]').ready(function(){
		comCodeUtil.getCodeNPrint('SIDO', 'dist1', 'select');
		$('.select_type_01').selectric('refresh'); 
	}).on({
        change : function() {
        	$('form[name=mainForm1] select[name=dist2]').find("option").eq(0).siblings().remove();
        	comCodeUtil.getCodeNPrint($('form[name=mainForm1] select[name=dist1] option:selected').val(), 'dist2', 'select');
        	$('.select_type_01').selectric('refresh'); 
        }
    });
	
	$('form[name=mainForm2] select[name=dist1]').ready(function(){
		comCodeUtil.getCodeNPrint('SIDO', 'dist1', 'select');
		$('.select_type_01').selectric('refresh'); 
	}).on({
        change : function() {
        	$('form[name=mainForm2] select[name=dist2]').find("option").eq(0).siblings().remove();
        	comCodeUtil.getCodeNPrint($('form[name=mainForm2] select[name=dist1] option:selected').val(), 'dist2', 'select');
        	$('.select_type_01').selectric('refresh'); 
        }
    });
	
	$('form[name=mainForm2] select[name=mgrpCd]').on({
        change : function() {
        	$('div.vsebusinessInfo').hide();
        	$('div#sn_' + $('form[name=mainForm2] select[name=mgrpCd] option:selected').data('sn')).show();
        }
    });
});

var formSubmitObj1 = {
    submit : function(form){
        
    	if(!$("input[name=agreePersonal]").is(":checked")){
			alert('개인정보 수집 동의 해주세요.');
			return false;
		}
    	
        //제목
        if(!submitUtil.isEmpty(form.rgstName)){
            return false;
        }
        
        if(!submitUtil.isEmpty(form.rgstHp)){
            return false;
        }
        
        if(!submitUtil.isEmpty(form.dist1)){
            return false;
        }
        
        if(!submitUtil.isEmpty(form.item1)){
            return false;
        }
        
        if(!submitUtil.isEmpty(form.blcSbc1)){
            return false;
        }
        
        if(!submitUtil.isEmpty(form.blcSbc2)){
            return false;
        }
        
        ajaxUtil.formSubmit($(form), formSubmitObj1.result);
        
        return false;
    },
    result : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        if(json.bindingStatus == undefined && json.result != undefined && json.result.uploadInfo.status ){
            alert('감사합니다. 신청이 완료되었습니다. 담당영업팀에서 확인 후 연락 드리겠습니다.');
        	location.replace('/index.vse');
        }else{
            ajaxUtil.error(json);
        }
    }
};

var formSubmitObj2 = {
    submit : function(form){
        
    	if(!$("input[name=agreePersonal]").is(":checked")){
			alert('개인정보 수집 동의 해주세요.');
			return false;
		}
    	debugger;
    	
    	if(!submitUtil.isEmpty(form.mgrpCd, '사업설명회 일정을 선택해주세요.')){
        	return false;
        }
    	
        if(!submitUtil.isEmpty(form.rgstName)){
            return false;
        }
        
        if(!submitUtil.isEmpty(form.rgstHp)){
            return false;
        }
        
        if(!submitUtil.isEmpty(form.dist1)){
            return false;
        }
        
        if(!submitUtil.isEmpty(form.item1)){
            return false;
        }
        
        if(!submitUtil.isEmpty(form.blcSbc1)){
            return false;
        }
        
        if(!submitUtil.isEmpty(form.blcSbc2)){
            return false;
        }
        
        ajaxUtil.formSubmit($(form), formSubmitObj2.result);
        
        return false;
    },
    result : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        if(json.bindingStatus == undefined && json.result != undefined && json.result.uploadInfo.status ){
            alert('감사합니다. 신청이 완료되었습니다. 담당영업팀에서 확인 후 연락 드리겠습니다.');
        	location.replace('/index.vse');
        }else{
            ajaxUtil.error(json);
        }
    }
};
</script>			