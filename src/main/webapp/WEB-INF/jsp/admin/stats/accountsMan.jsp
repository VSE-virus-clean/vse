<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<link rel="stylesheet" href="/resources/admin/ui_common/css/manage.css">
<style>
.ui-datepicker-calendar { display: none; }
.ui-datepicker-month { display: none; }
</style>


<div class="sec_top">
	<h3 class="sec_tit">통계관리</h3>
	<ul class="top_tab">
		<li><a href="#">정산 통계</a></li>
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
					   		<select id="searchStartDate" name="searchStartDate" title="기간">
		                        <option value="2021">2021</option>
		                    </select>							
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
		</form>
	</div>
	
	
	<div class="content-body">                   
		<table class="cont_table" style="TABLE-layout:fixed">
			<colgroup>
				<col width="">
				<col width="">
				<col width="">
				<col width="">
			</colgroup>
			<thead>
				<tr style="height:58px;">
					<th>
						<div class="stat_icon_wrap">
							<p>주문금액(가수금)</p>
						</div>
					</th>
					<th>
						<div class="stat_icon_wrap">
							<p>결제완료금액(매출)</p>
						</div>
					</th>
					<th>
						<div class="stat_icon_wrap">
							<p>취소/환불금액</p>
						</div>
					</th>
					<th>
						<div class="stat_icon_wrap">
							<p>구매확정금액(정산)</p>
						</div>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr style="height:58px;">
					<td>
						<p><span><fmt:formatNumber value="${result.summary.sale01}" type="number"/></span>원</p>
					</td>
					<td>
						<p><span><fmt:formatNumber value="${result.summary.sale02}" type="number"/></span>원</p>
					</td>
					<td>
						<p><span><fmt:formatNumber value="${result.summary.sale03}" type="number"/></span>원</p>
					</td>
					<td>
						<p><span><fmt:formatNumber value="${result.summary.sale04}" type="number"/></span>원</p>
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
  
  
<!--   아니면 취소된게 전월건지 다시 계산해야 한다는 의미? -->
<!--   환불건이 이전달에 결제된건지. 이전달에 결제된건지 확인해야 한다는 의미인거죠? -->
<!--   그럼 전월정산은 0아니면 마이너스 겟네요? -->
  
		<table class="cont_table" style="table-layout:fixed">
	    	<colgroup>
				<col style="width:150px">
				<col style="">
				<col style="">
				<col style="">
				<col style="">
				<col style="width:180px">
			</colgroup>
	        <thead>
	            <tr>
	                <th>월</th>
					<th>결제금액</th>
					<th>전월정산</th>
					<th>당월정산</th>
					<th>총합계</th>
	                <th>관리</th>
	            </tr>
	        </thead>
	        <tbody>
	            <c:choose>
	                <c:when test="${empty result.list}">
	                    <tr>
	                        <td colspan="6" class="nodata">정보가 없습니다.</td>
	                    </tr>
	                </c:when>
	                <c:otherwise>
	                    <c:forEach items="${result.list}" var="data" varStatus="i">
	                        <tr>
	                            <td>${data.rgstDtm}</td>
								<td><fmt:formatNumber value="${data.orderPrice}" type="number"/> 원</td>
								<td><fmt:formatNumber value="${data.preMonthPrice}" type="number"/> 원</td>
								<td><fmt:formatNumber value="${data.curMonthPrice}" type="number"/> 원</td>
								<td><fmt:formatNumber value="${data.totalPrice}" type="number"/> 원</td>
	                            <td class="table_btn">
	                            	<a class="btn btn_red btnOrderList" data-date="${data.rgstDtm}" href="#">보기</a>
								</td>
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
    	$('select[name=searchStartDate] option[value="${result.searchInfo.searchStartDate}"]').prop('selected', true);
    });
	
	$('.excelDown').on('click', function(event){
		event.preventDefault();  
		formSubmitObj.excelSubmit();
	});
	
	
	$('.btnOrderList').on('click', function(event){
		event.preventDefault();  
		var date = $(this).data('date');
		location.href = '/admin/shop/order/list.vc?searchStartDate=' + (date + '-01') + '&searchEndDate=' + ( date +'-' +  dateUtil.getMonthLastDay(date.split('-')[0], date.split('-')[1]));
	});
});

$('.datepicker').datepicker( {
      dateFormat: "yy"
     ,buttonImageOnly: false
     ,showMonthAfterYear: true
     ,changeMonth : false
     ,changeYear : true
     ,yearRange : 'c-10:c+0'
     ,setDate : 'today'
	 ,yearSuffix: "년" //달력의 년도 부분 뒤에 붙는 텍스트
   	 ,onClose: function(dateText, inst) { 
   		$(this).datepicker('setDate', new Date(inst.selectedYear, 0, 1));
   	 }
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
	excelSubmit : function(){
		location.href = '${contextPath}/${requestUri}/excel.vc?' + $('form[name=downForm]').serialize(); 
	    return false;
	}
};

</script>