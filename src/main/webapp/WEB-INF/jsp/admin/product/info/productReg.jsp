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
	<h3 class="sec_tit">상품 등록</h3>
	<ul class="top_tab">
		<li><a href="#">상품관리</a></li>
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
	                <th class="necessary">상품명</th>
	                <td colspan="3">
	                    <input type="text" id="prdTitl" name="prdTitl" maxlength="100" style="width:80%;" title="제목" />
	                </td> 
	            </tr>
	            <tr>    
	                <th>카테고리</th>
	                <td colspan="3">
	                    <label class="radio_box" for="lgrpCd1"><input type="radio" name="lgrpCd" id="lgrpCd1" value="VCL" /><span>바이러스 클린 랩</span></label>&nbsp;&nbsp;
	                    <label class="radio_box" for="lgrpCd2"><input type="radio" name="lgrpCd" id="lgrpCd2" value="ACC" /><span>엑세서리</span></label>
	                </td> 
	            </tr>
	            <tr>    
	                <th>상품노출여부</th>
	                <td colspan="3">
	                    <label class="radio_box" for="useY"><input type="radio" name="useYn" id="useY" value="Y" /><span>노출</span></label>&nbsp;&nbsp;
	                    <label class="radio_box" for="useN"><input type="radio" name="useYn" id="useN" value="N" /><span>비노출</span></label>
	                </td>    
	            </tr>
	            <tr>    
	                <th>품절여부</th>
	                <td colspan="3">
	                    <label class="radio_box" for="sellY"><input type="radio" name="sellYn" id="sellY" value="Y" /><span>재고있음</span></label>&nbsp;&nbsp;
	                    <label class="radio_box" for="sellN"><input type="radio" name="sellYn" id="sellN" value="N" /><span>품절</span></label>
	                </td>    
	            </tr>
	            <tr>    
	                <th>노출 시작시간</th>
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
	                    </ul>
	                </td>
	            </tr>
	            <tr>    
	                <th>노출 만료시간</th>
	                <td colspan="3">
	                    <input type="text" id="expsFnhDay" name="expsFnhDay" class="datepicker date_input" readonly="readonly" autocomplete="off"/>
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
	                <th>공급가격</th>
	                <td colspan="3">
	                    <input type="number" class="numOnly" id="supplyPrice" name="supplyPrice" maxlength="7" style="width:300px;" title="공급가격" value="0" /> 원
	                </td>   
	            </tr>
	            <tr>    
	                <th class="necessary">판매가격</th>
	                <td colspan="3">
	                    <input type="number" class="numOnly" id="salePrice" name="salePrice" maxlength="7" style="width:300px;" title="판매가격" value="0" /> 원
	                </td>   
	            </tr>
	            <tr>    
	                <th>상품재고수량</th>
	                <td colspan="3">
	                    <input type="number" class="numOnly" id="quantity" name="quantity" maxlength="7" style="width:300px;" title="상품재고수량" value="0" /> 개
	                </td>   
	            </tr>
	            <tr>    
	                <th>상품코드</th>
	                <td colspan="3">
	                    <input type="text" id="prdCd" name="prdCd" maxlength="100" style="width:80%;" title="상품코드" />
	                </td>   
	            </tr>
	            <tr>    
	                <th class="necessary">대표이미지</th>
	                <td colspan="3">
	                	<div class="filebox">
	                   		${function:printAttachFileReg(1, "THUMB", "대표이미지")}
	                    </div>
	                    <ul class="warn">
	                     	<li>- 이미지 파일만 등록 가능합니다.(JPG, PNG, GIF)</li>
	                    </ul>
	                </td>   
	            </tr>
	            <tr>    
	                <th rowspan="3" class="necessary">추가이미지</th>
	                <td colspan="3">
	                	<div class="filebox">
	                   		${function:printAttachFileReg(2, "THUMB1", "추가이미지1")}
	                    </div>
	                    <ul class="warn">
	                     	<li>- 이미지 파일만 등록 가능합니다.(JPG, PNG, GIF)</li>
	                    </ul>
	                </td>   
	            </tr>
	             <tr>    
	                <td colspan="3">
	                    <div class="filebox">
	                   		${function:printAttachFileReg(3, "THUMB2", "추가이미지2")}
	                    </div>
	                    <ul class="warn">
	                     	<li>- 이미지 파일만 등록 가능합니다.(JPG, PNG, GIF)</li>
	                    </ul>
	                </td>   
	            </tr>
	             <tr>    
	                <td colspan="3">
	                    <div class="filebox">
	                   		${function:printAttachFileReg(4, "THUMB3", "추가이미지3")}
	                    </div>
	                    <ul class="warn">
	                     	<li>- 이미지 파일만 등록 가능합니다.(JPG, PNG, GIF)</li>
	                    </ul>
	                </td>   
	            </tr>
<!-- 	            <tr>     -->
<!-- 	                <th>상품요약</th> -->
<!-- 	                <td colspan="3"> -->
<!-- 	                    <textarea name="prdSbc1" id="prdSbc1" style="width:100%;height:200px;" title="상품요약"></textarea> -->
<!-- 	                </td>    -->
<!-- 	            </tr> -->
	            <tr>    
	                <th>상품상세</th>
	                <td colspan="3">
	                    <textarea name="prdSbc2" id="prdSbc2" style="width:100%;height:500px;" title="상품상세"></textarea>
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
smarteditor.create([{'id':'prdSbc2', 'htmlMode':true}]);
$(function () {
    $('form[name=mainForm]').ready(function(){
    	$('input[name=lgrpCd][value="VCL"]').prop('checked', true).trigger('click'); 
    	$('input[name=useYn][value="Y"]').prop('checked', true).trigger('click'); 
    	$('input[name=sellYn][value="Y"]').prop('checked', true).trigger('click'); 
    });
});

var formSubmitObj = {
    submit : function(form){
        
        //제목
        if(!submitUtil.isEmpty(form.prdTitl)){
            return false;
        }
        
        //등록일
        if(!submitUtil.isNull(form.expsRgstDay)){
            if(!submitUtil.isDateFormat(form.expsRgstDay)){
                return false;
            }
        }
        
        if(!submitUtil.isNull(form.expsFnhDay)){
            if(!submitUtil.isDateFormat(form.expsFnhDay)){
                return false;
            }
        }
        
        if(!submitUtil.isNull(form.expsRgstDay) && !submitUtil.isNull(form.expsFnhDay)){
    		if(!submitUtil.isDateCompare(form.expsRgstDay, form.expsFnhDay)){
            	return false;
    		}
        }
        
      	//에디터 내용 삽입
        prdSbc2Editors.getById["prdSbc2"].exec("UPDATE_CONTENTS_FIELD", []);
        
        if(!submitUtil.isEmpty(form.prdSbc2)){
            return false;
        }
        
        //필수
        if(!submitUtil.isEmpty(form.attachFile1)){
        	return false;
        }else{
        	if(!submitUtil.isAttachFile(form.attachFile1, 'IMG')){
                return false;
            }
        }
        
        if(!submitUtil.isEmpty(form.attachFile2)){
        	return false;
        }else{
        	if(!submitUtil.isAttachFile(form.attachFile2, 'IMG')){
                return false;
            }
        }
        
//      비필수
        if(!submitUtil.isNull(form.attachFile3)){
            if(!submitUtil.isAttachFile(form.attachFile3, 'IMG')){
                return false;
            }
        }
        
//      비필수
        if(!submitUtil.isNull(form.attachFile4)){
            if(!submitUtil.isAttachFile(form.attachFile4, 'IMG')){
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