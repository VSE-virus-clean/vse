<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
    JSP Name : qnaList.jsp
    Description : QnA 목록
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
    
    <h1>사용자 문의 목록</h1> 
    
    <div class="tbl_toparea mgbtm10">
        <div class="dvright">
            <form name="searchForm" method="get"  onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
                <fieldset>
                    <legend>검색영역</legend>
                    <select id="searchType" name="searchType">
                        <option value="0">제목 + 내용</option>
                        <option value="1">제목</option>
                        <option value="2">내용</option>
                        
                        <option value="name">NAME</option>
                        <option value="email">EMAIL</option>
                    </select>
                    <input id="searchKey" name="searchKey" type="text" title="상세검색입력" style="width:250px;" maxlength="100" minlength="2"/>
                    <a href="#" class="btnstyle black submit">검색</a>
                </fieldset>
            </form>
        </div>
    </div>
    
    <table cellspacing="0" class="tbl_list mgbtm10">
        <thead>
            <tr>
                <th style="width:50px;">번호</th>
                <th>제목</th>
                <th style="width:130px;">작성일</th>
                <th style="width:110px;">작성자</th>
                <th style="width:130px;">답변일</th>
                <th style="width:110px;">답변자</th>
            </tr>
        </thead>
        <tbody>
            <c:choose>
                <c:when test="${empty result.list}">
                    <tr>
                        <td colspan="6" class="nodata">등록된 게시물이 없습니다.</td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <c:forEach items="${result.list}" var="data" varStatus="i">
                        <tr>
                            <td>${function:rowNumber(result.searchInfo, i.count)}</td>
                            <td class="left">
                                <a href="${contextPath}/${requestUri}/view.vc?blcSn=${data.blcSn}${function:searchQuery(result.searchInfo)}"><tag:html value="${data.blcTitl}" attr="NQ" /></a>
                            </td>
                            <td>${data.rgstDtm}</td>
                            <td>${data.mbrNick}</td>
                            <td><span class="${empty data.mdfyId ? '' : 'rtxt'}">${empty data.mdfyDtm ? '-' : data.mdfyDtm}</span></td>
                            <td><span class="${empty data.mdfyId ? '' : 'rtxt'}">${empty data.mdfyId ? '-' : data.mdfyId}</span></td>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
        </tbody>
    </table>
    
<!--     <div class="btn_area"> -->
<!--         <div class="btn_right"> -->
<%--             <a href="${contextPath}/${requestUri}/register.vc" class="btnstyle blue">등록</a> --%>
<!--         </div> -->
<!--     </div> -->
    
    <div class="paging_wrap">
        <tag:paging url="${contextPath}/${requestUri}/list.vc?${function:searchQueryPaging(result.searchInfo)}" totalRow="${result.searchInfo.totalRow}" page="${result.searchInfo.page}" rowLimit="${result.searchInfo.rowLimit}" />
    </div>
    
</div>

<script type="text/javascript">
$(function () {
    $('form[name=searchForm]').ready(function(){
    	$('select[name=searchType] option[value="${result.searchInfo.searchType}"]').prop('selected', true);
        $('input[name=searchKey]').val('${result.searchInfo.searchKey}');  
    });
});

var formSubmitObj = {
    submit : function(form){
        if(!submitUtil.isNull(form.searchKey)){
            if(!submitUtil.isMinLength(form.searchKey)){
                return false;    
            }        
        }  
        
        return true;
    }
};
</script>