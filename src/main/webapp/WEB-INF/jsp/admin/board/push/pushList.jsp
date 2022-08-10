<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="sec_top">
	<h3 class="sec_tit">APP PUSH 목록</h3>
	<ul class="top_tab">
		<li><a href="#">배너&amp;팝업관리</a></li>
		<li><a href="#">APP PUSH관리</a></li>
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
					<tr>
					   <th>등록일</th>
					   <td colspan="3">										
							<input type="text" id="searchStartDate" name="searchStartDate" class="date_input is-datepick datepicker" readonly="readonly" autocomplete="off"  title="기간"> <em> ~ </em>
							<input type="text" id="searchEndDate" name="searchEndDate" class="date_input is-datepick datepicker" readonly="readonly" autocomplete="off" title="기간">
						</td>
					</tr>
					<tr>
						<th>검색어</th>
						<td colspan="3">
							<input id="searchKey" name="searchKey" type="text" title="상세검색입력" style="width:450px;" maxlength="100" minlength="2"/>
						</td>
					</tr>
					<tr>
						<th>발송타입</th>
						<td colspan="3">
		                    <label class="radio_box" for="mgrpCd1">
								<input type="radio" name="mgrpCd" id="mgrpCd1" value="" checked="checked">
								<span>전체</span>
							</label>
							<label class="radio_box" for="mgrpCd2">
								<input type="radio" name="mgrpCd" id="mgrpCd2" value="즉시발송">
								<span>즉시발송</span>
							</label>
							<label class="radio_box" for="mgrpCd3">
								<input type="radio" name="mgrpCd" id="mgrpCd3" value="예약발송">
								<span>예약발송</span>
							</label>
						</td>
					</tr>
					<tr>
						<th>발송상태</th>
						<td colspan="3">
		                    <label class="radio_box" for="sgrpCd1">
								<input type="radio" name="sgrpCd" id="sgrpCd1" value="" checked="checked">
								<span>전체</span>
							</label>
							<label class="radio_box" for="sgrpCd2">
								<input type="radio" name="sgrpCd" id="sgrpCd2" value="발송완료">
								<span>발송완료</span>
							</label>
							<label class="radio_box" for="sgrpCd3">
								<input type="radio" name="sgrpCd" id="sgrpCd3" value="발송실패">
								<span>발송실패</span>
							</label>
							<label class="radio_box" for="sgrpCd4">
								<input type="radio" name="sgrpCd" id="sgrpCd4" value="발송예정">
								<span>발송예정</span>
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
					<col style="width:150px">
					<col style="">
					<col style="width:150px">
					<col style="width:150px">
					<col style="width:180px">
				</colgroup>
		        <thead>
		            <tr>
		                <th><input type="checkbox" class="checkbox-selectAll" id="checkbox-selectAll">번호</th>
		                <th>제목</th>
		                <th>발송타입</th>
		                <th>발송상태</th>
		                <th>발송일시</th>
		                <th>발송자</th>
		                <th>관리</th>
		            </tr>
		        </thead>
		        <tbody>
		            <c:choose>
		                <c:when test="${empty result.list}">
		                    <tr>
		                        <td colspan="7" class="nodata">등록된 게시물이 없습니다.</td>
		                    </tr>
		                </c:when>
		                <c:otherwise>
		                    <c:forEach items="${result.list}" var="data" varStatus="i">
		                        <tr>
		                            <td>
		                            	<input type="checkbox" id="sn_${data.blcSn}" name="delSeq" class="check2 checkbox-select" value="${data.blcSn}" title="선택"> ${function:rowNumber(result.searchInfo, i.count)}
		                            </td>
		                            <td class="left"><tag:html value="${data.blcTitl}" attr="NQ" /> ${function:printBoardListFileLink(data.fileSn, data.filNm)}</td>
		                            <td>${data.mgrpCd}</td>
		                            <td>${data.sgrpCd}</td>
		                            <td>${data.expsRgstDtm}</td>
		                            <td>${data.rgstId}</td>
		                            <td class="table_btn">
		                            	<a class="btn btn_red" href="${contextPath}/${requestUri}/view.vc?blcSn=${data.blcSn}${function:searchQuery(result.searchInfo)}">보기</a>
										<a href="${contextPath}/${requestUri}/delete.vc" class="btn btn_gray btnRowDelete"  data-sn="${data.blcSn}">삭제</a>
									</td>
		                        </tr>
		                    </c:forEach>
		                </c:otherwise>
		            </c:choose>
		        </tbody>
		    </table>
     
		    <div class="btn_center_gorup clearfix">
				<div class="left">
					<button type="button" class="btn btn_gray btnInfoCancle" onclick="">선택취소</button>
					<button type="submit" class="btn btn_gray" onclick="">선택삭제</button>
				</div>
				<div class="right">
					<button type="button" onclick="location.href='${contextPath}/${requestUri}/register.vc';" class="btn btn_bk">등록</button>
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
        $('input[name=searchStartDate]').val('${result.searchInfo.searchStartDate}');  
	 	$('input[name=searchEndDate]').val('${result.searchInfo.searchEndDate}');
	 	$('input[name=mgrpCd][value="${result.searchInfo.mgrpCd}"]').prop('checked', true).trigger('click'); 
    	$('input[name=sgrpCd][value="${result.searchInfo.sgrpCd}"]').prop('checked', true).trigger('click'); 
    });
});

var formSubmitObj = {
    submit : function(form){
        if(!submitUtil.isNull(form.searchKey)){
            if(!submitUtil.isMinLength(form.searchKey)){
                return false;    
            }        
        }
        
        if(!submitUtil.isNull(form.searchStartDate) && !submitUtil.isNull(form.searchEndDate)){
    		if(!submitUtil.isDateCompare(form.searchStartDate, form.searchEndDate)){
            	return false;
    		}
        }
        
        return true;
    },
    submitDel : function(object){
		var msg = '게시물을 삭제 하시겠습니까?'
	    if(confirm(msg)){
	        var data = { blcSn : $(object).data('sn') };
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