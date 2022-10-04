<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%-- 
    JSP Name : inc_header.jsp
    Description : 페이지 상단을 장식한다. GNB영역이다.
--%>
<header>
	<div id="header">
		<div class="header_inner">
			<a href="${contextPath}/logoutMan.vc" onclick="location.replace(this.href); return false;">Sign out</a>
		</div>
	</div>
</header>
