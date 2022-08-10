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
<link rel="stylesheet" href="/resources/user/css/community.css?v=${cacheParam}">
<div id="container">
	<div class="location_bar">
		<ul class="clearfix inner pcTab">
			<li><a href="/index.vc">Home</a></li>
			<li><a href="#">Community</a></li>
		</ul>
		<div class="mo inner">
			<a href="" ></a>
		</div>			
	</div>
	<div class="community">
		<div class="inner">
			<h3 class="p_tit01">Community</h3>
		</div>
		<div class="search_section community_banner">
			<div class="inner">
				<!-- <p>바이러스 클린 랩 커뮤니티 입니다.</p> -->
				<form name="searchForm" method="get" class="search_bar" onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
					<input type="hidden" name="searchType" value="0" />
					<input type="search" id="searchKey" name="searchKey" title="검색어" maxlength="100" minlength="2" placeholder="검색어를 입력해주세요" />
					<button type="submit"><img src="/resources/user/ui_common/images/search_icon.png" alt=""></button>
				</form>
			</div>	
		</div>
		<!-- <div class="tab_ui_mini">
			<ul class="tab">
				<li class="${pageMenuId eq 'KSDA' ? 'active' : '' }"><a href="/event/list.vc">전체</a></li>
				<li class="${pageMenuId eq 'KSDB' ? 'active' : '' }"><a href="/event/ing/list.vc">커뮤니티 공지사항</a></li>
				<li class="${pageMenuId eq 'KSDC' ? 'active' : '' }"><a href="/event/end/list.vc">커뮤니티 게시글</a></li>
			</ul>
		</div> -->
		<div class="inner inner_cont">
			<c:if test="${not empty sessionScope.sessionVO}">
				<%-- 로그인시만 노출 --%>
				<div class="btn_right_wrap">
					<a href="${contextPath}/${requestUri}/register.vc" class="btn02 btn_pp" style="width:200px;"><span>등록</span></a>
				</div>
			</c:if>
			<div class="gallery_list">
				<ul class="clearfix">
					<c:choose>
	                <c:when test="${empty result.list and empty result.notiList}">
	                    <li class="nodata">등록된 게시물이 없습니다.</li>
	                </c:when>
	                <c:otherwise>
	                	<c:forEach items="${result.notiList}" var="data" varStatus="i">
                  			<li class="box noti">
								<a href="${contextPath}/${requestUri}/view.vc?blcSn=${data.blcSn}&rowNum=${data.rowNum}${function:searchQuery(result.searchInfo)}">
									<div class="img_box">
										${function:printImageFileByList(data.fileSn, data.lgrpCd, data.filNm, data.filNm)}
									</div>
									<div class="txt_box">
										<p class="event_tit"><tag:html value="${data.blcTitl}" attr="NQ" /></p>
										<p class="date">관리자 &nbsp;|&nbsp; ${data.userViewDtm}</p>
									</div>												
								</a>
							</li>	
                    	</c:forEach>
                    
	                    <c:forEach items="${result.list}" var="data" varStatus="i">
                   			<li class="box">
								<a href="${contextPath}/${requestUri}/view.vc?blcSn=${data.blcSn}&rowNum=${data.rowNum}${function:searchQuery(result.searchInfo)}">
									<div class="img_box">
										${function:printImageFileByList(data.fileSn, data.lgrpCd, data.filNm, data.filNm)}
									</div>
									<div class="txt_box">
										<p class="event_tit"><tag:html value="${data.blcTitl}" attr="NQ" /></p>
										<p class="date">${data.mbrNick} &nbsp;|&nbsp; ${data.userViewDtm}</p>
									</div>												
								</a>
							</li>	
	                    </c:forEach>
	                </c:otherwise>
	            	</c:choose>
				</ul>
			</div>	
			
			<div class="paging">
				<tag:paging2 url="${contextPath}/${requestUri}/list.vc?${function:searchQueryPaging(result.searchInfo)}" totalRow="${result.searchInfo.totalRow}" page="${result.searchInfo.page}" rowLimit="${result.searchInfo.rowLimit}" />
			</div>
		</div>
	</div>
</div>


<script type="text/javascript">
$(function () {
    $('form[name=searchForm]').ready(function(){
        $('input[name=searchKey]').val('${result.searchInfo.searchKey}');  
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