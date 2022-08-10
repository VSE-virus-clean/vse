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
<div class="sec_top">
	<h3 class="sec_tit">쿠폰내역</h3>
	<ul class="top_tab">
		<li><a href="#">쿠폰관리</a></li>
		<li><a href="#">쿠폰목록</a></li>
	</ul>
</div>
<div class="sec_cont">
	<h4 class="cont_tit">조회조건</h4>
	<div class="r_search_box">
		<form name="searchForm" method="get" onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
			<table class="r_search_table">
				<colgroup>
					<col style="width:150px">
					<col style="">
					<col style="width:150px">
					<col style="">
				</colgroup>
				<tbody>
<!-- 					<tr> -->
<!-- 					   <th>등록일</th> -->
<!-- 					   <td colspan="3">										 -->
<!-- 							<input type="text" placeholder="2020-05-15" name="FROM_APLY_DT_V_INFO" class="date_input is-datepick" readonly="readonly" autocomplete="off"  title="기간"> <em> ~ </em> -->
<!-- 							<input type="text" name="TO_APLY_DT_V_INFO" placeholder="2020-05-15" class="date_input is-datepick" readonly="readonly" autocomplete="off" title="기간"> -->
<!-- 						</td> -->
<!-- 					</tr> -->
					<tr>
						<th>검색어</th>
						<td colspan="3">
					   		<input type="hidden" id="searchType" name="searchType" value="0">
							<input type="text" id="searchKey" name="searchKey" title="상세검색입력" style="width:450px;" maxlength="100" minlength="2"/>
						</td>
					</tr>
					<tr>
						<th>할인상태</th>
						<td>
							<label class="radio_box"  for="dis01">
								<input type="radio" id="dis01" name="dis">
								<span>전체</span>
							</label>
							<label class="radio_box" for="dis02">
								<input type="radio" id="dis02" name="dis">
								<span>할인(%)</span>
							</label>
							<label class="radio_box" for="dis03">
								<input type="radio" id="dis03" name="dis">
								<span>할인금액(원)</span>
							</label>
						</td>
						<th>할인상태</th>
						<td>
							<label class="radio_box" for="cp01">
								<input type="radio" id="cp01" name="cp">
								<span>전체</span>
							</label>
							<label class="radio_box" for="cp02">
								<input type="radio" id="cp02" name="cp">
								<span>지급</span>
							</label>
							<label class="radio_box" for="cp03">
								<input type="radio" id="cp03" name="cp">
								<span>다운</span>
							</label>
						</td>
					</tr>
				</tbody>
			</table>
			<div class="btn_right_gorup">
				<button type="submit" class="btn btn_red">조회</button>
				<button type="reset" class="btn btn_gray">초기화</button>
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
					<col style="">  
					<col style="width:250px">
					<col style="width:100px">
					<col style="width:120px">
					<col style="width:100px">
					<col style="width:90px">
					<col style="width:90px">
					<col style="width:190px">
					<col style="width:200px">
				</colgroup>
		        <thead>
		            <tr>
		                <th><input type="checkbox" class="checkbox-selectAll" id="checkbox-selectAll">번호</th>
		                <th>쿠폰명</th>
						<th>사용기간</th>
						<th>할인타입</th>
						<th>발급타입</th>
						<th>상태</th>                           
						<th>발급수</th>
						<th>사용수</th>
						<th>등록일</th>
		                <th>관리</th>
		            </tr>
		        </thead>
		        <tbody>
		            <c:choose>
		                <c:when test="${empty result.list}">
		                    <tr>
		                        <td colspan="10" class="nodata">등록된 정보가 없습니다.</td>
		                    </tr>
		                </c:when>
		                <c:otherwise>
		                	<tr>
		                        <td colspan="10" class="nodata">등록된 정보가 없습니다.</td>
		                    </tr>
		                    <%-- c:forEach items="${result.list}" var="data" varStatus="i">
		                        <tr>
		                            <td>
		                            	<input type="checkbox" id="sn_${data.orgSn}" name="delSeq" class="check2 checkbox-select" value="${data.orgSn}" title="선택"> ${function:rowNumber(result.searchInfo, i.count)}
		                            </td>
		                            <td>할인율(10%)쿠폰</td>
									<td>2020-04-26  ~  2021-04-26</td>
									<td>원</td>
									<td><span class="box">지급</span></td>
<!-- 									<td><span >만료</span></td> -->
									<td>발급중</td>
									<td>1</td>
									<td>2021-01-26</td>
		                            <td class="table_btn">
		                            	<a class="btn btn_red" href="${contextPath}/${requestUri}/modify.vc?orgSn=${data.orgSn}${function:searchQuery(result.searchInfo)}">수정</a>
										<a href="${contextPath}/${requestUri}/delete.vc" class="btn btn_gray btnRowDelete"  data-sn="${data.orgSn}">삭제</a>
									</td>
		                        </tr>
		                    </c:forEach --%>
		                </c:otherwise>
		            </c:choose>
		        </tbody>
		    </table>
    
		    <div class="btn_center_gorup clearfix">
				<div class="left">
					<button type="button" class="btn btn_gray btnInfoCancle" onclick="">선택취소</button>
					<button type="submit" class="btn btn_gray" onclick="">선택삭제</button>
				</div>
			</div>
	    </form>
		    
	    <div class="paging_wrap">
	    	<tag:paging url="${contextPath}/${requestUri}/list.vc?${function:searchQueryPaging(result.searchInfo)}" totalRow="${result.searchInfo.totalRow}" page="${result.searchInfo.page}" rowLimit="${result.searchInfo.rowLimit}" />
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
    },
    submitDel : function(object){
		var msg = '게시물을 삭제 하시겠습니까?'
	    if(confirm(msg)){
	        var data = { orgSn : $(object).data('sn') };
	        ajaxUtil.postDisableAsync($(object).attr('href'), data, formSubmitObj.delResult);    
	    }
	},
	delSubmit : function(form){
        if($('input.checkbox-select:checked').length > 0){
        	if (confirm('선택한 게시물을 삭제 하시겠습니까?')) {
            	ajaxUtil.postDisableAsync(form.action, $(form).serialize(), formSubmitObj.delResult);
        	}
        }else{
            alert('1개이상 선택해 주세요.');
        }
        
        return false;
    },
    delResult : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        if(json.result != undefined && json.result.status){
            location.reload(true);
        }else{
            ajaxUtil.error(json);
        }
    }
};
</script>