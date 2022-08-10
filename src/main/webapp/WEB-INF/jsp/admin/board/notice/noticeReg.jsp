<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
    JSP Name : noticeReg.jsp
    Description : 게시판관리 > INFORMATION > NOTICE 입력폼
--%>
<div class="sec_top">
	<h3 class="sec_tit">공지사항 등록</h3>
	<ul class="top_tab">
		<li><a href="#">게시판관리</a></li>
		<li><a href="#">공지사항</a></li>
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
	                <th class="necessary">제목</th>
	                <td colspan="3">
	                    <input type="text" id="blcTitl" name="blcTitl" maxlength="100" style="width:80%;" title="제목" />
	                </td> 
	            </tr>
	            <tr>    
	                <th>공지여부</th>
	                <td colspan="3">
	                    <label class="radio_box" for="notiY"><input type="radio" name="notiYn" id="notiY" value="Y" /><span>중요</span></label>&nbsp;&nbsp;
	                    <label class="radio_box" for="notiN"><input type="radio" name="notiYn" id="notiN" value="N" /><span>비중요</span></label>
	                    <ul class="warn">
	                     	<li>- 목록 상단에 노출됩니다.</li>
	                    </ul>
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
	                <th class="necessary">등록일</th>
	                <td colspan="3">
	                    <input type="text" id="expsRgstDay" name="expsRgstDay" class="datepicker date_input" readonly="readonly" autocomplete="off" />
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
	                     	<li>- 바로 적용하실거면 값을 변경하지 않으시면 됩니다.</li>
	                    </ul>
	                </td>
	            </tr>
	            <tr>    
	                <th class="necessary">내용</th>
	                <td colspan="3">
	                    <textarea name="blcSbc1" id="blcSbc1" style="width:100%;height:500px;" title="내용"></textarea>
	                </td>   
	            </tr>
	            <tr>    
	                <th class="necessary">첨부파일</th>
	                <td colspan="3">
	                    <div class="filebox">
	                   		${function:printAttachFileReg(1, "ATTCH", "첨부파일")}
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

<script type="text/javascript" src="/resources/admin/editor/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">
smarteditor.create([{'id':'blcSbc1', 'htmlMode':true}]);

$(function () {
    $('form[name=mainForm]').ready(function(){
    	//$("#expsRgstDay").datepicker("option", "maxDate", "0d").val(dateUtil.getDate());
        $('input[name=notiYn][value=N]').prop('checked', true).trigger('click'); 
        $('input[name=useYn][value=Y]').prop('checked', true).trigger('click'); 
        $("#expsRgstDay").val(dateUtil.getDate());
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
        
        //에디터 내용 삽입
        blcSbc1Editors.getById["blcSbc1"].exec("UPDATE_CONTENTS_FIELD", []);
        
        if(!submitUtil.isEmpty(form.blcSbc1)){
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