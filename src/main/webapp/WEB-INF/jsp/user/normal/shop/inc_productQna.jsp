<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
    Description : 상품문의
--%>
<div class="inner">
	<div class="product_info_contents">
		<div class="nav_order">
			<select name="mgrpCd" title="문의종류">
	            <option value="">전체</option>
	            <option value="제품">제품</option>
	            <option value="배송">배송</option>
	            <option value="교환/반품">교환/반품</option>
	            <option value="기타">기타</option>
            </select>
		</div>
		<div class="table-type01 qna_table pcTab">
			<table>
				<colgroup>
					<col style="width:13%;">
					<col style="width:13%">
					<col style="width:40%;">
					<col style="width:12%;">
					<col style="width:12%;">
				</colgroup>
				<thead>
					<tr>
						<th>문의유형</th>
						<th></th>
						<th>문의/답변</th>
						<th>작성자</th>
						<th>작성일</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
		                <c:when test="${empty resultQna.list}">
		                    <tr>
		                        <td colspan="5" class="nodata">등록된 문의내역이 없습니다.</td>
		                    </tr>
		                </c:when>
		                <c:otherwise>
		                    <c:forEach items="${resultQna.list}" var="data" varStatus="i">
		                        <tr>
		                            <td><p>${data.mgrpCd}</p></td>	
									<c:choose>
										<c:when test="${data.asYn eq 'N'}"><td class="qna_cont_wait"><p>답변대기</p></td></c:when>
										<c:when test="${data.asYn eq 'Y'}"><td class="qna_cont_fin"><p>답변완료</p></td></c:when>
									</c:choose>
									<td class="qna_cont_txt">
										<p>
											<a href="javascript:;" data-sn="${data.blcSn}" onclick="modalUtil.open('qna-view', this);">
												<tag:html value="${data.blcTitl}" attr="NQ" />
											</a>
										</p>
									</td>
									<td><p>${data.mbrNick}</p></td>
									<td><p>${data.userViewDtm}</p></td>
		                        </tr>
		                    </c:forEach>
		                </c:otherwise>
		            </c:choose>
				</tbody>
			</table>
		</div>
		
		<%-- 모바일 --%>
		<div class="table-type01 qna_table mo">
			<table>
				<colgroup>
					<col style="width:25%;">
					<col style="width:45%;">
					<col style="width:30%;">
				</colgroup>
				<thead>
					<tr>
						<th>문의유형</th>
						<th>문의/답변</th>
						<th>작성자/작성일</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
		                <c:when test="${empty resultQna.list}">
		                    <tr>
		                        <td colspan="3" class="nodata">등록된 문의내역이 없습니다.</td>
		                    </tr>
		                </c:when>
		                <c:otherwise>
		                    <c:forEach items="${resultQna.list}" var="data" varStatus="i">
		                        <tr>
		                            <td><p>${data.mgrpCd}</p></td>	
		                            <td class="qna_cont_txt">
										<c:choose>
											<c:when test="${data.asYn eq 'N'}"><p class="qna_cont_wait">답변대기</p></c:when>
											<c:when test="${data.asYn eq 'Y'}"><p class="qna_cont_fin">답변완료</p></c:when>
										</c:choose>
										<p>
											<a href="javascript:;" data-sn="${data.blcSn}" onclick="modalUtil.open('qna-view', this);">
												<tag:html value="${data.blcTitl}" attr="NQ" />
											</a>
										</p>
									</td>
									<td class="id"><p>${data.mbrNick}</p><p>${data.userViewDtm}</p></td>
		                        </tr>
		                    </c:forEach>
		                </c:otherwise>
		            </c:choose>
				</tbody>
			</table>
		</div>
		
		<c:if test="${resultQna.searchInfo.totalRow gt 10}">
			<a href="#" class="more_btn btn btn_line"> <span>문의 더 보기</span></a>
		</c:if>
		
		<c:if test="${not empty sessionScope.sessionVO}">
			<a href="#" class="btn btn_pp nav_write" onclick="modalUtil.open('qna-register');" style="width: 330px; margin:25px auto;">
				<span>문의하기</span>
			</a>
		</c:if>
	</div>
</div>
