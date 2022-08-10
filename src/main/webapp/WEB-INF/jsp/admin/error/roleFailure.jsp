<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%-- 
    JSP Name : roleFailure.jsp
    Description : 권한 에러
    author Jeong.hyoungjea
    since 2017.12.25
    version 1.0
    Modification Information
       since          author              description
    ===========    =============    ===========================
    2017.12.25     Jeong.hyoungjea     최초 생성
--%>

<!-- Script -->    
<script type="text/javascript">
    alert("Access 권한이 없는 메뉴입니다.\n\n관리자에게 문의 바랍니다.");
    
    if('${param.isPop}'=='Y'){    
        opener.top.location.replace('/admin/mainMan.vc');
        self.close();
    }else{
        top.location.replace('/admin/mainMan.vc');
    }
</script>   
