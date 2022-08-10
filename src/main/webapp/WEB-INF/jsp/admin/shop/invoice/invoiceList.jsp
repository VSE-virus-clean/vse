<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
    JSP Name : noticeList.jsp
    Description : 게시판관리 > INFORMATION > NOTICE 목록
--%>
<style>
a.excelDown { margin-right: 15px; color: #fff; background-color: #8c8c8c; padding: 6px 20px; }
a.excelDown span:before { content: url(/resources/admin/images/excel_img.gif); width: 16px; height: 16px; display: inline-block; transform: translate(-8px, 2px); }
</style>
<div class="sec_top">
	<h3 class="sec_tit">송장등록</h3>
	<ul class="top_tab">
		<li><a href="#">주문관리</a></li>
		<li><a href="#">송장등록</a></li>
	</ul>
</div>
<div class="sec_cont">
	<h4 class="cont_tit">송장등록</h4>
	<div class="r_search_box">
		<form name="searchForm" method="post" action="${contextPath}/${requestUri}/register.vc" onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
			<table class="r_search_table">
				<colgroup>
					<col style="width:200px">
					<col style="">
					<col style="width:200px">
					<col style="">
				</colgroup>
				<tbody>
					<tr>
						<th>엑셀 파일업로드</th>
						<td colspan="3">
							<div class="filebox">
								<input name="lgrpCd" type="hidden" value="INVOICE"/>
								<input type="hidden" name="attachFileSn1" id="attachFileSn1" value="0">
								<input type="hidden" name="attachFileCd1" id="attachFileCd1" value="ATTCH">
								<input type="hidden" name="attachFileUseYn1" id="attachFileUseYn1" value="Y">
								<input type="text" class="upload-name bg_g" value="" disabled="disabled" style="width:350px;" title="송장파일">
								<label class="file" for="attachFile1">찾아보기</label>
								<input type="file" id="attachFile1" name="attachFile1" class="upload-hidden" accept=".xls,.xlsx" title="엑셀파일">
								
								<a href="/resources/invoice_sample_form.xls" class="btnstyle blue excelDown down" style="margin-left:30px;"><span>양식 다운로드</span></a>
							</div>
						</td>
					</tr>
				</tbody>
			</table>
			
			<div class="btn_right_gorup">
				<button type="submit" class="btn btn_red">등록</button>
			</div>
		</form>
	</div>
	
	<div class="content-body">
		<div class="table_tit">
			<div class="table_left_title">
				<span>총<strong class="totCnt" style="font-weight: 700;"> <fmt:formatNumber value="${result.searchInfo.totalRow}" type="number"/></strong>건 조회</span>
			</div>
		</div>
    
    	<form name="subForm" method="post" action="${contextPath}/${requestUri}/delete.vc" onsubmit="var rtn = formSubmitObj.delSubmit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
		    <table class="cont_table" style="table-layout:fixed">
		    	<colgroup>
					<col style="width:100px">
					<col style="width:250px">  
					<col style="">
					<col style="">
					<col style="">
					<col style="">
				</colgroup>
		        <thead>
		            <tr>
		                <th>번호</th>
		                <th>등록일</th>
						<th>전체건수</th>
						<th>성공건수</th>
						<th>실패건수</th>
						<th>관리</th>                           
		            </tr>
		        </thead>
		        <tbody>
		            <c:choose>
		                <c:when test="${empty result.list}">
		                    <tr>
		                        <td colspan="6" class="nodata">등록된 정보가 없습니다.</td>
		                    </tr>
		                </c:when>
		                <c:otherwise>
		                    <c:forEach items="${result.list}" var="data" varStatus="i">
		                        <tr>
		                            <td>
		                            	${function:rowNumber(result.searchInfo, i.count)}
		                            </td>
		                            <td>${data.rgstDtm}</td>
		                            
		                            <c:choose>
		                            <c:when test="${empty data.blcSbc1}">
		                            	<td colspan="3">등록 에러</td>
		                            </c:when>
		                            <c:otherwise>
			                            <c:forTokens var="cnt" items="${data.blcSbc1}" delims="|">
											<td>${cnt}건</td>
										</c:forTokens>
									</c:otherwise>
									</c:choose>
									
		                            <td class="table_btn">
		                            	<a class="btn btn_red" href="${contextPath}/${requestUri}/view.vc?blcSn=${data.blcSn}${function:searchQuery(result.searchInfo)}">보기</a>
									</td>
		                        </tr>
		                    </c:forEach>
		                </c:otherwise>
		            </c:choose>
		        </tbody>
		    </table>
	    </form>
		    
	    <div class="paging_wrap">
	    	<tag:paging url="${contextPath}/${requestUri}/list.vc?${function:searchQueryPaging(result.searchInfo)}" totalRow="${result.searchInfo.totalRow}" page="${result.searchInfo.page}" rowLimit="${result.searchInfo.rowLimit}" />
	    </div>
    </div>
</div>

<script type="text/javascript">
var formSubmitObj = {
    submit : function(form){
    	if(!submitUtil.isNull(form.attachFile1)){
            if(!submitUtil.isAttachFile(form.attachFile1, 'XLS')){
                return false;
            }
        }
        
    	if(confirm('송장정보를 배송처리 하시겠습니까?\n\n작업에 시간이 소요되니 완료될때까지 기다려주세요.')){
			ajaxUtil.formSubmit($(form), formSubmitObj.result);
    	}
        
        return false;
    },
    result : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        if(json.bindingStatus == undefined && json.result != undefined && json.result.status){
        	alert('배송처리가 완료되었습니다.');
        	location.reload(true);
        }else{
            ajaxUtil.error(json);
        }
    }
};
</script>