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
	<div class="order history_page n_member">
		<div class="inner">
			<div class="location_bar">
				<ul class="clearfix inner pcTab">
					<li><a href="/index.vc">Home</a></li>
					<li><a href="#">비회원 주문 조회</a></li>
				</ul>
				<div class="mo inner">
					<a href="" ></a>
				</div>			
			</div>
			<h3 class="p_tit">비회원 주문 조회</h3>
		
			<div class="detail_info">
				<div class="history_cart history_cart01 history_cart02 active">
					<c:choose>
					<c:when test="${empty result.info}">
						<div class="table-type01">
							주문 내역이 없습니다. 주문 정보를 확인하여 주시기 바랍니다.
						</div>
					</c:when>
					<c:otherwise>
						<div class="table-type01">
							<table>
								<colgroup>
									<col >
									<col>
									<col>
									<col >
									<col>
								</colgroup>
								<thead>
									<tr class="order_state">
										<th colspan="3">
											<span><b>&middot;</b> &nbsp;주문번호 ${result.info.orderNo}</span>&emsp;
											<span class="date_gray">${result.info.userViewDtm}</span>
										</th>
										<th colspan="2" class="txt_process">
												<span class="${result.info.orderCd eq '결제완료' ? 'txt_orng' : ''}">결제완료 </span>

												<c:if test="${fn:startsWith(result.info.orderCd, '배송')}">
													<span class="${result.info.orderCd eq '배송준비중' ? 'txt_orng' : ''}">배송준비중 </span>
													<span class="${result.info.orderCd eq '배송중' ? 'txt_orng' : ''}">배송중 </span>
													<span class="${result.info.orderCd eq '배송완료' ? 'txt_orng' : ''}">배송완료 </span>
												</c:if>
												<c:if test="${result.info.orderCd eq '주문취소'}">
													<span class="${result.info.orderCd eq '주문취소' ? 'txt_orng' : ''}">주문취소</span>
												</c:if>
												<c:if test="${fn:startsWith(result.info.orderCd, '취소')}">
													<span class="${result.info.orderCd eq '취소신청' ? 'txt_orng' : ''}">취소신청</span>
													<span class="${result.info.orderCd eq '취소승인' ? 'txt_orng' : ''}">취소승인 </span>
													<span class="${result.info.orderCd eq '취소완료' ? 'txt_orng' : ''}">취소완료 </span>
												</c:if>
												<c:if test="${fn:startsWith(result.info.orderCd, '교환')}">
													<span class="${result.info.orderCd eq '교환신청' ? 'txt_orng' : ''}">교환신청</span>
													<span class="${result.info.orderCd eq '교환승인' ? 'txt_orng' : ''}">교환승인 </span>
													<span class="${result.info.orderCd eq '교환완료' ? 'txt_orng' : ''}">교환완료 </span>
												</c:if>
												<c:if test="${fn:startsWith(result.info.orderCd, '반품')}">
													<span class="${result.info.orderCd eq '반품신청' ? 'txt_orng' : ''}">반품신청 </span>
													<span class="${result.info.orderCd eq '반품승인' ? 'txt_orng' : ''}">반품승인 </span>
													<span class="${result.info.orderCd eq '반품완료' ? 'txt_orng' : ''}">반품완료 </span>
												</c:if>
										</th>
									</tr>
								</thead>
								<tbody>
									<tr class="history_tbl_tr">
										<td colspan="2" class="pro_detail">
											<div class="pro_img">
												<img src="/resources/user/images/product/product_img.jpg" alt="">
											</div>
											<div class="name">
												<span class="pro_name">${result.info.goodNm}</span>
												<span class="pro_descipt"><fmt:formatNumber value="${result.info.productPrice}" type="number"/>원<em>&emsp;I&emsp;</em>${result.info.orderQuantity}개</span>
											</div>
										</td>
										<td class="qaunt">
											<p>배송비 <b><fmt:formatNumber value="${result.info.deliveryFee}" type="number"/>원</b><em>&emsp;I&emsp;</em><b>택배</b></p>
										</td>
										<td colspan="2" class="price total_price text_right">
											<p class="txt_orng">${result.info.orderCd}</p>
											<p class="total_price_bold"><fmt:formatNumber value="${result.info.orderPrice}" type="number"/>원</p>
										</td>
									</tr>
								</tbody>
							</table>
						</div>

						<div class="history_detail">
							<c:if test="${result.info.orderCd eq '결제완료' or result.info.orderCd eq '입금대기'}">
								<div class="btn_wrap btn_wrap_right">
									<a href="#" class="btn btn_gray btnHistoryModalShow" data-cd="주문취소" style="width: 200px;">
										<span>주문취소</span>
									</a>
								</div>
							</c:if>
							<c:if test="${result.info.orderCd eq '배송준비중'}">
								<div class="btn_wrap btn_wrap_right">
									<a href="#" class="btn btn_gray btnHistoryModalShow" data-cd="주문취소신청" style="width: 200px;">
										<span>주문취소신청</span>
									</a>
								</div>
							</c:if>
							<c:if test="${result.info.orderCd eq '주문취소신청'}">
								<div class="btn_wrap btn_wrap_right">
									<a href="#" class="btn btn_gray btnHistoryMody" data-cd="배송준비중" style="width: 200px;">
										<span>주문취소철회</span>
									</a>
								</div>
							</c:if>
							<c:if test="${result.info.orderCd eq '배송중'}">
								<div class="btn_wrap btn_wrap_right">
									<a href="#" class="btn btn_gray btnHistoryModalShow" data-cd="교환신청" style="width: 200px;">
										<span>교환신청</span>
									</a>
									<a href="#" class="btn btn_gray btnHistoryModalShow" data-cd="반품신청" style="width: 200px;">
										<span>반품신청</span>
									</a>
								</div>
							</c:if>
							<c:if test="${result.info.orderCd eq '배송완료'}">
								<div class="btn_wrap btn_wrap_right">
									<a href="#" class="btn btn_gray btnHistoryModalShow" data-cd="교환신청" style="width: 200px;">
										<span>교환신청</span>
									</a>
									<a href="#" class="btn btn_gray btnHistoryModalShow" data-cd="반품신청" style="width: 200px;">
										<span>반품신청</span>
									</a>
									<a href="#" class="btn btn_gray btnHistoryMody" data-cd="구매확정" style="width: 200px;">
										<span>구매확정</span>
									</a>
								</div>
							</c:if>
							<c:if test="${result.info.orderCd eq '구매확정'}">
								<div class="btn_wrap btn_wrap_right">
									<a href="javascript:;" class="btn btn_gray" onclick="modalUtil.open('review-register');" style="width: 200px;">
										<span>리뷰등록</span>
									</a>
								</div>
							</c:if>
							<div class="history_detail_wrap">
								<h4 class="cont_tit02 cont_tit02_first">결제정보</h4>
								<div class="table-type01 text_right">
									<table>
										<colgroup>
											<col style="width:20%">
											<col style="width:40%">
											<col style="width:40%">
										</colgroup>			
										<tbody>
											<tr>
												<th>결제수단</th>
												<td>
													<p>${result.info.payTypeTxt}</p>
												</td>
												<td rowspan="4" class="total_price">
													<p>총결제금액</p>
													<p class="total_price_bold">
														<fmt:formatNumber value="${result.info.orderPrice}" type="number"/>원
													</p>
												</td>
											</tr>
											<tr>
												<th>주문상품 합계</th>
												<td class="txt_bg_gray">
													<p><fmt:formatNumber value="${result.info.productPrice}" type="number"/>원</p>
												</td>
											</tr>
											<tr>
												<th>총 배송비</th>
												<td class="txt_bg_gray">
													<p><fmt:formatNumber value="${result.info.deliveryFee}" type="number"/>원</p>
												</td>
											</tr>
											<tr>
												<th>상품할인(쿠폰)</th>
												<td class="txt_bg_gray">
													<p><fmt:formatNumber value="${result.info.discountPrice}" type="number"/>원</p>
												</td>
											</tr>
										</tbody>
									</table>
									<div class="total_price mo">
										<p>총결제금액</p>
										<p class="total_price_bold">
											<fmt:formatNumber value="${result.info.orderPrice}" type="number"/>원
										</p> 
									</div>
								</div>
								
								<c:if test="${result.info.orderCd eq '입금대기' and result.info.payType eq 'VBANK'}">
									<h4 class="cont_tit02">입금정보</h4>
									<div class="table-type01">
										<table class="btn_wrap_left">
											<colgroup>
												<col style="width:20%">
												<col style="width:80%">
											</colgroup>
											<tbody >
												<tr>
													<th>은행명</th>
													<td>
														<p>${result.info.vbankNm}</p>
													</td>
												</tr>
												<tr>
													<th>계좌주명</th>
													<td>
														<p>(주)브이씨</p>
													</td>
												</tr>
												<tr>
													<th>계좌번호</th>
													<td>
														<p>${result.info.vbankNum}</p>
													</td>
												</tr>
												<tr>
													<th>마감일</th>
													<td>
														<p>${result.info.vbankFnhDtm} 까지</p>
													</td>
												</tr>
											</tbody>
										</table>
									</div>
								</c:if>
								
								<h4 class="cont_tit02">배송지 정보</h4>
								<div class="table-type01">
									<table class="btn_wrap_left">
										<colgroup>
											<col style="width:20%">
											<col style="width:80%">
										</colgroup>
										<tbody >
											<tr>
												<th>이름</th>
												<td>
													<p>${result.info.receiverNm}</p>
												</td>
											</tr>
											<tr>
												<th>핸드폰</th>
												<td>
													<p>${result.info.receiverHp}</p>
												</td>
											</tr>
											<tr>
												<th>주소</th>
												<td>
													<p>[${result.info.receiverZipCd}] ${result.info.receiverAdrSbc1} ${result.info.receiverAdrSbc2}</p>
												</td>
											</tr>
											<tr>
												<th>배송메모</th>
												<td>
													<p><tag:html value="${result.info.orderMemo}" attr="BR" /></p>
												</td>
											</tr>
											
										</tbody>
									</table>
								</div>
							</div>
						</div>
					</c:otherwise>
					</c:choose>
					
					<%@ include file="/WEB-INF/jsp/user/normal/member/order/inc_order.jsp" %>
					
				</div>
			</div>				
		</div>						
	</div>		
</div>

<script src="/resources/user/js/order.js"></script>
