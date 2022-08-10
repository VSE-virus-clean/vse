<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%--
    JSP Name : fromjihwanDet.jsp
    Description : fromjihwan 상세
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
			<span class="category">FROM.JIHWAN</span>
		</div>
		<div class="board-title"><tag:html value="${result.info.blcTitl}" attr="NQ" /></div>
		<div class="board-date">Date. ${result.info.userViewDtm}</div>
		<div class="board-content"><tag:html value="${result.info.blcSbc1}" attr="NQ" /></div>
	</div>
    <div class="btn_area mt20">
        <div class="right">
            <a href="/${requestUri}/list?${function:searchQuery(result.searchInfo)}" class="btnbx2 bdefault">List</a>
        </div>
    </div>
</div>