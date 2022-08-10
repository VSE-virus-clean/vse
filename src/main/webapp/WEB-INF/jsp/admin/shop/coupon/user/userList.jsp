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
	<h3 class="sec_tit">쿠폰내역</h3>
	<ul class="top_tab">
		<li><a href="#">쿠폰관리</a></li>
		<li><a href="#">쿠폰목록</a></li>
	</ul>
</div>
<div class="sec_cont">
	<h4 class="cont_tit">조회조건</h4>
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
					   <th>등록일</th>
					   <td colspan="3">										
							<input type="text" id="searchStartDate" name="searchStartDate" class="date_input is-datepick datepicker" readonly="readonly" autocomplete="off"  value=""> <em> ~ </em>
							<input type="text" id="searchEndDate" name="searchEndDate" class="date_input is-datepick datepicker" readonly="readonly" autocomplete="off" value="">
						</td>
					</tr>
					<tr>
						<th>검색어</th>
						<td colspan="3">
							<input type="text" id="searchKey" name="searchKey" title="상세검색입력" style="width:450px;" maxlength="100" minlength="2"/>
						</td>
					</tr>
					<tr>
						<th>할인타입</th>
						<td>
							<label class="radio_box" for="mgrpCd1">
								<input type="radio" name="searchGubunType2" id="mgrpCd1" value="" checked="checked">
								<span>전체</span>
							</label>
							<label class="radio_box" for="mgrpCd2">
								<input type="radio" name="searchGubunType2" id="mgrpCd2" value="PER">
								<span>할인(%)</span>
							</label>
							<label class="radio_box" for="mgrpCd3">
								<input type="radio" name="searchGubunType2" id="mgrpCd3" value="WON">
								<span>할인금액(원)</span>
							</label>
						</td>
						<th>할인상태</th>
						<td>
							<label class="radio_box" for="lgrpCd1">
								<input type="radio" name="searchGubunType" id="lgrpCd1" value="" checked="checked">
								<span>전체</span>
							</label>
							<label class="radio_box" for="lgrpCd2">
								<input type="radio" name="searchGubunType" id="lgrpCd2" value="지급쿠폰">
								<span>지급</span>
							</label>
							<label class="radio_box" for="lgrpCd3">
								<input type="radio" name="searchGubunType" id="lgrpCd3" value="다운쿠폰">
								<span>다운</span>
							</label>
							<label class="radio_box" for="lgrpCd4">
								<input type="radio" name="searchGubunType" id="lgrpCd4" value="이벤트쿠폰">
								<span>이벤트</span>
							</label>
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
				<span>총<strong class="totCnt" style="font-weight: 700;"> <fmt:formatNumber value="${result.searchInfo.totalRow}" type="number"/></strong>건 조회</span>
			</div>
		</div>
    
    	<form name="subForm" method="post" action="${contextPath}/${requestUri}/delete.vc" onsubmit="var rtn = formSubmitObj.delSubmit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
		    <table class="cont_table" style="table-layout:fixed">
		    	<colgroup>
					<col style="width:80px">
					<col style="width:200px">  
					<col style="width:250px">
					<col style="width:100px">
					<col style="">
					<col style="width:120px">
					<col style="width:140px">
					<col style="width:100px">
				</colgroup>
		        <thead>
		            <tr>
		                <th>번호</th>
		                <th>쿠폰번호</th>
						<th>회원아이디</th>
						<th>쿠폰타입</th>
						<th>쿠폰명</th>
						<th>쿠폰다운일</th>
						<th>쿠폰유효기간</th>
						<th>사용상태</th>                           
		            </tr>
		        </thead>
		        <tbody>
		            <c:choose>
		                <c:when test="${empty result.list}">
		                    <tr>
		                        <td colspan="8" class="nodata">등록된 정보가 없습니다.</td>
		                    </tr>
		                </c:when>
		                <c:otherwise>
		                    <c:forEach items="${result.list}" var="data" varStatus="i">
		                        <tr>
		                            <td>${i.count}</td>
		                            <td>${data.cupNo}</td>
		                            <td>${data.mbrId}</td>
									<td>${data.lgrpCd}</td>
									<td>${data.cupTitl}</td>
									<td>${data.expsRgstDtm}</td>
									<td>${data.expsFnhDtm}</td>
									<td>${data.useYn eq 'Y' ? '미사용' : '사용완료'}</td>
		                        </tr>
		                    </c:forEach>
		                </c:otherwise>
		            </c:choose>
		        </tbody>
		    </table>
	    </form>
		    
	    <div class="paging_wrap">
	    	<tag:paging url="${contextPath}/${requestUri}/list.vc?${function:searchQueryPaging(result.searchInfo)}" totalRow="${result.searchInfo.totalRow}" page="${result.searchInfo.page}" rowLimit="${result.searchInfo.rowLimit}" />
	    </div>
    </div>
</div>

<script type="text/javascript">
$(function () {
	$('form[name=searchForm]').ready(function(){
    	$('input[name=searchGubunType][value="${result.searchInfo.searchGubunType}"]').prop('checked', true).trigger('click'); 
    	$('input[name=searchGubunType2][value="${result.searchInfo.searchGubunType2}"]').prop('checked', true).trigger('click'); 
    	$('input[name=searchStartDate]').val('${result.searchInfo.searchStartDate}');
    	$('input[name=searchEndDate]').val('${result.searchInfo.searchEndDate}');
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
        
        if(!submitUtil.isNull(form.searchStartDate) && !submitUtil.isNull(form.searchEndDate)){
    		if(!submitUtil.isDateCompare(form.searchStartDate, form.searchEndDate)){
            	return false;
    		}
        }
        
        return true;
    }
};
</script>