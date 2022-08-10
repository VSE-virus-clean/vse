<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    JSP Name : layout_default.jsp
    Description : 기본 Tiles Template
--%>

<!DOCTYPE html>
<html lang="ko">
<head>
<tiles:insertAttribute name="head" />
</head>
<body>
	<div id="wrap" class="sub">
		<tiles:insertAttribute name="header" />
		
		<tiles:insertAttribute name="content" />
		
		<tiles:insertAttribute name="footer" />
	</div>
</body>
</html>