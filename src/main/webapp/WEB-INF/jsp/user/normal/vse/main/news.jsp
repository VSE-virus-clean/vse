<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
    Description : NEWS
--%>

<div id="contentWrap" class="">
	<h2 id="contentAnchor">콘텐츠 영역</h2>
	<h3 class="tit"> NEWS </h3>

	<div class="bg_gray search_wrap mt50">
		<div class="inner">
			<form name="searchForm" method="get" class="search_bar" onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
				<select name="searchType" class="select_type_01 search_select">
					<option value="0">ALL</option>
                       <option value="1">TITLE</option>
                       <option value="2">CONTENTS</option>
				</select>
				<div class="search_type_01">
					<input type="search" id="searchKey" name="searchKey" title="검색어" maxlength="100" minlength="2" placeholder="Please enter the contents." value="${result.searchInfo.searchKey}"/>
					<a class="btn_search submit" href="#" title="Search">
						<img src="/resources/vse/images/btn/btn_search.png" alt="search icon">
					</a>
				</div>
			</form>
		</div>
	</div>
	<div class="inner mt60">
		
		<div class="clear mb20">
			<span class="f_r mt20">Total of <em class="color_red"><fmt:formatNumber value="${result.searchInfo.totalRow}" type="number"/></em> | Page <em class="color_red"><fmt:parseNumber value="${result.searchInfo.totalRow % 10}" integerOnly="true"/></em></span>
		</div>
		
		<div class="gallery_list">
			<ul class="clear">
				<c:choose>
	                <c:when test="${empty result.list}">
	                    <li class="nodata">No Data.</li>
	                </c:when>
	                <c:otherwise>
	                    <c:forEach items="${result.list}" var="data" varStatus="i">
	                    	<li class="box <c:if test="${function:isNewDif(data.expsRgstDay, 7) eq true}"> new</c:if>">
								<a href="${contextPath}/${requestUri}/view.vse?blcSn=${data.blcSn}&rowNum=${data.rowNum}${function:searchQuery(result.searchInfo)}">
									<div class="img_box">
										${function:printImageFileByList(data.fileSn, data.lgrpCd, data.filNm, data.filNm)}
									</div>
									<div class="txt_box">
										<p class="event_tit"><tag:html value="${data.blcTitl}" attr="NQ" /></p>
										<p class="date">${data.userViewDtm}</p>
									</div>												
								</a>
							</li>	
	                    </c:forEach>
	                </c:otherwise>
	            </c:choose>
			</ul>
		</div>
		
		<div class="paging mt40">
			<tag:paging url="${contextPath}/${requestUri}/list.vse?${function:searchQueryPaging(result.searchInfo)}" totalRow="${result.searchInfo.totalRow}" page="${result.searchInfo.page}" rowLimit="${result.searchInfo.rowLimit}" />
		</div>
		
    </div>


</div>
<script>
$(function(){
	
	 $('form[name=searchForm]').ready(function(){
		$('select[name=searchType] option[value="${result.searchInfo.searchType}"]').prop('selected', true);
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

