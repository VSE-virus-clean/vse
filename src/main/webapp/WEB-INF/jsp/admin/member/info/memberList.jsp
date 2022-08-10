<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
    JSP Name : memberList.jsp
    Description : 고객관리 / 고객목록
--%>
<div class="sec_top">
	<h3 class="sec_tit">고객 목록</h3>
	<ul class="top_tab">
		<li><a href="#">고객 관리</a></li>
	</ul>
</div>

<div class="sec_cont">
	<h4 class="cont_tit">조회조건</h4>
	<div class="r_search_box">
		<form name="searchForm" method="get" onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
		<input type="hidden" name="rowLimit" value="${result.searchInfo.rowLimit}" />
			<table class="r_search_table">
				<colgroup>
					<col style="width:150px">
					<col style="">
					<col style="width:150px">
					<col style="">
				</colgroup>
				<tbody>
					<tr>
						<th>회원등급</th>
						<td colspan="3">
							<select id="searchGubunType" name="searchGubunType">
		                        <option value="">선택</option>
		                    </select>
						</td>
					</tr>
					<tr>
						<th>검색어</th>
						<td colspan="3">
					   		<select id="searchType" name="searchType">
		                        <option value="">선택</option>
		                        <option value="id">아이디</option>
		                        <option value="name">이름</option>
		                        <option value="nick">닉네임</option>
		                        <option value="email">이메일</option>
		                        <option value="phone">휴대폰번호</option>
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
		<form name="downForm" method="get">
			<input type="hidden" name="searchGubunType" value="${result.searchInfo.searchGubunType}" />
			<input type="hidden" name="searchType" value="${result.searchInfo.searchType}" />
			<input type="hidden" name="searchKey" value="${result.searchInfo.searchKey}" />
		</form>
	</div>
	
	<div class="content-body">
		<div class="table_tit left_right">
			<div class="table_left_title">
				<span>총<strong class="totCnt" style="font-weight: 700;"> <fmt:formatNumber value="${result.searchInfo.totalRow}" type="number"/></strong>건 조회</span>
			</div>
    		<div class="right">
    			<a href="${contextPath}/${requestUri}/allList.vc" class="btnstyle blue excelDown"><span>엑셀 다운로드</span></a> 
    			<select name="rowLimit" id="rowLimit">
					<option value="10">10개씩보기</option>
                    <option value="20">20개씩보기</option>
                    <option value="50">50개씩보기</option>
                    <option value="100">100개씩보기</option>
                    <option value="1000">전건보기</option>
				</select>
    		</div>
		</div>
    
 			<table class="cont_table" style="table-layout:fixed">
	        <colgroup>
	            <col width="100px" />
	            <col width="130px" />
	            <col width="*" />
	            <col width="200px" />
	            <col width="150px" />
	            <col width="150px" />
	            <col width="150px" />
	            <col width="130px" />
	            <col width="130px" />
	        </colgroup>
	        <thead>
	            <tr>
	                <th>번호</th>
	                <th>회원등급</th>
	                <th>아이디</th>
	                <th>닉네임</th>
	                <th>이름</th>
	                <th>휴대번호</th>
	                <th>가입일</th>
	                <th>계정상태</th>
	                <th>관리</th>
	            </tr>
	        </thead>
	        <tbody>
	            <c:choose>
	                <c:when test="${empty result.list}">
	                    <tr>
	                        <td colspan="9">등록된 사용자가 없습니다.</td>
	                    </tr>
	                </c:when>
	                <c:otherwise>
	                    <c:forEach items="${result.list}" var="data" varStatus="i">
	                         <tr>
	                            <td>${function:rowNumber(result.searchInfo, i.count)}</td>
	                            <td>${data.mbrGrade}</td>
	                            <td class="left">${data.mbrId}</td>
	                            <td>${data.mbrNick}</td>
	                            <td>${data.mbrNm}</td>
	                            <td>${data.mbrHp}</td>
	                            <td>${data.rgstDtm}</td>
								<td class="table_btn">
									<c:choose>
										<c:when test="${data.stCd eq 'D'}">탈퇴<br/>${data.mdftDtm}</c:when>
										<c:otherwise>
			                                <a class="btn btn_gray stCdMod" data="${data.mbrSn}|${data.stCd  eq 'Y' ? 'N' : 'Y'}" href="#" >${data.stCd eq 'Y' ? '사용' : '사용중지'}</a>
										</c:otherwise>
									</c:choose>
								</td>
	                            <td class="table_btn">
	                            	<a class="btn btn_red" href="${contextPath}/${requestUri}/view.vc?mbrSn=${data.mbrSn}${function:searchQuery(result.searchInfo)}">상세</a>
								</td>
	                        </tr>
	                    </c:forEach>
	                </c:otherwise>
	            </c:choose>
	        </tbody>
	    </table>
    
	    <div class="paging_wrap">
	    	<tag:paging url="${contextPath}/${requestUri}/list.vc?${function:searchQueryPaging(result.searchInfo)}" totalRow="${result.searchInfo.totalRow}" page="${result.searchInfo.page}" rowLimit="${result.searchInfo.rowLimit}"/>
	    </div>
    </div>
</div>


<script type="text/javascript">
$(function () {
    $('form[name=searchForm]').ready(function(){
    	comCodeUtil.getCodeNPrint('GRADE', 'searchGubunType', 'select');

    	$('select[name=searchType] option[value="${result.searchInfo.searchType}"]').prop('selected', true);
        $('input[name=searchKey]').val('${result.searchInfo.searchKey}');  
        $('select[name=searchGubunType] option[value="${result.searchInfo.searchGubunType}"]').prop('selected', true);
        $('select[name=rowLimit] option[value="${result.searchInfo.rowLimit}"]').prop('selected', true);
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
    
    $('.excelDown').on('click', function(event){
		event.preventDefault();  
		formSubmitObj.excelSubmit();
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
            var data = { mbrSn : $(object).attr('data').split('|')[0], stCd : $(object).attr('data').split('|')[1]};
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
    },
	excelSubmit : function(){
		location.href = '${contextPath}/${requestUri}/allList.vc?' + $('form[name=downForm]').serialize(); 
	    return false;
	}
};
</script>