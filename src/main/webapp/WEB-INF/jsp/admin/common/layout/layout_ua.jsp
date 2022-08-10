<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 
    JSP Name : layout_ua.jsp.jsp
    Description : 개인정보 수정 Tiles Template
--%>

<!doctype html>
<html lang="ko">
<head>
    <tiles:insertAttribute name="head"/>
</head>
<body>
    <div class="overaybg"></div>
    <div class="admwrap">
        <tiles:insertAttribute name="header"/>
        
        <div id="container">
            <div class="admcont">
                <div class="adm_left"></div>
                <tiles:insertAttribute name="content"/> 
            </div>
        </div>
    </div>

<tiles:insertAttribute name="footer"/>
    
</body>
</html>