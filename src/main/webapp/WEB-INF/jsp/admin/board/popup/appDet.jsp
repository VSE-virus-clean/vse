<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
    JSP Name : noticeDet.jsp
    Description : 게시판관리 > INFORMATION > NOTICE 상세조회
--%>
<div class="sec_top">
	<h3 class="sec_tit">팝업 정보</h3>
	<ul class="top_tab">
		<li><a href="#">배너&amp;팝업관리</a></li>
		<li><a href="#">APP 팝업관리</a></li>
	</ul>
</div>
<div class="sec_cont">
	<h4 class="cont_tit">상세정보</h4>
	<div class="r_search_box">
	    <form name="mainForm" method="post" action="${contextPath}/${requestUri}/delete.vc" onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
		    <input name="blcSn" type="hidden" value="${result.info.blcSn}"/>
	        <table class="r_search_table">
	            <colgroup>
	                <col width="170px" /><col width="*" /><col width="170px" /><col width="*" />
	            </colgroup>
	            <tr>    
	                <th>제목</th>
	                <td colspan="3"><tag:html value="${result.info.blcTitl}" attr="NQ" /></td>    
	            </tr>
	            
	            <tr>
	            	<th>카테고리</th>
	                <td colspan="3">${result.info.mgrpCd}</td>     
	            </tr>
	            <tr>
	            	<th>공개여부</th>
	                <td colspan="3">${'Y' eq result.info.useYn ? '공개' :'비공개'}</td>     
	            </tr>
	            <tr>
	                <th>작성자</th>
	                <td>${result.info.rgstId}</td>
	                <th>등록시간</th>
	                <td>${result.info.rgstDtm}</td>
	            </tr>
	            <tr>    
	                <th>노출시간</th>
	                <td colspan="3">${result.info.expsRgstDtm} ~ ${result.info.expsFnhDtm}</td> 
	            </tr>
	            <tr>    
	                <th>조회수</th>
	                <td colspan="3">${result.info.blcRct}</td>
	            </tr>
	            <tr>    
	                <th>링크</th>
	                <td colspan="3" class="con">
	                    <a href="${result.info.rltdLk}">${result.info.rltdLk}</a>
	                </td>   
	            </tr>
	            <tr>    
	                <th>내용</th>
	                <td colspan="3" class="con">
	                    <tag:html value="${result.info.blcSbc1}" attr="BR" />
	                </td>   
	            </tr>
	            <tr>    
	                <th>팝업 이미지</th>
	                <td colspan="3" class="con">
	                	<p>${function:printImageFile("THUMB", result.file.list)}</p>
	                </td>   
	            </tr>
	        </table>
	        
	        <div class="btn_center_gorup clearfix">
				<div class="left">
					<button type="button" class="btn btn_gray" onclick="location.href='${contextPath}/${requestUri}/list.vc?${function:searchQuery(result.searchInfo)}'; ">목록</button>
				</div>
				<div class="right">
					<button type="button" class="btn btn_gray"  onclick="location.href='${contextPath}/${requestUri}/modify.vc?blcSn=${result.info.blcSn}${function:searchQuery(result.searchInfo)}'; ">수정하기</button>
					<button type="submit" class="btn btn_red" onclick="">삭제</button>
				</div>
			</div>
	    </form>
   	</div>
</div>


<script type="text/javascript">
var formSubmitObj = {
    submit : function(form){
        if (confirm('<spring:message code="msg.C02" />')) {
            ajaxUtil.postDisableAsync($(form).attr('action'), $(form).serialize(), formSubmitObj.result);    
        }
        return false;
    },
    result : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        if(json.bindingStatus == undefined && json.result != undefined){
           location.replace('${contextPath}/${requestUri}/list.vc?${function:searchQuery(result.searchInfo)}');
        }else{
           
        }
    }
};
</script>