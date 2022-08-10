<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
    JSP Name : productList.jsp
    Description : 마이페이지 > 보유상품 목록
--%>
<link rel="stylesheet" href="/resources/user/css/mypage.css?v=${cacheParam}">

<div id="container">
	
	<div class="order order_main_page mp_prd_page">
		<div class="inner">
		
			<%@ include file="/WEB-INF/jsp/user/normal/member/inc_mypageTop.jsp" %>
			
			<div class="mp_prd_wrap">
				<div class="mp_prd_wrap01 active" >
					<h4 class="cont_tit02">보유중인 제품</h4>
					<div class="">
						<div class="table-type01 scroll_x">
							<table>
								<colgroup>
									<col style="width:30%">
									<col style="width:18%">
									<col style="width:18%">
									<col style="width:18%">
									<col style="width:16%">
								</colgroup>
								<thead>
									<tr>
										<th>모델명</th>
										<th>시리얼번호</th>
										<th>정품등록일</th>
										<th>보증기간</th>
										<th>설문조사</th>
									</tr>
								</thead>
								<tbody>
									<c:choose>
						                <c:when test="${empty result.list}">
						                    <tr>
						                        <td colspan="5" class="nodata">등록된 보유중인 제품이 없습니다.</td>
						                    </tr>
						                </c:when>
						                <c:otherwise>
						                    <c:forEach items="${result.list}" var="data" varStatus="i">
						                        <tr>
						                            <td><p>${data.prdTitl}</p></td>	
													<td><p>${data.serialNo}</p></td>
													<td><p>${data.userViewDtm}</p></td>
													<td><p>${data.wrntStrDtm}<em>&nbsp;~&nbsp;</em>${data.wrntFnhDtm}</p></td>
													<td><a href="https://docs.google.com/forms/d/e/1FAIpQLScrdab3lDQsnpcSuJhDXh9WV1AzilOKrK5fzLSzm7SOmMoTRA/viewform" class="btn_pp btn02" target="_blank"><span>설문조사참여</span></a></a></td>
						                        </tr>
						                    </c:forEach>
						                </c:otherwise>
						            </c:choose>
								</tbody>
							</table>
						</div>
					</div>
					<div class="btn_wrap">
						<a href="/member/product/register.vc" class="btn btn_pp" style="width:330px;">
							<span>등록하기</span>
						</a>
					</div>
				</div>
			</div>

		</div>						
	</div>		
</div>

<%@ include file="/WEB-INF/jsp/user/normal/common/include/inc_modal.jsp" %>

<script src="/resources/user/js/order.js"></script>
