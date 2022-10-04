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
    Description : NEWS 목록
--%>
<div class="sec_top">
	<h3 class="sec_tit">NEWS</h3>
	<!-- 
	<ul class="top_tab">
		<li><a href="#">NEWS</a></li>
	</ul>
	 -->
</div>
<div class="sec_cont">
<!-- 	<h4 class="cont_tit">Search</h4> -->
	<div class="r_search_box">
		<form name="searchForm" method="get" onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
			<table class="r_search_table">
				<colgroup>
					<col style="width:150px">
					<col style="">
					<col style="width:150px">
					<col style="">
				</colgroup>
				<tbody>
					<tr>
						<td colspan="3">
					   		<select id="searchType" name="searchType">
		                        <option value="0">All</option>
		                        <option value="1">Title</option>
		                        <option value="2">Contents</option>
		                    </select>
							<input id="searchKey" name="searchKey" type="text" title="the search input" style="width: calc(100% - 110px);" maxlength="100" minlength="2"/>
						</td>
					</tr>
				</tbody>
			</table>
			<div class="btn_right_gorup">
				<button type="submit" class="btn btn_red">search</button>
<!-- 			<button type="reset" class="btn btn_gray">reset</button> -->
			</div>
		</form>
	</div>
	
	<div class="content-body">
		<div class="table_tit">
			<div class="table_left_title">
				<span>total<strong class="totCnt" style="font-weight: 700;"> <fmt:formatNumber value="${result.searchInfo.totalRow}" type="number"/></strong></span>
			</div>
		</div>
    
    
    	<form name="subForm" method="post" action="${contextPath}/${requestUri}/delete.vc" onsubmit="var rtn = formSubmitObj.delSubmit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
		    <table class="cont_table" style="table-layout:fixed">
		    	<colgroup>
					<col style="width:7%">
					<col style="width:40%">
					<col style="width:7%">
					<col style="width:5%">
					<col style="width:13%">
					<col style="width:16%">
					<col style="width:12%">
				</colgroup>
		        <thead>
		            <tr>
		                <th><input type="checkbox" class="checkbox-selectAll" id="checkbox-selectAll">No.</th>
				   <!-- <th>Image</th> -->
		                <th>Title</th>
		                <th>Disclosure Y/N</th>
		                <th>Hits</th>
		                <th>Writer</th>
		                <th>Exposure Day</th>
		                <th>Management</th>
		            </tr>
		        </thead>
		        <tbody>
		            <c:choose>
		                <c:when test="${empty result.list}">
		                    <tr>
		                        <td colspan="7" class="nodata">There are no registered posts.</td>
		                    </tr>
		                </c:when>
		                <c:otherwise>
		                    <c:forEach items="${result.list}" var="data" varStatus="i">
		                        <tr>
		                            <td>
		                            	<input type="checkbox" id="sn_${data.blcSn}" name="delSeq" class="check2 checkbox-select" value="${data.blcSn}" title="선택"> ${function:rowNumber(result.searchInfo, i.count)}
		                            </td>
		                            <!--  
		                            <td class="con">
		                            	<c:if test="${data.fileSn ne 0 }">
		                            		${function:printImageFileByList(data.fileSn, data.lgrpCd, data.filNm, data.filNm)}
		                            	</c:if>
		                           	</td>
		                            -->
		                            <td class="left"><tag:html value="${data.blcTitl}" attr="NQ" /></td>
		                            <td><span class="${data.useYn eq 'Y' ? 'rtxt' : ''}">${data.useYn}</span></td>
		                            <td>${data.blcRct}</td>
		                            <td>${data.rgstId}</td>
		                            <td>${data.expsRgstDtm}<br/> ~ ${data.expsFnhDtm}</td>
		                            <td class="table_btn">
		                            	<a class="btn btn_red" href="${contextPath}/${requestUri}/modify.vc?blcSn=${data.blcSn}${function:searchQuery(result.searchInfo)}">mod</a>
										<a href="${contextPath}/${requestUri}/delete.vc" class="btn btn_gray btnRowDelete"  data-sn="${data.blcSn}">del</a>
									</td>
		                        </tr>
		                    </c:forEach>
		                </c:otherwise>
		            </c:choose>
		        </tbody>
		    </table>
    
		    <div class="btn_center_gorup clearfix">
				<div class="left">
					<button type="button" class="btn btn_gray btnInfoCancle" onclick="">Uncheck</button>
					<button type="submit" class="btn btn_gray" onclick="">Delete Selection</button>
				</div>
				<div class="right">
					<button type="button" onclick="location.href='${contextPath}/${requestUri}/register.vc';" class="btn btn_bk">Registration</button>
				</div>
			</div>
	    </form>
		    
	    <div class="paging_wrap">
	    	<tag:paging url="${contextPath}/${requestUri}/list.vc?${function:searchQueryPaging(result.searchInfo)}" totalRow="${result.searchInfo.totalRow}" page="${result.searchInfo.page}" rowLimit="${result.searchInfo.rowLimit}" />
	    </div>
    </div>
</div>

<script type="text/javascript">
$(function () {
    $('form[name=searchForm]').ready(function(){
    	$('select[name=searchType] option[value="${result.searchInfo.searchType}"]').prop('selected', true);
        $('input[name=searchKey]').val('${result.searchInfo.searchKey}');  
    });
});

var formSubmitObj = {
    submit : function(form){
        if(!submitUtil.isNull(form.searchKey)){
            if(!submitUtil.isMinLength(form.searchKey)){
                return false;    
            }        
        }  
        
        return true;
    },
    submitDel : function(object){
		var msg = '게시물을 삭제 하시겠습니까?'
	    if(confirm(msg)){
	        var data = { blcSn : $(object).data('sn') };
	        ajaxUtil.postDisableAsync($(object).attr('href'), data, formSubmitObj.delResult);    
	    }
	},
	delSubmit : function(form){
        if($('input.checkbox-select:checked').length > 0){
        	if (confirm('선택한 게시물을 삭제 하시겠습니까?')) {
            	ajaxUtil.postDisableAsync(form.action, $(form).serialize(), formSubmitObj.delResult);
        	}
        }else{
            alert('1개이상 선택해 주세요.');
        }
        
        return false;
    },
    delResult : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        if(json.result != undefined && json.result.status){
            location.reload(true);
        }else{
            ajaxUtil.error(json);
        }
    }
};
</script>