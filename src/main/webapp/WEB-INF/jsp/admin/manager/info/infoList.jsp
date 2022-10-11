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
	<h3 class="sec_tit">Admin List</h3>
	<!-- 
	<ul class="top_tab">
		<li><a href="#">운영자 관리</a></li>
	</ul>
	-->
</div>
<div class="sec_cont">
<!--<h4 class="cont_tit">조회조건</h4>-->
	<div class="r_search_box">
		<form name="searchForm" method="get" onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
			<table class="r_search_table">
				<%-- 
				<colgroup>
					<col style="width:150px">
					<col style="">
					<col style="width:150px">
					<col style="">
				</colgroup>
				--%>
				<tbody>
					<tr>
						<td>
					   		<select id="searchType" name="searchType">
		                        <option value="0">All</option>
		                        <option value="1">Id</option>
		                        <option value="2">Name</option>
		                    </select>
							<input id="searchKey" name="searchKey" type="text" title="the search input" style="width: calc(100% - 120px);" maxlength="100" minlength="2"/>
						</td>
					</tr>
				</tbody>
			</table>
			<div class="btn_right_gorup">
				<button type="submit" class="btn btn_red">search</button>
				<button type="reset" class="btn btn_gray">reset</button>
			</div>
		</form>
	</div>
	
	<div class="content-body">
		<div class="table_tit">
			<div class="table_left_title">
				<span>total<strong class="totCnt" style="font-weight: 700;"> <fmt:formatNumber value="${result.searchInfo.totalRow}" type="number"/></strong></span>
			</div>
		</div>
		<form name="subForm" method="post" action="${contextPath}/${requestUri}/delete.vc" onsubmit="var rtn = formSubmitObj.delSubmit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
   			<table class="cont_table" style="table-layout:fixed">
		        <colgroup>
		            <col width="3%" />
		            <col width="15%" />
		            <col width="33%" />
		            <col width="15%" />
		            <col width="10%" />
		            <col width="10%" />
		            <col width="7%" />
		            <col width="7%" />
		        </colgroup>
		        <thead>
		            <tr>
		                <th>No</th>
		                <th>Name</th>
		                <th>Id</th>
		                <th>Department</th>
		                <th>Registration date</th>
		                <th>Last connection date</th>
		                <th>Usage status</th>
		                <th>Management</th>
		            </tr>
		        </thead>
		        <tbody>
		            <c:choose>
		                <c:when test="${empty result.list}">
		                    <tr>
		                        <td colspan="8">There are no registered posts.</td>
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
		                                <a class="btn btn_gray stCdMod" data="${data.mgrSn}|${data.stCd  eq 'Y' ? 'N' : 'Y'}" href="#" >${data.stCd eq 'Y' ? 'Y' : 'N'}</a>
		                            </td>
		                            <td class="table_btn">
		                            	<a class="btn btn_red" href="${contextPath}/${requestUri}/modify.vc?mgrSn=${data.mgrSn}${function:searchQuery(result.searchInfo)}">mod</a>
									</td>
		                        </tr>
		                    </c:forEach>
		                </c:otherwise>
		            </c:choose>
		        </tbody>
		    </table>
    
		    <div class="btn_center_gorup clearfix">
				<div class="right">
					<button type="button" onclick="location.href='${contextPath}/${requestUri}/register.vc';" class="btn btn_bk">Registration</button>
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
        if(confirm('Do you want to change your account status?')){
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