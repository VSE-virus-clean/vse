<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%-- 
    JSP Name : faqList.jsp
    Description : FAQ 게시판 목록
--%>
<link rel="stylesheet" href="/resources/user/css/community.css?v=${cacheParam}">

<div id="container">
	<div class="location_bar">
		<ul class="clearfix inner pcTab">
			<li><a href="/index.vc">Home</a></li>
			<li><a href="#">Service</a></li>
			<li><a href="#">FAQ</a></li>
		</ul>
		<div class="mo inner">
			<a href="" ></a>
		</div>			
	</div>
	<div class="community">
		<div class="inner">
			<h3 class="p_tit01">FAQ</h3>
		</div>
		<div class="search_section fnq_banner">
			<div class="inner">
				<form name="searchForm" method="get" class="search_bar" onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
					<input type="hidden" name="searchType" value="0" />
					<select name="mgrpCd" id="mgrpCd">
						<option value="">질문타입 (전체)</option>
						<option value="회원문의">회원문의</option>
	                    <option value="주문관련">주문관련</option>
	                    <option value="결제관련">결제관련</option>
	                    <option value="배송관련">배송관련</option>
	                    <option value="제품관련">제품관련</option>
	                    <option value="취소/교환/반품">취소/교환/반품</option>
	                    <option value="AS">AS</option>
					</select>
		                    
					<input type="search" id="searchKey" name="searchKey" title="검색어" maxlength="100" minlength="2" placeholder="검색어를 입력해주세요" />
					<button type="submit"><img src="/resources/user/ui_common/images/search_icon.png" alt=""></button>
				</form>					
			</div>	
		</div>
		<div class="inner fnqbg">
			
			<div class="list-type02">
				<div class="list_head">
					<p></p>
					<p>질문타입</p>
					<p>제목</p>
				</div>
				<ul>	
					<c:choose>
					<c:when test="${empty result.list}">
						<li class="nodata">검색 결과가 없습니다.</li>
					</c:when>
					<c:otherwise>
			            <c:forEach items="${result.list}" var="data" varStatus="i">
			            <li>
							<div class="question">
								<a href="javascript:;">
									<div class="tit_wrap">
										<span class="q_type">${data.mgrpCd}</span> <p>${data.blcTitl}</p>
									</div>
								</a>
							</div>
							<div class="answer">
								<div class="answer_wrap editor_wrap">
									<p><tag:html value="${data.blcSbc1}" attr="NQ" /></p>
								</div>
							</div>
						</li>
						</c:forEach>
					</c:otherwise>
					</c:choose>
				</ul>
			 </div>
						
			<div class="paging">
				 <tag:paging2 url="/${requestUri}/list.vc?mgrpCd=${result.searchInfo.mgrpCd}${function:searchQueryPaging(result.searchInfo)}" totalRow="${result.searchInfo.totalRow}" page="${result.searchInfo.page}" rowLimit="${result.searchInfo.rowLimit}" />
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
$(function () {
    $('form[name=searchForm]').ready(function(){
    	 $('select[name=mgrpCd] option[value="${result.searchInfo.mgrpCd}"]').prop('selected', true);
        $('input[name=searchKey]').val('${result.searchInfo.searchKey}');  
    });
    
    $('select[name=mgrpCd]').on('change', function(event){
		$('form[name=searchForm]').submit();   
	});
});

var formSubmitObj = {
    submit : function(form){
        if(!submitUtil.isNull(form.searchKey)){
            if(!submitUtil.isMinLength(form.searchKey)){
                return false;    
            }        
        }  
        
        return true;
    }
};
</script>