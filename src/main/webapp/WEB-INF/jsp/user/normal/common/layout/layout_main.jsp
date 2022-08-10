<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    JSP Name : layout_maint.jsp
    Description : 메인  Tiles Template
--%>

<!DOCTYPE html>
<html lang="ko">
<head>
<tiles:insertAttribute name="head" />
</head>
<body>
	<div id="wrap">
		<tiles:insertAttribute name="header" />
		
		<div id="container">
			<tiles:insertAttribute name="content" />
			
			<tiles:insertAttribute name="footer" />
		</div>
	</div>
</body>
</html>