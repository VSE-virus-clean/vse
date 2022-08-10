<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<style>
	.tr-expsRgst { display:none; }
</style>
<div class="sec_top">
	<h3 class="sec_tit">APP PUSH 등록</h3>
	<ul class="top_tab">
		<li><a href="#">배너&amp;팝업관리</a></li>
		<li><a href="#">APP PUSH관리</a></li>
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
	                <th>발송타입</th>
	                <td colspan="3">
	                    <label class="radio_box" for="mgrpCd1"><input type="radio" name="mgrpCd" id="mgrpCd1" value="즉시발송" /><span>즉시발송</span></label>&nbsp;&nbsp;
	                    <label class="radio_box" for="mgrpCd2"><input type="radio" name="mgrpCd" id="mgrpCd2" value="예약발송" /><span>예약발송</span></label>
	                </td> 
	            </tr>
				<tr class="tr-expsRgst">
	                <th class="necessary">발송시간</th>
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
	                     	<li>- 지정하신 일/시간/분에 발송됩니다.</li>
	                    </ul>
	                </td>
	            </tr>
	            <tr>    
	                <th class="necessary">제목</th>
	                <td colspan="3">
	                    <input type="text" id="blcTitl" name="blcTitl" maxlength="100" style="width:100%;" title="제목" />
	                </td> 
	            </tr>
	            <tr>
					<th class="necessary">내용</th>
					<td colspan="3">
						<input type="text" id="blcSbc1" name="blcSbc1" style="width:100%;" title="내용">
					</td>
				</tr>
<!-- 	            <tr>     -->
<!-- 	                <th>URL</th> -->
<!-- 	                <td colspan="3"> -->
<!-- 	                    <input type="text" id="rltdLk" name="rltdLk" maxlength="1000" style="width:100%;" title="연결 URL" /> -->
<!-- 	                </td>    -->
<!-- 	            </tr> -->
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
        $('input[name=mgrpCd][value=즉시발송]').prop('checked', true).trigger('click'); 
    });
	
	$('input[name=mgrpCd]').on('click', function(event){
    	if($('input[name=mgrpCd]:checked').val() == '예약발송'){
    		$('.tr-expsRgst').show();
        }else{
        	$('.tr-expsRgst').hide();
        }
    });
});

var formSubmitObj = {
    submit : function(form){
        
        //제목
        if(!submitUtil.isEmpty(form.blcTitl)){
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
            location.replace('${contextPath}/${requestUri}/list.vc');
        }else{
            ajaxUtil.error(json);
        }
    }
};
</script>