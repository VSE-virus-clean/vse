<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
    JSP Name : shopList.jsp
    Description : shop 목록
--%>
<link rel="stylesheet" href="/resources/user/css/product.css?v=${cacheParam}">
<div id="container">
	<div class="location_bar">
		<ul class="clearfix inner pcTab">
			<li><a href="/index.vc">Home</a></li>
			
			<c:choose>
			<c:when test="${result.searchInfo.lgrpCd eq 'VCL'}">
				<li><a href="#">Z-mini</a></li>
			</c:when>
			<c:when test="${result.searchInfo.lgrpCd eq 'ACC'}">
				<li><a href="#">액세서리</a></li>
			</c:when>
			</c:choose>
		</ul>
		<div class="mo inner">
			<a href="" ></a>
		</div>			
	</div>
	<div class="productlist">
		<c:choose>
		<c:when test="${result.searchInfo.lgrpCd eq 'VCL'}">
			<h3 class="p_tit">Z-mini</h3>
		</c:when>
		<c:when test="${result.searchInfo.lgrpCd eq 'ACC'}">
			<h3 class="p_tit">액세서리</h3>
		</c:when>
		</c:choose>
		
		<!--<div class="banner"></div>-->
		<div class="inner">
			<form name="searchForm" method="get" onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
				<div class="pro_list_top">
					<h4 class="cont_tit02">주문상세내역
						<p>총 <strong><fmt:formatNumber value="${result.searchInfo.totalRow}" type="number"/></strong>개의 제품이 있습니다.</p>
					</h4>
					<select name="searchGubunType5">
						<option value="DATE">최신순</option>
						<option value="PRICE_LOW">낮은가격순</option>
						<option value="PRICE_HIGH">높은가격순</option>
						<option value="GRADE">별점순</option>
					</select>
				</div>
			</form>
			<div class="product_list">
				<ul class="clearfix">
					<c:choose>
					<c:when test="${empty result.list}">
<!-- 	                    <li class="no-data">등록된 상품이 없습니다.</li> -->
	                </c:when>
	                <c:otherwise>
	                	<c:forEach items="${result.list}" var="data" varStatus="i">
						<li class="${data.sellYn eq 'Y' ? '' : 'soldout'}">
							<a href="/${requestUri}/view.vc?prdSn=${data.prdSn}${function:searchQuery(result.searchInfo)}" class="box">
								${function:printImageFileByList(data.fileSn, "PRODUCT", data.filNm, data.filNm)}
								<div  class="pro_info">
									<p class="pro_name"><tag:html value="${data.prdTitl}" attr="NQ" /></p>	
									<p class="price"><fmt:formatNumber value="${data.salePrice}" type="number"/> 원</p>
								</div>
							</a> 
							<div class="pro_cont">
								<div class="add_btn_wrap">
									<a href="#" data-cd="PRODUCT" data-sn="${data.prdSn}" class="btn_like like ${data.myScrapCnt > 0 ? 'active' : ''}"></a>
									<a href="#" data-sn="${data.prdSn}" class="addcart btnAddCart"></a>
									<a href="#" data-sn="${data.prdSn}" class="buy_prd btnQuikOrder"></a>
								</div>
							</div>
						</li>
						</c:forEach>
	                </c:otherwise>
	                </c:choose>
				</ul>
			</div>
			<div class="paging">
				 <tag:paging2 url="/${requestUri}/list.vc?${function:searchQueryPaging(result.searchInfo)}" totalRow="${result.searchInfo.totalRow}" page="${result.searchInfo.page}" rowLimit="${result.searchInfo.rowLimit}" />
			</div>
		</div>
	</div>		
</div>

<script src="/resources/user/js/product.js"></script>
<script type="text/javascript">
$(function () {
    $('form[name=searchForm]').ready(function(){
	   	$('select[name=searchGubunType5] option[value="${result.searchInfo.searchGubunType5}"]').prop('selected', true);
    });
    
	$('select[name=searchGubunType5]').on('change', function(event){
		$('form[name=searchForm]').submit();   
	});
	
	$('.buy_prd').on('click',function(){
		if($(this).hasClass('active')){
			$(this).removeClass('active');
		}else{
			$(this).addClass('active');
		}
	}); //구매하기 버튼
});

var formSubmitObj = {
    submit : function(form){
        return true;
    }
    
};

</script>