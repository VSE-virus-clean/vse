<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%--
    JSP Name : qnaDet.jsp
    Description : 문의 상세보기
    author Jeong.hyoungjea
    since 2017.02.01.
    version 1.0
    Modification Information
       since          author              description
    ===========    =============    ===========================
    2017.02.01.   Jeong.hyoungjea     최초 생성
--%>
<div id="container">
	<div class="tab_area">
        <div class="left">
            <a href="/member/myInfo/changeInfo" class="btnbx2 bdefault">会員情報変更</a>
            <a href="/member/qna/list" class="btnbx2 bdefault on">お問合せリスト</a>
            <a href="/member/order/list" class="btnbx2 bdefault">購入リスト</a>
        </div>
    </div>
    
    <div class="board-list">
    	<div class="board-title"><tag:html value="${result.info.blcTitl}" attr="NQ" /></div>
		<div class="board-date">Date. ${result.info.rgstDtm}</div>
		<div class="board-content"><tag:html value="${result.info.blcSbc1}" attr="BR" /></div>
		<div class="board-content" style="border-top:0px;margin-top:0;">
			<c:choose>
				<c:when test="${not empty result.info.blcSbc2}"><tag:html value="${result.info.blcSbc2}" attr="BR" /></c:when>
				<c:otherwise>返事を作成中です。</c:otherwise>
			</c:choose>
		</div>
	</div>	
	
	<div class="btn_area mt20">
        <div class="right">
            <a href="/${requestUri}/list?${function:searchQuery(result.searchInfo)}" class="btnbx2 bdefault">List</a>
        </div>
    </div>
</div>