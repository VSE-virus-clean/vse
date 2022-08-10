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
	<div class="order order_main_page">
		<div class="inner">
			
			<%@ include file="/WEB-INF/jsp/user/normal/member/inc_mypageTop.jsp" %>
		</div>
		<div class="inner inner_full">
			<div class="order_cont">
				<div class="history_cart">
				<h4 class="cont_tit02">최근 주문내역</h4>
				<div class="order_cart">
					<div class="table-type01">
						<table>
							<colgroup>
								<col style="width:20%">
								<col style="width:15%">
								<col style="width:40%">
								<col style="width:25%">
							</colgroup>
							<thead>
								<tr>
									<th>주문번호</th>
									<th>주문일시</th>
									<th>주문명</th>
									<th>상태</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
					                <c:when test="${empty resultOrder.list}">
					                    <tr>
					                        <td colspan="4" class="nodata">주문 내역이 없습니다.</td>
					                    </tr>
					                </c:when>
					                <c:otherwise>
					                    <c:forEach items="${resultOrder.list}" var="data" varStatus="i" begin="0" end="2">
					                        <tr class="history_tbl_tr01">
					                        	<td>
						                            <div class="pro_numb"><p>${data.orderNo}</p></div>	
												</td>
												<td class="pro_detail"><div class="date"><p>${data.userViewDtm}</p></div></td>
												<td colspan="2" class="order_prd_img">
													<img src="/resources/user/images/product/product_img.jpg" alt="">
													<p>${data.goodNm}</p>
												</td>
												<td class="pro_state"><p class="bold">${data.orderCd}</p></td>
					                        </tr>
					                    </c:forEach>
					                </c:otherwise>
					            </c:choose>
							</tbody>
						</table>
					</div>
				</div>
				<a href="/member/order/active/list.vc" class="more_btn btn btn_line"> <span>더 보기</span></a>
				</div>
			</div>

			<div class="buy_cont">
				<h4 class="cont_tit02">보유중인 제품</h4>
				<div class="buy_cart">
					<div class="table-type01">
						<table>
							<colgroup>
								<col style="width:33%">
								<col style="width:27%">
								<col style="width:15%">
								<col style="width:23%">
							</colgroup>
							<thead>
								<tr>
									<th>모델명</th>
									<th>시리얼번호</th>
									<th>정품등록일</th>
									<th>보증기간</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
					                <c:when test="${empty resultOrigin.list}">
					                    <tr>
					                        <td colspan="4" class="nodata">등록된 보유중인 제품이 없습니다.</td>
					                    </tr>
					                </c:when>
					                <c:otherwise>
					                    <c:forEach items="${resultOrigin.list}" var="data" varStatus="i">
					                        <tr>
					                            <td><p class="mo">모델명</p><p>${data.prdTitl}</p></td>	
												<td><p class="mo">시리얼번호</p><p>${data.serialNo}</p></td>
												<td><p class="mo">정품등록일</p><p>${data.userViewDtm}</p></td>
												<td><p class="mo">보증기간</p><p>${data.wrntStrDtm}<em>&nbsp;~&nbsp;</em>${data.wrntFnhDtm}</p></td>
					                        </tr>
					                    </c:forEach>
					                </c:otherwise>
					            </c:choose>
							</tbody>
						</table>
					</div>
				</div>
				<a href="/member/product/list.vc" class="more_btn btn btn_line"> <span>더 보기</span></a>
			</div>
			
			<div class="qna_cont ">
				<h4 class="cont_tit02">문의내역</h4>
				<div class="qna_cart">
					<div class="table-type01">
						<table>
							<colgroup>
								<col style="width:20%">
								<col style="width:20%">
								<col style="width:40%">
								<col style="width:20%">
							</colgroup>
							<thead>
								<tr>
									<th>문의유형</th>
									<th>상태</th>
									<th>문의/답변</th>
									<th>작성일</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
					                <c:when test="${empty resultQna.list}">
					                    <tr>
					                        <td colspan="4" class="nodata">등록된 문의내역이 없습니다.</td>
					                    </tr>
					                </c:when>
					                <c:otherwise>
					                    <c:forEach items="${resultQna.list}" var="data" varStatus="i">
					                        <tr>
					                            <td class="q_type pcTab"><p>${data.mgrpCd}</p></td>	
												<td class="q_state pcTab">
													<c:choose>
														<c:when test="${data.asYn eq 'N'}"><p class="bold">답변대기</p></c:when>
														<c:when test="${data.asYn eq 'Y'}"><p class="gray">답변완료</p></c:when>
													</c:choose>
												</td>
												<td class="q_cont pcTab">
													<a href="javascript:;" data-sn="${data.blcSn}" onclick="modalUtil.open('qna-view', this);">
														<p><tag:html value="${data.blcTitl}" attr="NQ" /></p>
													</a>
												</td>
												<td class="q_date pcTab"><p>${data.userViewDtm}</p></td>
												<td class="q_tit mo">
													<div class="q_left">
														<p>${data.mgrpCd}</p>
														<div class="bold q_state">
															<c:choose>
																<c:when test="${data.asYn eq 'N'}"><p class="bold">답변대기</p></c:when>
																<c:when test="${data.asYn eq 'Y'}"><p class="gray">답변완료</p></c:when>
															</c:choose>
														</div>
													</div>
													<div class="q_date"><p>${data.userViewDtm}</p></div>
												</td>
												<td class="q_cont mo">
													<a href="javascript:;" data-sn="${data.blcSn}" onclick="modalUtil.open('qna-view', this);">
														<p><tag:html value="${data.blcTitl}" attr="NQ" /></p>
													</a>
												</td>
					                        </tr>
					                    </c:forEach>
					                </c:otherwise>
					            </c:choose>
							</tbody>
						</table>
					</div>
				</div>
				<a href="/member/qna/list.vc" class="more_btn btn btn_line"> <span>더 보기</span></a>
			</div>

		</div>						
	</div>		
</div>

<%@ include file="/WEB-INF/jsp/user/normal/common/include/inc_modal.jsp" %>

<script src="/resources/user/js/order.js"></script>
<script>
$(function(){
	comCodeUtil.getCodeNPrint('QNA', 'mgrpCd', 'select');
});
</script>

</html>