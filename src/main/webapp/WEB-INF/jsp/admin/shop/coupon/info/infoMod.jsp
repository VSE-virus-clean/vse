<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
    JSP Name : noticeMod.jsp
    Description : 게시판관리 > INFORMATION > NOTICE 수정폼
--%>
<div class="sec_top">
	<h3 class="sec_tit">쿠폰관리</h3>
	<ul class="top_tab">
		<li><a href="#">쿠폰관리</a></li>
		<li><a href="#">쿠폰목록</a></li>
	</ul>
</div>
<div class="sec_cont">
	<h4 class="cont_tit">쿠폰 기본정보</h4>
	<div class="r_search_box">
	    <form name="mainForm" method="post" enctype="multipart/form-data" onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
	    	<input type="hidden" name="cupMetaSn" value="${result.info.cupMetaSn}"/>
	    	<input type="hidden" name="lgrpCd" value="${result.info.lgrpCd}"/>
	        <table class="r_search_table">
	            <colgroup>
	                 <col width="170px" /><col width="*" /><col width="170px" /><col width="*" />
	            </colgroup>
	            <tr>    
	                <th class="necessary">쿠폰명</th>
	                <td colspan="3">
	                    <input type="text" id="cupTitl" name="cupTitl" style="width:80%;" maxlength="10" title="쿠폰명" value="${result.info.cupTitl}" />
	                </td> 
	            </tr>
	            <tr>
					<th class="">쿠폰설명</th>
					<td colspan="3">
						<input type="text" id="cupSbc" name="cupSbc" style="width:100%;" title="쿠폰설명" value="${result.info.cupSbc}" />
					</td>
				</tr>
	            <tr>
					<th class="necessary">발급상태</th>
					<td colspan="3">
					   <label class="radio_box"  for="useY">
							<input type="radio" id="useY" name="useYn" value="Y">
							<span>발급중</span>
						</label>
						<label class="radio_box" for="useN">
							<input type="radio" id="useN" name="useYn" value="N">
							<span>발급중지</span>
						</label>
					</td>
				</tr>
				<tr>
					<th class="necessary">쿠폰타입</th>
					<td colspan="3">${result.info.lgrpCd}</td>
				</tr>
				<tr class="tr-maxCnt">
					<th class="necessary">최대발급수</th>
					<td colspan="3">
					   <div class="check_box" id="text_box_group">
							<input type="text" id="maxCnt" name="maxCnt" class="numOnly" style="width:260px;" value="${result.info.maxCnt}" title="최대발급수">
						</div>
					</td>
				</tr>
				<tr>
					<th class="necessary">할인설정</th>
					<td colspan="3">
					  <label class="radio_box"  for="mgrpCd1">
							<input type="radio" id="mgrpCd1" name="mgrpCd" value="PER">
							<span>할인율(%)</span>
						</label>
						<label class="radio_box" for="mgrpCd2">
							<input type="radio" id="mgrpCd2" name="mgrpCd" value="WON">
							<span>할인금액(원)</span>
						</label>
					</td>
				</tr>
				<tr>
					<th class="necessary">할인율/가격</th>
					<td colspan="3">
					   <div class="check_box" id="text_box_group">
							<input type="text" id="price" name="price" class="numOnly" style="width:260px;" value="${result.info.price}" title="할인율/가격">
						</div>
					</td>
				</tr>
				<tr>
					<th class="necessary">최대 할인금액</th>
					<td colspan="3">
					   <div class="check_box" id="text_box_group">
							<input type="text" id="maxPrice" name="maxPrice" class="numOnly" style="width:260px;" value="${result.info.maxPrice}" title="최대할인가능금액">
						</div>
					</td>
				</tr>
				<tr>
					<th class="necessary">유효기간</th>
					<td colspan="3">
					   <div class="check_box" id="text_box_group">
							<select name="expsSelect" style="width: 264px;">
								<option value="day">기간설정 </option>
								<option value="period">쿠폰발급일 기준 </option>
							</select>
							<span class="span-day">
								<input type="text" placeholder="사용가능일수" name="expsDay" class="numOnly" title="사용가능일수" value="${result.info.expsDay}">
							</span>
							<span class="span-period">
								<input type="text" placeholder="시작일시" name="expsRgstDtm" value="${result.info.expsRgstDtm}" class="date_input is-datepick datepicker" readonly="readonly" autocomplete="off" title="시작일시">
								<em> ~ </em>
								<input type="text" placeholder="만료일시" name="expsFnhDtm" value="${result.info.expsFnhDtm}" class="date_input is-datepick datepicker" readonly="readonly" autocomplete="off" title="만료일시">
							</span>
						</div>
						<p class="info_txt">*기간설정 :  실제 발급된 일시에 관계없이, 지정된 기간에만 사용가능 <br>*쿠폰 발급일 기준 : 실제 발급된 일시를 기준으로 입력된 일수의 기간까지 이용가능
						</p>
					</td>
				</tr>
	            <tr>    
	                <th class="necessary">첨부파일</th>
	                <td colspan="3" class="con">
	                	<div class="filebox">
		                	<p>${function:printImageFile("THUMB", result.file.list)}</p>
		                    ${function:printAttachFileModList2("Y", 1, "THUMB", "쿠폰이미지", result.file.list, "Y")}
	                    </div>
	                </td>   
	            </tr>
	        </table>
	        <div class="btn_center_gorup clearfix">
				<div class="left">
					<button type="button" class="btn btn_gray" onclick="location.href='${contextPath}/${requestUri}/list.vc?${function:searchQuery(result.searchInfo)}'; ">목록</button>
				</div>
				<div class="right">
					<button type="submit" class="btn btn_red">수정</button>
				</div>
			</div>
	    </form>
	</div>
