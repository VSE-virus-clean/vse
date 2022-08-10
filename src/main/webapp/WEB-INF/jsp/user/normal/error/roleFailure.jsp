<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<% response.setStatus(200); %>
<%-- 
    JSP Name : roleFailure.jsp
    Description : 회원전용페이지 접근시 안내페이지
--%>
<script type="text/javascript">
    if('${param.isPop}'=='Y'){    
        opener.top.location.replace('/member/login.vc?returnUrl=' + encodeURIComponent('${param.returnUrl}'));
        self.close();
    }else{
    	location.replace('/member/login.vc?returnUrl=' + encodeURIComponent('${param.returnUrl}'));
    }
</script>
