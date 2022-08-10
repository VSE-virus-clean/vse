<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<style>
.download { float: right; margin-top: -30px; }
.btn_file_download { display: inline-block; height: 20px; line-height: 20px; border: 1px solid #2E54AB; padding: 10px 20px; cursor: pointer; }
</style>
<%--
    JSP Name : multimediaList.jsp
    Description : multimedia 상세
    author Jeong.hyoungjea
    since 2017.02.01.
    version 1.0
    Modification Information
       since          author              description
    ===========    =============    ===========================
    2017.02.01.   Jeong.hyoungjea     최초 생성
--%>
<div id="container">
	<div>
		<div class="board-header">
			<span class="line"></span>
			<span class="category">
				<c:choose>
				<c:when test="${result.info.mgrpCd eq 'MOVIE'}">MOVIE</c:when>
				<c:when test="${result.info.mgrpCd eq 'PHOTO'}">PHOTO</c:when>
				</c:choose>
			</span>
		</div>
		<div class="board-title"><tag:html value="${result.info.blcTitl}" attr="NQ" /></div>
		<div class="board-date">Date. ${result.info.userViewDtm}</div>
		<div class="download">${function:printAttachFileList2("Y", "THUMB", result.file.list)}</div>
		<div class="board-content center">
			<c:choose>
            <c:when test="${result.info.mgrpCd eq 'MOVIE'}">
            	<p>${function:printImageFile("MP4", result.file.list)}</p>
            	
            	<c:if test="${not empty result.info.rltdLk}">
                	<p><iframe width="900" height="500" src="//www.youtube-nocookie.com/embed/${result.info.rltdLk}?rel=0&showinfo=0&autoplay=1" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe></p>
               	</c:if>
            </c:when>
            <c:when test="${result.info.mgrpCd eq 'PHOTO'}">
            	<p>${function:printImageFile("THUMB", result.file.list)}</p>
            </c:when>
            </c:choose>
            <c:if test="${not empty result.info.blcSbc1}">
            	<p style="height:30px;"></p>
            	<tag:html value="${result.info.blcSbc1}" attr="NQ" />
            </c:if>
		</div>
	</div>
    <div class="btn_area mt20">
        <div class="right">
            <a href="/${requestUri}/list?${function:searchQuery(result.searchInfo)}" class="btnbx2 bdefault">List</a>
        </div>
    </div>
</div>