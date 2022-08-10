<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tag" uri="http://www.jksoft.com/taglib/tag" %>
<%@ taglib prefix="function" uri="http://www.jksoft.com/taglib/function" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<%-- 
    JSP Name : noticeMod.jsp
    Description : 게시판관리 > INFORMATION > NOTICE 수정폼
--%>
<div class="sec_top">
	<h3 class="sec_tit">정품관리 목록</h3>
	<ul class="top_tab">
		<li><a href="#">정품관리</a></li>
	</ul>
</div>
<div class="sec_cont">
	<h4 class="cont_tit">정품관리</h4>
	<div class="r_search_box">
	    <form name="mainForm" method="post" enctype="multipart/form-data" onsubmit="var rtn = formSubmitObj.submit(this); if(!rtn){ submitUtil.enable(); } return rtn;">
	    	<input name="orgSn" type="hidden" value="${result.info.orgSn}"/>
	        <table class="r_search_table">
	            <colgroup>
	                 <col width="190px" /><col width="*" /><col width="190px" /><col width="*" />
	            </colgroup>
	            <tr>    
	                <th>등록자</th>
	                <td colspan="3">${result.info.mbrId} ( ${result.info.mbrNick} )</td> 
	            </tr>
	            <tr>    
	                <th>등록일</th>
	                <td colspan="3">${result.info.userViewDtm}</td>
	            </tr>
	            <tr>    
	                <th>보증기간</th>
	                <td colspan="3">${result.info.wrntStrDtm}<em>&nbsp;~&nbsp;</em>${result.info.wrntFnhDtm}</td>
	            </tr>
	            <tr>    
	                <th>제품명</th>
	                <td colspan="3">
	                	<tag:html value="${result.info.prdTitl}" attr="NQ" />
	                </td> 
	            </tr>
	            <tr>    
	                <th>시리얼번호</th>
	                <td colspan="3">
	                	<input type="text" id="serialNo" name="serialNo" value="${result.info.serialNo}" style="width:60%" title="시리얼번호">
	                </td>
	            </tr>
	            <tr>    
	                <th>구입처</th>
	                <td colspan="3">
	                	<c:choose>
	                		<c:when test="${result.info.makCd eq 'MARKET-01'}">바이러스 클린 랩 공식몰</c:when>
	                		<c:when test="${result.info.makCd eq 'MARKET-02'}">네이버 스토어팜</c:when>
	                		<c:when test="${result.info.makCd eq 'MARKET-03'}">오픈마켓(11번가, G마켓, 쿠팡 등)</c:when>
	                		<c:when test="${result.info.makCd eq 'MARKET-04'}">브랜드몰(신세계, 롯데 등)</c:when>
	                		<c:when test="${result.info.makCd eq 'MARKET-05'}">기타(중고거래 등)</c:when>
	                	</c:choose>
	                </td>
	            </tr>
	            <tr>    
	                <th>사유</th>
	                <td colspan="3" class="con">
	                    <tag:html value="${result.info.orgSbc}" attr="BR" />
	                </td>  
	            </tr>
	            <tr>    
	                <th>첨부파일</th>
	                <td colspan="3" class="con">
	                	<div class="filebox">
		                	<p>${function:printImageFile("THUMB", result.file.list)}</p>
	                    </div>
	                </td>   
	            </tr>
	        </table>
	        <div class="btn_center_gorup clearfix">
				<div class="left">
					<button type="button" class="btn btn_gray" onclick="location.href='${contextPath}/${requestUri}/list.vc?${function:searchQuery(result.searchInfo)}'; ">목록</button>
				</div>
				<div class="right">
					<button type="submit" class="btn btn_red">수정</button>
				</div>
			</div>
	    </form>
	</div>
</div>
<script>
var formSubmitObj = {
    submit : function(form){
        
    	if(!submitUtil.isEmpty(form.serialNo)){
            return false;
        }
        
    	var msg = '시러얼 정보를 수정 하시겠습니까?';
   	    if(confirm(msg)){
        	ajaxUtil.formSubmit($(form), formSubmitObj.result);
   	    }
        
        return false;
    },
    result : function(json){
        try{
            json = JSON.parse(json);
        }catch(e){}
        
        if(json.bindingStatus == undefined && json.result != undefined){
        	alert('정보가 수정 도었습니다.');
           	location.reload(true);
        }else{
           	ajaxUtil.error(json);
        }
    }
};
</script>
