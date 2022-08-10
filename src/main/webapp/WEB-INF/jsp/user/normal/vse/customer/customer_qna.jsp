<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
    Description : 고객지원 > FAQ
--%>

<div id="contentWrap" class="">
	<h2 id="contentAnchor">콘텐츠 영역</h2>
	<h3 class="tit"> FAQ 자주하는 질문 </h3>
	<div class="inner mt100">
		<ul class="faq_wrap">
			<c:choose>
			<c:when test="${empty result.list}">
				<li class="nodata">검색 결과가 없습니다.</li>
			</c:when>
			<c:otherwise>
				<c:forEach items="${result.list}" var="data" varStatus="i">
					<li>
						<a class="faq_tit" href="javascript:;">${data.blcTitl}</a>
						<div class="faq_con editor_wrap"><tag:html value="${data.blcSbc1}" attr="NQ" /><div>
					</li>
				</c:forEach>
			</c:otherwise>
			</c:choose>
		</ul>
	</div>
</div>
<script>
$(function(){
	$('#header .gnb > li').eq(4).addClass('active');
});
</script>
			