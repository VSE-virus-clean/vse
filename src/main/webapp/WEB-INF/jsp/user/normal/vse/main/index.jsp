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
	<h2 id="contentAnchor">content</h2>
	<div class="section01 section">
		<div class="video_wrap">
		
		<!-- 
			<video id="MainVideo01" style="min-width:1200px;" autoplay loop muted  playsinline>
				<source src="/resources/vse/videos/videos_01.mp4" type="video/mp4">
			</video>
		-->
		<div id="MainVideo01" class="v_image_only">
		</div>
		</div>
		<div class="video_text up move">
			<h3 class="">GOLF SHOULD BE EASY</h3>
			<a href="javascript:;" id="MAIN-STARTUP" class="btn btn_round btn_section05">LEARN MORE</a>
		</div>
		<p class="scroll_swing up">
			<img src="/resources/vse/images/com/icon_swing.png" alt="scroll" />
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
				<p class="sub_txt text_center mt40">You can raise the quality of practice with VSE<br />And experience a now play with Voce Caddie.</p>
			</div>
		</div>
		<!--section02-->
		<div class="section03 section">
			<div class="inner">
				<div class="con text_center">
					<h3 class="tit move">VSE leads the golf market by adding new “experience” and “Value” to golfers’ lifestyle.</h3>
					<a href="/interior.vse" class="btn btn_round move move02">Choose Your Space</a>
				</div>
			</div>
		</div>
		<!--section03-->
		<!-- 
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
		 -->
		<!--section04-->
		<div class="section05 section" id="section05">
			<div class="tab_wrap inner">
				<h3 class="tit move move01">REQUEST MORE INFORMATION</h3>
				<ul class="tab_type_01 tab_btn x_2">
					<li class="active" style="width:100% !important"><a href="#none">&nbsp;</a></li>
				</ul>
				<div class="tab_con mt40">
					<div class="tab_list active">
						<h4 class="sub_tit">
			     			Call : 562-926-3978
						</h4>
						<p class="sub_txt">9:00 ~ 17:00 (PST)</p>
						<h3 class="sub_tit mt40">Consultation Request</h3>
						<p class="sub_txt">If you fill out the application form, We will get in touch with you shortly.</p>
						<div class="table_type_01 mt20">
							<form name="mainForm1" method="post" action="/apply/register.vse" enctype="multipart/form-data" onsubmit="var rtn = formSubmitObj1.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
							<input type="hidden" name="lgrpCd" value="BUSINESS" />
								<table>
									<colgroup>
										<col style="width:215px;" />
										<col style="width:auto"/>
									</colgroup>
									<tbody>
										<tr>
											<th><label for="a1">Name <span class="color_red">*</span></label></th>
											<td class="pr0">
												<p class="x_2 _flex_">
													<input type="text" id="a1" name="a1" maxlength="100" title="First Name" value="" placeholder="First Name" />
													<input class="_margin_left_10" type="text" id="a1_2" name="a1_2" maxlength="100" title="Last Name" value="" placeholder="Last Name" />
												</p>
											</td>
										</tr>
										<tr>
											<th><label for="b1">Phone <span class="color_red">*</span></label></th>
											<td class="pr0">
												<p class="x_1">
													<input type="text" id="b1" name="b1" class="numOnly" maxlength="100" title="Phone" value="" placeholder="Phone" />
												</p>
											</td>
										</tr>
										<tr>
											<th><label for="c1">Email <span class="color_red">*</span></label></th>
											<td class="pr0">
												<p class="x_1">
													<input type="text" id="c1" name="c1" maxlength="100" title="Email" value="" placeholder="Email" />
												</p>
											</td>
										</tr>
										<tr>
											<th><label for="d1">Address <span class="color_red">*</span></label></th>
											<td class="pr0">
												<p class="x_1 _flex_">
													<input type="text" id="d1" name="d1" maxlength="100" title="City" value="" placeholder="City" />
													<input class="_margin_left_10" type="text" id="d1_2" name="d1_2" maxlength="100" title="State" value="" placeholder="State" />
													<input class="_margin_left_10" type="text" id="d1_3" name="d1_3" maxlength="100" title="Zip Code" value="" placeholder="Zip Code" />
												</p>
											</td>
										</tr>
										<tr>
											<th><label for="e1">Installation Type <span class="color_red">*</span></label></th>
											<td class="pr0">
												<p class="x_1">
													<select name="e1" class="select_type_01" id="e1" title="Installation Type">
														<option value=""></option>
														<option value="Residential">Residential</option>
														<option value="Academy">Academy </option>
														<option value="Practice Range">Practice Range</option>
														<option value="Sports Lounge">Sports Lounge</option>
														<option value="Golf Club & Golf Facility">Golf Club & Golf Facility</option>
														<option value="Etc">Etc</option>
													</select>
												</p>
											</td>
										</tr>
										<tr>
											<th><label for="f1">How did you find us? <span class="color_red">*</span></label></th>
											<td class="pr0">
												<p class="x_1">
													<input type="text" id="f1" name="f1" maxlength="100" title="Email" value="" placeholder="How did you find us?" />
												</p>
											</td>
										</tr>
										<tr>
											<th><label for="g1">What can you tell us? <span class="color_red">*</span></label></th>
											<td class="pr0">
												<p class="x_1">
													<input type="text" id="g1" name="g1" maxlength="100" title="What can you tell us?" value="" placeholder="What can you tell us?" />
												</p>
											</td>
										</tr>
										
									</tbody>
								
								</table>
								<div class="privacy_wrap flex-center mt10">
									<p class="mb5"><em class="mr10">Personal Information Collection and Usage Agreement</em><span class="color_red sub_txt_s">* You can use it after agreeing to the terms and conditions below.</span></p>
									<div id="CounselingprivacyScroll" class="privacy_wrap_info " style="resize:none;">
										<%@ include file="/WEB-INF/jsp/user/normal/vse/common/include/inc_privateTextarea.jsp" %>
									</div>	
								</div>
								<div class="form_box ckinfo_wrap mt15" id="CounselingInfoRCkWarp">
									<label for="CounselingInfoRCk" class="ck_type_01">
										<input type="checkbox" id="CounselingInfoRCk" name="agreePersonal" class=""/><span class="mr10"></span>You agree to Privacy Policy and Terms of VSE
									</label>
								</div>
								<div class="btn_wrap mt60">
									<a href="#" class="btn wd_250 submit">Submit</a>
								</div>
							</form>
						</div>
						<!--table_type_01-->

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
                        <!-- 
                            <a href="javascript:;" class="section05_slide_arrow prev pc_show">Prev<em></em></a>
                            <a href="javascript:;" class="section05_slide_arrow next pc_show">Next<em></em></a>
                         -->                          
                            <!--인스타 사진 부분-->
                            <ul class="sec05_sl_outer pc_show" id="pc">
                            </ul>
                          
                        </div>
                        <div class="clear sns_linkwrap">
                            <span class="t_copperplate">FOLLOW US</span>
                            <ul class="clear">
                                <li><a class="icon_sns_i" href="https://instagram.com/voicecaddie_vse?utm_medium=copy_link" title="instagram" target="_blank"></a></li>
                                <li><a class="icon_sns_f" href="https://www.facebook.com/voicecaddie.VSE/" title="facebook" target="_blank"></a></li>
                                <li><a class="icon_sns_y" href="https://www.youtube.com/channel/UCLMJLbX6Pur9ukrnJ52LPLQ" title="youtube" target="_blank"></a></li>
                            </ul>
                        </div>
                    </div>
                </div>
		</div>
	</div>
</div>
<script>

var formSubmitObj1 = {
    submit : function(form){
        
    	if(!$("input[name=agreePersonal]").is(":checked")){
			alert("You must agree to VSE's Privacy Policy and Terms.");
			return false;
		}
    	
        //바뀐것 체크
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
            alert('Were successfully completed.');
        	location.replace('/index.vse');
        }else{
            ajaxUtil.error(json);
        }
    }
};

</script>			