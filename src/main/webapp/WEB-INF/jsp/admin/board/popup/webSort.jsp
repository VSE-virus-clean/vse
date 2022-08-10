<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
    JSP Name : noticeList.jsp
    Description : 게시판관리 > INFORMATION > NOTICE 목록
--%>
<div class="sec_top">
	<h3 class="sec_tit">팝업 목록</h3>
	<ul class="top_tab">
		<li><a href="#">배너&amp;팝업관리</a></li>
		<li><a href="#">WEB 팝업관리</a></li>
	</ul>
</div>
<div class="sec_cont">
	<h4 class="cont_tit">노출순서변경</h4>
	
	<div class="content-body">
		<div class="table_tit">
			<div class="table_left_title">
				<span>총<strong class="totCnt" style="font-weight: 700;"> <fmt:formatNumber value="${result.searchInfo.totalRow}" type="number"/></strong>건 조회</span>
			</div>
		</div>
  
 		<form name="subForm" method="post" onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
   			<table class="cont_table" style="table-layout:fixed">
		        <colgroup>
					<col style="width:150px">
					<col style="width:250px">
					<col style="">
					<col style="width:80px">
					<col style="width:80px">
					<col style="width:150px">
					<col style="width:180px">
				</colgroup>
		        <thead>
		            <tr>
		                <th>정렬순서</th>
		                <th>팝업이미지</th>
		                <th>제목</th>
		                <th>공개여부</th>
		                <th>조회수</th>
		                <th>노출일</th>
		                <th>관리</th>
		            </tr>
		        </thead>
		        <tbody>
		            <c:choose>
		                <c:when test="${empty result.list}">
		                    <tr>
		                        <td colspan="7" class="nodata">공개중인 팝업만 노출순서 변경이 가능합니다.</td>
		                    </tr>
		                </c:when>
		                <c:otherwise>
		                    <c:forEach items="${result.list}" var="data" varStatus="i">
		                        <tr>
		                            <td>
		                            	<input type="hidden" name="delSeq" value="${data.blcSn}"> ${i.count}
		                            </td>
		                            <td class="con">
		                            	<c:if test="${data.fileSn ne 0 }">
		                            		${function:printImageFileByList(data.fileSn, data.lgrpCd, data.filNm, data.filNm)}
		                            	</c:if>
		                           	</td>
		                            <td class="left"><tag:html value="${data.blcTitl}" attr="NQ" /></td>
		                            <td><span class="${data.useYn eq 'Y' ? 'rtxt' : ''}">${data.useYn}</span></td>
		                            <td>${data.blcRct}</td>
		                            <td>${data.expsRgstDtm}<br/> ~ ${data.expsFnhDtm}</td>
		                            <td class="table_btn">
		                            	<a class="btn btn_red levelonehandle" href="javascript:;">Drag</a>
									</td>
		                        </tr>
		                    </c:forEach>
		                </c:otherwise>
		            </c:choose>
		        </tbody>
		    </table>

			<div class="btn_center_gorup clearfix">
				<div class="left">
					<button type="button" class="btn btn_gray" onclick="location.href='${contextPath}/${requestUri}/list.vc'; ">목록</button>
				</div>
				<div class="right">
					<button type="submit" class="btn btn_red">적용</button>
				</div>
			</div>
	    </form>
		    
	    <div class="paging_wrap">
	    	<tag:paging url="${contextPath}/${requestUri}/list.vc?${function:searchQueryPaging(result.searchInfo)}" totalRow="${result.searchInfo.totalRow}" page="${result.searchInfo.page}" rowLimit="${result.searchInfo.rowLimit}" />
	    </div>
    </div>
</div>

<script type="text/javascript">
$(function() {
	$("table.cont_table tbody").sortable({
	   handle: ".levelonehandle",
	   placeholder: "ui-state-highlight",
	   helper: function( event, tr ){
			var $originals = tr.children();
			var $helper = tr.clone();
			$helper.children().each(function(index)
			{
			  // Set helper cell sizes to match the original sizes
			 // alert($originals.eq(index).html());
			 $(this).width($originals.eq(index).width());
			});
			return $helper;
	   },
	   update: function( event, ui ) {
// 		   alert($(this).sortable('toArray'));
	   }
	});
	$("table.cont_table tbody").disableSelection();
});
var formSubmitObj = {
    submit : function(form){
    	
    	if(confirm('정렬순서를 변경하시겠습니까?')){
    		ajaxUtil.postDisableAsync(form.action, $(form).serialize(), formSubmitObj.result);    
    	}
        
        return false;
    },
    result : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        if(json.result != undefined && json.result.status){
        	alert('정렬순서를 변경 되었습니다.');
            location.reload(true);
        }else{
            ajaxUtil.error(json);
        }
    }
};
</script>