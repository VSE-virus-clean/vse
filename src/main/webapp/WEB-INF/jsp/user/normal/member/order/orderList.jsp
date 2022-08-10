<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
    JSP Name : communityList.jsp
    Description : 커뮤니티 목록
--%>
<link rel="stylesheet" href="/resources/user/css/mypage.css?v=${cacheParam}">

<div id="container">
	<div class="order history_page">
		<div class="inner">
		
			<%@ include file="/WEB-INF/jsp/user/normal/member/inc_mypageTop.jsp" %>
		
			<div class="detail_info">
				<div class="tab_ui col_3">
					<div class="tab">
						<ul class="inner clearfix">
							<li class="${pageMenuId eq 'KMEDA' ? 'active' : '' }"><a href="/member/order/active/list.vc">주문내역 조회</a></li>
							<li class="${pageMenuId eq 'KMEDB' ? 'active' : '' }"><a href="/member/order/inactive/list.vc">취소 / 교환 / 반품 내역</a></li>
						</ul>
					</div>
					<div class="tab_cont">
						<div class="order_cart">
							<form name="searchForm" method="get" onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
								<div class="table-type01">
									<table>
										<colgroup>
											<col style="width:15%;">
											<col style="width:85%;">
										</colgroup>
										<tbody>
											<tr>
												<th><p>상태</p></th>
												<td>
													<select id="searchGubunType" name="searchGubunType">
														<option value="">선택</option>
														<c:if test="${pageMenuId eq 'KMEDA'}">
															<option value="결제완료">결제완료</option>
															<option value="배송준비중">배송준비중</option>
															<option value="배송중">배송중</option>
															<option value="배송완료">배송완료</option>
															<option value="구매확정">구매확정</option>
														</c:if>
														<c:if test="${pageMenuId eq 'KMEDB'}">
															<option value="주문취소">주문취소</option>
															<option value="취소신청">취소신청</option>
															<option value="취소완료">취소완료</option>
															<option value="반품신청">반품신청</option>
															<option value="반품완료">반품완료</option>
															<option value="교환신청">교환신청</option>
															<option value="교환완료">교환완료</option>
														</c:if>
													</select>
												</td>
											</tr>
											<tr>
												<th><p>기간</p></th>
												<td class="cal_input">
													<input type="text" id="searchStartDate" name="searchStartDate" class="datepicker" readonly="readonly" autocomplete="off" value="">
													<em>~</em>
													<input type="text" id="searchEndDate" name="searchEndDate" class="datepicker" readonly="readonly" autocomplete="off" value="">
												</td>
											</tr>
										</tbody>
									</table>
								</div>
								<div class="btn_wrap">
									<a href="#" class="btn btn_pp submit" style="width: 330px;"><span>검색</span></a>
								</div>
							</form>
						</div>
						
						<c:choose>
						<c:when test="${not empty resultOrder.list}">
							<c:forEach items="${resultOrder.list}" var="data" varStatus="i">
								<div class="history_cart history_cart01">
									<div class="table-type01">
										<table>
											<colgroup>
												<col style="">
												<col>
												<col>
												<col>
												<col>
											</colgroup>
											<thead>
												<tr class="order_state">
													<th colspan="3">
														<span><b>&middot;</b> &nbsp;주문번호 ${data.orderNo}</span>&emsp;
														<span class="date_gray">${data.userViewDtm}</span>
													</th>
													<th colspan="2" ></th>
												</tr>
											</thead>
											<tbody>
												<tr class="history_tbl_tr">
													<td colspan="2" class="pro_detail">
														<div class="pro_img">
															<img src="/resources/user/images/product/product_img.jpg" alt="">
														</div>
														<div class="name">
															<span class="pro_name">${data.goodNm}</span>
															<span class="pro_descipt"><fmt:formatNumber value="${data.productPrice}" type="number"/>원<em>&emsp;I&emsp;</em>${data.orderQuantity}개</span>
														</div>
													</td>
													<td class="qaunt">
														<p>배송비 <b><fmt:formatNumber value="${data.deliveryFee}" type="number"/>원</b><em>&emsp;I&emsp;<b>택배</b></p>
													</td>
													<td class="price total_price text_right">
														<p class="txt_orng">${data.orderCd}</p>
														<p class="total_price_bold"><fmt:formatNumber value="${data.orderPrice}" type="number"/>원</p>
													</td>
													<td class="history_detail_btn" style="">
														<button type="button" class="btn02" style="width: 130px;" onclick="location.href='${contextPath}/${requestUri}/view.vc?orderNo=${data.orderNo}';">
															<span>상세보기</span>
														</button>
													</td>
												</tr>
											</tbody>
										</table>
									</div>
								</div>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<div class="history_cart history_cart01">
								<div class="table-type01">
									<table>
										<colgroup>
											<col style="">
											<col>
											<col>
											<col>
											<col>
										</colgroup>
										<tbody>
											<tr class="nodata_wrap">
												<td colspan="5" class="nodata"><p>선택하신 조건에 대한 검색 결과가 없습니다.</p></td>
											</tr>
										</tbody>
									</table>
								</div>
							</div>
						</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>				

		</div>						
	</div>		
</div>

<%@ include file="/WEB-INF/jsp/user/normal/common/include/inc_modal.jsp" %>

<script src="/resources/user/js/order.js"></script>
<script>
$(function(){
	$('form[name=searchForm]').ready(function(){
	 	<c:choose>
    	<c:when test="${empty result.searchInfo.searchStartDate}">
	    	$('input[name=searchStartDate]').val(dateUtil.getDate());
	    	$('input[name=searchEndDate]').val(dateUtil.addDate("d", 7, dateUtil.getDate()));
    	</c:when>
    	<c:otherwise>
	    	$('input[name=searchStartDate]').val('${result.searchInfo.searchStartDate}');
	    	$('input[name=searchEndDate]').val('${result.searchInfo.searchEndDate}');
    	</c:otherwise>
    	</c:choose>
    	
	 	
		$('select[name=searchGubunType] option[value="${resultOrder.searchInfo.searchGubunType}"]').prop('selected', true);
	});
});

var formSubmitObj = {
    submit : function(form){
    	
    	if(!submitUtil.isNull(form.searchStartDate) && !submitUtil.isNull(form.searchEndDate)){
    		if(!submitUtil.isDateCompare(form.searchStartDate, form.searchEndDate)){
            	return false;
    		}
        }
    	
        return true;
    }
};
</script>
