<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%--
    JSP Name : informationList.jsp
    Description : information 목록
--%>
<div id="container">
	<div class="tab_area">
        <div class="left">
            <a href="/information/list" class="btnbx2 bdefault ${empty result.searchInfo.mgrpCd ? 'on' :''}">すべて</a>
            <a href="/information/notice/list" class="btnbx2 bdefault ${result.searchInfo.mgrpCd eq 'NOTICE' ? 'on' :''}">最近情報FC告知</a>
            <a href="/information/news/list" class="btnbx2 bdefault ${result.searchInfo.mgrpCd eq 'NEWS' ? 'on' :''}">関連記事</a>
            <a href="/information/jihwan/list" class="btnbx2 bdefault ${result.searchInfo.mgrpCd eq 'JIHWAN' ? 'on' :''}">FC告知</a>
        </div>
    </div>
    
    <div class="board-list">
		<ul>
			<c:choose>
				<c:when test="${empty result.list}">
                    <li class="no-data">No Data.</li>
                </c:when>
                <c:otherwise>
					<c:forEach items="${result.list}" var="data" varStatus="i">
					<li class="${data.mgrpCd eq 'NOTICE' ? 'notice' : ''}">
						<a class="${data.secretYn eq 'Y' ? 'memberOnly' : ''}" href="/${requestUri}/view?blcSn=${data.blcSn}${function:searchQuery(result.searchInfo)}">
						<dl>
							<dt class="content-title">
								<span class="category ${data.notiYn eq 'Y' ? 'red2' : ''}">
									<c:choose>
									<c:when test="${data.mgrpCd eq 'NOTICE'}">最近情報</c:when>
									<c:when test="${data.mgrpCd eq 'NEWS'}">関連記事</c:when>
									<c:when test="${data.mgrpCd eq 'JIHWAN'}">FC告知</c:when>
									</c:choose>
								</span>
							</dt>
							<dd class="content-summary ${data.notiYn eq 'Y' ? 'red2' : ''}"><tag:html value="${data.blcTitl}" attr="NQ" /></dd>
							<dd class="content-thumb">${function:printImageFileByList(data.fileSn, "INFORMATION", data.filNm, data.filNm)}</dd>
							<dd class="content-date ${data.notiYn eq 'Y' ? 'red2' : ''}">${data.userViewDtm} / ${data.secretYn eq 'Y' ? 'Member Only' : 'Anyone Free'}</dd>
						</dl>
						</a>
					</li>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</ul>
	</div>	
    
    <div class="paginate">
        <tag:paging url="/${requestUri}/list?${function:searchQueryPaging(result.searchInfo)}" totalRow="${result.searchInfo.totalRow}" page="${result.searchInfo.page}" rowLimit="${result.searchInfo.rowLimit}" />
    </div>
</div>