<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
    JSP Name : mainMan.jsp
    Description : 관리자 메인 / 대쉬보드 페이지
--%>

<link type="text/css" rel="stylesheet" href="/resources/admin/css/dashboard.css?v=${cacheParam}"  />
<style>
.dsbd_page .cont_table img { width:auto; }
</style>
<div class="sec_top">
	<h2 class="sec_tit">대시보드</h2>
</div>
<div class="sec_cont dsbd_page">
    <div class="content-body">                   
	    <h4 class="cont_tit">사용자 현황</h4>
		<table class="cont_table" style="TABLE-layout:fixed">
			<colgroup>
				<col width="">
				<col width="">
				<col width="">
			</colgroup>
			<thead>
				<tr style="height:58px;">
					<th>
						<div class="stat_icon_wrap">
							<div class="tbl_icon">
								<img src="/resources/admin/images/common/icon_profile.png" alt="">
							</div>
							<p>총 가입자 수</p>
						</div>
					</th>
					<th>
						<div class="stat_icon_wrap">
							<div class="tbl_icon">
								<img src="/resources/admin/images/common/icon_join.png" alt="">
							</div>
							<p>오늘 가입자 수</p>
						</div>
					</th>
					<th>
						<div class="stat_icon_wrap">
							<div class="tbl_icon">
								<img src="/resources/admin/images/common/icon_visit.png" alt="">
							</div>
							<p>오늘 방문자 수</p>
						</div>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr style="height:58px;">
					<td>
						<p><span>${dashboard.info.user01}</span>명</p>
					</td>
					<td>
						<p><span>${dashboard.info.user02}</span>명</p>
					</td>
					<td>
						<p><a href="https://analytics.google.com/" target="_blank">Google Analytics</a></p>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<div class="content-body">
		<h4 class="cont_tit">일별 주문 현황</h4>
		<table class="cont_table" style="TABLE-layout:fixed">
			<colgroup>
				<col width="">
				<col width="">
				<col width="">
			</colgroup>
			<thead>
				<tr style="height:58px;">
					<th>
						<div class="stat_icon_wrap">
							<div class="tbl_icon">
								<img src="/resources/admin/images/common/icon_payment.png" alt="">
							</div>
							<p>결제완료 수</p>
						</div>
					</th>
					<th>
						<div class="stat_icon_wrap">
							<div class="tbl_icon">
								<img src="/resources/admin/images/common/icon_preparation.png" alt="">
							</div>
							<p>배송준비중 수</p>
						</div>
					</th>
					<th>
						<div class="stat_icon_wrap">
							<div class="tbl_icon">
								<img src="/resources/admin/images/common/icon_refund.png" alt="">
							</div>
							<p>환불 수</p>
						</div>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr style="height:58px;">
					<td>
						<p><span>${dashboard.info.day01}</span>건</p>
					</td>
					<td>
						<p><span>${dashboard.info.day02}</span>건</p>
					</td>
					<td>
						<p><span>${dashboard.info.day03}</span>건</p>
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	      
    <div class="content-body">            
		<h4 class="cont_tit">판매현황</h4>
		<table class="cont_table" style="TABLE-layout:fixed">
			<colgroup>
				<col width="20%">
				<col width="20%">
				<col width="20%">
				<col width="20%">   
				<col width="20%">                                     
			</colgroup>
			<thead>
				<tr style="height:58px;">
					<th>
						<div class="stat_icon_wrap">
							<div class="tbl_icon">
								<img src="/resources/admin/images/common/icon_waitingdeposit.png" alt="">
							</div>
							<p>입금대기</p>
						</div>
					</th>
					<th>
						<div class="stat_icon_wrap">
							<div class="tbl_icon">
								<img src="/resources/admin/images/common/icon_payment.png" alt="">
							</div>
							<p>결제완료</p>
						</div>
					</th>
					<th>
						<div class="stat_icon_wrap">
							<div class="tbl_icon">
								<img src="/resources/admin/images/common/icon_preparation.png" alt="">
							</div>
							<p>배송준비중</p>
						</div>
					</th>
					<th>
						<div class="stat_icon_wrap">
							<div class="tbl_icon">
								<img src="/resources/admin/images/common/icon_delivery.png" alt="">
							</div>
							<p>배송중</p>
						</div>
					</th>
					<th>
						<div class="stat_icon_wrap">
							<div class="tbl_icon">
								<img src="/resources/admin/images/common/icon_delivery_complete.png" alt="">
							</div>
							<p>배송완료</p>
						</div>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr style="height:58px;">
					<td>
						<p><span>${dashboard.info.sale01}</span>건</p>
					</td>
					<td>
						<p><span>${dashboard.info.sale02}</span>건</p>
					</td>
					<td>
						<p><span>${dashboard.info.sale03}</span>건</p>
					</td>
					<td>
						<p><span>${dashboard.info.sale04}</span>건</p>
					</td>
					<td>
						<p><span>${dashboard.info.sale05}</span>건</p>
					</td>
				</tr>
			</tbody>
			<thead>
				<tr style="height:58px;">
					<th>
						<div class="stat_icon_wrap">
							<div class="tbl_icon">
								<img src="/resources/admin/images/common/icon_refund.png" alt="">
							</div>
							<p>취소신청</p>
						</div>
					</th>
					<th>
						<div class="stat_icon_wrap">
							<div class="tbl_icon">
								<img src="/resources/admin/images/common/icon_cancell_complete.png" alt="">
							</div>
							<p>취소완료</p>
						</div>
					</th>
					<th>
						<div class="stat_icon_wrap">
							<div class="tbl_icon">
								<img src="/resources/admin/images/common/icon_change_application.png" alt="">
							</div>
							<p>교환신청</p>
						</div>
					</th>
					<th>
						<div class="stat_icon_wrap">
							<div class="tbl_icon">
								<img src="/resources/admin/images/common/icon_change_complete.png" alt="">
							</div>
							<p>교환완료</p>
						</div>
					</th>
					<th>
						<div class="stat_icon_wrap"><p></p></div>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr style="height:58px;">
					<td>
						<p><span>${dashboard.info.sale06}</span>건</p>
					</td>
					<td>
						<p><span>${dashboard.info.sale07}</span>건</p>
					</td>
					<td>
						<p><span>${dashboard.info.sale08}</span>건</p>
					</td>
					<td>
						<p><span>${dashboard.info.sale09}</span>건</p>
					</td>
					<td>
						<p></p>
					</td>
				</tr>
			</tbody>
			<thead>
				<tr style="height:58px;">
					<th>
						<div class="stat_icon_wrap">
							<div class="tbl_icon">
								<img src="/resources/admin/images/common/icon_return_application.png" alt="">
							</div>
							<p>반품신청</p>
						</div>
					</th>
					<th>
						<div class="stat_icon_wrap">
							<div class="tbl_icon">
								<img src="/resources/admin/images/common/icon_return_complete.png" alt="">
							</div>
							<p>반품완료</p>
						</div>
					</th>
					<th>
						<div class="stat_icon_wrap">
							<div class="tbl_icon">
								<img src="/resources/admin/images/common/icon_purchase.png" alt="">
							</div>
							<p>구매확정</p>
						</div>
					</th>
					<th>
						<div class="stat_icon_wrap"><p></p></div>
					</th>
					<th>
						<div class="stat_icon_wrap"><p></p></div>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr style="height:58px;">
					<td>
						<p><span>${dashboard.info.sale10}</span>건</p>
					</td>
					<td>
						<p><span>${dashboard.info.sale11}</span>건</p>
					</td>
					<td>
						<p><span>${dashboard.info.sale12}</span>건</p>
					</td>
					<td>
						<p></p>
					</td>
					<td>
						<p></p>
					</td>
				</tr>
			</tbody>
		</table>
		<div class="btn_right_gorup">
			<button type="button" class="btn btn_red" onclick="location.href='${contextPath}/shop/order/list.vc';">바로가기</button>
		</div>
	</div>
	
	
	<div class="content-body">
        <h4 class="cont_tit">상품문의</h4>
        <table class="cont_table cont_table02">
            <colgroup>
                <col width="200px">
                <col width="120px">
                <col width="">
                <col width="200px">                              
            </colgroup>
            <thead>
                <tr>
                    <th>문의유형</th>
                    <th>답변여부</th>
                    <th>제목</th>  
                    <th>등록일</th>                                         
                </tr>
            </thead>
            <tbody>
