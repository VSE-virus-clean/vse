<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="sec_top">
	<h3 class="sec_tit">펌웨어 등록</h3>
	<ul class="top_tab">
		<li><a href="#">펌웨어관리</a></li>
		<li><a href="#">환경설정</a></li>
	</ul>
</div>
<div class="sec_cont">
	<h4 class="cont_tit">정보입력</h4>
	<div class="r_search_box">
	    <form name="mainForm" method="post" enctype="multipart/form-data" onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
	        <table class="r_search_table">
	            <colgroup>
	                <col width="170px" /><col width="*" /><col width="170px" /><col width="*" />
	            </colgroup>
	            <tr>    
	                <th class="necessary">버전</th>
	                <td colspan="3">
	                    <input type="text" id="blcTitl" name="blcTitl" maxlength="100" style="width:80%;" title="버전" />
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
	                <td colspan="3">
	                    <div class="filebox">
	                   		${function:printAttachFileReg(1, "ATTCH", "업데이트 파일")}
	                    </div>
	                </td>   
	            </tr>
	        </table>
	        
	        <div class="btn_center_gorup clearfix">
				<div class="left">
					<button type="button" class="btn btn_gray" onclick="location.href='${contextPath}/${requestUri}/list.vc'; ">목록</button>
				</div>
				<div class="right">
					<button type="submit" class="btn btn_red">등록</button>
				</div>
			</div>
	    </form>
	</div>
</div>


<script type="text/javascript">
$(function () {
    $('form[name=mainForm]').ready(function(){
    	$('input[name=mgrpCd][value="WEB"]').prop('checked', true).trigger('click');
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
            location.replace('${contextPath}/${requestUri}/list.vc');
        }else{
            ajaxUtil.error(json);
        }
    }
};
</script>