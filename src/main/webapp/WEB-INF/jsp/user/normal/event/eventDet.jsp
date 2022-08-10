<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
    JSP Name : eventDet.jsp
    Description : 이벤트 상세조회
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
	
	<div class="event event_view">
		<div class="inner">
			<h3 class="p_tit">Event</h3>
			<div class="tab_ui_mini">
				<ul class="tab">
					<li class="${pageMenuId eq 'KSDA' ? 'active' : '' }"><a href="/event/list.vc">전체</a></li>
					<li class="${pageMenuId eq 'KSDB' ? 'active' : '' }"><a href="/event/ing/list.vc">진행중인 이벤트</a></li>
					<li class="${pageMenuId eq 'KSDC' ? 'active' : '' }"><a href="/event/end/list.vc">종료된 이벤트</a></li>
				</ul>
				<div class="gallery_view">
					<div class="view_tit">
						<p class="tit"><tag:html value="${result.info.blcTitl}" attr="NQ" /></p>
						<p class="date">${result.info.expsRgstDay} ~ ${result.info.expsFnhDay}</p>
					</div>
					<div class="view_cont_wrap">
						<div class="view_cont_top">
							<ul>
								<li><strong>관리자</strong> ${result.info.userViewDtm}</li>
								<li><strong>조회</strong> ${result.info.blcRct}</li>
							</ul>
							<a href="javascript:;" class="btn-page-share"><img src="/resources/user/images/event/share_icon.png" alt=""></a>
						</div>
						
						<div class="view_cont">
							<tag:html value="${result.info.blcSbc1}" attr="NQ" />
						</div>
						
						<%-- 다음/이전글 --%>
						<div class="view_cont_bottom">
							<ul>
								<li class="none"> 
									<p>다음글 -</p>
									<c:choose>
									<c:when test="${not empty result.preInfo}">
										<div class="next_cont">
											<a href="${contextPath}/${requestUri}/view.vc?blcSn=${result.preInfo.blcSn}&rowNum=${result.preInfo.rowNum}${function:searchQuery(result.searchInfo)}">${result.preInfo.blcTitl}</a>
											<p class="date">${result.preInfo.expsRgstDay} ~ ${result.preInfo.expsFnhDay}</p>
										</div>
									</c:when>									
									<c:otherwise><a href="#">다음 글이 없습니다.</a></c:otherwise>
        							</c:choose>
								</li>
								<li> 
									<p>이전글 -</p>
									<c:choose>
									<c:when test="${not empty result.nextInfo}">
										<div class="prev_cont">
											<a href="${contextPath}/${requestUri}/view.vc?blcSn=${result.nextInfo.blcSn}&rowNum=${result.nextInfo.rowNum}${function:searchQuery(result.searchInfo)}">${result.nextInfo.blcTitl}</a>
											<p class="date">${result.nextInfo.expsRgstDay} ~ ${result.nextInfo.expsFnhDay}</p>
										</div>
									</c:when>									
									<c:otherwise><a href="#">이전 글이 없습니다.</a></c:otherwise>
        							</c:choose>
								</li>
							</ul>	
						</div>
					</div>
					<div class="btn_wrap">
						<a href="${contextPath}/${requestUri}/list.vc?${function:searchQuery(result.searchInfo)}" class="btn btn_pp" style="width: 200px;"><span>목록</span></a>
					</div>
				</div>
				
			</div>
		</div>			
	</div>
</div>

<script>
$(function(){
	$('.btn-page-share').attr('data-clipboard-text', document.location.href); 
    var clipboard = new Clipboard('.btn-page-share');
    clipboard.on('success', function(e) {
        alert('해당페이지 링크가 복사되었습니다.\nCtrl+V로 붙여넣어세요');
    });	
});

</script>
