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
	<h3 class="sec_tit">쿠폰관리</h3>
	<ul class="top_tab">
		<li><a href="#">쿠폰관리</a></li>
		<li><a href="#">쿠폰목록</a></li>
		<li><a href="#">쿠폰상세</a></li>
	</ul>
</div>
<div class="sec_cont">
	<h4 class="cont_tit">쿠폰 기본정보</h4>
	<div class="r_search_box">
		<table class="r_search_table">
			<colgroup>
                 <col width="170px" /><col width="*" /><col width="170px" /><col width="*" />
            </colgroup>
			<tbody>
				<tr>
					<th class="necessary">쿠폰명</th>
					<td colspan="3">${result.info.cupTitl}</td>
				</tr>
				<tr>
					<th class="necessary">쿠폰설명</th>
					<td colspan="3">${result.info.cupSbc}</td>
				</tr>
				<tr>
					<th class="necessary">발급타입</th>
					<td colspan="3">${result.info.lgrpCd}</td>
				</tr>
				<tr>
					<th class="necessary">
						<c:choose>
							<c:when test="${result.info.lgrpCd eq '지급쿠폰'}">지급수</c:when>
							<c:when test="${result.info.lgrpCd eq '다운쿠폰'}">다운수</c:when>
							<c:when test="${result.info.lgrpCd eq '이벤트쿠폰'}">발급수</c:when>
						</c:choose>
					</th>
					<td colspan="3">${result.info.usable}</td>
				</tr>
				<tr>
					<th class="necessary">사용수</th>
					<td colspan="3">${result.info.unusable}</td>
				</tr>
			</tbody>
		</table>
		<div class="btn_center_gorup clearfix">
			<div class="right">
				<button type="button" class="btn btn_gray" onclick="location.href='${contextPath}/shop/coupon/info/list.vc'; ">쿠폰관리목록</button>
			</div>
		</div>
	</div>
	
	<h4 class="cont_tit content-body">조회조건</h4>
	<div class="r_search_box">
		<form name="searchForm" method="get" onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
			<input type="hidden" name="cupMetaSn" value="${result.info.cupMetaSn}" />
			<table class="r_search_table">
				<colgroup>
					<col style="width:150px">
					<col style="">
					<col style="width:150px">
					<col style="">
				</colgroup>
				<tbody>
					<tr>
					   <th>유효기간</th>
					   <td colspan="3">										
							<input type="text" id="searchStartDate" name="searchStartDate" class="date_input is-datepick datepicker" readonly="readonly" autocomplete="off"  value=""> <em> ~ </em>
							<input type="text" id="searchEndDate" name="searchEndDate" class="date_input is-datepick datepicker" readonly="readonly" autocomplete="off" value="">
						</td>
					</tr>
					<tr>
					   <th>상태</th>
					   <td colspan="3">
					   		<select id="searchType" name="searchType">
		                        <option value="">전체</option>
		                        <option value="N">사용</option>
		                        <option value="Y">미사용</option>
		                    </select>										
						</td>
					</tr>
					
					<tr>
						<th>검색어</th>
						<td colspan="3">
							<input type="text" id="searchKey" name="searchKey" title="상세검색입력" style="width:450px;" maxlength="100" minlength="2"/>
						</td>
					</tr>
				</tbody>
			</table>
			<div class="btn_right_gorup">
				<button type="submit" class="btn btn_red">조회</button>
				<button type="reset" class="btn btn_gray">초기화</button>
			</div>
		</form>
	</div>
	
	<div class="content-body">
		<div class="table_tit">
			<div class="table_left_title">
				<span>총<strong class="totCnt" style="font-weight: 700;"> <fmt:formatNumber value="${result2.searchInfo.totalRow}" type="number"/></strong>건 조회</span>
			</div>
		</div>
    
	    <table class="cont_table" style="table-layout:fixed">
	    	<colgroup>
				<col style="width:100px">
				<col style="width:200px">
				<col style="">
				<col style="width:220px">
				<col style="width:100px">
			</colgroup>
	        <thead>
	            <tr>
	                <th>번호</th>
	                <th>쿠폰번호</th>
					<th>회원아이디</th>
					<th>쿠폰유효기간</th>
					<th>사용상태</th>                           
	            </tr>
	        </thead>
	        <tbody>
	            <c:choose>
	                <c:when test="${empty result2.list}">
	                    <tr>
	                        <td colspan="5" class="nodata">등록된 정보가 없습니다.</td>
	                    </tr>
	                </c:when>
	                <c:otherwise>
	                    <c:forEach items="${result2.list}" var="data" varStatus="i">
	                        <tr>
	                            <td>${i.count}</td>
	                            <td>${data.cupNo}</td>
	                            <td>${data.mbrId}</td>
								<td>${data.expsRgstDtm} ~ ${data.expsFnhDtm}</td>
								<td>${data.useYn eq 'Y' ? '미사용' : '사용완료'}</td>
	                        </tr>
	                    </c:forEach>
	                </c:otherwise>
	            </c:choose>
	        </tbody>
	    </table>
		    
	    <div class="paging_wrap">
	    	<tag:paging url="${contextPath}/${requestUri}/list.vc?${function:searchQueryPaging(result.searchInfo)}" totalRow="${result.searchInfo.totalRow}" page="${result.searchInfo.page}" rowLimit="${result.searchInfo.rowLimit}" />
	    </div>
    </div>
</div>

<script type="text/javascript">
$(function () {
	$('form[name=searchForm]').ready(function(){
		$('select[name=searchType] option[value="${result2.searchInfo.searchType}"]').prop('selected', true);
    	$('input[name=searchStartDate]').val('${result2.searchInfo.searchStartDate}');
    	$('input[name=searchEndDate]').val('${result2.searchInfo.searchEndDate}');
        $('input[name=searchKey]').val('${result2.searchInfo.searchKey}');  
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
    }
};
</script>