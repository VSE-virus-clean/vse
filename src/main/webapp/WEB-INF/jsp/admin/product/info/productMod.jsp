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
	<h3 class="sec_tit">상품 수정</h3>
	<ul class="top_tab">
		<li><a href="#">상품관리</a></li>
	</ul>
</div>
<div class="sec_cont">
	<h4 class="cont_tit">정보입력</h4>
	<div class="r_search_box">
		
	    <form name="mainForm" method="post" enctype="multipart/form-data" onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
	    	<input name="prdSn" type="hidden" value="${result.info.prdSn}"/>
	        <table class="r_search_table">
	            <colgroup>
	                 <col width="170px" /><col width="*" /><col width="170px" /><col width="*" />
	            </colgroup>
	            <tr>    
	                <th class="necessary">상품명</th>
	                <td colspan="3">
	                    <input type="text" id="prdTitl" name="prdTitl" maxlength="100" style="width:80%;" title="제목" value="${result.info.prdTitl}" />
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
	                <th>노출 만료시간</th>
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
	                    <ul class="warn">
	                     	<li>- 지정하신 일/시간/분 이후에 사이트에 노출 되지 않습니다.</li>
	                    </ul>
	                </td>
	            </tr>
	            <tr>    
	                <th>공급가격</th>
	                <td colspan="3">
	                    <input type="number" class="numOnly" id="supplyPrice" name="supplyPrice" maxlength="7" style="width:300px;" title="공급가격" value="${result.info.supplyPrice}"/> 원
	                </td>   
	            </tr>
	            <tr>    
	                <th class="necessary">판매가격</th>
	                <td colspan="3">
	                    <input type="number" class="numOnly" id="salePrice" name="salePrice" maxlength="7" style="width:300px;" title="판매가격" value="${result.info.salePrice}"/> 원
	                </td>   
	            </tr>
	            <tr>    
	                <th>상품재고수량</th>
	                <td colspan="3">
	                    <input type="number" class="numOnly" id="quantity" name="quantity" maxlength="7" style="width:300px;" title="상품재고수량" value="${result.info.quantity}"/> 개
	                </td>   
	            </tr>
	            <tr>    
	                <th>상품코드</th>
	                <td colspan="3">
	                    <input type="text" id="prdCd" name="prdCd" maxlength="100" style="width:80%;" title="상품코드" value="${result.info.prdCd}"/>
	                </td>   
	            </tr>
	            <tr>    
	                <th class="necessary">대표이미지</th>
	                <td colspan="3" class="con">
	                	<div class="filebox">
		                	<p>${function:printImageFile("THUMB", result.file.list)}</p>
		                    ${function:printAttachFileModList2("Y", 1, "THUMB", "대표이미지", result.file.list, "Y")}
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
	                		<p>${function:printImageFile("THUMB1", result.file.list)}</p>
		                    ${function:printAttachFileModList2("Y", 2, "THUMB1", "대표이미지", result.file.list, "Y")}
	                    </div>
	                    <ul class="warn">
	                     	<li>- 이미지 파일만 등록 가능합니다.(JPG, PNG, GIF)</li>
	                    </ul>
	                </td>   
	            </tr>
	             <tr>    
	                <td colspan="3">
	                    <div class="filebox">
	                		<p>${function:printImageFile("THUMB2", result.file.list)}</p>
		                    ${function:printAttachFileModList2("Y", 3, "THUMB2", "대표이미지", result.file.list, "Y")}
	                    </div>
	                    <ul class="warn">
	                     	<li>- 이미지 파일만 등록 가능합니다.(JPG, PNG, GIF)</li>
	                    </ul>
	                </td>   
	            </tr>
	             <tr>    
	                <td colspan="3">
	                    <div class="filebox">
	                		<p>${function:printImageFile("THUMB3", result.file.list)}</p>
		                    ${function:printAttachFileModList2("Y", 4, "THUMB3", "대표이미지", result.file.list, "Y")}
	                    </div>
	                    <ul class="warn">
	                     	<li>- 이미지 파일만 등록 가능합니다.(JPG, PNG, GIF)</li>
	                    </ul>
	                </td>   
	            </tr>
<!-- 	            <tr>     -->
<!-- 	                <th>상품요약</th> -->
<!-- 	                <td colspan="3"> -->
<%-- 	                    <textarea name="prdSbc1" id="prdSbc1" style="width:100%;height:200px;" title="상품요약">${result.info.prdSbc1}</textarea> --%>
<!-- 	                </td>    -->
<!-- 	            </tr> -->
	            <tr>    
	                <th>상품상세</th>
	                <td colspan="3">
	                    <textarea name="prdSbc2" id="prdSbc2" style="width:100%;height:500px;" title="상품상세">${result.info.prdSbc2}</textarea>
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
smarteditor.create([{'id':'prdSbc2', 'htmlMode':true}]);
$(function () {
    $('form[name=mainForm]').ready(function(){
    	$('input[name=useYn][value=${result.info.useYn}]').prop('checked', true).trigger('click');  
		$('input[name=lgrpCd][value=${result.info.lgrpCd}]').prop('checked', true).trigger('click'); 
    	$('input[name=useYn][value=${result.info.useYn}]').prop('checked', true).trigger('click'); 
    	$('input[name=sellYn][value=${result.info.sellYn}]').prop('checked', true).trigger('click'); 
    	<c:if test="${not empty result.info.expsRgstTime}">
        	$('select[name=expsRgstTime] option[value=${result.info.expsRgstTime}]').prop('selected', true);
	        $('select[name=expsRgstMinute] option[value=${result.info.expsRgstMinute}]').prop('selected', true);
	        $('select[name=expsFnhTime] option[value=${result.info.expsFnhTime}]').prop('selected', true);
	        $('select[name=expsFnhMinute] option[value=${result.info.expsFnhMinute}]').prop('selected', true);
        </c:if>
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

        if(!submitUtil.isNull(form.attachFile1)){
        	if(!submitUtil.isAttachFile(form.attachFile1, 'IMG')){
                return false;
            }
        }
        
        if(!submitUtil.isNull(form.attachFile2)){
        	if(!submitUtil.isAttachFile(form.attachFile2, 'IMG')){
                return false;
            }
        }
        
        if(!submitUtil.isNull(form.attachFile3)){
        	if(!submitUtil.isAttachFile(form.attachFile3, 'IMG')){
                return false;
            }
        }
        
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
           location.reload(true);
//            location.replace('${contextPath}/${requestUri}/view.vc?prdSn=${result.info.prdSn}${function:searchQuery(result.searchInfo)}');
        }else{
           ajaxUtil.error(json);
        }
    }
};
</script>