<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
    Description :  VSE레슨프로 
--%>

<div id="contentWrap" class="pt0">
	<h2 id="contentAnchor">콘텐츠 영역</h2>
	
	<div class="bg_gray pt150 pb60">
		<div class="inner">
			<h3 class="tit move">VSE레슨프로란?</h3>
			<p class="sub_txt text_center mt30 move move02">
				보이스캐디 VSE 티칭 프로는, 전국 각 지역의 티칭 라이선스를 보유한 프로를 대상으로 모집하며<br />
				전국 VSE 매장에 소속 프로로 매칭해 주는 프로그램입니다. 
			</p>
			<p class="sub_txt text_center mt30 move move02">
				VSE 소속 프로가 되기 위해서는 간단한 서류 심사와 필요에 따라 전화 심사로 진행되며, <br />
				VSE 매장과 매칭이 성사 되었을 때는 보이스캐디 와펜, 모자, 파우치, 측정기 할인 코드가 제공됩니다. 
			</p>
			<div class="techingpro_process_slide process_slide swiper-container move move02 ">
				<ul class="techingpro_process swiper-wrapper mt60">
					<li class="swiper-slide "><span>가입접수</span></li>
					<li class="swiper-slide "><span>1.서류심사<br/>2.전화심사</span></li>
					<li class="swiper-slide "><span>VSE티칭<br/>리스트등록</span></li>
					<li class="swiper-slide "><span>각 지역 VSE<br/>매장 매칭</span></li>
					<li class="swiper-slide "><span>보이스캐디<br/>굿즈 발송</span></li>
				</ul>
				<div class="swiper-scrollbar"></div>
			</div>
		</div>
	</div>
	<div class="inner pt60">
		<div class="table_type_01">
			<form name="mainForm" method="post" action="/apply/register.vse" enctype="multipart/form-data" onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
				<input type="hidden" name="lgrpCd" value="APPLICATION" />
<!-- 				<input type="hidden" name="item1" value="" /> -->
				
				<h4 class="sub_tit">
	     			VSE레슨프로 지원서
				</h4>
				<table class="mt15">
					<colgroup>
						<col style="width:180px;">
						<col style="width:auto">
					</colgroup>
					<tbody>
						<tr>
							<th><label for="rgstName">성명 <span class="color_red">*</span></label></th>
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
									<input type="text" id="rgstHp" name="rgstHp" class="numOnly" maxlength="11" title="연락처" value="" />
								</p>
							</td>
						</tr>
						<tr>
							<th><label for="">성별 <span class="color_red">*</span></label></th>
							<td class="pr0">
								<p class="ck_type_03 x_2">
									<label for="ProGenderM"><input type="radio" id="ProGenderM" name="rgstGenderCd" value="M" checked="checked"><span>남</span></label>
									<label for="ProGenderW"><input type="radio" id="ProGenderW" name="rgstGenderCd" value="F"><span>여</span></label>							
								</p>	
							</td>
						</tr>
						<tr>
							<th><label for="">자격증 구분 <span class="color_red">*</span></label></th>
							<td class="pr0">
								<p class="">
									<label for="ProCertif-KPGA"  class="ck_type_02">
										<input type="checkbox" id="ProCertif-KPGA" name="item1" value="KPGA">
										<span class="mr20"></span>KPGA
									</label>
									<label for="ProCertif-KLPGA" class="ck_type_02 ml40">
										<input type="checkbox" id="ProCertif-KLPGA" name="item1" value="KPGA">
										<span class="mr20"></span>KLPGA
									</label>
									<label for="ProCertif-USGTF" class="ck_type_02 ml40">
										<input type="checkbox" id="ProCertif-USGTF" name="item1" value="USGTF">
										<span class="mr20"></span>USGTF
									</label>
									<label for="ProCertif-Other" class="ck_type_02 ml40">
										<input type="checkbox" id="ProCertif-Other" name="item1" value="기타">
										<span class="mr20"></span>기타
									</label>
									<input type="text" id="ProCertifTxt" class="ml20" name="ProCertifEtc" value=""  maxlength="50" style="display:none"/>
								</p>
							</td>
						</tr>
						<tr>
							<th><label for="blcSbc1">주요경력사항 <span class="color_red">*</span></label></th>
							<td class="pr0">
								<p class="x_1">
									<textarea id="blcSbc1" name="blcSbc1" placeholder="작성해주세요."></textarea>
								</p>
							</td>
						</tr>
						<tr>
							<th><label for="dist1">선호 근무 지역 <span class="color_red">*</span></label></th>
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
							<th><label for="workTime">선호 근무 시간 <span class="color_red">*</span></label></th>
							<td class="pr0">
								<p class="x_1">
									<select name="workTime" class="select_type_01" id="workTime">
										<option value="">선택해주세요.</option>
										<option value="6시~12시">6시~12시</option>
										<option value="12시~18시">12시~18시</option>
										<option value="18시~23시">18시~23시</option>
										<option value="주말">주말</option>
									</select>
								</p>
							</td>
						</tr>
						
						<tr>
							<th><label for="blcSbc2">지원사유 <span class="color_red">*</span></label></th>
							<td class="pr0">
								<p class="x_1">
									<textarea id="blcSbc2" name="blcSbc2" placeholder="자유롭게 작성해주세요."></textarea>
								</p>
							</td>
						</tr>
						<tr>
							<th><label for="">첨부파일</label></th>
							<td class="pr0">
								<div class="filebox">
									${function:printAttachFileRegUser(1, "ATTCH1", "첨부파일")}
									<span class="color_point ml20 sub_txt_s" style="">프로자격증 사본, 신분증 사본을 첨부해주세요.</span>
								</div>
								<div class="filebox">
									${function:printAttachFileRegUser(2, "ATTCH2", "첨부파일")}
									<span class="color_point ml20 sub_txt_s mo" style="">프로자격증 사본, 신분증 사본을 첨부해주세요.</span>
								</div>
							</td>
						</tr>
					</tbody>
				
				</table>
				<div class="privacy_wrap flex-center mt10">
					<p class="mb5"><em class="mr10">개인정보 수집 안내</em><span class="color_red sub_txt_s">* 아래 약관을 읽은 후 이용가능합니다.</span></p>
					<div id="CounselingprivacyScroll" class="privacy_wrap_info" style="resize:none;">
						<%@ include file="/WEB-INF/jsp/user/normal/vse/common/include/inc_privateTextarea.jsp" %>
					</div>			
				</div>
				<div class="form_box ckinfo_wrap mt15" id="CounselingInfoRCkWarp">
					<label for="CounselingInfoRCk" class="ck_type_01">
						<input type="checkbox" id="CounselingInfoRCk" name="agreePersonal" class=""/><span class="mr10"></span><b class="color_red">[필수]</b> 개인정보 수집안내 동의 및 안내
					</label>
				</div>
				<div class="btn_wrap mt60">
					<a href="#" class="btn wd_250 submit">신청하기</a>
				</div>
			</form>
		</div>
		<!--table_type_01-->
	</div>