<!--             	<tr> -->
<!--                     <td colspan="2">신규 글 : <a href="#">10건</a></td>                                         -->
<!--                     <td colspan="2">미답변 글 : <a href="#">10건</a></td>                                         -->
<!--                 </tr> -->
                <c:choose>
	                <c:when test="${empty resultQna.list}">
	                    <tr>
	                        <td colspan="4" class="nodata">등록된 문의내역이 없습니다.</td>
	                    </tr>
	                </c:when>
	                <c:otherwise>
	                    <c:forEach items="${resultQna.list}" var="data" varStatus="i">
	                        <tr>
	                            <td><p>${data.mgrpCd}</p></td>	
								<td>
									<c:choose>
										<c:when test="${data.asYn eq 'N'}"><p class="bold">답변대기</p></c:when>
										<c:when test="${data.asYn eq 'Y'}"><p class="gray">답변완료</p></c:when>
									</c:choose>
								</td>
								<td>
									<a href="${contextPath}/product/qna/modify.vc?blcSn=${data.blcSn}">
										<p><tag:html value="${data.blcTitl}" attr="NQ" /></p>
									</a>
								</td>
								<td><p>${data.userViewDtm}</p></td>
	                        </tr>
	                    </c:forEach>
	                </c:otherwise>
	            </c:choose>
            </tbody>                                
        </table>
		<div class="btn_right_gorup">
			<button type="button" class="btn btn_red" onclick="location.href='${contextPath}/product/qna/list.vc';">바로가기</button>
		</div>
	</div>

    <!-- 공지사항 최근 5개 -->
	<div class="content-body">
        <h4 class="cont_tit">공지사항</h4>
        <table class="cont_table cont_table02">
            <colgroup>
                <col width="70%">
                <col width="10%">                                    
                <col width="20%">
            </colgroup>
            <thead>
                <tr>
                    <th>제목</th>
                    <th>조회수</th>                                        
                    <th>등록일</th>
                </tr>
            </thead>
            <tbody>
            	<c:choose>
	                <c:when test="${empty notice.list}">
	                    <tr>
	                        <td colspan="3" class="nodata">등록된 게시물이 없습니다.</td>
	                    </tr>
	                </c:when>
	                <c:otherwise>
	                    <c:forEach items="${notice.list}" var="data" varStatus="i">
	                        <tr>
	                            <td class="left">
	                            	<a href="${contextPath}/board/notice/modify.vc?blcSn=${data.blcSn}"><tag:html value="${data.blcTitl}" attr="NQ" /></a>
	                            </td>
	                            <td>${data.blcRct}</td>
	                            <td>${data.userViewDtm}</td>
	                        </tr>
	                    </c:forEach>
	                </c:otherwise>
	            </c:choose>
            </tbody>                                
        </table>
		<div class="btn_right_gorup">
			<button type="button" class="btn btn_red" onclick="location.href='${contextPath}/board/notice/list.vc';">바로가기</button>
		</div>
	</div>
</div>                       
