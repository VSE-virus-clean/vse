<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%--
Description : 개인정보보호방침
--%>

<div id="contentWrap" class="">
	<h2 id="contentAnchor">콘텐츠 영역</h2>

	<div class="inner">
		<%@ include file="/WEB-INF/jsp/user/normal/vse/common/include/inc_privateTextarea.jsp" %>
	</div>
</div>
<script>
$(function(){
$('#header .gnb > li').eq(0).addClass('active');
});
</script>
