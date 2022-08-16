<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
    Description : 고객지원 > 매창찾기
--%>
<div id="contentWrap" class="">
	<h2 id="contentAnchor">콘텐츠 영역</h2>
	<div class="inner map_wrap">
		<h3 class="tit">LOCATION</h3>
		<p class="sub_tit text_center mb50">SHOWROOMS / DEALERS</p>

		<div class="table_type_02 mt50">
			<table>
				<colgroup>
					<col class="pc" style="width:10%;max-width:80px;">
					<col style="width:30%;min-width:150px">
					<col class="pc" style="width:10%;max-width:80px;">
					<col style="width:auto">
				</colgroup>
				<thead>
					<tr>
						<th class="">NO</th>
						<th class="">Store name</th>
						<th class="">Contact</th>
						<th class="">Address</th>
					</tr>
				</thead>
				<tbody>
					<c:choose>
						<c:when test="${empty result.list}">
							<tr><td colspan="4"  class="nodata">No Data.</td></tr>
						</c:when>
						<c:otherwise>
	                    	<c:forEach items="${result.list}" var="data" varStatus="i">
								<tr class="text_center">
									<td>${function:rowNumber(result.searchInfo, i.count)}</td>
									<td>
										<a href="https://www.google.co.kr/maps/place/${data.blcSbc4}" target="_blank">
											<strong><tag:html value="${data.blcTitl}" attr="NQ" /></strong>
										</a>
									</td>
									<td>${data.blcSbc3}</td>
									<td>${data.blcSbc4}</td>
								</tr>
	                    	</c:forEach>
	                    </c:otherwise>
	                </c:choose>
				</tbody>
			</table>
		</div>
		
		<div class="paging mt40">
			<tag:paging url="${contextPath}/${requestUri}/list.vse?${function:searchQueryPaging(result.searchInfo)}" totalRow="${result.searchInfo.totalRow}" page="${result.searchInfo.page}" rowLimit="${result.searchInfo.rowLimit}" />
		</div>
	</div>
</div>

<!-- <script charset="UTF-8" class="daum_roughmap_loader_script" src="https://ssl.daumcdn.net/dmaps/map_js_init/roughmapLoader.js"></script> -->
<script>
// 	new daum.roughmap.Lander({
// 		"timestamp" : "1628842576774",
// 		"key" : "26yqw",
		//"mapWidth" : "585",
		//"mapHeight" : "546",
		//"mapHeight" : "<?php if(is_mobile()) { ?>250<?php } else { ?>546<?php } ?>"
		
// 	}).render();
	
</script>
<style>
.section_address {display:none;}
</style>
			