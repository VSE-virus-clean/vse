<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 
    JSP Name : layout_popUp.jsp
    Description : 팝업  Tiles Template
--%>

<!doctype html>
<html lang="ko">
<head>
    <tiles:insertAttribute name="head"/>
</head>
<body>
    <tiles:insertAttribute name="content"/>
    
    <div id="mask"></div>
	<div id="mask2"></div>
	<div id="loading-wrap"><img src="/resources/admin/images/common/loading.gif" alt="" /></div>
</body>
</html>