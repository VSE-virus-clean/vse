<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%--
    JSP Name : qnaList.jsp
    Description : 문의 목록
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
    	<table cellspacing="0" class="tbl_list">
	        <thead>
	            <tr>
	                <th style="width:150px;">作成日</th>
	                <th>お問合せタイトル</th>
	                <th style="width:150px;">返信日</th>
	            </tr>
	        </thead>
	        <tbody>
	            <c:choose>
	                <c:when test="${empty result.list}">
	                    <tr>
	                        <td colspan="3">お問い合わせ内容がありません。</td>
	                    </tr>
	                </c:when>
	                <c:otherwise>
	                    <c:forEach items="${result.list}" var="data" varStatus="i">
	                        <tr>
	                            <td>${data.rgstDtm}</td>
	                            <td class="left">
	                            	<a href="/${requestUri}/view?blcSn=${data.blcSn}"><tag:html value="${data.blcTitl}" attr="NQ" /></a>
	                            </td>
	                            <td><span class="${empty data.mdfyId ? '' : 'rtxt'}">${empty data.mdfyDtm ? '-' : data.mdfyDtm}</span></td>
	                        </tr>
	                    </c:forEach>
	                </c:otherwise>
	            </c:choose>
	        </tbody>
	    </table>
	</div>	
	
    <div class="paginate">
        <tag:paging url="/${requestUri}/list?${function:searchQueryPaging(result.searchInfo)}" totalRow="${result.searchInfo.totalRow}" page="${result.searchInfo.page}" rowLimit="${result.searchInfo.rowLimit}" />
    </div>
</div>