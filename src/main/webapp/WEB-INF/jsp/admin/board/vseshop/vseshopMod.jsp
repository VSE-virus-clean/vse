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
	<h3 class="sec_tit">VSE 매장 정보 수정</h3>
	<ul class="top_tab">
		<li><a href="#">매장관리</a></li>
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
	                <th class="necessary">매장명</th>
	                <td>
	                    <input type="text" id="blcTitl" name="blcTitl" maxlength="100" style="width:80%;" title="매장명" value="${result.info.blcTitl}" />
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
	                <th class="necessary">타석수</th>
	                <td>
	                    <input type="text" name="blcSbc3" id="blcSbc3" maxlength="100" title="타석수" style="width:95%;" value="${result.info.blcSbc3}" />
	                </td>   
	            </tr>
	            <tr>    
	                <th class="necessary">주소</th>
	                <td>
	                    <input type="text" name="blcSbc4" id="blcSbc4" maxlength="500" title="주소" style="width:95%;" value="${result.info.blcSbc4}" />
	                    <p><a target="_blank" href="https://map.kakao.com/?map_type=TYPE_MAP&q=${result.info.blcSbc1}&urlLevel=3">카카오맵확인</a></p>
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
    });
});

var formSubmitObj = {
    submit : function(form){
        
        //제목
        if(!submitUtil.isEmpty(form.blcTitl)){
            return false;
        }
        
        if(!submitUtil.isEmpty(form.blcSbc3)){
            return false;
        }
        
        if(!submitUtil.isEmpty(form.blcSbc4)){
            return false;
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
           location.replace('${contextPath}/${requestUri}/view.vc?blcSn=${result.info.blcSn}${function:searchQuery(result.searchInfo)}');
        }else{
           ajaxUtil.error(json);
        }
    }
};
</script>