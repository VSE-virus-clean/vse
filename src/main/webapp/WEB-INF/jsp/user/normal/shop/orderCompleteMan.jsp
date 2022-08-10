<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%-- 
    JSP Name : completeMan.jsp
    Description : 주문결제 > Step3. 주문완료
--%>
<link rel="stylesheet" href="/resources/user/css/order.css?v=${cacheParam}">
<div id="container">
	<div class="location_bar">
		<ul class="clearfix inner pcTab">
			<li><a href="/index.vc">Home</a></li>
		</ul>
		<div class="mo inner">
			<a href=""></a>
		</div>	
	</div>
	<div class="order order_fin_pg">
		<div class="inner">		
			<h3 class="p_tit">주문·결제</h3>
			<div class="step">
				<ul>
					<li><p><span class="num"><em>01</em></span> <span>장바구니</span></p></li>
					<li><p><span class="num"><em>02</em></span> <span>주문서작성/결제</span></p></li>
					<li class="active"><p><span class="num"><em>03</em></span> <span>주문완료</span></p></li>
				</ul>
			</div>
			<div class="order_cont">
				<h3>주문완료</h3>
				<div class="line_box">
					<div class="line_box_inner">
						<img src="/resources/user/images/menber/joinok.png" alt="">
						<h3>고객님의 주문이 완료 되었습니다.</h3>
						<p>주문내역 확인은 <b>마이페이지 > 주문내역</b> 에서 확인 가능합니다.</p>
					</div>						
				</div>
				<h4 class="cont_tit03">구매자 정보</h4>
				<div class="">
					<div class="table-type01">
						<table>
							<colgroup>
								<col width="20%">
								<col width="80%">
							</colgroup>
							<tbody>
								<tr>
									<th>주문번호</th>
									<td><p>${result.info.orderNo}</p></td>
								</tr>
								<tr>
									<th>주문일자</th>
									<td><p>${result.info.rgstDtm}</p></td>
								</tr>
								<tr>
									<th>주문자명</th>
									<td><p>${result.info.buyerNm}</p></td>
								</tr>
								
								<c:if test="${result.info.payType eq 'VBANK'}">
									<tr>
										<th>가상계좌 은행명</th>
										<td><p>${result.info.vbankNm}</p></td>
									</tr>
									<tr>
										<th>가상계좌 계좌번호</th>
										<td><p>${result.info.vbankNum}</p></td>
									</tr>
									<tr>
										<th>가상계좌 입금만료일</th>
										<td><p>${result.info.vbankFnhDtm} 까지</p></td>
									</tr>
								</c:if>
							</tbody>
						</table>
					</div>
				</div>
				<div class="btn_wrap">
					<c:choose>
						<c:when test="${empty sessionScope.sessionVO}">
							<a href="/member/login.vc" class="btn btn_pp" style="width: 330px;"><span>완료</span></a>
						</c:when>
						<c:otherwise>
							<a href="/member.vc" class="btn btn_pp" style="width: 330px;"><span>완료</span></a>
						</c:otherwise>
					</c:choose>
				</div>

			</div>
		</div>						
	</div>		
</div>