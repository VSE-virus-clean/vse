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
<style>
.r_search_table input[type="checkbox"] {width: 16px ; position: relative; top: 5px; height: 16px; background-image: url(/resources/admin/images/common/checkoff.png); -webkit-appearance: none;  -moz-appearance: none;  appearance: none; background-color: transparent; border:none; -webkit-background-size: cover; background-size: cover; }
.r_search_table input[type="checkbox"]:checked{background-image: url(/resources/admin/images/common/checkon.png);} 
.r_search_table label { padding-left:7px; }
label.attention { color:red; }
</style>
<div class="sec_top">
	<h3 class="sec_tit">주문관리</h3>
	<ul class="top_tab">
		<li><a href="#">주문관리</a></li>
		<li><a href="#">주문현황</a></li>
	</ul>
</div>
<div class="sec_cont">
	<h4 class="cont_tit">조회조건</h4>
	<div class="r_search_box">
		<form name="searchForm" method="get" onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
		<input type="hidden" name="rowLimit" value="${result.searchInfo.rowLimit}" />
		<input type="hidden" name="searchGubunType" value="${result.searchInfo.searchGubunType}" />
			<table class="r_search_table">
				<colgroup>
					<col style="width:150px">
					<col style="">
					<col style="width:150px">
					<col style="">
				</colgroup>
				<tbody>
					<tr>
					   <th>주문일</th>
					   <td colspan="3">										
							<input type="text" id="searchStartDate" name="searchStartDate" class="date_input is-datepick datepicker" readonly="readonly" autocomplete="off"  title="기간"> <em> ~ </em>
							<input type="text" id="searchEndDate" name="searchEndDate" class="date_input is-datepick datepicker" readonly="readonly" autocomplete="off" title="기간">
						</td>
					</tr>
					<tr>
						<th>주문상태</th>
						<td colspan="3">
