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
	<h3 class="sec_tit">프로지원서 정보</h3>
	<ul class="top_tab">
		<li><a href="#">프로지원서관리</a></li>
	</ul>
</div>
<div class="sec_cont">
	<h4 class="cont_tit">상세정보</h4>
	<div class="r_search_box">
	    <form name="mainForm" method="post" action="${contextPath}/${requestUri}/delete.vc" onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
		    <input name="blcSn" type="hidden" value="${result.info.blcSn}"/>
	        <table class="r_search_table">
	            <colgroup>
	                <col width="170px" /><col width="*" />
	            </colgroup>
	            <tbody>
	            <tr>
	                <th>등록시간</th>
	                <td>${result.info.rgstDtm}</td>
	            </tr>
	            <tr>    
	                <th>이름</th>
	                <td>${result.info.rgstName}</td>    
	            </tr>
	            <tr>    
	                <th>연락처</th>
	                <td>${result.info.rgstHp}</td>    
	            </tr>
	            <tr>    
	                <th>성별</th>
	                <td>${result.info.rgstGenderCd}</td>    
	            </tr>
	            <tr>    
	                <th>자젹증</th>
	                <td>${result.info.item1}</td>    
	            </tr>
	            <tr>    
	                <th>주요경력사항</th>
	                <td><tag:html value="${result.info.blcSbc1}" attr="BR" /></td>    
	            </tr>
	            <tr>    
	                <th>선호 근무 지역</th>
	                <td>${result.info.dist1} ${not empty result.info.dist2 ? ' > ' : ''} ${result.info.dist2}</td>    
	            </tr>
	            <tr>    
	                <th>선호 근무 시간</th>
	                <td>${result.info.workTime}</td>    
	            </tr>
	            <tr>    
	                <th>지원사유</th>
	                <td><tag:html value="${result.info.blcSbc2}" attr="BR" /></td>    
	            </tr>
	            <tr>    
	                <th>첨부파일</th>
	                <td class="con">
	                	<p>${function:printAttachFileList2("Y", "ATTCH1", result.file.list)}</p>
	                	<p>${function:printAttachFileList2("Y", "ATTCH2", result.file.list)}</p>
	                </td>   
	            </tr>
	            </tbody>
	        </table>
	        <div class="btn_center_gorup clearfix">
				<div class="left">
					<button type="button" class="btn btn_gray" onclick="location.href='${contextPath}/${requestUri}/list.vc?${function:searchQuery(result.searchInfo)}'; ">목록</button>
				</div>
				<div class="right">
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