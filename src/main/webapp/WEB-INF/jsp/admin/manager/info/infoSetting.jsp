<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
    JSP Name : infoSetting.jsp
    Description : 화면 세팅 목록
--%>
<div class="sec_top" style="display:flex; justify-content: space-between;">
	<h3 class="sec_tit">View Setting</h3>
</div>
<div class="sec_cont">

	<div class="content-body">
		<form name="subForm" method="post" action="${contextPath}/${requestUri}/delete.vc" onsubmit="var rtn = formSubmitObj.delSubmit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
   			<table class="cont_table" style="table-layout:fixed">
   				<%-- 
		        <colgroup>
		            <col width="3%" />
		            <col width="15%" />
		            <col width="33%" />
		            <col width="15%" />
		            <col width="10%" />
		            <col width="10%" />
		            <col width="7%" />
		            <col width="7%" />
		        </colgroup>
		         --%>
		        <thead>
		            <tr>
		                <th>No</th>
		                <th>Name</th>
		                <th>Usage status</th>
		                <th>Modify ID</th>
		                <th>Last Modify date</th>
		            </tr>
		        </thead>
		        <tbody>
		            <c:choose>
		                <c:when test="${empty result.list}">
		                    <tr>
		                        <td colspan="5">There are no registered posts.</td>
		                    </tr>
		                </c:when>
		                <c:otherwise>
		                    <c:forEach items="${result.list}" var="data" varStatus="i">
		                         <tr>
		                            <td>${data.blcSn}</td>
		                            <td>${data.lgrpCd}</td>
		                            <td>
		                                <a class="btn btn_gray useYnMod" data="${data.blcSn}|${data.useYn  eq 'Y' ? 'N' : 'Y'}" href="#" >${data.useYn eq 'Y' ? 'Y' : 'N'}</a>
		                            </td>
		                            <td>${data.mdfyId}</td>
		                            <td>${data.mdfyDtm}</td>
		                        </tr>
		                    </c:forEach>
		                </c:otherwise>
		            </c:choose>
		        </tbody>
		    </table>
    
	        <div class="r_search_box">
		        <div class="btn_center_gorup clearfix">
					<div class="left">
						<button type="button" class="btn btn_gray" onclick="location.href='${contextPath}/${requestUri}/list.vc'; ">List</button>
					</div>
				</div>
			</div>
	    </form>

    </div>
</div>

<script type="text/javascript">
$(function () {

    /**
     * 화면 노출 상태 변경
     */
    $('a.useYnMod').on({
        'click' : function(event){
            event.preventDefault();  
            formSubmitObj.submitUseYnMod($(this));
        }
    });
});

var formSubmitObj = {

    /**
     * 화면 노출 변경
     * - 사용 -> 사용중지
     * - 사용중지 -> 사용
     */
     submitUseYnMod : function(object){
        if(confirm('Do you want to change the usage status?')){
            var data = { blcSn : $(object).attr('data').split('|')[0], useYn : $(object).attr('data').split('|')[1]};
            ajaxUtil.postDisableAsync('${contextPath}/${requestUri}/useYnMod.vc', data, formSubmitObj.resultUseYnMod);
        }
    },
    resultUseYnMod : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        if(json.bindingStatus == undefined && json.result != undefined && json.result.status){
            location.reload(true);
        }else{
            ajaxUtil.error(json);     
        }
    }
};
</script>