</div>

<script type="text/javascript">
$(function () {
    $('form[name=mainForm]').ready(function(){
        $('input[name=useYn][value=${result.info.useYn}]').prop('checked', true).trigger('click');  
        $('input[name=mgrpCd][value=${result.info.mgrpCd}]').prop('checked', true).trigger('click');   
        $('select[name=expsSelect] option[value="${empty result.info.expsFnhDtm ? 'day' :  'period'}"]').prop('selected', true).trigger('change');

        if('${result.info.lgrpCd}' == '이벤트쿠폰'){
    		$('.tr-maxCnt').show();
        }else{
        	$('.tr-maxCnt').hide();
        }
    });
    
    //비공개글 공지로 설정시 공지등록 불가
//     $('input[name=useYn]').on({
//         click : function(){
//             if($('input[name=useYn]:checked').val() == 'N'){
//                 $('input[name=notiYn]').prop('checked', false);
//             }
//         }
//     });
    
    $('select[name=expsSelect]').on('change', function(event){
    	if($(this).val() == 'day'){
    		$('span.span-day').show();
    		$('span.span-period').hide();
    	}else{
    		$('span.span-day').hide();
    		$('span.span-period').show();
    	}
	});
    
    
});

var formSubmitObj = {
    submit : function(form){
        
        //제목
    	if(!submitUtil.isEmpty(form.cupTitl)){
            return false;
        }
        
//     	if(!submitUtil.isEmpty(form.cupSbc)){
//             return false;
//         }
        
        if(form.lgrpCd.value == '이벤트쿠폰'){
        	if(!submitUtil.isEmpty(form.maxCnt)){
                return false;
            }
        }
        
        if(!submitUtil.isEmpty(form.price)){
            return false;
        }else{
        	if(form.mgrpCd.value == 'PER'){
        		if(parseInt(form.price.value) > 100){
        			submitUtil.alertNfocus(form.price, '할인율은 100%를 넘을수 없습니다.');
        		}
        	}
        }
        
        if(!submitUtil.isEmpty(form.maxPrice)){
            return false;
        }
        
        //유효기간
        if(form.expsSelect.value == 'day'){
        	form.expsRgstDtm.value = '';
        	form.expsFnhDtm.value = '';
        	
        	if(!submitUtil.isEmpty(form.expsDay)){
                return false;
            }
        }else{
        	form.expsDay.value = '0';
        	if(!submitUtil.isEmpty(form.expsRgstDtm)){
                return false;
            }else{
                if(!submitUtil.isDateFormat(form.expsRgstDtm)){
                    return false;
                }
            }
        	
        	if(!submitUtil.isEmpty(form.expsFnhDtm)){
                return false;
            }else{
                if(!submitUtil.isDateFormat(form.expsFnhDtm)){
                    return false;
                }
            }
        	
        	if(!submitUtil.isDateCompare(form.expsRgstDtm, form.expsFnhDtm)){
        		return false;
        	}
        }
        
        
        if(!submitUtil.isNull(form.attachFile1)){
            if(!submitUtil.isAttachFile(form.attachFile1, 'IMG')){
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
           location.reload(true);
        }else{
           ajaxUtil.error(json);
        }
    }
};
</script>