<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 
    JSP Name : layout_default.jsp
    Description : 기본 Tiles Template
--%>

<!doctype html>
<html lang="ko">
<head>
    <tiles:insertAttribute name="head"/>
</head>
<body id="wrap">
    <div class="overaybg"></div>
    <div class="modal_backdrop"></div>
    
    <tiles:insertAttribute name="header"/>
    
    <div class="contaioner_wrap clearfix">
        <tiles:insertAttribute name="lnb"/>
		
		<div id="content">
			<div id="container">
				<div class="inner">
       				<tiles:insertAttribute name="content"/>
       			</div>
       		</div>
       	</div> 
    </div>

    <tiles:insertAttribute name="footer"/>
    
</body>
</html>