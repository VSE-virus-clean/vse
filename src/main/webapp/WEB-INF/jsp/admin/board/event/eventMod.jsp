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
<!-- CONTENT -->
<div class="sec_top">
	<h3 class="sec_tit">이벤트 목록</h3>
	<ul class="top_tab">
		<li><a href="#">게시판관리</a></li>
		<li><a href="#">이벤트</a></li>
	</ul>
</div>
<div class="sec_cont">
	<h4 class="cont_tit">정보입력</h4>
	<div class="r_search_box">
	    <form name="mainForm" method="post" enctype="multipart/form-data" onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
	    	<input name="blcSn" type="hidden" value="${result.info.blcSn}"/>
	        <table class="r_search_table">
	            <colgroup>
	                 <col width="170px" /><col width="*" /><col width="170px" /><col width="*" />
	            </colgroup>
	            <tr>    
	                <th class="necessary">제목</th>
	                <td colspan="3">
	                    <input type="text" id="blcTitl" name="blcTitl" maxlength="100" style="width:80%;" title="제목" value="${result.info.blcTitl}" />
	                </td> 
	            </tr>
	            <tr>    
	                <th>공개여부</th>
	                <td colspan="3">
	                    <label class="radio_box" for="useY"><input type="radio" name="useYn" id="useY" value="Y" /><span>공개</span></label>&nbsp;&nbsp;
	                    <label class="radio_box" for="useN"><input type="radio" name="useYn" id="useN" value="N" /><span>비공개</span></label>
	                </td>  
	            </tr>
	            <tr>    
	                <th class="necessary">이벤트 시작시간</th>
	                <td colspan="3">
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
	                <th class="necessary">이벤트 만료시간</th>
	                <td colspan="3">
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
	                </td>
	            </tr>
	            <tr>    
	                <th class="necessary">내용</th>
	                <td colspan="3">
	                    <textarea name="blcSbc1" id="blcSbc1" style="width:100%;height:500px;background:#ddd;" title="내용">${result.info.blcSbc1}</textarea>    
	                </td>   
	            </tr>
	            <tr>    
	                <th>이벤트 이미지</th>
	                <td colspan="3" class="con">
	                	<div class="filebox">
	                		<p>${function:printImageFile("THUMB", result.file.list)}</p>
	                    	${function:printAttachFileModList2("Y", 1, "THUMB", "Thumbnail", result.file.list, "Y")}
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

<script type="text/javascript" src="/resources/admin/editor/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">
smarteditor.create([{'id':'blcSbc1', 'htmlMode':true}]);

$(function () {
    $('form[name=mainForm]').ready(function(){
    	$('input[name=useYn][value=${result.info.useYn}]').prop('checked', true).trigger('click');  
//      $('select[name=mgrpCd] option[value="${result.info.mgrpCd}"]').prop('selected', true);
     $('select[name=expsRgstTime] option[value=${result.info.expsRgstTime}]').prop('selected', true);
     $('select[name=expsRgstMinute] option[value=${result.info.expsRgstMinute}]').prop('selected', true);
     $('select[name=expsFnhTime] option[value=${result.info.expsFnhTime}]').prop('selected', true);
     $('select[name=expsFnhMinute] option[value=${result.info.expsFnhMinute}]').prop('selected', true);
    });
    
    //비공개글 공지로 설정시 공지등록 불가
    $('input[name=useYn]').on({
        click : function(){
            if($('input[name=useYn]:checked').val() == 'N'){
                $('input[name=notiYn]').prop('checked', false);
            }
        }
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
        
        //에디터 내용 => 이미지만 삽입하는 경우도 있음.
        blcSbc1Editors.getById["blcSbc1"].exec("UPDATE_CONTENTS_FIELD", []);  

        if(!submitUtil.isEmpty(form.blcSbc1)){
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