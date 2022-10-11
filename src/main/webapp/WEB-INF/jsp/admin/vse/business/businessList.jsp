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
	<h3 class="sec_tit">Counseling List</h3>
	<!-- 
	<ul class="top_tab">
		<li><a href="#">창업상담 신청관리</a></li>
	</ul>
	-->
</div>
<div class="sec_cont">
<!--<h4 class="cont_tit">조회조건</h4>-->
	<div class="r_search_box">
		<form name="searchForm" method="get" onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
			<input type="hidden" name="rowLimit" value="10" />
			<table class="r_search_table">
				<%-- 
				<colgroup>
					<col style="width:150px">
					<col style="">
					<col style="width:150px">
					<col style="">
				</colgroup>
				--%>
				<tbody>
					<tr>
					   <th>Registration date</th>
					   <td colspan="3">										
							<input type="text" id="searchStartDate" name="searchStartDate" class="date_input is-datepick datepicker" readonly="readonly" autocomplete="off"  title="기간" value="${result.searchInfo.searchStartDate}"> <em> ~ </em>
							<input type="text" id="searchEndDate" name="searchEndDate" class="date_input is-datepick datepicker" readonly="readonly" autocomplete="off" title="기간"  value="${result.searchInfo.searchEndDate}">
						</td>
					</tr>
					<tr>
						<th>Search</th>
						<td colspan="3">
					   		<select id="searchType" name="searchType">
		                        <option value="0">All</option>
		                        <option value="1">Title</option>
		                        <option value="2">Contents</option>
		                    </select>
							<input id="searchKey" name="searchKey" type="text" title="the search input" style="width: calc(100% - 120px);" maxlength="100" minlength="2" value="${result.searchInfo.searchKey}"/>
						</td>
					</tr>
				</tbody>
			</table>
			<div class="btn_right_gorup">
				<button type="submit" class="btn btn_red">search</button>
				<button type="reset" class="btn btn_gray">reset</button>
			</div>
		</form>
		<form name="downForm" method="get">
			<input type="hidden" name="searchType" value="${result.searchInfo.searchType}" />
			<input type="hidden" name="searchKey" value="${result.searchInfo.searchKey}" />
			<input type="hidden" name="searchStartDate" value="${result.searchInfo.searchStartDate}" />
			<input type="hidden" name="searchEndDate" value="${result.searchInfo.searchEndDate}" />
		</form>
	</div>
	
	<div class="content-body">
		<div class="table_tit left_right">
			<div class="table_left_title">
				<span>total<strong class="totCnt" style="font-weight: 700;"> <fmt:formatNumber value="${result.searchInfo.totalRow}" type="number"/></strong></span>
			</div>
    		<div class="right">
    			<a href="${contextPath}/${requestUri}/allList.vc" class="btnstyle blue excelDown"><span>Excel Download</span></a> 
    			<select name="rowLimit" id="rowLimit">
					<option value="10">View 10 each</option>
                    <option value="20">View 20 each</option>
                    <option value="50">View 50 each</option>
                    <option value="100">View 100 each</option>
                    <option value="1000">Everything</option>
				</select>
    		</div>
		</div>
    
    	<form name="subForm" method="post" action="${contextPath}/${requestUri}/delete.vc" onsubmit="var rtn = formSubmitObj.delSubmit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
		    <table class="cont_table" style="table-layout:fixed">
		    	<colgroup>
					<col style="width:3%">
					<col style="width:40%">
					<col style="width:17%">
					<col style="width:20%">
					<col style="width:20%">
				</colgroup>
		        <thead>
		            <tr>
		                <th><input type="checkbox" class="checkbox-selectAll" id="checkbox-selectAll">No</th>
		                <th>Name</th>
		                <th>Phone</th>
		                <th>Registration date</th>
		                <th>Management</th>
		            </tr>
		        </thead>
		        <tbody>
		            <c:choose>
		                <c:when test="${empty result.list}">
		                    <tr>
		                        <td colspan="5" class="nodata">There are no registered posts.</td>
		                    </tr>
		                </c:when>
		                <c:otherwise>
		                    <c:forEach items="${result.list}" var="data" varStatus="i">
		                        <tr>
		                            <td>
		                            	<input type="checkbox" id="sn_${data.blcSn}" name="delSeq" class="check2 checkbox-select" value="${data.blcSn}" title="선택"> ${function:rowNumber(result.searchInfo, i.count)}
		                            </td>
		                            <td>${data.rgstName}</td>
		                            <td>${data.rgstHp}</td>
		                            <td>${data.rgstDtm}</td>
		                            <td class="table_btn">
		                            	<a class="btn btn_red" href="${contextPath}/${requestUri}/view.vc?blcSn=${data.blcSn}${function:searchQuery(result.searchInfo)}">view</a>
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
	 	$('select[name=rowLimit] option[value="${result.searchInfo.rowLimit}"]').prop('selected', true);
    });
    
    $('.excelDown').on('click', function(event){
		event.preventDefault();  
		formSubmitObj.excelSubmit();
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
    },
	excelSubmit : function(){
		location.href = '${contextPath}/${requestUri}/allList.vc?' + $('form[name=downForm]').serialize();
	    return false;
	}
};
</script>