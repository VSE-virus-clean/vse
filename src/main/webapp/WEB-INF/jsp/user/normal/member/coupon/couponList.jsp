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
	
	<div class="coupon">
		<div class="inner">
		
			<%@ include file="/WEB-INF/jsp/user/normal/member/inc_mypageTop.jsp" %>
			
			<div class="gray_bar">
				<div class="box_inner">
					<form name="searchForm" method="get" onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
						<div class="box_date01">
							<p class="bar_tit">발급일</p>
							<div class="cal_btn_wrap">
								<button type="button" class="" onclick="addDay(1);">오늘</button>
								<button type="button" class="" onclick="addDay(7);">7일</button>
								<button type="button" class="" onclick="addDay(15);">15일</button>
								<button type="button" class="" onclick="addDay(30);">30일</button>
							</div>	
						</div>
						<div class="cal_input">
							<input type="text" id="searchStartDate" name="searchStartDate" class="date_input is-datepick datepicker" readonly="readonly" autocomplete="off"  value="">
							<em>~</em>
							<input type="text" id="searchEndDate" name="searchEndDate" class="date_input is-datepick datepicker" readonly="readonly" autocomplete="off" value="">
						</div>
						<button class="btn02 btn_pp" style="width: 170px;"><span>조회</span></button>
					</form>						
				</div>
			</div>
		</div>	
		<div class="tab_ui col_2">
			<div class="tab">
				<ul class="inner clearfix">
					<li class="${pageMenuId eq 'KMEEA' ? 'active' : '' }"><a href="/member/coupon/active/list.vc">사용 가능한 쿠폰 내역</a></li>
					<li class="${pageMenuId eq 'KMEEB' ? 'active' : '' }"><a href="/member/coupon/inactive/list.vc">사용한 쿠폰 내역</a></li>
				</ul>
			</div>
			<div class="tab_cont">
				<ul>
					<li class="active">
						<div class="inner">
							<div class="coupon_cont">
								<div class="table-type01">
									<table>
										<thead>
											<tr>
												<th style="width:42%">쿠폰정보</th>
												<th  style="width:15%">쿠폰번호</th>
												<th style="width:15%">사용상태</th>
												<th style="width:28%">유효기간</th>
											</tr>
										</thead>
										<tbody>
										<c:choose>
							                <c:when test="${empty result.list}">
							                	<c:if test="${pageMenuId eq 'KMEEA'}">
							                    	<tr><td colspan="4" class="nodata">사용 가능한 쿠폰이 없습니다.</td></tr>
							                    </c:if>
							                    <c:if test="${pageMenuId eq 'KMEEB'}">
							                    	<tr><td colspan="4" class="nodata">사용한 쿠폰이 없습니다.</td></tr>
							                    </c:if>
							                </c:when>
							                <c:otherwise>
							                    <c:forEach items="${result.list}" var="data" varStatus="i">
													<tr>
														<td class="coupon_info">
															<img src="/resources/user/images/mypage/c_icon.png" alt="">
															<p class="coupon_tit">
																<strong>[${data.lgrpCd}] ${data.cupTitl}</strong><br>${data.cupTitl}
															</p>
														</td>	
														<td class="pcTab">
															<p>${data.cupNo}</p>
														</td>
														<td class="order_prd_img pcTab">
															${data.useYn eq 'Y' ? '미사용' : '사용가능'}
														</td>
														<td class="pcTab">
															<c:choose>
																<c:when test="${empty data.expsFnhDtm}">유효기간 없음.</c:when>
																<c:otherwise>${data.expsRgstDtm} ~ ${data.expsFnhDtm}</c:otherwise>
															</c:choose>
														</td>
														
														
														<td class="mo mo_coup_cont">
															<div class="mo_coup_wrap">
																<div class="coup_numb_wrap">
																	<p class="mo">쿠폰번호</p>
																	<p class="coup_numb">${data.cupNo}</p>
																</div>
																<div class="usage_wrap">
																	<p class="mo">사용상태</p>
																	<p class="order_prd_img">${data.useYn eq 'Y' ? '미사용' : '사용'}</p>
																</div>
																<div class="available_wrap">
																	<p>유효기간</p>
																	<p>
																		<c:choose>
																			<c:when test="${empty data.expsFnhDtm}">유효기간 없음.</c:when>
																			<c:otherwise>${data.expsRgstDtm} ~ ${data.expsFnhDtm}</c:otherwise>
																		</c:choose>
																	</p>
																</div>
															</div>
														</td>
													</tr>
							                    </c:forEach>
							                </c:otherwise>
							            </c:choose>
							            
										</tbody>
									</table>
								</div>
								<div class="gray_bar coup_regi">
									<div class="box_inner">
										<form name="subForm" method="post" action="/member/coupon/register.vc" onsubmit="var rtn = formSubmitObj.submitReg(this); if(!rtn){ submitUtil.enable(); } return rtn;">
											<div class="coup_regi_form">
												<p class="bar_tit">쿠폰등록</p>
												<input type="text" name="cupNo" maxlength="20">
											</div>
											<button type="submit" class="btn02 btn_pp" style="width: 170px;"><span>등록</span></button>
										</form>						
									</div>
								</div>
								<h4 class="cont_tit02">다운로드 가능한 쿠폰</h4>
								<div class="gray_box coup_able">
									<div class="gray_box_inner">
										<ul class="coupon_use">
											<c:choose>
								                <c:when test="${empty resultDown.list}">
								                    <li class="nodata">다운로드 가능한 쿠폰이 없습니다.</li>
								                </c:when>
								                <c:otherwise>
								                    <c:forEach items="${resultDown.list}" var="data" varStatus="i">
								                    	<li>
								                    		<div class="coupon_able_wrap">
																<div class="coupon_img">
																	${function:printImageFileByList(data.fileSn, "COUPON", data.filNm, data.filNm)}
																</div>
																<a href="#" class="btnDownCoupon" data-sn="${data.cupMetaSn}"><span>쿠폰 <br class="mo">다운받기</span></a>
															</div>
														</li>
								                    </c:forEach>
								                </c:otherwise>
								            </c:choose>

										</ul>
									</div>
								</div>
							</div>
							
						</div>
					</li>
				</ul>
			</div>
		</div> 
	</div>		
