<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
    JSP Name : infoList.jsp
    Description : 사이트 관리자 목록
--%>
<div class="sec_top">
	<h3 class="sec_tit">운영자 목록</h3>
	<ul class="top_tab">
		<li><a href="#">운영자 관리</a></li>
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
						<th>검색어</th>
						<td colspan="3">
					   		<select id="searchType" name="searchType">
		                        <option value="0">전체</option>
		                        <option value="1">아이디</option>
		                        <option value="2">이름</option>
		                    </select>
							<input id="searchKey" name="searchKey" type="text" title="상세검색입력" style="width:450px;" maxlength="100" minlength="2"/>
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
		            <col width="100px" />
		            <col width="130px" />
		            <col width="*" />
		            <col width="150px" />
		            <col width="110px" />
		            <col width="150px" />
		            <col width="100px" />
		            <col width="100px" />
		        </colgroup>
		        <thead>
		            <tr>
		                <th>번호</th>
		                <th>이름</th>
		                <th>아이디</th>
		                <th>부서</th>
		                <th>등록일</th>
		                <th>최종접속일</th>
		                <th>계정상태</th>
		                <th>관리</th>
		            </tr>
		        </thead>
		        <tbody>
		            <c:choose>
		                <c:when test="${empty result.list}">
		                    <tr>
		                        <td colspan="8">등록된 사용자가 없습니다.</td>
		                    </tr>
		                </c:when>
		                <c:otherwise>
		                    <c:forEach items="${result.list}" var="data" varStatus="i">
		                         <tr>
		                            <td>${function:rowNumber(result.searchInfo, i.count)}</td>
		                            <td>${data.mgrNm}</td>
		                            <td class="left">${data.mgrId}</td>
		                            <td>${data.mgrOpsNm}</td>
		                            <td>${data.rgstDtm}</td>
		                            <td>${data.finCnncDtm}</td>
		                            <td>
		                                <a class="btn btn_gray stCdMod" data="${data.mgrSn}|${data.stCd  eq 'Y' ? 'N' : 'Y'}" href="#" >${data.stCd eq 'Y' ? '사용' : '사용중지'}</a>
		                            </td>
		                            <td class="table_btn">
		                            	<a class="btn btn_red" href="${contextPath}/${requestUri}/modify.vc?mgrSn=${data.mgrSn}${function:searchQuery(result.searchInfo)}">수정</a>
									</td>
		                        </tr>
		                    </c:forEach>
		                </c:otherwise>
		            </c:choose>
		        </tbody>
		    </table>
    
		    <div class="btn_center_gorup clearfix">
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
    	$('select[name=searchType] option[value="${result.searchInfo.searchType}"]').prop('selected', true);
        $('input[name=searchKey]').val('${result.searchInfo.searchKey}');  
    });
    
    /**
     * 계정상태 변경
     */
    $('a.stCdMod').on({
        'click' : function(event){
            event.preventDefault();  
            formSubmitObj.submitStCdMod($(this));
        }
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
    /**
     * 계정상태 변경
     * - 사용 -> 사용중지
     * - 사용중지 -> 사용
     */
    submitStCdMod : function(object){
        if(confirm('계정상태를 변경하시겠습니까?')){
            var data = { mgrSn : $(object).attr('data').split('|')[0], stCd : $(object).attr('data').split('|')[1]};
            ajaxUtil.postDisableAsync('${contextPath}/${requestUri}/stcdMod.vc', data, formSubmitObj.resultStCdMod);
        }
    },
    resultStCdMod : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        if(json.bindingStatus == undefined && json.result != undefined && json.result.status){
            location.reload(true);
        }else{
            ajaxUtil.error(json);     
        }
    }
};
</script>