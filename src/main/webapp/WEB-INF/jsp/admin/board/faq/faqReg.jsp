<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
    JSP Name : faqReg.jsp
    Description : faq 입력폼
--%>
<div class="sec_top">
	<h3 class="sec_tit">FAQ 등록</h3>
	<ul class="top_tab">
		<li><a href="#">게시판관리</a></li>
		<li><a href="#">FAQ</a></li>
	</ul>
</div>
<div class="sec_cont">
	<h4 class="cont_tit">정보입력</h4>
	<div class="r_search_box">
	
	    <form name="mainForm" method="post" enctype="multipart/form-data" onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
	        <table class="r_search_table">
	            <colgroup>
	                <col width="130px" /><col width="*" />
	            </colgroup>
	            <tr>    
	                <th class="necessary">제목</th>
	                <td>
	                	<input type="text" id="blcTitl" name="blcTitl" maxlength="100" style="width:80%;" title="제목" value="" />
	                </td>    
	            </tr>
	            <tr>    
	                <th class="necessary">카테고리</th>
	                <td>
	                    <select id="mgrpCd" name="mgrpCd" title="카테고리">
	                        <option value="" >선택해주세요.</option>
	                    </select>
	                </td> 
	            </tr>
	            <tr>    
	                <th>작성자</th>
	                <td>${sessionScope.sessionVO.id}</td>   
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
	                <td>
	                    <textarea name="blcSbc1" id="blcSbc1" style="width:95%;height:500px;" title="내용"></textarea>
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


<script type="text/javascript" src="/resources/admin/editor/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">
smarteditor.create([{'id':'blcSbc1', 'htmlMode':false}]);

$(function () {
    $('form[name=mainForm]').ready(function(){
    	comCodeUtil.getCodeNPrint('FAQ', 'mgrpCd', 'select');
        $('select[name=mgrpCd] option[value="${result.searchInfo.mgrpCd}"]').prop('selected', true);
    	$('input[name=useYn][value=Y]').prop('checked', true).trigger('click'); 
    });
});

var formSubmitObj = {
    submit : function(form){
        
        //제목
        if(!submitUtil.isEmpty(form.blcTitl)){
            return false;
        }
        
        if(!submitUtil.isEmpty(form.mgrpCd)){
            return false;
        }
        
      	//에디터 내용 삽입
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
            location.replace('${contextPath}/${requestUri}/list.vc');
        }else{
            ajaxUtil.error(json);
        }
    }
};
</script>