</div>

<%@ include file="/WEB-INF/jsp/user/normal/common/include/inc_modal.jsp" %>

<script src="/resources/user/js/order.js"></script>
<script>

$(function () {
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
    });
    
    $('a.btnDownCoupon').on('click', function(event){
    	event.preventDefault();  
    	ajaxUtil.postDisableAsync('/member/coupon/down.vc', { 'cupMetaSn' : $(this).data('sn')}, formSubmitObj.resultDown);
    });
});

function addDay(_day){
	 $('input[name=searchStartDate]').val(dateUtil.getDate());
	 $('input[name=searchEndDate]').val(dateUtil.addDate('d', _day, $('input[name=searchStartDate]').val()));
}

var formSubmitObj = {
    submit : function(form){
    	
    	if(!submitUtil.isNull(form.searchStartDate) && !submitUtil.isNull(form.searchEndDate)){
    		if(!submitUtil.isDateCompare(form.searchStartDate, form.searchEndDate)){
            	return false;
    		}
        }
    	
        return true;
    },
    submitReg : function(form){
    	if(!submitUtil.isEmpty(form.cupNo, '쿠폰번호를 입력해 주세요.')){
        	return false;
        }  
    	
    	ajaxUtil.postDisableAsync(form.action, $(form).serialize(), formSubmitObj.resultReg);
    	
    	return false;
    },
    resultReg : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        if(json.result != undefined && json.result.status){
            location.reload(true);
        }else{
            alert('정상쿠폰이 아닙니다 확인바랍니다.');
        }
    },
	resultDown : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        if(json.bindingStatus == undefined && json.result != undefined && json.result.status){
        	alert('다운받았습니다. 쿠폰함에서 확인해 주세요.');
        	location.reload(true);
        }else{
            ajaxUtil.error(json);
        }
    },
};
</script>
