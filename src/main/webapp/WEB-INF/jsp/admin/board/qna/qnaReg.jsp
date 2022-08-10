<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
    JSP Name : qnaReg.jsp
    Description : QnA 입력폼
    author Jeong.hyoungjea
    since 2017.12.25
    version 1.0
    Modification Information
       since          author              description
    ===========    =============    ===========================
    2017.12.25     Jeong.hyoungjea     최초 생성
--%>
<!-- CONTENT -->
<div class="adm_con" class="notice">
    <%@ include file="/WEB-INF/jsp/admin/common/include/inc_breadcrumb.jsp" %>
	
    <h1>사용자 문의 등록</h1> 
    
    <p class="sti">
        <span class="s"><span>*</span> 표시는 필수 항목입니다.</span>
    </p>
    <form name="mainForm" method="post" enctype="multipart/form-data" onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
        <table class="r_search_table">
            <colgroup>
                <col width="170px" /><col width="*" /><col width="170px" /><col width="*" />
            </colgroup>
            <tr>    
                <th class="necessary">제목</th>
                <td colspan="3">
                    <input type="text" id="blcTitl" name="blcTitl" maxlength="100" style="width:80%;" title="제목" />
                </td> 
            </tr>
            <tr>    
                <th class="necessary">질문 내용</th>
                <td colspan="3">
                    <textarea name="blcSbc1" id="blcSbc1" style="width:95%;height:500px;" title="내용"></textarea>
                </td>   
            </tr>
        </table>
        
        <div class="btn_area mgbtm30">
            <div class="btn_left">
                <a href="${contextPath}/${requestUri}/list.vc" class="btnstyle dark">목록</a>
            </div>
            <div class="btn_right">
                <a href="#" class="btnstyle blue submit">등록</a>
            </div>
        </div>
    </form>

</div>


<script type="text/javascript" src="/resources/admin/editor/js/HuskyEZCreator.js" charset="utf-8"></script>
<script type="text/javascript">

var formSubmitObj = {
    submit : function(form){
        
        //제목
        if(!submitUtil.isEmpty(form.blcTitl)){
            return false;
        }
        
        if(!submitUtil.isEmpty(form.blcSbc1)){
            return false;
        }
        
        ajaxUtil.formSubmit($(form), formSubmitObj.result);
        
        return false;
    },
    result : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        if(json.bindingStatus == undefined && json.result != undefined && json.result.status ){
            location.replace('${contextPath}/${requestUri}/list.vc');
        }else{
            ajaxUtil.error(json);
        }
    }
};
</script>