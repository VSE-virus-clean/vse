<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag"%>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="contextPath" value="" scope="session"/>
<c:set var="cacheParam" value="<%=System.currentTimeMillis() %>" scope="session"/>

<%-- 
    JSP Name : inc_commonHead.jsp    
    Description : 공통 Web Resource를 정의한다.
--%>
<title>바이러스클린랩</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="imagetoolbar" content="no" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta http-equiv="ScreenOrientation" content="autoRotate:disabled"> 
<meta name="viewport" content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
<meta name="description" content="나만의 휴먼 코로나 안전지대 Z-mini(지미니), 휴대용 듀얼 바이러스 살균기 브랜드, 바이러스클린랩입니다." />
<meta name="keywords" content="바이러스클린랩, virus clean lab, 지미니, z-mini, 바이러스 살균기, 공기 살균기, 공기 청정기, UV 살균기, 휴대용 살균기, 휴대용 공기 청정기, 휴대용 공기 살균기, 휴대용 UV 살균기" />
<meta property="og:title" content="바이러스클린랩 | Z-mini(지미니)" />
<meta property="og:description" content="나만의 휴먼 코로나 안전지대 Z-mini(지미니), 휴대용 듀얼 바이러스 살균기 브랜드, 바이러스클린랩입니다." />
<meta property="og:type" content="website" />
<meta property="og:image" content="https://www.viruscleanlab.com/resources/upload/PRODUCT/%EB%B0%94%EC%9D%B4%EB%9F%AC%EC%8A%A4%ED%81%B4%EB%A6%B0%EB%9E%A9%ED%99%88%ED%8E%98%EC%9D%B4%EC%A7%801_1618374934727_0.jpg" />
<meta name="google-signin-client_id" content="<spring:eval expression="@global['sns.google.clientId']" />">
<meta name="google-site-verification" content="<spring:eval expression="@global['sns.google.site.verification']" />">

<link rel="shortcut icon" href="/resources/user/images/favicon_32.ico">
<link rel="icon" sizes="16x16" href="/resources/user/images/favicon_16.ico" />
<link rel="icon" sizes="32x32" href="/resources/user/images/favicon_32.ico" />
<link rel="icon" sizes="64x64" href="/resources/user/images/favicon_64.ico" />

<link type="text/css" rel="stylesheet" href="/resources/user/ui_common/css/reset.css?v=${cacheParam}">
<link type="text/css" rel="stylesheet" href="/resources/user/ui_common/fonts/font.css"> 
<link type="text/css" rel="stylesheet" href="/resources/user/ui_common/css/swiper-bundle.min.css">
<link type="text/css" rel="stylesheet" href="/resources/user/ui_common/css/jquery_fullpage.css"> 
<link type="text/css" rel="stylesheet" href="/resources/user/ui_common/css/ui_common.css?v=${cacheParam}"> 

<c:choose>
	<c:when test="${pageMenuId eq 'KMA' }">
		<link type="text/css" rel="stylesheet" href="/resources/user/css/main.css?v=${cacheParam}">
	</c:when>
	<c:otherwise>
		<link type="text/css" rel="stylesheet" href="/resources/user/ui_common/css/sub.css?v=${cacheParam}">
	</c:otherwise>
</c:choose>

<link type="text/css" rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<style type="text/css">
	.modal_window, .modal_backdrop { display:none; z-index:1010;}
	.nodata { text-align:center !important; }
    .hidden {display:none !important;}
</style>

<script type="text/javascript" src="/resources/js/jquery-1.11.3.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery-ui.js"></script>
<script type="text/javascript" src="/resources/js/jquery.ui.datepicker-ko.js"></script>
<script type="text/javascript" src="/resources/js/jquery-migrate-1.2.1.min.js"></script>
<script type="text/javascript" src="/resources/js/jquery-ajax-options.js"></script>
<script type="text/javascript" src="/resources/js/jquery-submit.js?v=${cacheParam}"></script>
<script type="text/javascript" src="/resources/js/jquery.form.js"></script>
<script type="text/javascript" src="/resources/js/json2.js"></script>
<script type="text/javascript" src="/resources/js/common-user.js?v=${cacheParam}"></script>

<!-- <script type="text/javascript" src="/resources/user/ui_common/js/fullpage.js?v=${cacheParam}"></script>-->
<script type="text/javascript" src="/resources/user/ui_common/js/jquery_fullpage.js"></script>
<script type="text/javascript" src="/resources/user/ui_common/js/swiper-bundle.min.js"></script>
<script type="text/javascript" src="/resources/user/ui_common/js/ui_common.js?v=${cacheParam}"></script>
<script type="text/javascript">
loginObj.contextPath = '';
<c:choose>
<c:when test="${empty sessionScope.sessionVO}">
	loginObj.init('N', 'N', 'N', 'N');
</c:when>
<c:otherwise>
	loginObj.init('Y', '${sessionScope.sessionVO.grade}', 'N', 'N');
</c:otherwise>
</c:choose>

//document.oncontextmenu = new Function('return false');

function fn_moveScrollTop(iOffSet){
	$('html, body').animate({scrollTop:iOffSet}, 300);
}
</script>
<script type="text/javascript" src="/resources/js/common-util.js?v=${cacheParam}"></script>
<script type="text/javascript" src="/resources/js/jquery-submit.js?v=${cacheParam}"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/clipboard.js/1.5.16/clipboard.min.js"></script>
<!-- Google Tag Manager -->
<script>
(function(w,d,s,l,i){w[l]=w[l]||[];w[l].push({'gtm.start':
new Date().getTime(),event:'gtm.js'});var f=d.getElementsByTagName(s)[0],
j=d.createElement(s),dl=l!='dataLayer'?'&l='+l:'';j.async=true;j.src=
' https://www.googletagmanager.com/gtm.js?id='+i+dl;f.parentNode.insertBefore(j,f);
})(window,document,'script','dataLayer','GTM-MVJVT9X');
</script>
