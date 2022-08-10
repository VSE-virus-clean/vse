<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<% response.setStatus(200); %>
<%-- 
    JSP Name : errorMan.jsp
    Description : 에러 발생시 사용자에게 적정한 메세지를 보여준다.
--%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>VIRUS CLEAN LAB</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="imagetoolbar" content="no" />
    <meta http-equiv="cache-control" content="no-cache" />
    <meta http-equiv="expires" content="0" />
    <meta http-equiv="pragma" content="no-cache" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no"> 
    <link rel="stylesheet" href="/resources/admin/ui_common/css/reset.css?v=${cacheParam}">	
	<link rel="stylesheet" href="/resources/admin/ui_common/css/fonts/fonts.css?v=${cacheParam}">	
	<link rel="stylesheet" href="/resources/admin/ui_common/css/ui_common.css?v=${cacheParam}">
	<link rel="stylesheet" href="/resources/admin/ui_common/css/firstpage.css?v=${cacheParam}">
    <script type="text/javascript" src="/resources/js/jquery-1.11.3.min.js"></script>
</head>
<body>
	<div class="top_bar"></div>	
	<div class="inner">
		<a href="#" class="ad_logo" style="display:block; text-align:center;"><img src="/resources/admin/imagescommon/ad_logo.png" alt=""></a>
		<div class="page_box">
			<img src="/resources/admin/images/common/error.png" alt="">
			<h3>요청하신 페이지를 찾을 수 없습니다.</h3>
			<p>서비스 이용에 불편을 드려 죄송합니다.</p>
		</div>
	</div>
</body>
</html>