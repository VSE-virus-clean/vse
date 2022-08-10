<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<c:set var="contextPath" value="/admin" scope="session"/>
<c:set var="siteCd" value="" scope="session"/>
<c:set var="cacheParam" value="<%=System.currentTimeMillis() %>" scope="session"/>

<%-- 
    JSP Name : inc_commonHead.jsp    
    Description : 공통 Web Resource를 정의한다.
--%>
<title>관리자 | VIRUS CLEAN LAB</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="imagetoolbar" content="no" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no" /> 

<link rel="shortcut icon" href="/resources/user/images/favicon_32.ico">
<link rel="icon" sizes="16x16" href="/resources/user/images/favicon_16.ico" />
<link rel="icon" sizes="32x32" href="/resources/user/images/favicon_32.ico" />
<link rel="icon" sizes="64x64" href="/resources/user/images/favicon_64.ico" />

<link type="text/css" rel="stylesheet" href="/resources/admin/ui_common/css/reset.css?v=${cacheParam}">	
<link type="text/css" rel="stylesheet" href="/resources/admin/ui_common/fonts/font.css?v=${cacheParam}">	
<link type="text/css" rel="stylesheet" href="/resources/admin/ui_common/css/ui_common.css?v=${cacheParam}">
<link type="text/css" rel="stylesheet" href="/resources/admin/ui_common/css/paging.css?v=${cacheParam}">
<link type="text/css" rel="stylesheet" href="/resources/admin/css/datepicker.css?v=${cacheParam}"  />

<style type="text/css">

	.modal_window, .modal_backdrop { display:none; }
	.cont_table img { width:100%; vertical-align: middle;}
	td.con img  { max-width:700px; }
	ul.paging { text-align: center; }
	.upload-hidden { display:none; }
	.upload-name { margin-right:5px; width:80%;}
	.nodata { text-align:center !important; }
	a.btn_file_download.down img { width: 9px; margin-left: 3px; }
    .editor p {min-height:18px;}
    .hidden {display:none;}
    .float_l {float:left !important;}
    .float_r {float:right !important;}
    .sortHandle img { cursor:pointer; }
    input.disabled {'background-color' : '#F5F5F5'}
    a.disabled {'background-color' : '#F5F5F5'}
    .filebox img { max-height:400px; }
    #loading-wrap {display:none; z-index:11000;}
    #mask, #mask2 {display:none;overflow:hidden;position:fixed;top:0;left:0;width:100%;height:100%;min-height:100%;font-size:0;line-height:0;background-color:rgba( 0, 0, 0, 0.7 );z-index:990;}
    #ifr, #ifr2, #ifr3 {position:absolute; top:0; left:0; z-index:9900; border:0px; display:none;}
    ul.warn { margin-top: 10px; }
    table td { vertical-align: middle; }
    table td.left { text-align:left !important; }
</style>

<script type="text/javascript" src="/resources/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery-ui.js"></script>
<script type="text/javascript" src="/resources/js/jquery.ui.datepicker-ko.js?v=${cacheParam}"></script>
<script type="text/javascript" src="/resources/js/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery-ajax-options.js?v=${cacheParam}"></script>
<script type="text/javascript" src="/resources/js/jquery.form.js"></script>
<script type="text/javascript" src="/resources/js/json2.js"></script>
<script type="text/javascript" src="/resources/js/common-user.js?v=${cacheParam}"></script>
<script type="text/javascript">
loginObj.contextPath = '${contextPath}';
</script>
<script type="text/javascript" src="/resources/js/common-util.js?v=${cacheParam}"></script>
<script type="text/javascript" src="/resources/js/jquery-submit.js?v=${cacheParam}"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/clipboard.js/1.5.16/clipboard.min.js"></script>
