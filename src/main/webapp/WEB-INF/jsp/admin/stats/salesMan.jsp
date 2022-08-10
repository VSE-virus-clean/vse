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
<c:choose>
<c:when test="${result.searchInfo.searchType eq 'month'}">
	.ui-datepicker-calendar { display: none; }
</c:when>
<c:when test="${result.searchInfo.searchType eq 'year'}">
	.ui-datepicker-calendar { display: none; }
	.ui-datepicker-month { display: none; }
</c:when>
</c:choose>    

.google-visualization-table-table th { padding: 10px 0; }
.google-visualization-table-table td { padding: 10px 0; }
.google-visualization-table-table td:nth-child(0) { text-align:center; }
.google-visualization-table-table td:nth-child(1) { text-align:center; }
.google-visualization-table-table td:nth-child(2) { text-align:center; }
</style>


<div class="sec_top">
	<h3 class="sec_tit">통계관리</h3>
	<ul class="top_tab">
		<li><a href="#">매출 통계</a></li>
	</ul>
</div>
<div class="sec_cont">
	<div class="sec_cont_tab">
		<ul class="tab_wrap clearfix">
			<li class="${result.searchInfo.searchType eq 'day'  ? 'active' : ''}"><a href="${contextPath}/${requestUri}.vc?searchType=day">일별</a></li>
			<li class="${result.searchInfo.searchType eq 'month'  ? 'active' : ''}"><a href="${contextPath}/${requestUri}.vc?searchType=month">월별</a></li>
			<li class="${result.searchInfo.searchType eq 'year'  ? 'active' : ''}"><a href="${contextPath}/${requestUri}.vc?searchType=year">년별</a></li>
		</ul>
	</div>
</div>

<div class="sec_cont">	
	<div class="r_search_box">
		<form name="searchForm" method="get" onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
			<input type="hidden" name="searchType" value="${result.searchInfo.searchType}" />
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
				</tbody>
			</table>
			<div class="btn_right_gorup">
				<button type="submit" class="btn btn_red">조회</button>
				<button type="reset" class="btn btn_gray">초기화</button>
			</div>
		</form>
		<form name="downForm" method="get">
			<input type="hidden" name="searchType" value="${result.searchInfo.searchType}" />
			<input type="hidden" name="searchStartDate" value="${result.searchInfo.searchStartDate}" />
			<input type="hidden" name="searchEndDate" value="${result.searchInfo.searchEndDate}" />
		</form>
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
  
		<div class="grp_wrap" style="padding:0;">
			<div class="grp">
				<div class="grp_inner" id="divStatsChart">
					<!-- 막대그래프 -->
				</div>
			</div>	
		</div>
		
		<div class="mt20 mb20">
			<div class="grp">
				<div class="grp_inner" id="divStatsTable">
					<!-- 테이블그래프 -->
				</div>
			</div>	
		</div>
		    
    </div>
</div>

<script src="https://www.gstatic.com/charts/loader.js"></script>      
<script type="text/javascript">
$(function () {
	$('form[name=searchForm]').ready(function(){
    	$('input[name=searchStartDate]').val('${result.searchInfo.searchStartDate}');  
    	$('input[name=searchEndDate]').val('${result.searchInfo.searchEndDate}');  
    });
	
	$('.excelDown').on('click', function(event){
		event.preventDefault();  
		formSubmitObj.excelSubmit();
	});
});

<c:choose>
<c:when test="${result.searchInfo.searchType eq 'month'}">
	$('.datepicker').datepicker( {
		  dateFormat: "yy-mm"
		 ,buttonImageOnly: false
	     ,showMonthAfterYear: true
	     ,changeMonth : true
	     ,changeYear : true
	     ,yearRange : 'c-30:c+60'
	     ,setDate : 'today'
		 ,yearSuffix: "년" //달력의 년도 부분 뒤에 붙는 텍스트
		 ,monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 텍스트
		 ,monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] //달력의 월 부분 Tooltip 텍스트
	     ,onClose: function(dateText, inst) { 
	       $(this).datepicker('setDate', new Date(inst.selectedYear, inst.selectedMonth, 1));
	   	 }
	});
</c:when>
<c:when test="${result.searchInfo.searchType eq 'year'}">
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
</c:when>
</c:choose>

google.charts.load('current', {'packages':['corechart', 'table']});
google.charts.setOnLoadCallback(drawChart);

function drawChart() {
	var data = new google.visualization.DataTable();
  	data.addColumn('string', '일자');
  	data.addColumn('number', '매출(원)');
  	data.addRows([
  		<c:forEach var="data" items="${result.list}" varStatus="i">
			['${data.rgstDtm}',  ${data.totalPrice}]<c:if test="${not i.last}">,</c:if>
		</c:forEach>
	]);
  	
  	var data2 = new google.visualization.DataTable();
  	data2.addColumn('string', '일자');
  	data2.addColumn('number', '주문수');
  	data2.addColumn('number', '상품구매금액');
  	data2.addColumn('number', '배송비');
  	data2.addColumn('number', '쿠폰');
  	data2.addColumn('number', '결제합계');
  	data2.addColumn('number', '환불합계');
  	data2.addColumn('number', '순매출');
  	data2.addRows([
  		<c:forEach var="data" items="${result.list}" varStatus="i">
			['${data.rgstDtm}', ${data.orderQuantity}, ${data.productPrice}, ${data.deliveryFee}, ${data.discountPrice}, ${data.orderPrice}, ${data.refundPrice}, ${data.totalPrice}]<c:if test="${not i.last}">,</c:if>
		</c:forEach>
	]);

    // Set chart options
    var options = {        	
        height: 500
    };

    var chart = new google.visualization.ColumnChart(document.getElementById('divStatsChart'));
    chart.draw(data, options);
    
    var options2 = {
   		showRowNumber: true, width: '100%', height: '100%'
    }
    
    var table = new google.visualization.Table(document.getElementById('divStatsTable'));
    table.draw(data2, options2);
}

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