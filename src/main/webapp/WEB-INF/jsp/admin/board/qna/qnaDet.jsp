<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
    JSP Name : qnaDet.jsp
    Description : QnA 상세조회
    author Jeong.hyoungjea
    since 2017.12.25
    version 1.0
    Modification Information
       since          author              description
    ===========    =============    ===========================
    2017.12.25     Jeong.hyoungjea     최초 생성
--%>
<!-- CONTENT -->
<div class="adm_con" class="notice">
	<%@ include file="/WEB-INF/jsp/admin/common/include/inc_breadcrumb.jsp" %>
    
    <h1>사용자 문의 상세보기</h1> 
	
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
                <th>작성자</th>
                <td>
                	<c:choose>
	                	<c:when test="${result.info.rgstSn ne 0}">
	                		${result.userInfo.mbrNm} (FC No. ${result.userInfo.mbrNo})
	                	</c:when>
	                	<c:otherwise>${result.info.mbrNick}</c:otherwise>
                	</c:choose>
                	<c:if test="${result.info.rgstSn ne 0}">
                		<a href="/admin/member/info/view.vc?mbrSn=${result.userInfo.mbrSn}" target="_blank" class="btnstyle blue">회원정보</a>
                	</c:if>
               	</td>
                <th>등록시간</th>
                <td>${result.info.rgstDtm}</td>
            </tr>
            <tr> 
            	<th>E-MAIL</th>
                <td colspan="3">${result.info.rgstEml}</td>    
            </tr>
            <tr>
                <th>답변자</th>
                <td>${empty result.info.mdfyId ? '-' : result.info.mdfyId}</td>
                <th>답변시간</th>
                <td>${empty result.info.mdfyDtm ? '-' : result.info.mdfyDtm}</td>
            </tr>
            <tr>    
                <th>질문 내용</th>
                <td colspan="3" class="con">
                    <tag:html value="${result.info.blcSbc1}" attr="BR" />
                </td>   
            </tr>
            <tr>    
                <th>답변 내용</th>
                <td colspan="3" class="con">
                    <tag:html value="${result.info.blcSbc2}" attr="BR" />
                </td>   
            </tr>
        </table>
        <div class="btn_area mgbtm30">
            <div class="btn_left">
                <a href="${contextPath}/${requestUri}/list.vc?${function:searchQuery(result.searchInfo)}" class="btnstyle blue">목록</a>
            </div>
            <div class="btn_right">
  	            <c:if test="${result.info.asYn eq 'N'}">
	                <a href="${contextPath}/${requestUri}/modify.vc?blcSn=${result.info.blcSn}${function:searchQuery(result.searchInfo)}" class="btnstyle blue">답변</a>
	                <a href="${contextPath}/${requestUri}/delete.vc" class="btnstyle dark submit">삭제</a>
                </c:if>
            </div>
        </div>
    </form>
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