<!-- 						searchGubunTypeList0의 값이 하나라면 searchGubunType 에 넣어줄것. -->
<!-- 	                    	<input type="checkbox" class="nn-type" name="searchGubunType" id="searchGubunType0" value="" /><label for="searchGubunType0">전체</label> -->
							<input type="checkbox" class="on-type" name="gubunType" id="searchGubunType0" value="입금대기" /><label for="searchGubunType0">입금대기</label>
	                    	<input type="checkbox" class="on-type" name="gubunType" id="searchGubunType1" value="결제완료" /><label for="searchGubunType1" class="attention">결제완료</label>
	                    	<input type="checkbox" class="on-type" name="gubunType"  id="searchGubunType2" value="배송준비중" /><label for="searchGubunType2" class="attention">배송준비중</label>
	                    	<input type="checkbox" class="on-type" name="gubunType"  id="searchGubunType3" value="배송중" /><label for="searchGubunType3" class="attention">배송중</label>
	                    	<input type="checkbox" class="on-type" name="gubunType"  id="searchGubunType4" value="배송완료" /><label for="searchGubunType4">배송완료</label>
	                    	<input type="checkbox" class="on-type" name="gubunType"  id="searchGubunType5" value="주문취소" /><label for="searchGubunType5">주문취소</label>
	                    	<input type="checkbox" class="on-type" name="gubunType"  id="searchGubunType6" value="주문취소신청" /><label for="searchGubunType6" class="attention">주문취소신청</label>
	                    	<input type="checkbox" class="on-type" name="gubunType"  id="searchGubunType7" value="주문취소완료" /><label for="searchGubunType7">주문취소완료</label>
	                    	<input type="checkbox" class="on-type" name="gubunType"  id="searchGubunType8" value="반품신청" /><label for="searchGubunType8" class="attention">반품신청</label>
	                    	<input type="checkbox" class="on-type" name="gubunType"  id="searchGubunType9" value="반품완료" /><label for="searchGubunType9">반품완료</label>
	                    	<input type="checkbox" class="on-type" name="gubunType"  id="searchGubunType10" value="교환신청" /><label for="searchGubunType10" class="attention">교환신청</label>
	                    	<input type="checkbox" class="on-type" name="gubunType"  id="searchGubunType11" value="교환완료" /><label for="searchGubunType11">교환완료</label>
	                    	<input type="checkbox" class="on-type" name="gubunType"  id="searchGubunType12" value="구매확정" /><label for="searchGubunType12">구매확정</label>
							<ul class="warn">
		                     	<li>- 색이 다른 주문상태는 해당 주문상태만 단독으로 선택시 하단에 기능버튼이 활성화 됩니다.</li>
		                    </ul>
						</td>
					</tr>
					<tr>
						<th>검색어</th>
						<td colspan="3">
					   		<select id="searchType" name="searchType">
								<option value="">전체</option>
								<option value="orderNo">주문번호</option>
								<option value="userName">이름</option>
								<option value="email">이메일</option>
								<option value="phone">핸드폰번호</option>
							</select>
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
		<form name="downForm" method="get">
			<input type="hidden" name="searchGubunType" value="${result.searchInfo.searchGubunType}" />
			<input type="hidden" name="searchType" value="${result.searchInfo.searchType}" />
			<input type="hidden" name="searchKey" value="${result.searchInfo.searchKey}" />
			<input type="hidden" name="searchStartDate" value="${result.searchInfo.searchStartDate}" />
			<input type="hidden" name="searchEndDate" value="${result.searchInfo.searchEndDate}" />
		</form>
	</div>
	
	<div class="content-body">
		<div class="table_tit left_right">
			<div class="table_left_title">
				<span>총<strong class="totCnt" style="font-weight: 700;"> <fmt:formatNumber value="${result.searchInfo.totalRow}" type="number"/></strong>건 조회</span>
			</div>
    		<div class="right">
    			<a href="${contextPath}/${requestUri}/allList.vc" class="btnstyle blue excelDown"><span>엑셀 다운로드</span></a> 
    			<select name="rowLimit" id="rowLimit">
					<option value="10">10개씩보기</option>
                    <option value="20">20개씩보기</option>
                    <option value="50">50개씩보기</option>
                    <option value="100">100개씩보기</option>
                    <option value="1000">전건보기</option>
				</select>
    		</div>
		</div>
    
    	<form name="subForm" method="post" action="${contextPath}/shop/order/status/modify.vc" onsubmit="var rtn = formSubmitObj.delSubmit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
		<input type="hidden" name="oldOrderCd" value="${result.searchInfo.searchGubunType}" />
		<input type="hidden" name="nowOrderCd" value="" />
		<input type="hidden" name="excSbc2" value="" />
		
		    <table class="cont_table" style="table-layout:fixed">
		    	<colgroup>
		    		<col style="width:100px">
					<col style="width:190px">  
					<col style="width:130px">
					<col style="width:100px">
					<col style="width:150px">
					<col style="">
					<col style="width:150px">
					<col style="width:120px">
					<col style="width:100px">
					<col style="width:120px">
					<col style="width:100px">
				</colgroup>
		        <thead>
		            <tr>
		            	<th><input type="checkbox" class="checkbox-selectAll" id="checkbox-selectAll">번호</th>
		                <th>주문번호</th>
						<th>주문일시</th>
						<th>회원구분</th>
						<th>주문자</th>                           
						<th>상품정보</th>
						<th>결제금액</th>
						<th>결제방식</th>
		                <th>상태</th>
		                <th>최종상태<br/> 처리일시</th>
		                <th>관리</th>
		            </tr>
		        </thead>
		        <tbody>
		            <c:choose>
		                <c:when test="${empty result.list}">
		                    <tr>
		                        <td colspan="11" class="nodata">등록된 정보가 없습니다.</td>
		                    </tr>
		                </c:when>
		                <c:otherwise>
		                    <c:forEach items="${result.list}" var="data" varStatus="i">
		                        <tr>
		                        	<td>
		                            	<input type="checkbox" name="delSeq" class="check2 checkbox-select" value="${data.orderNo}" title="선택"> ${function:rowNumber(result.searchInfo, i.count)}
		                            </td>
		                            <td>${data.orderNo}</td>
									<td>${data.rgstDtm}</td>
									<td>
										<c:choose>
											<c:when test="${data.mbrSn  eq 0}">비회원</c:when>
											<c:otherwise>일반회원</c:otherwise>
										</c:choose>
									</td>
									<td>${data.buyerNm}</td>
									<td>${data.goodNm}</td>
									<td><fmt:formatNumber value="${data.orderPrice}" type="number"/>원</td>
									<td>${data.payTypeTxt}</td>
									<td>${data.orderCd}</td>
									<td>${data.userViewDtm}</td>
		                            <td class="table_btn">
		                            	<a class="btn btn_red" href="${contextPath}/${requestUri}/view.vc?orderNo=${data.orderNo}${function:searchQuery(result.searchInfo)}">보기</a>
									</td>
		                        </tr>
		                    </c:forEach>
		                </c:otherwise>
		            </c:choose>
		        </tbody>
		    </table>
			<div class="btn_center_gorup clearfix">
				<div class="left">
					<c:if test="${result.searchInfo.searchGubunType eq '결제완료'}">
						<button type="submit" onclick="$('form[name=subForm] input[name=nowOrderCd]').val('배송준비중');" class="btn btn_bk">배송준비중</button>
					</c:if>
					<c:if test="${result.searchInfo.searchGubunType eq '배송준비중'}">
						<button type="submit" onclick="$('form[name=subForm] input[name=nowOrderCd]').val('배송중');" class="btn btn_bk">배송중</button>
					</c:if>
					<c:if test="${result.searchInfo.searchGubunType eq '배송중'}">
						<button type="submit" onclick="$('form[name=subForm] input[name=nowOrderCd]').val('배송완료');" class="btn btn_bk">배송완료</button>
					</c:if>
					<c:if test="${result.searchInfo.searchGubunType eq '주문취소신청'}">
						<button type="submit" onclick="$('form[name=subForm] input[name=nowOrderCd]').val('주문취소완료');" class="btn btn_bk">주문취소승인</button>
					</c:if>
					<c:if test="${result.searchInfo.searchGubunType eq '반품신청'}">
						<button type="submit" onclick="$('form[name=subForm] input[name=nowOrderCd]').val('반품완료');" class="btn btn_bk">반품승인</button>
					</c:if>
					<c:if test="${result.searchInfo.searchGubunType eq '교환신청'}">
						<button type="submit" onclick="$('form[name=subForm] input[name=nowOrderCd]').val('교환완료');" class="btn btn_bk">교환승인</button>
					</c:if>
				</div>
			</div>
	    </form>
				    
	    <div class="paging_wrap">
	    	<tag:paging url="${contextPath}/${requestUri}/list.vc?${function:searchQueryPaging(result.searchInfo)}" totalRow="${result.searchInfo.totalRow}" page="${result.searchInfo.page}" rowLimit="${result.searchInfo.rowLimit}"/>
	    </div>
    </div>
