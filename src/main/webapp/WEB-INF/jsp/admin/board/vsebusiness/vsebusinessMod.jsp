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
	<h3 class="sec_tit">VSE 사업설명회 수정</h3>
	<ul class="top_tab">
		<li><a href="#">사업설명회 관리</a></li>
	</ul>
</div>
<div class="sec_cont">
	<h4 class="cont_tit">정보입력</h4>
	<div class="r_search_box">
	    <form name="mainForm" method="post" enctype="multipart/form-data" onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
	    	<input name="blcSn" type="hidden" value="${result.info.blcSn}"/>
	        <table class="r_search_table">
	            <colgroup>
	                 <col width="170px" /><col width="*" />
	            </colgroup>
	            <tr>    
	                <th class="necessary">제목</th>
	                <td>
	                    <input type="text" id="blcTitl" name="blcTitl" maxlength="100" style="width:80%;" title="제목" value="${result.info.blcTitl}" />
	                </td> 
	            </tr>
	            <tr>    
	                <th>공개여부</th>
	                <td>
	                    <label class="radio_box" for="useY"><input type="radio" name="useYn" id="useY" value="Y" /><span>공개</span></label>&nbsp;&nbsp;
	                    <label class="radio_box" for="useN"><input type="radio" name="useYn" id="useN" value="N" /><span>비공개</span></label>
	                </td>  
	            </tr>
	            <tr>    
	                <th class="necessary">노출 시작시간</th>
	                <td>
	                    <input type="text" id="expsRgstDay" name="expsRgstDay" class="datepicker date_input" readonly="readonly" value="${result.info.expsRgstDay}" />
	                    <select name="expsRgstTime" style="width:50px; margin-left:20px;" >
	                    	<c:forEach var="time" begin="0" end="23" varStatus="i">
	                    		<option value="${time < 10 ? '0' : '' }${time}" >${time}</option>
	                    	</c:forEach>
	                    </select> 시
	                    <select name="expsRgstMinute" style="width:50px; margin-left:10px;" >
	                    	<c:forEach var="minute" begin="0" end="59" step="1" varStatus="i">
	                    		<option value="${minute < 10 ? '0' : '' }${minute}" >${minute}</option>
	                    	</c:forEach>
	                    </select> 분
	                    <ul class="warn">
	                     	<li>- 지정하신 일/시간/분 이후에 사이트에 노출됩니다.</li>
	                    </ul>
	                </td>
	            </tr>
	            <tr>    
	                <th class="necessary">노출 만료시간</th>
	                <td>
	                    <input type="text" id="expsFnhDay" name="expsFnhDay" class="datepicker date_input" readonly="readonly" value="${result.info.expsFnhDay}" />
	                    <select name="expsFnhTime" style="width:50px; margin-left:20px;" >
	                    	<c:forEach var="time" begin="0" end="23" varStatus="i">
	                    		<option value="${time < 10 ? '0' : '' }${time}" >${time}</option>
	                    	</c:forEach>
	                    </select> 시
	                    <select name="expsFnhMinute" style="width:50px; margin-left:10px;" >
	                    	<c:forEach var="minute" begin="0" end="59" step="1" varStatus="i">
	                    		<option value="${minute < 10 ? '0' : '' }${minute}" >${minute}</option>
	                    	</c:forEach>
	                    </select> 분
	                    <ul class="warn">
	                     	<li>- 지정하신 일/시간/분 이후에 사이트에 노출 되지 않습니다.</li>
	                    </ul>
	                </td>
	            </tr>
	            <tr>    
	                <th class="necessary">주소</th>
	                <td>
	                    <input type="text" id="blcSbc1" name="blcSbc1" maxlength="100" title="주소" style="width:95%;" value="${result.info.blcSbc1}" />
	                </td>   
	            </tr>
	             <tr>    
	                <th class="necessary">연락처</th>
	                <td>
	                    <input type="text" id="blcSbc2" name="blcSbc2" maxlength="100" title="연락처" style="width:95%;" value="${result.info.blcSbc2}" />
	                </td>   
	            </tr>
	             <tr>    
	                <th class="necessary">운영시간</th>
	                <td>
	                    <input type="text" id="blcSbc3" name="blcSbc3" maxlength="100" title="운영시간" style="width:95%;" value="${result.info.blcSbc3}" />
	                </td>   
	            </tr>
	             <tr>    
	                <th class="necessary">시설</th>
	                <td>
	                    <input type="text" id="blcSbc4" name="blcSbc4" maxlength="100" title="시설" style="width:95%;" value="${result.info.blcSbc4}" />
	                </td>   
	            </tr>
	            <tr>    
	                <th class="necessary">이미지</th>
	                <td class="con">
	                	<div class="filebox">
		                	<p>${function:printImageFile("THUMB", result.file.list)}</p>
		                    ${function:printAttachFileModList2("Y", 1, "THUMB", "이미지", result.file.list, "Y")}
	                    </div>
	                    <ul class="warn">
	                     	<li>- 이미지 파일만 등록 가능합니다.(JPG, PNG, GIF)</li>
	                    </ul>
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
	     $('select[name=expsRgstTime] option[value=${result.info.expsRgstTime}]').prop('selected', true);
	     $('select[name=expsRgstMinute] option[value=${result.info.expsRgstMinute}]').prop('selected', true);
	     $('select[name=expsFnhTime] option[value=${result.info.expsFnhTime}]').prop('selected', true);
	     $('select[name=expsFnhMinute] option[value=${result.info.expsFnhMinute}]').prop('selected', true);
    });
});

var formSubmitObj = {
    submit : function(form){
        
        //제목
        if(!submitUtil.isEmpty(form.blcTitl)){
            return false;
        }
        
        //등록일
        if(!submitUtil.isEmpty(form.expsRgstDay)){
            return false;
        }else{
            if(!submitUtil.isDateFormat(form.expsRgstDay)){
                return false;
            }
        }
        
        if(!submitUtil.isEmpty(form.expsFnhDay)){
            return false;
        }else{
            if(!submitUtil.isDateFormat(form.expsFnhDay)){
                return false;
            }
        }
        
        if(!submitUtil.isNull(form.expsRgstDay) && !submitUtil.isNull(form.expsFnhDay)){
    		if(!submitUtil.isDateCompare(form.expsRgstDay, form.expsFnhDay)){
            	return false;
    		}
        }
        
        if(!submitUtil.isEmpty(form.blcSbc1)){
            return false;
        }
        
        if(!submitUtil.isEmpty(form.blcSbc2)){
            return false;
        }
        
        if(!submitUtil.isEmpty(form.blcSbc3)){
            return false;
        }
        
        if(!submitUtil.isEmpty(form.blcSbc4)){
            return false;
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
//            location.replace('${contextPath}/${requestUri}/view.vc?blcSn=${result.info.blcSn}${function:searchQuery(result.searchInfo)}');
        }else{
           ajaxUtil.error(json);
        }
    }
};
</script>