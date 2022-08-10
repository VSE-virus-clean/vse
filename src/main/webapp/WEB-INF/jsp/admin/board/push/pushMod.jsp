<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="sec_top">
	<h3 class="sec_tit">APP PUSH 수정</h3>
	<ul class="top_tab">
		<li><a href="#">배너&amp;팝업관리</a></li>
		<li><a href="#">APP PUSH관리</a></li>
	</ul>
</div>
<div class="sec_cont">
	<h4 class="cont_tit">상세정보</h4>
	<div class="r_search_box">
	    <form name="mainForm" method="post" enctype="multipart/form-data" onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
	    	<input name="blcSn" type="hidden" value="${result.info.blcSn}"/>
	        <table class="r_search_table">
	            <colgroup>
	                 <col width="170px" /><col width="*" /><col width="170px" /><col width="*" />
	            </colgroup>
	            <tr>    
	                <th class="necessary">버전</th>
	                <td colspan="3">
	                    <input type="text" id="blcTitl" name="blcTitl" maxlength="100" style="width:80%;" title="버전" value="${result.info.blcTitl}" />
	                </td> 
	            </tr>
	            <tr>    
	                <th>타입</th>
	                <td colspan="3">
	                    <label class="radio_box" for="mgrpCd1"><input type="radio" name="mgrpCd" id="mgrpCd1" value="FIRMWARE" /><span>펌웨어</span></label>&nbsp;&nbsp;
	                    <label class="radio_box" for="mgrpCd2"><input type="radio" name="mgrpCd" id="mgrpCd2" value="BOOT" /><span>부트로더</span></label>
	                </td> 
	            </tr>
	            <tr>    
	                <th class="necessary">업데이트 파일</th>
	                <td colspan="3" class="con">
	                	<div class="filebox">
		                	<p>${function:printImageFile("ATTCH", result.file.list)}</p>
		                    ${function:printAttachFileModList2("Y", 1, "ATTCH", "업데이트 파일", result.file.list, "Y")}
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
    	$('input[name=mgrpCd][value=${result.info.mgrpCd}]').prop('checked', true).trigger('click');   
    });
    
});

var formSubmitObj = {
    submit : function(form){
        
        //제목
        if(!submitUtil.isEmpty(form.blcTitl)){
            return false;
        }
        
        if(!submitUtil.isNull(form.attachFile1)){
            if(!submitUtil.isAttachFile(form.attachFile1, 'ALL')){
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