</div>

<script type="text/javascript">
$(function () {
    $('form[name=searchForm]').ready(function(){
//     	comCodeUtil.getCodeNPrint('ORDER', 'searchGubunType', 'select');
        $('input[name=searchKey]').val('${result.searchInfo.searchKey}');  
        $('select[name=searchType] option[value="${result.searchInfo.searchType}"]').prop('selected', true);
//         $('select[name=searchGubunType] option[value="${result.searchInfo.searchGubunType}"]').prop('selected', true);
        $('input[name=searchStartDate]').val('${result.searchInfo.searchStartDate}');  
	 	$('input[name=searchEndDate]').val('${result.searchInfo.searchEndDate}');
	 	$('select[name=rowLimit] option[value="${result.searchInfo.rowLimit}"]').prop('selected', true);
	 	
	 	<c:choose>
	 	<c:when test="${not empty result.searchInfo.searchGubunType}">
	 		<c:forTokens var="type" items="${result.searchInfo.searchGubunType}" delims="|">
	 			$('input[name=gubunType][value="${type}"]').prop('checked', true);
	 		</c:forTokens>
	 	</c:when>
	 	</c:choose>
	 	
	 	$(this).parents('table').find('input.checkbox-select').prop('checked', $(this).is(':checked'));
    });
    
//     $('input.on-type').live({
//         change : function(event){
//         	$('input.nn-type')
//             $(this).parents('table').find('input.checkbox-select').prop('checked', $(this).is(':checked'));
        	
        	
//         	$.map($('input.on-type'), function(element, index) {

//         		return element.val();
        	
//         	}).get().join('|');
        	
//         	$.map($('input[name=searchGubunType]'), function(element, index) { 
//         		if(element.checked){ return element.value; }
        		        	
//  		    }).join('|');
//     });

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
        
        form.searchGubunType.value = $.map(form.gubunType, function(element, index) { 
							   			if(element.checked){ return element.value; }
								     }).join('|');
        
								    
        return true;
    },
	delSubmit : function(form){
        if($('input.checkbox-select:checked').length > 0){
        	var msg = '주문상태를 ${result.searchInfo.searchGubunType} 에서 ' + form.nowOrderCd.value + ' 으로 변경'
    		if(confirm(msg + ' 하시겠습니까?')){
    			form.excSbc2.value = '[${sessionScope.sessionVO.name}]' + msg;
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