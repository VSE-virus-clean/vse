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
	
	<div class="order order_main_page qna_page">
		<div class="inner">
		
			<%@ include file="/WEB-INF/jsp/user/normal/member/inc_mypageTop.jsp" %>
			
			<div class="qna_cont ">
				<h4 class="cont_tit02">문의내역</h4>
				<form name="searchForm" method="get" onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
					<div class="qna_type">
						<select name="mgrpCd" title="카테고리">
	                        <option value="" >선택해주세요.</option>
	                    </select>
						<a href="javascript:;" class="btn btn_pp qna_pop_btn" onclick="modalUtil.open('qna-register');" style="width:330px;" >
							<span>문의하기</span>
						</a>
					</div>
				</form>
				<div class="qna_cart">
					<div class="table-type01">
						<table>
							
							<thead>
								<tr>
									<th style="width:20%">문의유형</th>
									<th style="width:20%">작성일</th>
									<th style="width:40%">제목</th>
									<th style="width:20%">상태</th>
								</tr>
							</thead>
							<tbody>
								<c:choose>
					                <c:when test="${empty result.list}">
					                    <tr>
					                        <td colspan="4" class="nodata">등록된 문의내역이 없습니다.</td>
					                    </tr>
					                </c:when>
					                <c:otherwise>
					                    <c:forEach items="${result.list}" var="data" varStatus="i">
					                        <tr> 
												<td class="pcTab"><p>${data.mgrpCd}</p></td>	
												<td class="pcTab"><p>${data.userViewDtm}</p></td>
												<td class="pcTab">
													<a href="javascript:;" data-sn="${data.blcSn}" onclick="modalUtil.open('qna-view', this);">
														<p><tag:html value="${data.blcTitl}" attr="NQ" /></p>
													</a>
												</td>
												<td class="pcTab">
													<c:choose>
														<c:when test="${data.asYn eq 'N'}"><p class="bold">답변대기</p></c:when>
														<c:when test="${data.asYn eq 'Y'}"><p class="gray">답변완료</p></c:when>
													</c:choose>
												</td>
												
												<td class="mo qna_tit">
													<p>${data.mgrpCd}</p>
													<c:choose>
														<c:when test="${data.asYn eq 'N'}"><p class="bold">답변대기</p></c:when>
														<c:when test="${data.asYn eq 'Y'}"><p class="gray">답변완료</p></c:when>
													</c:choose>
												</td>
												<td class="mo qna_cont">
													<a href="javascript:;" data-sn="${data.blcSn}" onclick="modalUtil.open('qna-view', this);">
														<p><tag:html value="${data.blcTitl}" attr="NQ" /></p>
													</a>
												</td>
												<td class="mo qna_date"><p>${data.userViewDtm}</p></td>
					                        </tr>
					                    </c:forEach>
					                </c:otherwise>
					            </c:choose> 
							</tbody>
						</table>
					</div>
				</div>
				<div class="paging paging_wrap">
			    	<tag:paging url="${contextPath}/${requestUri}/list.vc?${function:searchQueryPaging(result.searchInfo)}" totalRow="${result.searchInfo.totalRow}" page="${result.searchInfo.page}" rowLimit="${result.searchInfo.rowLimit}" />
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
    	comCodeUtil.getCodeNPrint('QNA', 'mgrpCd', 'select');
        $('form[name=searchForm] select[name=mgrpCd] option[value="${result.searchInfo.mgrpCd}"]').prop('selected', true);
    });
	
	$('form[name=searchForm] select[name=mgrpCd]').on('change', function(event){
		$('form[name=searchForm]').submit();   
	});
});

var formSubmitObj = {
    submit : function(form){
        return true;
    }
    
};

</script>
