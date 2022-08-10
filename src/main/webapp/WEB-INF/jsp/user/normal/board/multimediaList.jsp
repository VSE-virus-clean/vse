<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%--
    JSP Name : multimediaList.jsp
    Description : multimedia 목록
    author Jeong.hyoungjea
    since 2017.02.01.
    version 1.0
    Modification Information
       since          author              description
    ===========    =============    ===========================
    2017.02.01.   Jeong.hyoungjea     최초 생성
--%>
<style>
td img { max-height:80px; margin-right:10px; vertical-align: middle;}
</style>
<div id="container">
	<div class="tab_area">
        <div class="left">
            <a href="/multimedia/list" class="btnbx2 bdefault ${empty result.searchInfo.mgrpCd ? 'on' :''}">すべて</a>
            <a href="/multimedia/movie/list" class="btnbx2 bdefault ${result.searchInfo.mgrpCd eq 'MOVIE' ? 'on' :''}">MOVIE</a>
            <a href="/multimedia/photo/list" class="btnbx2 bdefault ${result.searchInfo.mgrpCd eq 'PHOTO' ? 'on' :''}">PHOTO</a>
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
								<span class="category">
									<c:choose>
									<c:when test="${data.mgrpCd eq 'MOVIE'}">MOVIE</c:when>
									<c:when test="${data.mgrpCd eq 'PHOTO'}">PHOTO</c:when>
									</c:choose>
								</span>
							</dt>
							<dd class="content-summary"><tag:html value="${data.blcTitl}" attr="NQ" /></dd>
							
							<c:choose>
							<c:when test="${data.secretYn eq 'Y'}">
								<c:if test="${not empty sessionScope.sessionVO  && sessionScope.sessionVO.authLevel eq 'S'}">
								<dd class="content-thumb">${function:printImageFileByList(data.fileSn, "MULTIMEDIA", data.filNm, data.filNm)}</dd>
								</c:if>
							</c:when>
							<c:otherwise>
								<dd class="content-thumb">${function:printImageFileByList(data.fileSn, "MULTIMEDIA", data.filNm, data.filNm)}</dd>
							</c:otherwise>
							</c:choose>
							
							<dd class="content-date">${data.userViewDtm} / ${data.secretYn eq 'Y' ? 'Member Only' : 'Anyone Free'}</dd>
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