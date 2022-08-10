<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
    JSP Name : /eventList.jsp
    Description : 이벤트 목록
--%>
<link rel="stylesheet" href="/resources/user/css/event.css?v=${cacheParam}">

<div id="container">
	<div class="location_bar">
		<ul class="clearfix inner pcTab">
			<li><a href="/index.vc">Home</a></li>
			<li><a href="#">Event</a></li>
		</ul>
		<div class="mo inner">
			<a href="" ></a>
		</div>			
	</div>
	<div class="event">
		<div class="inner">
			<h3 class="p_tit01">Event</h3>
			<div class="tab_ui_mini">
				<ul class="tab">
					<li class="${pageMenuId eq 'KSDA' ? 'active' : '' }"><a href="/event/list.vc">전체</a></li>
					<li class="${pageMenuId eq 'KSDB' ? 'active' : '' }"><a href="/event/ing/list.vc">진행중인 이벤트</a></li>
					<li class="${pageMenuId eq 'KSDC' ? 'active' : '' }"><a href="/event/end/list.vc">종료된 이벤트</a></li>
				</ul>
				<div class="tab_cont">
					<ul>
						<li class="active">
							<div class="gallery_list">
								<ul class="clearfix">
									<c:choose>
					                <c:when test="${empty result.list}">
					                    <li class="nodata">등록된 게시물이 없습니다.</li>
					                </c:when>
					                <c:otherwise>
					                    <c:forEach items="${result.list}" var="data" varStatus="i">
					                    	<c:choose>
				                    		<c:when test="${function:isClosed(data.expsFnhDay)}">
				                    			<li class="box end">
													<a href="javascript:;">
														<div class="img_box">
															${function:printImageFileByList(data.fileSn, data.lgrpCd, data.filNm, data.filNm)}
														</div>
														<div class="txt_box">
															<p class="event_tit"><tag:html value="${data.blcTitl}" attr="NQ" /></p>
															<p class="date">${data.expsRgstDay} ~ ${data.expsFnhDay}</p>
														</div>												
													</a>
												</li>	
				                    		</c:when>
				                    		<c:otherwise>
				                    			<li class="box <c:if test="${function:isNewDif(data.expsRgstDay, 7) eq true}"> new</c:if>">
													<a href="${contextPath}/${requestUri}/view.vc?blcSn=${data.blcSn}&rowNum=${data.rowNum}${function:searchQuery(result.searchInfo)}">
														<div class="img_box">
															${function:printImageFileByList(data.fileSn, data.lgrpCd, data.filNm, data.filNm)}
														</div>
														<div class="txt_box">
															<p class="event_tit"><tag:html value="${data.blcTitl}" attr="NQ" /></p>
															<p class="date">${data.expsRgstDay} ~ ${data.expsFnhDay}</p>
														</div>												
													</a>
												</li>	
				                    		</c:otherwise>
					                    	</c:choose>
					                    </c:forEach>
					                </c:otherwise>
					            	</c:choose>
								</ul>
							</div>	
							<div class="paging">
								<tag:paging2 url="${contextPath}/${requestUri}/list.vc?${function:searchQueryPaging(result.searchInfo)}" totalRow="${result.searchInfo.totalRow}" page="${result.searchInfo.page}" rowLimit="${result.searchInfo.rowLimit}" />
							</div>
						</li>
					</ul>
				</div>
			</div>
		</div>			
	</div>
</div>
