<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%-- 
    JSP Name : authFailure.jsp
    Description : 로그인 에러
--%>
<script type="text/javascript">
    if('${param.isPop}'=='Y'){    
        opener.top.location.replace('/member/login.vc?returnUrl=' + encodeURIComponent('${param.returnUrl}'));
        self.close();
    }else{
        top.location.replace('/member/login.vc?returnUrl=' + encodeURIComponent('${param.returnUrl}'));
    }
</script>    
