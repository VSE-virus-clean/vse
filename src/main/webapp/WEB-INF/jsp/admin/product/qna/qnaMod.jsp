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
	<h3 class="sec_tit">상품문의</h3>
	<ul class="top_tab">
		<li><a href="#">상품관리</a></li>
	</ul>
</div>
<div class="sec_cont">
	<h4 class="cont_tit">상품문의</h4>
	<div class="r_search_box">
	    <form name="mainForm" method="post" enctype="multipart/form-data" onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
	    	<input name="blcSn" type="hidden" value="${result.info.blcSn}"/>
	        <table class="r_search_table">
	            <colgroup>
	                 <col width="190px" /><col width="*" /><col width="190px" /><col width="*" />
	            </colgroup>
	            <tr>    
	                <th>등록자</th>
	                <td colspan="3">${result.info.mbrId} ( ${result.info.mbrNick} )</td> 
	            </tr>
	            <tr>    
	                <th>제목</th>
	                <td colspan="3">
	                	<tag:html value="${result.info.blcTitl}" attr="NQ" />
	                </td> 
	            </tr>
	            <tr>    
	                <th>공개여부</th>
	                <td colspan="3">
	                	${result.info.useYn eq 'Y' ? '공개' : '비공개'}
<!-- 	                    <label class="radio_box" for="useY"><input type="radio" name="useYn" id="useY" value="Y" /><span>공개</span></label>&nbsp;&nbsp; -->
<!-- 	                    <label class="radio_box" for="useN"><input type="radio" name="useYn" id="useN" value="N" /><span>비공개</span></label> -->
	                </td>  
	            </tr>
	            <tr>    
	                <th>문의유형</th>
	                <td colspan="3">${result.info.mgrpCd}</td> 
	            </tr>
	            <tr>    
	                <th>답변상태</th>
	                <td colspan="3">
	                	<c:choose>
							<c:when test="${result.info.asYn eq 'N'}"><p class="bold">답변대기</p></c:when>
							<c:when test="${result.info.asYn eq 'Y'}"><p class="gray">답변완료</p></c:when>
						</c:choose>
	                </td>
	            </tr>
	            <tr>    
	                <th>등록일</th>
	                <td colspan="3">${result.info.rgstDtm}</td>
	            </tr>
	            <tr>    
	                <th>문의 내용</th>
	                <td colspan="3" class="con">
	                    <tag:html value="${result.info.blcSbc1}" attr="BR" />
	                </td>  
	            </tr>
	            <tr>    
	                <th class="necessary">답변</th>
	                <td colspan="3" class="con">
	                	<c:choose>
							<c:when test="${result.info.asYn eq 'N'}">
								<textarea name="blcSbc2" id="blcSbc2" style="width:100%;height:500px;background:#ddd;" title="답변"></textarea>   
							</c:when>
							<c:when test="${result.info.asYn eq 'Y'}">
								<tag:html value="${result.info.blcSbc2}" attr="BR" />
							</c:when>
						</c:choose>
	                </td>   
	            </tr>
	        </table>
	        <div class="btn_center_gorup clearfix">
				<div class="left">
					<button type="button" class="btn btn_gray" onclick="location.href='${contextPath}/${requestUri}/list.vc?${function:searchQuery(result.searchInfo)}'; ">목록</button>
				</div>
				
				<c:if test="${result.info.asYn eq 'N'}">
					<div class="right">
						<button type="submit" class="btn btn_red">답변</button>
					</div>
				</c:if>
			</div>
	    </form>
	</div>
</div>

<script type="text/javascript">
$(function () {
    $('form[name=mainForm]').ready(function(){
//         $('input[name=useYn][value=${result.info.useYn}]').prop('checked', true).trigger('click');  
    });
});

var formSubmitObj = {
    submit : function(form){
        
        if(!submitUtil.isEmpty(form.blcSbc2)){
            return false;
        }
        
        ajaxUtil.formSubmit($(form), formSubmitObj.result);
        
        return false;
    },
    result : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        if(json.bindingStatus == undefined && json.result != undefined ){
           location.reload(true);
//            location.replace('${contextPath}/${requestUri}/view.vc?blcSn=${result.info.blcSn}${function:searchQuery(result.searchInfo)}');
        }else{
           ajaxUtil.error(json);
        }
    }
};
</script>