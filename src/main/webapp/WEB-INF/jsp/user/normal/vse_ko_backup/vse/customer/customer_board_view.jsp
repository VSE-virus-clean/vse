<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
    Description : 고객지원 > 이벤트뉴스 > 상세
--%>
<div id="contentWrap" class="">
	<h2 id="contentAnchor">콘텐츠 영역</h2>
	<div class="inner board_view">
		<h3 class="tit"><tag:html value="${result.info.blcTitl}" attr="NQ" /></h3>
		<div class="table_type_02 mt60">
			<table>
				<colgroup>
					<col style="width:20%;max-width:200px;min-width: 100px;" />
					<col style="auto"/>
				</colgroup>
				<tbody>
<!-- 					<tr> -->
<!-- 						<th>작성자</th> -->
<!-- 						<td>보이스캐디</td> -->
<!-- 					</tr> -->
					<tr>
						<th>작성일</th>
						<td>${result.info.userViewDtm}</td>
					</tr>
					<tr>
						<th>조회</th>
						<td>${result.info.blcRct}</td>
					</tr>
					<c:if test="${not empty result.file.list}">
					<tr>
						<th>첨부파일</th>
						<td>
							${function:printAttachFileList3("Y", "ATTCH", result.file.list)}
						</td>
					</tr>
					</c:if>
				</tbody>
			</table>
		</div>
		
		<div class="mt20 form_box pt20 pb20">
			<div class="board_con">
				<tag:html value="${result.info.blcSbc1}" attr="NQ" />
			</div>
		</div>

		<div class="btn_wrap mt60 clear">
<!-- 			<a href="javascript:;" class="btn_type_02 btn_round_bk f_l">이전글</a> -->
			<p class="inline_block f_r">
				<a href="${contextPath}/${requestUri}/list.vse?${function:searchQuery(result.searchInfo)}" class="btn_type_02 btn_round_bk">목록</a>	
			</p>
		</div>
    </div>


</div>
<script>
$(function(){
	$('#header .gnb > li').eq(4).addClass('active');
});
</script>