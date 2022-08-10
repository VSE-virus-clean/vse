<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<link rel="stylesheet" href="/resources/admin/ui_common/css/manage.css">

<div class="sec_top">
	<h3 class="sec_tit">통계관리</h3>
	<ul class="top_tab">
		<li><a href="#">상품관리 통계</a></li>
	</ul>
</div>

<div class="sec_cont">	
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
					   <th>날짜</th>
					   <td colspan="3">										
							<input type="text" id="searchStartDate" name="searchStartDate" class="date_input is-datepick datepicker" readonly="readonly" autocomplete="off"  title="기간"> <em> ~ </em>
							<input type="text" id="searchEndDate" name="searchEndDate" class="date_input is-datepick datepicker" readonly="readonly" autocomplete="off" title="기간">
						</td>
					</tr>
					<tr>
						<th>상품명</th>
						<td colspan="3">
							<input id="searchKey" name="searchKey" type="text" title="상품명" style="width:450px;" maxlength="100" minlength="2"/>
						</td>
					</tr>
				</tbody>
			</table>
			<div class="btn_right_gorup">
				<button type="submit" class="btn btn_red">조회</button>
			</div>
		</form>
		<form name="downForm" method="get">
			<input type="hidden" name="searchStartDate" value="${result.searchInfo.searchStartDate}" />
			<input type="hidden" name="searchEndDate" value="${result.searchInfo.searchEndDate}" />
			<input type="hidden" name="searchKey" value="${result.searchInfo.searchKey}" />
		</form>
	</div>
	
	
	<div class="content-body">                   
		<table class="cont_table" style="TABLE-layout:fixed">
			<colgroup>
				<col width="">
				<col width="">
				<col width="">
			</colgroup>
			<thead>
				<tr style="height:58px;">
					<th>
						<div class="stat_icon_wrap">
							<p>총 상품금액</p>
						</div>
					</th>
					<th>
						<div class="stat_icon_wrap">
							<p>총 결제 확정 상품수</p>
						</div>
					</th>
					<th>
						<div class="stat_icon_wrap">
							<p>총 결제 상품수</p>
						</div>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr style="height:58px;">
					<td>
						<p><span><fmt:formatNumber value="${result.summary.sale01}" type="number"/></span> 원</p>
					</td>
					<td>
						<p><span><fmt:formatNumber value="${result.summary.sale02}" type="number"/></span> 건</p>
					</td>
					<td>
						<p><span><fmt:formatNumber value="${result.summary.sale03}" type="number"/></span> 건</p>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<div class="content-body">
		<div class="table_tit left_right">
			<div class="table_left_title"></div>
    		<div class="right">
    			<a href="${contextPath}/${requestUri}/excel.vc" class="btnstyle blue excelDown">
    				<span>엑셀 다운로드</span>
    			</a>
    		</div>
		</div>
  
		<table class="cont_table" style="table-layout:fixed">
	    	<colgroup>
				<col style="width:80px">
				<col style="width:150px">
				<col style="">
				<col style="width:200px">
				<col style="width:150px">
				<col style="width:150px">
				<col style="width:150px">
				<col style="width:180px">
			</colgroup>
	        <thead>
	            <tr>
	                <th>순위</th>
					<th>상품이미지</th>
					<th>상품명</th>
					<th>판매가격</th>
					<th>결제수</th>
	                <th>취소/환불수</th>
	                <th>결제 확정수</th>
	                <th>합계(금액)</th>
	            </tr>
	        </thead>
	        <tbody>
	            <c:choose>
	                <c:when test="${empty result.list}">
	                    <tr>
	                        <td colspan="8" class="nodata">정보가 없습니다.</td>
	                    </tr>
	                </c:when>
	                <c:otherwise>
	                    <c:forEach items="${result.list}" var="data" varStatus="i">
	                        <tr>
	                            <td><fmt:formatNumber value="${data.num}" type="number"/></td>
								<td class="con">
	                            	<c:if test="${data.fileSn ne 0 }">
	                            		${function:printImageFileByList(data.fileSn, "PRODUCT", data.filNm, data.filNm)}
	                            	</c:if>
	                           	</td>
								<td class="left"><tag:html value="${data.prdTitl}" attr="NQ" /></td>
								<td><fmt:formatNumber value="${data.salePrice}" type="number"/> 원</td>
								<td><fmt:formatNumber value="${data.cnt1}" type="number"/> 건</td>
								<td><fmt:formatNumber value="${data.cnt2}" type="number"/> 건</td>
								<td><fmt:formatNumber value="${data.cnt3}" type="number"/> 개</td>
								<td><fmt:formatNumber value="${data.sum3}" type="number"/> 원</td>
	                        </tr>
	                    </c:forEach>
	                </c:otherwise>
	            </c:choose>
	        </tbody>
	    </table>
		    
    </div>
</div>

<script type="text/javascript">
$(function () {
	$('form[name=searchForm]').ready(function(){
		$('input[name=searchStartDate]').val('${result.searchInfo.searchStartDate}');  
    	$('input[name=searchEndDate]').val('${result.searchInfo.searchEndDate}');
    	$('input[name=searchKey]').val('${result.searchInfo.searchKey}');  
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
        
        if(!submitUtil.isNull(form.searchStartDate) && !submitUtil.isNull(form.searchEndDate)){
    		if(!submitUtil.isDateCompare(form.searchStartDate, form.searchEndDate)){
            	return false;
    		}
        }
        
        return true;
    },
	excelSubmit : function(){
		location.href = '${contextPath}/${requestUri}/excel.vc?' + $('form[name=downForm]').serialize(); 
	    return false;
	}
};

</script>