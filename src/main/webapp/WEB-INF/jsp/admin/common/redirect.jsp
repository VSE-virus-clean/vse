<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
    JSP Name : redirect.jsp
    Description : SUBMIT 완료 처리
    author Jeong.hyoungjea
    since 2014. 2. 7.
    version 1.0
    Modification Information
       since          author              description
    ===========    =============    ===========================
    2014. 2. 7.     Jeong.hyoungjea     최초 생성
--%>

<body>
    <script type="text/javascript">
        var json = ${result};
        var errorMsg = "";
    
        //Service에서 넘오는 메세지가 있다면 해당 메세지를 출력하고.
        //없다면 각 상황에 맞는 메세지를 출력한다.
        if(json.message == undefined){
            if(json.bindingStatus == undefined && json.status){
                //작업 성공
            }else{
                //바인딩 에러
                for(var i = json.bindingFields.length -1; i >= 0 ; i--){
                    errorMsg += '\n - ' + stringUtil.replaceNewLine(json.bindingFields[i].defaultMessage);
                }
                alert(errorMsg);
            }    
        }else{
            alert(stringUtil.replaceNewLine(json.message));
        }
        
        location.replace(json.returnUrl == undefined ? '${contextPath}' : '${contextPath}' + json.returnUrl);
    </script>
</body>
