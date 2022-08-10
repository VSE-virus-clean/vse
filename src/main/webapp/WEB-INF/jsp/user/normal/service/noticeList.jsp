<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
    JSP Name : noticeList.jsp
    Description : 서비스 > NOTICE 목록
--%>
<link rel="stylesheet" href="/resources/user/css/community.css?v=${cacheParam}">

<div id="container">
	<div class="location_bar">
		<ul class="clearfix inner pcTab">
			<li><a href="/index.vc">Home</a></li>
			<li><a href="#">Service</a></li>
			<li><a href="#">공지사항</a></li>
		</ul>
		<div class="mo inner">
			<a href="" ></a>
		</div>			
	</div>
	<div class="community">
		<div class="inner">
			<h3 class="p_tit01">공지사항</h3>
			
		</div>
		<div class="search_section notice_banner">
			<div class="inner">
				<!-- <p>바이러스 클린 랩 공지사항 입니다.</p> -->
				<form name="searchForm" method="get" class="search_bar" onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
					<input type="hidden" name="searchType" value="0" />
					<input type="search" id="searchKey" name="searchKey" title="검색어" maxlength="100" minlength="2" placeholder="검색어를 입력해주세요" />
					<button type="submit"><img src="/resources/user/ui_common/images/search_icon.png" alt=""></button>
				</form>
			</div>	
		</div>
		<div class="inner">
			<div class="table-type01 table-white community_list">
				<table>
					<colgroup>
						<col style="width:20%;">
						<col style="width:60%">
						<col style="width:20%;">
					</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>작성일</th>
						</tr>
					</thead>
					<tbody>
		                <c:if test="${not empty result.notiList}">
		                    <c:forEach items="${result.notiList}" var="data" varStatus="i">
		                        <tr class="notice">	
		                            <td>
		                            	<p>공지</p>
		                            </td>
		                            <td class="list_tit">
										<p>
											<a href="${contextPath}/${requestUri}/view.vc?blcSn=${data.blcSn}${function:searchQuery(result.searchInfo)}">
												<tag:html value="${data.blcTitl}" attr="NQ" />
											</a>
										</p>
										<c:if test="${not empty data.filNm}">
											<img style="margin-left:10px" src="/resources/user/images/community/clip.png" alt="">
										</c:if>
									</td>								
									<td>
										<p>${data.userViewDtm}</p>
									</td>
		                        </tr>
		                    </c:forEach>
		            	</c:if>
		            	
						<c:choose>
		                <c:when test="${empty result.list}">
		                    <tr>
		                        <td colspan="3" class="nodata">등록된 게시물이 없습니다.</td>
		                    </tr>
		                </c:when>
		                <c:otherwise>
		                    <c:forEach items="${result.list}" var="data" varStatus="i">
		                        <tr>
		                            <td>
		                            	<p>${function:rowNumber(result.searchInfo, i.count)}<p>
		                            </td>
		                            <td class="list_tit">
										<p>
											<a href="${contextPath}/${requestUri}/view.vc?blcSn=${data.blcSn}${function:searchQuery(result.searchInfo)}">
												<tag:html value="${data.blcTitl}" attr="NQ" />
											</a>
										</p>
										<c:if test="${not empty data.filNm}">
											<img src="/resources/user/images/community/clip.png" alt="">
										</c:if>
									</td>								
									<td>
										<p>${data.userViewDtm}</p>
									</td>
		                        </tr>
		                    </c:forEach>
		                </c:otherwise>
		            	</c:choose>
					</tbody>
				</table>
			</div>
			<div class="paging">
				<tag:paging2 url="${contextPath}/${requestUri}/list.vc?${function:searchQueryPaging(result.searchInfo)}" totalRow="${result.searchInfo.totalRow}" page="${result.searchInfo.page}" rowLimit="${result.searchInfo.rowLimit}" />
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
$(function () {
    $('form[name=searchForm]').ready(function(){
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