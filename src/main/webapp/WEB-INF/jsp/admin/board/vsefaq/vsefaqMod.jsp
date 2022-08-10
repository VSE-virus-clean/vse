<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
    JSP Name : faqMod.jsp
    Description : FAQ 수정
--%>
<div class="sec_top">
	<h3 class="sec_tit">VSE FAQ 수정</h3>
	<ul class="top_tab">
		<li><a href="#">FAQ</a></li>
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
	                <th>제목</th>
	                <td colspan="3"><input type="text" id="blcTitl" name="blcTitl" maxlength="100" style="width:80%;" title="제목" value="${result.info.blcTitl}" />
	            </tr>
<!-- 	            <tr>     -->
<!-- 	                <th class="necessary">카테고리</th> -->
<!-- 	                <td colspan="3"> -->
<!-- 	                    <select id="mgrpCd" name="mgrpCd" title="카테고리"> -->
<!-- 	                        <option value="" >선택해주세요.</option> -->
<!-- 	                    </select> -->
<!-- 	                </td>  -->
<!-- 	            </tr> -->
	            <tr>
	                <th>작성자</th>
	                <td>${result.info.rgstId}</td>
	                <th>등록시간</th>
	                <td>${result.info.rgstDtm}</td>
	            </tr>
	          	<tr>    
		             <th>공개여부</th>
		             <td colspan="3">
	                    <label class="radio_box" for="useY"><input type="radio" name="useYn" id="useY" value="Y" /><span>공개</span></label>&nbsp;&nbsp;
	                    <label class="radio_box" for="useN"><input type="radio" name="useYn" id="useN" value="N" /><span>비공개</span></label>
	                 </td>  
	            </tr>
	            <tr>    
	                <th class="necessary">내용</th>
	                <td colspan="3" class="con">
	                    <textarea name="blcSbc1" id="blcSbc1" style="width:95%;height:500px;" title="내용">${result.info.blcSbc1}</textarea>
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
//         comCodeUtil.getCodeNPrint('FAQ', 'mgrpCd', 'select');
//         $('select[name=mgrpCd] option[value="${result.info.mgrpCd}"]').prop('selected', true);
    });
});


var formSubmitObj = {
    submit : function(form){
        
    	//제목
        if(!submitUtil.isEmpty(form.blcTitl)){
            return false;
        }
    	
//         if(!submitUtil.isEmpty(form.mgrpCd)){
//             return false;
//         }
        
        //에디터 내용 => 이미지만 삽입하는 경우도 있음.
        blcSbc1Editors.getById["blcSbc1"].exec("UPDATE_CONTENTS_FIELD", []);  

        if(!submitUtil.isEmpty(form.blcSbc1)){
            return false;
        }
        
        ajaxUtil.formSubmit($(form), formSubmitObj.result);
        
        return false;
    },
    result : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        if(json.bindingStatus == undefined && json.result != undefined && json.result.status ){
           location.reload(true);
//            location.replace('${contextPath}/${requestUri}/view.vc?blcSn=${result.info.blcSn}${function:searchQuery(result.searchInfo)}');
        }else{
           ajaxUtil.error(json);
        }
    }
};
</script>