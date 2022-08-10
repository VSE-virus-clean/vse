<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
    JSP Name : qnaMod.jsp
    Description : QnA 답변폼
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
    
    <h1>사용자 문의 답변</h1> 
	
    <p class="sti">
        <span class="s"><span>*</span> 표시는 필수 항목입니다.</span>
    </p>
    <form name="mainForm" method="post" enctype="multipart/form-data" onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
    	<input name="blcSn" type="hidden" value="${result.info.blcSn}"/>
        <table class="r_search_table">
            <colgroup>
                 <col width="170px" /><col width="*" /><col width="170px" /><col width="*" />
            </colgroup>
            <tr>    
                <th>제목</th>
                <td colspan="3"><tag:html value="${result.info.blcTitl}" attr="NQ" /></td>    
            </tr>
            <tr>
                <th>작성자</th>
                <td>
                	<c:choose>
	                	<c:when test="${result.info.rgstSn ne 0}">
	                		${result.userInfo.mbrNm} (FC No. ${result.userInfo.mbrNo})
	                	</c:when>
	                	<c:otherwise>${result.info.mbrNick}</c:otherwise>
                	</c:choose>
                	<c:if test="${result.info.rgstSn ne 0}">
                		<a href="/admin/member/info/view.vc?mbrSn=${result.userInfo.mbrSn}" target="_blank" class="btnstyle blue">회원정보</a>
                	</c:if>
               	</td>
                <th>등록시간</th>
                <td>${result.info.rgstDtm}</td>
            </tr>
            <tr> 
            	<th>E-MAIL</th>
                <td colspan="3">${result.info.rgstEml}</td>    
            </tr>
            <tr>    
                <th>질문 내용</th>
                <td colspan="3" class="con">
                    <tag:html value="${result.info.blcSbc1}" attr="BR" />
                </td>   
            </tr>
            <tr>    
                <th>답변 내용</th>
                <td colspan="3" class="con">
                    <textarea name="blcSbc2" id="blcSbc2" style="width:95%;height:500px;" title="답변">${result.info.blcSbc2}</textarea>
                </td>   
            </tr>
        </table>
        <div class="btn_area mgbtm30">
            <div class="btn_left">
                <a href="${contextPath}/${requestUri}/list.vc" class="btnstyle dark">목록</a>
            </div>
            <div class="btn_right">
                <a href="#" class="btnstyle blue submit">답변</a>
            </div>
        </div>
    </form>

</div>

<!-- <script type="text/javascript" src="/resources/admin/editor/js/HuskyEZCreator.js" charset="utf-8"></script> -->
<script type="text/javascript">
// smarteditor.create([{'id':'blcSbc2', 'htmlMode':true}]);

var formSubmitObj = {
    submit : function(form){
        
        //에디터 내용 => 이미지만 삽입하는 경우도 있음.
//         blcSbc2Editors.getById["blcSbc2"].exec("UPDATE_CONTENTS_FIELD", []);  

        if(!submitUtil.isEmpty(form.blcSbc2)){
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
           location.reload(true);
           location.replace('${contextPath}/${requestUri}/view.vc?blcSn=${result.info.blcSn}${function:searchQuery(result.searchInfo)}');
        }else{
           ajaxUtil.error(json);
        }
    }
};
</script>