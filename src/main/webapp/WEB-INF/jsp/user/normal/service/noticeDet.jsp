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
    Description : 서비스 > NOTICE 상세조회
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
	<div class="communityview">
		<div class="inner">
			<h3 class="p_tit01">공지사항</h3>
			
		</div>	
		<!-- <div class="banner"></div> -->
		<div class="inner">
			<div class="tab_ui_mini">
				<div class="view_title">
					<p class="tit second"><tag:html value="${result.info.blcTitl}" attr="NQ" /></p>							
				</div>
				<div class="gallery_view">
					<div class="view_cont">
						<div class="review_cont_left">
							<img src="/resources/user/images/community/person.png" alt="">
							<div class="review_left_wrap">
								<p class="tit"><strong>${result.info.rgstId}</strong></p>
								<p class="date second">${result.info.userViewDtm}</p>
							</div>
						</div>
						<div class="review_cont_right">
							<c:if test="${not empty result.file.list}">
								<p><strong>첨부파일</strong></p>
								<p class="second">${function:printAttachFileList2("Y", "ATTCH", result.file.list)}</p>
							</c:if>
						</div>						
					</div>
					<div class="view_cont_img">
						<tag:html value="${result.info.blcSbc1}" attr="NQ" />
					</div>	
					<div class="btn_wrap">
						<a href="${contextPath}/${requestUri}/list.vc?${function:searchQuery(result.searchInfo)}" class="btn btn_pp" style="width: 200px;"><span>목록</span></a>
					</div>
				</div>
			</div>			
		</div>
	</div>
</div>
