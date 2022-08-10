<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
    JSP Name : inc_breadcrumb.jsp
    Description : BreadCrumb
--%>
<div class="loca">
    <span><a href="${contextPath}/mainMan.vc"><img src="/resources/admin/images/ico_home.gif" alt="/" /></a></span>
<%--     <c:forTokens var="menuNm" items="${sessionScope.sessionVO.menuPathNm}" varStatus="i" begin="0" end="2" delims="|"> --%>
<%--         <span>&gt; ${menuNm}</span> --%>
<%--     </c:forTokens> --%>
</div>
<%-- <h1>${sessionScope.sessionVO.menuNm}</h1> --%>

