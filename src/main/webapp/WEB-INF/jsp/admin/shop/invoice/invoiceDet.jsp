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
<link type="text/css" rel="stylesheet" href="/resources/admin/ui_common/css/popup.css?v=${cacheParam}" />

<div class="sec_top">
	<h3 class="sec_tit">송장등록</h3>
	<ul class="top_tab">
		<li><a href="#">주문관리</a></li>
		<li><a href="#">송장등록</a></li>
	</ul>
</div>
<div class="sec_cont">
	<h4 class="cont_tit">상세정보</h4>
	<div class="r_search_box">
        <table class="r_search_table">
            <colgroup>
                <col width="170px" /><col width="*" /><col width="170px" /><col width="*" />
            </colgroup>
            <tr>
                <th>작성자</th>
                <td>${result.info.rgstId}</td>
                <th>등록시간</th>
                <td>${result.info.rgstDtm}</td>
            </tr>
            
			<tr>
                <th>결과</th>
            	<td>            
            		<c:choose>
                    	<c:when test="${empty result.info.blcSbc1}">
                          	등록 에러
                        </c:when>
                        <c:otherwise>
                        	<c:forTokens var="cnt" items="${result.info.blcSbc1}" delims="|" varStatus="i">
                        		<p>
                        		<c:if test="${i.index eq 0}">전체 : </c:if>
                        		<c:if test="${i.index eq 1}">성공 :</c:if>
                        		<c:if test="${i.index eq 2}">실패 : </c:if>
								${cnt}건
								</p>
							</c:forTokens>
						</c:otherwise>
					</c:choose>
				</td>	
			</tr>
									
            <tr>    
                <th>상세내용</th>
                <td colspan="3" class="con" style="line-height:1.6em;padding-top:0px;">
                    <tag:html value="${result.info.blcSbc2}" attr="BR" />
                </td>   
            </tr>
            <tr>    
                <th>첨부파일</th>
                <td colspan="3" class="con">
                	<p>${function:printAttachFileList("Y", result.file.list)}</p>
                </td>   
            </tr>
        </table>
        
        <div class="btn_center_gorup clearfix">
			<div class="left">
				<button type="button" class="btn btn_gray" onclick="location.href='${contextPath}/${requestUri}/list.vc?${function:searchQuery(result.searchInfo)}'; ">목록</button>
			</div>
		</div>
   	</div>
</div>
