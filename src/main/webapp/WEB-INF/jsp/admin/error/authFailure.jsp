<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%-- 
    JSP Name : authFailure.jsp
    Description : 로그인 에러
    author Jeong.hyoungjea
    since 2014. 1. 29.
    version 1.0
    Modification Information
       since          author              description
    ===========    =============    ===========================
    2014. 1. 29.   Jeong.hyoungjea     최초 생성
--%>
<script type="text/javascript">
    alert('세션이 종료 되었습니다.\n다시 로그인 하시기 바랍니다.');
    if('${param.isPop}'=='Y'){    
        opener.top.location.replace('/admin/loginMan.vc?returnUrl=' + encodeURIComponent('${param.returnUrl}'));
        self.close();
    }else{
        top.location.replace('/admin/loginMan.vc?returnUrl=' + encodeURIComponent('${param.returnUrl}'));
    }
</script>    