</div>


<script>

$(function(){
	$('label[for="ProCertif-Other"]').on('click',function(){
	    if($('#ProCertif-Other').is(':checked')){
	    	 $('#ProCertifTxt').show();
	    }else{
	    	 $('#ProCertifTxt').hide();
	    }
	});


});
$(function(){
	
	
	$('#header .gnb > li').eq(5).addClass('active');
	
	$('select[name=dist1]').ready(function(){
		comCodeUtil.getCodeNPrint('SIDO', 'dist1', 'select');
		$('.select_type_01').selectric('refresh'); 
	}).on({
        change : function() {
        	$('select[name=dist2]').find("option").eq(0).siblings().remove();
        	comCodeUtil.getCodeNPrint($('select[name=dist1] option:selected').val(), 'dist2', 'select');
        	$('.select_type_01').selectric('refresh'); 
        }
    });
});

var formSubmitObj = {
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

	        
	        if(!submitUtil.isChecked(form.item1, '자격증을 선택해 주세요.')){
	            return false;
	        }
	        
	        if(!submitUtil.isEmpty(form.blcSbc1)){
	            return false;
	        }
	        
	        if(!submitUtil.isEmpty(form.dist1)){
	            return false;
	        }
	        
	        if(!submitUtil.isEmpty(form.workTime)){
	            return false;
	        }
	        
	        if(!submitUtil.isEmpty(form.blcSbc2)){
	            return false;
	        }
	        
	        if(!submitUtil.isNull(form.attachFile1)){
	        	if(!submitUtil.isAttachFile(form.attachFile1, 'PRO')){
	            	return false;
	            }
	        }
			
	        if(!submitUtil.isNull(form.attachFile2)){
	        	if(!submitUtil.isAttachFile(form.attachFile2, 'PRO')){
	            	return false;
	            }
	        }
	        
	        ajaxUtil.formSubmit($(form), formSubmitObj.result);
	        
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
